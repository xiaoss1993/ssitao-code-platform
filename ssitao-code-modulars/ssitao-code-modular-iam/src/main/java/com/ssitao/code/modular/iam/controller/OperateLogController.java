package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.log.OperateLogPageReqVO;
import com.ssitao.code.modular.iam.controller.vo.log.OperateLogRespVO;
import com.ssitao.code.modular.iam.dal.dataobject.OperateLogDO;
import com.ssitao.code.modular.iam.service.OperateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 操作日志控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 操作日志")
@RestController
@RequestMapping("/iam/operate-log")
@RequiredArgsConstructor
@Validated
public class OperateLogController {

    private final OperateLogService operateLogService;

    @GetMapping("/list")
    @Operation(summary = "获取操作日志列表")
    public CommonResult<List<OperateLogRespVO>> getOperateLogList(@Valid OperateLogPageReqVO reqVO) {
        List<OperateLogDO> list = operateLogService.getOperateLogList(reqVO);
        List<OperateLogRespVO> respList = list.stream()
                .map(log -> {
                    OperateLogRespVO respVO = new OperateLogRespVO();
                    BeanUtils.copyProperties(log, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

}
