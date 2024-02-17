package com.tkzc00.kongojcodesandbox.controller;

import com.tkzc00.kongojcodesandbox.JavaNativeCodeSandbox;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sandbox")
public class MainController {
    @Resource
    private JavaNativeCodeSandbox sandbox;

    // 定义鉴权请求头和密钥
    public static final String AUTH_REQUEST_HEADER = "auth";
    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletRequest request, HttpServletResponse response) {
        // 进行基本的认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHeader)) {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null)
            throw new RuntimeException("请求参数为空");
        return sandbox.executeCode(executeCodeRequest);
    }
}
