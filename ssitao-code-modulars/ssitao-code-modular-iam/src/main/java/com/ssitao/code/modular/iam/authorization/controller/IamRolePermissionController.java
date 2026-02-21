package com.ssitao.code.modular.iam.authorization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.service.IamRolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM角色权限关联管理控制器
 * 管理 tb_iam_role_permission 表，处理角色与权限的关联关系
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM角色权限关联", description = "IAM角色与权限关联管理接口")
@RestController
@RequestMapping("/iam/role-permission")
public class IamRolePermissionController {

    private final IamRolePermissionService rolePermissionService;

    public IamRolePermissionController(IamRolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    // ==================== 角色权限关联管理接口 ====================

    @PostMapping("/assign")
    @Operation(summary = "为角色分配权限", description = "为角色分配一个或多个权限")
    public CommonResult<Void> assignPermissions(@RequestParam String roleId,
                                                 @RequestBody List<String> permissionIds,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        rolePermissionService.assignPermissions(roleId, permissionIds, tenantId);
        return success();
    }

    @PostMapping("/revoke")
    @Operation(summary = "撤销角色权限", description = "撤销角色中的一个或多个权限")
    public CommonResult<Void> revokePermissions(@RequestParam String roleId,
                                                @RequestBody List<String> permissionIds,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        rolePermissionService.revokePermissions(roleId, permissionIds, tenantId);
        return success();
    }

    @PostMapping("/revoke-all")
    @Operation(summary = "撤销角色所有权限", description = "撤销角色的所有权限")
    public CommonResult<Void> revokeAllPermissions(@RequestParam String roleId,
                                                   @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        rolePermissionService.revokeAllPermissions(roleId, tenantId);
        return success();
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "获取角色的权限列表", description = "获取指定角色的所有权限")
    public CommonResult<List<IamPermissionDTO>> getRolePermissions(@PathVariable String roleId,
                                                                   @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermissionDTO> permissions = rolePermissionService.getRolePermissions(roleId, tenantId);
        return success(permissions);
    }

    @GetMapping("/permission/{permissionId}")
    @Operation(summary = "获取权限下的角色列表", description = "获取指定权限下的所有角色")
    public CommonResult<List<IamRoleDTO>> getPermissionRoles(@PathVariable String permissionId,
                                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> roles = rolePermissionService.getPermissionRoles(permissionId, tenantId);
        return success(roles);
    }

    @PostMapping("/check")
    @Operation(summary = "检查角色是否有指定权限", description = "检查角色是否拥有指定权限")
    public CommonResult<Boolean> checkPermission(@RequestParam String roleId,
                                                 @RequestParam String permissionId,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        Boolean hasPermission = rolePermissionService.checkPermission(roleId, permissionId, tenantId);
        return success(hasPermission);
    }

    @PostMapping("/batch-assign")
    @Operation(summary = "批量分配权限", description = "为多个角色批量分配权限")
    public CommonResult<Void> batchAssignPermissions(@RequestBody List<String> roleIds,
                                                     @RequestBody List<String> permissionIds,
                                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        rolePermissionService.batchAssignPermissions(roleIds, permissionIds, tenantId);
        return success();
    }

}
