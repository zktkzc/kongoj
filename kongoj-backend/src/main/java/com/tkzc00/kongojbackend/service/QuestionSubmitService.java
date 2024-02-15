package com.tkzc00.kongojbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.QuestionSubmitAddRequest;
import com.tkzc00.kongojbackend.model.entity.QuestionSubmit;
import com.tkzc00.kongojbackend.model.entity.User;

/**
* @author tkzc00
* @description 针对表【question_submit(题目提交表)】的数据库操作Service
* @createDate 2024-02-15 12:27:47
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);
}
