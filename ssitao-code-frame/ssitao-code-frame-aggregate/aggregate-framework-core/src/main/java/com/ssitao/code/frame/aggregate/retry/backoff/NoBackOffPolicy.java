package com.ssitao.code.frame.aggregate.retry.backoff;

import com.ssitao.code.frame.aggregate.retry.RetryContext;

/**
 * Created by changming.xie on 2/2/16.
 */
public class NoBackOffPolicy implements BackOffPolicy {


    @Override
    public BackOffContext start(RetryContext context) {
        return null;
    }

    @Override
    public void backOff(BackOffContext backOffContext) {

    }
}
