package com.tkzc00.kongojbackend.judge.codeSandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.tkzc00.kongojbackend.common.ErrorCode;
import com.tkzc00.kongojbackend.exception.BusinessException;
import com.tkzc00.kongojbackend.judge.codeSandbox.CodeSandbox;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = "http://localhost:8090/sandbox/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String response = HttpUtil.createPost(url).body(json).header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET).execute().body();
        if (StrUtil.isBlank(response)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "调用代码沙箱失败：" + response);
        }
        return JSONUtil.toBean(response, ExecuteCodeResponse.class);
    }
}
