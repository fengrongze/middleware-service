package com.softisland.middleware.domain.util;

/**
 * Created by softisland on 2016/8/30.
 */
public enum ServiceType {
    SERVICE_TYPE_FAQ(1,"SERVICE-FAQ");

    private final String desc;
    private final int val;
    ServiceType(int val, String desc)
    {
        this.val = val;
        this.desc = desc;
    }


    public String getDesc()
    {
        return desc;
    }

    public int getVal()
    {
        return val;
    }

}

