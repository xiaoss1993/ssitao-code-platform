package com.ssitao.code.modular.iam.audit.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM操作日志聚合根
 * Audit领域的操作日志管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamOperateLog {

    /**
     * 日志ID
     */
    private String id;

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
     * 操作人ID
     */
    private String operatorId;

    /**
     * 操作人账号
     */
    private String operatorAccount;

    /**
     * 操作人姓名
     */
    private String operatorName;

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建操作日志
     *
     * @param operatorId      操作人ID
     * @param operatorAccount 操作人账号
     * @param operatorName    操作人姓名
     * @param operateModule   操作模块
     * @param operateType     操作类型
     * @param operateDesc     操作描述
     * @param tenantId        租户ID
     * @return 操作日志聚合根
     */
    public static IamOperateLog create(String operatorId, String operatorAccount, String operatorName,
                                        String operateModule, String operateType, String operateDesc,
                                        String tenantId) {
        IamOperateLog log = new IamOperateLog();
        log.setOperatorId(operatorId);
        log.setOperatorAccount(operatorAccount);
        log.setOperatorName(operatorName);
        log.setOperateModule(operateModule);
        log.setOperateType(operateType);
        log.setOperateDesc(operateDesc);
        log.setTenantId(tenantId);
        log.setOperateStatus("SUCCESS");
        log.setCreateTime(LocalDateTime.now());
        return log;
    }

    /**
     * 设置请求信息
     *
     * @param requestMethod 请求方法
     * @param requestUrl    请求URL
     * @param requestParams 请求参数
     */
    public void setRequestInfo(String requestMethod, String requestUrl, String requestParams) {
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestParams = requestParams;
    }

    /**
     * 设置响应信息
     *
     * @param responseResult 响应结果
     * @param executeTime    执行时长
     */
    public void setResponseInfo(String responseResult, Long executeTime) {
        this.responseResult = responseResult;
        this.executeTime = executeTime;
    }

    /**
     * 设置操作环境信息
     *
     * @param operateIp       操作IP
     * @param operateLocation 操作地点
     * @param browserType     浏览器类型
     * @param osType          操作系统
     */
    public void setEnvironmentInfo(String operateIp, String operateLocation, String browserType, String osType) {
        this.operateIp = operateIp;
        this.operateLocation = operateLocation;
        this.browserType = browserType;
        this.osType = osType;
    }

    /**
     * 标记为失败
     *
     * @param errorMsg 错误消息
     */
    public void markAsFail(String errorMsg) {
        this.operateStatus = "FAIL";
        this.errorMsg = errorMsg;
    }

    /**
     * 判断是否成功
     *
     * @return true-成功，false-失败
     */
    public boolean isSuccess() {
        return "SUCCESS".equals(this.operateStatus);
    }

}
