package com.ssitao.code.frame.aggregate.storage;

import com.ssitao.code.frame.aggregate.persistent.redis.JedisCommands;
import com.ssitao.code.frame.aggregate.persistent.redis.RedisCommands;
import com.ssitao.code.frame.aggregate.transaction.serializer.TransactionStoreSerializer;
import com.ssitao.code.frame.aggregate.storage.helper.RedisHelper;
import com.ssitao.code.frame.aggregate.storage.helper.ShardHolder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by changming.xie on 2/24/16.
 * <p/>
 * As the storage of transactionStore need safely durable,make sure the redis server is set as AOF mode and always fsync.
 * set below directives in your redis.conf
 * appendonly yes
 * appendfsync always
 */
public class RedisTransactionStorage extends AbstractRedisTransactionStorage {

    private JedisPool jedisPool;

    public RedisTransactionStorage(TransactionStoreSerializer serializer, StoreConfig storeConfig) {
        super(serializer, storeConfig);
        setJedisPool(storeConfig.getJedisPool());
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    private void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
        isSupportScan = RedisHelper.isSupportScanCommand(jedisPool);
        if (!isSupportScan) {
            throw new UnsupportedOperationException("Redis not support 'scan' command, " +
                    "try update redis version higher than 2.8.0 ");
        }
    }

    @Override
    protected ShardHolder<Jedis> getShardHolder() {

        return new ShardHolder() {

            private List<Jedis> allShards = new ArrayList<>();

            @Override
            public List<Jedis> getAllShards() {
                if (allShards.isEmpty()) {
                    allShards.add(jedisPool.getResource());
                }

                return allShards;
            }

            @Override
            public void close() throws IOException {
                for (Jedis jedis : allShards) {
                    jedis.close();
                }
            }
        };
    }

    @Override
    protected RedisCommands getRedisCommands(byte[] shardKey) {
        return new JedisCommands(jedisPool.getResource());
    }

}
