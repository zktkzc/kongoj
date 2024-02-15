package com.tkzc00.kongojbackend.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（KB）
     */
    private Long timeLimit;
    /**
     * 内存限制（毫秒）
     */
    private Long memoryLimit;
    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;
}
