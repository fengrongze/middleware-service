package com.softisland.middleware.service.background.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.softisland.middleware.domain.bean.db.SysSite;
import com.softisland.middleware.service.background.business.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by softisland on 2017/10/31.
 */
@Controller
public class SiteController {
    private static Logger logger = LoggerFactory.getLogger(SiteController.class);

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/editsite/{siteId}",method = RequestMethod.GET)
    public ModelAndView editSiteSelect(@PathVariable Integer siteId) {

        SysSite sysSite = siteService.getSysSite(siteId);

        ModelAndView modelAndView = new ModelAndView("/edit-site");
        modelAndView.addObject("site_id",siteId);
        modelAndView.addObject("site_name", sysSite.getSiteName());
        modelAndView.addObject("site_domain", sysSite.getSiteDomain());
        return modelAndView;
    }

    @RequestMapping(value = "/editsite/update",method = RequestMethod.POST)
//    @ResponseBody
    public String editSiteSave(SysSite sysSite,HttpServletResponse response) {
        int res = siteService.updateSysSite(sysSite);

        JSONObject object = new JSONObject();
        object.put("code",String.valueOf(res));
        return String.valueOf(res);
    }
}
