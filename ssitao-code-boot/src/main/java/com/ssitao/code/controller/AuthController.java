package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamRefreshTokenCommand;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.application.service.impl.IamLoginAppServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证API控制器
 * 提供给前端Ajax调用的认证接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "认证API", description = "认证相关API接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IamLoginAppService loginAppService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @Operation(summary = "登录", description = "用户登录认证")
    public CommonResult<IamLoginResultDTO> login(@Valid @RequestBody IamLoginCommand command) {
        IamLoginResultDTO result = loginAppService.login(command);
        return CommonResult.success(result);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    @Operation(summary = "登出", description = "用户登出")
    public CommonResult<Void> logout(@RequestHeader(value = "Authorization", required = false) String authorization) {
        String token = extractToken(authorization);
        loginAppService.logout(token, new IamLogoutCommand());
        return CommonResult.success();
    }

    /**
     * 刷新Token
     */
    @PostMapping("/refresh")
    @Operation(summary = "刷新Token", description = "刷新访问令牌")
    public CommonResult<IamLoginResultDTO> refreshToken(@Valid @RequestBody IamRefreshTokenCommand command) {
        IamLoginResultDTO result = loginAppService.refreshToken(command);
        return CommonResult.success(result);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/userinfo")
    @Operation(summary = "获取用户信息", description = "获取当前登录用户信息")
    public CommonResult<Map<String, Object>> getUserInfo(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        String token = extractToken(authorization);

        if (!StpUtil.isLogin()) {
            return CommonResult.error(401, "未登录");
        }

        Object user = loginAppService.getCurrentUser(token);
        Object permissions = loginAppService.getCurrentUserPermissions(token);
        Object roles = loginAppService.getCurrentUserRoles(token);

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("permissions", permissions);
        result.put("roles", roles);
        result.put("userId", IamLoginAppServiceImpl.extractAccountId(StpUtil.getLoginId()));
        result.put("userName", StpUtil.getLoginIdAsString());

        return CommonResult.success(result);
    }

    /**
     * 检查登录状态
     */
    @GetMapping("/check")
    @Operation(summary = "检查登录状态", description = "检查当前用户是否已登录")
    public CommonResult<Map<String, Object>> checkLogin() {
        Map<String, Object> result = new HashMap<>();
        result.put("isLogin", StpUtil.isLogin());
        if (StpUtil.isLogin()) {
            result.put("userId", IamLoginAppServiceImpl.extractAccountId(StpUtil.getLoginId()));
            result.put("token", StpUtil.getTokenValue());
        }
        return CommonResult.success(result);
    }

    /**
     * 从 Authorization 头中提取 token
     */
    private String extractToken(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            return StpUtil.getTokenValue();
        }
        if (authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return authorization;
    }
}
