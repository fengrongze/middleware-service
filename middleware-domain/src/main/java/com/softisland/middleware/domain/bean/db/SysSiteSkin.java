package com.softisland.middleware.domain.bean.db;

import java.util.Date;

public class SysSiteSkin {
    private Integer id;

    private String skinName;

    private Short status;

    private Integer siteId;

    private Date created;

    private Date updated;

    private String resourceUrl;

    private String skinContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkinName() {
        return skinName;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName == null ? null : skinName.trim();
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getSkinContent() {
        return skinContent;
    }

    public void setSkinContent(String skinContent) {
        this.skinContent = skinContent == null ? null : skinContent.trim();
    }
}