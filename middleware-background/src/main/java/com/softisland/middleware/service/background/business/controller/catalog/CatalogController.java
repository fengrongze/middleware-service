package com.softisland.middleware.service.background.business.controller.catalog;
import com.softisland.middleware.domain.bean.query.CatalogQuery;
import com.softisland.middleware.domain.bean.query.CatalogQueryItem;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.util.ProjectConstant;
import com.softisland.middleware.domain.util.ServiceType;
import com.softisland.middleware.domain.util.exception.BusinessException;
import com.softisland.middleware.domain.util.page.PageResult;
import com.softisland.middleware.service.background.business.service.CatalogService;
import com.softisland.middleware.service.background.business.service.SiteLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafx.scene.input.KeyCode.O;

/**
 * Created by fengrongze on 2017/11/6.
 */
@Controller
public class CatalogController {

    @Autowired
    private CatalogService catalogService;



    @Autowired
    private SiteLanguageService siteLanguageService;

    @RequestMapping(value = "/catalog",method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, HttpServletResponse response) {

        //查询该用户的站点问题


        return "/catalog/cataloglist";
    }

    @RequestMapping(value = "/catalog/query")
    @ResponseBody
    public Object queryList(CatalogQuery catalogQuery) {

        PageResult<CatalogQueryItem> pageResult = new PageResult<>();
        //查询该用户的站点问题
        pageResult=catalogService.queryCatalogList(catalogQuery);

        return pageResult;

    }


    @RequestMapping(value = "/catalog/add" ,method =  RequestMethod.GET)
    public ModelAndView addGet(HttpServletRequest request) {

        //查询站点相关的语言
        Integer siteId=(Integer) request.getSession().getAttribute("mySiteId");

        //查询站点语言
        List<LanguageView>  languageViews = siteLanguageService.querySiteLanguageList(siteId);

        //检测站点语言的语言
        //是否具有中文语言
        String languageCode=null;
        String defualtCode=null;
        for (int i = 0; i <languageViews.size(); i++) {
            if(ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE.equals(languageViews.get(i).getCode())){
                languageCode=ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE;
            }

            if(languageViews.get(i).getDefaultFlag()==ProjectConstant.DEFUALT_SITE_LANGUAGE){
                defualtCode= languageViews.get(i).getCode();
            }
        }

        if(languageCode==null){

            languageCode= StringUtils.isEmpty(defualtCode)?"":defualtCode;
        }

        HashMap<String,Object> map = new HashMap();
        map.put("siteId",siteId);
        map.put("type",ProjectConstant.FAQ_CATALOG_TYPE_CATALOG);
        map.put("languageCode",languageCode);
        map.put("serviceId", ServiceType.SERVICE_TYPE_FAQ.getVal());

        //查询出所有的目录
        List<CatalogQueryItem> catalogQueryItems= catalogService.queryCatalogItemsList(map);

        for (int i = 0; i <catalogQueryItems.size() ; i++) {
            boolean check=true;
            int superId=catalogQueryItems.get(i).getSuperId();
            String catalogName=catalogQueryItems.get(i).getContent();
            while (check){
                if(superId == 0){
                    break;
                }else{
                    map .put("id",superId);
                    List<CatalogQueryItem> items= catalogService.queryCatalogItemsList(map);
                    superId=items.get(0).getSuperId();
                    catalogName=items.get(0).getContent().concat("->").concat(catalogName);
                    if(superId == 0){
                        check=false;
                    }
                }
            }

            catalogQueryItems.get(i).setDescription(catalogName);

        }


        Map<String,Object> data = new HashMap<String,Object>();
        data.put("languages",languageViews);
        data.put("catalogQueryItems",catalogQueryItems);
        data.put("function","add");
        return  new ModelAndView("/catalog/catalogadd",data);
    }



    @RequestMapping(value = "/catalog/add" ,method =  RequestMethod.POST)
    public void addPost(HttpServletRequest request,HttpServletResponse response) throws IOException {

        Map<String, String[]> param=request.getParameterMap();

        if(!param.containsKey("superId")){
            throw new BusinessException("上级目录不能为空");
        }

        if(!param.containsKey("catalogType")){
            throw new BusinessException("目录类型不能为空");
        }


        //查询站点相关的语言
        Integer siteId=(Integer) request.getSession().getAttribute("mySiteId");

        if(siteId==null){
            response.sendRedirect("/login");
        }

        List<LanguageView>  languageViews = siteLanguageService.querySiteLanguageList(siteId);

        String defual_code=null;

        for (int i = 0; i <languageViews.size() ; i++) {
            if(!param.containsKey(languageViews.get(i).getCode().concat("_name"))){
                throw new BusinessException(languageViews.get(i).getName()+"不能为空");
            }
            if(languageViews.get(i).getDefaultFlag()==1){
                defual_code= languageViews.get(i).getCode();
            }
        }

        //进行目录的保存
        catalogService.addCatalog(siteId,param,defual_code,languageViews);
    }


    @RequestMapping(value = "/catalog/edit" ,method =  RequestMethod.GET)
    public Object edit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //查询站点相关的语言
        Integer siteId=(Integer) request.getSession().getAttribute("mySiteId");

        //查询站点语言
        List<LanguageView>  languageViews = siteLanguageService.querySiteLanguageList(siteId);

        Integer id=Integer.parseInt(request.getParameter("id"));

        //检测站点语言的语言
        //是否具有中文语言
        String languageCode=null;
        String defualtCode=null;
        for (int i = 0; i <languageViews.size(); i++) {
            if(ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE.equals(languageViews.get(i).getCode())){
                languageCode=ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE;
            }

            if(languageViews.get(i).getDefaultFlag()==ProjectConstant.DEFUALT_SITE_LANGUAGE){
                defualtCode= languageViews.get(i).getCode();
            }
        }

        if(languageCode==null){

            languageCode= StringUtils.isEmpty(defualtCode)?"":defualtCode;
        }

        HashMap<String,Object> map = new HashMap();
        map.put("siteId",siteId);
        map.put("type",ProjectConstant.FAQ_CATALOG_TYPE_CATALOG);
        map.put("languageCode",languageCode);
        map.put("serviceId", ServiceType.SERVICE_TYPE_FAQ.getVal());

        //查询出所有的目录
        List<CatalogQueryItem> catalogQueryItems= catalogService.queryCatalogItemsList(map);

        for (int i = 0; i <catalogQueryItems.size() ; i++) {
            boolean check=true;
            int superId=catalogQueryItems.get(i).getSuperId();
            String catalogName=catalogQueryItems.get(i).getContent();
            while (check){
                if(superId == 0){
                    break;
                }else{
                    map .put("id",superId);
                    List<CatalogQueryItem> items= catalogService.queryCatalogItemsList(map);
                    superId=items.get(0).getSuperId();
                    catalogName=items.get(0).getContent().concat("->").concat(catalogName);
                    if(superId == 0){
                        check=false;
                    }
                }
            }

            catalogQueryItems.get(i).setDescription(catalogName);

        }


        //查询内容
        map.clear();
        map.put("siteId",siteId);
        map.put("serviceId", ServiceType.SERVICE_TYPE_FAQ.getVal());
        map.put("siteId",siteId);
        map.put("id",id);
        //查询出所有的目录
        List<CatalogQueryItem> catalogLanguageItems= catalogService.queryCatalogItemsList(map);

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("languages",languageViews);
        data.put("catalogQueryItems",catalogQueryItems);
        data.put("catalogLanguageItems",catalogLanguageItems);
        data.put("function","edit");
        return  new ModelAndView("/catalog/catalogadd",data);

    }
}
