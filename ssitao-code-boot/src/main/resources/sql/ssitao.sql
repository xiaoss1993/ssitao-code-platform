/*
 Navicat Premium Dump SQL

 Source Server         : DEV_MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023 (8.0.23)
 Source Host           : localhost:3306
 Source Schema         : ssitao

 Target Server Type    : MySQL
 Target Server Version : 80023 (8.0.23)
 File Encoding         : 65001

 Date: 09/03/2026 17:22:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aggregate_event
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event`;
CREATE TABLE `aggregate_event` (
  `event_id` varchar(64) NOT NULL COMMENT '事件ID',
  `aggregate_id` varchar(64) NOT NULL COMMENT '聚合根ID',
  `aggregate_type` varchar(128) NOT NULL COMMENT '聚合根类型',
  `event_type` varchar(128) NOT NULL COMMENT '事件类型',
  `event_data` text NOT NULL COMMENT '事件数据（JSON格式）',
  `version` bigint NOT NULL COMMENT '版本号',
  `occurred_time` datetime NOT NULL COMMENT '事件发生时间',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  `source` varchar(128) DEFAULT NULL COMMENT '事件来源',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`event_id`),
  KEY `idx_aggregate_id` (`aggregate_id`),
  KEY `idx_aggregate_type` (`aggregate_type`),
  KEY `idx_event_type` (`event_type`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_occurred_time` (`occurred_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聚合框架-事件存储表';

-- ----------------------------
-- Table structure for aggregate_event_publication
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event_publication`;
CREATE TABLE `aggregate_event_publication` (
  `publication_id` varchar(64) NOT NULL COMMENT '发布ID',
  `event_id` varchar(64) NOT NULL COMMENT '事件ID',
  `subscription_id` varchar(64) NOT NULL COMMENT '订阅ID',
  `status` varchar(32) NOT NULL COMMENT '状态：PENDING, PUBLISHED, FAILED',
  `retry_count` int NOT NULL DEFAULT '0' COMMENT '重试次数',
  `error_message` text COMMENT '错误信息',
  `published_time` datetime DEFAULT NULL COMMENT '发布时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`publication_id`),
  KEY `idx_event_id` (`event_id`),
  KEY `idx_subscription_id` (`subscription_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聚合框架-事件发布记录表';

-- ----------------------------
-- Table structure for aggregate_event_subscription
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_event_subscription`;
CREATE TABLE `aggregate_event_subscription` (
  `subscription_id` varchar(64) NOT NULL COMMENT '订阅ID',
  `event_type` varchar(128) NOT NULL COMMENT '事件类型',
  `subscriber_name` varchar(128) NOT NULL COMMENT '订阅者名称',
  `subscriber_type` varchar(32) NOT NULL COMMENT '订阅者类型：HANDLER, EXTERNAL',
  `subscription_status` varchar(32) NOT NULL COMMENT '订阅状态：ACTIVE, INACTIVE',
  `retry_policy` varchar(32) DEFAULT NULL COMMENT '重试策略',
  `max_retry_count` int DEFAULT '3' COMMENT '最大重试次数',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`subscription_id`),
  KEY `idx_event_type` (`event_type`),
  KEY `idx_subscriber_name` (`subscriber_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聚合框架-事件订阅表';

-- ----------------------------
-- Table structure for aggregate_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_snapshot`;
CREATE TABLE `aggregate_snapshot` (
  `snapshot_id` varchar(64) NOT NULL COMMENT '快照ID',
  `aggregate_id` varchar(64) NOT NULL COMMENT '聚合根ID',
  `aggregate_type` varchar(128) NOT NULL COMMENT '聚合根类型',
  `snapshot_data` mediumtext NOT NULL COMMENT '快照数据（JSON格式）',
  `version` bigint NOT NULL COMMENT '版本号',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`snapshot_id`),
  UNIQUE KEY `uk_aggregate_version` (`aggregate_id`,`version`),
  KEY `idx_aggregate_type` (`aggregate_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聚合框架-事件快照表';

-- ----------------------------
-- Table structure for aggregate_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `aggregate_transaction_log`;
CREATE TABLE `aggregate_transaction_log` (
  `transaction_id` varchar(64) NOT NULL COMMENT '事务ID',
  `aggregate_id` varchar(64) NOT NULL COMMENT '聚合根ID',
  `aggregate_type` varchar(128) NOT NULL COMMENT '聚合根类型',
  `transaction_type` varchar(32) NOT NULL COMMENT '事务类型：CREATE, UPDATE, DELETE',
  `transaction_data` text COMMENT '事务数据（JSON格式）',
  `status` varchar(32) NOT NULL COMMENT '状态：PENDING, COMMITTED, ROLLED_BACK',
  `retry_count` int NOT NULL DEFAULT '0' COMMENT '重试次数',
  `error_message` text COMMENT '错误信息',
  `tenant_id` varchar(64) DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`transaction_id`),
  KEY `idx_aggregate_id` (`aggregate_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聚合框架-分布式事务日志表';

-- ----------------------------
-- Table structure for core_association_field
-- ----------------------------
DROP TABLE IF EXISTS `core_association_field`;
CREATE TABLE `core_association_field` (
  `assoc_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联ID',
  `main_func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主功能ID',
  `sub_func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '子功能ID',
  `main_field_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主字段编码',
  `main_field_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '主字段名称',
  `sub_field_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '子字段编码',
  `sub_field_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '子字段名称',
  `assoc_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ONE_TO_MANY' COMMENT '关联类型: ONE_TO_ONE-一对一, ONE_TO_MANY-一对多, MANY_TO_MANY-多对多',
  `assoc_condition` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联条件',
  `assoc_cascade` tinyint(1) DEFAULT '0' COMMENT '是否级联: 0-否, 1-是',
  `assoc_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`assoc_id`),
  KEY `idx_main_func` (`main_func_id`),
  KEY `idx_sub_func` (`sub_func_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='关联字段表';

-- ----------------------------
-- Table structure for core_code_sequence
-- ----------------------------
DROP TABLE IF EXISTS `core_code_sequence`;
CREATE TABLE `core_code_sequence` (
  `sequence_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列ID',
  `sequence_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列编码',
  `sequence_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列名称',
  `sequence_prefix` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '前缀',
  `sequence_current_value` int DEFAULT '0' COMMENT '当前值',
  `sequence_step` int DEFAULT '1' COMMENT '步长',
  `sequence_min_value` int DEFAULT '1' COMMENT '最小值',
  `sequence_max_value` int DEFAULT '999999' COMMENT '最大值',
  `sequence_reset_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'NEVER' COMMENT '重置类型',
  `sequence_last_reset_time` datetime DEFAULT NULL COMMENT '最后重置时间',
  `sequence_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '序列描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`sequence_id`),
  UNIQUE KEY `uk_sequence_code` (`sequence_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='代码生成序列表';

-- ----------------------------
-- Table structure for core_comm_js
-- ----------------------------
DROP TABLE IF EXISTS `core_comm_js`;
CREATE TABLE `core_comm_js` (
  `js_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '脚本ID',
  `js_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '脚本编码',
  `js_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '脚本名称',
  `js_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FUNCTION' COMMENT '脚本类型: FUNCTION-函数, CLASS-类, OBJECT-对象',
  `js_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '脚本内容',
  `js_params` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '参数说明',
  `js_return` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '返回值说明',
  `js_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '脚本分类',
  `js_status` tinyint(1) DEFAULT '1' COMMENT '脚本状态: 0-停用, 1-启用',
  `js_is_init` tinyint(1) DEFAULT '0' COMMENT '是否初始化执行: 0-否, 1-是',
  `js_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '脚本描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`js_id`),
  UNIQUE KEY `uk_js_code` (`js_code`,`tenant_id`,`is_deleted`),
  KEY `idx_js_name` (`js_name`),
  KEY `idx_js_category` (`js_category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公共JS脚本表';

-- ----------------------------
-- Table structure for core_config
-- ----------------------------
DROP TABLE IF EXISTS `core_config`;
CREATE TABLE `core_config` (
  `config_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `config_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置编码',
  `config_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'STRING' COMMENT '配置类型: STRING-字符串, NUMBER-数字, BOOLEAN-布尔, JSON-JSON',
  `config_category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置分类',
  `config_status` tinyint(1) DEFAULT '1' COMMENT '配置状态: 0-停用, 1-启用',
  `config_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `config_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_config_code` (`config_code`,`tenant_id`,`is_deleted`),
  KEY `idx_config_name` (`config_name`),
  KEY `idx_config_category` (`config_category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统配置表';

-- ----------------------------
-- Table structure for core_data_flow
-- ----------------------------
DROP TABLE IF EXISTS `core_data_flow`;
CREATE TABLE `core_data_flow` (
  `flow_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流转ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `flow_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流转编码',
  `flow_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流转名称',
  `flow_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流转类型: INSERT-插入, UPDATE-更新, DELETE-删除',
  `flow_condition` text COLLATE utf8mb4_general_ci COMMENT '流转条件',
  `flow_sql` text COLLATE utf8mb4_general_ci COMMENT '流转SQL',
  `flow_callback` text COLLATE utf8mb4_general_ci COMMENT '回调配置',
  `flow_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用: 0-否, 1-是',
  `flow_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流转描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`flow_id`),
  UNIQUE KEY `uk_func_flow` (`func_id`,`flow_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据流转表';

-- ----------------------------
-- Table structure for core_develop_log
-- ----------------------------
DROP TABLE IF EXISTS `core_develop_log`;
CREATE TABLE `core_develop_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `log_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型: DEVELOP-开发, TEST-测试, DEPLOY-部署',
  `log_title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志标题',
  `log_content` text COLLATE utf8mb4_general_ci COMMENT '日志内容',
  `log_level` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'INFO' COMMENT '日志级别: DEBUG-调试, INFO-信息, WARN-警告, ERROR-错误',
  `log_module` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名称',
  `log_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `log_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_module` (`log_module`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='开发日志表';

-- ----------------------------
-- Table structure for core_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary`;
CREATE TABLE `core_dictionary` (
  `dict_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `dict_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '字典类型: SYSTEM-系统, BUSINESS-业务',
  `dict_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'CUSTOM' COMMENT '字典来源: CUSTOM-自定义, SQL-SQL, API-接口',
  `dict_sql` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '字典SQL',
  `dict_api` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典API',
  `dict_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '字典配置(JSON)',
  `dict_status` tinyint(1) DEFAULT '1' COMMENT '字典状态: 0-停用, 1-启用',
  `dict_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `dict_sort` int DEFAULT '0' COMMENT '排序号',
  `dict_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典描述',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `uk_dict_code` (`dict_code`,`tenant_id`,`is_deleted`),
  KEY `idx_dict_name` (`dict_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- Table structure for core_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary_item`;
CREATE TABLE `core_dictionary_item` (
  `item_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项ID',
  `dict_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `item_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项编码',
  `item_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `item_value` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项值',
  `item_parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父项ID',
  `item_level` int DEFAULT '1' COMMENT '项层级',
  `item_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项路径',
  `item_icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项图标',
  `item_color` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项颜色',
  `item_css_class` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'CSS样式',
  `item_status` tinyint(1) DEFAULT '1' COMMENT '项状态: 0-停用, 1-启用',
  `item_is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认: 0-否, 1-是',
  `item_sort` int DEFAULT '0' COMMENT '排序号',
  `item_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项描述',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `uk_dict_item` (`dict_id`,`item_code`,`is_deleted`),
  KEY `idx_item_code` (`item_code`),
  KEY `idx_item_value` (`item_value`),
  KEY `idx_parent_id` (`item_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典项表';

-- ----------------------------
-- Table structure for core_dictionary_log
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary_log`;
CREATE TABLE `core_dictionary_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `dict_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典ID',
  `dict_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典名称',
  `log_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型: CREATE-创建, UPDATE-更新, DELETE-删除',
  `log_data` text COLLATE utf8mb4_general_ci COMMENT '日志数据(JSON)',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `log_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `log_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_dict_id` (`dict_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典日志表';

-- ----------------------------
-- Table structure for core_exception
-- ----------------------------
DROP TABLE IF EXISTS `core_exception`;
CREATE TABLE `core_exception` (
  `exception_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '异常ID',
  `exception_code` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常编码',
  `exception_message` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常消息',
  `exception_stack` text COLLATE utf8mb4_general_ci COMMENT '异常栈',
  `exception_params` text COLLATE utf8mb4_general_ci COMMENT '异常参数',
  `exception_request_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `exception_request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `exception_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `exception_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `exception_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `exception_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '异常时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`exception_id`),
  KEY `idx_exception_code` (`exception_code`),
  KEY `idx_exception_time` (`exception_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='异常表';

-- ----------------------------
-- Table structure for core_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `core_exception_log`;
CREATE TABLE `core_exception_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `log_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型: BUSINESS-业务, SYSTEM-系统',
  `log_code` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常编码',
  `log_message` varchar(1000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常消息',
  `log_stack` text COLLATE utf8mb4_general_ci COMMENT '异常栈',
  `log_params` varchar(4000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '异常参数',
  `log_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `log_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `log_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_code` (`log_code`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='异常日志表';

-- ----------------------------
-- Table structure for core_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `core_execution_log`;
CREATE TABLE `core_execution_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `log_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型: REQUEST-请求, EXECUTE-执行',
  `log_title` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志标题',
  `log_method` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `log_params` text COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `log_result` text COLLATE utf8mb4_general_ci COMMENT '执行结果',
  `log_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SUCCESS' COMMENT '执行状态',
  `log_error` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `log_duration` int DEFAULT NULL COMMENT '执行时长(毫秒)',
  `log_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `log_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `log_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_user` (`log_user_id`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='执行日志表';

-- ----------------------------
-- Table structure for core_func_config
-- ----------------------------
DROP TABLE IF EXISTS `core_func_config`;
CREATE TABLE `core_func_config` (
  `config_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `config_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置类型: BUSINESS-业务, EVENT-事件, VALIDATION-验证',
  `config_data` text COLLATE utf8mb4_general_ci COMMENT '配置数据(JSON)',
  `config_script` text COLLATE utf8mb4_general_ci COMMENT '配置脚本',
  `config_table` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联表名',
  `config_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`config_id`),
  KEY `idx_func_id` (`func_id`),
  KEY `idx_config_type` (`config_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能配置表';

-- ----------------------------
-- Table structure for core_func_edit
-- ----------------------------
DROP TABLE IF EXISTS `core_func_edit`;
CREATE TABLE `core_func_edit` (
  `edit_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '编辑ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `edit_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '编辑类型: FORM-表单, LIST-列表, DETAIL-详情',
  `edit_config` text COLLATE utf8mb4_general_ci COMMENT '编辑配置(JSON)',
  `edit_template` text COLLATE utf8mb4_general_ci COMMENT '编辑模板',
  `edit_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编辑描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`edit_id`),
  UNIQUE KEY `uk_func_edit` (`func_id`,`edit_type`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能编辑表';

-- ----------------------------
-- Table structure for core_func_info
-- ----------------------------
DROP TABLE IF EXISTS `core_func_info`;
CREATE TABLE `core_func_info` (
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `func_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能编码',
  `func_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能名称',
  `func_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'CUSTOM' COMMENT '功能类型: SYSTEM-系统, CUSTOM-自定义',
  `func_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能分类',
  `func_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能路径',
  `func_component` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `func_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能图标',
  `func_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '功能描述',
  `func_is_public` tinyint(1) DEFAULT '0' COMMENT '是否公开: 0-否, 1-是',
  `func_status` tinyint(1) DEFAULT '1' COMMENT '功能状态: 0-停用, 1-启用',
  `func_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`func_id`),
  UNIQUE KEY `uk_func_code` (`func_code`,`tenant_id`,`is_deleted`),
  KEY `idx_func_name` (`func_name`),
  KEY `idx_func_type` (`func_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能信息表';

-- ----------------------------
-- Table structure for core_func_perm
-- ----------------------------
DROP TABLE IF EXISTS `core_func_perm`;
CREATE TABLE `core_func_perm` (
  `perm_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `perm_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
  `perm_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `perm_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUTTON' COMMENT '权限类型: BUTTON-按钮, API-接口, DATA-数据',
  `perm_resource` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源标识',
  `perm_action` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作类型',
  `perm_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限描述',
  `perm_status` tinyint(1) DEFAULT '1' COMMENT '权限状态: 0-停用, 1-启用',
  `perm_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `uk_func_perm` (`func_id`,`perm_code`,`is_deleted`),
  KEY `idx_perm_code` (`perm_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能权限表';

-- ----------------------------
-- Table structure for core_func_perm_config
-- ----------------------------
DROP TABLE IF EXISTS `core_func_perm_config`;
CREATE TABLE `core_func_perm_config` (
  `config_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `config_key` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置键',
  `config_value` text COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'STRING' COMMENT '配置类型: STRING-字符串, NUMBER-数字, BOOLEAN-布尔, JSON-JSON',
  `config_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_func_config` (`func_id`,`config_key`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能权限配置表';

-- ----------------------------
-- Table structure for core_func_relation
-- ----------------------------
DROP TABLE IF EXISTS `core_func_relation`;
CREATE TABLE `core_func_relation` (
  `relation_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '关系ID',
  `main_func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主功能ID',
  `sub_func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '子功能ID',
  `relation_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PARENT_CHILD' COMMENT '关系类型: PARENT_CHILD-父子, REFERENCE-引用',
  `relation_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关系描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`relation_id`),
  UNIQUE KEY `uk_func_relation` (`main_func_id`,`sub_func_id`,`relation_type`,`is_deleted`),
  KEY `idx_main_func` (`main_func_id`),
  KEY `idx_sub_func` (`sub_func_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能关系表';

-- ----------------------------
-- Table structure for core_func_relyon
-- ----------------------------
DROP TABLE IF EXISTS `core_func_relyon`;
CREATE TABLE `core_func_relyon` (
  `rely_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '依赖ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `rely_func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '依赖功能ID',
  `rely_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'REQUIRED' COMMENT '依赖类型: REQUIRED-必需, OPTIONAL-可选',
  `rely_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '依赖描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`rely_id`),
  UNIQUE KEY `uk_func_rely` (`func_id`,`rely_func_id`,`is_deleted`),
  KEY `idx_rely_func` (`rely_func_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='功能依赖表';

-- ----------------------------
-- Table structure for core_group_query
-- ----------------------------
DROP TABLE IF EXISTS `core_group_query`;
CREATE TABLE `core_group_query` (
  `group_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `group_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组编码',
  `group_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `group_field` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组字段',
  `group_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SIMPLE' COMMENT '分组类型: SIMPLE-简单, COMPLEX-复杂',
  `group_config` text COLLATE utf8mb4_general_ci COMMENT '分组配置(JSON)',
  `group_sql` text COLLATE utf8mb4_general_ci COMMENT '分组SQL',
  `group_sort` int DEFAULT '0' COMMENT '排序号',
  `group_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分组描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `uk_func_group` (`func_id`,`group_code`,`is_deleted`),
  KEY `idx_group_field` (`group_field`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分组查询表';

-- ----------------------------
-- Table structure for core_help_msg
-- ----------------------------
DROP TABLE IF EXISTS `core_help_msg`;
CREATE TABLE `core_help_msg` (
  `help_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '帮助ID',
  `help_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '帮助编码',
  `help_title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '帮助标题',
  `help_content` text COLLATE utf8mb4_general_ci COMMENT '帮助内容',
  `help_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'GUIDE' COMMENT '帮助类型: GUIDE-指南, FAQ-常见问题, VIDEO-视频',
  `help_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帮助分类',
  `help_sort` int DEFAULT '0' COMMENT '排序号',
  `help_status` tinyint(1) DEFAULT '1' COMMENT '帮助状态: 0-停用, 1-启用',
  `help_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帮助描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`help_id`),
  UNIQUE KEY `uk_help_code` (`help_code`,`tenant_id`,`is_deleted`),
  KEY `idx_help_category` (`help_category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='帮助信息表';

-- ----------------------------
-- Table structure for core_java_js_library
-- ----------------------------
DROP TABLE IF EXISTS `core_java_js_library`;
CREATE TABLE `core_java_js_library` (
  `library_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '库ID',
  `library_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '库编码',
  `library_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '库名称',
  `library_version` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '库版本',
  `library_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FRONTEND' COMMENT '库类型: FRONTEND-前端, BACKEND-后端',
  `library_content` text COLLATE utf8mb4_general_ci COMMENT '库内容',
  `library_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '库URL',
  `library_status` tinyint(1) DEFAULT '1' COMMENT '库状态: 0-停用, 1-启用',
  `library_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '库描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`library_id`),
  UNIQUE KEY `uk_library_code` (`library_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Java JS库表';

-- ----------------------------
-- Table structure for core_je_api
-- ----------------------------
DROP TABLE IF EXISTS `core_je_api`;
CREATE TABLE `core_je_api` (
  `api_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API ID',
  `api_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API编码',
  `api_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API名称',
  `api_path` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API路径',
  `api_method` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
  `api_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'REST' COMMENT 'API类型: REST-RESTful, RPC-RPC',
  `api_params` text COLLATE utf8mb4_general_ci COMMENT '参数说明',
  `api_return` text COLLATE utf8mb4_general_ci COMMENT '返回说明',
  `api_status` tinyint(1) DEFAULT '1' COMMENT 'API状态: 0-停用, 1-启用',
  `api_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'API描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`api_id`),
  UNIQUE KEY `uk_api_code` (`api_code`,`tenant_id`,`is_deleted`),
  KEY `idx_api_path` (`api_path`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='JE API表';

-- ----------------------------
-- Table structure for core_je_app_api
-- ----------------------------
DROP TABLE IF EXISTS `core_je_app_api`;
CREATE TABLE `core_je_app_api` (
  `api_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API ID',
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `api_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API编码',
  `api_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API名称',
  `api_path` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'API路径',
  `api_method` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '请求方式',
  `api_params` text COLLATE utf8mb4_general_ci COMMENT '参数说明',
  `api_status` tinyint(1) DEFAULT '1' COMMENT 'API状态: 0-停用, 1-启用',
  `api_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'API描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`api_id`),
  UNIQUE KEY `uk_app_api` (`app_id`,`api_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='JE应用API表';

-- ----------------------------
-- Table structure for core_kj_menu
-- ----------------------------
DROP TABLE IF EXISTS `core_kj_menu`;
CREATE TABLE `core_kj_menu` (
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LINK' COMMENT '菜单类型: LINK-链接, FUNCTION-功能',
  `menu_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单路径',
  `menu_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int DEFAULT '0' COMMENT '排序号',
  `menu_status` tinyint(1) DEFAULT '1' COMMENT '菜单状态: 0-停用, 1-启用',
  `menu_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `uk_menu_code` (`menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='快捷菜单表';

-- ----------------------------
-- Table structure for core_mark
-- ----------------------------
DROP TABLE IF EXISTS `core_mark`;
CREATE TABLE `core_mark` (
  `mark_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '标记ID',
  `mark_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '标记类型',
  `mark_target_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '目标ID',
  `mark_user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `mark_content` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标记内容',
  `mark_color` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标记颜色',
  `mark_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`mark_id`),
  UNIQUE KEY `uk_mark_target` (`mark_type`,`mark_target_id`,`mark_user_id`),
  KEY `idx_mark_user` (`mark_user_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='标记表';

-- ----------------------------
-- Table structure for core_menu
-- ----------------------------
DROP TABLE IF EXISTS `core_menu`;
CREATE TABLE `core_menu` (
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单类型: DIRECTORY-目录, MENU-菜单, BUTTON-按钮',
  `menu_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父菜单ID',
  `menu_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由路径',
  `menu_component` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `menu_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int DEFAULT '0' COMMENT '排序号',
  `menu_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `menu_status` tinyint(1) DEFAULT '1' COMMENT '菜单状态: 0-停用, 1-启用',
  `menu_permission` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `menu_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `uk_menu_code` (`menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_menu_name` (`menu_name`),
  KEY `idx_parent_id` (`menu_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='核心菜单表';

-- ----------------------------
-- Table structure for core_notice
-- ----------------------------
DROP TABLE IF EXISTS `core_notice`;
CREATE TABLE `core_notice` (
  `notice_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知ID',
  `notice_title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '通知标题',
  `notice_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'NOTICE' COMMENT '通知类型: NOTICE-通知, ANNOUNCEMENT-公告',
  `notice_content` text COLLATE utf8mb4_general_ci COMMENT '通知内容',
  `notice_level` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'NORMAL' COMMENT '通知级别: NORMAL-普通, IMPORTANT-重要, URGENT-紧急',
  `notice_status` tinyint(1) DEFAULT '1' COMMENT '通知状态: 0-停用, 1-启用',
  `notice_publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `notice_publisher_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发布人ID',
  `notice_publisher_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发布人姓名',
  `notice_target_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ALL' COMMENT '目标类型',
  `notice_target_ids` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标ID列表',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`notice_id`),
  KEY `idx_notice_type` (`notice_type`),
  KEY `idx_publish_time` (`notice_publish_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='通知表';

-- ----------------------------
-- Table structure for core_page_nicked
-- ----------------------------
DROP TABLE IF EXISTS `core_page_nicked`;
CREATE TABLE `core_page_nicked` (
  `nick_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '标记ID',
  `page_path` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面路径',
  `page_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面名称',
  `nick_config` text COLLATE utf8mb4_general_ci COMMENT '标记配置(JSON)',
  `nick_user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `nick_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '标记时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`nick_id`),
  UNIQUE KEY `uk_page_user` (`page_path`,`nick_user_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='页面标记表';

-- ----------------------------
-- Table structure for core_qj_css
-- ----------------------------
DROP TABLE IF EXISTS `core_qj_css`;
CREATE TABLE `core_qj_css` (
  `css_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'CSS ID',
  `css_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'CSS编码',
  `css_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'CSS名称',
  `css_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'CSS内容',
  `css_status` tinyint(1) DEFAULT '1' COMMENT 'CSS状态: 0-停用, 1-启用',
  `css_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'CSS描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`css_id`),
  UNIQUE KEY `uk_css_code` (`css_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='区间CSS表';

-- ----------------------------
-- Table structure for core_qj_jbk
-- ----------------------------
DROP TABLE IF EXISTS `core_qj_jbk`;
CREATE TABLE `core_qj_jbk` (
  `jbk_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '背景ID',
  `jbk_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '背景编码',
  `jbk_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '背景名称',
  `jbk_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'COLOR' COMMENT '背景类型: COLOR-颜色, IMAGE-图片',
  `jbk_value` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '背景值',
  `jbk_status` tinyint(1) DEFAULT '1' COMMENT '背景状态: 0-停用, 1-启用',
  `jbk_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '背景描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`jbk_id`),
  UNIQUE KEY `uk_jbk_code` (`jbk_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='区间背景表';

-- ----------------------------
-- Table structure for core_qj_sql
-- ----------------------------
DROP TABLE IF EXISTS `core_qj_sql`;
CREATE TABLE `core_qj_sql` (
  `sql_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SQL ID',
  `sql_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SQL编码',
  `sql_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SQL名称',
  `sql_content` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SQL内容',
  `sql_params` text COLLATE utf8mb4_general_ci COMMENT '参数说明',
  `sql_status` tinyint(1) DEFAULT '1' COMMENT 'SQL状态: 0-停用, 1-启用',
  `sql_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'SQL描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`sql_id`),
  UNIQUE KEY `uk_sql_code` (`sql_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='区间SQL表';

-- ----------------------------
-- Table structure for core_query_strategy
-- ----------------------------
DROP TABLE IF EXISTS `core_query_strategy`;
CREATE TABLE `core_query_strategy` (
  `strategy_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '策略ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `strategy_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '策略编码',
  `strategy_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '策略名称',
  `strategy_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'QUERY' COMMENT '策略类型: QUERY-查询, EXPORT-导出',
  `strategy_config` text COLLATE utf8mb4_general_ci COMMENT '策略配置(JSON)',
  `strategy_sql` text COLLATE utf8mb4_general_ci COMMENT '策略SQL',
  `strategy_params` text COLLATE utf8mb4_general_ci COMMENT '参数配置(JSON)',
  `strategy_is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认: 0-否, 1-是',
  `strategy_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '策略描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`strategy_id`),
  UNIQUE KEY `uk_func_strategy` (`func_id`,`strategy_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='查询策略表';

-- ----------------------------
-- Table structure for core_resource_button
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_button`;
CREATE TABLE `core_resource_button` (
  `button_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '按钮ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `button_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '按钮编码',
  `button_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '按钮名称',
  `button_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUTTON' COMMENT '按钮类型: BUTTON-按钮, LINK-链接, DROPDOWN-下拉',
  `button_style` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'DEFAULT' COMMENT '按钮样式: DEFAULT-默认, PRIMARY-主要, SUCCESS-成功, WARNING-警告, DANGER-危险',
  `button_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '按钮图标',
  `button_action` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '按钮动作',
  `button_confirm` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '确认提示',
  `button_permission` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `button_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `button_disabled` tinyint(1) DEFAULT '0' COMMENT '是否禁用: 0-否, 1-是',
  `button_sort` int DEFAULT '0' COMMENT '排序号',
  `button_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '按钮描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`button_id`),
  UNIQUE KEY `uk_func_button` (`func_id`,`button_code`,`is_deleted`),
  KEY `idx_button_code` (`button_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源按钮表';

-- ----------------------------
-- Table structure for core_resource_column
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_column`;
CREATE TABLE `core_resource_column` (
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `column_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列编码',
  `column_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列名称',
  `column_field` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列字段',
  `column_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'TEXT' COMMENT '列类型: TEXT-文本, NUMBER-数字, DATE-日期, IMAGE-图片, LINK-链接',
  `column_width` int DEFAULT NULL COMMENT '列宽度(px)',
  `column_align` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LEFT' COMMENT '对齐方式: LEFT-左, CENTER-中, RIGHT-右',
  `column_fixed` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固定: LEFT-左固定, RIGHT-右固定',
  `column_sortable` tinyint(1) DEFAULT '0' COMMENT '是否可排序: 0-否, 1-是',
  `column_filterable` tinyint(1) DEFAULT '0' COMMENT '是否可过滤: 0-否, 1-是',
  `column_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `column_format` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '格式化',
  `column_dict_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `column_sort` int DEFAULT '0' COMMENT '排序号',
  `column_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`column_id`),
  UNIQUE KEY `uk_func_column` (`func_id`,`column_code`,`is_deleted`),
  KEY `idx_column_field` (`column_field`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源列表表';

-- ----------------------------
-- Table structure for core_resource_column_plan
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_column_plan`;
CREATE TABLE `core_resource_column_plan` (
  `plan_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划ID',
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列ID',
  `plan_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划编码',
  `plan_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划名称',
  `plan_config` text COLLATE utf8mb4_general_ci COMMENT '计划配置(JSON)',
  `plan_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_column_plan` (`column_id`,`plan_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源列计划表';

-- ----------------------------
-- Table structure for core_resource_column_print_plan
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_column_print_plan`;
CREATE TABLE `core_resource_column_print_plan` (
  `plan_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划ID',
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列ID',
  `plan_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划编码',
  `plan_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划名称',
  `print_config` text COLLATE utf8mb4_general_ci COMMENT '打印配置(JSON)',
  `plan_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_column_print_plan` (`column_id`,`plan_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源列打印计划表';

-- ----------------------------
-- Table structure for core_resource_field
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_field`;
CREATE TABLE `core_resource_field` (
  `field_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `field_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段编码',
  `field_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `field_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'TEXT' COMMENT '字段类型: TEXT-文本, NUMBER-数字, DATE-日期, SELECT-下拉等',
  `field_length` int DEFAULT NULL COMMENT '字段长度',
  `field_decimal` int DEFAULT NULL COMMENT '小数位数',
  `field_required` tinyint(1) DEFAULT '0' COMMENT '是否必填: 0-否, 1-是',
  `field_readonly` tinyint(1) DEFAULT '0' COMMENT '是否只读: 0-否, 1-是',
  `field_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `field_default_value` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `field_placeholder` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '占位符',
  `field_dict_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `field_options` text COLLATE utf8mb4_general_ci COMMENT '选项配置(JSON)',
  `field_validation` text COLLATE utf8mb4_general_ci COMMENT '验证规则(JSON)',
  `field_events` text COLLATE utf8mb4_general_ci COMMENT '字段事件(JSON)',
  `field_sort` int DEFAULT '0' COMMENT '排序号',
  `field_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`field_id`),
  UNIQUE KEY `uk_func_field` (`func_id`,`field_code`,`is_deleted`),
  KEY `idx_field_code` (`field_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源字段表';

-- ----------------------------
-- Table structure for core_resource_field_plan
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_field_plan`;
CREATE TABLE `core_resource_field_plan` (
  `plan_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划ID',
  `field_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `plan_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划编码',
  `plan_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划名称',
  `plan_config` text COLLATE utf8mb4_general_ci COMMENT '计划配置(JSON)',
  `plan_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_field_plan` (`field_id`,`plan_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源字段计划表';

-- ----------------------------
-- Table structure for core_resource_field_print_plan
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_field_print_plan`;
CREATE TABLE `core_resource_field_print_plan` (
  `plan_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划ID',
  `field_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `plan_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划编码',
  `plan_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划名称',
  `print_config` text COLLATE utf8mb4_general_ci COMMENT '打印配置(JSON)',
  `plan_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_field_print_plan` (`field_id`,`plan_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源字段打印计划表';

-- ----------------------------
-- Table structure for core_resource_table
-- ----------------------------
DROP TABLE IF EXISTS `core_resource_table`;
CREATE TABLE `core_resource_table` (
  `table_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `table_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
  `table_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'GRID' COMMENT '表类型: GRID-网格, TREE-树形, CARD-卡片',
  `table_data_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据URL',
  `table_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'POST' COMMENT '请求方式',
  `table_pagination` tinyint(1) DEFAULT '1' COMMENT '是否分页: 0-否, 1-是',
  `table_page_size` int DEFAULT '20' COMMENT '每页数量',
  `table_row_key` varchar(64) COLLATE utf8mb4_general_ci DEFAULT 'id' COMMENT '行主键',
  `table_expand` tinyint(1) DEFAULT '0' COMMENT '是否可展开: 0-否, 1-是',
  `table_selection` tinyint(1) DEFAULT '0' COMMENT '是否可选: 0-否, 1-是',
  `table_border` tinyint(1) DEFAULT '1' COMMENT '是否边框: 0-否, 1-是',
  `table stripe` tinyint(1) DEFAULT '1' COMMENT '是否斑马纹: 0-否, 1-是',
  `table_config` text COLLATE utf8mb4_general_ci COMMENT '表配置(JSON)',
  `table_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`table_id`),
  UNIQUE KEY `uk_func_table` (`func_id`,`table_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资源表表';

-- ----------------------------
-- Table structure for core_set_plan
-- ----------------------------
DROP TABLE IF EXISTS `core_set_plan`;
CREATE TABLE `core_set_plan` (
  `plan_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划ID',
  `table_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `plan_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划编码',
  `plan_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '计划名称',
  `plan_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'COLUMN' COMMENT '计划类型: COLUMN-列, FIELD-字段',
  `plan_config` text COLLATE utf8mb4_general_ci COMMENT '计划配置(JSON)',
  `plan_is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认: 0-否, 1-是',
  `plan_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '计划描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`plan_id`),
  UNIQUE KEY `uk_table_plan` (`table_id`,`plan_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='设置计划表';

-- ----------------------------
-- Table structure for core_setting
-- ----------------------------
DROP TABLE IF EXISTS `core_setting`;
CREATE TABLE `core_setting` (
  `setting_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置ID',
  `setting_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置编码',
  `setting_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设置名称',
  `setting_value` text COLLATE utf8mb4_general_ci COMMENT '设置值',
  `setting_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'USER' COMMENT '设置类型: SYSTEM-系统, USER-用户, DEPT-部门',
  `setting_owner_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所有者ID',
  `setting_owner_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所有者姓名',
  `setting_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`setting_id`),
  UNIQUE KEY `uk_setting_code_owner` (`setting_code`,`setting_type`,`setting_owner_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='设置表';

-- ----------------------------
-- Table structure for core_slow_sql_log
-- ----------------------------
DROP TABLE IF EXISTS `core_slow_sql_log`;
CREATE TABLE `core_slow_sql_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `log_sql` text COLLATE utf8mb4_general_ci NOT NULL COMMENT 'SQL语句',
  `log_duration` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `log_rows` int DEFAULT NULL COMMENT '影响行数',
  `log_database` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库',
  `log_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `log_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_log_duration` (`log_duration`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='慢SQL日志表';

-- ----------------------------
-- Table structure for core_table_column
-- ----------------------------
DROP TABLE IF EXISTS `core_table_column`;
CREATE TABLE `core_table_column` (
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `column_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列编码',
  `column_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列名称',
  `column_type` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列类型',
  `column_length` int DEFAULT NULL COMMENT '列长度',
  `column_decimal` int DEFAULT NULL COMMENT '小数位数',
  `column_nullable` tinyint(1) DEFAULT '1' COMMENT '是否可空: 0-否, 1-是',
  `column_default` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `column_comment` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列注释',
  `column_is_pk` tinyint(1) DEFAULT '0' COMMENT '是否主键: 0-否, 1-是',
  `column_is_fk` tinyint(1) DEFAULT '0' COMMENT '是否外键: 0-否, 1-是',
  `column_fk_table` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外键表',
  `column_fk_column` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外键列',
  `column_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`column_id`),
  UNIQUE KEY `uk_table_column` (`table_code`,`column_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表列表';

-- ----------------------------
-- Table structure for core_table_display
-- ----------------------------
DROP TABLE IF EXISTS `core_table_display`;
CREATE TABLE `core_table_display` (
  `display_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `display_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示类型: FORM-表单, LIST-列表, DETAIL-详情',
  `display_config` text COLLATE utf8mb4_general_ci COMMENT '显示配置(JSON)',
  `display_template` text COLLATE utf8mb4_general_ci COMMENT '显示模板',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`display_id`),
  UNIQUE KEY `uk_table_display` (`table_code`,`display_type`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表显示表';

-- ----------------------------
-- Table structure for core_table_index
-- ----------------------------
DROP TABLE IF EXISTS `core_table_index`;
CREATE TABLE `core_table_index` (
  `index_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '索引ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `index_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '索引编码',
  `index_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '索引名称',
  `index_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'NORMAL' COMMENT '索引类型: NORMAL-普通, UNIQUE-唯一, FULLTEXT-全文',
  `index_columns` varchar(512) COLLATE utf8mb4_general_ci NOT NULL COMMENT '索引列',
  `index_comment` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '索引注释',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`index_id`),
  UNIQUE KEY `uk_table_index` (`table_code`,`index_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表索引表';

-- ----------------------------
-- Table structure for core_table_key
-- ----------------------------
DROP TABLE IF EXISTS `core_table_key`;
CREATE TABLE `core_table_key` (
  `key_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '键ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `key_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '键编码',
  `key_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '键名称',
  `key_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PRIMARY' COMMENT '键类型: PRIMARY-主键, FOREIGN-外键, UNIQUE-唯一',
  `key_columns` varchar(512) COLLATE utf8mb4_general_ci NOT NULL COMMENT '键列',
  `ref_table` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '引用表',
  `ref_columns` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '引用列',
  `key_comment` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '键注释',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`key_id`),
  UNIQUE KEY `uk_table_key` (`table_code`,`key_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表键表';

-- ----------------------------
-- Table structure for core_table_trace
-- ----------------------------
DROP TABLE IF EXISTS `core_table_trace`;
CREATE TABLE `core_table_trace` (
  `trace_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '跟踪ID',
  `table_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表编码',
  `trace_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '跟踪类型: INSERT-插入, UPDATE-更新, DELETE-删除',
  `trace_data_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据ID',
  `trace_old_data` text COLLATE utf8mb4_general_ci COMMENT '旧数据(JSON)',
  `trace_new_data` text COLLATE utf8mb4_general_ci COMMENT '新数据(JSON)',
  `trace_change_fields` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '变更字段',
  `trace_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '跟踪时间',
  `trace_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `trace_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`trace_id`),
  KEY `idx_table_code` (`table_code`),
  KEY `idx_data_id` (`trace_data_id`),
  KEY `idx_trace_time` (`trace_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表跟踪表';

-- ----------------------------
-- Table structure for core_table_view
-- ----------------------------
DROP TABLE IF EXISTS `core_table_view`;
CREATE TABLE `core_table_view` (
  `view_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图ID',
  `view_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图编码',
  `view_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图名称',
  `view_sql` text COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图SQL',
  `view_tables` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包含表',
  `view_comment` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '视图注释',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`view_id`),
  UNIQUE KEY `uk_view_code` (`view_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='表视图表';

-- ----------------------------
-- Table structure for core_user_info
-- ----------------------------
DROP TABLE IF EXISTS `core_user_info`;
CREATE TABLE `core_user_info` (
  `info_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `info_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息类型: PREFERENCE-偏好, SETTING-设置, LAYOUT-布局',
  `info_key` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '信息键',
  `info_value` text COLLATE utf8mb4_general_ci COMMENT '信息值',
  `info_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '信息描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`info_id`),
  UNIQUE KEY `uk_user_info` (`user_id`,`info_type`,`info_key`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

-- ----------------------------
-- Table structure for core_user_info_column
-- ----------------------------
DROP TABLE IF EXISTS `core_user_info_column`;
CREATE TABLE `core_user_info_column` (
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `column_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列编码',
  `column_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `column_width` int DEFAULT NULL COMMENT '列宽度',
  `column_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`column_id`),
  UNIQUE KEY `uk_user_column` (`user_id`,`func_id`,`column_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息列表';

-- ----------------------------
-- Table structure for core_view_cascade
-- ----------------------------
DROP TABLE IF EXISTS `core_view_cascade`;
CREATE TABLE `core_view_cascade` (
  `cascade_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '级联ID',
  `view_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图ID',
  `cascade_field` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '级联字段',
  `cascade_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PARENT' COMMENT '级联类型: PARENT-父级, CHILD-子级',
  `cascade_config` text COLLATE utf8mb4_general_ci COMMENT '级联配置(JSON)',
  `cascade_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级联描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`cascade_id`),
  KEY `idx_view_id` (`view_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='视图级联表';

-- ----------------------------
-- Table structure for core_view_events
-- ----------------------------
DROP TABLE IF EXISTS `core_view_events`;
CREATE TABLE `core_view_events` (
  `event_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件ID',
  `view_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图ID',
  `event_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件名称',
  `event_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件类型: CLICK-点击, CHANGE-改变, LOAD-加载',
  `event_handler` text COLLATE utf8mb4_general_ci COMMENT '事件处理',
  `event_params` text COLLATE utf8mb4_general_ci COMMENT '事件参数',
  `event_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '事件描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`event_id`),
  KEY `idx_view_id` (`view_id`),
  KEY `idx_event_name` (`event_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='视图事件表';

-- ----------------------------
-- Table structure for credential
-- ----------------------------
DROP TABLE IF EXISTS `credential`;
CREATE TABLE `credential` (
  `credential_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭证ID',
  `credential_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭证类型: API-接口, DB-数据库, FTP-文件传输',
  `credential_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭证编码',
  `credential_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '凭证名称',
  `credential_username` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `credential_password` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码(加密)',
  `credential_access_key` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '访问密钥',
  `credential_secret_key` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '安全密钥',
  `credential_endpoint` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '端点地址',
  `credential_config` text COLLATE utf8mb4_general_ci COMMENT '凭证配置(JSON)',
  `credential_status` tinyint(1) DEFAULT '1' COMMENT '凭证状态: 0-停用, 1-启用',
  `credential_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '凭证描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`credential_id`),
  UNIQUE KEY `uk_credential_code` (`credential_code`,`tenant_id`,`is_deleted`),
  KEY `idx_credential_type` (`credential_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='凭证表';

-- ----------------------------
-- Table structure for framework_application_install
-- ----------------------------
DROP TABLE IF EXISTS `framework_application_install`;
CREATE TABLE `framework_application_install` (
  `install_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '安装ID',
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `app_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用编码',
  `app_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `app_version` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用版本',
  `install_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'INSTALL' COMMENT '安装类型: INSTALL-安装, UPGRADE-升级',
  `install_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '安装状态: PENDING-待处理, INSTALLING-安装中, SUCCESS-成功, FAILED-失败',
  `install_start_time` datetime DEFAULT NULL COMMENT '安装开始时间',
  `install_end_time` datetime DEFAULT NULL COMMENT '安装结束时间',
  `install_log` text COLLATE utf8mb4_general_ci COMMENT '安装日志',
  `install_error` text COLLATE utf8mb4_general_ci COMMENT '安装错误',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`install_id`),
  KEY `idx_app_id` (`app_id`),
  KEY `idx_app_version` (`app_version`),
  KEY `idx_install_status` (`install_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='应用安装表';

-- ----------------------------
-- Table structure for framework_application_resource
-- ----------------------------
DROP TABLE IF EXISTS `framework_application_resource`;
CREATE TABLE `framework_application_resource` (
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `resource_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型: MENU-菜单, BUTTON-按钮, API-接口',
  `resource_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源编码',
  `resource_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `resource_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源路径',
  `resource_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'GET' COMMENT '请求方式',
  `resource_permission` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `resource_status` tinyint(1) DEFAULT '1' COMMENT '资源状态: 0-停用, 1-启用',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`resource_id`),
  KEY `idx_app_id` (`app_id`),
  KEY `idx_resource_type` (`resource_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='应用资源表';

-- ----------------------------
-- Table structure for framework_base_resource
-- ----------------------------
DROP TABLE IF EXISTS `framework_base_resource`;
CREATE TABLE `framework_base_resource` (
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  `resource_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源编码',
  `resource_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `resource_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型: ICON-图标, IMAGE-图片, FILE-文件',
  `resource_path` varchar(512) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源路径',
  `resource_url` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源URL',
  `resource_size` bigint DEFAULT NULL COMMENT '资源大小',
  `resource_ext` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源扩展名',
  `resource_mime_type` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'MIME类型',
  `resource_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源分类',
  `resource_status` tinyint(1) DEFAULT '1' COMMENT '资源状态: 0-停用, 1-启用',
  `resource_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-说除',
  PRIMARY KEY (`resource_id`),
  UNIQUE KEY `uk_resource_code` (`resource_code`,`tenant_id`,`is_deleted`),
  KEY `idx_resource_type` (`resource_type`),
  KEY `idx_resource_category` (`resource_category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='基础资源表';

-- ----------------------------
-- Table structure for framework_database_instance
-- ----------------------------
DROP TABLE IF EXISTS `framework_database_instance`;
CREATE TABLE `framework_database_instance` (
  `instance_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例ID',
  `instance_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例编码',
  `instance_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `instance_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'MYSQL' COMMENT '数据库类型: MYSQL-MySQL, ORACLE-Oracle, SQLSERVER-SQLServer',
  `instance_host` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库主机',
  `instance_port` int DEFAULT NULL COMMENT '数据库端口',
  `instance_database` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据库名称',
  `instance_username` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `instance_password` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码(加密)',
  `instance_charset` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'utf8mb4' COMMENT '字符集',
  `instance_status` tinyint(1) DEFAULT '1' COMMENT '实例状态: 0-停用, 1-启用',
  `instance_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实例描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`instance_id`),
  UNIQUE KEY `uk_instance_code` (`instance_code`,`tenant_id`,`is_deleted`),
  KEY `idx_instance_type` (`instance_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据库实例表';

-- ----------------------------
-- Table structure for framework_database_resource
-- ----------------------------
DROP TABLE IF EXISTS `framework_database_resource`;
CREATE TABLE `framework_database_resource` (
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  `instance_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例ID',
  `resource_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型: TABLE-表, VIEW-视图, PROCEDURE-存储过程',
  `resource_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `resource_code` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源编码',
  `resource_sql` text COLLATE utf8mb4_general_ci COMMENT '资源SQL',
  `resource_comment` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源注释',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`resource_id`),
  KEY `idx_instance_id` (`instance_id`),
  KEY `idx_resource_type` (`resource_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据库资源表';

-- ----------------------------
-- Table structure for framework_manage
-- ----------------------------
DROP TABLE IF EXISTS `framework_manage`;
CREATE TABLE `framework_manage` (
  `manage_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理ID',
  `manage_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理编码',
  `manage_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '管理名称',
  `manage_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SYSTEM' COMMENT '管理类型: SYSTEM-系统, BUSINESS-业务',
  `manage_config` text COLLATE utf8mb4_general_ci COMMENT '管理配置(JSON)',
  `manage_status` tinyint(1) DEFAULT '1' COMMENT '管理状态: 0-停用, 1-启用',
  `manage_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '管理描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`manage_id`),
  UNIQUE KEY `uk_manage_code` (`manage_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='框架管理表';

-- ----------------------------
-- Table structure for framework_server
-- ----------------------------
DROP TABLE IF EXISTS `framework_server`;
CREATE TABLE `framework_server` (
  `server_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器ID',
  `server_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器编码',
  `server_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器名称',
  `server_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'APPLICATION' COMMENT '服务器类型: APPLICATION-应用, DATABASE-数据库, FILE-文件',
  `server_host` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器主机',
  `server_port` int DEFAULT NULL COMMENT '服务器端口',
  `server_username` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `server_password` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码(加密)',
  `server_os` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `server_status` tinyint(1) DEFAULT '1' COMMENT '服务器状态: 0-停用, 1-启用',
  `server_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务器描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`server_id`),
  UNIQUE KEY `uk_server_code` (`server_code`,`tenant_id`,`is_deleted`),
  KEY `idx_server_type` (`server_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='服务器表';

-- ----------------------------
-- Table structure for hrms_staff_archives
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_archives`;
CREATE TABLE `hrms_staff_archives` (
  `archives_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '档案ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `archives_no` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '档案编号',
  `archives_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `archives_gender` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '性别',
  `archives_birthday` date DEFAULT NULL COMMENT '生日',
  `archives_id_card` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `archives_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `archives_email` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `archives_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '居住地址',
  `archives_native_place` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '籍贯',
  `archives_nation` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'HAN' COMMENT '民族',
  `archives_marital_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '婚姻状况',
  `archives_political_status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '政治面貌',
  `archives_photo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '照片',
  `archives_work_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `archives_entry_date` date DEFAULT NULL COMMENT '入职日期',
  `archives_probation_end_date` date DEFAULT NULL COMMENT '试用期结束日期',
  `archives_employment_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '用工性质',
  `archives_education` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '学历',
  `archives_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ON_JOB' COMMENT '员工状态',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门名称',
  `post_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位ID',
  `post_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位名称',
  `position_level` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`archives_id`),
  UNIQUE KEY `uk_user_id` (`user_id`),
  UNIQUE KEY `uk_archives_no` (`archives_no`,`tenant_id`,`is_deleted`),
  KEY `idx_work_no` (`archives_work_no`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工档案表';

-- ----------------------------
-- Table structure for hrms_staff_certificate
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_certificate`;
CREATE TABLE `hrms_staff_certificate` (
  `certificate_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证书ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `certificate_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '证书名称',
  `certificate_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PROFESSIONAL' COMMENT '证书类型: PROFESSIONAL-职业资格, ACADEMIC-学历, LANGUAGE-语言, OTHER-其他',
  `certificate_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书编号',
  `certificate_issue_org` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '颁发机构',
  `certificate_issue_date` date DEFAULT NULL COMMENT '颁发日期',
  `certificate_expiry_date` date DEFAULT NULL COMMENT '有效期至',
  `certificate_file_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书文件路径',
  `certificate_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`certificate_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_certificate_type` (`certificate_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工证书表';

-- ----------------------------
-- Table structure for hrms_staff_contract
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_contract`;
CREATE TABLE `hrms_staff_contract` (
  `contract_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `contract_no` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同编号',
  `contract_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LABOR' COMMENT '合同类型: LABOR-劳动合同, INTERN-实习协议, OUTSOURCING-外包协议',
  `contract_start_date` date NOT NULL COMMENT '合同开始日期',
  `contract_end_date` date DEFAULT NULL COMMENT '合同结束日期',
  `contract_duration` int DEFAULT NULL COMMENT '合同期限(月)',
  `contract_trial_duration` int DEFAULT NULL COMMENT '试用期(月)',
  `contract_trial_end_date` date DEFAULT NULL COMMENT '试用期结束日期',
  `contract_salary` decimal(18,2) DEFAULT NULL COMMENT '合同薪资',
  `contract_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'EFFECT' COMMENT '合同状态: DRAFT-草稿, EFFECT-生效, EXPIRE-到期, TERMINATE-终止, RENEW-续签',
  `contract_file_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '合同文件路径',
  `contract_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '合同备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`contract_id`),
  UNIQUE KEY `uk_contract_no` (`contract_no`,`tenant_id`,`is_deleted`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_contract_status` (`contract_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工合同表';

-- ----------------------------
-- Table structure for hrms_staff_education
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_education`;
CREATE TABLE `hrms_staff_education` (
  `education_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '教育ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `education_level` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学历: HIGH_SCHOOL-高中, JUNIOR_COLLEGE-大专, BACHELOR-本科, MASTER-硕士, DOCTOR-博士',
  `education_school` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校名称',
  `education_major` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业',
  `education_start_date` date DEFAULT NULL COMMENT '开始日期',
  `education_end_date` date DEFAULT NULL COMMENT '结束日期',
  `education_degree` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学位',
  `education_certificate` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书路径',
  `education_is_highest` tinyint(1) DEFAULT '0' COMMENT '是否最高学历: 0-否, 1-是',
  `education_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`education_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_education_level` (`education_level`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工教育表';

-- ----------------------------
-- Table structure for hrms_staff_family
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_family`;
CREATE TABLE `hrms_staff_family` (
  `family_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '家庭成员ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `family_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `family_relation` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '关系: SPOUSE-配偶, FATHER-父亲, MOTHER-母亲, CHILD-子女, OTHER-其他',
  `family_gender` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '性别',
  `family_id_card` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `family_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `family_work_unit` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作单位',
  `family_occupation` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职业',
  `family_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '居住地址',
  `family_is_emergency` tinyint(1) DEFAULT '0' COMMENT '是否紧急联系人: 0-否, 1-是',
  `family_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`family_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_family_relation` (`family_relation`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工家庭成员表';

-- ----------------------------
-- Table structure for hrms_staff_job_resume
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_job_resume`;
CREATE TABLE `hrms_staff_job_resume` (
  `resume_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '简历ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `resume_company` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `resume_position` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职位',
  `resume_industry` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '行业',
  `resume_start_date` date DEFAULT NULL COMMENT '开始日期',
  `resume_end_date` date DEFAULT NULL COMMENT '结束日期',
  `resume_work_description` text COLLATE utf8mb4_general_ci COMMENT '工作描述',
  `resume_salary` decimal(18,2) DEFAULT NULL COMMENT '薪资',
  `resume_reason` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '离职原因',
  `resume_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`resume_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_resume_company` (`resume_company`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工工作简历表';

-- ----------------------------
-- Table structure for hrms_staff_language
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_language`;
CREATE TABLE `hrms_staff_language` (
  `language_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '语言ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `language_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '语言类型: ENGLISH-英语, JAPANESE-日语, KOREAN-韩语, OTHER-其他',
  `language_level` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'GENERAL' COMMENT '语言水平: GENERAL-一般, GOOD-良好, EXCELLENT-优秀, NATIVE-母语',
  `language_listen` tinyint(1) DEFAULT '0' COMMENT '听力: 0-不会, 1-一般, 2-良好, 3-优秀',
  `language_speak` tinyint(1) DEFAULT '0' COMMENT '口语: 0-不会, 1-一般, 2-良好, 3-优秀',
  `language_read` tinyint(1) DEFAULT '0' COMMENT '阅读: 0-不会, 1-一般, 2-良好, 3-优秀',
  `language_write` tinyint(1) DEFAULT '0' COMMENT '写作: 0-不会, 1-一般, 2-良好, 3-优秀',
  `language_certificate` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证书名称',
  `language_score` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '成绩分数',
  `language_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`language_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_language_type` (`language_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工语言能力表';

-- ----------------------------
-- Table structure for hrms_staff_reward_punish
-- ----------------------------
DROP TABLE IF EXISTS `hrms_staff_reward_punish`;
CREATE TABLE `hrms_staff_reward_punish` (
  `rp_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖惩ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `rp_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型: REWARD-奖励, PUNISH-惩罚',
  `rp_category` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖惩类别',
  `rp_title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '奖惩标题',
  `rp_date` date DEFAULT NULL COMMENT '奖惩日期',
  `rp_amount` decimal(18,2) DEFAULT NULL COMMENT '奖惩金额',
  `rp_reason` text COLLATE utf8mb4_general_ci COMMENT '奖惩原因',
  `rp_approval_org` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '批准机构',
  `rp_doc_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文号',
  `rp_doc_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '文件路径',
  `rp_remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`rp_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_rp_type` (`rp_type`),
  KEY `idx_rp_date` (`rp_date`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工奖惩表';

-- ----------------------------
-- Table structure for iam_account
-- ----------------------------
DROP TABLE IF EXISTS `iam_account`;
CREATE TABLE `iam_account` (
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `account_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户编码',
  `account_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户名称',
  `account_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户密码(加密)',
  `account_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `account_mail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `account_avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像URL',
  `account_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'SYSTEM' COMMENT '账户类型: SYSTEM-系统, THIRD-第三方',
  `account_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'LOCAL' COMMENT '账户来源: LOCAL-本地, DINGTALK-钉钉, WECHAT-微信',
  `account_status` tinyint(1) DEFAULT '1' COMMENT '账户状态: 0-禁用, 1-启用',
  `account_is_admin` tinyint(1) DEFAULT '0' COMMENT '是否管理员: 0-否, 1-是',
  `account_last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `account_last_login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录IP',
  `account_init_password` tinyint(1) DEFAULT '0' COMMENT '是否初始密码: 0-否, 1-是',
  `account_init_password_reset_time` datetime DEFAULT NULL COMMENT '初始密码重置时间',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织ID',
  `create_org_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织名称',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改组织ID',
  `modify_org_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改组织名称',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `modify_user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人姓名',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `uk_account_code` (`account_code`,`tenant_id`,`is_deleted`),
  KEY `idx_account_name` (`account_name`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_status` (`account_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户表';

-- ----------------------------
-- Table structure for iam_account_bind
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_bind`;
CREATE TABLE `iam_account_bind` (
  `bind_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '绑定ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `bind_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '绑定类型: DINGTALK-钉钉, WECHAT-微信, QQ-QQ',
  `bind_openid` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方OpenID',
  `bind_unionid` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方UnionID',
  `bind_nickname` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方昵称',
  `bind_avatar` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方头像',
  `bind_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
  `unbind_time` datetime DEFAULT NULL COMMENT '解绑时间',
  `bind_status` tinyint(1) DEFAULT '1' COMMENT '绑定状态: 0-解绑, 1-绑定',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`bind_id`),
  UNIQUE KEY `uk_bind_openid` (`bind_type`,`bind_openid`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户绑定表';

-- ----------------------------
-- Table structure for iam_account_dept
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_dept`;
CREATE TABLE `iam_account_dept` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `dept_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `is_primary` tinyint(1) DEFAULT '0' COMMENT '是否主部门: 0-否, 1-是',
  `is_leader` tinyint(1) DEFAULT '0' COMMENT '是否部门负责人: 0-否, 1-是',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户部门关联表';

-- ----------------------------
-- Table structure for iam_account_onjob
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_onjob`;
CREATE TABLE `iam_account_onjob` (
  `onjob_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '在职ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `onjob_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '在职类型: FULL_TIME-全职, PART_TIME-兼职',
  `onjob_start_date` date DEFAULT NULL COMMENT '开始日期',
  `onjob_end_date` date DEFAULT NULL COMMENT '结束日期',
  `onjob_status` tinyint(1) DEFAULT '1' COMMENT '在职状态: 0-离职, 1-在职',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`onjob_id`),
  UNIQUE KEY `uk_account_id` (`account_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='在职账户表';

-- ----------------------------
-- Table structure for iam_account_role
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_role`;
CREATE TABLE `iam_account_role` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户角色关联表';

-- ----------------------------
-- Table structure for iam_bury_data
-- ----------------------------
DROP TABLE IF EXISTS `iam_bury_data`;
CREATE TABLE `iam_bury_data` (
  `bury_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '埋点ID',
  `bury_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '埋点类型',
  `bury_event` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '埋点事件',
  `bury_page` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面路径',
  `bury_data` text COLLATE utf8mb4_general_ci COMMENT '埋点数据(JSON)',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户ID',
  `bury_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '埋点时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`bury_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_bury_type` (`bury_type`),
  KEY `idx_bury_event` (`bury_event`),
  KEY `idx_bury_time` (`bury_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='埋点数据表';

-- ----------------------------
-- Table structure for iam_captcha_log
-- ----------------------------
DROP TABLE IF EXISTS `iam_captcha_log`;
CREATE TABLE `iam_captcha_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `captcha_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码类型',
  `captcha_target` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '验证目标(手机号/邮箱)',
  `captcha_code` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `captcha_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '验证码状态',
  `use_time` datetime DEFAULT NULL COMMENT '使用时间',
  `expire_time` datetime NOT NULL COMMENT '过期时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_captcha_target` (`captcha_target`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='验证码日志表';

-- ----------------------------
-- Table structure for iam_company
-- ----------------------------
DROP TABLE IF EXISTS `iam_company`;
CREATE TABLE `iam_company` (
  `company_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司ID',
  `company_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  `company_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `company_short_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简称',
  `company_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'ENTERPRISE' COMMENT '公司类型: ENTERPRISE-企业, BRANCH-分公司, HOLDING-控股',
  `company_level` int DEFAULT '1' COMMENT '公司级别',
  `company_parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父公司ID',
  `company_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司地址',
  `company_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `company_mail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司邮箱',
  `company_website` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司网站',
  `company_logo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司Logo',
  `company_legal_rep` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法定代表人',
  `company_registration_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工商注册号',
  `company_tax_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '税务登记号',
  `company_status` tinyint(1) DEFAULT '1' COMMENT '公司状态: 0-注销, 1-正常',
  `company_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简介',
  `company_sort` int DEFAULT '0' COMMENT '排序号',
  `company_tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `company_tree_level` int DEFAULT '1' COMMENT '树层级',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `uk_company_code` (`company_code`,`tenant_id`,`is_deleted`),
  KEY `idx_company_name` (`company_name`),
  KEY `idx_parent_id` (`company_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司表';

-- ----------------------------
-- Table structure for iam_company_dept
-- ----------------------------
DROP TABLE IF EXISTS `iam_company_dept`;
CREATE TABLE `iam_company_dept` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `company_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司ID',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司部门关联表';

-- ----------------------------
-- Table structure for iam_company_user
-- ----------------------------
DROP TABLE IF EXISTS `iam_company_user`;
CREATE TABLE `iam_company_user` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `company_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_company_id` (`company_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司用户关联表';

-- ----------------------------
-- Table structure for iam_department
-- ----------------------------
DROP TABLE IF EXISTS `iam_department`;
CREATE TABLE `iam_department` (
  `dept_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `dept_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
  `dept_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `dept_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'DEPARTMENT' COMMENT '部门类型: HEADQUARTERS-总部, DEPARTMENT-部门, TEAM-小组',
  `dept_parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父部门ID',
  `dept_company_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属公司ID',
  `dept_leader_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门负责人ID',
  `dept_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门电话',
  `dept_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门地址',
  `dept_status` tinyint(1) DEFAULT '1' COMMENT '部门状态: 0-停用, 1-启用',
  `dept_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门描述',
  `dept_sort` int DEFAULT '0' COMMENT '排序号',
  `dept_tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `dept_tree_level` int DEFAULT '1' COMMENT '树层级',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `uk_dept_code` (`dept_code`,`tenant_id`,`is_deleted`),
  KEY `idx_dept_name` (`dept_name`),
  KEY `idx_parent_id` (`dept_parent_id`),
  KEY `idx_company_id` (`dept_company_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';

-- ----------------------------
-- Table structure for iam_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `iam_dept_user`;
CREATE TABLE `iam_dept_user` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `is_primary` tinyint(1) DEFAULT '0' COMMENT '是否主部门: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门用户关联表';

-- ----------------------------
-- Table structure for iam_developer_org
-- ----------------------------
DROP TABLE IF EXISTS `iam_developer_org`;
CREATE TABLE `iam_developer_org` (
  `org_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织ID',
  `org_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织编码',
  `org_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织名称',
  `org_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'DEVELOPMENT' COMMENT '组织类型',
  `org_status` tinyint(1) DEFAULT '1' COMMENT '组织状态: 0-禁用, 1-启用',
  `org_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-说除',
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `uk_org_code` (`org_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='开发者组织表';

-- ----------------------------
-- Table structure for iam_dingtalk_app
-- ----------------------------
DROP TABLE IF EXISTS `iam_dingtalk_app`;
CREATE TABLE `iam_dingtalk_app` (
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `app_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用编码',
  `app_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `app_key` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用Key',
  `app_secret` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用Secret',
  `app_agent_id` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用AgentId',
  `app_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'INTERNAL' COMMENT '应用类型',
  `app_status` tinyint(1) DEFAULT '1' COMMENT '应用状态: 0-禁用, 1-启用',
  `app_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_code` (`app_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='钉钉应用表';

-- ----------------------------
-- Table structure for iam_dingtalk_config
-- ----------------------------
DROP TABLE IF EXISTS `iam_dingtalk_config`;
CREATE TABLE `iam_dingtalk_config` (
  `config_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `config_key` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置键',
  `config_value` text COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_config_key` (`config_key`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='钉钉配置表';

-- ----------------------------
-- Table structure for iam_head_menu
-- ----------------------------
DROP TABLE IF EXISTS `iam_head_menu`;
CREATE TABLE `iam_head_menu` (
  `head_menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '顶部菜单ID',
  `head_menu_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '顶部菜单编码',
  `head_menu_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '顶部菜单名称',
  `head_menu_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '顶部菜单图标',
  `head_menu_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '顶部菜单路径',
  `head_menu_sort` int DEFAULT '0' COMMENT '排序号',
  `head_menu_status` tinyint(1) DEFAULT '1' COMMENT '顶部菜单状态: 0-禁用, 1-启用',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`head_menu_id`),
  UNIQUE KEY `uk_head_menu_code` (`head_menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='顶部菜单表';

-- ----------------------------
-- Table structure for iam_head_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `iam_head_menu_relation`;
CREATE TABLE `iam_head_menu_relation` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `head_menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '顶部菜单ID',
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `relation_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_head_menu_id` (`head_menu_id`),
  KEY `idx_menu_id` (`menu_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='顶部菜单关联表';

-- ----------------------------
-- Table structure for iam_import_func
-- ----------------------------
DROP TABLE IF EXISTS `iam_import_func`;
CREATE TABLE `iam_import_func` (
  `import_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '导入ID',
  `import_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '导入编码',
  `import_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '导入名称',
  `import_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'USER' COMMENT '导入类型',
  `import_status` tinyint(1) DEFAULT '1' COMMENT '导入状态: 0-禁用, 1-启用',
  `import_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '导入描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`import_id`),
  UNIQUE KEY `uk_import_code` (`import_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='导入功能表';

-- ----------------------------
-- Table structure for iam_latest_user
-- ----------------------------
DROP TABLE IF EXISTS `iam_latest_user`;
CREATE TABLE `iam_latest_user` (
  `latest_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '最近ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `account_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户名称',
  `account_avatar` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `latest_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近访问时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`latest_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_latest_time` (`latest_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='最近用户表';

-- ----------------------------
-- Table structure for iam_login_log
-- ----------------------------
DROP TABLE IF EXISTS `iam_login_log`;
CREATE TABLE `iam_login_log` (
  `log_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户ID',
  `account_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户编码',
  `account_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户名称',
  `login_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'PASSWORD' COMMENT '登录方式: PASSWORD-密码, CAPTCHA-验证码, WECHAT-微信, DINGTALK-钉钉',
  `login_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录状态: SUCCESS-成功, FAIL-失败',
  `login_fail_reason` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录失败原因',
  `login_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP',
  `login_location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录地点',
  `login_device` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录设备: PC-电脑, MOBILE-手机, TABLET-平板',
  `login_browser` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器类型',
  `login_os` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '登出时间',
  `online_duration` int DEFAULT NULL COMMENT '在线时长(秒)',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_login_time` (`login_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志表';

-- ----------------------------
-- Table structure for iam_menu
-- ----------------------------
DROP TABLE IF EXISTS `iam_menu`;
CREATE TABLE `iam_menu` (
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'MENU' COMMENT '菜单类型: DIRECTORY-目录, MENU-菜单, BUTTON-按钮',
  `menu_parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父菜单ID',
  `menu_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由路径',
  `menu_component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `menu_icon` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int DEFAULT '0' COMMENT '排序号',
  `menu_is_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-否, 1-是',
  `menu_is_cached` tinyint(1) DEFAULT '0' COMMENT '是否缓存: 0-否, 1-是',
  `menu_is_affix` tinyint(1) DEFAULT '0' COMMENT '是否固定: 0-否, 1-是',
  `menu_permission` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单权限',
  `menu_redirect` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '重定向路径',
  `menu_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `menu_status` tinyint(1) DEFAULT '1' COMMENT '菜单状态: 0-停用, 1-启用',
  `menu_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `menu_tree_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `menu_tree_level` int DEFAULT '1' COMMENT '树层级',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `uk_menu_code` (`menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_menu_name` (`menu_name`),
  KEY `idx_parent_id` (`menu_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';

-- ----------------------------
-- Table structure for iam_menu_history
-- ----------------------------
DROP TABLE IF EXISTS `iam_menu_history`;
CREATE TABLE `iam_menu_history` (
  `history_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '历史ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名称',
  `menu_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单路径',
  `visit_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
  `visit_count` int DEFAULT '1' COMMENT '访问次数',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`history_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_menu_id` (`menu_id`),
  KEY `idx_visit_time` (`visit_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单历史表';

-- ----------------------------
-- Table structure for iam_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `iam_operate_log`;
CREATE TABLE `iam_operate_log` (
  `log_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `operate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作类型: LOGIN-登录, LOGOUT-登出, CREATE-新增, UPDATE-更新, DELETE-删除, EXPORT-导出, IMPORT-导入',
  `module_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块名称',
  `business_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务类型',
  `business_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务ID',
  `method_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `request_url` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方法',
  `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `response_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '响应结果',
  `operate_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作状态: SUCCESS-成功, FAIL-失败',
  `error_msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '错误信息',
  `execute_duration` int DEFAULT NULL COMMENT '执行时长(毫秒)',
  `operator_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `operator_dept` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人部门',
  `operate_ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `operate_location` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作地点',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_operate_type` (`operate_type`),
  KEY `idx_module_name` (`module_name`),
  KEY `idx_operator_id` (`operator_id`),
  KEY `idx_operate_time` (`operate_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';

-- ----------------------------
-- Table structure for iam_organization
-- ----------------------------
DROP TABLE IF EXISTS `iam_organization`;
CREATE TABLE `iam_organization` (
  `org_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织ID',
  `org_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织编码',
  `org_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织名称',
  `org_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'COMPANY' COMMENT '组织类型: COMPANY-公司, DEPARTMENT-部门, GROUP-小组',
  `org_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父组织ID',
  `org_leader_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人ID',
  `org_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `org_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `org_status` tinyint(1) DEFAULT '1' COMMENT '组织状态: 0-停用, 1-启用',
  `org_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组织描述',
  `org_sort` int DEFAULT '0' COMMENT '排序号',
  `org_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树形路径',
  `org_tree_level` int DEFAULT '1' COMMENT '树形层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`org_id`),
  UNIQUE KEY `uk_org_code` (`org_code`,`tenant_id`,`is_deleted`),
  KEY `idx_org_name` (`org_name`),
  KEY `idx_parent_id` (`org_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='组织表';

-- ----------------------------
-- Table structure for iam_permission
-- ----------------------------
DROP TABLE IF EXISTS `iam_permission`;
CREATE TABLE `iam_permission` (
  `permission_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `permission_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
  `permission_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'BUTTON' COMMENT '权限类型: MENU-菜单, BUTTON-按钮, API-接口, DATA-数据',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '父权限ID，用于树形结构',
  `permission_resource` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限资源',
  `permission_action` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'VIEW' COMMENT '权限操作: VIEW-查看, ADD-新增, EDIT-编辑, DELETE-删除, EXPORT-导出, IMPORT-导入',
  `permission_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限描述',
  `permission_status` tinyint(1) DEFAULT '1' COMMENT '权限状态: 0-停用, 1-启用',
  `permission_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `permission_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`,`tenant_id`,`is_deleted`),
  KEY `idx_permission_name` (`permission_name`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

-- ----------------------------
-- Table structure for iam_permission_group
-- ----------------------------
DROP TABLE IF EXISTS `iam_permission_group`;
CREATE TABLE `iam_permission_group` (
  `group_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限组ID',
  `group_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限组编码',
  `group_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限组名称',
  `group_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限组描述',
  `group_status` tinyint(1) DEFAULT '1' COMMENT '权限组状态: 0-禁用, 1-启用',
  `group_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `uk_group_code` (`group_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限组表';

-- ----------------------------
-- Table structure for iam_permission_group_perm
-- ----------------------------
DROP TABLE IF EXISTS `iam_permission_group_perm`;
CREATE TABLE `iam_permission_group_perm` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `group_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限组ID',
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_perm` (`group_id`,`permission_id`,`is_deleted`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限组权限关联表';

-- ----------------------------
-- Table structure for iam_post
-- ----------------------------
DROP TABLE IF EXISTS `iam_post`;
CREATE TABLE `iam_post` (
  `post_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_level` int DEFAULT '1' COMMENT '岗位级别',
  `post_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'TECHNICAL' COMMENT '岗位类型: MANAGEMENT-管理, TECHNICAL-技术, BUSINESS-业务, FUNCTIONAL-职能',
  `post_status` tinyint(1) DEFAULT '1' COMMENT '岗位状态: 0-停用, 1-启用',
  `post_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位描述',
  `post_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `uk_post_code` (`post_code`,`tenant_id`,`is_deleted`),
  KEY `idx_post_name` (`post_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位表';

-- ----------------------------
-- Table structure for iam_role
-- ----------------------------
DROP TABLE IF EXISTS `iam_role`;
CREATE TABLE `iam_role` (
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '角色类型: SYSTEM-系统, BUSINESS-业务',
  `role_level` int DEFAULT '5' COMMENT '角色级别',
  `role_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(1) DEFAULT '1' COMMENT '角色状态: 0-停用, 1-启用',
  `role_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `role_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_code` (`role_code`,`tenant_id`,`is_deleted`),
  KEY `idx_role_name` (`role_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- Table structure for iam_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `iam_role_permission`;
CREATE TABLE `iam_role_permission` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Table structure for iam_supplier
-- ----------------------------
DROP TABLE IF EXISTS `iam_supplier`;
CREATE TABLE `iam_supplier` (
  `supplier_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '供应商ID',
  `supplier_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '供应商编码',
  `supplier_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '供应商名称',
  `supplier_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'EXTERNAL' COMMENT '供应商类型',
  `supplier_contact` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `supplier_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `supplier_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系邮箱',
  `supplier_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `supplier_status` tinyint(1) DEFAULT '1' COMMENT '供应商状态: 0-停用, 1-启用',
  `supplier_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '供应商描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`supplier_id`),
  UNIQUE KEY `uk_supplier_code` (`supplier_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='供应商表';

-- ----------------------------
-- Table structure for iam_sync_log
-- ----------------------------
DROP TABLE IF EXISTS `iam_sync_log`;
CREATE TABLE `iam_sync_log` (
  `sync_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '同步ID',
  `sync_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '同步类型',
  `sync_source` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '同步来源',
  `sync_target` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '同步目标',
  `sync_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '同步状态',
  `sync_start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `sync_end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `sync_result` text COLLATE utf8mb4_general_ci COMMENT '同步结果',
  `error_msg` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`sync_id`),
  KEY `idx_sync_type` (`sync_type`),
  KEY `idx_sync_status` (`sync_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='同步日志表';

-- ----------------------------
-- Table structure for iam_tenant
-- ----------------------------
DROP TABLE IF EXISTS `iam_tenant`;
CREATE TABLE `iam_tenant` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户ID',
  `tenant_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编码',
  `tenant_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  `tenant_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'ENTERPRISE' COMMENT '租户类型',
  `contact` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `tenant_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'NORMAL' COMMENT '租户状态',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `user_limit` int DEFAULT NULL COMMENT '用户数量限制',
  `current_user_count` int DEFAULT '0' COMMENT '当前用户数',
  `storage_limit` bigint DEFAULT NULL COMMENT '存储空间限制',
  `used_storage` bigint DEFAULT '0' COMMENT '已使用存储空间',
  `logo_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Logo URL',
  `domain` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '域名',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `updater` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_code` (`tenant_code`,`deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='租户表';

-- ----------------------------
-- Table structure for iam_user
-- ----------------------------
DROP TABLE IF EXISTS `iam_user`;
CREATE TABLE `iam_user` (
  `user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编码',
  `user_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `nickname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实姓名',
  `user_sex` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '性别: MALE-男, FEMALE-女, UNKNOWN-未知',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `user_id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `user_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `user_mail` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `user_photo` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '照片URL',
  `user_address` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '居住地址',
  `user_native_place` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '籍贯',
  `user_nation` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '民族',
  `user_marital_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '婚姻状况: SINGLE-未婚, MARRIED-已婚, DIVORCED-离异',
  `user_political_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '政治面貌: MEMBER-党员, LEAGUE-团员, MASS-群众',
  `user_work_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `user_entry_date` date DEFAULT NULL COMMENT '入职日期',
  `user_probation_end_date` date DEFAULT NULL COMMENT '试用期结束日期',
  `user_employment_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '用工性质: FULL_TIME-全职, PART_TIME-兼职, INTERNSHIP-实习',
  `user_education` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学历: DOCTOR-博士, MASTER-硕士, BACHELOR-本科, ASSOCIATE-大专, HIGH_SCHOOL-高中',
  `user_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'ON_JOB' COMMENT '员工状态: ON_JOB-在职, RESIGN-离职, PROBATION-试用期',
  `status` tinyint(1) DEFAULT '1' COMMENT '账号状态: 0-禁用, 1-启用',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门ID',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织ID',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_code` (`user_code`,`tenant_id`,`is_deleted`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_user_phone` (`user_phone`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for iam_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `iam_user_permission`;
CREATE TABLE `iam_user_permission` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_permission` (`user_id`,`permission_id`,`is_deleted`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户权限关联表';

-- ----------------------------
-- Table structure for iam_wechat_app
-- ----------------------------
DROP TABLE IF EXISTS `iam_wechat_app`;
CREATE TABLE `iam_wechat_app` (
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `app_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用编码',
  `app_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `app_appid` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信AppId',
  `app_secret` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信Secret',
  `app_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'WORK' COMMENT '应用类型: WORK-企业号, MP-公众号, OPEN-开放平台',
  `app_status` tinyint(1) DEFAULT '1' COMMENT '应用状态: 0-禁用, 1-启用',
  `app_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_code` (`app_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信应用表';

-- ----------------------------
-- Table structure for iam_wechat_config
-- ----------------------------
DROP TABLE IF EXISTS `iam_wechat_config`;
CREATE TABLE `iam_wechat_config` (
  `config_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `config_key` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置键',
  `config_value` text COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_config_key` (`config_key`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微信配置表';

-- ----------------------------
-- Table structure for meta_column
-- ----------------------------
DROP TABLE IF EXISTS `meta_column`;
CREATE TABLE `meta_column` (
  `column_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `table_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `column_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `column_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段描述',
  `column_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
  `java_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Java类型',
  `java_field` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Java字段名',
  `is_pk` tinyint(1) DEFAULT '0' COMMENT '是否主键',
  `is_increment` tinyint(1) DEFAULT '0' COMMENT '是否自增',
  `is_required` tinyint(1) DEFAULT '0' COMMENT '是否必填',
  `is_query` tinyint(1) DEFAULT '0' COMMENT '是否为查询条件',
  `query_type` int DEFAULT '1' COMMENT '查询方式:1-精确 2-模糊 3-范围',
  `is_display` tinyint(1) DEFAULT '0' COMMENT '是否为显示字段',
  `is_list` tinyint(1) DEFAULT '0' COMMENT '是否为列表显示',
  `is_form` tinyint(1) DEFAULT '0' COMMENT '是否为表单字段',
  `form_type` int DEFAULT '1' COMMENT '表单类型:1-文本框 2-文本域 3-下拉框 4-单选框 5-复选框 6-日期选择 7-文件上传 8-富文本',
  `dict_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `default_value` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `column_sort` int DEFAULT '0' COMMENT '排序',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`column_id`),
  KEY `idx_table_id` (`table_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表字段定义';

-- ----------------------------
-- Table structure for meta_entity
-- ----------------------------
DROP TABLE IF EXISTS `meta_entity`;
CREATE TABLE `meta_entity` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体ID',
  `entity_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体编码',
  `entity_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实体名称',
  `table_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表名',
  `entity_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实体类型',
  `category` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类: BUSINESS-业务, SYSTEM-系统, CONFIG-配置',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `package_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包名',
  `module_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名',
  `business_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务名',
  `template_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模板类型',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态: 0-禁用, 1-启用',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_entity_code` (`entity_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据实体配置';

-- ----------------------------
-- Table structure for meta_form
-- ----------------------------
DROP TABLE IF EXISTS `meta_form`;
CREATE TABLE `meta_form` (
  `form_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单ID',
  `form_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单编码',
  `form_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单名称',
  `table_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联表ID',
  `form_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'NORMAL' COMMENT '表单类型: NORMAL-普通, POPUP-弹窗, STEPPER-分步',
  `form_layout` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'single' COMMENT '表单布局: single-单列, double-双列',
  `form_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '表单配置(JSON)',
  `form_status` tinyint(1) DEFAULT '1' COMMENT '表单状态: 0-停用, 1-启用',
  `form_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表单描述',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`form_id`),
  UNIQUE KEY `uk_form_code` (`form_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表单配置';

-- ----------------------------
-- Table structure for meta_kftdgl
-- ----------------------------
DROP TABLE IF EXISTS `meta_kftdgl`;
CREATE TABLE `meta_kftdgl` (
  `tool_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工具ID',
  `tool_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工具编码',
  `tool_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '工具名称',
  `tool_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'GENERATOR' COMMENT '工具类型: GENERATOR-代码生成, DESIGNER-设计器, DEBUGGER-调试器',
  `tool_config` text COLLATE utf8mb4_general_ci COMMENT '工具配置(JSON)',
  `tool_status` tinyint(1) DEFAULT '1' COMMENT '工具状态: 0-停用, 1-启用',
  `tool_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工具描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`tool_id`),
  UNIQUE KEY `uk_tool_code` (`tool_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tool_type` (`tool_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='开发工具管理表';

-- ----------------------------
-- Table structure for meta_list
-- ----------------------------
DROP TABLE IF EXISTS `meta_list`;
CREATE TABLE `meta_list` (
  `list_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表ID',
  `list_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表编码',
  `list_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表名称',
  `table_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联表ID',
  `list_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'TABLE' COMMENT '列表类型: TABLE-表格, TREE-树形, CARD-卡片',
  `list_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '列表配置(JSON)',
  `list_status` tinyint(1) DEFAULT '1' COMMENT '列表状态: 0-停用, 1-启用',
  `list_desc` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列表描述',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`list_id`),
  UNIQUE KEY `uk_list_code` (`list_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据列表配置';

-- ----------------------------
-- Table structure for meta_menu_func
-- ----------------------------
DROP TABLE IF EXISTS `meta_menu_func`;
CREATE TABLE `meta_menu_func` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `func_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能ID',
  `func_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能编码',
  `func_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能名称',
  `is_enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_menu_func` (`menu_id`,`func_id`,`is_deleted`),
  KEY `idx_func_code` (`func_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单功能关联表';

-- ----------------------------
-- Table structure for meta_microapp
-- ----------------------------
DROP TABLE IF EXISTS `meta_microapp`;
CREATE TABLE `meta_microapp` (
  `app_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用ID',
  `app_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用编码',
  `app_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `app_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'INTERNAL' COMMENT '应用类型: INTERNAL-内部, EXTERNAL-外部',
  `app_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用分类',
  `app_entry` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用入口URL',
  `app_active_route` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '激活路由',
  `app_active_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ROUTE' COMMENT '激活方式: ROUTE-路由, LINK-链接, MODAL-模态框',
  `app_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用图标',
  `app_logo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用LOGO',
  `app_screenshot` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用截图',
  `app_version` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用版本',
  `app_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '应用描述',
  `app_status` tinyint(1) DEFAULT '1' COMMENT '应用状态: 0-停用, 1-启用',
  `app_menu_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '激活菜单类型',
  `app_menu_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联菜单ID',
  `app_permission_code` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限编码',
  `app_is_public` tinyint(1) DEFAULT '0' COMMENT '是否公开: 0-否, 1-是',
  `app_is_platform` tinyint(1) DEFAULT '0' COMMENT '是否平台应用: 0-否, 1-是',
  `app_config` text COLLATE utf8mb4_general_ci COMMENT '应用配置(JSON)',
  `app_params` text COLLATE utf8mb4_general_ci COMMENT '应用参数(JSON)',
  `app_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`app_id`),
  UNIQUE KEY `uk_app_code` (`app_code`,`tenant_id`,`is_deleted`),
  KEY `idx_app_name` (`app_name`),
  KEY `idx_app_type` (`app_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='微应用表';

-- ----------------------------
-- Table structure for meta_page_config
-- ----------------------------
DROP TABLE IF EXISTS `meta_page_config`;
CREATE TABLE `meta_page_config` (
  `page_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面配置ID',
  `page_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面编码',
  `page_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面名称',
  `page_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面类型: FORM-表单, LIST-列表, DASHBOARD-仪表板, WORKBENCH-工作台',
  `page_sub_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面子类型: ADD-新增, EDIT-编辑, VIEW-查看, QUERY-查询',
  `page_title` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面标题',
  `page_object_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '对象类型',
  `page_func_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联功能ID',
  `workflow_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流ID',
  `workflow_node_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作流节点ID',
  `is_save_workflow_page` tinyint(1) DEFAULT '0' COMMENT '是否保存工作流页面: 0-否, 1-是',
  `is_all_readonly` tinyint(1) DEFAULT '0' COMMENT '是否全部只读: 0-否, 1-是',
  `owner_field_ids` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '拥有者字段ID列表',
  `actor_field_ids` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作者字段ID列表',
  `reader_field_ids` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '阅读者字段ID列表',
  `page_layout` text COLLATE utf8mb4_general_ci COMMENT '页面布局配置(JSON)',
  `page_columns` text COLLATE utf8mb4_general_ci COMMENT '列配置(JSON)',
  `page_fields_params` text COLLATE utf8mb4_general_ci COMMENT '字段参数配置(JSON)',
  `page_actions` text COLLATE utf8mb4_general_ci COMMENT '页面操作配置(JSON)',
  `page_validation` text COLLATE utf8mb4_general_ci COMMENT '页面验证规则(JSON)',
  `page_events` text COLLATE utf8mb4_general_ci COMMENT '页面事件配置(JSON)',
  `parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父页面ID',
  `tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树形路径',
  `tree_level` int DEFAULT '1' COMMENT '树形层级',
  `page_status` tinyint(1) DEFAULT '1' COMMENT '页面状态: 0-停用, 1-启用',
  `page_version` int DEFAULT '1' COMMENT '页面版本',
  `page_is_template` tinyint(1) DEFAULT '0' COMMENT '是否模板: 0-否, 1-是',
  `page_is_published` tinyint(1) DEFAULT '0' COMMENT '是否已发布: 0-否, 1-是',
  `page_publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `page_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '页面描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号(乐观锁)',
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `uk_page_code` (`page_code`,`tenant_id`,`is_deleted`),
  KEY `idx_page_type` (`page_type`),
  KEY `idx_func_id` (`page_func_id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='页面配置表';

-- ----------------------------
-- Table structure for meta_page_field
-- ----------------------------
DROP TABLE IF EXISTS `meta_page_field`;
CREATE TABLE `meta_page_field` (
  `field_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `page_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '页面配置ID',
  `field_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段编码',
  `field_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `field_key` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段键',
  `field_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型: TEXT-文本, NUMBER-数字, DATE-日期, SELECT-下拉, USER-用户, DEPT-部门, FILE-文件, IMAGE-图片, TEXTAREA-多行文本, EDITOR-富文本, TREE-树形, CASCADER-级联',
  `field_sub_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段子类型',
  `field_data_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据类型: STRING-字符串, INTEGER-整数, DECIMAL-小数, DATE-日期, DATETIME-日期时间, BOOLEAN-布尔',
  `field_default_value` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `field_placeholder` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '占位符',
  `field_format` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '格式化',
  `field_width` int DEFAULT NULL COMMENT '字段宽度(px)',
  `field_height` int DEFAULT NULL COMMENT '字段高度(px)',
  `field_max_length` int DEFAULT NULL COMMENT '最大长度',
  `field_min_length` int DEFAULT NULL COMMENT '最小长度',
  `field_min_value` decimal(20,6) DEFAULT NULL COMMENT '最小值',
  `field_max_value` decimal(20,6) DEFAULT NULL COMMENT '最大值',
  `field_regex` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '正则表达式',
  `field_regex_msg` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '正则错误提示',
  `field_options` text COLLATE utf8mb4_general_ci COMMENT '选项配置(JSON)',
  `field_dict_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `field_data_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据源URL',
  `field_data_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'GET' COMMENT '请求方式: GET, POST',
  `field_data_params` text COLLATE utf8mb4_general_ci COMMENT '请求参数(JSON)',
  `field_cascade_field` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '级联字段',
  `field_api_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'API类型',
  `is_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `is_readonly` tinyint(1) DEFAULT '0' COMMENT '是否只读: 0-否, 1-是',
  `is_disabled` tinyint(1) DEFAULT '0' COMMENT '是否禁用: 0-否, 1-是',
  `is_required` tinyint(1) DEFAULT '0' COMMENT '是否必填: 0-否, 1-是',
  `is_searchable` tinyint(1) DEFAULT '0' COMMENT '是否可搜索: 0-否, 1-是',
  `is_sortable` tinyint(1) DEFAULT '0' COMMENT '是否可排序: 0-否, 1-是',
  `is_filterable` tinyint(1) DEFAULT '0' COMMENT '是否可过滤: 0-否, 1-是',
  `is_exportable` tinyint(1) DEFAULT '1' COMMENT '是否可导出: 0-否, 1-是',
  `is_importable` tinyint(1) DEFAULT '1' COMMENT '是否可导入: 0-否, 1-是',
  `is_list_show` tinyint(1) DEFAULT '1' COMMENT '列表是否显示: 0-否, 1-是',
  `list_width` int DEFAULT NULL COMMENT '列表宽度(px)',
  `list_align` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LEFT' COMMENT '列表对齐方式: LEFT-左, CENTER-中, RIGHT-右',
  `list_fixed` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列表固定: LEFT-左固定, RIGHT-右固定',
  `is_form_show` tinyint(1) DEFAULT '1' COMMENT '表单是否显示: 0-否, 1-是',
  `form_col` int DEFAULT '24' COMMENT '表单栅格(1-24)',
  `form_row` int DEFAULT NULL COMMENT '表单行号',
  `field_validators` text COLLATE utf8mb4_general_ci COMMENT '验证规则配置(JSON)',
  `field_events` text COLLATE utf8mb4_general_ci COMMENT '字段事件配置(JSON)',
  `field_sort` int DEFAULT '0' COMMENT '排序号',
  `field_ext_props` text COLLATE utf8mb4_general_ci COMMENT '扩展属性(JSON)',
  `field_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`field_id`),
  KEY `idx_page_id` (`page_id`),
  KEY `idx_field_code` (`field_code`),
  KEY `idx_field_key` (`field_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='页面字段表';

-- ----------------------------
-- Table structure for meta_table
-- ----------------------------
DROP TABLE IF EXISTS `meta_table`;
CREATE TABLE `meta_table` (
  `table_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `table_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
  `table_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表描述',
  `table_type` int DEFAULT '1' COMMENT '表类型:1-业务表 2-字典表 3-树形表 4-关联表',
  `package_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包名',
  `module_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名',
  `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类名',
  `class_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类描述',
  `entity_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实体类名称',
  `author` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否启用: 1-是, 0-否',
  `generated` tinyint(1) DEFAULT '0' COMMENT '是否已生成: 1-是, 0-否',
  `gen_path` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成路径',
  `remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`table_id`),
  UNIQUE KEY `uk_table_name` (`table_name`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表配置';

-- ----------------------------
-- Table structure for meta_upgrade_install
-- ----------------------------
DROP TABLE IF EXISTS `meta_upgrade_install`;
CREATE TABLE `meta_upgrade_install` (
  `install_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '安装记录ID',
  `package_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包ID',
  `package_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包编码',
  `package_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包名称',
  `package_version` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包版本',
  `install_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'UPGRADE' COMMENT '安装类型: INSTALL-安装, UPGRADE-升级, ROLLBACK-回滚',
  `install_env` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PRODUCTION' COMMENT '安装环境: DEVELOPMENT-开发, TESTING-测试, STAGING-预发布, PRODUCTION-生产',
  `install_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '安装状态: PENDING-待处理, RUNNING-进行中, SUCCESS-成功, FAILED-失败, ROLLED_BACK-已回滚',
  `install_progress` int DEFAULT '0' COMMENT '安装进度(0-100)',
  `install_start_time` datetime DEFAULT NULL COMMENT '安装开始时间',
  `install_end_time` datetime DEFAULT NULL COMMENT '安装结束时间',
  `install_duration` int DEFAULT NULL COMMENT '安装耗时(秒)',
  `install_result` text COLLATE utf8mb4_general_ci COMMENT '安装结果(JSON)',
  `install_error_msg` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `install_log_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志文件路径',
  `install_backup_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备份文件路径',
  `operator_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `operator_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `is_backup` tinyint(1) DEFAULT '1' COMMENT '是否备份: 0-否, 1-是',
  `backup_file_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备份文件路径',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`install_id`),
  KEY `idx_package_id` (`package_id`),
  KEY `idx_package_version` (`package_version`),
  KEY `idx_install_status` (`install_status`),
  KEY `idx_install_time` (`install_start_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='升级安装记录表';

-- ----------------------------
-- Table structure for meta_upgrade_log
-- ----------------------------
DROP TABLE IF EXISTS `meta_upgrade_log`;
CREATE TABLE `meta_upgrade_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `install_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '安装记录ID',
  `log_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型: INFO-信息, WARN-警告, ERROR-错误, DEBUG-调试',
  `log_level` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'INFO' COMMENT '日志级别: TRACE-追踪, DEBUG-调试, INFO-信息, WARN-警告, ERROR-错误, FATAL-致命',
  `log_title` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日志标题',
  `log_content` text COLLATE utf8mb4_general_ci COMMENT '日志内容',
  `log_detail` text COLLATE utf8mb4_general_ci COMMENT '日志详情(JSON)',
  `log_stack` text COLLATE utf8mb4_general_ci COMMENT '异常堆栈',
  `log_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_install_id` (`install_id`),
  KEY `idx_log_type` (`log_type`),
  KEY `idx_log_level` (`log_level`),
  KEY `idx_log_time` (`log_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='升级日志表';

-- ----------------------------
-- Table structure for meta_upgrade_package
-- ----------------------------
DROP TABLE IF EXISTS `meta_upgrade_package`;
CREATE TABLE `meta_upgrade_package` (
  `package_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包ID',
  `package_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包编码',
  `package_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包名称',
  `package_version` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '升级包版本',
  `package_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PATCH' COMMENT '升级类型: MAJOR-主版本, MINOR-次版本, PATCH-补丁, HOTFIX-热修复',
  `package_target_version` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标版本',
  `package_source_version` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '源版本',
  `package_file_name` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '升级包文件名',
  `package_file_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '升级包文件路径',
  `package_file_size` bigint DEFAULT NULL COMMENT '升级包文件大小(字节)',
  `package_file_md5` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '升级包文件MD5',
  `package_desc` text COLLATE utf8mb4_general_ci COMMENT '升级包说明',
  `package_change_log` text COLLATE utf8mb4_general_ci COMMENT '变更日志',
  `package_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'DRAFT' COMMENT '升级包状态: DRAFT-草稿, TESTED-已测试, PUBLISHED-已发布, DEPRECATED-已废弃',
  `package_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '打包人ID',
  `package_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '打包人姓名',
  `package_time` datetime DEFAULT NULL COMMENT '打包时间',
  `product_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品编码',
  `product_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品名称',
  `package_download_count` int DEFAULT '0' COMMENT '下载次数',
  `package_install_count` int DEFAULT '0' COMMENT '安装次数',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`package_id`),
  UNIQUE KEY `uk_package_code` (`package_code`,`tenant_id`,`is_deleted`),
  KEY `idx_package_version` (`package_version`),
  KEY `idx_product_code` (`product_code`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='升级包表';

-- ----------------------------
-- Table structure for product_flow_log
-- ----------------------------
DROP TABLE IF EXISTS `product_flow_log`;
CREATE TABLE `product_flow_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `product_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `flow_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程类型: INSTALL-安装, UPGRADE-升级, CONFIG-配置, MAINTENANCE-维护',
  `flow_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '流程状态: PENDING-待处理, RUNNING-进行中, SUCCESS-成功, FAILED-失败',
  `flow_title` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '流程标题',
  `flow_content` text COLLATE utf8mb4_general_ci COMMENT '流程内容',
  `flow_result` text COLLATE utf8mb4_general_ci COMMENT '流程结果',
  `flow_error` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `flow_start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `flow_end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `flow_duration` int DEFAULT NULL COMMENT '耗时(秒)',
  `operator_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_flow_type` (`flow_type`),
  KEY `idx_flow_status` (`flow_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品流程日志表';

-- ----------------------------
-- Table structure for product_manage
-- ----------------------------
DROP TABLE IF EXISTS `product_manage`;
CREATE TABLE `product_manage` (
  `product_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `product_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品编码',
  `product_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `product_short_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品简称',
  `product_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SOFTWARE' COMMENT '产品类型: SOFTWARE-软件, SERVICE-服务, HARDWARE-硬件',
  `product_version` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品版本',
  `product_logo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品LOGO',
  `product_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品描述',
  `product_status` tinyint(1) DEFAULT '1' COMMENT '产品状态: 0-停用, 1-启用',
  `product_is_public` tinyint(1) DEFAULT '0' COMMENT '是否公开: 0-否, 1-是',
  `product_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `uk_product_code` (`product_code`,`tenant_id`,`is_deleted`),
  KEY `idx_product_name` (`product_name`),
  KEY `idx_product_type` (`product_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品管理表';

-- ----------------------------
-- Table structure for product_resources
-- ----------------------------
DROP TABLE IF EXISTS `product_resources`;
CREATE TABLE `product_resources` (
  `resource_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  `product_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `resource_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型: DOCUMENT-文档, IMAGE-图片, VIDEO-视频, SOFTWARE-软件',
  `resource_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `resource_path` varchar(512) COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源路径',
  `resource_url` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源URL',
  `resource_size` bigint DEFAULT NULL COMMENT '资源大小',
  `resource_ext` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源扩展名',
  `resource_mime_type` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'MIME类型',
  `resource_download_count` int DEFAULT '0' COMMENT '下载次数',
  `resource_status` tinyint(1) DEFAULT '1' COMMENT '资源状态: 0-停用, 1-启用',
  `resource_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`resource_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_resource_type` (`resource_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品资源表';

-- ----------------------------
-- Table structure for product_server
-- ----------------------------
DROP TABLE IF EXISTS `product_server`;
CREATE TABLE `product_server` (
  `server_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器ID',
  `product_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品ID',
  `server_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器编码',
  `server_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器名称',
  `server_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'APPLICATION' COMMENT '服务器类型: APPLICATION-应用, DATABASE-数据库, FILE-文件',
  `server_host` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务器主机',
  `server_port` int DEFAULT NULL COMMENT '服务器端口',
  `server_username` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `server_password` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码(加密)',
  `server_config` text COLLATE utf8mb4_general_ci COMMENT '服务器配置(JSON)',
  `server_status` tinyint(1) DEFAULT '1' COMMENT '服务器状态: 0-停用, 1-启用',
  `server_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务器描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`server_id`),
  UNIQUE KEY `uk_product_server` (`product_id`,`server_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品服务器表';

-- ----------------------------
-- Table structure for saas_func_perm
-- ----------------------------
DROP TABLE IF EXISTS `saas_func_perm`;
CREATE TABLE `saas_func_perm` (
  `perm_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `perm_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
  `perm_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `perm_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'MODULE' COMMENT '权限类型: MODULE-模块, FUNCTION-功能, OPERATION-操作',
  `perm_resource` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '资源标识',
  `perm_action` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作类型',
  `perm_status` tinyint(1) DEFAULT '1' COMMENT '权限状态: 0-停用, 1-启用',
  `perm_sort` int DEFAULT '0' COMMENT '排序号',
  `perm_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `uk_perm_code` (`perm_code`,`tenant_id`,`is_deleted`),
  KEY `idx_perm_name` (`perm_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='SAAS功能权限表';

-- ----------------------------
-- Table structure for sys_calendar
-- ----------------------------
DROP TABLE IF EXISTS `sys_calendar`;
CREATE TABLE `sys_calendar` (
  `calendar_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历ID',
  `calendar_date` date NOT NULL COMMENT '日历日期',
  `calendar_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'WORKDAY' COMMENT '日历类型: WORKDAY-工作日, WEEKEND-周末, HOLIDAY-节假日',
  `calendar_year` int NOT NULL COMMENT '年份',
  `calendar_month` int NOT NULL COMMENT '月份',
  `calendar_day` int NOT NULL COMMENT '日期',
  `calendar_lunar` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '农历日期',
  `calendar_festival` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '节日',
  `calendar_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '日历说明',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`calendar_id`),
  UNIQUE KEY `uk_calendar_date` (`calendar_date`),
  KEY `idx_calendar_year` (`calendar_year`),
  KEY `idx_calendar_month` (`calendar_month`),
  KEY `idx_calendar_type` (`calendar_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统日历表';

-- ----------------------------
-- Table structure for sys_calendar_push
-- ----------------------------
DROP TABLE IF EXISTS `sys_calendar_push`;
CREATE TABLE `sys_calendar_push` (
  `push_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '推送ID',
  `calendar_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历ID',
  `push_title` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '推送标题',
  `push_content` text COLLATE utf8mb4_general_ci COMMENT '推送内容',
  `push_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'REMIND' COMMENT '推送类型: REMIND-提醒, NOTICE-通知',
  `push_time` datetime NOT NULL COMMENT '推送时间',
  `push_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PENDING' COMMENT '推送状态: PENDING-待推送, SENT-已发送, FAILED-失败',
  `push_target_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ALL' COMMENT '目标类型: ALL-全部, DEPT-部门, ROLE-角色, USER-用户',
  `push_target_ids` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '目标ID列表',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`push_id`),
  KEY `idx_calendar_id` (`calendar_id`),
  KEY `idx_push_time` (`push_time`),
  KEY `idx_push_status` (`push_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统日历推送表';

-- ----------------------------
-- Table structure for wps_view_fields
-- ----------------------------
DROP TABLE IF EXISTS `wps_view_fields`;
CREATE TABLE `wps_view_fields` (
  `field_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `view_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '视图编码',
  `field_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段编码',
  `field_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `field_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'TEXT' COMMENT '字段类型',
  `field_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `field_readonly` tinyint(1) DEFAULT '0' COMMENT '是否只读: 0-否, 1-是',
  `field_required` tinyint(1) DEFAULT '0' COMMENT '是否必填: 0-否, 1-是',
  `field_sort` int DEFAULT '0' COMMENT '排序号',
  `field_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`field_id`),
  UNIQUE KEY `uk_view_field` (`view_code`,`field_code`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='WPS视图字段表';

SET FOREIGN_KEY_CHECKS = 1;
