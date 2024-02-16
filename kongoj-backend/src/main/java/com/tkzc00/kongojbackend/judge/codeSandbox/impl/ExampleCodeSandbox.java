package com.tkzc00.kongojbackend.judge.codeSandbox.impl;

import com.tkzc00.kongojbackend.judge.codeSandbox.CodeSandbox;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojbackend.judge.codeSandbox.model.ExecuteCodeResponse;
import com.tkzc00.kongojbackend.model.dto.question.JudgeConfig;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.JudgeInfo;
import com.tkzc00.kongojbackend.model.enums.JudgeInfoMessageEnum;
import com.tkzc00.kongojbackend.model.enums.QuestionSubmitStatusEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 示例代码沙箱（只为了跑通业务）
 */
@Slf4j
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(executeCodeRequest.getInputList());
        executeCodeResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
