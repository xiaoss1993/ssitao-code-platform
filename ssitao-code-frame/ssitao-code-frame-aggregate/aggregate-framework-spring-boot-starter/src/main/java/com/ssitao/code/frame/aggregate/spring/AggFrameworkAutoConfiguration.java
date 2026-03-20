package com.ssitao.code.frame.aggregate.spring;


import com.ssitao.code.frame.aggregate.ClientConfig;
import com.ssitao.code.frame.aggregate.properties.RecoveryProperties;
import com.ssitao.code.frame.aggregate.properties.registry.ClientRegistryProperties;
import com.ssitao.code.frame.aggregate.properties.remoting.NettyClientProperties;
import com.ssitao.code.frame.aggregate.properties.store.StoreProperties;
import com.ssitao.code.frame.aggregate.recovery.RecoveryConfig;
import com.ssitao.code.frame.aggregate.remoting.netty.NettyClientConfig;
import com.ssitao.code.frame.aggregate.spring.annotation.EnableSpringIntegration;
import com.ssitao.code.frame.aggregate.storage.StoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Nervose.Wu
 * @date 2022/5/26 11:48
 */
@EnableSpringIntegration
@EnableConfigurationProperties
public class AggFrameworkAutoConfiguration {

    @Bean
    @ConfigurationProperties("spring.agg.remoting")
    public NettyClientProperties nettyClientProperties() {
        return new NettyClientProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.storage")
    public StoreProperties storeProperties() {
        return new StoreProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.registry")
    public ClientRegistryProperties registryProperties() {
        return new ClientRegistryProperties();
    }

    @Bean
    @ConfigurationProperties("spring.agg.recovery")
    public RecoveryProperties recoveryProperties() {
        return new RecoveryProperties();
    }

    @Bean
    public ClientConfig clientConfig(@Autowired ClientRegistryProperties clientRegistryProperties,
                                     @Autowired StoreConfig storeConfig,
                                     @Autowired RecoveryConfig recoveryConfig,
                                     @Autowired NettyClientConfig nettyClientConfig) {
        return new ClientConfig(storeConfig, recoveryConfig, nettyClientConfig, clientRegistryProperties);
    }
}
