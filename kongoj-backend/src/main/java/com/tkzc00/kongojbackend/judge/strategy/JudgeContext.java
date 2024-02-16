package com.tkzc00.kongojbackend.judge.strategy;

import com.tkzc00.kongojbackend.model.dto.question.JudgeCase;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.JudgeInfo;
import com.tkzc00.kongojbackend.model.entity.Question;
import com.tkzc00.kongojbackend.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 判题上下文，用于定义在策略中传递的参数
 */
@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;
    private List<String> inputList;
    private List<String> outputList;
    private Question question;
    private List<JudgeCase> judgeCaseList;
    private QuestionSubmit questionSubmit;
}
