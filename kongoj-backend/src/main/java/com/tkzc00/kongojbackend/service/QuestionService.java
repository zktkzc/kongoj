package com.tkzc00.kongojbackend.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tkzc00.kongojbackend.model.dto.question.QuestionQueryRequest;
import com.tkzc00.kongojbackend.model.entity.Question;
import com.tkzc00.kongojbackend.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author tkzc00
* @description 针对表【question(题目表)】的数据库操作Service
* @createDate 2024-02-15 12:27:47
*/
public interface QuestionService extends IService<Question> {

    void validQuestion(Question question, boolean b);

    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    Wrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);
}
