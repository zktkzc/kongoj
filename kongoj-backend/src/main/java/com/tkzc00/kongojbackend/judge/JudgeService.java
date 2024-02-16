package com.tkzc00.kongojbackend.judge;

import com.tkzc00.kongojbackend.model.entity.QuestionSubmit;

public interface JudgeService {
    /**
     * 判题服务
     *
     * @param questionSubmitId 题目提交ID
     * @return 判题结果
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
