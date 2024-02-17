package com.tkzc00.kongojcodesandbox.security;

import java.security.Permission;

/**
 * 禁用所有权限的安全管理器
 */
public class DenySecurityManager extends SecurityManager {
    /**
     * 检查所有的权限
     *
     * @param perm the requested permission.
     */
    @Override
    public void checkPermission(Permission perm) {
        throw new SecurityException("权限不足，禁止执行" + perm.toString());
    }
}
