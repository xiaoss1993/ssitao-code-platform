package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeRespVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictTypeDO;
import com.ssitao.code.modular.iam.service.DictTypeService;
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
 * 字典类型控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 字典类型")
@RestController
@RequestMapping("/iam/dict-type")
@RequiredArgsConstructor
@Validated
public class DictTypeController {

    private final DictTypeService dictTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建字典类型")
    public CommonResult<Long> createDictType(@Valid @RequestBody DictTypeCreateReqVO createReqVO) {
        return success(dictTypeService.createDictType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新字典类型")
    public CommonResult<Void> updateDictType(@Valid @RequestBody DictTypeUpdateReqVO updateReqVO) {
        dictTypeService.updateDictType(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除字典类型")
    @Parameter(name = "id", description = "字典类型ID", required = true)
    public CommonResult<Void> deleteDictType(@PathVariable("id") Long id) {
        dictTypeService.deleteDictType(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取字典类型详情")
    @Parameter(name = "id", description = "字典类型ID", required = true)
    public CommonResult<DictTypeRespVO> getDictType(@PathVariable("id") Long id) {
        DictTypeDO dictType = dictTypeService.getDictType(id);
        DictTypeRespVO respVO = new DictTypeRespVO();
        BeanUtils.copyProperties(dictType, respVO);
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取字典类型列表")
    public CommonResult<List<DictTypeRespVO>> getDictTypeList(@Valid DictTypeListReqVO reqVO) {
        List<DictTypeDO> list = dictTypeService.getDictTypeList(reqVO);
        List<DictTypeRespVO> respList = list.stream()
                .map(dictType -> {
                    DictTypeRespVO respVO = new DictTypeRespVO();
                    BeanUtils.copyProperties(dictType, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

}
