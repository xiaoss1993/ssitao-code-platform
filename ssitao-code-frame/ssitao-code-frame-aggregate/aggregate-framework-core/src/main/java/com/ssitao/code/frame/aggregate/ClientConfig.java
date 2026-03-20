package com.ssitao.code.frame.aggregate;

import com.ssitao.code.frame.aggregate.discovery.registry.ClientRegistryConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryRole;
import com.ssitao.code.frame.aggregate.properties.registry.ClientRegistryProperties;
import com.ssitao.code.frame.aggregate.properties.remoting.NettyClientProperties;
import com.ssitao.code.frame.aggregate.recovery.RecoveryConfig;
import com.ssitao.code.frame.aggregate.remoting.netty.NettyClientConfig;
import com.ssitao.code.frame.aggregate.storage.StoreConfig;

public class ClientConfig extends AbstractConfig implements RecoveryConfig, NettyClientConfig, StoreConfig, ClientRegistryConfig {

    public static final ClientConfig DEFAULT = new ClientConfig();

    private NettyClientConfig nettyClientConfig = new NettyClientProperties();

    private ClientRegistryConfig clientRegistryConfig = new ClientRegistryProperties();

    public ClientConfig() {
    }

    public ClientConfig(StoreConfig storeConfig, RecoveryConfig recoveryConfig, NettyClientConfig nettyClientConfig, ClientRegistryConfig clientRegistryConfig) {
        super(storeConfig, recoveryConfig, nettyClientConfig, clientRegistryConfig);
        if (nettyClientConfig != null) {
            this.nettyClientConfig = nettyClientConfig;
        }
        if (clientRegistryConfig != null) {
            this.clientRegistryConfig = clientRegistryConfig;
        }
    }

    @Override
    public long getConnectTimeoutMillis() {
        return nettyClientConfig.getConnectTimeoutMillis();
    }

    @Override
    public int getChannelPoolMaxTotal() {
        return nettyClientConfig.getChannelPoolMaxTotal();
    }

    @Override
    public int getChannelPoolMaxIdlePerKey() {
        return nettyClientConfig.getChannelPoolMaxIdlePerKey();
    }

    @Override
    public int getChannelPoolMaxTotalPerKey() {
        return nettyClientConfig.getChannelPoolMaxTotalPerKey();
    }

    @Override
    public int getChannelPoolMinIdlePerKey() {
        return nettyClientConfig.getChannelPoolMinIdlePerKey();
    }

    @Override
    public long getChannelPoolMaxWaitMillis() {
        return nettyClientConfig.getChannelPoolMaxWaitMillis();
    }

    @Override
    public long getChannelPoolTimeBetweenEvictionRunsMillis() {
        return nettyClientConfig.getChannelPoolTimeBetweenEvictionRunsMillis();
    }

    @Override
    public long getChannelPoolSoftMinEvictableIdleTimeMillis() {
        return nettyClientConfig.getChannelPoolSoftMinEvictableIdleTimeMillis();
    }

    @Override
    public int getNumTestsPerEvictionRun() {
        return nettyClientConfig.getNumTestsPerEvictionRun();
    }


    @Override
    public int getChannelMaxIdleTimeSeconds() {
        return nettyClientConfig.getChannelMaxIdleTimeSeconds();
    }

    @Override
    public int getReconnectIntervalSeconds() {
        return nettyClientConfig.getReconnectIntervalSeconds();
    }

    @Override
    public RegistryRole getRegistryRole() {
        return clientRegistryConfig.getRegistryRole();
    }

    @Override
    public String getLoadBalanceType() {
        return clientRegistryConfig.getLoadBalanceType();
    }

    public void setNettyClientConfig(NettyClientConfig nettyClientConfig) {
        this.nettyClientConfig = nettyClientConfig;
        setNettyConfig(nettyClientConfig);
    }

    public void setClientRegistryConfig(ClientRegistryConfig clientRegistryConfig) {
        this.clientRegistryConfig = clientRegistryConfig;
        setRegistryConfig(clientRegistryConfig);
    }
}
