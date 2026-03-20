package com.ssitao.code.frame.aggregate.persistent.redis;

import redis.clients.jedis.ShardedJedis;

public interface ShardedJedisCallback<T> {
    public T doInJedis(ShardedJedis jedis);
}
