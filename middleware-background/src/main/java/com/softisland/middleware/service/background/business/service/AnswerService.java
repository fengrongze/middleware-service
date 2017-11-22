package com.softisland.middleware.service.background.business.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softisland.middleware.domain.bean.db.*;
import com.softisland.middleware.domain.bean.query.AnswerQuery;
import com.softisland.middleware.domain.bean.query.AnswerQueryItem;
import com.softisland.middleware.domain.bean.view.LanguageView;
import com.softisland.middleware.domain.mapper.FaqAnswerMapper;
import com.softisland.middleware.domain.mapper.SysDictionaryMapper;
import com.softisland.middleware.domain.mapper.SysSiteMapper;
import com.softisland.middleware.domain.util.ProjectConstant;
import com.softisland.middleware.domain.util.ServiceType;
import com.softisland.middleware.domain.util.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.tools.ant.taskdefs.email.EmailTask.UU;

/**
 * Created by fengrongze on 2017/11/3.
 */
@Service
public class AnswerService {

    @Autowired
    private FaqAnswerMapper faqAnswerMapper;

    @Autowired
    private SysSiteMapper sysSiteMapper;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    public PageResult<AnswerQueryItem> queryAnswerList(AnswerQuery query) {
        int pageIndex = query.getPageIndex();
        int pageSize = query.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        Map<String,Object> param = new HashMap<>();
        param.put("languageCode", ProjectConstant.DEFUALT_SITE_LANGUAGE_SIMPLE_CODE);
        param.put("siteId", query.getSiteId());
        List<AnswerQueryItem> answerList=faqAnswerMapper.queryBackgroundAnswerList(param);
        PageInfo<AnswerQueryItem> pageInfo = new PageInfo<>(answerList);
        return new PageResult<AnswerQueryItem>(pageInfo);
    }

    public void addAnswer(Integer siteId, Map<String, String[]> param, String defualt_code, List<LanguageView> languageViews) {
        //查询site相关信息
        SysSiteExample example = new SysSiteExample();
        SysSite sysSite = new SysSite();
        sysSite.setId(siteId);
        sysSite= sysSiteMapper.selectOne(sysSite);

        //1.生成word_key
        String title_word_key= sysSite.getSiteCode().concat("_answer_title_").concat(UUID.randomUUID().toString().replaceAll("-",""));

        String content_word_key=sysSite.getSiteCode().concat("_answer_content_").concat(UUID.randomUUID().toString().replaceAll("-",""));

        int catalogPageId=Integer.parseInt(param.get("catalogPageId")[0]);

        String description=param.get(defualt_code.concat("_name"))[0];


        Short subType=Short.parseShort(param.get("subType")[0]);


        FaqAnswer faqAnswer = new FaqAnswer();
        faqAnswer.setCatalogId(catalogPageId);
        faqAnswer.setContentKey(content_word_key);
        faqAnswer.setTitleKey(title_word_key);
        faqAnswer.setDescription(description);
        faqAnswer.setStatus(subType);
        faqAnswer.setCreated(new Date());
        faqAnswer.setUpdated(new Date());

        faqAnswerMapper.insert(faqAnswer);


        //将title名词存入词典中
        languageViews.forEach(v->{
            SysDictionary sysDictionary=new SysDictionary();
            sysDictionary.setSiteId(siteId);
            sysDictionary.setWordKey(title_word_key);
            sysDictionary.setLanguageCode(v.getCode());
            sysDictionary.setServiceId(ServiceType.SERVICE_TYPE_FAQ.getVal());
            sysDictionary.setWordContent( param.get(v.getCode().concat("_name"))[0]);
            sysDictionaryMapper.insert(sysDictionary);
        });


        //将名词存入词典中
        languageViews.forEach(v->{
            SysDictionary sysDictionary=new SysDictionary();
            sysDictionary.setSiteId(siteId);
            sysDictionary.setWordKey(content_word_key);
            sysDictionary.setLanguageCode(v.getCode());
            sysDictionary.setServiceId(ServiceType.SERVICE_TYPE_FAQ.getVal());
            sysDictionary.setWordContent( param.get("content_editor_".concat(v.getCode()))[0]);
            sysDictionaryMapper.insert(sysDictionary);
        });
    }
}
