package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigListReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigRespVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.ConfigDO;
import com.ssitao.code.modular.iam.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 系统参数控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 系统参数")
@RestController
@RequestMapping("/iam/config")
@RequiredArgsConstructor
@Validated
public class ConfigController {

    private final ConfigService configService;

    @PostMapping("/create")
    @Operation(summary = "创建系统参数")
    public CommonResult<Long> createConfig(@Valid @RequestBody ConfigCreateReqVO createReqVO) {
        return success(configService.createConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新系统参数")
    public CommonResult<Void> updateConfig(@Valid @RequestBody ConfigUpdateReqVO updateReqVO) {
        configService.updateConfig(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除系统参数")
    @Parameter(name = "id", description = "参数ID", required = true)
    public CommonResult<Void> deleteConfig(@PathVariable("id") Long id) {
        configService.deleteConfig(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取系统参数详情")
    @Parameter(name = "id", description = "参数ID", required = true)
    public CommonResult<ConfigRespVO> getConfig(@PathVariable("id") Long id) {
        ConfigDO config = configService.getConfig(id);
        ConfigRespVO respVO = new ConfigRespVO();
        BeanUtils.copyProperties(config, respVO);
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取系统参数列表")
    public CommonResult<List<ConfigRespVO>> getConfigList(@Valid ConfigListReqVO reqVO) {
        List<ConfigDO> list = configService.getConfigList(reqVO);
        List<ConfigRespVO> respList = list.stream()
                .map(config -> {
                    ConfigRespVO respVO = new ConfigRespVO();
                    BeanUtils.copyProperties(config, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

}
