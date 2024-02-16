package com.tkzc00.kongojbackend.judge;

import com.tkzc00.kongojbackend.judge.strategy.DefaultJudgeStrategy;
import com.tkzc00.kongojbackend.judge.strategy.JavaLanguageJudgeStrategy;
import com.tkzc00.kongojbackend.judge.strategy.JudgeContext;
import com.tkzc00.kongojbackend.judge.strategy.JudgeStrategy;
import com.tkzc00.kongojbackend.model.dto.questionSubmit.JudgeInfo;
import com.tkzc00.kongojbackend.model.entity.QuestionSubmit;
import com.tkzc00.kongojbackend.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Component;

/**
 * 判题管理，简化调用
 */
@Component
public class JudgeManager {
    /**
     * 执行判题
     *
     * @param judgeContext 判题上下文
     * @return 判题结果
     */
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
