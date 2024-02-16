package com.tkzc00.kongojbackend.judge.codeSandbox.impl;

import com.tkzc00.kongojbackend.judge.codeSandbox.CodeSandbox;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
