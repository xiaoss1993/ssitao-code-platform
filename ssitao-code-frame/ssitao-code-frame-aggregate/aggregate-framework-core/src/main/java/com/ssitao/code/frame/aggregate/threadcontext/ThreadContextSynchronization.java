package com.ssitao.code.frame.aggregate.threadcontext;

public interface ThreadContextSynchronization {

    public String getCurrentThreadContext();

    public void setThreadContext(String threadContext);

    public void clear();
}
