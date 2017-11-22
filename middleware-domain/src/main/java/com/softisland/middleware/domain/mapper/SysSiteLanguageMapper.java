package com.softisland.middleware.domain.mapper;

import com.softisland.middleware.domain.bean.db.SysSiteLanguage;

import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSiteLanguageMapper extends MyMapper<SysSiteLanguage> {

    List<LanguageView> querySiteLanguageList(@Param("siteId") Integer siteId);
}