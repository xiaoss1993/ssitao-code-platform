package com.ssitao.code.modular.iam.identity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM登录日志实体
 * 属于IamAccount聚合
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamLoginLog {

    /**
     * 日志ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 账号ID
     */
    private String accountId;

    /**
     * 账号编码
     */
    private String accountCode;

    /**
     * 登录类型：PASSWORD-密码 SMS-短信验证码 SOCIAL-第三方
     */
    private String loginType;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 设备信息
     */
    private String deviceInfo;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 登录状态：1-成功 0-失败
     */
    private Boolean loginStatus;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建登录日志
     *
     * @param accountId  账号ID
     * @param accountCode 账号编码
     * @param loginType  登录类型
     * @param loginIp    登录IP
     * @param deviceInfo 设备信息
     * @param success    是否成功
     * @param errorMsg   错误信息
     * @return 登录日志实体
     */
    public static IamLoginLog create(String accountId, String accountCode, String loginType,
                                      String loginIp, String deviceInfo,
                                      boolean success, String errorMsg) {
        IamLoginLog log = new IamLoginLog();
        log.setAccountId(accountId);
        log.setAccountCode(accountCode);
        log.setLoginType(loginType);
        log.setLoginIp(loginIp);
        log.setDeviceInfo(deviceInfo);
        log.setLoginStatus(success);
        log.setErrorMessage(errorMsg);
        log.setLoginTime(LocalDateTime.now());
        return log;
    }

}
