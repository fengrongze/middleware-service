package com.softisland.middleware.domain.bean.db;

import java.util.Date;

public class SysService {
    private Integer id;

    private String serviceName;

    private String serviceDescprtion;

    private Short status;

    private Date created;

    private Date update;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getServiceDescprtion() {
        return serviceDescprtion;
    }

    public void setServiceDescprtion(String serviceDescprtion) {
        this.serviceDescprtion = serviceDescprtion == null ? null : serviceDescprtion.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
}