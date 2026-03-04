package com.ssitao.code.modular.iam.authorization.domain.context;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限上下文
 * 存储当前用户的数据权限范围和部门信息
 */
public class DataPermissionContext {

    /**
     * 数据权限范围: ALL/DEPT/DEPT_AND_CHILD/SELF/CUSTOM
     */
    private static final ThreadLocal<String> DATA_SCOPE = new ThreadLocal<>();

    /**
     * 用户所属部门ID
     */
    private static final ThreadLocal<String> DEPT_ID = new ThreadLocal<>();

    /**
     * 用户所属公司ID
     */
    private static final ThreadLocal<String> COMPANY_ID = new ThreadLocal<>();

    /**
     * 自定义数据权限-允许访问的部门ID列表
     */
    private static final ThreadLocal<List<String>> ALLOWED_DEPT_IDS = new ThreadLocal<>();

    /**
     * 设置数据权限范围
     * @param dataScope 数据权限范围
     */
    public static void setDataScope(String dataScope) {
        DATA_SCOPE.set(dataScope);
    }

    /**
     * 获取数据权限范围
     * @return 数据权限范围
     */
    public static String getDataScope() {
        return DATA_SCOPE.get();
    }

    /**
     * 设置用户所属部门ID
     * @param deptId 部门ID
     */
    public static void setDeptId(String deptId) {
        DEPT_ID.set(deptId);
    }

    /**
     * 获取用户所属部门ID
     * @return 部门ID
     */
    public static String getDeptId() {
        return DEPT_ID.get();
    }

    /**
     * 设置用户所属公司ID
     * @param companyId 公司ID
     */
    public static void setCompanyId(String companyId) {
        COMPANY_ID.set(companyId);
    }

    /**
     * 获取用户所属公司ID
     * @return 公司ID
     */
    public static String getCompanyId() {
        return COMPANY_ID.get();
    }

    /**
     * 设置允许访问的部门ID列表
     * @param deptIds 部门ID列表
     */
    public static void setAllowedDeptIds(List<String> deptIds) {
        ALLOWED_DEPT_IDS.set(deptIds);
    }

    /**
     * 获取允许访问的部门ID列表
     * @return 部门ID列表
     */
    public static List<String> getAllowedDeptIds() {
        List<String> deptIds = ALLOWED_DEPT_IDS.get();
        return deptIds != null ? deptIds : new ArrayList<>();
    }

    /**
     * 清除所有上下文数据
     */
    public static void clear() {
        DATA_SCOPE.remove();
        DEPT_ID.remove();
        COMPANY_ID.remove();
        ALLOWED_DEPT_IDS.remove();
    }
}
