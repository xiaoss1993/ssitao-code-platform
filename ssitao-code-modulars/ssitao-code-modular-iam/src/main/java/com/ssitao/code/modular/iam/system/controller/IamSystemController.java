package com.ssitao.code.modular.iam.system.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageParam;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM系统管理控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM系统管理", description = "IAM租户相关接口")
@RestController
@RequestMapping("/iam/system")
public class IamSystemController {

    private final IamTenantAppService tenantAppService;

    public IamSystemController(IamTenantAppService tenantAppService) {
        this.tenantAppService = tenantAppService;
    }

    // ==================== 租户管理接口 ====================

    @PostMapping("/tenant")
    @Operation(summary = "创建租户", description = "创建新租户")
    public CommonResult<String> createTenant(@Valid @RequestBody IamTenantCreateCommand command) {
        String tenantId = tenantAppService.create(command);
        return success(tenantId);
    }

    @PutMapping("/tenant")
    @Operation(summary = "更新租户", description = "更新租户信息")
    public CommonResult<Void> updateTenant(@Valid @RequestBody IamTenantUpdateCommand command) {
        tenantAppService.update(command);
        return success();
    }

    @DeleteMapping("/tenant/{id}")
    @Operation(summary = "删除租户", description = "删除指定租户")
    public CommonResult<Void> deleteTenant(@PathVariable String id) {
        tenantAppService.delete(id);
        return success();
    }

    @GetMapping("/tenant/{id}")
    @Operation(summary = "获取租户详情", description = "根据ID获取租户详情")
    public CommonResult<IamTenantDTO> getTenant(@PathVariable String id) {
        IamTenantDTO tenant = tenantAppService.getById(id);
        return success(tenant);
    }

    @GetMapping("/tenant/code/{tenantCode}")
    @Operation(summary = "根据编码获取租户", description = "根据租户编码获取租户信息")
    public CommonResult<IamTenantDTO> getTenantByCode(@PathVariable String tenantCode) {
        IamTenantDTO tenant = tenantAppService.getByTenantCode(tenantCode);
        return success(tenant);
    }

    @GetMapping("/tenant/domain/{domain}")
    @Operation(summary = "根据域名获取租户", description = "根据域名获取租户信息")
    public CommonResult<IamTenantDTO> getTenantByDomain(@PathVariable String domain) {
        IamTenantDTO tenant = tenantAppService.getByDomain(domain);
        return success(tenant);
    }

    @GetMapping("/tenants")
    @Operation(summary = "获取租户列表", description = "获取所有租户列表")
    public CommonResult<List<IamTenantDTO>> listTenants() {
        List<IamTenantDTO> tenants = tenantAppService.listAll();
        return success(tenants);
    }

    @GetMapping("/tenants/page")
    @Operation(summary = "分页查询租户列表", description = "分页查询租户列表，支持按租户编码、租户名称、状态过滤")
    public CommonResult<PageResult<IamTenantDTO>> listTenantsPage(
            @Parameter(description = "分页参数") PageParam pageParam,
            @Parameter(description = "租户编码（模糊查询）") @RequestParam(required = false) String tenantCode,
            @Parameter(description = "租户名称（模糊查询）") @RequestParam(required = false) String tenantName,
            @Parameter(description = "租户状态：NORMAL-正常, DISABLED-禁用, EXPIRED-过期") @RequestParam(required = false) String tenantStatus) {
        PageResult<IamTenantDTO> pageResult = tenantAppService.listPage(pageParam, tenantCode, tenantName, tenantStatus);
        return success(pageResult);
    }

    @PutMapping("/tenant/{id}/enable")
    @Operation(summary = "启用租户", description = "启用指定租户")
    public CommonResult<Void> enableTenant(@PathVariable String id) {
        tenantAppService.enable(id);
        return success();
    }

    @PutMapping("/tenant/{id}/disable")
    @Operation(summary = "禁用租户", description = "禁用指定租户")
    public CommonResult<Void> disableTenant(@PathVariable String id) {
        tenantAppService.disable(id);
        return success();
    }

}
