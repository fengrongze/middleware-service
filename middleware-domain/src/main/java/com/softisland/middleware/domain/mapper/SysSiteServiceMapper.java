package com.softisland.middleware.domain.mapper;

import com.softisland.middleware.domain.bean.db.SysSiteService;
import com.softisland.middleware.domain.bean.db.SysSiteServiceExample;
import java.util.List;
import java.util.Map;

import com.softisland.middleware.domain.pojo.SiteServiceInfo;
import com.softisland.middleware.domain.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysSiteServiceMapper extends MyMapper<SysSiteService> {

    SysSiteService querySiteServiceByServiceDomain(Map<String, String> para);

    List<SiteServiceInfo> getSiteService(Integer siteId);
}