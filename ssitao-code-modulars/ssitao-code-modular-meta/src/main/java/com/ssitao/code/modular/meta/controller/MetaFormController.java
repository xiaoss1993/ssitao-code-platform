package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaFormDTO;
import com.ssitao.code.modular.meta.api.dto.MetaFormFieldDTO;
import com.ssitao.code.modular.meta.application.command.MetaFormCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaFormUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaFormAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据表单配置管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据表单配置管理", description = "元数据表单配置相关接口")
@RestController
@RequestMapping("/meta/form")
@Validated
public class MetaFormController {

    @Resource
    private MetaFormAppService metaFormAppService;

    @PostMapping("/create")
    @Operation(summary = "创建表单配置", description = "创建一个新的元数据表单配置")
    public CommonResult<String> create(@Valid @RequestBody MetaFormCreateCommand command,
                                        @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String formId = metaFormAppService.create(command, tenantId);
        return success(formId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新表单配置", description = "更新元数据表单配置信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaFormUpdateCommand command,
                                      @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaFormAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除表单配置", description = "删除指定表单配置")
    public CommonResult<Void> delete(@RequestParam String formId,
                                      @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaFormAppService.delete(formId, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取表单配置详情", description = "根据ID获取表单配置详细信息")
    public CommonResult<MetaFormDTO> getById(@RequestParam String formId,
                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaFormDTO form = metaFormAppService.getById(formId, tenantId);
        return success(form);
    }

    @GetMapping("/list")
    @Operation(summary = "获取表单配置列表", description = "获取指定实体的所有表单配置列表")
    public CommonResult<List<MetaFormDTO>> listByEntityId(@RequestParam String entityId,
                                                          @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaFormDTO> forms = metaFormAppService.listByEntityId(entityId, tenantId);
        return success(forms);
    }

    @GetMapping("/list-by-entity/{entityId}")
    @Operation(summary = "根据实体ID获取表单列表", description = "根据实体ID获取表单配置列表")
    public CommonResult<List<MetaFormDTO>> listByEntityIdPath(@PathVariable String entityId,
                                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaFormDTO> forms = metaFormAppService.listByEntityId(entityId, tenantId);
        return success(forms);
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取表单配置", description = "分页获取表单配置列表")
    public CommonResult<List<MetaFormDTO>> page(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaFormDTO> forms = metaFormAppService.page(keyword, page, limit, sort, order, tenantId);
        return success(forms);
    }

    // ==================== 表单字段操作 ====================

    @PostMapping("/field/create")
    @Operation(summary = "创建表单字段", description = "创建表单字段配置")
    public CommonResult<String> createField(@RequestParam String formId,
                                             @Valid @RequestBody MetaFormFieldDTO command,
                                             @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String fieldId = metaFormAppService.createField(formId, command, tenantId);
        return success(fieldId);
    }

    @PutMapping("/field/update")
    @Operation(summary = "更新表单字段", description = "更新表单字段配置")
    public CommonResult<Void> updateField(@Valid @RequestBody MetaFormFieldDTO command,
                                           @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaFormAppService.updateField(command, tenantId);
        return success();
    }

    @DeleteMapping("/field/delete")
    @Operation(summary = "删除表单字段", description = "删除指定表单字段")
    public CommonResult<Void> deleteField(@RequestParam String fieldId,
                                           @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaFormAppService.deleteField(fieldId, tenantId);
        return success();
    }

    @GetMapping("/field/get")
    @Operation(summary = "获取表单字段详情", description = "根据ID获取表单字段详细信息")
    public CommonResult<MetaFormFieldDTO> getFieldById(@RequestParam String fieldId,
                                                        @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaFormFieldDTO field = metaFormAppService.getFieldById(fieldId, tenantId);
        return success(field);
    }

    @GetMapping("/field/list")
    @Operation(summary = "获取表单字段列表", description = "获取指定表单的所有字段列表")
    public CommonResult<List<MetaFormFieldDTO>> listFieldsByFormId(@RequestParam String formId,
                                                                  @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaFormFieldDTO> fields = metaFormAppService.listFieldsByFormId(formId, tenantId);
        return success(fields);
    }
}
