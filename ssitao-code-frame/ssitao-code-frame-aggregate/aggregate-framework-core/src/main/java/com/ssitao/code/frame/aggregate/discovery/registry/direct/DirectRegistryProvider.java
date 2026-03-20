package com.ssitao.code.frame.aggregate.discovery.registry.direct;

import com.ssitao.code.frame.aggregate.discovery.registry.RegistryConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryProvider;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryService;
import com.ssitao.code.frame.aggregate.load.LoadInfo;

/**
 * @author Nervose.Wu
 * @date 2022/5/18 17:11
 */
@LoadInfo(name = "direct")
public class DirectRegistryProvider implements RegistryProvider {
    @Override
    public RegistryService provide(RegistryConfig registryConfig) {
        return new DirectRegistryServiceImpl(registryConfig);
    }
}
