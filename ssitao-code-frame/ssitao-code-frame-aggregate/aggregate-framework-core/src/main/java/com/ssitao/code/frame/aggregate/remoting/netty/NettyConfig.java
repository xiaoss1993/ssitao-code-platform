package com.ssitao.code.frame.aggregate.remoting.netty;

public interface NettyConfig {

    int getWorkSelectorThreadSize();

    int getWorkerThreadSize();

    int getSocketBacklog();

    int getSocketRcvBufSize();

    int getSocketSndBufSize();

    int getFrameMaxLength();

    int getRequestProcessThreadSize();

    int getRequestProcessThreadQueueCapacity();


}
