package com.softisland.middleware.service.background.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softisland.middleware.domain.bean.db.FaqCatalog;
import com.softisland.middleware.domain.bean.db.SysDictionary;
import com.softisland.middleware.domain.bean.db.SysSite;
import com.softisland.middleware.domain.bean.db.SysSiteExample;
import com.softisland.middleware.domain.bean.query.CatalogQuery;
import com.softisland.middleware.domain.bean.query.CatalogQueryItem;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.mapper.FaqCatalogMapper;
import com.softisland.middleware.domain.mapper.SysDictionaryMapper;
import com.softisland.middleware.domain.mapper.SysSiteMapper;
import com.softisland.middleware.domain.util.ProjectConstant;
import com.softisland.middleware.domain.util.ServiceType;
import com.softisland.middleware.domain.util.exception.BusinessException;
import com.softisland.middleware.domain.util.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fengrongze on 2017/11/6.
 */
@Service
public class CatalogService {

    @Autowired
    private FaqCatalogMapper faqCatalogMapper;

    @Autowired
    private SysSiteMapper sysSiteMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;


    public PageResult<CatalogQueryItem> queryCatalogList(CatalogQuery query) {
        int pageIndex = query.getPageIndex();
        int pageSize = query.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> param = new HashMap<>();
        param.put("siteId",1);
        List<CatalogQueryItem> answereList=faqCatalogMapper.queryBackgroundCatalogList(param);
        PageInfo<CatalogQueryItem> pageInfo = new PageInfo<>(answereList);
        return new PageResult<>(pageInfo);
    }

    public List<CatalogQueryItem> queryCatalogList(HashMap<String, Object> map) {
        return faqCatalogMapper.queryBackgroundCatalogList(map);
    }


    @Transactional
    public void addCatalog(Integer siteId, Map<String, String[]> param, String defualt_code, List<LanguageView> languageViews) {

        //查询site相关信息
        SysSiteExample example = new SysSiteExample();
        SysSite sysSite = new SysSite();
        sysSite.setId(siteId);
        sysSite= sysSiteMapper.selectOne(sysSite);

        //1.生成word_key
        String word_key= sysSite.getSiteCode().concat("_catalog_").concat(param.get(defualt_code.concat("_name"))[0]);

        int super_id=Integer.parseInt(param.get("superId")[0]);

        String description=param.get(defualt_code.concat("_name"))[0];

        FaqCatalog faqCatalog = new FaqCatalog();

        //生成目录表
        faqCatalog.setDescription(description);
        faqCatalog.setSuperId(super_id);
        faqCatalog.setWordKey(word_key);
        faqCatalog.setSiteId(siteId);
        faqCatalog.setCreated(new Date());
        faqCatalog.setUpdated(new Date());
        faqCatalog.setSortWeight(1);
        faqCatalog.setType(Short.parseShort(param.get("catalogType")[0]));
        faqCatalogMapper.insert(faqCatalog);

        //将名词存入词典中
        languageViews.forEach(v->{
            SysDictionary sysDictionary=new SysDictionary();
            sysDictionary.setSiteId(siteId);
            sysDictionary.setWordKey(word_key);
            sysDictionary.setLanguageCode(v.getCode());
            sysDictionary.setServiceId(ServiceType.SERVICE_TYPE_FAQ.getVal());
            sysDictionary.setWordContent( param.get(v.getCode().concat("_name"))[0]);
            sysDictionaryMapper.insert(sysDictionary);
        });

    }

    public List<CatalogQueryItem> queryCatalogItemsList(HashMap<String, Object> map) {
        return faqCatalogMapper.queryCatalogItemsList(map);
    }


}
