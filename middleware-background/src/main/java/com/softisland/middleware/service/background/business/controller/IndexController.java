package com.softisland.middleware.service.background.business.controller;

import com.softisland.middleware.domain.bean.db.SysUser;
import com.softisland.middleware.service.background.business.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    SysUserService sysUserService;
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/user")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        List<SysUser> users=sysUserService.queryListSysUser();
        for (SysUser user : users) {
        }

        request.getSession().setAttribute("userid", "123");

        return "index";
    }


    @RequestMapping("/sessiones")
    public String sessions(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getSession().getAttribute("userid"));
        return "sessions";
    }




}
