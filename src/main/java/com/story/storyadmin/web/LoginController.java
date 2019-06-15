package com.story.storyadmin.web;

import com.alibaba.fastjson.JSONObject;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.domain.entity.sysmgr.User;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.ResourceNode;
import com.story.storyadmin.domain.vo.sysmgr.UserVo;
import com.story.storyadmin.service.sysmgr.AuthorityService;
import com.story.storyadmin.service.sysmgr.ResourceService;
import com.story.storyadmin.service.sysmgr.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lianghuaibin on 2018/12/26 11:02
 **/
@Controller
@RequestMapping(value="/user")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ResourceService resourceService;

    @Autowired
    AuthorityService authorityService;

    @Value("${project.domain}")
    String domain;


    /**
     * 登录
     * @param user
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/login")
    @ResponseBody
    public Result login(HttpServletResponse response, @RequestBody UserVo user) {
        return userService.login(user,response);
    }

    /**
     * erp登录验证ticket，生成本地token，由本地来管理token生命周期
     * @return
     */
    @RequestMapping(value="/valid_erp")
    @ResponseBody
    public Result loginErp(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return userService.loginErp(response);
    }

    /**
     * 获取登录用户基础信息
     * @return
     */
    @RequiresAuthentication
    @RequestMapping(value="/info")
    @ResponseBody
    public Result info(){
        Result result = new Result();
        result.setResult(true);
        result.setCode(Constants.TOKEN_CHECK_SUCCESS);
        JSONObject json = new JSONObject();

        User user;
        user = userService.findUserByAccount(JwtUtil.getClaim(SecurityUtils.getSubject().getPrincipal().toString(), SecurityConsts.ACCOUNT));

        json.put("name", user.getName());
        json.put("erp", user.getErpFlag());

        json.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        json.put("roles",new String[]{"admin"});

        //查询菜单
        List<ResourceNode> menus = resourceService.findByUserId(user.getId());

        //查询权限
        List<Object> authorityList = authorityService.findByUserId(user.getId());

        json.put("menus",menus);
        json.put("auth",authorityList);

        result.setData(json);
        return result;
    }

}
