package com.ssitao.code.frame.aggregate.storage;

import io.openmessaging.storage.dledger.proxy.DLedgerProxy;
import com.ssitao.code.frame.aggregate.transaction.serializer.SerializerType;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedisPool;

import javax.sql.DataSource;
import java.util.Map;

public interface StoreConfig {

    StorageType getStorageType();

    String getDomain();

    String getTransactionStorageClass();

    long getRequestTimeoutMillis();

    String getLocation();

    JedisPool getJedisPool();

    ShardedJedisPool getShardedJedisPool();

    JedisCluster getJedisCluster();

    DLedgerProxy getDLedgerProxy();

    String getRemoteCluster();

    String getTbSuffix();

    DataSource getDataSource();

    SerializerType getSerializerType();

    String getTransactionSerializerClassName();

    int getKryoPoolSize();

    int getMaxTransactionSize();

    int getMaxAttempts();

    Map<String,Object> getCustomStorageProperties();

}
