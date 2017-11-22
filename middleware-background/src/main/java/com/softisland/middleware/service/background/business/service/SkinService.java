package com.softisland.middleware.service.background.business.service;

import com.softisland.middleware.domain.bean.db.SysSiteSkin;
import com.softisland.middleware.domain.bean.db.SysSiteSkinExample;
import com.softisland.middleware.domain.mapper.SysSiteSkinMapper;
import com.softisland.middleware.domain.pojo.UserSiteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by softisland on 2017/11/1.
 */
@Service
public class SkinService {
    private static Logger logger = LoggerFactory.getLogger(SkinService.class);

    @Autowired
    private SysSiteSkinMapper siteSkinMapper;

    public List<SysSiteSkin> getSiteSkinList(int siteId)
    {
        SysSiteSkinExample example = new SysSiteSkinExample();
        SysSiteSkinExample.Criteria criteria = example.createCriteria();
        criteria.andSiteIdEqualTo(siteId);
        return siteSkinMapper.selectByExample(example);
    }
}
