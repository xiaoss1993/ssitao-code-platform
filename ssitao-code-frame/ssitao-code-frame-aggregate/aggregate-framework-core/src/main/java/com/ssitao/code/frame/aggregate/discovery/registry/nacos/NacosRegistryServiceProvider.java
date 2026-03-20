package com.ssitao.code.frame.aggregate.discovery.registry.nacos;

import com.ssitao.code.frame.aggregate.discovery.registry.RegistryConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryProvider;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryService;
import com.ssitao.code.frame.aggregate.load.LoadInfo;

/**
 * @author Nervose.Wu
 * @date 2022/5/12 17:29
 */
@LoadInfo(name = "nacos")
public class NacosRegistryServiceProvider implements RegistryProvider {
    @Override
    public RegistryService provide(RegistryConfig registryConfig) {
        return new NacosRegistryServiceImpl(registryConfig);
    }
}
