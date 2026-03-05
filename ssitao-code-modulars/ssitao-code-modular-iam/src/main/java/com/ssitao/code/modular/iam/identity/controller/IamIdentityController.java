package com.ssitao.code.modular.iam.identity.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.identity.api.dto.CaptchaResultDTO;
import com.ssitao.code.modular.iam.identity.application.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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

    private final CaptchaService captchaService;

    public IamIdentityController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    // ==================== 验证码接口 ====================

    @GetMapping("/captcha")
    @Operation(summary = "获取验证码", description = "获取图形验证码")
    public CommonResult<CaptchaResultDTO> getCaptcha() {
        CaptchaResultDTO result = captchaService.generateCaptcha();
        return success(result);
    }

    @GetMapping("/captcha/refresh")
    @Operation(summary = "刷新验证码", description = "刷新图形验证码")
    public CommonResult<CaptchaResultDTO> refreshCaptcha(
            @Parameter(description = "旧验证码ID") @RequestParam(required = false) String captchaId) {
        CaptchaResultDTO result = captchaService.refreshCaptcha(captchaId);
        return success(result);
    }

    // ==================== 登录/登出接口 ====================
    // 注意：登录接口已移至 /api/auth/login，此处仅保留登出和令牌管理

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
