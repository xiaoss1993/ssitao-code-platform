package com.ssitao.code.modular.iam.identity.application.service;

import com.ssitao.code.modular.iam.identity.api.dto.CaptchaResultDTO;

/**
 * 验证码服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @return 验证码结果（包含验证码ID和图片Base64）
     */
    CaptchaResultDTO generateCaptcha();

    /**
     * 验证验证码
     *
     * @param captchaId 验证码ID
     * @param captchaCode 用户输入的验证码
     * @return 验证是否成功
     */
    boolean verifyCaptcha(String captchaId, String captchaCode);

    /**
     * 刷新验证码
     *
     * @param captchaId 旧验证码ID
     * @return 新的验证码结果
     */
    CaptchaResultDTO refreshCaptcha(String captchaId);
}
