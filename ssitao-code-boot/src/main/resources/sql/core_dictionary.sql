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

 Date: 09/03/2026 16:58:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
