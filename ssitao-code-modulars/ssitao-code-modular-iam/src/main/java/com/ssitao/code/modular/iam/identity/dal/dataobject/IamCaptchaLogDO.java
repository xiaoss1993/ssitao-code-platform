package com.ssitao.code.modular.iam.identity.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 验证码日志数据对象
 * 对应表：iam_captcha_log
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_captcha_log")
public class IamCaptchaLogDO {

    /**
     * 日志ID
     */
    @Id(keyType = KeyType.None)
    private String logId;

    /**
     * 验证码类型
     */
    private String captchaType;

    /**
     * 验证目标(手机号/邮箱)
     */
    private String captchaTarget;

    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 验证码状态
     */
    private String captchaStatus;

    /**
     * 使用时间
     */
    private LocalDateTime useTime;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 租户ID
     */
    private String tenantId;

}
