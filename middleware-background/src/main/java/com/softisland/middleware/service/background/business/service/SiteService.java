package com.softisland.middleware.service.background.business.service;

import com.softisland.middleware.domain.bean.db.*;
import com.softisland.middleware.domain.mapper.*;
import com.softisland.middleware.domain.pojo.SiteServiceInfo;
import com.softisland.middleware.domain.pojo.UserSiteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by softisland on 2017/10/31.
 */
@Service
public class SiteService {
    private static Logger logger = LoggerFactory.getLogger(SiteService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysSiteMapper sysSiteMapper;

    @Autowired
    private SysUserSiteMapper sysUserSiteMapper;

    @Autowired
    private SysSiteServiceMapper sysSiteServiceMapper;

    @Autowired
    private SysServiceMapper sysServiceMapper;

    public List<UserSiteInfo> getUserSiteInfo(int userID)
    {
        return sysUserSiteMapper.getUserSite(userID);
    }

    public SysSite getSysSite(int siteId)
    {
        SysSite sysSite = new SysSite();
        sysSite.setId(siteId);
        return sysSiteMapper.selectOne(sysSite);
    }

    public int updateSysSite(SysSite sysSite)
    {
        SysSiteExample example = new SysSiteExample();
        SysSiteExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(sysSite.getId());
        return sysSiteMapper.updateByExampleSelective(sysSite,example);
    }

    public List<SiteServiceInfo> getSiteService(int siteId)
    {
        return sysSiteServiceMapper.getSiteService(siteId);
    }

    public SysSiteService getSiteServiceById(int serviceId)
    {
        SysSiteService sysSiteService = new SysSiteService();
        sysSiteService.setId(serviceId);
        return sysSiteServiceMapper.selectOne(sysSiteService);
    }

    public SysService getServiceById(int serviceId)
    {
        SysService sysService = new SysService();
        sysService.setId(serviceId);
        return sysServiceMapper.selectOne(sysService);
    }

    public int updateSiteService(SysSiteService siteService)
    {
        SysSiteServiceExample example = new SysSiteServiceExample();
        SysSiteServiceExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(siteService.getId());
        return sysSiteServiceMapper.updateByExampleSelective(siteService,example);
    }

}
