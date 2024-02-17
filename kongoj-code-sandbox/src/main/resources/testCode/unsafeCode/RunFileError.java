import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 运行其他程序（比如危险木马）
 */
public class Main {
    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + File.separator + "src/main/resources/木马程序.bat";
        Process process = Runtime.getRuntime().exec(filePath);
        process.waitFor();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("执行完成");
    }
}
