package com.ssitao.code.frame.aggregate.remoting;

import com.ssitao.code.frame.aggregate.remoting.protocol.RemotingCommand;

public interface RequestProcessor<T> {

    RemotingCommand processRequest(T context, RemotingCommand request);
}
