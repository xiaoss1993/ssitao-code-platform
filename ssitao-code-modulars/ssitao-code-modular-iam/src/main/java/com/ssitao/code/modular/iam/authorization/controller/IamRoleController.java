package com.ssitao.code.modular.iam.authorization.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleAssignPermissionCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleCreateCommand;
import com.ssitao.code.modular.iam.authorization.application.command.IamRoleUpdateCommand;
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
 * IAM角色管理控制器
 * 管理 tb_iam_role 表，处理角色的CRUD操作
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM角色管理", description = "IAM角色管理接口")
@Controller
@RequestMapping("/iam/role")
@Validated
public class IamRoleController {

    @Resource
    private IamRoleAppService roleAppService;

    // ==================== 页面跳转 ====================

    /**
     * 角色管理页面
     */
    @GetMapping
    @Operation(summary = "角色管理页面")
    public String rolePage(Model model) {
        addCommonModel(model, "角色管理", "role");
        return "iam/role";
    }

    /**
     * 角色添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "角色添加页面")
    public String roleAddPage(Model model) {
        addCommonModel(model, "添加角色", "role");
        return "iam/role-edit";
    }

    /**
     * 角色编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "角色编辑页面")
    public String roleEditPage(Model model) {
        addCommonModel(model, "编辑角色", "role");
        return "iam/role-edit";
    }

    // ==================== 角色CRUD接口 ====================

    /**
     * 角色管理API基础路径
     */
    @GetMapping("/api")
    @Operation(summary = "角色API入口")
    public String apiIndex() {
        return "redirect:/iam/role/page";
    }

    @PostMapping("/create")
    @Operation(summary = "创建角色", description = "创建一个新的角色")
    @ResponseBody
    public CommonResult<Long> createRole(@Valid @RequestBody IamRoleCreateCommand command) {
        Long roleId = roleAppService.createRole(command);
        return success(roleId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新角色", description = "更新角色信息")
    @ResponseBody
    public CommonResult<Void> updateRole(@Valid @RequestBody IamRoleUpdateCommand command) {
        roleAppService.updateRole(command);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除角色", description = "删除指定角色")
    @ResponseBody
    public CommonResult<Void> deleteRole(@RequestParam String id,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.deleteRole(id, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取角色详情", description = "根据ID获取角色详细信息")
    @ResponseBody
    public CommonResult<IamRoleDTO> getRoleById(@RequestParam String id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamRoleDTO role = roleAppService.getRoleById(id, tenantId);
        return success(role);
    }

    @GetMapping("/list")
    @Operation(summary = "获取角色列表", description = "获取所有角色列表")
    @ResponseBody
    public CommonResult<List<IamRoleDTO>> listRoles(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> roles = roleAppService.listRoles(tenantId);
        return success(roles);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询角色", description = "分页查询角色列表")
    @ResponseBody
    public CommonResult<Page<IamRoleDTO>> pageRoles(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId,
                                                      @RequestParam(defaultValue = "1") int current,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "limit", defaultValue = "10") int limit) {
        // 兼容前端参数
        int pageNum = current > 0 ? current : (page > 0 ? page : 1);
        int pageSize = size > 0 ? size : (limit > 0 ? limit : 10);
        Page<IamRoleDTO> result = roleAppService.pageRoles(tenantId, pageNum, pageSize);
        return success(result);
    }

    @GetMapping("/tree")
    @Operation(summary = "获取角色树", description = "获取角色树形结构")
    @ResponseBody
    public CommonResult<List<IamRoleDTO>> getRoleTree(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> tree = roleAppService.getRoleTree(tenantId);
        return success(tree);
    }

    // ==================== 角色权限管理接口 ====================

    @PostMapping("/assign-permissions")
    @Operation(summary = "分配权限", description = "为角色分配权限")
    @ResponseBody
    public CommonResult<Void> assignPermissions(@Valid @RequestBody IamRoleAssignPermissionCommand command) {
        roleAppService.assignPermissions(command);
        return success();
    }

    // ==================== 角色状态管理接口 ====================

    @PostMapping("/enable")
    @Operation(summary = "启用角色", description = "启用指定角色")
    @ResponseBody
    public CommonResult<Void> enableRole(@RequestParam String id,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.enableRole(id, tenantId);
        return success();
    }

    @PostMapping("/disable")
    @Operation(summary = "禁用角色", description = "禁用指定角色")
    @ResponseBody
    public CommonResult<Void> disableRole(@RequestParam String id,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        roleAppService.disableRole(id, tenantId);
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


