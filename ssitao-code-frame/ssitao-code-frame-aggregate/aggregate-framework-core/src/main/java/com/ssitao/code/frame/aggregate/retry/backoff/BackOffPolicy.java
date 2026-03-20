package com.ssitao.code.frame.aggregate.retry.backoff;

import com.ssitao.code.frame.aggregate.retry.RetryContext;

/**
 * Created by changming.xie on 2/2/16.
 */

public interface BackOffPolicy {

    BackOffContext start(RetryContext context);

    void backOff(BackOffContext backOffContext);

}