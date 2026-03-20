package com.ssitao.code.frame.aggregate.sample.threadcontext;

import com.ssitao.code.frame.aggregate.threadcontext.ThreadContextSynchronization;

public class TestThreadContextSynchronization implements ThreadContextSynchronization {

//    private ThreadLocal<String> threadContext = new ThreadLocal<>();

    public volatile static String THREAD_CONTEXT = null;

    @Override
    public String getCurrentThreadContext() {
        return THREAD_CONTEXT;
    }

    @Override
    public void setThreadContext(String threadContext) {
        System.out.println("set thread context:" + threadContext);
        THREAD_CONTEXT = threadContext;
    }

    @Override
    public void clear() {
        THREAD_CONTEXT = null;
    }
}
