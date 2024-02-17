package com.tkzc00.kongojcodesandbox.utils;

import cn.hutool.core.util.StrUtil;
import com.tkzc00.kongojcodesandbox.model.ExecuteMessage;
import org.springframework.util.StopWatch;

import java.io.*;

/**
 * 进程工具类
 */
public class ProcessUtils {
    /**
     * 执行进程并获取执行信息
     *
     * @param runProcess 进程
     * @param operation  操作名称
     * @return 执行信息
     */
    public static ExecuteMessage runProcessAndGetMessage(Process runProcess, String operation) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        StringBuilder compileOutputStringBuilder = new StringBuilder();
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 等待程序执行，获取错误码
            int exitCode = runProcess.waitFor();
            executeMessage.setExitCode(exitCode);
            if (exitCode == 0) {
                // 程序正常退出
                System.out.println(operation + "成功");
                // 分批获取编译正常输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
                // 循环读取并打印编译输出
                String compileOutputLine;
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setMessage(compileOutputStringBuilder.toString());
            } else {
                // 程序异常退出
                System.out.println(operation + "失败，错误码：" + exitCode);
                // 分批获取编译错误输出
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                // 循环读取并打印编译错误输出
                String compileOutputLine;
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setErrorMessage(compileOutputStringBuilder.toString());
            }
            stopWatch.stop();
            executeMessage.setTime(stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }

    /**
     * 执行交互式进程并获取执行信息
     *
     * @param runProcess 进程
     * @param operation  操作名称
     * @return 执行信息
     */
    public static ExecuteMessage runInteractProcessAndGetMessage(Process runProcess, String operation, String args) {
        ExecuteMessage executeMessage = new ExecuteMessage();
        StringBuilder compileOutputStringBuilder = new StringBuilder();
        try {
            // 向控制台输入参数
            OutputStream outputStream = runProcess.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] argArr = args.split(" ");
            outputStreamWriter.write(StrUtil.join("\n", argArr) + "\n");
            // 相当于按了回车，执行输入的发送
            outputStreamWriter.flush();

            // 分批获取编译正常输出
            InputStream inputStream = runProcess.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // 循环读取并打印编译输出
            String compileOutputLine;
            while ((compileOutputLine = bufferedReader.readLine()) != null) {
                compileOutputStringBuilder.append(compileOutputLine).append("\n");
            }
            executeMessage.setMessage(compileOutputStringBuilder.toString());

            // 等待程序执行，获取错误码
            int exitCode = runProcess.waitFor();
            executeMessage.setExitCode(exitCode);
            if (exitCode == 0) {
                // 程序正常退出
                System.out.println(operation + "成功");
            } else {
                // 程序异常退出
                System.out.println(operation + "失败，错误码：" + exitCode);
                // 分批获取编译错误输出
                bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));
                compileOutputStringBuilder = new StringBuilder();
                // 循环读取并打印编译错误输出
                while ((compileOutputLine = bufferedReader.readLine()) != null) {
                    compileOutputStringBuilder.append(compileOutputLine).append("\n");
                }
                executeMessage.setErrorMessage(compileOutputStringBuilder.toString());
            }

            // 释放资源，否则会卡死
            bufferedReader.close();
            inputStream.close();
            outputStreamWriter.close();
            outputStream.close();
            runProcess.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return executeMessage;
    }
}
