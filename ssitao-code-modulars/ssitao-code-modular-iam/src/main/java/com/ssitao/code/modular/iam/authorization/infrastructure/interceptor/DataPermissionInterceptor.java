package com.ssitao.code.modular.iam.authorization.infrastructure.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.authorization.domain.context.DataPermissionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 数据权限拦截器
 * 用于在请求时解析并设置用户的数据权限范围
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Component
public class DataPermissionInterceptor implements HandlerInterceptor {

    /**
     * 数据权限范围常量
     */
    public static final String DATA_SCOPE_ALL = "ALL";
    public static final String DATA_SCOPE_DEPT = "DEPT";
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "DEPT_AND_CHILD";
    public static final String DATA_SCOPE_SELF = "SELF";
    public static final String DATA_SCOPE_CUSTOM = "CUSTOM";

    /**
     * 超管角色编码
     */
    private static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断用户是否已登录
        if (!SecurityUtil.isLogin()) {
            return true;
        }

        try {
            // 获取当前登录用户
            LoginUser loginUser = SecurityUtil.getLoginUser();
            if (loginUser == null) {
                return true;
            }

            // 1. 获取数据权限范围
            String dataScope = getDataScope(loginUser);

            // 2. 获取用户所属部门ID
            String deptId = loginUser.getDeptId();

            // 3. 设置到 DataPermissionContext
            DataPermissionContext.setDataScope(dataScope);
            if (deptId != null) {
                DataPermissionContext.setDeptId(deptId);
            }

            log.debug("数据权限拦截器设置: userId={}, dataScope={}, deptId={}",
                    loginUser.getId(), dataScope, deptId);

        } catch (Exception e) {
            log.error("数据权限拦截器执行异常: {}", e.getMessage(), e);
            // 出现异常不阻断请求
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求完成后清除数据权限上下文，防止线程池复用导致数据泄露
        DataPermissionContext.clear();
    }

    /**
     * 获取数据权限范围
     * 优先级：超管 > 自定义数据权限
     *
     * @param loginUser 登录用户
     * @return 数据权限范围
     */
    private String getDataScope(LoginUser loginUser) {
        // 1. 超管拥有全部数据权限
        if (Boolean.TRUE.equals(loginUser.getSuperAdmin()) || isSuperAdmin(loginUser)) {
            return DATA_SCOPE_ALL;
        }

        // 2. 获取用户角色
        Set<String> roles = loginUser.getRoles();
        if (roles == null || roles.isEmpty()) {
            // 没有角色，默认只能看自己的数据
            return DATA_SCOPE_SELF;
        }

        // 3. 从角色中获取数据权限范围（这里可以扩展为从角色配置中读取）
        // 目前默认处理：如果有角色，根据角色级别或配置确定数据权限范围
        // 这里可以后续对接角色数据权限配置
        return determineDataScopeFromRoles(roles);
    }

    /**
     * 判断是否为超管
     *
     * @param loginUser 登录用户
     * @return 是否为超管
     */
    private boolean isSuperAdmin(LoginUser loginUser) {
        Set<String> roles = loginUser.getRoles();
        return roles != null && roles.contains(SUPER_ADMIN_ROLE);
    }

    /**
     * 根据角色确定数据权限范围
     * 这里可以根据业务需求扩展：
     * - 可以从数据库角色配置中读取数据权限范围
     * - 可以根据角色级别确定权限范围
     *
     * @param roles 角色编码集合
     * @return 数据权限范围
     */
    private String determineDataScopeFromRoles(Set<String> roles) {
        // TODO: 可以根据业务需求扩展
        // 示例实现：
        // - 管理员角色：ALL
        // - 部门经理角色：DEPT_AND_CHILD
        // - 普通员工角色：SELF

        // 目前默认返回：部门及子部门
        return DATA_SCOPE_DEPT_AND_CHILD;
    }
}
