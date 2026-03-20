package com.ssitao.code.frame.aggregate.eventhandling.processor;

import com.ssitao.code.frame.aggregate.eventhandling.EventInvokerEntry;
import com.ssitao.code.frame.aggregate.eventhandling.annotation.EventHandler;
import com.ssitao.code.frame.aggregate.utils.ReflectionUtils;

/**
 * Created by changmingxie on 12/2/15.
 */
public class EventHandlerProcessor {

    public static void proceed(EventInvokerEntry eventInvokerEntry) {

        EventHandler eventHandler = ReflectionUtils.getAnnotation(eventInvokerEntry.getMethod(), EventHandler.class);
        if (eventHandler.asynchronous()) {
            AsyncMethodInvoker.getInstance().invoke(eventInvokerEntry);
        } else {
            EventMethodInvoker.getInstance().invoke(eventInvokerEntry);
        }
    }

    public static void prepare(EventInvokerEntry entry) {
        EventMethodInvoker.getInstance().preInvoke(entry);
    }

    public static void cancel(EventInvokerEntry entry) {
        EventMethodInvoker.getInstance().cancelInvoke(entry);
    }
}
