package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.log.LoginLogPageReqVO;
import com.ssitao.code.modular.iam.controller.vo.log.LoginLogRespVO;
import com.ssitao.code.modular.iam.dal.dataobject.LoginLogDO;
import com.ssitao.code.modular.iam.service.LoginLogService;
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
 * 登录日志控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 登录日志")
@RestController
@RequestMapping("/iam/login-log")
@RequiredArgsConstructor
@Validated
public class LoginLogController {

    private final LoginLogService loginLogService;

    @GetMapping("/list")
    @Operation(summary = "获取登录日志列表")
    public CommonResult<List<LoginLogRespVO>> getLoginLogList(@Valid LoginLogPageReqVO reqVO) {
        List<LoginLogDO> list = loginLogService.getLoginLogList(reqVO);
        List<LoginLogRespVO> respList = list.stream()
                .map(log -> {
                    LoginLogRespVO respVO = new LoginLogRespVO();
                    BeanUtils.copyProperties(log, respVO);
                    return respVO;
                })
                .collect(Collectors.toList());
        return success(respList);
    }

}
