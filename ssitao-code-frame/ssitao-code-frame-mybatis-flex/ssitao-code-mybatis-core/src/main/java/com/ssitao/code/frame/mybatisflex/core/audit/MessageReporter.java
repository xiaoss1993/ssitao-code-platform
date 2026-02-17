
package com.ssitao.code.frame.mybatisflex.core.audit;


import java.util.List;

/**
 * 审计消息发送器，作用是把审计消息发送到指定的位置
 * 比如控制台，磁盘、或者 kafka 服务器， http 服务器等
 */
public interface MessageReporter {

    void sendMessages(List<AuditMessage> messages);

}
