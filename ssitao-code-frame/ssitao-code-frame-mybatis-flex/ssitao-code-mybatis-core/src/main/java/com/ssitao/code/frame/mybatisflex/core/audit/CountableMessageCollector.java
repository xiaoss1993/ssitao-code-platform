
package com.ssitao.code.frame.mybatisflex.core.audit;

/**
 * 计数消息收集器，当消息达到指定数量时发送消息。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class CountableMessageCollector extends AbstractMessageCollector {

    private final int count;

    public CountableMessageCollector() {
        this(1000, new ConsoleMessageReporter());
    }

    public CountableMessageCollector(int count, MessageReporter messageSender) {
        super(messageSender);
        this.count = count;
    }

    @Override
    public void collect(AuditMessage message) {
        super.collect(message);
        if (getMessages().size() >= count) {
            new Thread(this::doSendMessages).start();
        }
    }

}
