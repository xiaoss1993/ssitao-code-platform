package com.ssitao.code.modular.iam.audit.application.query;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM操作日志查询对象
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamOperateLogQuery {

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 操作人账号
     */
    private String operatorAccount;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作模块
     */
    private String operateModule;

    /**
     * 操作类型：CREATE-创建 UPDATE-更新 DELETE-删除 QUERY-查询 LOGIN-登录 LOGOUT-登出 OTHER-其他
     */
    private String operateType;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 响应结果
     */
    private String responseResult;

    /**
     * 执行时长(毫秒)
     */
    private Long executeTime;

    /**
     * 操作状态：SUCCESS-成功 FAIL-失败
     */
    private String operateStatus;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作IP
     */
    private String operateIp;

    /**
     * 操作地点
     */
    private String operateLocation;

    /**
     * 浏览器类型
     */
    private String browserType;

    /**
     * 操作系统
     */
    private String osType;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

}
