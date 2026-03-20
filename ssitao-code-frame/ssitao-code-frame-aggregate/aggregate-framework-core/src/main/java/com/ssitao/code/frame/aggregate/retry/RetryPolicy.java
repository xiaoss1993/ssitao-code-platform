package com.ssitao.code.frame.aggregate.retry;

/**
 * Created by changming.xie on 2/2/16.
 */
public interface RetryPolicy {

    boolean canRetry(RetryContext context);

    void registerThrowable(RetryContext context, Throwable e);

    RetryContext requireRetryContext();
}
