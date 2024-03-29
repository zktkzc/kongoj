# OJ 判题系统-空 OJ

> by tkzc00

## 项目介绍

OJ，Online Judge，在线判题系统

用户可以选择题目，在线做题，编写代码并提交代码。系统根据出题人设置的答案判断用户的提交结果是否正确。

用于在线评测编程题目代码的系统，能够根据用户提交的代码、出题人预先设置的题目输入和输出用例，进行编译代码、运行代码、判断代码运行结果是否正确。

将判题系统作为一个开放 API 提供给开发者，便于开发者开发自己的判题系统。

## 实现核心

1. **权限校验**

谁能提交代码，谁不能提交代码

1. **代码****沙箱****（安全沙箱）**

沙箱：隔离的、安全的环境，用户的代码不会影响到沙箱之外的系统的运行

资源分配：要限制用户程序的占用资源

1. **判题规则**

题目用例的比对、结果的验证

1. **任务调度**

服务器资源有限，用户要排队，按照顺序依次执行判题，而不是直接拒绝

## 功能模块

1. 题目模块
   1. 创建题目（管理员）
   2. 删除题目（管理员）
   3. 修改题目（管理员）
   4. 搜索题目（用户）
   5. 在线做题
   6. 提交题目代码
2. 用户模块
   1. 注册
   2. 登录
3. 判题模块
   1. 提交判题（结果是否正确）
   2. 错误处理（内存溢出、安全性、超时）
   3. 自主实现代码沙箱（安全沙箱）
   4. 开放接口（提供一个独立的新服务）

## 技术选型

### 前端

- Vue3
- Arco Design 组件库
- 在线代码编辑器
- 在线文档浏览

### 后端

- Java
- SpringBoot
- Spring Cloud 微服务
- Java 进程控制
- Java 安全管理器
- JVM
- 消息队列