package com.ssitao.code.modular.iam.authorization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermGroupDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermGroupUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermGroupAppService;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermissionAppService;
import com.ssitao.code.modular.iam.authorization.application.service.IamRoleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM授权管理控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM授权管理", description = "IAM角色、权限、权限组相关接口")
@RestController
@RequestMapping("/iam/authorization")
public class IamAuthorizationController {

    private final IamRoleAppService roleAppService;
    private final IamPermissionAppService permissionAppService;
    private final IamPermGroupAppService permGroupAppService;

    public IamAuthorizationController(IamRoleAppService roleAppService,
                                       IamPermissionAppService permissionAppService,
                                       IamPermGroupAppService permGroupAppService) {
        this.roleAppService = roleAppService;
        this.permissionAppService = permissionAppService;
        this.permGroupAppService = permGroupAppService;
    }

    // ==================== 角色管理接口 ====================

    @PostMapping("/role")
    @Operation(summary = "创建角色", description = "创建新的角色")
    public CommonResult<Long> createRole(@Valid @RequestBody IamRoleCreateCommand command) {
        Long roleId = roleAppService.createRole(command);
        return success(roleId);
    }

    @PutMapping("/role")
    @Operation(summary = "更新角色", description = "更新角色信息")
    public CommonResult<Void> updateRole(@Valid @RequestBody IamRoleUpdateCommand command) {
        roleAppService.updateRole(command);
        return success();
    }

    @DeleteMapping("/role/{id}")
    @Operation(summary = "删除角色", description = "删除指定角色")
    public CommonResult<Void> deleteRole(@PathVariable Long id,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.deleteRole(id, tenantId);
        return success();
    }

    @GetMapping("/role/{id}")
    @Operation(summary = "获取角色详情", description = "根据ID获取角色详情")
    public CommonResult<IamRoleDTO> getRole(@PathVariable Long id,
                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamRoleDTO role = roleAppService.getRoleById(id, tenantId);
        return success(role);
    }

    @GetMapping("/roles")
    @Operation(summary = "获取角色列表", description = "获取所有角色列表")
    public CommonResult<List<IamRoleDTO>> listRoles(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> roles = roleAppService.listRoles(tenantId);
        return success(roles);
    }

    @GetMapping("/role/tree")
    @Operation(summary = "获取角色树", description = "获取角色树形结构")
    public CommonResult<List<IamRoleDTO>> getRoleTree(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> tree = roleAppService.getRoleTree(tenantId);
        return success(tree);
    }

    @PostMapping("/role/assign-permissions")
    @Operation(summary = "分配权限给角色", description = "为角色分配权限")
    public CommonResult<Void> assignPermissions(@Valid @RequestBody IamRoleAssignPermissionCommand command) {
        roleAppService.assignPermissions(command);
        return success();
    }

    @PutMapping("/role/{id}/enable")
    @Operation(summary = "启用角色", description = "启用指定角色")
    public CommonResult<Void> enableRole(@PathVariable Long id,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.enableRole(id, tenantId);
        return success();
    }

    @PutMapping("/role/{id}/disable")
    @Operation(summary = "禁用角色", description = "禁用指定角色")
    public CommonResult<Void> disableRole(@PathVariable Long id,
                                           @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.disableRole(id, tenantId);
        return success();
    }

    // ==================== 权限管理接口 ====================

    @PostMapping("/permission")
    @Operation(summary = "创建权限", description = "创建新的权限")
    public CommonResult<Long> createPermission(@Valid @RequestBody IamPermissionCreateCommand command) {
        Long permissionId = permissionAppService.createPermission(command);
        return success(permissionId);
    }

    @PutMapping("/permission")
    @Operation(summary = "更新权限", description = "更新权限信息")
    public CommonResult<Void> updatePermission(@Valid @RequestBody IamPermissionUpdateCommand command) {
        permissionAppService.updatePermission(command);
        return success();
    }

    @DeleteMapping("/permission/{id}")
    @Operation(summary = "删除权限", description = "删除指定权限")
    public CommonResult<Void> deletePermission(@PathVariable Long id,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permissionAppService.deletePermission(id, tenantId);
        return success();
    }

    @GetMapping("/permission/{id}")
    @Operation(summary = "获取权限详情", description = "根据ID获取权限详情")
    public CommonResult<IamPermissionDTO> getPermission(@PathVariable Long id,
                                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamPermissionDTO permission = permissionAppService.getPermissionById(id, tenantId);
        return success(permission);
    }

