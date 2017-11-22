package com.softisland.middleware.domain.bean.query;

import com.softisland.middleware.domain.util.page.Query;

/**
 * Created by fengrongze on 2017/11/6.
 */
public class CatalogQuery extends Query {
    private Integer siteId;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}
