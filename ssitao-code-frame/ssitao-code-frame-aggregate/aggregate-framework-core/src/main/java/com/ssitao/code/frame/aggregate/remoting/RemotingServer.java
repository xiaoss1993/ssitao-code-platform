package com.ssitao.code.frame.aggregate.remoting;

import com.ssitao.code.frame.aggregate.remoting.protocol.RemotingCommand;

public interface RemotingServer<T> extends RemotingService<T> {
    RemotingCommand invokeSync(final String key, final RemotingCommand request, final long timeoutMillis);
}
