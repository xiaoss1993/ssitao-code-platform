package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.result.Result;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionListReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PermissionDO;
import com.ssitao.code.modular.iam.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限管理控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "权限管理", description = "权限相关接口")
@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 创建权限
     */
    @Operation(summary = "创建权限", description = "创建新的权限")
    @PostMapping("/create")
    public Result<Long> createPermission(@Valid @RequestBody PermissionCreateReqVO createReqVO) {
        Long permissionId = permissionService.createPermission(createReqVO);
        return Result.success(permissionId);
    }

    /**
     * 更新权限
     */
    @Operation(summary = "更新权限", description = "更新权限信息")
    @PostMapping("/update")
    public Result<Void> updatePermission(@Valid @RequestBody PermissionUpdateReqVO updateReqVO) {
        permissionService.updatePermission(updateReqVO);
        return Result.success();
    }

    /**
     * 删除权限
     */
    @Operation(summary = "删除权限", description = "删除指定权限")
    @DeleteMapping("/delete/{id}")
    public Result<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return Result.success();
    }

    /**
     * 获取权限详情
     */
    @Operation(summary = "获取权限详情", description = "获取权限详细信息")
    @GetMapping("/get/{id}")
    public Result<PermissionDO> getPermission(@PathVariable Long id) {
        PermissionDO permission = permissionService.getPermission(id);
        return Result.success(permission);
    }

    /**
     * 获取权限列表
     */
    @Operation(summary = "获取权限列表", description = "查询权限列表")
    @PostMapping("/list")
    public Result<List<PermissionDO>> getPermissionList(@Valid @RequestBody PermissionListReqVO reqVO) {
        List<PermissionDO> permissions = permissionService.getPermissionList(reqVO);
        return Result.success(permissions);
    }

    /**
     * 获取权限树
     */
    @Operation(summary = "获取权限树", description = "查询权限树结构")
    @PostMapping("/tree")
    public Result<List<PermissionDO>> getPermissionTree(@Valid @RequestBody PermissionListReqVO reqVO) {
        List<PermissionDO> tree = permissionService.getPermissionTree(reqVO);
        return Result.success(tree);
    }

}
