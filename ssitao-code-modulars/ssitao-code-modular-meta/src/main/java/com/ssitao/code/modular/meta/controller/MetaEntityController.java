package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaEntityUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaEntityAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据实体管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据实体管理", description = "元数据实体相关接口")
@RestController
@RequestMapping("/meta/entity")
@Validated
public class MetaEntityController {

    @Resource
    private MetaEntityAppService metaEntityAppService;

    @PostMapping("/create")
    @Operation(summary = "创建元数据实体", description = "创建一个新的元数据实体")
    public CommonResult<String> create(@Valid @RequestBody MetaEntityCreateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        String id = metaEntityAppService.create(command, tenantId);
        return success(id);
    }

    @PutMapping("/update")
    @Operation(summary = "更新元数据实体", description = "更新元数据实体信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaEntityUpdateCommand command,
                                    @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaEntityAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除元数据实体", description = "删除指定元数据实体")
    public CommonResult<Void> delete(@RequestParam String id,
                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaEntityAppService.delete(id, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取元数据实体详情", description = "根据ID获取元数据实体详细信息")
    public CommonResult<MetaEntityDTO> getById(@RequestParam String id,
                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        MetaEntityDTO entity = metaEntityAppService.getById(id, tenantId);
        return success(entity);
    }

    @GetMapping("/list")
    @Operation(summary = "获取元数据实体列表", description = "获取所有元数据实体列表")
    public CommonResult<List<MetaEntityDTO>> list(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaEntityDTO> entities = metaEntityAppService.list(tenantId);
        return success(entities);
    }
}
