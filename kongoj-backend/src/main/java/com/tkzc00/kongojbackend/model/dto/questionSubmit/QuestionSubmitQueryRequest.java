package com.tkzc00.kongojbackend.model.dto.questionSubmit;

import com.tkzc00.kongojbackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 题目提交查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 提交状态
     */
    private Integer status;

    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}