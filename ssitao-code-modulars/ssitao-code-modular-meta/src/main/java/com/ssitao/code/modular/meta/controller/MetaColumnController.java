package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaColumnAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据字段管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据字段管理", description = "元数据字段相关接口")
@RestController
@RequestMapping("/meta/column")
@Validated
public class MetaColumnController {

    @Resource
    private MetaColumnAppService metaColumnAppService;

    @PostMapping("/create")
    @Operation(summary = "创建字段", description = "创建一个新的元数据字段")
    public CommonResult<String> create(@Valid @RequestBody MetaColumnCreateCommand command,
                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String columnId = metaColumnAppService.create(command, tenantId);
        return success(columnId);
    }

    @PostMapping("/batch-create")
    @Operation(summary = "批量创建字段", description = "批量创建元数据字段")
    public CommonResult<List<String>> batchCreate(@Valid @RequestBody MetaColumnBatchCreateCommand command,
                                                   @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<String> columnIds = metaColumnAppService.batchCreate(command, tenantId);
        return success(columnIds);
    }

    @PutMapping("/update")
    @Operation(summary = "更新字段", description = "更新元数据字段信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaColumnUpdateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaColumnAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字段", description = "删除指定字段")
    public CommonResult<Void> delete(@RequestParam String columnId,
                                      @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaColumnAppService.delete(columnId, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取字段详情", description = "根据ID获取字段详细信息")
    public CommonResult<MetaColumnDTO> getById(@RequestParam String columnId,
                                                @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaColumnDTO column = metaColumnAppService.getById(columnId, tenantId);
        return success(column);
    }

    @GetMapping("/list")
    @Operation(summary = "获取字段列表", description = "获取指定表的所有字段列表")
    public CommonResult<List<MetaColumnDTO>> listByTableId(@RequestParam String tableId,
                                                            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaColumnDTO> columns = metaColumnAppService.listByTableId(tableId, tenantId);
        return success(columns);
    }

    @GetMapping("/list-by-table/{tableId}")
    @Operation(summary = "根据表ID获取字段列表", description = "根据表ID获取字段列表")
    public CommonResult<List<MetaColumnDTO>> listByTableIdPath(@PathVariable String tableId,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaColumnDTO> columns = metaColumnAppService.listByTableId(tableId, tenantId);
        return success(columns);
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取字段", description = "分页获取字段列表")
    public CommonResult<List<MetaColumnDTO>> page(
            @RequestParam(required = false) String tableId,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaColumnDTO> columns = metaColumnAppService.page(tableId, keyword, page, limit, sort, order, tenantId);
        return success(columns);
    }
}
