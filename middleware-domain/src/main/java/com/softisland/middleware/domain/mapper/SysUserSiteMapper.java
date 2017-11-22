package com.softisland.middleware.domain.mapper;

import com.softisland.middleware.domain.bean.db.SysUserSite;
import com.softisland.middleware.domain.bean.db.SysUserSiteExample;
import java.util.List;

import com.softisland.middleware.domain.pojo.SiteServiceInfo;
import com.softisland.middleware.domain.pojo.UserSiteInfo;
import com.softisland.middleware.domain.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserSiteMapper extends MyMapper<SysUserSite> {
    List<UserSiteInfo> getUserSite(Integer userId);
}