
package com.ssitao.code.frame.mybatisflex.core.audit;

/**
 * 审计消息创建器，在一般的 web 服务中，用户应该自定义自己的 AuditMessageFactory
 * 用来配置当前操作用户、用户 IP 地址，访问的 url 地址等
 */
public interface MessageFactory {

    AuditMessage create();

}
