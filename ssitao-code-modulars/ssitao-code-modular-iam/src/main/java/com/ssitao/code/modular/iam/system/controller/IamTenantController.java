package com.ssitao.code.modular.iam.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM租户管理控制器
 * 负责租户的CRUD操作及相关管理功能
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "租户管理", description = "租户CRUD操作")
@Controller
@RequestMapping("/admin/tenant")
public class IamTenantController {

    private final IamTenantAppService tenantAppService;

    public IamTenantController(IamTenantAppService tenantAppService) {
        this.tenantAppService = tenantAppService;
    }

    // ==================== 页面跳转 ====================

    /**
     * 租户管理页面
     */
    @GetMapping
    @Operation(summary = "租户管理页面")
    public String tenantPage(Model model) {
        addCommonModel(model, "租户管理", "tenant");
        return "iam/tenant";
    }

    /**
     * 租户添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "租户添加页面")
    public String tenantAddPage(Model model) {
        addCommonModel(model, "添加租户", "tenant");
        return "iam/tenant-edit";
    }

    /**
     * 租户编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "租户编辑页面")
    public String tenantEditPage(Model model) {
        addCommonModel(model, "编辑租户", "tenant");
        return "iam/tenant-edit";
    }

    // ==================== 租户CRUD接口 ====================

    @PostMapping
    @Operation(summary = "创建租户", description = "创建新租户")
    @ResponseBody
    public CommonResult<String> create(@Valid @RequestBody IamTenantCreateCommand command) {
        String tenantId = tenantAppService.create(command);
        return success(tenantId);
    }

    @PutMapping
    @Operation(summary = "更新租户", description = "更新租户信息")
    @ResponseBody
    public CommonResult<Void> update(@Valid @RequestBody IamTenantUpdateCommand command) {
        tenantAppService.update(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户", description = "删除指定租户")
    @ResponseBody
    public CommonResult<Void> delete(@PathVariable String id) {
        tenantAppService.delete(id);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户详情", description = "根据ID获取租户详情")
    @ResponseBody
    public CommonResult<IamTenantDTO> getById(@PathVariable String id) {
        IamTenantDTO tenant = tenantAppService.getById(id);
        return success(tenant);
    }

    @GetMapping("/list")
    @Operation(summary = "获取租户列表", description = "获取所有租户列表")
    @ResponseBody
    public CommonResult<List<IamTenantDTO>> list() {
        List<IamTenantDTO> tenants = tenantAppService.listAll();
        return success(tenants);
    }

    @GetMapping("/code/{tenantCode}")
    @Operation(summary = "根据编码获取租户", description = "根据租户编码获取租户信息")
    @ResponseBody
    public CommonResult<IamTenantDTO> getByTenantCode(@PathVariable String tenantCode) {
        IamTenantDTO tenant = tenantAppService.getByTenantCode(tenantCode);
        return success(tenant);
    }

    @GetMapping("/domain/{domain}")
    @Operation(summary = "根据域名获取租户", description = "根据域名获取租户信息")
    @ResponseBody
    public CommonResult<IamTenantDTO> getByDomain(@PathVariable String domain) {
        IamTenantDTO tenant = tenantAppService.getByDomain(domain);
        return success(tenant);
    }

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用租户", description = "启用指定租户")
    @ResponseBody
    public CommonResult<Void> enable(@PathVariable String id) {
        tenantAppService.enable(id);
        return success();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用租户", description = "禁用指定租户")
    @ResponseBody
    public CommonResult<Void> disable(@PathVariable String id) {
        tenantAppService.disable(id);
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
