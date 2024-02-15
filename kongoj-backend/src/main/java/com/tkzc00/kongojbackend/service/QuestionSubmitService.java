package com.tkzc00.kongojbackend.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tkzc00.kongojbackend.model.dto.question.QuestionQueryRequest;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.QuestionSubmitQueryRequest;
import com.tkzc00.kongojbackend.model.entity.Question;
import com.tkzc00.kongojbackend.model.entity.QuestionSubmit;
import com.tkzc00.kongojbackend.model.entity.User;
import com.tkzc00.kongojbackend.model.vo.QuestionSubmitVO;
import com.tkzc00.kongojbackend.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author tkzc00
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-02-15 12:27:47
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);

    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
