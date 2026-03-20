package com.ssitao.code.frame.aggregate.discovery.registry.zookeeper;

import com.ssitao.code.frame.aggregate.discovery.registry.RegistryConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryProvider;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryService;
import com.ssitao.code.frame.aggregate.load.LoadInfo;

/**
 * @author Nervose.Wu
 * @date 2022/5/12 17:27
 */
@LoadInfo(name = "zookeeper")
public class ZookeeperRegistryProvider implements RegistryProvider {
    @Override
    public RegistryService provide(RegistryConfig registryConfig) {
        return new ZookeeperRegistryServiceImpl(registryConfig);
    }
}
