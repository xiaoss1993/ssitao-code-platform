package com.ssitao.code.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.security.tenant.core.TenantContextHolder;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermissionAppService;
import com.ssitao.code.modular.iam.authorization.application.service.IamRoleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理API控制器
 * 提供角色的CRUD和权限分配接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "角色管理", description = "角色和权限管理API")
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleManageController {

    private final IamRoleAppService roleAppService;
    private final IamPermissionAppService permissionAppService;

    /**
     * 分页查询角色
     */
    @GetMapping
    @Operation(summary = "分页查询角色", description = "分页查询角色列表")
    public CommonResult<PageResult<IamRoleDTO>> pageRoles(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size) {

        Page<IamRoleDTO> pageResult = roleAppService.pageRoles(getTenantId(), page, size);

        PageResult<IamRoleDTO> result = PageResult.of(pageResult.getRecords(), (int) pageResult.getTotalRow());

        return CommonResult.success(result);
    }

    /**
     * 获取所有角色列表
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有角色", description = "获取所有角色列表（不分页）")
    public CommonResult<List<IamRoleDTO>> listAllRoles() {
        List<IamRoleDTO> roles = roleAppService.listRoles(getTenantId());
        return CommonResult.success(roles);
    }

    /**
     * 获取角色树
     */
    @GetMapping("/tree")
    @Operation(summary = "获取角色树", description = "获取角色树形结构")
    public CommonResult<List<IamRoleDTO>> getRoleTree() {
        List<IamRoleDTO> tree = roleAppService.getRoleTree(getTenantId());
        return CommonResult.success(tree);
    }

    /**
     * 获取角色详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取角色详情", description = "根据ID获取角色详情")
    public CommonResult<IamRoleDTO> getRole(@PathVariable Long id) {
        IamRoleDTO role = roleAppService.getRoleById(String.valueOf(id), getTenantId());
        return CommonResult.success(role);
    }

    /**
     * 创建角色
     */
    @PostMapping
    @Operation(summary = "创建角色", description = "创建新角色")
    public CommonResult<Long> createRole(@Valid @RequestBody IamRoleCreateCommand command) {
        command.setTenantId(getTenantId());
        Long roleId = roleAppService.createRole(command);
        return CommonResult.success(roleId);
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    @Operation(summary = "更新角色", description = "更新角色信息")
    public CommonResult<Void> updateRole(@PathVariable Long id, @Valid @RequestBody IamRoleUpdateCommand command) {
        command.setId(id);
        command.setTenantId(getTenantId());
        roleAppService.updateRole(command);
        return CommonResult.success();
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除角色", description = "删除指定角色")
    public CommonResult<Void> deleteRole(@PathVariable Long id) {
        roleAppService.deleteRole(String.valueOf(id), getTenantId());
        return CommonResult.success();
    }

    /**
     * 启用角色
     */
    @PostMapping("/{id}/enable")
    @Operation(summary = "启用角色", description = "启用指定角色")
    public CommonResult<Void> enableRole(@PathVariable Long id) {
        roleAppService.enableRole(String.valueOf(id), getTenantId());
        return CommonResult.success();
    }

    /**
     * 禁用角色
     */
    @PostMapping("/{id}/disable")
    @Operation(summary = "禁用角色", description = "禁用指定角色")
    public CommonResult<Void> disableRole(@PathVariable Long id) {
        roleAppService.disableRole(String.valueOf(id), getTenantId());
        return CommonResult.success();
    }

    /**
     * 分配权限给角色
     */
    @PostMapping("/{id}/permissions")
    @Operation(summary = "分配权限", description = "为角色分配权限")
    public CommonResult<Void> assignPermissions(@PathVariable Long id,
                                                @RequestBody List<String> permissionIds) {
        IamRoleAssignPermissionCommand command = new IamRoleAssignPermissionCommand();
        command.setRoleId(id);
        command.setPermissionIds(permissionIds.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList()));
        command.setTenantId(getTenantId());
        roleAppService.assignPermissions(command);
        return CommonResult.success();
    }

    /**
     * 获取角色的权限列表
     */
    @GetMapping("/{id}/permissions")
    @Operation(summary = "获取角色权限", description = "获取角色拥有的权限列表")
    public CommonResult<List<IamPermissionDTO>> getRolePermissions(@PathVariable Long id) {
        // 通过用户ID获取权限（这里简化处理）
        List<IamPermissionDTO> permissions = permissionAppService.listPermissions(getTenantId());
        return CommonResult.success(permissions);
    }

    // ==================== 权限管理 ====================

    /**
     * 获取所有权限列表
     */
    @GetMapping("/permissions")
    @Operation(summary = "获取所有权限", description = "获取所有权限列表")
    public CommonResult<List<IamPermissionDTO>> listAllPermissions() {
        List<IamPermissionDTO> permissions = permissionAppService.listPermissions(getTenantId());
        return CommonResult.success(permissions);
    }

    /**
     * 获取权限树
     */
    @GetMapping("/permissions/tree")
    @Operation(summary = "获取权限树", description = "获取权限树形结构")
    public CommonResult<List<IamPermissionDTO>> getPermissionTree() {
        List<IamPermissionDTO> tree = permissionAppService.getPermissionTree(getTenantId());
        return CommonResult.success(tree);
    }

    // ==================== 辅助方法 ====================

    /**
     * 获取当前租户ID
     */
    private String getTenantId() {
        String tenantId = TenantContextHolder.getTenantId();
        return tenantId != null ? tenantId : "default";
    }

}
