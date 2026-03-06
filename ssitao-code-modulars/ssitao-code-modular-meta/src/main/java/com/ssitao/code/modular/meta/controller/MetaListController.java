package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaListDTO;
import com.ssitao.code.modular.meta.application.command.MetaListCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaListAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据列表配置管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据列表配置管理", description = "元数据列表配置相关接口")
@RestController
@RequestMapping("/meta/list")
@Validated
public class MetaListController {

    @Resource
    private MetaListAppService metaListAppService;

    @PostMapping("/create")
    @Operation(summary = "创建列表配置", description = "创建一个新的元数据列表配置")
    public CommonResult<String> create(@Valid @RequestBody MetaListCreateCommand command,
                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        String listId = metaListAppService.create(command, tenantId);
        return success(listId);
    }

    @PutMapping("/update")
    @Operation(summary = "更新列表配置", description = "更新元数据列表配置信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaListUpdateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaListAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除列表配置", description = "删除指定列表配置")
    public CommonResult<Void> delete(@RequestParam String listId,
                                      @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaListAppService.delete(listId, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取列表配置详情", description = "根据ID获取列表配置详细信息")
    public CommonResult<MetaListDTO> getById(@RequestParam String listId,
                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        MetaListDTO list = metaListAppService.getById(listId, tenantId);
        return success(list);
    }

    @GetMapping("/list")
    @Operation(summary = "获取列表配置列表", description = "获取指定实体的所有列表配置列表")
    public CommonResult<List<MetaListDTO>> listByEntityId(@RequestParam String entityId,
                                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaListDTO> lists = metaListAppService.listByEntityId(entityId, tenantId);
        return success(lists);
    }

    @GetMapping("/list-by-entity/{entityId}")
    @Operation(summary = "根据实体ID获取列表", description = "根据实体ID获取列表配置列表")
    public CommonResult<List<MetaListDTO>> listByEntityIdPath(@PathVariable String entityId,
                                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaListDTO> lists = metaListAppService.listByEntityId(entityId, tenantId);
        return success(lists);
    }
}
