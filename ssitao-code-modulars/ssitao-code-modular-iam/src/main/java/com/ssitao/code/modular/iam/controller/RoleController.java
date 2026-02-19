package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.result.Result;
import com.ssitao.code.modular.iam.controller.vo.role.RoleCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleListReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.RoleDO;
import com.ssitao.code.modular.iam.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色管理控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "角色管理", description = "角色相关接口")
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    /**
     * 创建角色
     */
    @Operation(summary = "创建角色", description = "创建新的角色")
    @PostMapping("/create")
    public Result<Long> createRole(@Valid @RequestBody RoleCreateReqVO createReqVO) {
        Long roleId = roleService.createRole(createReqVO);
        return Result.success(roleId);
    }

    /**
     * 更新角色
     */
    @Operation(summary = "更新角色", description = "更新角色信息")
    @PostMapping("/update")
    public Result<Void> updateRole(@Valid @RequestBody RoleUpdateReqVO updateReqVO) {
        roleService.updateRole(updateReqVO);
        return Result.success();
    }

    /**
     * 删除角色
     */
    @Operation(summary = "删除角色", description = "删除指定角色")
    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return Result.success();
    }

    /**
     * 获取角色详情
     */
    @Operation(summary = "获取角色详情", description = "获取角色详细信息")
    @GetMapping("/get/{id}")
    public Result<RoleDO> getRole(@PathVariable Long id) {
        RoleDO role = roleService.getRole(id);
        return Result.success(role);
    }

    /**
     * 获取角色列表
     */
    @Operation(summary = "获取角色列表", description = "查询角色列表")
    @PostMapping("/list")
    public Result<List<RoleDO>> getRoleList(@Valid @RequestBody RoleListReqVO reqVO) {
        List<RoleDO> roles = roleService.getRoleList(reqVO);
        return Result.success(roles);
    }

    /**
     * 分配权限给角色
     */
    @Operation(summary = "分配权限", description = "为角色分配权限")
    @PostMapping("/assign-permissions")
    public Result<Void> assignPermissions(
            @RequestParam Long roleId,
            @RequestParam List<Long> permissionIds
    ) {
        roleService.assignPermissions(roleId, permissionIds);
        return Result.success();
    }

    /**
     * 获取角色的权限列表
     */
    @Operation(summary = "获取角色权限", description = "获取角色拥有的权限列表")
    @GetMapping("/permissions/{id}")
    public Result<List<Long>> getRolePermissions(@PathVariable Long id) {
        List<Long> permissions = roleService.getRolePermissions(id);
        return Result.success(permissions);
    }

}
