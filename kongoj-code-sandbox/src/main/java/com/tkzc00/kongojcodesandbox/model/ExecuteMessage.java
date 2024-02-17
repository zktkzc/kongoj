package com.tkzc00.kongojcodesandbox.model;

import lombok.Data;

/**
 * 进程执行信息
 */
@Data
public class ExecuteMessage {
    private Integer exitCode;
    private String message;
    private String errorMessage;
    private Long time;
    private Long memory;
}
