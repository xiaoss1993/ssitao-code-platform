package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM操作日志数据对象
 * 对应数据库表：tb_iam_operatelog
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_operatelog")
public class IamOperateLogDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamOperatelogId;

    /**
     * 操作模块
     */
    @Column(value = "operatelog_module")
    private String operatelogModule;

    /**
     * 操作名称
     */
    @Column(value = "operatelog_desc")
    private String operatelogDesc;

    /**
     * 操作类型：CREATE-创建 UPDATE-更新 DELETE-删除 QUERY-查询 EXPORT-导出 IMPORT-导入 OTHER-其他
     */
    @Column(value = "operatelog_type")
    private String operatelogType;

    /**
     * 请求方法
     */
    @Column(value = "request_method")
    private String requestMethod;

    /**
     * 请求URI
     */
    @Column(value = "request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Column(value = "request_params")
    private String requestParams;

    /**
     * 返回结果
     */
    @Column(value = "response_result")
    private String responseResult;

    /**
     * 操作状态：1-成功 0-失败
     */
    @Column(value = "operatelog_status")
    private String operatelogStatus;

    /**
     * 错误信息
     */
    @Column(value = "error_msg")
    private String errorMsg;

    /**
     * 执行时长(ms)
     */
    @Column(value = "execute_time")
    private Long executeTime;

    /**
     * 用户ID
     */
    @Column(value = "operator_id")
    private String operatorId;

    /**
     * 用户名
     */
    @Column(value = "operator_account")
    private String operatorAccount;

    /**
     * 操作IP
     */
    @Column(value = "operate_ip")
    private String operateIp;

    /**
     * 操作地点
     */
    @Column(value = "operate_location")
    private String operateLocation;

    /**
     * 用户代理
     */
    @Column(value = "user_agent")
    private String userAgent;

    /**
     * 操作时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

}
