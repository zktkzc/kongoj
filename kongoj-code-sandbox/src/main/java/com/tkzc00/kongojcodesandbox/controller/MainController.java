package com.tkzc00.kongojcodesandbox.controller;

import com.tkzc00.kongojcodesandbox.JavaNativeCodeSandbox;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sandbox")
public class MainController {
    @Resource
    private JavaNativeCodeSandbox sandbox;

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest) {
        if (executeCodeRequest == null)
            throw new RuntimeException("请求参数为空");
        return sandbox.executeCode(executeCodeRequest);
    }
}
