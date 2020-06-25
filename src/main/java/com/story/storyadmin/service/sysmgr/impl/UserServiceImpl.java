package com.story.storyadmin.service.sysmgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.story.storyadmin.config.shiro.LoginUser;
import com.story.storyadmin.config.shiro.ShiroKit;
import com.story.storyadmin.config.shiro.security.JwtProperties;
import com.story.storyadmin.config.shiro.security.JwtToken;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.constant.sysmgr.UserStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.LoginLog;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.entity.sysmgr.UserRole;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.UserPassword;
import com.story.storyadmin.domain.vo.sysmgr.UserRoleVo;
import com.story.storyadmin.domain.vo.sysmgr.UserVo;
import com.story.storyadmin.mapper.sysmgr.UserMapper;
import com.story.storyadmin.service.sysmgr.LoginLogService;
import com.story.storyadmin.service.sysmgr.UserRoleService;
import com.story.storyadmin.service.sysmgr.UserService;
import com.story.storyadmin.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sunnj
 * @since 2018-12-28
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    JedisUtils jedisUtils;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    UserRoleService userRoleService;

    @Override
    public User findUserByAccount(String account) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account", account);
        wrapper.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());

        List<User> userList = baseMapper.findUserByAccount(wrapper);
        return userList.size() > 0 ? userList.get(0) : null;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Result login(UserVo user, HttpServletResponse response) {
        Assert.notNull(user.getUsername(), "用户名不能为空");
        Assert.notNull(user.getPassword(), "密码不能为空");

        User userBean = this.findUserByAccount(user.getUsername());

        if (userBean == null) {
            return new Result(false, "用户不存在", null, Constants.PASSWORD_CHECK_INVALID);
        }

        //ERP账号直接提示账号不存在
        if (YNFlagStatusEnum.VALID.getCode().equals(userBean.getErpFlag())) {
            return new Result(false, "账号不存在", null, Constants.PASSWORD_CHECK_INVALID);
        }

        String encodePassword = ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT);
        if (!encodePassword.equals(userBean.getPassword())) {
            return new Result(false, "用户名或密码错误", null, Constants.PASSWORD_CHECK_INVALID);
        }

        //账号是否锁定
        if (UserStatusEnum.LOCK.code().equals(userBean.getStatus())) {
            return new Result(false, "该账号已被锁定", null, Constants.PASSWORD_CHECK_INVALID);
        }

        String strToken;
        try (UserContext context = new UserContext(new LoginUser(userBean.getAccount()))) {
            strToken = this.loginSuccess(userBean.getAccount(),  response);

            Subject subject = SecurityUtils.getSubject();
            AuthenticationToken token = new JwtToken(strToken);
            subject.login(token);
        }

        //登录成功
        return new Result(true, "登录成功", strToken, Constants.TOKEN_CHECK_SUCCESS);
    }

    /**
     * 登录后更新缓存，生成token，设置响应头部信息
     * @param account
     * @param response
     */
    private String loginSuccess(String account,HttpServletResponse response) {
        long millTimes= System.currentTimeMillis();

        //生成token
        String token = this.genToken(account, millTimes);

        //记录登录日志
        LoginLog loginLog= new LoginLog();
        loginLog.setAccount(account);
        loginLog.setLoginTime(Date.from(Instant.now()));
        loginLog.setContent("登录成功");
        loginLog.setYnFlag(YNFlagStatusEnum.VALID.getCode());
        loginLog.setCreator(account);
        loginLog.setEditor(account);
        loginLog.setCreatedTime(loginLog.getLoginTime());
        loginLogService.save(loginLog);

        //写入header
        response.setHeader(SecurityConsts.REQUEST_AUTH_HEADER, token);
        response.setHeader("Access-Control-Expose-Headers", SecurityConsts.REQUEST_AUTH_HEADER);

        return token;
    }

    @Override
    public String genToken(String account, long millTimes) {
        log.info(String.format("为账户%s颁发新的令牌", account));

        String currentTimeMillis = String.valueOf(millTimes);
        String token = JwtUtil.sign(account, String.valueOf(millTimes));

        //更新RefreshToken缓存的时间戳
        String refreshTokenKey= SecurityConsts.PREFIX_SHIRO_REFRESH_TOKEN + account;
        jedisUtils.saveString(refreshTokenKey, currentTimeMillis, jwtProperties.getTokenExpireTime()*60);
        return token;
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @Override
    public Result persist(User user) {

        Date currentDate = Date.from(Instant.now());
        if (user.getId() == null) {
            User existUser = this.findUserByAccount(user.getAccount());
            if (existUser != null) {
                //账号已存在
                return new Result(false, "账号已经存在");
            } else {
                //保存密码
                if (!StringUtils.isEmpty(user.getPassword())) {
                    user.setPassword(ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT));
                }
                user.setLastPwdModifiedTime(Date.from(Instant.now()));
//                user.setStatus(UserStatusEnum.NORMAL.code());
                user.setYnFlag(YNFlagStatusEnum.VALID.getCode());
                user.setEditor(UserContext.getCurrentUser().getAccount());
                user.setCreator(UserContext.getCurrentUser().getAccount());
                user.setCreatedTime(currentDate);
                user.setModifiedTime(currentDate);
                //新增用户
                baseMapper.insert(user);
            }
        } else {
            User userBean = baseMapper.selectById(user.getId());
            if (user.getAccount().equals(userBean.getAccount())) {
                if (!user.getPassword().equals("********")) {
                    //修改密码
                    user.setPassword(ShiroKit.md5(user.getPassword(), SecurityConsts.LOGIN_SALT));
                    user.setLastPwdModifiedTime(Date.from(Instant.now()));
                } else {
                    user.setPassword(userBean.getPassword());
                    user.setLastPwdModifiedTime(userBean.getLastPwdModifiedTime());
                }
                user.setEditor(UserContext.getCurrentUser().getAccount());
                user.setModifiedTime(currentDate);
                //更新用户
                baseMapper.updateById(user);
            } else {
                return new Result(false, "账号不能修改", null, Constants.PARAMETERS_MISSING);
            }
        }

        return new Result(true, "修改成功", null, Constants.TOKEN_CHECK_SUCCESS);
    }

    @Override
    public Result findUserRole(Long userId) {
        QueryWrapper<UserRole> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("yn_flag",YNFlagStatusEnum.VALID.getCode());
        queryWrapper.eq("user_id",userId);
        List<UserRole> userRoles = userRoleService.list(queryWrapper);
        List<Long> auths= userRoles.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
        return new Result(true, null, auths, Constants.TOKEN_CHECK_SUCCESS);
    }

    /**
     * 保存用户角色
     * @param userRole
     * @return
     */
    @Override
    public Result saveUserRoles(UserRoleVo userRole) {
        Date currentDate = Date.from(Instant.now());

        //先删除
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("yn_flag",YNFlagStatusEnum.VALID.getCode());
        queryWrapper.eq("user_id",userRole.getUserId());

        UserRole user = new UserRole();
        user.setEditor(UserContext.getCurrentUser().getAccount());
        user.setModifiedTime(currentDate);
        user.setYnFlag(YNFlagStatusEnum.FAIL.getCode());
        userRoleService.update(user,queryWrapper);

        //再插入
        UserRole tempUserRole;
        List<UserRole> authList = new ArrayList<>();
        for (Long roleId : userRole.getRoleIds()) {
            tempUserRole = new UserRole(userRole.getUserId(), roleId);
            tempUserRole.setYnFlag(YNFlagStatusEnum.VALID.getCode());
            tempUserRole.setEditor(UserContext.getCurrentUser().getAccount());
            tempUserRole.setCreator(UserContext.getCurrentUser().getAccount());
            tempUserRole.setCreatedTime(currentDate);
            tempUserRole.setModifiedTime(currentDate);
            authList.add(tempUserRole);
        }
        userRoleService.saveBatch(authList);

        return new Result(true, null, null, Constants.TOKEN_CHECK_SUCCESS);
    }

    /**
     * 修改密码
     * @param userPassword
     * @return
     */
    @Override
    public Result editPassWord(UserPassword userPassword) {
        if (!StringUtils.isEmpty(userPassword.getPassword()) && !StringUtils.isEmpty(userPassword.getNewPassword())) {

            User user = this.findUserByAccount(UserContext.getCurrentUser().getAccount());

            String encodeNewPassword = ShiroKit.md5(userPassword.getPassword(), SecurityConsts.LOGIN_SALT);
            if (YNFlagStatusEnum.FAIL.getCode().equals(user.getErpFlag())) {
                if (user.getPassword().equals(encodeNewPassword)) {
                    User entity = new User();
                    entity.setPassword(ShiroKit.md5(userPassword.getNewPassword(), SecurityConsts.LOGIN_SALT));
                    entity.setEditor(UserContext.getCurrentUser().getAccount());

                    entity.setEditor(UserContext.getCurrentUser().getAccount());
                    entity.setModifiedTime(Date.from(Instant.now()));

                    QueryWrapper<User> wrapper = new QueryWrapper<>();
                    wrapper.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());
                    wrapper.eq("account", user.getAccount());

                    baseMapper.update(entity, wrapper);

                    return new Result(true, "修改成功", null, Constants.TOKEN_CHECK_SUCCESS);
                } else {
                    //原始密码错误
                    return new Result(false, "原密码错误", null, Constants.PASSWORD_CHECK_INVALID);
                }
            } else {
                return new Result(false, "参数不完整", null, Constants.PARAMETERS_MISSING);
            }
        }
        return new Result(false, "参数不完整", null, Constants.PARAMETERS_MISSING);
    }

}