package com.ssitao.code.modular.meta.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaFormDTO;
import com.ssitao.code.modular.meta.api.dto.MetaFormFieldDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListColumnDTO;
import com.ssitao.code.modular.meta.application.service.MetaFormAppService;
import com.ssitao.code.modular.meta.application.service.MetaListAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 动态渲染API控制器
 * 提供前端动态渲染所需的配置数据
 *
 * @author ssitao-code
 */
@Slf4j
@RestController
@RequestMapping("/meta/render")
@Tag(name = "动态渲染API", description = "提供前端动态渲染所需的配置数据")
@RequiredArgsConstructor
public class DynamicRenderController {

    private final MetaFormAppService metaFormAppService;
    private final MetaListAppService metaListAppService;

    // ==================== 表单渲染API ====================

    @GetMapping("/form/{entityId}")
    @Operation(summary = "获取表单配置", description = "根据实体ID获取默认表单配置，用于前端动态渲染表单")
    public CommonResult<Map<String, Object>> getFormConfig(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @Parameter(description = "表单类型") @RequestParam(required = false, defaultValue = "1") Integer formType,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {

        // 获取指定类型的表单
        List<MetaFormDTO> forms = metaFormAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isEmpty(forms)) {
            throw new BusinessException("实体[" + entityId + "]没有配置表单");
        }

        // 查找匹配类型的表单，如果没有则使用第一个
        MetaFormDTO form = forms.stream()
                .filter(f -> formType.equals(f.getFormType()))
                .findFirst()
                .orElse(forms.get(0));

        // 获取表单字段
        List<MetaFormFieldDTO> fields = metaFormAppService.listFieldsByFormId(form.getFormId(), tenantId);

        // 构建前端需要的配置
        Map<String, Object> config = new HashMap<>();
        config.put("formId", form.getFormId());
        config.put("formName", form.getFormName());
        config.put("formType", form.getFormType());
        config.put("layout", form.getLayout());
        config.put("fields", fields.stream().map(this::convertToFieldConfig).collect(Collectors.toList()));

        return success(config);
    }

    @GetMapping("/form/all/{entityId}")
    @Operation(summary = "获取所有表单配置", description = "根据实体ID获取所有表单配置列表")
    public CommonResult<List<Map<String, Object>>> getAllFormConfigs(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {

        List<MetaFormDTO> forms = metaFormAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isEmpty(forms)) {
            return success(java.util.Collections.emptyList());
        }

        List<Map<String, Object>> result = forms.stream()
                .map(form -> {
                    Map<String, Object> config = new HashMap<>();
                    config.put("formId", form.getFormId());
                    config.put("formName", form.getFormName());
                    config.put("formType", form.getFormType());
                    config.put("layout", form.getLayout());
                    return config;
                })
                .collect(Collectors.toList());

        return success(result);
    }

    // ==================== 列表渲染API ====================

    @GetMapping("/list/{entityId}")
    @Operation(summary = "获取列表配置", description = "根据实体ID获取默认列表配置，用于前端动态渲染表格")
    public CommonResult<Map<String, Object>> getListConfig(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @Parameter(description = "列表类型") @RequestParam(required = false, defaultValue = "1") Integer listType,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {

        // 获取指定类型的列表
        List<MetaListDTO> lists = metaListAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isEmpty(lists)) {
            throw new BusinessException("实体[" + entityId + "]没有配置列表");
        }

        // 查找匹配类型的列表，如果没有则使用第一个
        MetaListDTO list = lists.stream()
                .filter(l -> listType.equals(l.getListType()))
                .findFirst()
                .orElse(lists.get(0));

        // 获取列表列
        List<MetaListColumnDTO> columns = metaListAppService.listColumnsByListId(list.getListId(), tenantId);

        // 构建前端需要的配置
        Map<String, Object> config = new HashMap<>();
        config.put("listId", list.getListId());
        config.put("listName", list.getListName());
        config.put("listType", list.getListType());
        config.put("showRowNumber", list.getShowPagination());
        config.put("showCheckbox", list.getShowToolbar());
        config.put("columns", columns.stream().map(this::convertToColumnConfig).collect(Collectors.toList()));

        return success(config);
    }

    @GetMapping("/list/all/{entityId}")
    @Operation(summary = "获取所有列表配置", description = "根据实体ID获取所有列表配置列表")
    public CommonResult<List<Map<String, Object>>> getAllListConfigs(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {

        List<MetaListDTO> lists = metaListAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isEmpty(lists)) {
            return success(java.util.Collections.emptyList());
        }

        List<Map<String, Object>> result = lists.stream()
                .map(list -> {
                    Map<String, Object> config = new HashMap<>();
                    config.put("listId", list.getListId());
                    config.put("listName", list.getListName());
                    config.put("listType", list.getListType());
                    return config;
                })
                .collect(Collectors.toList());

        return success(result);
    }

