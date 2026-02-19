package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信日志数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_sms_log")
public class SmsLogDO {

    /**
     * 日志ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 短信类型：1-验证码 2-通知 3-营销
     */
    private Integer type;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 模板编码
     */
    private String templateCode;

    /**
     * 模板参数
     */
    private String templateParams;

    /**
     * 发送结果：1-成功 0-失败
     */
    private Integer result;

    /**
     * 结果消息
     */
    private String resultMsg;

    /**
     * 短信平台返回ID
     */
    private String platformMessageId;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
