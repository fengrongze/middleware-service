package com.softisland.middleware.service.faq.client.business.controller;

import com.softisland.middleware.domain.bean.db.*;
import com.softisland.middleware.domain.bean.view.AnswerView;
import com.softisland.middleware.domain.bean.view.CatalogItemView;
import com.softisland.middleware.domain.bean.view.CatalogView;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.util.ProjectConstant;
import com.softisland.middleware.service.faq.client.business.service.FaqClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bouncycastle.asn1.iana.IANAObjectIdentifiers.directory;

/**
 * Created by fengrongze on 2017/10/30.
 */
@Controller
public class FaqClientController {


    @Autowired
    private FaqClientService faqClientService;



    @RequestMapping("/deft")
    public String gphello(Map<String,Object> map, HttpServletRequest request){


        String directory=this.dealCommon(map,request);

        return directory+"/index";

    }

    private String dealCommon(Map<String, Object> map, HttpServletRequest request) {
        //获取是从那个域名访问进来的，以便定位到是哪个站点
        int endIndex = request.getRequestURL().length() - (request.getPathInfo()==null?0:request.getPathInfo().length()+1);
        String domain = request.getRequestURL().substring(0, endIndex);
        //获取"//"的位置
        int index=domain.indexOf("//");
        if(index>0){
            domain=domain.substring(index+2);
        }
        index=domain.indexOf("/");

        domain=domain.substring(0,index);

        //查询站点服务相关信息

        SysSiteService sysSiteService= faqClientService.querySiteServiceByServiceDomain(domain,ProjectConstant.SERVICE_NAME_FAQ);

        SysSite sysSite =faqClientService.querySiteById(sysSiteService.getSiteId());

        if(sysSiteService==null){
            //需要抛出异常
        }

        SysSiteSkin  sysSiteSkin = faqClientService.querySiteSkinBySiteId(sysSiteService.getSiteId());

        if(sysSiteSkin==null){
            sysSiteSkin=faqClientService.queryDefualtSiteSkin();
        }

        map.put("resourceUrl",sysSiteSkin.getResourceUrl());

        //查询默认语言
        SysSiteLanguage sysSiteLanguage=faqClientService.querySysSiteLanguage(sysSiteService.getSiteId());

        String siteLanguage=null;

        if(sysSiteLanguage==null){
            siteLanguage= ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE;
        }else{
            siteLanguage=sysSiteLanguage.getLanguageCode();
        }

        //查询站点语言列表
        List<LanguageView> languageViewList=faqClientService.querySiteLanguageList(sysSiteService.getSiteId());



        List<CatalogView> catalogViewList=new ArrayList<>();

        //查询1-2级目录
        List<CatalogItemView> faqTopCatalogList= faqClientService.queryTopSiteCatologListBySite(sysSiteService.getSiteId(),0,siteLanguage);

        if(!CollectionUtils.isEmpty(faqTopCatalogList)){
            for (int i = 0; i <faqTopCatalogList.size() ; i++) {
                //查询该目录的下级目录
                List<CatalogItemView> faqCatalogList= faqClientService.queryTopSiteCatologListBySite(sysSiteService.getSiteId(),(Integer) faqTopCatalogList.get(i).getId(),siteLanguage);
                CatalogView catalog = new CatalogView();
                catalog.setTopCatalog(faqTopCatalogList.get(i));
                catalog.setSubCatalogs(faqCatalogList);
                catalogViewList.add(catalog);
            }
        }


        map.put("catalogs", catalogViewList);
        map.put("languages",languageViewList);
        map.put("siteId",sysSiteService.getSiteId());

        String directory=siteLanguage.replaceAll("-","");

        return sysSite.getSiteCode()+"/"+directory;
    }


    @RequestMapping("/search")
    public String search(Map<String,Object> map, HttpServletRequest request){

        String directory=this.dealCommon(map,request);

        String key = request.getParameter("key");

        String language=this.getLanguageFromRequest(request);



        List<AnswerView> searchResult= faqClientService.searchAnswerByTitleKeyList(key,ProjectConstant.SITE_ANSWER_STATUS_ACTIVE,language,(Integer)map.get("siteId"));

        map.put("searchResult",searchResult);
        map.put("searchKey",StringUtils.isEmpty(key)?"":key);
        return directory+"/search-results";
    }


    @RequestMapping("/detail")
    public String detail(Map<String,Object> map, HttpServletRequest request){

        String directory=this.dealCommon(map,request);

        //查询该问题的解答
        String sid=request.getParameter("id");

        if(StringUtils.isEmpty(sid)){
            return directory+"/index";
        }

        Integer answerId=Integer.parseInt(sid);

        String language=this.getLanguageFromRequest(request);

        //查询问题的内容
        AnswerView answer= faqClientService.queryAnswer(answerId,language,(Integer)map.get("siteId"));
        map.put("answer",answer);

        //查询目录顺序
        this.addSuperCatalogsToView(map,answer.getCid(),language,(Integer)map.get("siteId"));

        return directory+"/faq-detail";

    }



    @RequestMapping("/catalogue")
    public String catalogue(Map<String,Object> map, HttpServletRequest request){

        String directory=this.dealCommon(map,request);

        String scid=request.getParameter("cid");
        int cid=Integer.parseInt(scid);

        String language=this.getLanguageFromRequest(request);

        if(StringUtils.isEmpty(scid)){
            return directory+"/index";
        }

        //查询问题列表
        List<AnswerView> answerViews=faqClientService.queryAnswerList(cid,ProjectConstant.SITE_ANSWER_STATUS_ACTIVE,language,(Integer)map.get("siteId"));

        //查询目录顺序
        this.addSuperCatalogsToView(map,cid,language,(Integer)map.get("siteId"));


          map.put("answers",answerViews);

        return directory+"/catalogue";

    }

    private void addSuperCatalogsToView(Map<String, Object> map, int cid, String language, Integer siteId) {
        List<CatalogItemView> superCatalogList=faqClientService.querySuperCatalogList(cid,language,siteId);
        map.put("superCatalogs",superCatalogList);
    }

    private String getLanguageFromRequest(HttpServletRequest request) {
        String language=  request.getParameter("language");

        if(StringUtils.isEmpty(language)){
            language=ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE;
        }
        return language;
    }


}
