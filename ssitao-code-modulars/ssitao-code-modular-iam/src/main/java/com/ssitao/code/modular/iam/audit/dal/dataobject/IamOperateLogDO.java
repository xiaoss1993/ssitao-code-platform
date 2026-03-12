package com.ssitao.code.modular.iam.audit.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 操作日志数据对象
 * 对应表：iam_operate_log
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_operate_log")
public class IamOperateLogDO {

    /**
     * 日志ID
     */
    @Id(keyType = KeyType.None)
    private String logId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 业务ID
     */
    private String businessId;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 返回结果
     */
    private String responseResult;

    /**
     * 操作状态
     */
    private String operateStatus;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 执行时长(毫秒)
     */
    private Integer executeDuration;

    /**
     * 操作人ID
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作部门
     */
    private String operatorDept;

    /**
     * 操作IP
     */
    private String operateIp;

    /**
     * 操作地点
     */
    private String operateLocation;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 租户ID
     */
    private String tenantId;

}
