package com.softisland.middleware.service.background.business.service;

import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.mapper.SysSiteLanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengrongze on 2017/11/13.
 */
@Service
public class SiteLanguageService {
    @Autowired
    private SysSiteLanguageMapper sysSiteLanguageMapper;

    public List<LanguageView> querySiteLanguageList(Integer siteId) {
        return sysSiteLanguageMapper.querySiteLanguageList(siteId);
    }
}
