package com.tkzc00.kongojcodesandbox;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeRequest;
import com.tkzc00.kongojcodesandbox.model.ExecuteCodeResponse;
import com.tkzc00.kongojcodesandbox.model.ExecuteMessage;
import com.tkzc00.kongojcodesandbox.model.JudgeInfo;
import com.tkzc00.kongojcodesandbox.utils.ProcessUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Java代码沙箱模板类
 */
@Slf4j
public abstract class JavaCodeSandboxTemplate implements CodeSandbox {
    private static final String GLOBAL_CODE_DIR_NAME = "tmpCode";
    private static final String GLOBAL_JAVA_CLASS_NAME = "Main.java";
    private static final long TIME_OUT = 5000L;

    /**
     * 把用户的代码保存为文件
     *
     * @param code 用户的代码
     * @return 文件
     */
    public File saveCodeToFile(String code) {
        // 1. 把用户的代码保存为文件
        String userDir = System.getProperty("user.dir");
        String globalCodePathName = userDir + File.separator + GLOBAL_CODE_DIR_NAME;
        // 判断全局代码目录是否存在
        if (!FileUtil.exist(globalCodePathName)) {
            FileUtil.mkdir(globalCodePathName);
        }
        // 把用户的代码隔离存放
        String userCodeParentPath = globalCodePathName + File.separator + UUID.randomUUID();
        String userCodePath = userCodeParentPath + File.separator + GLOBAL_JAVA_CLASS_NAME;
        return FileUtil.writeString(code, userCodePath, StandardCharsets.UTF_8);
    }

    /**
     * 编译代码，得到class文件
     *
     * @param file 代码文件
     * @return 执行信息
     */
    public ExecuteMessage compileFile(File file) {
        ExecuteMessage compileMessage = null;
        try {
            String compileCommand = String.format("javac -encoding utf-8 %s", file.getAbsolutePath());
            Process compileProcess = Runtime.getRuntime().exec(compileCommand);
            compileMessage = ProcessUtils.runProcessAndGetMessage(compileProcess, "编译");
            if (compileMessage.getExitCode() != 0)
                throw new RuntimeException("编译错误");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return compileMessage;
    }

    /**
     * 执行文件，获得执行结果列表
     *
     * @param userCodeFile 用户代码文件
     * @param inputList    输入用例列表
     * @return 执行结果列表
     */
    public List<ExecuteMessage> runFile(File userCodeFile, List<String> inputList) {
        String userCodeParentPath = userCodeFile.getParentFile().getAbsolutePath();
        List<ExecuteMessage> executeMessageList = new ArrayList<>();
        for (String inputArgs : inputList) {
            String runCommand = String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main %s", userCodeParentPath, inputArgs);
            try {
                Process runProcess = Runtime.getRuntime().exec(runCommand);
                new Thread(() -> {
                    try {
                        // 超时强制结束
                        Thread.sleep(TIME_OUT);
                        System.out.println("程序超时，强制结束");
                        runProcess.destroy();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                ExecuteMessage executeMessage = ProcessUtils.runProcessAndGetMessage(runProcess, "运行");
                System.out.println("运行输出：" + executeMessage);
                executeMessageList.add(executeMessage);
            } catch (Exception e) {
                throw new RuntimeException("程序执行异常：" + e);
            }
        }
        return executeMessageList;
    }

    /**
     * 获取响应结果
     *
     * @param executeMessageList 执行结果列表
     * @return 响应结果
     */
    public ExecuteCodeResponse getOutputResponse(List<ExecuteMessage> executeMessageList) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        List<String> outputList = new ArrayList<>();
        long maxTime = 0L;
        for (ExecuteMessage executeMessage : executeMessageList) {
            String errorMessage = executeMessage.getErrorMessage();
            if (StrUtil.isNotBlank(errorMessage)) {
                outputList.add(errorMessage);
                // 执行中存在错误
                executeCodeResponse.setStatus(3);
                break;
            }
            outputList.add(executeMessage.getMessage());
            if (executeMessage.getTime() != null && executeMessage.getTime() > maxTime) {
                maxTime = executeMessage.getTime();
            }
        }
        if (outputList.size() == executeMessageList.size()) {
            executeCodeResponse.setStatus(1);
        }
        executeCodeResponse.setOutputList(outputList);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(maxTime);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }

    /**
     * 删除文件
     *
     * @param userCodeFile 用户代码文件
     * @return 是否删除成功
     */
    public boolean deleteFile(File userCodeFile) {
        boolean success = false;
        if (userCodeFile.getParentFile() != null) {
            success = FileUtil.del(userCodeFile.getParentFile().getAbsolutePath());
            System.out.println("删除" + (success ? "成功" : "失败"));
        }
        return success;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        String code = executeCodeRequest.getCode();
        String language = executeCodeRequest.getLanguage();

        // 1. 把用户的代码保存为文件
        File file = saveCodeToFile(code);

        // 2. 编译代码，得到class文件
        ExecuteMessage compileMessage = compileFile(file);
        System.out.println("编译输出：" + compileMessage);

        // 3. 执行代码，得到执行结果
        List<ExecuteMessage> executeMessageList = runFile(file, inputList);

        // 4. 收集整理输出
        ExecuteCodeResponse executeCodeResponse = getOutputResponse(executeMessageList);

        // 5. 文件清理
        boolean success = deleteFile(file);
        if (!success)
            log.error("删除文件失败：" + file.getAbsolutePath());

        return executeCodeResponse;
    }

    /**
     * 获取错误响应
     *
     * @param e 异常
     * @return 错误响应
     */
    private ExecuteCodeResponse getErrorResponse(Throwable e) {
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setStatus(2);
        executeCodeResponse.setMessage(e.getMessage());
        executeCodeResponse.setJudgeInfo(new JudgeInfo());
        executeCodeResponse.setOutputList(new ArrayList<>());
        return executeCodeResponse;
    }
}
