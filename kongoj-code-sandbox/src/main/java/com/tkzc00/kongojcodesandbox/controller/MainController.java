package com.tkzc00.kongojcodesandbox.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandbox")
public class MainController {
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
