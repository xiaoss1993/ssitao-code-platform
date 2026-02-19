package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_operate_log")
public class OperateLogDO {

    /**
     * 日志ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 模块名
     */
    private String module;

    /**
     * 操作名
     */
    private String name;

    /**
     * 操作类型：1-查询 2-新增 3-修改 4-删除 5-导出 6-导入
     */
    private Integer type;

    /**
     * 业务编号
     */
    private String businessNo;

    /**
     * 请求方法名
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
     * 返回值
     */
    private String returnValue;

    /**
     * 返回码：0-成功 其他-失败
     */
    private Integer returnCode;

    /**
     * 返回消息
     */
    private String returnMsg;

    /**
     * 执行时长（毫秒）
     */
    private Integer costTime;

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 用户代理
     */
    private String userAgent;

    /**
     * 操作时间
     */
    private LocalDateTime operateTime;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
