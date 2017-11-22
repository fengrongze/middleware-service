package com.softisland.middleware.service.faq.client.business.service;

import com.softisland.middleware.domain.bean.db.SysUser;
import com.softisland.middleware.domain.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fengrongze on 2017/10/24.
 */
@Service
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> queryListSysUser() {
        return sysUserMapper.selectAll();
    }
}
