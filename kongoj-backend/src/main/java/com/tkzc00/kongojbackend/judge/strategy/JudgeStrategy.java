package com.tkzc00.kongojbackend.judge.strategy;

import com.tkzc00.kongojbackend.model.dto.questionSubmit.JudgeInfo;

/**
 * 判题策略接口
 */
public interface JudgeStrategy {
    /**
     * 执行判题
     * @param judgeContext 判题上下文
     * @return 判题结果
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
