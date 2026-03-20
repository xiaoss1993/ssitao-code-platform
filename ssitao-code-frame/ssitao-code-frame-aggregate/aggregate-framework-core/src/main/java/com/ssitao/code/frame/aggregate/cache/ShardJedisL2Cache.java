package com.ssitao.code.frame.aggregate.cache;

import com.ssitao.code.frame.aggregate.entity.AggregateRoot;
import com.ssitao.code.frame.aggregate.persistent.redis.RedisCommands;
import com.ssitao.code.frame.aggregate.persistent.redis.ShardJedisCommands;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Serializable;

/**
 * Created by changming.xie on 9/17/17.
 */
public class ShardJedisL2Cache<T extends AggregateRoot<ID>, ID extends Serializable> extends AbstractRedisL2Cache<T, ID> {

    private ShardedJedisPool shardedJedisPool;

    @Override
    public RedisCommands getRedisCommands() {
        return new ShardJedisCommands(shardedJedisPool.getResource());
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }
}
