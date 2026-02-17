
package com.ssitao.code.frame.mybatisflex.core.audit;

/**
 * 审计消息收集器
 */
public interface MessageCollector {

    void collect(AuditMessage message);

}
