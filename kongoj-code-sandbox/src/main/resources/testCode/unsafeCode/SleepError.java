import java.util.concurrent.TimeUnit;

/**
 * 无限睡眠（阻塞程序执行）
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        long sleepTime = TimeUnit.HOURS.toMillis(1);
        Thread.sleep(sleepTime);
        System.out.println("睡眠结束");
    }
}
