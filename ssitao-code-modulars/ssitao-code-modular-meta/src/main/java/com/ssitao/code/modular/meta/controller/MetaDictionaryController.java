package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.domain.model.MetaDictionary;
import com.ssitao.code.modular.meta.application.service.MetaDictionaryAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 字典类型管理控制器
 *
 * @author ssitao-code
 */
@RestController
@RequestMapping("/meta/dictionary")
@Tag(name = "字典类型管理", description = "字典类型的增删改查管理")
public class MetaDictionaryController {

    @Resource
    private MetaDictionaryAppService dictionaryAppService;

    /**
     * 获取字典类型列表（分页）
     */
    @GetMapping("/list")
    @Operation(summary = "获取字典类型列表", description = "获取字典类型列表，支持分页")
    public CommonResult<PageResult<MetaDictionary>> listDictionaries(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        PageResult<MetaDictionary> result = dictionaryAppService.listDictionariesPage(page, size, tenantId, sort, order);
        return success(result);
    }

    /**
     * 获取所有字典类型
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有字典类型", description = "获取所有字典类型")
    public CommonResult<List<MetaDictionary>> allDictionaries(
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaDictionary> list = dictionaryAppService.listDictionaries(tenantId);
        return success(list);
    }

    /**
     * 获取字典类型树
     */
    @GetMapping("/tree")
    @Operation(summary = "获取字典类型树", description = "获取字典类型树形结构")
    public CommonResult<List<MetaDictionary>> treeDictionaries(
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaDictionary> list = dictionaryAppService.listDictionaries(tenantId);
        return success(list);
    }

    /**
     * 根据ID获取字典类型
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取字典类型", description = "根据ID获取字典类型")
    public CommonResult<MetaDictionary> getDictionary(@PathVariable String id) {
        MetaDictionary dictionary = dictionaryAppService.getDictionaryById(id);
        return success(dictionary);
    }

    /**
     * 根据字典类型编码获取字典类型
     */
    @GetMapping("/code/{dictTypeCode}")
    @Operation(summary = "根据编码获取字典类型", description = "根据字典类型编码获取字典类型")
    public CommonResult<MetaDictionary> getDictionaryByCode(
            @PathVariable String dictTypeCode,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaDictionary dictionary = dictionaryAppService.getDictionaryByCode(dictTypeCode, tenantId);
        return success(dictionary);
    }

    /**
     * 创建字典类型
     */
    @PostMapping
    @Operation(summary = "创建字典类型", description = "创建新的字典类型")
    public CommonResult<String> createDictionary(MetaDictionary dictionary,
                                                  @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        dictionary.setTenantId(tenantId);
        String id = dictionaryAppService.createDictionary(dictionary);
        return success(id);
    }

    /**
     * 更新字典类型
     */
    @PutMapping
    @Operation(summary = "更新字典类型", description = "更新字典类型")
    public CommonResult<Void> updateDictionary(MetaDictionary dictionary) {
        dictionaryAppService.updateDictionary(dictionary);
        return success();
    }

    /**
     * 删除字典类型
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除字典类型", description = "删除字典类型")
    public CommonResult<Void> deleteDictionary(@PathVariable String id) {
        dictionaryAppService.deleteDictionary(id);
        return success();
    }

    /**
     * 批量删除字典类型
     */
    @DeleteMapping("/batch")
    @Operation(summary = "批量删除字典类型", description = "批量删除字典类型")
    public CommonResult<Void> batchDeleteDictionaries(@RequestBody List<String> ids) {
        dictionaryAppService.batchDeleteDictionaries(ids);
        return success();
    }
}
