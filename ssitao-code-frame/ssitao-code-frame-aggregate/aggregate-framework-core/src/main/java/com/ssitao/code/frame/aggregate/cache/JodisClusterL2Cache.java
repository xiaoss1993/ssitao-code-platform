package com.ssitao.code.frame.aggregate.cache;

import io.codis.jodis.JedisResourcePool;
import com.ssitao.code.frame.aggregate.entity.AggregateRoot;
import com.ssitao.code.frame.aggregate.persistent.redis.JedisCommands;
import com.ssitao.code.frame.aggregate.persistent.redis.RedisCommands;

import java.io.Serializable;

public class JodisClusterL2Cache<T extends AggregateRoot<ID>, ID extends Serializable> extends AbstractRedisL2Cache<T, ID> {

    private JedisResourcePool jedisPool;

    @Override
    public RedisCommands getRedisCommands() {
        return new JedisCommands(jedisPool.getResource());
    }

    public void setJedisPool(JedisResourcePool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
