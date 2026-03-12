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
 * 登录日志数据对象
 * 对应表：iam_login_log
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_login_log")
public class IamLoginLogDO {

    /**
     * 日志ID
     */
    @Id(keyType = KeyType.None)
    private String logId;

    /**
     * 账户ID
     */
    private String accountId;

    /**
     * 账户编码
     */
    private String accountCode;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 登录类型
     */
    private String loginType;

    /**
     * 登录状态
     */
    private String loginStatus;

    /**
     * 失败原因
     */
    private String loginFailReason;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 登录设备
     */
    private String loginDevice;

    /**
     * 浏览器
     */
    private String loginBrowser;

    /**
     * 操作系统
     */
    private String loginOs;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 退出时间
     */
    private LocalDateTime logoutTime;

    /**
     * 在线时长(秒)
     */
    private Integer onlineDuration;

    /**
     * 租户ID
     */
    private String tenantId;

}
