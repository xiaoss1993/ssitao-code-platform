package com.ssitao.code.frame.aggregate.eventhandling.processor.async;

import com.ssitao.code.frame.aggregate.eventhandling.EventInvokerEntry;

/**
 * Created by changmingxie on 12/2/15.
 */
public class AsyncEvent {

    public static final AsyncEventFactory FACTORY = new AsyncEventFactory();

    private EventInvokerEntry eventInvokerEntry;
    private String threadContext;

    public void reset(EventInvokerEntry eventInvokerEntry, String threadContext) {
        this.eventInvokerEntry = eventInvokerEntry;
        this.threadContext = threadContext;
    }

    public EventInvokerEntry getEventInvokerEntry() {
        return eventInvokerEntry;
    }

    public String getThreadContext() {
        return threadContext;
    }

    public void clear() {
        eventInvokerEntry = null;
        threadContext = null;
    }
}
