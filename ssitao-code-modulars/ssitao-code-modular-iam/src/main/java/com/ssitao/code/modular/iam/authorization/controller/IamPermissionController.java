package com.ssitao.code.modular.iam.authorization.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.authorization.api.dto.IamPermissionDTO;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamPermissionUpdateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.service.IamPermissionAppService;
import com.ssitao.code.modular.iam.authorization.application.service.IamRoleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM权限管理控制器
 * 管理权限的CRUD操作和分配功能
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM权限管理", description = "IAM权限管理接口")
@Controller
@RequestMapping("/iam/permission")
@Validated
public class IamPermissionController {

    @Resource
    private IamPermissionAppService permissionAppService;

    @Resource
    private IamRoleAppService roleAppService;

    // ==================== 页面跳转 ====================

    /**
     * 权限管理页面
     */
    @GetMapping
    @Operation(summary = "权限管理页面")
    public String permissionPage(Model model) {
        addCommonModel(model, "权限管理", "permission");
        return "iam/permission";
    }

    /**
     * 权限添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "权限添加页面")
    public String permissionAddPage(Model model) {
        addCommonModel(model, "添加权限", "permission");
        return "iam/permission-edit";
    }

    /**
     * 权限编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "权限编辑页面")
    public String permissionEditPage(Model model) {
        addCommonModel(model, "编辑权限", "permission");
        return "iam/permission-edit";
    }

    // ==================== 权限CRUD接口 ====================

    @PostMapping("/create")
    @Operation(summary = "创建权限", description = "创建一个新的权限")
    @ResponseBody
    public CommonResult<Long> createPermission(@Valid @RequestBody IamPermissionCreateCommand command) {
        Long permissionId = permissionAppService.createPermission(command);
        return success(permissionId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新权限", description = "更新权限信息")
    @ResponseBody
    public CommonResult<Void> updatePermission(@Valid @RequestBody IamPermissionUpdateCommand command) {
        permissionAppService.updatePermission(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除权限", description = "删除指定权限")
    @ResponseBody
    public CommonResult<Void> deletePermission(@PathVariable String id,
                                                @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        permissionAppService.deletePermission(Long.parseLong(id), tenantId);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取权限详情", description = "根据ID获取权限详细信息")
    @ResponseBody
    public CommonResult<IamPermissionDTO> getPermissionById(@PathVariable String id,
                                                               @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamPermissionDTO permission = permissionAppService.getPermissionById(Long.parseLong(id), tenantId);
        return success(permission);
    }

    @GetMapping("/list")
    @Operation(summary = "获取权限列表", description = "获取所有权限列表")
    @ResponseBody
    public CommonResult<List<IamPermissionDTO>> listPermissions(@RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamPermissionDTO> permissions = permissionAppService.listPermissions(tenantId);
        return success(permissions);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取权限树", description = "获取权限树形结构")
    @ResponseBody
    public CommonResult<List<IamPermissionDTO>> getPermissionTree(@RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamPermissionDTO> tree = permissionAppService.getPermissionTree(tenantId);
        return success(tree);
    }

    // ==================== 权限分配接口 ====================

    @PostMapping("/assign")
    @Operation(summary = "分配权限给角色", description = "为角色分配权限")
    @ResponseBody
    public CommonResult<Void> assignPermission(@Valid @RequestBody IamRoleAssignPermissionCommand command) {
        roleAppService.assignPermissions(command);
        return success();
    }

    // ==================== 权限状态管理接口 ====================

    @PostMapping("/enable")
    @Operation(summary = "启用权限", description = "启用指定权限")
    @ResponseBody
    public CommonResult<Void> enablePermission(@RequestParam String id,
                                                @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        permissionAppService.enablePermission(Long.parseLong(id), tenantId);
        return success();
    }

    @PostMapping("/disable")
    @Operation(summary = "禁用权限", description = "禁用指定权限")
    @ResponseBody
    public CommonResult<Void> disablePermission(@RequestParam String id,
                                                  @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        permissionAppService.disablePermission(Long.parseLong(id), tenantId);
        return success();
    }

    // ==================== 通用方法 ====================

    /**
     * 添加通用模板变量
     */
    private void addCommonModel(Model model, String title, String controllerName) {
        model.addAttribute("title", title);
        model.addAttribute("controllerName", controllerName);
        model.addAttribute("moduleName", "iam");

        if (StpUtil.isLogin()) {
            model.addAttribute("isLogin", true);
            model.addAttribute("userId", StpUtil.getLoginId());
            model.addAttribute("userName", StpUtil.getLoginIdAsString());
        } else {
            model.addAttribute("isLogin", false);
        }
    }

}
