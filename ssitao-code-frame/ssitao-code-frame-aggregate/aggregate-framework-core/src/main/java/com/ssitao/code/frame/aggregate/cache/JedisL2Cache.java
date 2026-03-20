package com.ssitao.code.frame.aggregate.cache;

import com.ssitao.code.frame.aggregate.entity.AggregateRoot;
import com.ssitao.code.frame.aggregate.persistent.redis.JedisCommands;
import com.ssitao.code.frame.aggregate.persistent.redis.RedisCommands;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;

public class JedisL2Cache<T extends AggregateRoot<ID>, ID extends Serializable> extends AbstractRedisL2Cache<T, ID> {

    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public RedisCommands getRedisCommands() {
        return new JedisCommands(jedisPool.getResource());
    }

}
