package com.softisland.middleware.service.faq.client.business.service;

import com.softisland.middleware.domain.bean.db.*;
import com.softisland.middleware.domain.bean.view.AnswerView;
import com.softisland.middleware.domain.bean.view.CatalogItemView;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.mapper.*;
import com.softisland.middleware.domain.util.ProjectConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by fengrongze on 2017/10/30.
 */
@Service
public class FaqClientService {

    @Autowired
    private SysSiteSkinMapper sysSiteSkinMapper;

    @Autowired
    private SysSiteMapper sysSiteMapper;

    @Autowired
    private SysSiteLanguageMapper sysSiteLanguageMapper;

    @Autowired
    private SysSiteServiceMapper sysSiteServiceMapper;

    @Autowired
    private FaqCatalogMapper faqCatalogMapper;

    @Autowired
    private FaqAnswerMapper faqAnswerMapper;



    public SysSiteSkin querySitSkinBySiteDomain(String domain) {

        SysSiteSkin sysSiteSkin=null;

        SysSiteExample example=new SysSiteExample();
        example.createCriteria().andSiteDomainEqualTo(domain);
        List<SysSite> siteList= sysSiteMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(siteList)){
            SysSiteSkinExample exampleSkin=new SysSiteSkinExample();
            exampleSkin.createCriteria().andSiteIdEqualTo(siteList.get(0).getId()).andStatusEqualTo(ProjectConstant.SITE_SKIN_STATUS_ACTIVE);
                   List<SysSiteSkin>  siteSkinList= sysSiteSkinMapper.selectByExample(exampleSkin);

            if(!CollectionUtils.isEmpty(siteSkinList)){
                sysSiteSkin= siteSkinList.get(0);
            }

        }


        if(sysSiteSkin==null){
            SysSiteSkinExample exampleSkin=new SysSiteSkinExample();
            exampleSkin.createCriteria().andSiteIdEqualTo(ProjectConstant.DEFUALT_SITE_ID);
            List<SysSiteSkin>  siteSkinList= sysSiteSkinMapper.selectByExample(exampleSkin);
            sysSiteSkin= siteSkinList.get(0);
        }


        return sysSiteSkin;

    }

    public SysSite querySiteByDomian(String domain) {
        SysSiteExample example=new SysSiteExample();
        example.createCriteria().andSiteDomainEqualTo(domain);
        List<SysSite> siteList= sysSiteMapper.selectByExample(example);
        if(!CollectionUtils.isEmpty(siteList)){
            return siteList.get(0);
        }
        return null;
    }

    public SysSiteSkin querySiteSkinBySiteId(Integer siteId) {
        SysSiteSkinExample exampleSkin=new SysSiteSkinExample();
        exampleSkin.createCriteria().andSiteIdEqualTo(siteId).andStatusEqualTo(ProjectConstant.SITE_SKIN_STATUS_ACTIVE);
        List<SysSiteSkin>  siteSkinList= sysSiteSkinMapper.selectByExample(exampleSkin);

        if(!CollectionUtils.isEmpty(siteSkinList)){
            return siteSkinList.get(0);
        }

        return null;
    }

    public SysSiteSkin queryDefualtSiteSkin() {
        SysSiteSkinExample exampleSkin=new SysSiteSkinExample();
        exampleSkin.createCriteria().andSiteIdEqualTo(ProjectConstant.DEFUALT_SITE_ID).andStatusEqualTo(ProjectConstant.SITE_SKIN_STATUS_ACTIVE);
        List<SysSiteSkin>  siteSkinList= sysSiteSkinMapper.selectByExample(exampleSkin);

        if(!CollectionUtils.isEmpty(siteSkinList)){
            return siteSkinList.get(0);
        }

        return null;
    }

    public SysSiteLanguage querySysSiteLanguage(Integer siteId) {
        SysSiteLanguageExample example=new SysSiteLanguageExample();
        example.createCriteria().andSiteIdEqualTo(siteId).andDefaultFlagEqualTo(ProjectConstant.DEFUALT_SITE_LANGUAGE);
        List<SysSiteLanguage>  siteLanguageList= sysSiteLanguageMapper.selectByExample(example);

        if(!CollectionUtils.isEmpty(siteLanguageList)){
            return siteLanguageList.get(0);
        }

        return null;
    }

    public SysSiteService querySiteServiceByServiceDomain(String domain, String serviceName) {
        Map<String,String > para=new HashMap<>();
        para.put("domain",domain);
        para.put("serviceName",serviceName);
        return sysSiteServiceMapper.querySiteServiceByServiceDomain(para);
    }

    public SysSite querySiteById(Integer siteId) {

        SysSite example=new SysSite();
        example.setId(siteId);

        return sysSiteMapper.selectOne(example);
    }

    public List<CatalogItemView> queryTopSiteCatologListBySite(Integer siteId, Integer superId, String language) {
        Map<String,Object> para=new HashMap<>();
        para.put("siteId",siteId);
        para.put("superId",superId);
        para.put("language",language);
        return faqCatalogMapper.queryTopSiteCatalogListBySite(para);
    }

    public List<LanguageView> querySiteLanguageList(Integer siteId) {
       return sysSiteLanguageMapper.querySiteLanguageList(siteId);
    }


    public List<AnswerView> queryAnswerList(Integer cid, short status, String language, Integer siteId) {
        Map<String,Object> para=new HashMap<>();
        para.put("cid",cid);
        para.put("status",status);
        para.put("language",language);
        para.put("siteId",siteId);

        return faqAnswerMapper.queryAnswerList(para);
    }


    public List<AnswerView> searchAnswerByTitleKeyList(String titleKey, Short status, String language, Integer siteId) {
        Map<String,Object> para=new HashMap<>();
        para.put("titleKey",titleKey);
        para.put("status",status);
        para.put("language",language);
        para.put("siteId",siteId);

        return faqAnswerMapper.queryAnswerList(para);
    }

    public List<CatalogItemView> querySuperCatalogList(int cid, String language, Integer siteId) {
        List<CatalogItemView> superCatalogs=new ArrayList<>();
        Map<String,Object> para=new HashMap<>();
        para.put("id",cid);
        para.put("language",language);
        para.put("siteId",siteId);

        boolean check=true;

        do{
            CatalogItemView catalog=faqCatalogMapper.queryCatalog(para);
            superCatalogs.add(catalog);
            if(catalog==null){
                check=false;
            }else if(catalog.getSuperId()==null || catalog.getSuperId()==0){
                check=false;
            }else{
                para.put("id",catalog.getSuperId());
            }


        }while(check);

        return superCatalogs;
    }




    public AnswerView queryAnswer(Integer answerId, String language, Integer siteId) {
        Map<String,Object> para=new HashMap<>();
        para.put("id",answerId);
        para.put("language",language);
        para.put("siteId",siteId);

        return faqAnswerMapper.queryAnswerView(para);
    }


}
