
package com.ssitao.code.frame.mybatisflex.core.audit;

import java.util.List;

/**
 * 控制台输出审计消息
 */
public class ConsoleMessageReporter implements MessageReporter {

    @Override
    public void sendMessages(List<AuditMessage> messages) {
        for (AuditMessage message : messages) {
            System.out.println(">>>>>>Sql Audit: " + message.toString());
        }
    }

}
