package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataRespVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictDataDO;
import com.ssitao.code.modular.iam.service.DictDataService;
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
 * 字典数据控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 字典数据")
@RestController
@RequestMapping("/iam/dict-data")
@RequiredArgsConstructor
@Validated
public class DictDataController {

    private final DictDataService dictDataService;

    @PostMapping("/create")
    @Operation(summary = "创建字典数据")
    public CommonResult<Long> createDictData(@Valid @RequestBody DictDataCreateReqVO createReqVO) {
        return success(dictDataService.createDictData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新字典数据")
    public CommonResult<Void> updateDictData(@Valid @RequestBody DictDataUpdateReqVO updateReqVO) {
        dictDataService.updateDictData(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除字典数据")
    @Parameter(name = "id", description = "字典数据ID", required = true)
    public CommonResult<Void> deleteDictData(@PathVariable("id") Long id) {
        dictDataService.deleteDictData(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取字典数据详情")
    @Parameter(name = "id", description = "字典数据ID", required = true)
    public CommonResult<DictDataRespVO> getDictData(@PathVariable("id") Long id) {
        DictDataDO dictData = dictDataService.getDictData(id);
        DictDataRespVO respVO = new DictDataRespVO();
        BeanUtils.copyProperties(dictData, respVO);
        return success(respVO);
    }

    @GetMapping("/list")
    @Operation(summary = "获取字典数据列表")
    public CommonResult<List<DictDataRespVO>> getDictDataList(@Valid DictDataListReqVO reqVO) {
        List<DictDataDO> list = dictDataService.getDictDataList(reqVO);
        List<DictDataRespVO> respList = list.stream()
                .map(dictData -> {
                    DictDataRespVO respVO = new DictDataRespVO();
                    BeanUtils.copyProperties(dictData, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

    @GetMapping("/type/{dictType}")
    @Operation(summary = "根据字典类型获取字典数据列表")
    @Parameter(name = "dictType", description = "字典类型", required = true)
    public CommonResult<List<DictDataRespVO>> getDictDataByType(@PathVariable("dictType") String dictType) {
        List<DictDataDO> list = dictDataService.getDictDataByType(dictType);
        List<DictDataRespVO> respList = list.stream()
                .map(dictData -> {
                    DictDataRespVO respVO = new DictDataRespVO();
                    BeanUtils.copyProperties(dictData, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

}
