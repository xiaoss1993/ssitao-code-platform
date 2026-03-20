package com.ssitao.code.frame.aggregate.threadcontext;

public class EmptyThreadContextSynchronization implements ThreadContextSynchronization {

    @Override
    public String getCurrentThreadContext() {
        return null;
    }

    @Override
    public void setThreadContext(String threadContext) {

    }

    @Override
    public void clear() {

    }
}
