package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.result.Result;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.modular.iam.controller.vo.AuthLoginReqVO;
import com.ssitao.code.modular.iam.controller.vo.AuthLoginRespVO;
import com.ssitao.code.modular.iam.controller.vo.auth.RefreshTokenReqVO;
import com.ssitao.code.modular.iam.controller.vo.auth.ResetPasswordReqVO;
import com.ssitao.code.modular.iam.service.AuthService;
import com.ssitao.code.modular.iam.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 认证控制器
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "认证管理", description = "用户认证相关接口")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final CaptchaService captchaService;

    /**
     * 用户登录
     *
     * @param reqVO 登录请求
     * @return 登录响应
     */
    @Operation(summary = "用户登录", description = "使用用户名和密码登录")
    @PostMapping("/login")
    public Result<AuthLoginRespVO> login(@Valid @RequestBody AuthLoginReqVO reqVO) {
        String token = authService.login(reqVO.getUsername(), reqVO.getPassword());
        LoginUser user = authService.getLoginUser();
        return Result.success(new AuthLoginRespVO(token, user));
    }

    /**
     * 用户登出
     */
    @Operation(summary = "用户登出", description = "退出登录")
    @PostMapping("/logout")
    public Result<Void> logout() {
        authService.logout();
        return Result.success();
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 登录用户信息
     */
    @Operation(summary = "获取用户信息", description = "获取当前登录用户的详细信息")
    @GetMapping("/user")
    public Result<LoginUser> getUser() {
        LoginUser user = authService.getLoginUser();
        return Result.success(user);
    }

    /**
     * 检查登录状态
     *
     * @return 是否已登录
     */
    @Operation(summary = "检查登录状态", description = "检查当前是否已登录")
    @GetMapping("/check")
    public Result<Boolean> checkLogin() {
        return Result.success(true);
    }

    /**
     * 刷新Token
     */
    @Operation(summary = "刷新Token", description = "使用刷新令牌获取新的访问令牌")
    @PostMapping("/refresh-token")
    public Result<String> refreshToken(@Valid @RequestBody RefreshTokenReqVO reqVO) {
        String token = authService.refreshToken(reqVO.getRefreshToken());
        return Result.success(token);
    }

    /**
     * 重置密码
     */
    @Operation(summary = "重置密码", description = "通过验证码重置用户密码")
    @PostMapping("/reset-password")
    public Result<Void> resetPassword(@Valid @RequestBody ResetPasswordReqVO reqVO) {
        authService.resetPassword(reqVO.getUsername(), reqVO.getNewPassword(), reqVO.getVerifyCode());
        return Result.success();
    }

}
