package com.story.storyadmin.web.sysmgr;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.SysLogAnnotation;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.config.shiro.security.UserContext;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.domain.vo.sysmgr.UserPassword;
import com.story.storyadmin.domain.vo.sysmgr.UserRoleVo;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.service.sysmgr.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/sysmgr/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    AuthorityService authorityService;

    /**
     * 分页查询
     * @param user
     * @param pageNo
     * @param limit
     * @return
     */
    @SysLogAnnotation
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/list",method = {RequestMethod.POST,RequestMethod.GET})
    public Result list(User user,
                       @RequestParam(defaultValue = "1")int pageNo,
                       @RequestParam(defaultValue = "10")int limit){
        Result result = new Result();
        Page<User> page = new Page(pageNo, limit);
        QueryWrapper<User> eWrapper = new QueryWrapper(user);
        eWrapper.eq("yn_flag","1");
        IPage<User> list = userService.page(page, eWrapper);
        result.setData(list);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }


    /**
     * 根据Id查询
     * @param user
     * @return
     */
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/find",method = {RequestMethod.POST})
    public Result findById(@RequestBody User user){
        User userBean= userService.getById(user.getId());
        userBean.setPassword("********");

        Result result = new Result();
        result.setData(userBean);
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        return result;
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/save",method = {RequestMethod.POST})
    public Result save(@RequestBody User user){
        return userService.persist(user);
    }

    /**
     * 删除
     * @param user
     * @return
     */
    @RequiresPermissions("sysmgr.user.delete")
    @RequestMapping(value="/delete",method = {RequestMethod.POST})
    public Result dropById(@RequestBody User user){
        Result result ;
        if(user.getId()!=null){
            User delUser= new User();
            delUser.setId(user.getId());
            delUser.setYnFlag("0");
            delUser.setEditor(UserContext.getCurrentUser().getAccount());
            delUser.setModifiedTime(Date.from(Instant.now()));
            result=new Result(userService.updateById(delUser),null,null,Constants.TOKEN_CHECK_SUCCESS);
        }else{
            result = new Result(false, "", null ,Constants.PARAMETERS_MISSING);
        }
        return result;
    }

    /**
     * 根据用户Id查询角色
     * @param user
     * @return
     */
    @RequiresPermissions("sysmgr.user.query")
    @RequestMapping(value="/findUserRole",method = {RequestMethod.POST})
    public Result findUserRole(@RequestBody UserRoleVo user){
        return userService.findUserRole(user.getUserId());
    }

    /**
     * 更改用户角色
     * @param userRole
     * @return
     */
    @RequiresPermissions("sysmgr.user.save")
    @RequestMapping(value="/saveUserRole",method = {RequestMethod.POST})
    public Result saveUserRole(@RequestBody UserRoleVo userRole){
        return userService.saveUserRoles(userRole);
    }

    /**
     * 修改密码
     * @param userPassword
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value="/editpassword",method = {RequestMethod.POST})
    public Result editPassWord(@RequestBody UserPassword userPassword){
        return userService.editPassWord(userPassword);
    }

    /**
     * 获取登录用户基础信息
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value="/info",method = RequestMethod.GET)
    public Result info(){
        User user = userService.findUserByAccount(JwtUtil.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));
        //查询菜单
        List<ResourceNode> menus = resourceService.findByUserId(user.getId());
        //查询权限
        List<Object> authorityList = authorityService.findByUserId(user.getId());

        JSONObject json = new JSONObject();
        json.put("name", user.getName());
        json.put("erp", user.getErpFlag());
        json.put("avatar","");
        json.put("roles",new String[]{"admin"});
        json.put("menus",menus);
        json.put("auth",authorityList);

        return new Result(true,null,json,Constants.TOKEN_CHECK_SUCCESS);
    }
}
