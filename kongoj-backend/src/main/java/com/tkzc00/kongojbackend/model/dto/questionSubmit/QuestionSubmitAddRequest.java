package com.tkzc00.kongojbackend.model.dto.questionSubmit;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目提交请求
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 用户代码
     */
    private String code;

    private static final long serialVersionUID = 1L;
}