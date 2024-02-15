package com.tkzc00.kongojbackend.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息枚举
 *
 */
public enum JudgeInfoMessageEnum {

    ACCEPTED("成功", "成功"),
    WRONG_ANSWER("答案错误", "答案错误"),
    COMPILE_ERROR("编译错误", "编译错误"),
    MEMORY_LIMIT_EXCEEDED("内存溢出", "内存溢出"),
    TIME_LIMIT_EXCEEDED("时长超时", "时长超时"),
    OUTPUT_LIMIT_EXCEEDED("输出超限", "输出超限"),
    PRESENTATION_ERROR("展示错误", "展示错误"),
    WAITING("等待中", "等待中"),
    DANGEROUS_OPERATION("危险操作", "危险操作"),
    RUNTIME_ERROR("运行时异常", "运行时异常"),
    SYSTEM_ERROR("系统异常", "系统异常");

    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
