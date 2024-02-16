package com.tkzc00.kongojcodesandbox;

import com.tkzc00.kongojcodesandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest 请求参数
     * @return 执行结果
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
