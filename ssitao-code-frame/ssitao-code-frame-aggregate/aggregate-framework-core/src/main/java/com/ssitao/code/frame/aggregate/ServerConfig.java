package com.ssitao.code.frame.aggregate;

import com.ssitao.code.frame.aggregate.discovery.registry.ServerRegistryConfig;
import com.ssitao.code.frame.aggregate.properties.registry.ServerRegistryProperties;
import com.ssitao.code.frame.aggregate.properties.remoting.NettyServerProperties;
import com.ssitao.code.frame.aggregate.recovery.RecoveryConfig;
import com.ssitao.code.frame.aggregate.remoting.netty.NettyServerConfig;
import com.ssitao.code.frame.aggregate.storage.StoreConfig;

public class ServerConfig extends AbstractConfig implements NettyServerConfig, RecoveryConfig, StoreConfig, ServerRegistryConfig {

    public static final ServerConfig DEFAULT = new ServerConfig();

    private NettyServerConfig nettyServerConfig = new NettyServerProperties();

    private ServerRegistryConfig serverRegistryConfig = new ServerRegistryProperties();

    public ServerConfig() {
    }

    public ServerConfig(StoreConfig storeConfig, RecoveryConfig recoveryConfig, NettyServerConfig nettyServerConfig, ServerRegistryConfig serverRegistryConfig) {
        super(storeConfig, recoveryConfig, nettyServerConfig, serverRegistryConfig);
        if (nettyServerConfig != null) {
            this.nettyServerConfig = nettyServerConfig;
        }
        if (serverRegistryConfig != null) {
            this.serverRegistryConfig = serverRegistryConfig;
        }
    }

    @Override
    public int getListenPort() {
        return nettyServerConfig.getListenPort();
    }

    @Override
    public int getChannelIdleTimeoutSeconds() {
        return nettyServerConfig.getChannelIdleTimeoutSeconds();
    }

    @Override
    public int getFlowMonitorPrintIntervalMinutes() {
        return nettyServerConfig.getFlowMonitorPrintIntervalMinutes();
    }


    @Override
    public String getRegistryAddress() {
        return serverRegistryConfig.getRegistryAddress();
    }

    @Override
    public int getRegistryPortForDashboard() {
        return serverRegistryConfig.getRegistryPortForDashboard();
    }

    @Override
    public String getRegistryAddressForDashboard() {
        return serverRegistryConfig.getRegistryAddressForDashboard();
    }

    public void setNettyServerConfig(NettyServerConfig nettyServerConfig) {
        this.nettyServerConfig = nettyServerConfig;
        setNettyConfig(nettyServerConfig);
    }

    public void setServerRegistryConfig(ServerRegistryConfig serverRegistryConfig) {
        this.serverRegistryConfig = serverRegistryConfig;
        setRegistryConfig(serverRegistryConfig);
    }
}
