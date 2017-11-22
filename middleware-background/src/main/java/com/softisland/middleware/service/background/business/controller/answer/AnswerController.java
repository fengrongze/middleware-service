package com.softisland.middleware.service.background.business.controller.answer;

import com.softisland.middleware.domain.bean.query.AnswerQuery;
import com.softisland.middleware.domain.bean.query.AnswerQueryItem;
import com.softisland.middleware.domain.bean.query.CatalogQueryItem;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.util.ProjectConstant;
import com.softisland.middleware.domain.util.ServiceType;
import com.softisland.middleware.domain.util.exception.BusinessException;
import com.softisland.middleware.domain.util.page.PageResult;
import com.softisland.middleware.service.background.business.service.AnswerService;
import com.softisland.middleware.service.background.business.service.CatalogService;
import com.softisland.middleware.service.background.business.service.SiteLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengrongze on 2017/11/3.
 */
@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private SiteLanguageService siteLanguageService;

    @RequestMapping(value = "/answer",method = RequestMethod.GET)
    public String loginGet(HttpServletRequest request, HttpServletResponse response) {
        //查询该用户的站点问题
        return "/answer/answerlist";
    }

    @RequestMapping(value = "/answer/query")
    @ResponseBody
    public Object queryList( AnswerQuery answerQuery, HttpServletRequest request) {

        //查询站点相关的语言
        Integer siteId = (Integer) request.getSession().getAttribute("mySiteId");
        answerQuery.setSiteId(siteId);
        PageResult<AnswerQueryItem> pageResult = new PageResult<>();
        //查询该用户的站点问题
        pageResult=answerService.queryAnswerList(answerQuery);

        return pageResult;

    }


    @RequestMapping(value = "/answer/add",method = RequestMethod.GET)
    public Object add(HttpServletRequest request, HttpServletResponse response) {

        //查询站点相关的语言
        Integer siteId = (Integer) request.getSession().getAttribute("mySiteId");

        //查询站点语言
        List<LanguageView> languageViews = siteLanguageService.querySiteLanguageList(siteId);

        //检测站点语言的语言,是否具有中文语言
        String languageCode = null;
        String defualtCode = null;
        for (int i = 0; i < languageViews.size(); i++) {
            if (ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE.equals(languageViews.get(i).getCode())) {
                languageCode = ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE;
            }

            if (languageViews.get(i).getDefaultFlag() == ProjectConstant.DEFUALT_SITE_LANGUAGE) {
                defualtCode = languageViews.get(i).getCode();
            }
        }

        if (languageCode == null) {

            languageCode = StringUtils.isEmpty(defualtCode) ? "" : defualtCode;
        }

        HashMap<String, Object> map = new HashMap();
        map.put("siteId", siteId);
        map.put("type", ProjectConstant.FAQ_CATALOG_TYPE_PAGE);
        map.put("languageCode", languageCode);
        map.put("serviceId", ServiceType.SERVICE_TYPE_FAQ.getVal());

        //查询站点所有页面
        List<CatalogQueryItem> catalogQueryItems = catalogService.queryCatalogItemsList(map);

        for (int i = 0; i < catalogQueryItems.size(); i++) {
            boolean check = true;
            int superId = catalogQueryItems.get(i).getSuperId();
            String catalogName = catalogQueryItems.get(i).getContent();
            while (check) {
                if (superId == 0) {
                    break;
                } else {
                    map.clear();
                    map.put("id", superId);
                    List<CatalogQueryItem> items = catalogService.queryCatalogItemsList(map);
                    superId = items.get(0).getSuperId();
                    catalogName = items.get(0).getContent().concat("->").concat(catalogName);
                    if (superId == 0) {
                        check = false;
                    }
                }
            }
            catalogQueryItems.get(i).setDescription(catalogQueryItems.get(i).getContent().concat("(").concat(catalogName).concat(")"));
        }

        Map<String,Object> data = new HashMap<String,Object>();
        data.put("languages",languageViews);
        data.put("catalogQueryItems",catalogQueryItems);

        return new ModelAndView( "/answer/answeradd",data);
    }


    @RequestMapping(value = "/answer/add",method = RequestMethod.POST)
    public void addPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String[]> param=request.getParameterMap();

        if(!param.containsKey("catalogPageId")){
            throw new BusinessException("问题类型不能为空");
        }


        //查询站点相关的语言
        Integer siteId=(Integer) request.getSession().getAttribute("mySiteId");

        if(siteId==null){
            response.sendRedirect("/login");
        }

        List<LanguageView>  languageViews = siteLanguageService.querySiteLanguageList(siteId);

        String defualt_code=null;

        Short subType=Short.parseShort(param.get("subType")[0]);

        for (int i = 0; i <languageViews.size() ; i++) {
            if(!param.containsKey(languageViews.get(i).getCode().concat("_name"))){
                throw new BusinessException(languageViews.get(i).getName()+"标题不能为空");
            }
            if(languageViews.get(i).getDefaultFlag()==1){
                defualt_code= languageViews.get(i).getCode();
            }
        }

        for (int i = 0; i <languageViews.size() ; i++) {
            if(!param.containsKey("content_editor_".concat(languageViews.get(i).getCode()))){
                throw new BusinessException(languageViews.get(i).getName()+"内容不能为空");
            }
            if(languageViews.get(i).getDefaultFlag()==1){
                defualt_code= languageViews.get(i).getCode();
            }
        }

        //进行目录的保存
        answerService.addAnswer(siteId,param,defualt_code,languageViews);

       response.sendRedirect("/answer");
    }
}
