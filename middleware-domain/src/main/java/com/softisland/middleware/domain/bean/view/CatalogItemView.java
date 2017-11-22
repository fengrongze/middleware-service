package com.softisland.middleware.domain.bean.view;

import java.util.List;

/**
 * Created by fengrongze on 2017/10/31.
 */
public class CatalogItemView {
    private Integer id;

    private String name;

    private Integer superId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSuperId() {
        return superId;
    }

    public void setSuperId(Integer superId) {
        this.superId = superId;
    }
}
