package com.ssitao.code.frame.aggregate.server.config;

import com.ssitao.code.frame.aggregate.AggServer;
import com.ssitao.code.frame.aggregate.ServerConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.ServerRegistryConfig;
import com.ssitao.code.frame.aggregate.properties.RecoveryProperties;
import com.ssitao.code.frame.aggregate.properties.registry.ServerRegistryProperties;
import com.ssitao.code.frame.aggregate.properties.remoting.NettyServerProperties;
import com.ssitao.code.frame.aggregate.properties.store.StoreProperties;
import com.ssitao.code.frame.aggregate.recovery.RecoveryConfig;
import com.ssitao.code.frame.aggregate.remoting.netty.NettyServerConfig;
import com.ssitao.code.frame.aggregate.spring.factory.SpringBeanFactory;
import com.ssitao.code.frame.aggregate.storage.StoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class AppConfig {

    @Bean
    @ConfigurationProperties("spring.agg.remoting")
    public NettyServerProperties nettyServerProperties() {
        return new NettyServerProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.storage")
    public StoreProperties storeProperties() {
        return new StoreProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.registry")
    public ServerRegistryProperties registryProperties() {
        return new ServerRegistryProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.recovery")
    public RecoveryProperties recoveryProperties() {
        return new RecoveryProperties();
    }

    @Bean
    public ServerConfig serverConfig(@Autowired ServerRegistryConfig serverRegistryConfig,
                                     @Autowired StoreConfig storeConfig,
                                     @Autowired RecoveryConfig recoveryConfig,
                                     @Autowired NettyServerConfig nettyServerConfig) {
        return new ServerConfig(storeConfig, recoveryConfig, nettyServerConfig, serverRegistryConfig);
    }

    @Bean
    public AggServer aggServer(@Autowired ServerConfig serverConfig) {
        return new AggServer(serverConfig);
    }

    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }
}