    // ==================== 实体配置综合API ====================

    @GetMapping("/entity/{entityId}")
    @Operation(summary = "获取实体完整配置", description = "获取实体的表单和列表配置，用于配置页面")
    public CommonResult<Map<String, Object>> getEntityConfig(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {

        Map<String, Object> config = new HashMap<>();

        // 获取表单配置
        List<MetaFormDTO> forms = metaFormAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isNotEmpty(forms)) {
            List<Map<String, Object>> formConfigs = forms.stream()
                    .map(form -> {
                        Map<String, Object> formConfig = new HashMap<>();
                        formConfig.put("formId", form.getFormId());
                        formConfig.put("formName", form.getFormName());
                        formConfig.put("formType", form.getFormType());
                        formConfig.put("layout", form.getLayout());
                        return formConfig;
                    })
                    .collect(Collectors.toList());
            config.put("forms", formConfigs);
        } else {
            config.put("forms", java.util.Collections.emptyList());
        }

        // 获取列表配置
        List<MetaListDTO> lists = metaListAppService.listByEntityId(entityId, tenantId);
        if (CollUtil.isNotEmpty(lists)) {
            List<Map<String, Object>> listConfigs = lists.stream()
                    .map(list -> {
                        Map<String, Object> listConfig = new HashMap<>();
                        listConfig.put("listId", list.getListId());
                        listConfig.put("listName", list.getListName());
                        listConfig.put("listType", list.getListType());
                        return listConfig;
                    })
                    .collect(Collectors.toList());
            config.put("lists", listConfigs);
        } else {
            config.put("lists", java.util.Collections.emptyList());
        }

        return success(config);
    }

    // ==================== 转换方法 ====================

    /**
     * 将表单字段转换为前端配置
     */
    private Map<String, Object> convertToFieldConfig(MetaFormFieldDTO field) {
        Map<String, Object> config = new HashMap<>();
        config.put("fieldCode", field.getFieldCode());
        config.put("fieldName", field.getFieldName());
        config.put("fieldType", field.getFieldType());
        // 转换controlType为component
        config.put("component", convertControlType(field.getControlType()));
        config.put("required", field.getRequired());
        config.put("defaultValue", field.getDefaultValue());
        config.put("placeholder", field.getPlaceholder());
        config.put("disabled", field.getEditable() == 0 ? 1 : 0);
        config.put("readonly", field.getEditable() == 0 ? 1 : 0);
        config.put("sort", field.getSortOrder());

        // 解析验证规则
        if (StrUtil.isNotBlank(field.getRules())) {
            config.put("validationRule", field.getRules());
        }

        // 解析字典数据
        if (StrUtil.isNotBlank(field.getDictTypeCode())) {
            config.put("dictCode", field.getDictTypeCode());
        }

        // 解析远程数据配置
        if (StrUtil.isNotBlank(field.getRemoteUrl())) {
            Map<String, Object> remoteConfig = new HashMap<>();
            remoteConfig.put("url", field.getRemoteUrl());
            remoteConfig.put("labelField", field.getLabelField());
            remoteConfig.put("valueField", field.getValueField());
            config.put("remoteConfig", remoteConfig);
        }

        return config;
    }

    /**
     * 转换controlType为前端component
     */
    private String convertControlType(Integer controlType) {
        if (controlType == null) {
            return "Input";
        }
        switch (controlType) {
            case 1: return "Input";
            case 2: return "Textarea";
            case 3: return "InputNumber";
            case 4: return "Select";
            case 5: return "Radio";
            case 6: return "Checkbox";
            case 7: return "Switch";
            case 8: return "Date";
            case 9: return "DateTime";
            default: return "Input";
        }
    }

    /**
     * 将列表列转换为前端配置
     */
    private Map<String, Object> convertToColumnConfig(MetaListColumnDTO column) {
        Map<String, Object> config = new HashMap<>();
        config.put("fieldCode", column.getFieldCode());
        config.put("fieldName", column.getFieldName());
        config.put("width", column.getWidth());
        config.put("align", convertAlign(column.getAlign()));
        config.put("fixed", column.getFixedLeft() != null ? "left" : (column.getFixedRight() != null ? "right" : null));
        config.put("sortable", column.getSortable() != null && column.getSortable() == 1);
        config.put("show", column.getShowInList() != null && column.getShowInList() == 1);
        config.put("formatter", column.getDictTypeCode());
        config.put("sort", column.getSortOrder());

        return config;
    }

    /**
     * 转换对齐方式
     */
    private String convertAlign(Integer align) {
        if (align == null) {
            return "left";
        }
        switch (align) {
            case 1: return "left";
            case 2: return "center";
            case 3: return "right";
            default: return "left";
        }
    }
}
