package com.tkzc00.kongojbackend.judge.codeSandbox;

import com.tkzc00.kongojbackend.judge.codeSandbox.impl.ExampleCodeSandbox;
import com.tkzc00.kongojbackend.judge.codeSandbox.impl.RemoteCodeSandbox;
import com.tkzc00.kongojbackend.judge.codeSandbox.impl.ThirdPartCodeSandbox;

/**
 * 代码沙箱创建工厂（根据字符串参数创建指定的代码沙箱实例）
 */
public class CodeSandboxFactory {
    /**
     * 创建代码沙箱实例
     *
     * @param type 代码沙箱类型
     * @return 代码沙箱实例
     */
    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartCodeSandbox();
            case "example":
            default:
                return new ExampleCodeSandbox();
        }
    }
}
