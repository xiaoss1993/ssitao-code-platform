package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaTableDTO;
import com.ssitao.code.modular.meta.application.command.MetaTableCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaTableUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaTableAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据表管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据表管理", description = "元数据表相关接口")
@RestController
@RequestMapping("/meta/table")
@Validated
public class MetaTableController {

    @Resource
    private MetaTableAppService metaTableAppService;

    @PostMapping("/create")
    @Operation(summary = "创建元数据表", description = "创建一个新的元数据表")
    public CommonResult<String> create(@Valid @RequestBody MetaTableCreateCommand command,
                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String id = metaTableAppService.create(command, tenantId);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新元数据表", description = "更新元数据表信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaTableUpdateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaTableAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除元数据表", description = "删除指定元数据表")
    public CommonResult<Void> delete(@RequestParam String id,
                                     @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaTableAppService.delete(id, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取元数据表详情", description = "根据ID获取元数据表详细信息")
    public CommonResult<MetaTableDTO> getById(@RequestParam String id,
                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaTableDTO table = metaTableAppService.getById(id, tenantId);
        return success(table);
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取元数据表详情", description = "根据ID获取元数据表详细信息(REST风格)")
    public CommonResult<MetaTableDTO> getByIdPath(@PathVariable String id,
                                                   @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        MetaTableDTO table = metaTableAppService.getById(id, tenantId);
        return success(table);
    }

    @GetMapping("/list")
    @Operation(summary = "获取元数据表列表", description = "获取所有元数据表列表")
    public CommonResult<List<MetaTableDTO>> list(@RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaTableDTO> tables = metaTableAppService.list(tenantId);
        return success(tables);
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取元数据表", description = "分页获取元数据表列表")
    public CommonResult<List<MetaTableDTO>> page(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<MetaTableDTO> tables = metaTableAppService.page(keyword, page, limit, sort, order, tenantId);
        return success(tables);
    }

    @PostMapping("/generate/{id}")
    @Operation(summary = "生成代码", description = "生成代码")
    public CommonResult<Void> generate(@PathVariable String id,
                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaTableAppService.generate(id, tenantId);
        return success();
    }
}
