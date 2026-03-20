package com.ssitao.code.frame.aggregate.properties.remoting;

import com.ssitao.code.frame.aggregate.remoting.netty.NettyServerConfig;

/**
 * @author Nervose.Wu
 * @date 2022/5/24 09:46
 */
public class NettyServerProperties extends NettyProperties implements NettyServerConfig {

    private int listenPort = 2332;
    //max idle time for client channel,then exceed the idle time, close the client channel
    private int channelIdleTimeoutSeconds = 60;

    private int flowMonitorPrintIntervalMinutes = 5;

    @Override
    public int getListenPort() {
        return listenPort;
    }

    public void setListenPort(int listenPort) {
        this.listenPort = listenPort;
    }

    @Override
    public int getChannelIdleTimeoutSeconds() {
        return channelIdleTimeoutSeconds;
    }

    public void setChannelIdleTimeoutSeconds(int channelIdleTimeoutSeconds) {
        this.channelIdleTimeoutSeconds = channelIdleTimeoutSeconds;
    }

    @Override
    public int getFlowMonitorPrintIntervalMinutes() {
        return flowMonitorPrintIntervalMinutes;
    }

    public void setFlowMonitorPrintIntervalMinutes(int flowMonitorPrintIntervalMinutes) {
        this.flowMonitorPrintIntervalMinutes = flowMonitorPrintIntervalMinutes;
    }
}
