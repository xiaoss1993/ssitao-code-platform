package com.ssitao.code.frame.aggregate.discovery.registry;

import com.ssitao.code.frame.aggregate.discovery.registry.nacos.NacosRegistryProperties;
import com.ssitao.code.frame.aggregate.discovery.registry.direct.DirectRegistryProperties;
import com.ssitao.code.frame.aggregate.discovery.registry.zookeeper.ZookeeperRegistryProperties;

/**
 * @author Nervose.Wu
 * @date 2022/5/12 18:03
 */
public interface RegistryConfig {

    String getClusterName();

    ZookeeperRegistryProperties getZookeeperRegistryProperties();

    NacosRegistryProperties getNacosRegistryProperties();

    DirectRegistryProperties getDirectRegistryProperties();

    RegistryType getRegistryType();

    String getCustomRegistryName();
}
