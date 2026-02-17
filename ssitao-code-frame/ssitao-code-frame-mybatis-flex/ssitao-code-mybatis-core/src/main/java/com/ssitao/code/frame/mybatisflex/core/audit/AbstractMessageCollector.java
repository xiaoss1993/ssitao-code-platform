

package com.ssitao.code.frame.mybatisflex.core.audit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 抽象消息收集器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public abstract class AbstractMessageCollector implements MessageCollector {

    private final MessageReporter messageSender;
    private final List<AuditMessage> messages = Collections.synchronizedList(new ArrayList<>());
    private final ReentrantReadWriteLock rrwLock = new ReentrantReadWriteLock();

    protected AbstractMessageCollector(MessageReporter messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void collect(AuditMessage message) {
        try {
            rrwLock.readLock().lock();
            messages.add(message);
        } finally {
            rrwLock.readLock().unlock();
        }
    }

    protected void doSendMessages() {
        if (messages.isEmpty()) {
            return;
        }
        List<AuditMessage> sendMessages;
        try {
            rrwLock.writeLock().lock();
            sendMessages = new ArrayList<>(messages);
            messages.clear();
        } finally {
            rrwLock.writeLock().unlock();
        }
        messageSender.sendMessages(sendMessages);
    }

    public void release() {
        doSendMessages();
    }

    protected List<AuditMessage> getMessages() {
        return messages;
    }

    protected MessageReporter getMessageSender() {
        return messageSender;
    }

}
