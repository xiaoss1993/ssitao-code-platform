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
                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        String id = metaTableAppService.create(command, tenantId);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新元数据表", description = "更新元数据表信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaTableUpdateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaTableAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除元数据表", description = "删除指定元数据表")
    public CommonResult<Void> delete(@RequestParam String id,
                                      @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaTableAppService.delete(id, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取元数据表详情", description = "根据ID获取元数据表详细信息")
    public CommonResult<MetaTableDTO> getById(@RequestParam String id,
                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        MetaTableDTO table = metaTableAppService.getById(id, tenantId);
        return success(table);
    }

    @GetMapping("/list")
    @Operation(summary = "获取元数据表列表", description = "获取所有元数据表列表")
    public CommonResult<List<MetaTableDTO>> list(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaTableDTO> tables = metaTableAppService.list(tenantId);
        return success(tables);
    }

    @PostMapping("/generate/{id}")
    @Operation(summary = "生成代码", description = "生成代码")
    public CommonResult<Void> generate(@PathVariable String id,
                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        // TODO: 实现代码生成逻辑
        return success();
    }
}
