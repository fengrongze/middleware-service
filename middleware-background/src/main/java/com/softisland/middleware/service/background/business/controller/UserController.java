package com.softisland.middleware.service.background.business.controller;

import com.softisland.middleware.domain.pojo.UserSiteInfo;
import com.softisland.middleware.service.background.business.service.SiteService;
import com.softisland.middleware.service.background.business.service.SysUserService;
import org.springframework.stereotype.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by softisland on 2017/10/27.
 */
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private SiteService siteService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "login";
    }

    @RequestMapping(value = "/usersiteinfo",method = RequestMethod.GET)
    public ModelAndView userManage(HttpServletRequest request, HttpServletResponse response)
    {
        //从session中获取登录信息
        int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        userID = 1;
        List<UserSiteInfo> infos = siteService.getUserSiteInfo(userID);
        ModelAndView modelAndView = new ModelAndView("/usersiteinfo");
        modelAndView.addObject("userSiteInfos", infos);
        return modelAndView;
    }

    @RequestMapping(value = "/user/set",method = RequestMethod.GET)
    public ModelAndView userset(HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("/passwordset");
        return modelAndView;
    }

    @RequestMapping(value = "/user/updatepwd",method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(String oldpassowrd,String newpassword,HttpServletRequest request)
    {
        //从session中获取登录信息
        int userID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        userID = 1;

        boolean isExists = sysUserService.selectUserByPwdAndId(userID,oldpassowrd);
        if(!isExists)
        {
            //旧密码不正确
            return String.valueOf(-1);
        }
        int res = sysUserService.updateUserPwd(userID,newpassword);
        return String.valueOf(res);
    }
}
