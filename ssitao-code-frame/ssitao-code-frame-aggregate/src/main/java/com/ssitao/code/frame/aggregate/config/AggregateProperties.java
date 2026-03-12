package com.ssitao.code.frame.aggregate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 聚合框架属性配置
 * <p>
 * 从 application.yml 中读取 aggregate.framework 配置
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "aggregate.framework")
public class AggregateProperties {

    /**
     * 事件存储配置
     */
    private Event event = new Event();

    /**
     * 事务配置
     */
    private Transaction transaction = new Transaction();

    /**
     * 序列化配置
     */
    private Serialization serialization = new Serialization();

    @Data
    public static class Event {
        /**
         * 事件存储类型：MEMORY, DATABASE, REDIS
         */
        private String type = "DATABASE";

        /**
         * 事件表名
         */
        private String tableName = "aggregate_event";

        /**
         * 是否启用快照
         */
        private Boolean snapshotEnabled = true;

        /**
         * 快照间隔
         */
        private Integer snapshotInterval = 10;
    }

    @Data
    public static class Transaction {
        /**
         * 是否启用分布式事务
         */
        private Boolean enabled = true;

        /**
         * 事务日志表名
         */
        private String transactionLogTable = "aggregate_transaction_log";
    }

    @Data
    public static class Serialization {
        /**
         * 序列化类型：JACKSON, FASTJSON
         */
        private String type = "FASTJSON";
    }
}
