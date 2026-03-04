-- ========================================
-- Aggregate Framework 数据库表初始化脚本
-- ========================================

-- ----------------------------
-- 事件存储表
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event`;
CREATE TABLE `aggregate_event` (
    `event_id` VARCHAR(64) NOT NULL COMMENT '事件ID',
    `aggregate_id` VARCHAR(64) NOT NULL COMMENT '聚合根ID',
    `aggregate_type` VARCHAR(128) NOT NULL COMMENT '聚合根类型',
    `event_type` VARCHAR(128) NOT NULL COMMENT '事件类型',
    `event_data` TEXT NOT NULL COMMENT '事件数据（JSON格式）',
    `version` BIGINT NOT NULL COMMENT '版本号',
    `occurred_time` DATETIME NOT NULL COMMENT '事件发生时间',
    `tenant_id` VARCHAR(64) DEFAULT NULL COMMENT '租户ID',
    `source` VARCHAR(128) DEFAULT NULL COMMENT '事件来源',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`event_id`),
    KEY `idx_aggregate_id` (`aggregate_id`),
    KEY `idx_aggregate_type` (`aggregate_type`),
    KEY `idx_event_type` (`event_type`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_occurred_time` (`occurred_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聚合框架-事件存储表';

-- ----------------------------
-- 事件快照表
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_snapshot`;
CREATE TABLE `aggregate_snapshot` (
    `snapshot_id` VARCHAR(64) NOT NULL COMMENT '快照ID',
    `aggregate_id` VARCHAR(64) NOT NULL COMMENT '聚合根ID',
    `aggregate_type` VARCHAR(128) NOT NULL COMMENT '聚合根类型',
    `snapshot_data` MEDIUMTEXT NOT NULL COMMENT '快照数据（JSON格式）',
    `version` BIGINT NOT NULL COMMENT '版本号',
    `tenant_id` VARCHAR(64) DEFAULT NULL COMMENT '租户ID',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`snapshot_id`),
    UNIQUE KEY `uk_aggregate_version` (`aggregate_id`, `version`),
    KEY `idx_aggregate_type` (`aggregate_type`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聚合框架-事件快照表';

-- ----------------------------
-- 事务日志表
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_transaction_log`;
CREATE TABLE `aggregate_transaction_log` (
    `transaction_id` VARCHAR(64) NOT NULL COMMENT '事务ID',
    `aggregate_id` VARCHAR(64) NOT NULL COMMENT '聚合根ID',
    `aggregate_type` VARCHAR(128) NOT NULL COMMENT '聚合根类型',
    `transaction_type` VARCHAR(32) NOT NULL COMMENT '事务类型：CREATE, UPDATE, DELETE',
    `transaction_data` TEXT COMMENT '事务数据（JSON格式）',
    `status` VARCHAR(32) NOT NULL COMMENT '状态：PENDING, COMMITTED, ROLLED_BACK',
    `retry_count` INT NOT NULL DEFAULT 0 COMMENT '重试次数',
    `error_message` TEXT COMMENT '错误信息',
    `tenant_id` VARCHAR(64) DEFAULT NULL COMMENT '租户ID',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`transaction_id`),
    KEY `idx_aggregate_id` (`aggregate_id`),
    KEY `idx_status` (`status`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聚合框架-分布式事务日志表';

-- ----------------------------
-- 事件订阅表（可选）
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event_subscription`;
CREATE TABLE `aggregate_event_subscription` (
    `subscription_id` VARCHAR(64) NOT NULL COMMENT '订阅ID',
    `event_type` VARCHAR(128) NOT NULL COMMENT '事件类型',
    `subscriber_name` VARCHAR(128) NOT NULL COMMENT '订阅者名称',
    `subscriber_type` VARCHAR(32) NOT NULL COMMENT '订阅者类型：HANDLER, EXTERNAL',
    `subscription_status` VARCHAR(32) NOT NULL COMMENT '订阅状态：ACTIVE, INACTIVE',
    `retry_policy` VARCHAR(32) DEFAULT NULL COMMENT '重试策略',
    `max_retry_count` INT DEFAULT 3 COMMENT '最大重试次数',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`subscription_id`),
    KEY `idx_event_type` (`event_type`),
    KEY `idx_subscriber_name` (`subscriber_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聚合框架-事件订阅表';

-- ----------------------------
-- 事件发布记录表（可选）
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event_publication`;
CREATE TABLE `aggregate_event_publication` (
    `publication_id` VARCHAR(64) NOT NULL COMMENT '发布ID',
    `event_id` VARCHAR(64) NOT NULL COMMENT '事件ID',
    `subscription_id` VARCHAR(64) NOT NULL COMMENT '订阅ID',
    `status` VARCHAR(32) NOT NULL COMMENT '状态：PENDING, PUBLISHED, FAILED',
    `retry_count` INT NOT NULL DEFAULT 0 COMMENT '重试次数',
    `error_message` TEXT COMMENT '错误信息',
    `published_time` DATETIME DEFAULT NULL COMMENT '发布时间',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`publication_id`),
    KEY `idx_event_id` (`event_id`),
    KEY `idx_subscription_id` (`subscription_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聚合框架-事件发布记录表';
