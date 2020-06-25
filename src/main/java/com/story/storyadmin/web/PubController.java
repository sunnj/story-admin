package com.story.storyadmin.web;


import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.vo.Result;
import com.story.storyadmin.domain.vo.sysmgr.UserVo;
import com.story.storyadmin.service.sysmgr.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by sunnj
 **/
@Controller
@RequestMapping(value="/pub")
public class PubController {

    @Autowired
    UserService userService;

    @Value("${project.domain}")
    String domain;

    /**
     * 登录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/login",method = {RequestMethod.POST})
    public Result login(HttpServletResponse response, @RequestBody UserVo user) {
        response.setHeader("Access-Control-Allow-Origin", domain);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return userService.login(user,response);
    }
}
