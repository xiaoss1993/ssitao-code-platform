package com.ssitao.code.frame.aggregate.properties.store;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;

/**
 * @author Nervose.Wu
 * @date 2022/5/24 19:21
 */
public abstract class AbstractJedisStoreProperties {

    private String password;

    private int soTimeout = 1000;

    private int connectionTimeout = 1000;

    private JedisPoolConfig poolConfig = new JedisPoolConfig();

    protected JedisPoolConfig getJedisPoolConfig() {
        return poolConfig;
    }

    /**
     * 获取适用于 ShardedJedisPool 的配置
     * Jedis 3.x 需要 GenericObjectPoolConfig<ShardedJedis> 类型
     */
    @SuppressWarnings("unchecked")
    protected GenericObjectPoolConfig<ShardedJedis> getShardedJedisPoolConfig() {
        // JedisPoolConfig 继承自 GenericObjectPoolConfig<Jedis>
        // 需要转换为 GenericObjectPoolConfig<ShardedJedis>
        // 由于 JedisPoolConfig 的实现不依赖于具体类型，这里可以安全地转换
        return (GenericObjectPoolConfig<ShardedJedis>) (GenericObjectPoolConfig<?>) poolConfig;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }
}
