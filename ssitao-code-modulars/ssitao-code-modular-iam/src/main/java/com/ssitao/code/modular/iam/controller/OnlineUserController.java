package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.online.OnlineUserRespVO;
import com.ssitao.code.modular.iam.service.OnlineUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 在线用户控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "管理后台 - 在线用户")
@RestController
@RequestMapping("/iam/online-user")
@RequiredArgsConstructor
@Validated
public class OnlineUserController {

    private final OnlineUserService onlineUserService;

    @GetMapping("/list")
    @Operation(summary = "获取在线用户列表")
    public CommonResult<List<OnlineUserRespVO>> getOnlineUsers() {
        return success(onlineUserService.getOnlineUsers());
    }

    @DeleteMapping("/kickout/{userId}")
    @Operation(summary = "强制用户登出")
    @Parameter(name = "userId", description = "用户ID", required = true)
    public CommonResult<Void> forceLogout(@PathVariable("userId") Long userId) {
        onlineUserService.forceLogout(userId);
        return success();
    }

    @DeleteMapping("/kickout-token")
    @Operation(summary = "强制Token登出")
    @Parameter(name = "token", description = "令牌", required = true)
    public CommonResult<Void> forceLogoutByToken(@RequestParam("token") String token) {
        onlineUserService.forceLogoutByToken(token);
        return success();
    }

}
