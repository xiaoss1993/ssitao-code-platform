package com.ssitao.code.modular.iam.identity.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamRefreshTokenCommand;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM认证控制器
 * 负责登录、登出、令牌管理等认证相关功能
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM认证管理", description = "IAM认证相关接口（登录、登出、令牌）")
@RestController
@RequestMapping("/iam/identity")
public class IamIdentityController {

    private final IamLoginAppService loginAppService;

    public IamIdentityController(IamLoginAppService loginAppService) {
        this.loginAppService = loginAppService;
    }

    // ==================== 登录/登出接口 ====================

    @PostMapping("/login")
    @Operation(summary = "登录", description = "用户登录认证，返回访问令牌")
    public CommonResult<IamLoginResultDTO> login(@Valid @RequestBody IamLoginCommand command) {
        IamLoginResultDTO result = loginAppService.login(command);
        return success(result);
    }

    @PostMapping("/logout")
    @Operation(summary = "登出", description = "用户登出，使令牌失效")
    public CommonResult<Void> logout(@RequestHeader(value = "Authorization", required = false) String authorization,
                                     @Valid @RequestBody(required = false) IamLogoutCommand command) {
        String token = extractToken(authorization);
        loginAppService.logout(token, command);
        return success();
    }

    // ==================== 令牌管理接口 ====================

    @PostMapping("/token/refresh")
    @Operation(summary = "刷新令牌", description = "使用刷新令牌获取新的访问令牌")
    public CommonResult<IamLoginResultDTO> refreshToken(@Valid @RequestBody IamRefreshTokenCommand command) {
        IamLoginResultDTO result = loginAppService.refreshToken(command);
        return success(result);
    }

    @PostMapping("/token/verify")
    @Operation(summary = "验证令牌", description = "验证令牌是否有效")
    public CommonResult<Boolean> verifyToken(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        Boolean valid = loginAppService.verifyToken(token);
        return success(valid);
    }

    // ==================== 当前用户信息接口 ====================

    @GetMapping("/me")
    @Operation(summary = "获取当前登录用户信息", description = "根据令牌获取当前登录用户信息")
    public CommonResult<Object> getMe(@RequestHeader(value = "Authorization", required = false) String authorization) {
        String token = extractToken(authorization);
        Object user = loginAppService.getCurrentUser(token);
        return success(user);
    }

    @GetMapping("/current")
    @Operation(summary = "获取当前登录用户信息", description = "根据令牌获取当前登录用户信息")
    public CommonResult<Object> getCurrentUser(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        Object user = loginAppService.getCurrentUser(token);
        return success(user);
    }

    @GetMapping("/current/permissions")
    @Operation(summary = "获取当前用户权限", description = "获取当前登录用户的权限列表")
    public CommonResult<Object> getCurrentUserPermissions(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        Object permissions = loginAppService.getCurrentUserPermissions(token);
        return success(permissions);
    }

    @GetMapping("/current/roles")
    @Operation(summary = "获取当前用户角色", description = "获取当前登录用户的角色列表")
    public CommonResult<Object> getCurrentUserRoles(@RequestHeader("Authorization") String authorization) {
        String token = extractToken(authorization);
        Object roles = loginAppService.getCurrentUserRoles(token);
        return success(roles);
    }

    /**
     * 从 Authorization 头中提取 token
     * 支持 "Bearer token" 和纯 token 两种格式
     */
    private String extractToken(String authorization) {
        if (authorization == null || authorization.isEmpty()) {
            return null;
        }
        if (authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return authorization;
    }

}
