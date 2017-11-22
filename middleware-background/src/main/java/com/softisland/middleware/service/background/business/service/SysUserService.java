package com.softisland.middleware.service.background.business.service;

import com.softisland.middleware.domain.bean.db.SysUser;
import com.softisland.middleware.domain.bean.db.SysUserExample;
import com.softisland.middleware.domain.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by fengrongze on 2017/10/24.
 */
@Service
public class SysUserService {

    private static Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    public List<SysUser> queryListSysUser() {
        return sysUserMapper.selectAll();
    }

    public boolean selectUserByPwdAndId(int userId,String pwd)
    {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(pwd);

        SysUser user = sysUserMapper.selectOne(sysUser);

        if(null != user)
        {
            return true;
        }
        return false;
    }

    public int updateUserPwd(int userId,String newPwd)
    {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(userId);

        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setPassword(newPwd);

        return sysUserMapper.updateByExampleSelective(sysUser,example);
    }
}
