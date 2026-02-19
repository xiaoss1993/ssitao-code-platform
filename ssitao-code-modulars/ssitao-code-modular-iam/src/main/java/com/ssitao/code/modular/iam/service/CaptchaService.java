package com.ssitao.code.modular.iam.service;

/**
 * 验证码服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param uuid 验证码唯一标识
     * @return 验证码图片的 Base64 编码
     */
    String generateCaptcha(String uuid);

    /**
     * 验证验证码
     *
     * @param uuid 验证码唯一标识
     * @param code 验证码
     * @return 是否验证成功
     */
    boolean verifyCaptcha(String uuid, String code);

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @return 验证码
     */
    String sendSmsCode(String mobile);

    /**
     * 验证短信验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return 是否验证成功
     */
    boolean verifySmsCode(String mobile, String code);

}
