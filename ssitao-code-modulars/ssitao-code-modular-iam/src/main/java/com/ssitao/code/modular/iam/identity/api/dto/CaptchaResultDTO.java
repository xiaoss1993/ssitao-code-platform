package com.ssitao.code.modular.iam.identity.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 验证码结果DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaResultDTO {

    /**
     * 验证码ID（唯一标识）
     */
    private String captchaId;

    /**
     * 验证码图片Base64编码
     */
    private String captchaImage;

    /**
     * 验证码类型：1-数字 2-字母 3-数字+字母
     */
    private Integer captchaType;

    /**
     * 验证码长度
     */
    private Integer captchaLength;

    /**
     * 验证码过期时间（秒）
     */
    private Integer expireSeconds;
}
