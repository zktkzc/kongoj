import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 像服务器写文件（植入木马程序）
 */
public class Main {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + File.separator + "src/main/resources/木马程序.bat";
        String errorProgram = "java -version 2>&1";
        Files.write(Paths.get(filePath), Arrays.asList(errorProgram));
        System.out.println("写入木马成功！");
    }
}
