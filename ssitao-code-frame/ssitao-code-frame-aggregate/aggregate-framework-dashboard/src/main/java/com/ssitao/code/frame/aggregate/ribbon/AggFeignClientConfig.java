package com.ssitao.code.frame.aggregate.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.ssitao.code.frame.aggregate.AggClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nervose.Wu
 * @date 2023/2/8 16:48
 */
@Configuration
public class AggFeignClientConfig {

    @Autowired
    private AggClient aggClient;

    @Bean
    ServerList<Server> aggServerList(AggClient aggClient) {
        return new AggServerList<>(aggClient.getRegistryService());
    }
}