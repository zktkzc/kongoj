/**
 * 我的安全管理器
 */
public class MySecurityManager extends SecurityManager {
    /**
     * 检测是否有执行系统命令的权限
     * @param cmd   the specified system command.
     */
    @Override
    public void checkExec(String cmd) {
        throw new SecurityException("权限不足，禁止执行系统命令：" + cmd);
    }

    /**
     * 检测是否有读取文件的权限
     * @param file   the file to be read.
     */
    @Override
    public void checkRead(String file) {
        throw new SecurityException("权限不足，禁止读取文件：" + file);
    }

    /**
     * 检测是否有写文件的权限
     * @param file   the file to be written to.
     */
    @Override
    public void checkWrite(String file) {
        throw new SecurityException("权限不足，禁止写文件：" + file);
    }

    /**
     * 检测是否有删除文件的权限
     * @param file   the file to be deleted.
     */
    @Override
    public void checkDelete(String file) {
        throw new SecurityException("权限不足，禁止删除文件：" + file);
    }

    /**
     * 检测是否有连接到主机的权限
     * @param host   the host to which the connection is made.
     * @param port   the port to which the connection is made.
     */
    @Override
    public void checkConnect(String host, int port) {
        throw new SecurityException("权限不足，禁止连接到主机：" + host + ":" + port);
    }
}