    @GetMapping("/permissions")
    @Operation(summary = "获取权限列表", description = "获取所有权限列表")
    public CommonResult<List<IamPermissionDTO>> listPermissions(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermissionDTO> permissions = permissionAppService.listPermissions(tenantId);
        return success(permissions);
    }

    @GetMapping("/permission/tree")
    @Operation(summary = "获取权限树", description = "获取权限树形结构")
    public CommonResult<List<IamPermissionDTO>> getPermissionTree(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermissionDTO> tree = permissionAppService.getPermissionTree(tenantId);
        return success(tree);
    }

    @GetMapping("/permissions/type/{permType}")
    @Operation(summary = "根据类型获取权限", description = "根据权限类型获取权限列表")
    public CommonResult<List<IamPermissionDTO>> listPermissionsByType(@PathVariable String permType,
                                                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermissionDTO> permissions = permissionAppService.listPermissionsByType(permType, tenantId);
        return success(permissions);
    }

    @GetMapping("/permissions/user/{userId}")
    @Operation(summary = "获取用户权限", description = "获取指定用户的权限列表")
    public CommonResult<List<IamPermissionDTO>> listPermissionsByUserId(@PathVariable Long userId,
                                                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermissionDTO> permissions = permissionAppService.listPermissionsByUserId(userId, tenantId);
        return success(permissions);
    }

    @PutMapping("/permission/{id}/enable")
    @Operation(summary = "启用权限", description = "启用指定权限")
    public CommonResult<Void> enablePermission(@PathVariable Long id,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permissionAppService.enablePermission(id, tenantId);
        return success();
    }

    @PutMapping("/permission/{id}/disable")
    @Operation(summary = "禁用权限", description = "禁用指定权限")
    public CommonResult<Void> disablePermission(@PathVariable Long id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permissionAppService.disablePermission(id, tenantId);
        return success();
    }

    // ==================== 权限组管理接口 ====================

    @PostMapping("/perm-group")
    @Operation(summary = "创建权限组", description = "创建新的权限组")
    public CommonResult<Long> createPermGroup(@Valid @RequestBody IamPermGroupCreateCommand command) {
        Long groupId = permGroupAppService.createPermGroup(command);
        return success(groupId);
    }

    @PutMapping("/perm-group")
    @Operation(summary = "更新权限组", description = "更新权限组信息")
    public CommonResult<Void> updatePermGroup(@Valid @RequestBody IamPermGroupUpdateCommand command) {
        permGroupAppService.updatePermGroup(command);
        return success();
    }

    @DeleteMapping("/perm-group/{id}")
    @Operation(summary = "删除权限组", description = "删除指定权限组")
    public CommonResult<Void> deletePermGroup(@PathVariable Long id,
                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permGroupAppService.deletePermGroup(id, tenantId);
        return success();
    }

    @GetMapping("/perm-group/{id}")
    @Operation(summary = "获取权限组详情", description = "根据ID获取权限组详情")
    public CommonResult<IamPermGroupDTO> getPermGroup(@PathVariable Long id,
                                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamPermGroupDTO group = permGroupAppService.getPermGroupById(id, tenantId);
        return success(group);
    }

    @GetMapping("/perm-groups")
    @Operation(summary = "获取权限组列表", description = "获取所有权限组列表")
    public CommonResult<List<IamPermGroupDTO>> listPermGroups(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPermGroupDTO> groups = permGroupAppService.listPermGroups(tenantId);
        return success(groups);
    }

    @PostMapping("/perm-group/assign-permissions")
    @Operation(summary = "分配权限给权限组", description = "为权限组分配权限")
    public CommonResult<Void> assignPermissionsToGroup(@Valid @RequestBody IamPermGroupAssignPermissionCommand command) {
        permGroupAppService.assignPermissions(command);
        return success();
    }

    @PutMapping("/perm-group/{id}/enable")
    @Operation(summary = "启用权限组", description = "启用指定权限组")
    public CommonResult<Void> enablePermGroup(@PathVariable Long id,
                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permGroupAppService.enablePermGroup(id, tenantId);
        return success();
    }

    @PutMapping("/perm-group/{id}/disable")
    @Operation(summary = "禁用权限组", description = "禁用指定权限组")
    public CommonResult<Void> disablePermGroup(@PathVariable Long id,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        permGroupAppService.disablePermGroup(id, tenantId);
        return success();
    }

}
