package com.story.storyadmin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(value="/",method = {RequestMethod.GET})
    public String welcome(){
        return "welcome";
    }

}
