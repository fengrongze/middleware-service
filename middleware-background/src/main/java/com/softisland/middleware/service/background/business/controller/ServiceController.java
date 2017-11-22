package com.softisland.middleware.service.background.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.softisland.middleware.domain.bean.db.SysService;
import com.softisland.middleware.domain.bean.db.SysSite;
import com.softisland.middleware.domain.bean.db.SysSiteService;
import com.softisland.middleware.domain.pojo.SiteServiceInfo;
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
import java.util.List;

/**
 * Created by softisland on 2017/11/1.
 */
@Controller
public class ServiceController {
    private static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/siteservice/{siteId}",method = RequestMethod.GET)
    public ModelAndView getSiteService(@PathVariable Integer siteId) {
        List<SiteServiceInfo> infos = siteService.getSiteService(siteId);
        ModelAndView modelAndView = new ModelAndView("/siteserviceinfo");
        modelAndView.addObject("siteServiceInfos", infos);
        return modelAndView;
    }

    @RequestMapping(value = "/editsiteservice/{serviceId}",method = RequestMethod.GET)
    public ModelAndView editSiteSelect(@PathVariable Integer serviceId) {

        SysSiteService sysSiteService = siteService.getSiteServiceById(serviceId);

       // SysService sysService = siteService.getServiceById(serviceId);


        ModelAndView modelAndView = new ModelAndView("/edit-service");
        modelAndView.addObject("service_id",serviceId);
        modelAndView.addObject("service_domain", sysSiteService.getServiceDomain());
        return modelAndView;
    }


    @RequestMapping(value = "/editsiteservice/update",method = RequestMethod.POST)
    public String editSiteSave(SysSiteService service,HttpServletResponse response) {
        int res = siteService.updateSiteService(service);
        JSONObject object = new JSONObject();
        object.put("code",String.valueOf(res));
        return String.valueOf(res);
    }
}
