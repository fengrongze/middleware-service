package com.softisland.middleware.domain.mapper;

import com.softisland.middleware.domain.bean.db.FaqAnswer;

import com.softisland.middleware.domain.bean.query.AnswerQueryItem;
import com.softisland.middleware.domain.bean.view.AnswerView;
import com.softisland.middleware.domain.util.MyMapper;

import java.util.List;
import java.util.Map;


public interface FaqAnswerMapper extends MyMapper<FaqAnswer> {

    List<AnswerView> queryAnswerList(Map<String, Object> para);

    AnswerView queryAnswerView(Map<String, Object> para);

    List<AnswerQueryItem> queryBackgroundAnswerList(Map<String, Object> param);
}