package com.ssitao.code.frame.aggregate.remoting.netty;

public interface NettyServerConfig extends NettyConfig {

    int getListenPort();

    int getChannelIdleTimeoutSeconds();

    int getFlowMonitorPrintIntervalMinutes();
}
