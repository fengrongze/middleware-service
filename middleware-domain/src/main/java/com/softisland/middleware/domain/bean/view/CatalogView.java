package com.softisland.middleware.domain.bean.view;

import java.util.List;

/**
 * Created by fengrongze on 2017/10/31.
 */
public class CatalogView {
    private CatalogItemView topCatalog;

    private List<CatalogItemView> subCatalogs;


    public CatalogItemView getTopCatalog() {
        return topCatalog;
    }

    public void setTopCatalog(CatalogItemView topCatalog) {
        this.topCatalog = topCatalog;
    }

    public List<CatalogItemView> getSubCatalogs() {
        return subCatalogs;
    }

    public void setSubCatalogs(List<CatalogItemView> subCatalogs) {
        this.subCatalogs = subCatalogs;
    }
}
