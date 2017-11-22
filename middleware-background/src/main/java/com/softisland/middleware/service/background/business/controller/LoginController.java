package com.softisland.middleware.service.background.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fengrongze on 2017/10/30.
 */
@Controller
public class LoginController {


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("mySiteId",1);

        return "index";
    }





}
