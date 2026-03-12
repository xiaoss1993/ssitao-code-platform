package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;
import com.ssitao.code.modular.meta.application.service.MetaDictionaryItemAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 字典数据管理控制器
 *
 * @author ssitao-code
 */
@RestController
@RequestMapping("/meta/dictionary-item")
@Tag(name = "字典数据管理", description = "字典数据的增删改查管理")
public class MetaDictionaryItemController {

    @Resource
    private MetaDictionaryItemAppService dictionaryItemAppService;

    /**
     * 获取字典数据列表（分页）
     */
    @GetMapping("/list")
    @Operation(summary = "获取字典数据列表", description = "获取字典数据列表，支持分页和按字典类型筛选")
    public CommonResult<PageResult<MetaDictionaryItem>> listDictionaryItems(
            @RequestParam(required = false) String dictId,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        PageResult<MetaDictionaryItem> result = dictionaryItemAppService.listDictionaryItemsPage(page, size, dictId, tenantId, sort, order);
        return success(result);
    }

    /**
     * 根据字典类型ID获取字典数据列表
     */
    @GetMapping("/type/{dictTypeId}")
    @Operation(summary = "根据字典类型ID获取字典数据", description = "根据字典类型ID获取字典数据列表")
    public CommonResult<List<MetaDictionaryItem>> listByTypeId(
            @PathVariable String dictTypeId,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaDictionaryItem> list = dictionaryItemAppService.listDictionaryItemsByTypeId(dictTypeId, tenantId);
        return success(list);
    }

    /**
     * 根据字典类型编码获取字典数据列表
     */
    @GetMapping("/code/{dictTypeCode}")
    @Operation(summary = "根据字典类型编码获取字典数据", description = "根据字典类型编码获取字典数据列表")
    public CommonResult<List<MetaDictionaryItem>> listByTypeCode(
            @PathVariable String dictTypeCode,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaDictionaryItem> list = dictionaryItemAppService.listDictionaryItemsByTypeCode(dictTypeCode, tenantId);
        return success(list);
    }

    /**
     * 根据字典类型编码获取启用的字典数据列表
     */
    @GetMapping("/enabled/{dictTypeCode}")
    @Operation(summary = "获取启用的字典数据", description = "根据字典类型编码获取启用的字典数据列表")
    public CommonResult<List<MetaDictionaryItem>> listEnabledByTypeCode(
            @PathVariable String dictTypeCode,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaDictionaryItem> list = dictionaryItemAppService.listEnabledDictionaryItemsByTypeCode(dictTypeCode, tenantId);
        return success(list);
    }

    /**
     * 根据ID获取字典数据
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取字典数据", description = "根据ID获取字典数据")
    public CommonResult<MetaDictionaryItem> getDictionaryItem(@PathVariable String id) {
        MetaDictionaryItem item = dictionaryItemAppService.getDictionaryItemById(id);
        return success(item);
    }

    /**
     * 创建字典数据
     */
    @PostMapping
    @Operation(summary = "创建字典数据", description = "创建新的字典数据")
    public CommonResult<String> createDictionaryItem(MetaDictionaryItem item,
                                                    @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        item.setTenantId(tenantId);
        String id = dictionaryItemAppService.createDictionaryItem(item);
        return success(id);
    }

    /**
     * 更新字典数据
     */
    @PutMapping
    @Operation(summary = "更新字典数据", description = "更新字典数据")
    public CommonResult<Void> updateDictionaryItem(MetaDictionaryItem item) {
        dictionaryItemAppService.updateDictionaryItem(item);
        return success();
    }

    /**
     * 删除字典数据
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典数据", description = "删除字典数据")
    public CommonResult<Void> deleteDictionaryItem(@PathVariable String id) {
        dictionaryItemAppService.deleteDictionaryItem(id);
        return success();
    }

    /**
     * 批量删除字典数据
     */
    @DeleteMapping("/batch")
    @Operation(summary = "批量删除字典数据", description = "批量删除字典数据")
    public CommonResult<Void> batchDeleteDictionaryItems(@RequestBody List<String> ids) {
        dictionaryItemAppService.batchDeleteDictionaryItems(ids);
        return success();
    }

    /**
     * 根据字典类型ID删除字典数据
     */
    @DeleteMapping("/type/{dictTypeId}")
    @Operation(summary = "根据字典类型删除字典数据", description = "根据字典类型ID删除该类型下的所有字典数据")
    public CommonResult<Void> deleteByDictTypeId(@PathVariable String dictTypeId) {
        dictionaryItemAppService.deleteDictionaryItemsByTypeId(dictTypeId);
        return success();
    }
}
