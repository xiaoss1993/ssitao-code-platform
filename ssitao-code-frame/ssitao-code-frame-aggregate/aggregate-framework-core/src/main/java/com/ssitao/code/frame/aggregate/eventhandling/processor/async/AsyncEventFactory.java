package com.ssitao.code.frame.aggregate.eventhandling.processor.async;

import com.lmax.disruptor.EventFactory;

/**
 * Created by changmingxie on 12/2/15.
 */
public class AsyncEventFactory implements EventFactory<AsyncEvent> {
    @Override
    public AsyncEvent newInstance() {
        return new AsyncEvent();
    }
}
