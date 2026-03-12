
package com.ssitao.code.frame.mybatisflex.core.audit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 默认的审计消息收集器，其收集消息后，定时通过消息发送器{@link MessageReporter}把消息发送过去
 */
public class ScheduledMessageCollector extends AbstractMessageCollector {

    private final ScheduledExecutorService scheduler;

    public ScheduledMessageCollector() {
        this(10, new ConsoleMessageReporter());
    }


    public ScheduledMessageCollector(long period, MessageReporter messageSender) {
        super(messageSender);
        this.scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "ScheduledMessageCollector");
            thread.setDaemon(true);
            return thread;
        });
        this.scheduler.scheduleAtFixedRate(this::doSendMessages, period, period, TimeUnit.SECONDS);
    }

    public void release() {
        doSendMessages(); //clear the messages
        scheduler.shutdown();
    }

}
