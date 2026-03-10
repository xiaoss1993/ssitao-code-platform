package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.IdUtil;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 权限测试数据生成器 - 生成具有层级结构的权限数据
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamPermissionDataGenerator {

    /**
     * 生成具有层级结构的权限数据
     *
     * @param tenantId 租户ID
     * @return 权限列表
     */
    public List<IamPermissionDO> generate(String tenantId) {
        List<IamPermissionDO> permissions = new ArrayList<>();

        // ==================== 一级菜单：系统管理 ====================
        String systemManageId = "PERM_SYSTEM";
        permissions.add(createMenu(systemManageId, "system", "系统管理", "0", 1, tenantId));

        // 系统管理 -> 用户管理
        String userManageId = "PERM_USER";
        permissions.add(createMenu(userManageId, "system:user", "用户管理", systemManageId, 1, tenantId));
        permissions.add(createButton("PERM_USER_ADD", "system:user:add", "新增用户", userManageId, tenantId));
        permissions.add(createButton("PERM_USER_EDIT", "system:user:edit", "编辑用户", userManageId, tenantId));
        permissions.add(createButton("PERM_USER_DEL", "system:user:del", "删除用户", userManageId, tenantId));
        permissions.add(createButton("PERM_USER_EXPORT", "system:user:export", "导出用户", userManageId, tenantId));
        permissions.add(createButton("PERM_USER_RESET_PWD", "system:user:resetPwd", "重置密码", userManageId, tenantId));

        // 系统管理 -> 角色管理
        String roleManageId = "PERM_ROLE";
        permissions.add(createMenu(roleManageId, "system:role", "角色管理", systemManageId, 2, tenantId));
        permissions.add(createButton("PERM_ROLE_ADD", "system:role:add", "新增角色", roleManageId, tenantId));
        permissions.add(createButton("PERM_ROLE_EDIT", "system:role:edit", "编辑角色", roleManageId, tenantId));
        permissions.add(createButton("PERM_ROLE_DEL", "system:role:del", "删除角色", roleManageId, tenantId));
        permissions.add(createButton("PERM_ROLE_AUTH", "system:role:auth", "分配权限", roleManageId, tenantId));

        // 系统管理 -> 菜单管理
        String menuManageId = "PERM_MENU";
        permissions.add(createMenu(menuManageId, "system:menu", "菜单管理", systemManageId, 3, tenantId));
        permissions.add(createButton("PERM_MENU_ADD", "system:menu:add", "新增菜单", menuManageId, tenantId));
        permissions.add(createButton("PERM_MENU_EDIT", "system:menu:edit", "编辑菜单", menuManageId, tenantId));
        permissions.add(createButton("PERM_MENU_DEL", "system:menu:del", "删除菜单", menuManageId, tenantId));

        // 系统管理 -> 权限管理
        String permManageId = "PERM_PERM";
        permissions.add(createMenu(permManageId, "system:permission", "权限管理", systemManageId, 4, tenantId));
        permissions.add(createButton("PERM_PERM_ADD", "system:permission:add", "新增权限", permManageId, tenantId));
        permissions.add(createButton("PERM_PERM_EDIT", "system:permission:edit", "编辑权限", permManageId, tenantId));
        permissions.add(createButton("PERM_PERM_DEL", "system:permission:del", "删除权限", permManageId, tenantId));

        // 系统管理 -> 租户管理
        String tenantManageId = "PERM_TENANT";
        permissions.add(createMenu(tenantManageId, "system:tenant", "租户管理", systemManageId, 5, tenantId));
        permissions.add(createButton("PERM_TENANT_ADD", "system:tenant:add", "新增租户", tenantManageId, tenantId));
        permissions.add(createButton("PERM_TENANT_EDIT", "system:tenant:edit", "编辑租户", tenantManageId, tenantId));
        permissions.add(createButton("PERM_TENANT_DEL", "system:tenant:del", "删除租户", tenantManageId, tenantId));

        // ==================== 一级菜单：运营管理 ====================
        String operationManageId = "PERM_OPERATION";
        permissions.add(createMenu(operationManageId, "operation", "运营管理", "0", 2, tenantId));

        // 运营管理 -> 组织管理
        String orgManageId = "PERM_ORG";
        permissions.add(createMenu(orgManageId, "operation:org", "组织管理", operationManageId, 1, tenantId));
        permissions.add(createButton("PERM_ORG_ADD", "operation:org:add", "新增组织", orgManageId, tenantId));
        permissions.add(createButton("PERM_ORG_EDIT", "operation:org:edit", "编辑组织", orgManageId, tenantId));
        permissions.add(createButton("PERM_ORG_DEL", "operation:org:del", "删除组织", orgManageId, tenantId));

        // 运营管理 -> 岗位管理
        String postManageId = "PERM_POST";
        permissions.add(createMenu(postManageId, "operation:post", "岗位管理", operationManageId, 2, tenantId));
        permissions.add(createButton("PERM_POST_ADD", "operation:post:add", "新增岗位", postManageId, tenantId));
        permissions.add(createButton("PERM_POST_EDIT", "operation:post:edit", "编辑岗位", postManageId, tenantId));
        permissions.add(createButton("PERM_POST_DEL", "operation:post:del", "删除岗位", postManageId, tenantId));

        // 运营管理 -> 字典管理
        String dictManageId = "PERM_DICT";
        permissions.add(createMenu(dictManageId, "operation:dict", "字典管理", operationManageId, 3, tenantId));
        permissions.add(createButton("PERM_DICT_ADD", "operation:dict:add", "新增字典", dictManageId, tenantId));
        permissions.add(createButton("PERM_DICT_EDIT", "operation:dict:edit", "编辑字典", dictManageId, tenantId));
        permissions.add(createButton("PERM_DICT_DEL", "operation:dict:del", "删除字典", dictManageId, tenantId));

        // ==================== 一级菜单：日志管理 ====================
        String logManageId = "PERM_LOG";
        permissions.add(createMenu(logManageId, "log", "日志管理", "0", 3, tenantId));

        // 日志管理 -> 操作日志
        String operLogId = "PERM_OPER_LOG";
        permissions.add(createMenu(operLogId, "log:oper", "操作日志", logManageId, 1, tenantId));
        permissions.add(createButton("PERM_OPER_LOG_VIEW", "log:oper:view", "查看日志", operLogId, tenantId));
        permissions.add(createButton("PERM_OPER_LOG_DEL", "log:oper:del", "删除日志", operLogId, tenantId));
        permissions.add(createButton("PERM_OPER_LOG_EXPORT", "log:oper:export", "导出日志", operLogId, tenantId));

        // 日志管理 -> 登录日志
        String loginLogId = "PERM_LOGIN_LOG";
        permissions.add(createMenu(loginLogId, "log:login", "登录日志", logManageId, 2, tenantId));
        permissions.add(createButton("PERM_LOGIN_LOG_VIEW", "log:login:view", "查看日志", loginLogId, tenantId));
        permissions.add(createButton("PERM_LOGIN_LOG_DEL", "log:login:del", "删除日志", loginLogId, tenantId));

        // ==================== 一级菜单：元数据管理 ====================
        String metaManageId = "PERM_META";
        permissions.add(createMenu(metaManageId, "meta", "元数据管理", "0", 4, tenantId));

        // 元数据管理 -> 实体管理
        String entityManageId = "PERM_ENTITY";
        permissions.add(createMenu(entityManageId, "meta:entity", "实体管理", metaManageId, 1, tenantId));
        permissions.add(createButton("PERM_ENTITY_ADD", "meta:entity:add", "新增实体", entityManageId, tenantId));
        permissions.add(createButton("PERM_ENTITY_EDIT", "meta:entity:edit", "编辑实体", entityManageId, tenantId));
        permissions.add(createButton("PERM_ENTITY_DEL", "meta:entity:del", "删除实体", entityManageId, tenantId));
        permissions.add(createButton("PERM_ENTITY_SYNC", "meta:entity:sync", "同步实体", entityManageId, tenantId));

        // 元数据管理 -> 页面管理
        String pageManageId = "PERM_PAGE";
        permissions.add(createMenu(pageManageId, "meta:page", "页面管理", metaManageId, 2, tenantId));
        permissions.add(createButton("PERM_PAGE_ADD", "meta:page:add", "新增页面", pageManageId, tenantId));
        permissions.add(createButton("PERM_PAGE_EDIT", "meta:page:edit", "编辑页面", pageManageId, tenantId));
        permissions.add(createButton("PERM_PAGE_DEL", "meta:page:del", "删除页面", pageManageId, tenantId));

        return permissions;
    }

    /**
     * 创建菜单权限
     */
    private IamPermissionDO createMenu(String id, String code, String name, String parentId, int sort, String tenantId) {
        return IamPermissionDO.builder()
                .permissionId(id)
                .permissionCode(code)
                .permissionName(name)
                .permissionType("MENU")
                .parentId(parentId)
                .permissionResource(code)
                .permissionAction("VIEW")
                .permissionDesc(name + "菜单")
                .permissionStatus(1)
                .permissionIsBuiltin(0)
                .permissionSort(sort * 100)
                .tenantId(tenantId)
                .createTime(LocalDateTime.now())
                .isDeleted(0)
                .build();
    }

    /**
     * 创建按钮权限
     */
    private IamPermissionDO createButton(String id, String code, String name, String parentId, String tenantId) {
        return IamPermissionDO.builder()
                .permissionId(id)
                .permissionCode(code)
                .permissionName(name)
                .permissionType("BUTTON")
                .parentId(parentId)
                .permissionResource(code.split(":")[0])
                .permissionAction(code.split(":")[code.split(":").length - 1])
                .permissionDesc(name + "按钮")
                .permissionStatus(1)
                .permissionIsBuiltin(0)
                .permissionSort(0)
                .tenantId(tenantId)
                .createTime(LocalDateTime.now())
                .isDeleted(0)
                .build();
    }

    /**
     * 生成随机权限数据（旧方法，保留兼容）
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 权限列表
     */
    public List<IamPermissionDO> generate(String tenantId, int count) {
        return generate(tenantId);
    }

    private String generateId() {
        return "PERM" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
