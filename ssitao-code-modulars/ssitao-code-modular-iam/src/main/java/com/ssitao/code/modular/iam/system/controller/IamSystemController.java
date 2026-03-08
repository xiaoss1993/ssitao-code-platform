package com.ssitao.code.modular.iam.system.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageParam;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.system.api.dto.IamDictDataDTO;
import com.ssitao.code.modular.iam.system.api.dto.IamDictTypeDTO;
import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamDictDataCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictTypeCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictTypeUpdateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamDictAppService;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM系统管理控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM系统管理", description = "IAM租户、字典相关接口")
@RestController
@RequestMapping("/iam/system")
public class IamSystemController {

    private final IamTenantAppService tenantAppService;
    private final IamDictAppService dictAppService;

    public IamSystemController(IamTenantAppService tenantAppService,
                               IamDictAppService dictAppService) {
        this.tenantAppService = tenantAppService;
        this.dictAppService = dictAppService;
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

    // ==================== 字典类型管理接口 ====================

    @PostMapping("/dict-type")
    @Operation(summary = "创建字典类型", description = "创建新字典类型")
    public CommonResult<Long> createDictType(@Valid @RequestBody IamDictTypeCreateCommand command) {
        Long dictTypeId = dictAppService.createDictType(command);
        return success(dictTypeId);
    }

    @PutMapping("/dict-type")
    @Operation(summary = "更新字典类型", description = "更新字典类型信息")
    public CommonResult<Void> updateDictType(@Valid @RequestBody IamDictTypeUpdateCommand command) {
        dictAppService.updateDictType(command);
        return success();
    }

    @DeleteMapping("/dict-type/{id}")
    @Operation(summary = "删除字典类型", description = "删除指定字典类型")
    public CommonResult<Void> deleteDictType(@PathVariable Long id,
                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        dictAppService.deleteDictType(id, tenantId);
        return success();
    }

    @PostMapping("/dict-type/multi")
    @Operation(summary = "批量操作字典类型", description = "批量启用/禁用字典类型")
    public CommonResult<Void> multiDictType(@RequestParam String ids,
                                            @RequestParam String action,
                                            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        if ("enable".equals(action)) {
            dictAppService.enableDictType(ids, tenantId);
        } else if ("disable".equals(action)) {
            dictAppService.disableDictType(ids, tenantId);
        }
        return success();
    }

    @GetMapping("/dict-type/{id}")
    @Operation(summary = "获取字典类型详情", description = "根据ID获取字典类型详情")
    public CommonResult<IamDictTypeDTO> getDictType(@PathVariable Long id,
                                                     @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamDictTypeDTO dictType = dictAppService.getDictTypeById(id, tenantId);
        return success(dictType);
    }

    @GetMapping("/dict-types")
    @Operation(summary = "获取所有字典类型", description = "获取所有字典类型列表")
    public CommonResult<List<IamDictTypeDTO>> listDictTypes(@RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamDictTypeDTO> dictTypes = dictAppService.listDictTypes(tenantId);
        return success(dictTypes);
    }

    // ==================== 字典数据管理接口 ====================

    @PostMapping("/dict-data")
    @Operation(summary = "创建字典数据", description = "创建新字典数据")
    public CommonResult<Long> createDictData(@Valid @RequestBody IamDictDataCreateCommand command) {
        Long dictDataId = dictAppService.createDictData(command);
        return success(dictDataId);
    }

    @PostMapping("/dict-data/batch")
    @Operation(summary = "批量创建字典数据", description = "批量创建字典数据")
    public CommonResult<List<Long>> batchCreateDictData(@Valid @RequestBody List<IamDictDataCreateCommand> commands) {
        List<Long> dictDataIds = dictAppService.batchCreateDictData(commands);
        return success(dictDataIds);
    }

    @DeleteMapping("/dict-data/{id}")
    @Operation(summary = "删除字典数据", description = "删除指定字典数据")
    public CommonResult<Void> deleteDictData(@PathVariable Long id,
                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        dictAppService.deleteDictData(id, tenantId);
        return success();
    }

    @GetMapping("/dict-data/type/{dictTypeCode}")
    @Operation(summary = "根据类型编码获取字典数据", description = "根据字典类型编码获取字典数据列表")
    public CommonResult<List<IamDictDataDTO>> listDictDataByTypeCode(@PathVariable String dictTypeCode,
                                                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamDictDataDTO> dictDataList = dictAppService.listDictDataByTypeCode(dictTypeCode, tenantId);
        return success(dictDataList);
    }

    @GetMapping("/dict-data/all")
    @Operation(summary = "获取所有字典数据", description = "获取所有字典数据")
    public CommonResult<Map<String, List<IamDictDataDTO>>> getAllDictData(@RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        Map<String, List<IamDictDataDTO>> dictDataMap = dictAppService.getAllDictData(tenantId);
        return success(dictDataMap);
    }

}
