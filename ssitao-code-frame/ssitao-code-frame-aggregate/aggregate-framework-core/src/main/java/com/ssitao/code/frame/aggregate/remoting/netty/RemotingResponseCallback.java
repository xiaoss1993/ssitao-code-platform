package com.ssitao.code.frame.aggregate.remoting.netty;

import com.ssitao.code.frame.aggregate.remoting.protocol.RemotingCommand;

public interface RemotingResponseCallback {

    void callback(RemotingCommand response);
}
