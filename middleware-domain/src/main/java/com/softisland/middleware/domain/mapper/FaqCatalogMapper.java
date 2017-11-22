package com.softisland.middleware.domain.mapper;

import com.softisland.middleware.domain.bean.db.FaqCatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softisland.middleware.domain.bean.query.CatalogQueryItem;
import com.softisland.middleware.domain.bean.view.CatalogItemView;
import com.softisland.middleware.domain.bean.view.CatalogView;
import com.softisland.middleware.domain.util.MyMapper;

public interface FaqCatalogMapper extends MyMapper<FaqCatalog> {

    List<CatalogItemView>  queryTopSiteCatalogListBySite(Map<String, Object> para);

    CatalogItemView queryCatalog(Map<String, Object> para);

    List<CatalogQueryItem> queryBackgroundCatalogList(Map<String, Object> param);

    List<CatalogQueryItem> queryCatalogItemsList(HashMap<String, Object> map);
}