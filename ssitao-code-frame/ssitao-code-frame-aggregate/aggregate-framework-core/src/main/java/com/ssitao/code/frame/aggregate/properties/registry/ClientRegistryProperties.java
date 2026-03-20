package com.ssitao.code.frame.aggregate.properties.registry;

import com.ssitao.code.frame.aggregate.discovery.loadbalance.LoadBalanceType;
import com.ssitao.code.frame.aggregate.discovery.registry.ClientRegistryConfig;
import com.ssitao.code.frame.aggregate.discovery.registry.RegistryRole;

/**
 * @author Nervose.Wu
 * @date 2022/7/7 17:38
 */
public class ClientRegistryProperties extends RegistryProperties implements ClientRegistryConfig {

    private String loadBalanceType = LoadBalanceType.RoundRobin.name();

    private RegistryRole registryRole = RegistryRole.CLIENT;

    @Override
    public RegistryRole getRegistryRole() {
        return registryRole;
    }

    public void setRegistryRole(RegistryRole registryRole) {
        this.registryRole = registryRole;
    }

    @Override
    public String getLoadBalanceType() {
        return loadBalanceType;
    }

    public void setLoadBalanceType(String loadBalanceType) {
        this.loadBalanceType = loadBalanceType;
    }
}
