-- =============================================
-- SSITAO 低代码平台 - 完整数据库脚本
-- 数据库: MySQL 8.0
-- 作者: ssitao-code
-- 日期: 2026-03-08
-- 说明:
--   本文件整合了所有数据库表结构和初始化数据
--   执行前请确保数据库已创建
--   路由模块划分:
--     - /iam/**  身份与访问管理模块
--     - /meta/** 元数据/低代码平台模块
--     - /core/** 公共核心模块
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 第一部分: IAM 身份与访问管理模块 (/iam/**)
-- 包含: 公司、部门、岗位、用户、账户、角色、权限、菜单、日志
-- =============================================

-- ----------------------------
-- 数据清理（为支持重复执行）
-- ----------------------------
DELETE FROM iam_account_dept WHERE 1=1;
DELETE FROM iam_role_permission WHERE 1=1;
DELETE FROM iam_account_role WHERE 1=1;
DELETE FROM iam_login_log WHERE 1=1;
DELETE FROM iam_operate_log WHERE 1=1;
DELETE FROM iam_account WHERE 1=1;
DELETE FROM iam_user WHERE 1=1;
DELETE FROM iam_permission WHERE 1=1;
DELETE FROM iam_role WHERE 1=1;
DELETE FROM iam_menu WHERE 1=1;
DELETE FROM iam_post WHERE 1=1;
DELETE FROM iam_department WHERE 1=1;
DELETE FROM iam_company WHERE 1=1;

-- ----------------------------
-- 删除所有表（为支持重新创建）
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_dept`;
DROP TABLE IF EXISTS `iam_role_permission`;
DROP TABLE IF EXISTS `iam_account_role`;
DROP TABLE IF EXISTS `iam_login_log`;
DROP TABLE IF EXISTS `iam_operate_log`;
DROP TABLE IF EXISTS `iam_account`;
DROP TABLE IF EXISTS `iam_user`;
DROP TABLE IF EXISTS `iam_permission`;
DROP TABLE IF EXISTS `iam_role`;
DROP TABLE IF EXISTS `iam_menu`;
DROP TABLE IF EXISTS `iam_post`;
DROP TABLE IF EXISTS `iam_department`;
DROP TABLE IF EXISTS `iam_company`;

-- ----------------------------
-- 1. 公司表 (iam_company)
-- ----------------------------
CREATE TABLE `iam_company` (
  `company_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司ID',
  `company_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  `company_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `company_short_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简称',
  `company_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ENTERPRISE' COMMENT '公司类型: ENTERPRISE-企业, BRANCH-分公司, HOLDING-控股',
  `company_level` int DEFAULT 1 COMMENT '公司级别',
  `company_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父公司ID',
  `company_address` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司地址',
  `company_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `company_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司邮箱',
  `company_website` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司网站',
  `company_logo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司Logo',
  `company_legal_rep` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法定代表人',
  `company_registration_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工商注册号',
  `company_tax_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '税务登记号',
  `company_status` tinyint(1) DEFAULT 1 COMMENT '公司状态: 0-注销, 1-正常',
  `company_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简介',
  `company_sort` int DEFAULT 0 COMMENT '排序号',
  `company_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `company_tree_level` int DEFAULT 1 COMMENT '树层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `uk_company_code` (`company_code`,`tenant_id`,`is_deleted`),
  KEY `idx_company_name` (`company_name`),
  KEY `idx_parent_id` (`company_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司表';

-- ----------------------------
-- 2. 部门表 (iam_department)
-- ----------------------------
CREATE TABLE `iam_department` (
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `dept_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
  `dept_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `dept_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'DEPARTMENT' COMMENT '部门类型: HEADQUARTERS-总部, DEPARTMENT-部门, TEAM-小组',
  `dept_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父部门ID',
  `dept_company_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属公司ID',
  `dept_leader_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门负责人ID',
  `dept_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门电话',
  `dept_address` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门地址',
  `dept_status` tinyint(1) DEFAULT 1 COMMENT '部门状态: 0-停用, 1-启用',
  `dept_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门描述',
  `dept_sort` int DEFAULT 0 COMMENT '排序号',
  `dept_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `dept_tree_level` int DEFAULT 1 COMMENT '树层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `uk_dept_code` (`dept_code`,`tenant_id`,`is_deleted`),
  KEY `idx_dept_name` (`dept_name`),
  KEY `idx_parent_id` (`dept_parent_id`),
  KEY `idx_company_id` (`dept_company_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';

-- ----------------------------
-- 3. 岗位表 (iam_post)
-- ----------------------------
CREATE TABLE `iam_post` (
  `post_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_level` int DEFAULT 1 COMMENT '岗位级别',
  `post_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'TECHNICAL' COMMENT '岗位类型: MANAGEMENT-管理, TECHNICAL-技术, BUSINESS-业务, FUNCTIONAL-职能',
  `post_status` tinyint(1) DEFAULT 1 COMMENT '岗位状态: 0-停用, 1-启用',
  `post_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位描述',
  `post_sort` int DEFAULT 0 COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `uk_post_code` (`post_code`,`tenant_id`,`is_deleted`),
  KEY `idx_post_name` (`post_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位表';

-- ----------------------------
-- 4. 用户表 (iam_user)
-- ----------------------------
CREATE TABLE `iam_user` (
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编码',
  `user_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `user_sex` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '性别: MALE-男, FEMALE-女, UNKNOWN-未知',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `user_id_card` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `user_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `user_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `user_photo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '照片URL',
  `user_address` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '居住地址',
  `user_native_place` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '籍贯',
  `user_nation` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '民族',
  `user_marital_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '婚姻状况: SINGLE-未婚, MARRIED-已婚, DIVORCED-离异',
  `user_political_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '政治面貌: MEMBER-党员, LEAGUE-团员, MASS-群众',
  `user_work_number` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `user_entry_date` date DEFAULT NULL COMMENT '入职日期',
  `user_probation_end_date` date DEFAULT NULL COMMENT '试用期结束日期',
  `user_employment_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '用工性质: FULL_TIME-全职, PART_TIME-兼职, INTERNSHIP-实习',
  `user_education` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '学历: DOCTOR-博士, MASTER-硕士, BACHELOR-本科, ASSOCIATE-大专, HIGH_SCHOOL-高中',
  `user_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ON_JOB' COMMENT '员工状态: ON_JOB-在职, RESIGN-离职, PROBATION-试用期',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_org_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织ID',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_code` (`user_code`,`tenant_id`,`is_deleted`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_user_phone` (`user_phone`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- 5. 账户表 (iam_account)
-- ----------------------------
CREATE TABLE `iam_account` (
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `account_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户编码',
  `account_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户名称',
  `account_password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户密码(加密)',
  `account_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `account_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `account_avatar` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像URL',
  `account_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SYSTEM' COMMENT '账户类型: SYSTEM-系统, THIRD-第三方',
  `account_source` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LOCAL' COMMENT '账户来源: LOCAL-本地, DINGTALK-钉钉, WECHAT-微信',
  `account_status` tinyint(1) DEFAULT 1 COMMENT '账户状态: 0-禁用, 1-启用',
  `account_is_admin` tinyint(1) DEFAULT 0 COMMENT '是否管理员: 0-否, 1-是',
  `account_last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `account_last_login_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录IP',
  `account_init_password` tinyint(1) DEFAULT 0 COMMENT '是否初始密码: 0-否, 1-是',
  `account_init_password_reset_time` datetime DEFAULT NULL COMMENT '初始密码重置时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_org_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织ID',
  `create_org_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织名称',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `create_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_org_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改组织ID',
  `modify_org_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改组织名称',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `modify_user_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人姓名',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `uk_account_code` (`account_code`,`tenant_id`,`is_deleted`),
  KEY `idx_account_name` (`account_name`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_status` (`account_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户表';

-- ----------------------------
-- 6. 角色表 (iam_role)
-- ----------------------------
CREATE TABLE `iam_role` (
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `role_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '角色类型: SYSTEM-系统, BUSINESS-业务',
  `role_level` int DEFAULT 5 COMMENT '角色级别',
  `role_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(1) DEFAULT 1 COMMENT '角色状态: 0-停用, 1-启用',
  `role_is_builtin` tinyint(1) DEFAULT 0 COMMENT '是否内置: 0-否, 1-是',
  `role_sort` int DEFAULT 0 COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_code` (`role_code`,`tenant_id`,`is_deleted`),
  KEY `idx_role_name` (`role_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- 7. 权限表 (iam_permission)
-- ----------------------------
CREATE TABLE `iam_permission` (
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `permission_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
  `permission_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUTTON' COMMENT '权限类型: MENU-菜单, BUTTON-按钮, API-接口, DATA-数据',
  `permission_resource` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限资源',
  `permission_action` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'VIEW' COMMENT '权限操作: VIEW-查看, ADD-新增, EDIT-编辑, DELETE-删除, EXPORT-导出, IMPORT-导入',
  `permission_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限描述',
  `permission_status` tinyint(1) DEFAULT 1 COMMENT '权限状态: 0-停用, 1-启用',
  `permission_is_builtin` tinyint(1) DEFAULT 0 COMMENT '是否内置: 0-否, 1-是',
  `permission_sort` int DEFAULT 0 COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`,`tenant_id`,`is_deleted`),
  KEY `idx_permission_name` (`permission_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

-- ----------------------------
-- 8. 菜单表 (iam_menu)
-- ----------------------------
CREATE TABLE `iam_menu` (
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'MENU' COMMENT '菜单类型: DIRECTORY-目录, MENU-菜单, BUTTON-按钮',
  `menu_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父菜单ID',
  `menu_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由路径',
  `menu_component` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `menu_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int DEFAULT 0 COMMENT '排序号',
  `menu_is_visible` tinyint(1) DEFAULT 1 COMMENT '是否可见: 0-否, 1-是',
  `menu_is_cached` tinyint(1) DEFAULT 0 COMMENT '是否缓存: 0-否, 1-是',
  `menu_is_affix` tinyint(1) DEFAULT 0 COMMENT '是否固定: 0-否, 1-是',
  `menu_permission` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单权限',
  `menu_redirect` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '重定向路径',
  `menu_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `menu_status` tinyint(1) DEFAULT 1 COMMENT '菜单状态: 0-停用, 1-启用',
  `menu_is_builtin` tinyint(1) DEFAULT 0 COMMENT '是否内置: 0-否, 1-是',
  `menu_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树路径',
  `menu_tree_level` int DEFAULT 1 COMMENT '树层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `uk_menu_code` (`menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_menu_name` (`menu_name`),
  KEY `idx_parent_id` (`menu_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';

-- ----------------------------
-- 9. 账户角色关联表 (iam_account_role)
-- ----------------------------
CREATE TABLE `iam_account_role` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `is_valid` tinyint(1) DEFAULT 1 COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户角色关联表';

-- ----------------------------
-- 10. 角色权限关联表 (iam_role_permission)
-- ----------------------------
CREATE TABLE `iam_role_permission` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(1) DEFAULT 1 COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- 11. 账户部门关联表 (iam_account_dept)
-- ----------------------------
CREATE TABLE `iam_account_dept` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `is_primary` tinyint(1) DEFAULT 0 COMMENT '是否主部门: 0-否, 1-是',
  `is_leader` tinyint(1) DEFAULT 0 COMMENT '是否部门负责人: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户部门关联表';

-- ----------------------------
-- 12. 登录日志表 (iam_login_log)
-- ----------------------------
CREATE TABLE `iam_login_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户ID',
  `account_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户编码',
  `account_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户名称',
  `login_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PASSWORD' COMMENT '登录方式: PASSWORD-密码, CAPTCHA-验证码, WECHAT-微信, DINGTALK-钉钉',
  `login_status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录状态: SUCCESS-成功, FAIL-失败',
  `login_fail_reason` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录失败原因',
  `login_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP',
  `login_location` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录地点',
  `login_device` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录设备: PC-电脑, MOBILE-手机, TABLET-平板',
  `login_browser` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器类型',
  `login_os` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '登出时间',
  `online_duration` int DEFAULT NULL COMMENT '在线时长(秒)',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_login_time` (`login_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志表';

-- ----------------------------
-- 13. 操作日志表 (iam_operate_log)
-- ----------------------------
CREATE TABLE `iam_operate_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `operate_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作类型: LOGIN-登录, LOGOUT-登出, CREATE-新增, UPDATE-更新, DELETE-删除, EXPORT-导出, IMPORT-导入',
  `module_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模块名称',
  `business_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务类型',
  `business_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务ID',
  `method_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `request_url` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方法',
  `request_params` text COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `response_result` text COLLATE utf8mb4_general_ci COMMENT '响应结果',
  `operate_status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作状态: SUCCESS-成功, FAIL-失败',
  `error_msg` varchar(1024) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '错误信息',
  `execute_duration` int DEFAULT NULL COMMENT '执行时长(毫秒)',
  `operator_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `operator_dept` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人部门',
  `operate_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `operate_location` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作地点',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_operate_type` (`operate_type`),
  KEY `idx_module_name` (`module_name`),
  KEY `idx_operator_id` (`operator_id`),
  KEY `idx_operate_time` (`operate_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';

-- =============================================
-- 第二部分: Core 公共核心模块 (/core/**)
-- 包含: 数据字典、系统配置
-- =============================================

DELETE FROM core_dictionary_item WHERE 1=1;
DELETE FROM core_dictionary WHERE 1=1;
DELETE FROM core_config WHERE 1=1;

DROP TABLE IF EXISTS `core_dictionary_item`;
DROP TABLE IF EXISTS `core_dictionary`;
DROP TABLE IF EXISTS `core_config`;

-- ----------------------------
-- 14. 数据字典表 (core_dictionary)
-- ----------------------------
CREATE TABLE `core_dictionary` (
  `dict_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `dict_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '字典类型: SYSTEM-系统, BUSINESS-业务',
  `dict_source` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'CUSTOM' COMMENT '字典来源: CUSTOM-自定义, SQL-SQL, API-接口',
  `dict_sql` text COLLATE utf8mb4_general_ci COMMENT '字典SQL',
  `dict_api` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典API',
  `dict_config` text COLLATE utf8mb4_general_ci COMMENT '字典配置(JSON)',
  `dict_status` tinyint(1) DEFAULT 1 COMMENT '字典状态: 0-停用, 1-启用',
  `dict_is_builtin` tinyint(1) DEFAULT 0 COMMENT '是否内置: 0-否, 1-是',
  `dict_sort` int DEFAULT 0 COMMENT '排序号',
  `dict_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `uk_dict_code` (`dict_code`,`tenant_id`,`is_deleted`),
  KEY `idx_dict_name` (`dict_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- 15. 数据字典项表 (core_dictionary_item)
-- ----------------------------
CREATE TABLE `core_dictionary_item` (
  `item_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项ID',
  `dict_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `item_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项编码',
  `item_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `item_value` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项值',
  `item_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父项ID',
  `item_level` int DEFAULT 1 COMMENT '项层级',
  `item_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项路径',
  `item_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项图标',
  `item_color` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项颜色',
  `item_css_class` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'CSS样式',
  `item_status` tinyint(1) DEFAULT 1 COMMENT '项状态: 0-停用, 1-启用',
  `item_is_default` tinyint(1) DEFAULT 0 COMMENT '是否默认: 0-否, 1-是',
  `item_sort` int DEFAULT 0 COMMENT '排序号',
  `item_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `uk_dict_item` (`dict_id`,`item_code`,`is_deleted`),
  KEY `idx_item_code` (`item_code`),
  KEY `idx_item_value` (`item_value`),
  KEY `idx_parent_id` (`item_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典项表';

-- ----------------------------
-- 16. 系统配置表 (core_config)
-- ----------------------------
CREATE TABLE `core_config` (
  `config_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置ID',
  `config_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置编码',
  `config_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `config_value` text COLLATE utf8mb4_general_ci COMMENT '配置值',
  `config_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'STRING' COMMENT '配置类型: STRING-字符串, NUMBER-数字, BOOLEAN-布尔, JSON-JSON',
  `config_category` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置分类',
  `config_status` tinyint(1) DEFAULT 1 COMMENT '配置状态: 0-停用, 1-启用',
  `config_is_builtin` tinyint(1) DEFAULT 0 COMMENT '是否内置: 0-否, 1-是',
  `config_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '配置描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`config_id`),
  UNIQUE KEY `uk_config_code` (`config_code`,`tenant_id`,`is_deleted`),
  KEY `idx_config_name` (`config_name`),
  KEY `idx_config_category` (`config_category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统配置表';

-- =============================================
-- 第三部分: Meta 元数据/低代码模块 (/meta/**)
-- 包含: 表管理、字段管理、表单管理、列表管理
-- =============================================

DELETE FROM meta_column WHERE 1=1;
DELETE FROM meta_form WHERE 1=1;
DELETE FROM meta_list WHERE 1=1;
DELETE FROM meta_table WHERE 1=1;

DROP TABLE IF EXISTS `meta_column`;
DROP TABLE IF EXISTS `meta_form`;
DROP TABLE IF EXISTS `meta_list`;
DROP TABLE IF EXISTS `meta_table`;

-- ----------------------------
-- 17. 元数据表配置 (meta_table)
-- ----------------------------
CREATE TABLE `meta_table` (
  `table_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `table_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
  `table_desc` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表描述',
  `table_type` int DEFAULT 1 COMMENT '表类型:1-业务表 2-字典表 3-树形表 4-关联表',
  `package_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '包名',
  `module_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名',
  `class_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类名',
  `class_desc` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类描述',
  `entity_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '实体类名称',
  `author` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '作者',
  `enabled` tinyint(1) DEFAULT 1 COMMENT '是否启用: 1-是, 0-否',
  `generated` tinyint(1) DEFAULT 0 COMMENT '是否已生成: 1-是, 0-否',
  `gen_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '生成路径',
  `remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`table_id`),
  UNIQUE KEY `uk_table_name` (`table_name`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表配置';

-- ----------------------------
-- 18. 元数据字段定义 (meta_column)
-- ----------------------------
CREATE TABLE `meta_column` (
  `column_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段ID',
  `table_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表ID',
  `column_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `column_desc` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字段描述',
  `column_type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
  `java_type` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Java类型',
  `java_field` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'Java字段名',
  `is_pk` tinyint(1) DEFAULT 0 COMMENT '是否主键',
  `is_increment` tinyint(1) DEFAULT 0 COMMENT '是否自增',
  `is_required` tinyint(1) DEFAULT 0 COMMENT '是否必填',
  `is_query` tinyint(1) DEFAULT 0 COMMENT '是否为查询条件',
  `query_type` int DEFAULT 1 COMMENT '查询方式:1-精确 2-模糊 3-范围',
  `is_display` tinyint(1) DEFAULT 0 COMMENT '是否为显示字段',
  `is_list` tinyint(1) DEFAULT 0 COMMENT '是否为列表显示',
  `is_form` tinyint(1) DEFAULT 0 COMMENT '是否为表单字段',
  `form_type` int DEFAULT 1 COMMENT '表单类型:1-文本框 2-文本域 3-下拉框 4-单选框 5-复选框 6-日期选择 7-文件上传 8-富文本',
  `dict_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典类型',
  `default_value` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '默认值',
  `column_sort` int DEFAULT 0 COMMENT '排序',
  `remark` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`column_id`),
  KEY `idx_table_id` (`table_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表字段定义';

-- ----------------------------
-- 19. 元数据表单配置 (meta_form)
-- ----------------------------
CREATE TABLE `meta_form` (
  `form_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单ID',
  `form_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单编码',
  `form_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单名称',
  `table_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联表ID',
  `form_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'NORMAL' COMMENT '表单类型: NORMAL-普通, POPUP-弹窗, STEPPER-分步',
  `form_layout` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'single' COMMENT '表单布局: single-单列, double-双列',
  `form_config` text COLLATE utf8mb4_general_ci COMMENT '表单配置(JSON)',
  `form_status` tinyint(1) DEFAULT 1 COMMENT '表单状态: 0-停用, 1-启用',
  `form_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表单描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`form_id`),
  UNIQUE KEY `uk_form_code` (`form_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据表单配置';

-- ----------------------------
-- 20. 元数据列表配置 (meta_list)
-- ----------------------------
CREATE TABLE `meta_list` (
  `list_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表ID',
  `list_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表编码',
  `list_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '列表名称',
  `table_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关联表ID',
  `list_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'TABLE' COMMENT '列表类型: TABLE-表格, TREE-树形, CARD-卡片',
  `list_config` text COLLATE utf8mb4_general_ci COMMENT '列表配置(JSON)',
  `list_status` tinyint(1) DEFAULT 1 COMMENT '列表状态: 0-停用, 1-启用',
  `list_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '列表描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`list_id`),
  UNIQUE KEY `uk_list_code` (`list_code`,`tenant_id`,`is_deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='元数据列表配置';

-- =============================================
-- 第四部分: 初始化数据
-- =============================================

-- ----------------------------
-- 一、IAM 模块初始化数据 (/iam/**)
-- ----------------------------

-- 重置自增ID
ALTER TABLE iam_account_dept AUTO_INCREMENT = 1;
ALTER TABLE iam_role_permission AUTO_INCREMENT = 1;
ALTER TABLE iam_account_role AUTO_INCREMENT = 1;
ALTER TABLE iam_login_log AUTO_INCREMENT = 1;
ALTER TABLE iam_operate_log AUTO_INCREMENT = 1;
ALTER TABLE iam_account AUTO_INCREMENT = 1;
ALTER TABLE iam_user AUTO_INCREMENT = 1;
ALTER TABLE iam_permission AUTO_INCREMENT = 1;
ALTER TABLE iam_role AUTO_INCREMENT = 1;
ALTER TABLE iam_menu AUTO_INCREMENT = 1;
ALTER TABLE iam_post AUTO_INCREMENT = 1;
ALTER TABLE iam_department AUTO_INCREMENT = 1;
ALTER TABLE iam_company AUTO_INCREMENT = 1;

-- 公司数据
INSERT INTO `iam_company` (`company_id`, `company_code`, `company_name`, `company_short_name`, `company_type`, `company_level`, `company_parent_id`, `company_address`, `company_phone`, `company_mail`, `company_website`, `company_legal_rep`, `company_registration_no`, `company_tax_no`, `company_status`, `company_desc`, `company_sort`, `company_tree_path`, `company_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'HQ', '思涛科技集团', '思涛科技', 'ENTERPRISE', 1, NULL, '北京市海淀区中关村大街1号', '010-88888888', 'contact@ssitao.com', 'https://www.ssitao.com', '李思涛', '91110108MA0198XXXX', '91110108MA0198XXXX', 1, '思涛科技集团总部，专注于企业级软件开发', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'BJ', '北京分公司', '北京思涛', 'BRANCH', 2, '1', '北京市朝阳区建国路88号SOHO现代城', '010-66666666', 'bj@ssitao.com', 'https://bj.ssitao.com', '王建国', '110105MA0198YYYY', '110105MA0198YYYY', 1, '北京分公司，负责北方区域业务', 2, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'SH', '上海分公司', '上海思涛', 'BRANCH', 2, '1', '上海市浦东新区陆家嘴金融中心A座', '021-55555555', 'sh@ssitao.com', 'https://sh.ssitao.com', '张海', '310115MA0198ZZZZ', '310115MA0198ZZZZ', 1, '上海分公司，负责华东区域业务', 3, '/1/3', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'SZ', '深圳分公司', '深圳思涛', 'BRANCH', 2, '1', '深圳市南山区科技园南区', '0755-88888888', 'sz@ssitao.com', 'https://sz.ssitao.com', '陈深圳', '440305MA0198AAAA', '440305MA0198AAAA', 1, '深圳分公司，负责华南区域业务', 4, '/1/4', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'CD', '成都分公司', '成都思涛', 'BRANCH', 2, '1', '成都市高新区天府软件园C区', '028-66666666', 'cd@ssitao.com', 'https://cd.ssitao.com', '刘成都', '510107MA0198BBBB', '510107MA0198BBBB', 1, '成都分公司，负责西南区域业务', 5, '/1/5', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- 部门数据
INSERT INTO `iam_department` (`dept_id`, `dept_code`, `dept_name`, `dept_type`, `dept_parent_id`, `dept_company_id`, `dept_leader_id`, `dept_phone`, `dept_address`, `dept_status`, `dept_desc`, `dept_sort`, `dept_tree_path`, `dept_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'ROOT', '集团总部', 'HEADQUARTERS', NULL, '1', '1', '010-88888881', '总部大楼1层', 1, '集团总部部门', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'TECH', '技术研发中心', 'DEPARTMENT', '1', '1', '2', '010-88888882', '总部大楼5层', 1, '负责公司技术研发和产品创新', 1, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEV_TEAM', '软件开发部', 'DEPARTMENT', '2', '1', '3', '010-88888883', '总部大楼5层A区', 1, '负责各业务线软件开发', 1, '/1/2/3', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'FRONTEND', '前端开发组', 'TEAM', '3', '1', NULL, '010-88888883', '总部大楼5层A1区', 1, '负责前端开发', 1, '/1/2/3/4', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'BACKEND', '后端开发组', 'TEAM', '3', '1', NULL, '010-88888884', '总部大楼5层A2区', 1, '负责后端开发', 2, '/1/2/3/5', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'QA', '质量保障部', 'DEPARTMENT', '2', '1', '4', '010-88888885', '总部大楼5层B区', 1, '负责软件测试和质量保证', 2, '/1/2/6', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'OPS', '运维部', 'DEPARTMENT', '2', '1', '5', '010-88888886', '总部大楼地下一层', 1, '负责系统运维和DevOps', 3, '/1/2/7', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'PRODUCT', '产品中心', 'DEPARTMENT', '1', '1', '6', '010-88888888', '总部大楼4层', 1, '负责产品规划与设计', 2, '/1/9', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'SALES', '销售中心', 'DEPARTMENT', '1', '1', '7', '010-88888891', '总部大楼3层', 1, '负责市场销售和客户拓展', 3, '/1/12', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('15', 'HR', '人力资源部', 'DEPARTMENT', '1', '1', '8', '010-88888892', '总部大楼2层', 1, '负责人力资源管理', 4, '/1/15', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('18', 'FIN', '财务部', 'DEPARTMENT', '1', '1', '9', '010-88888895', '总部大楼2层', 1, '负责财务管理', 5, '/1/18', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('19', 'ADMIN', '行政部', 'DEPARTMENT', '1', '1', NULL, '010-88888896', '总部大楼1层', 1, '负责行政后勤', 6, '/1/19', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- 岗位数据
INSERT INTO `iam_post` (`post_id`, `post_code`, `post_name`, `post_level`, `post_type`, `post_status`, `post_desc`, `post_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'CEO', '首席执行官', 1, 'MANAGEMENT', 1, '公司最高管理者，负责公司整体战略和运营', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'CTO', '首席技术官', 2, 'MANAGEMENT', 1, '技术负责人，负责技术研发方向', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'DIRECTOR', '总监', 4, 'MANAGEMENT', 1, '部门总监', 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'MGR', '部门经理', 5, 'MANAGEMENT', 1, '部门经理', 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'TL', '技术经理', 5, 'MANAGEMENT', 1, '技术团队管理', 7, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'PM', '项目经理', 5, 'MANAGEMENT', 1, '项目管理', 8, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'SE', '高级工程师', 6, 'TECHNICAL', 1, '高级技术专家', 10, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'SENIOR_DEV', '开发工程师', 7, 'TECHNICAL', 1, '开发工程师', 11, '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'QA', '测试工程师', 7, 'TECHNICAL', 1, '软件测试', 14, '1', NOW(), '1', NULL, NULL, 0, 0),
('16', 'DEVOPS', '运维工程师', 7, 'TECHNICAL', 1, '系统运维', 16, '1', NOW(), '1', NULL, NULL, 0, 0),
('18', 'PRODUCT_MGR', '产品经理', 6, 'BUSINESS', 1, '产品需求管理', 18, '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'SALES_MGR', '销售经理', 6, 'BUSINESS', 1, '销售团队管理', 24, '1', NOW(), '1', NULL, NULL, 0, 0),
('26', 'HR_MGR', '人事经理', 5, 'FUNCTIONAL', 1, '人力资源管理', 26, '1', NOW(), '1', NULL, NULL, 0, 0),
('28', 'FIN_MGR', '财务经理', 5, 'FUNCTIONAL', 1, '财务管理', 28, '1', NOW(), '1', NULL, NULL, 0, 0);

-- 用户数据
INSERT INTO `iam_user` (`user_id`, `user_code`, `user_name`, `user_sex`, `user_birthday`, `user_id_card`, `user_phone`, `user_mail`, `user_address`, `user_native_place`, `user_nation`, `user_marital_status`, `user_political_status`, `user_work_number`, `user_entry_date`, `user_probation_end_date`, `user_employment_type`, `user_education`, `user_status`, `tenant_id`, `create_org_id`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'ADMIN', '李思涛', 'MALE', '1985-03-15', '110108198503150011', '13800000001', 'lisi@ssitao.com', '北京市海淀区知春路', '北京', '汉', 'MARRIED', 'MEMBER', 'EMP001', '2015-01-01', '2015-04-01', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('2', 'ZHANGWEI', '张伟', 'MALE', '1988-07-22', '110108198807220012', '13800000002', 'zhangwei@ssitao.com', '北京市朝阳区望京', '山东', '汉', 'MARRIED', 'MEMBER', 'EMP002', '2016-03-15', '2016-06-15', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('3', 'LIHUA', '李华', 'MALE', '1990-05-10', '110108199005100013', '13800000003', 'lihua@ssitao.com', '北京市海淀区上地', '河北', '汉', 'SINGLE', 'LEAGUE', 'EMP003', '2017-06-01', '2017-09-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('4', 'WANGMEI', '王梅', 'FEMALE', '1992-11-25', '110108199211250014', '13800000004', 'wangmei@ssitao.com', '北京市西城区金融街', '北京', '汉', 'SINGLE', 'MEMBER', 'EMP004', '2018-01-10', '2018-04-10', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('5', 'ZHAOQiang', '赵强', 'MALE', '1991-08-08', '110108199108080015', '13800000005', 'zhaoqiang@ssitao.com', '北京市东城区王府井', '河南', '汉', 'MARRIED', 'MEMBER', 'EMP005', '2018-07-01', '2018-10-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('6', 'SUNLI', '孙莉', 'FEMALE', '1993-02-14', '110108199302140016', '13800000006', 'sunli@ssitao.com', '北京市海淀区中关村', '山西', '汉', 'SINGLE', 'MEMBER', 'EMP006', '2019-03-01', '2019-06-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('7', 'ZHOUMIN', '周敏', 'MALE', '1989-12-01', '110108198912010017', '13800000007', 'zhoumin@ssitao.com', '北京市朝阳区国贸', '辽宁', '汉', 'MARRIED', 'MEMBER', 'EMP007', '2019-08-01', NULL, 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('8', 'WUQING', '吴青', 'FEMALE', '1994-06-20', '110108199406200018', '13800000008', 'wuqing@ssitao.com', '北京市海淀区五道口', '江苏', '汉', 'SINGLE', 'LEAGUE', 'EMP008', '2020-01-15', '2020-04-15', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('9', 'ZHENGFU', '郑福', 'MALE', '1987-09-12', '110108198709120019', '13800000009', 'zhengfu@ssitao.com', '北京市丰台区', '四川', '汉', 'MARRIED', 'MEMBER', 'EMP009', '2020-06-01', NULL, 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0);

-- 账户数据（密码均为123456，BCrypt加密）
INSERT INTO `iam_account` (`account_id`, `account_code`, `account_name`, `account_password`, `account_phone`, `account_mail`, `account_avatar`, `account_type`, `account_source`, `account_status`, `account_is_admin`, `account_last_login_time`, `account_last_login_ip`, `account_init_password`, `account_init_password_reset_time`, `tenant_id`, `create_org_id`, `create_org_name`, `create_user_id`, `create_user_name`, `create_time`, `modify_org_id`, `modify_org_name`, `modify_user_id`, `modify_user_name`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'admin', '李思涛', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000001', 'lisi@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 1, NOW(), '127.0.0.1', 0, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('2', 'zhangwei', '张伟', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000002', 'zhangwei@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('3', 'lihua', '李华', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000003', 'lihua@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.100', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('4', 'wangmei', '王梅', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000004', 'wangmei@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.101', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('5', 'zhaoqiang', '赵强', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000005', 'zhaoqiang@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.102', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('6', 'sunli', '孙莉', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000006', 'sunli@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.103', 1, NULL, '1', '9', '产品中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('7', 'zhoumin', '周敏', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000007', 'zhoumin@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.104', 1, NULL, '1', '12', '销售中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('8', 'wuqing', '吴青', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000008', 'wuqing@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.105', 1, NULL, '1', '15', '人力资源部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('9', 'zhengfu', '郑福', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000009', 'zhengfu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.106', 1, NULL, '1', '18', '财务部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0);

-- 角色数据
INSERT INTO `iam_role` (`role_id`, `role_code`, `role_name`, `role_type`, `role_level`, `role_desc`, `role_status`, `role_is_builtin`, `role_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'SUPER_ADMIN', '超级管理员', 'SYSTEM', 1, '系统超级管理员，拥有所有权限', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'ADMIN', '系统管理员', 'SYSTEM', 2, '系统管理员，拥有大部分管理权限', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEPT_ADMIN', '部门管理员', 'BUSINESS', 3, '部门管理员，管理本部门人员和权限', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'DEV_LEADER', '开发组长', 'BUSINESS', 3, '开发团队负责人', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'DEVELOPER', '开发人员', 'BUSINESS', 4, '开发人员角色，拥有代码开发权限', 1, 0, 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'TESTER', '测试人员', 'BUSINESS', 4, '测试人员角色，拥有测试权限', 1, 0, 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'OPERATOR', '运维人员', 'BUSINESS', 4, '运维人员角色，拥有系统运维权限', 1, 0, 7, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'PRODUCT_MGR', '产品经理', 'BUSINESS', 4, '产品经理角色，拥有产品管理权限', 1, 0, 8, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'DESIGNER', '设计师', 'BUSINESS', 4, 'UI/UX设计师角色', 1, 0, 9, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'SALES', '销售人员', 'BUSINESS', 4, '销售人员角色', 1, 0, 10, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'HR', '人事专员', 'BUSINESS', 4, '人事专员角色', 1, 0, 11, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'FINANCE', '财务人员', 'BUSINESS', 4, '财务人员角色', 1, 0, 12, '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'USER', '普通用户', 'BUSINESS', 5, '普通用户角色，拥有基本查看权限', 1, 1, 13, '1', NOW(), '1', NULL, NULL, 0, 0);

-- 权限数据
INSERT INTO `iam_permission` (`permission_id`, `permission_code`, `permission_name`, `permission_type`, `permission_resource`, `permission_action`, `permission_desc`, `permission_status`, `permission_is_builtin`, `permission_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`) VALUES
-- 系统管理
('1', 'system:manage', '系统管理', 'MENU', '/system', 'VIEW', '系统管理模块', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('2', 'system:user:view', '用户查看', 'BUTTON', '/system/user', 'VIEW', '查看用户信息', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('3', 'system:user:add', '用户新增', 'BUTTON', '/system/user', 'ADD', '新增用户', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('6', 'system:role:view', '角色查看', 'BUTTON', '/system/role', 'VIEW', '查看角色', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
-- 组织管理
('10', 'org:manage', '组织管理', 'MENU', '/org', 'VIEW', '组织管理模块', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('11', 'org:company:view', '公司查看', 'BUTTON', '/org/company', 'VIEW', '查看公司', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('13', 'org:dept:view', '部门查看', 'BUTTON', '/org/dept', 'VIEW', '查看部门', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
-- 菜单管理
('16', 'menu:manage', '菜单管理', 'MENU', '/menu', 'VIEW', '菜单管理模块', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
-- 字典管理
('20', 'dict:manage', '字典管理', 'MENU', '/dict', 'VIEW', '字典管理模块', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 日志管理
('30', 'log:manage', '日志管理', 'MENU', '/log', 'VIEW', '日志管理模块', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
-- 低代码平台
('40', 'meta:manage', '低代码平台', 'MENU', '/meta', 'VIEW', '低代码平台管理', 1, 1, 6, '1', NOW(), '1', NULL, NULL, 0),
('41', 'meta:table:view', '表管理查看', 'BUTTON', '/meta/table', 'VIEW', '查看表管理', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('44', 'meta:form:view', '表单管理查看', 'BUTTON', '/meta/form', 'VIEW', '查看表单', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
('45', 'meta:list:view', '列表管理查看', 'BUTTON', '/meta/list', 'VIEW', '查看列表', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0);

-- 菜单数据
INSERT INTO `iam_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_type`, `menu_parent_id`, `menu_path`, `menu_component`, `menu_icon`, `menu_sort`, `menu_is_visible`, `menu_is_cached`, `menu_is_affix`, `menu_permission`, `menu_redirect`, `menu_desc`, `menu_status`, `menu_is_builtin`, `menu_tree_path`, `menu_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 一级菜单
('1', 'dashboard', '工作台', 'DIRECTORY', NULL, '/console', NULL, 'DashboardOutlined', 1, 1, 0, 0, NULL, '/console', '工作台', 1, 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'system', '系统管理', 'DIRECTORY', NULL, '/iam', NULL, 'SettingOutlined', 2, 1, 0, 0, NULL, '/iam', '系统管理', 1, 1, '/2', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'org', '组织管理', 'DIRECTORY', NULL, '/iam/org', NULL, 'ApartmentOutlined', 3, 1, 0, 0, NULL, '/iam/org', '组织管理', 1, 1, '/3', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'meta', '低代码平台', 'DIRECTORY', NULL, '/meta/table', NULL, 'BuildOutlined', 4, 1, 0, 0, NULL, '/meta/table', '低代码平台', 1, 1, '/4', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'workflow', '工作流', 'DIRECTORY', NULL, '/workflow', NULL, 'BranchOutlined', 5, 1, 0, 0, NULL, '/workflow', '工作流', 1, 1, '/5', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'monitor', '系统监控', 'DIRECTORY', NULL, '/iam/monitor', NULL, 'MonitorOutlined', 6, 1, 0, 0, NULL, '/iam/monitor', '系统监控', 1, 1, '/6', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'resource', '资源管理', 'DIRECTORY', NULL, '/resource', NULL, 'CloudOutlined', 7, 1, 0, 0, NULL, '/resource', '资源管理', 1, 1, '/7', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 工作台子菜单
('10', 'dashboard:analysis', '分析页', 'MENU', '1', '/console', 'dashboard/Analysis', 'AreaChartOutlined', 1, 1, 0, 0, NULL, NULL, '分析页', 1, 1, '/1/10', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'dashboard:workbench', '工作台', 'MENU', '1', '/console', 'dashboard/Workbench', 'AppstoreOutlined', 2, 1, 0, 0, NULL, NULL, '工作台', 1, 1, '/1/11', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'dashboard:notice', '通知公告', 'MENU', '1', '/notice', 'dashboard/Notice', 'NotificationOutlined', 3, 1, 0, 0, NULL, NULL, '通知公告', 1, 1, '/1/12', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统管理子菜单
('20', 'system:account', '账户管理', 'MENU', '2', '/iam/account', 'system/account/AccountList', 'UserOutlined', 1, 1, 0, 0, NULL, NULL, '账户管理', 1, 1, '/2/20', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'system:user', '用户管理', 'MENU', '2', '/iam/user', 'system/user/UserList', 'TeamOutlined', 2, 1, 0, 0, 'system:user:view', NULL, '用户管理', 1, 1, '/2/21', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'system:role', '角色管理', 'MENU', '2', '/iam/role', 'system/role/RoleList', 'SafetyCertificateOutlined', 3, 1, 0, 0, 'system:role:view', NULL, '角色管理', 1, 1, '/2/22', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'system:permission', '权限管理', 'MENU', '2', '/iam/permission', 'system/permission/PermissionList', 'KeyOutlined', 4, 1, 0, 0, NULL, NULL, '权限管理', 1, 1, '/2/23', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'system:menu', '菜单管理', 'MENU', '2', '/iam/menu', 'system/menu/MenuList', 'MenuOutlined', 5, 1, 0, 0, 'menu:view', NULL, '菜单管理', 1, 1, '/2/24', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('25', 'system:dict', '字典管理', 'MENU', '2', '/iam/dict', 'system/dict/DictList', 'BookOutlined', 6, 1, 0, 0, 'dict:view', NULL, '字典管理', 1, 1, '/2/25', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('26', 'system:config', '系统配置', 'MENU', '2', '/iam/config', 'system/config/ConfigList', 'SettingOutlined', 7, 1, 0, 0, NULL, NULL, '系统配置', 1, 1, '/2/26', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('27', 'system:tenant', '租户管理', 'MENU', '2', '/iam/tenant', 'system/tenant/TenantList', 'GlobalOutlined', 8, 1, 0, 0, NULL, NULL, '租户管理', 1, 1, '/2/27', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 组织管理子菜单
('30', 'org:company', '公司管理', 'MENU', '3', '/iam/org/company', 'org/company/CompanyList', 'HomeOutlined', 1, 1, 0, 0, 'org:company:view', NULL, '公司管理', 1, 1, '/3/30', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('31', 'org:dept', '部门管理', 'MENU', '3', '/iam/org/department', 'org/dept/DeptList', 'ApartmentOutlined', 2, 1, 0, 0, 'org:dept:view', NULL, '部门管理', 1, 1, '/3/31', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('32', 'org:post', '岗位管理', 'MENU', '3', '/iam/org/post', 'org/post/PostList', 'IdcardOutlined', 3, 1, 0, 0, 'org:post:view', NULL, '岗位管理', 1, 1, '/3/32', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 低代码平台子菜单
('40', 'meta:table', '表管理', 'MENU', '4', '/meta/table', 'meta/table/TableList', 'TableOutlined', 1, 1, 0, 0, 'meta:table:view', NULL, '表管理', 1, 1, '/4/40', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('41', 'meta:column', '字段管理', 'MENU', '4', '/meta/column', 'meta/column/ColumnList', 'AppstoreOutlined', 2, 1, 0, 0, NULL, NULL, '字段管理', 1, 1, '/4/41', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('42', 'meta:form', '表单管理', 'MENU', '4', '/meta/form', 'meta/form/FormList', 'FormOutlined', 3, 1, 0, 0, 'meta:form:view', NULL, '表单管理', 1, 1, '/4/42', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('43', 'meta:list', '列表管理', 'MENU', '4', '/meta/list', 'meta/list/ListList', 'UnorderedListOutlined', 4, 1, 0, 0, 'meta:list:view', NULL, '列表管理', 1, 1, '/4/43', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('44', 'meta:entity', '实体管理', 'MENU', '4', '/meta/entity', 'meta/entity/EntityList', 'DatabaseOutlined', 5, 1, 0, 0, NULL, NULL, '实体管理', 1, 1, '/4/44', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('45', 'meta:code', '代码生成', 'MENU', '4', '/meta/code', 'meta/code/CodeGen', 'CodeOutlined', 6, 1, 0, 0, NULL, NULL, '代码生成', 1, 1, '/4/45', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('46', 'meta:dynamic', '动态CRUD', 'MENU', '4', '/meta/dynamic', 'meta/dynamic/DynamicList', 'ThunderboltOutlined', 7, 1, 0, 0, NULL, NULL, '动态CRUD', 1, 1, '/4/46', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 工作流子菜单
('50', 'workflow:list', '流程列表', 'MENU', '5', '/workflow/list', 'workflow/WorkflowList', 'OrderedListOutlined', 1, 1, 0, 0, 'workflow:view', NULL, '流程列表', 1, 1, '/5/50', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('51', 'workflow:design', '流程设计', 'MENU', '5', '/workflow/design', 'workflow/WorkflowDesign', 'DragOutlined', 2, 1, 0, 0, 'workflow:design', NULL, '流程设计', 1, 1, '/5/51', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('52', 'workflow:task', '我的任务', 'MENU', '5', '/workflow/task', 'workflow/MyTask', 'TaskOutlined', 3, 1, 0, 0, NULL, NULL, '我的任务', 1, 1, '/5/52', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('53', 'workflow:instance', '流程实例', 'MENU', '5', '/workflow/instance', 'workflow/WorkflowInstance', 'HistoryOutlined', 4, 1, 0, 0, NULL, NULL, '流程实例', 1, 1, '/5/53', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统监控子菜单
('60', 'monitor:login-log', '登录日志', 'MENU', '6', '/iam/audit/login-log', 'monitor/LoginLogList', 'FileSearchOutlined', 1, 1, 0, 0, 'log:login:view', NULL, '登录日志', 1, 1, '/6/60', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('61', 'monitor:operate-log', '操作日志', 'MENU', '6', '/iam/audit/operate-log', 'monitor/OperateLogList', 'FileSyncOutlined', 2, 1, 0, 0, 'log:operate:view', NULL, '操作日志', 1, 1, '/6/61', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('62', 'monitor:online', '在线用户', 'MENU', '6', '/iam/monitor/online', 'monitor/OnlineUser', 'TeamOutlined', 3, 1, 0, 0, NULL, NULL, '在线用户', 1, 1, '/6/62', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('63', 'monitor:cache', '缓存监控', 'MENU', '6', '/iam/monitor/cache', 'monitor/CacheMonitor', 'DatabaseOutlined', 4, 1, 0, 0, NULL, NULL, '缓存监控', 1, 1, '/6/63', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('64', 'monitor:schedule', '定时任务', 'MENU', '6', '/iam/monitor/schedule', 'monitor/ScheduleList', 'ClockCircleOutlined', 5, 1, 0, 0, NULL, NULL, '定时任务', 1, 1, '/6/64', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 资源管理子菜单
('70', 'resource:file', '文件管理', 'MENU', '7', '/resource/file', 'resource/file/FileList', 'FolderOutlined', 1, 1, 0, 0, NULL, NULL, '文件管理', 1, 1, '/7/70', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('71', 'resource:attachment', '附件管理', 'MENU', '7', '/resource/attachment', 'resource/attachment/AttachmentList', 'FileOutlined', 2, 1, 0, 0, NULL, NULL, '附件管理', 1, 1, '/7/71', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- 账户角色关联
INSERT INTO `iam_account_role` (`id`, `account_id`, `role_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, '1', NOW(), '1', 0),
('2', '2', '2', 1, '1', NOW(), '1', 0),
('3', '3', '4', 1, '1', NOW(), '1', 0),
('4', '4', '5', 1, '1', NOW(), '1', 0),
('5', '5', '5', 1, '1', NOW(), '1', 0),
('6', '6', '8', 1, '1', NOW(), '1', 0),
('7', '7', '10', 1, '1', NOW(), '1', 0),
('8', '8', '11', 1, '1', NOW(), '1', 0),
('9', '9', '12', 1, '1', NOW(), '1', 0);

-- 角色权限关联（超级管理员拥有所有权限）
INSERT INTO `iam_role_permission` (`id`, `role_id`, `permission_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, '1', NOW(), '1', 0),
('2', '1', '2', 1, '1', NOW(), '1', 0),
('3', '1', '3', 1, '1', NOW(), '1', 0),
('4', '1', '6', 1, '1', NOW(), '1', 0),
('5', '1', '10', 1, '1', NOW(), '1', 0),
('6', '1', '11', 1, '1', NOW(), '1', 0),
('7', '1', '13', 1, '1', NOW(), '1', 0),
('8', '1', '16', 1, '1', NOW(), '1', 0),
('9', '1', '20', 1, '1', NOW(), '1', 0),
('10', '1', '30', 1, '1', NOW(), '1', 0),
('11', '1', '40', 1, '1', NOW(), '1', 0),
('12', '1', '41', 1, '1', NOW(), '1', 0),
('13', '1', '44', 1, '1', NOW(), '1', 0),
('14', '1', '45', 1, '1', NOW(), '1', 0);

-- 账户部门关联
INSERT INTO `iam_account_dept` (`id`, `account_id`, `dept_id`, `is_primary`, `is_leader`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, 1, '1', NOW(), '1', 0),
('2', '2', '2', 1, 1, '1', NOW(), '1', 0),
('3', '3', '3', 1, 1, '1', NOW(), '1', 0),
('4', '4', '3', 1, 0, '1', NOW(), '1', 0),
('5', '5', '3', 1, 0, '1', NOW(), '1', 0),
('6', '6', '9', 1, 1, '1', NOW(), '1', 0),
('7', '7', '12', 1, 1, '1', NOW(), '1', 0),
('8', '8', '15', 1, 1, '1', NOW(), '1', 0),
('9', '9', '18', 1, 1, '1', NOW(), '1', 0);

-- 登录日志测试数据
INSERT INTO `iam_login_log` (`log_id`, `account_id`, `account_code`, `account_name`, `login_type`, `login_status`, `login_fail_reason`, `login_ip`, `login_location`, `login_device`, `login_browser`, `login_os`, `login_time`, `logout_time`, `online_duration`, `tenant_id`) VALUES
('1', '1', 'admin', '李思涛', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR), 3600, '1'),
('2', '1', 'admin', '李思涛', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 1 HOUR), NULL, NULL, '1'),
('3', '2', 'zhangwei', '张伟', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.100', '北京市海淀区', 'PC', 'Firefox 121.0', 'macOS 14', DATE_SUB(NOW(), INTERVAL 30 MINUTE), NULL, NULL, '1');

-- 操作日志测试数据
INSERT INTO `iam_operate_log` (`log_id`, `operate_type`, `module_name`, `business_type`, `business_id`, `method_name`, `request_url`, `request_method`, `request_params`, `response_result`, `operate_status`, `error_msg`, `execute_duration`, `operator_id`, `operator_name`, `operator_dept`, `operate_ip`, `operate_location`, `operate_time`, `tenant_id`) VALUES
('1', 'LOGIN', '认证模块', '用户登录', '1', 'login', '/api/auth/login', 'POST', '{"username":"admin"}', '{"code":200,"message":"登录成功"}', 'SUCCESS', NULL, 150, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('2', 'CREATE', '用户管理', '新增用户', '2', 'createUser', '/api/system/user', 'POST', '{"username":"zhangwei"}', '{"code":200,"message":"创建成功"}', 'SUCCESS', NULL, 80, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1');

-- ----------------------------
-- 二、Core 模块初始化数据 (/core/**)
-- ----------------------------

ALTER TABLE core_dictionary_item AUTO_INCREMENT = 1;
ALTER TABLE core_dictionary AUTO_INCREMENT = 1;
ALTER TABLE core_config AUTO_INCREMENT = 1;

-- 字典类型
INSERT INTO `core_dictionary` (`dict_id`, `dict_code`, `dict_name`, `dict_type`, `dict_source`, `dict_sql`, `dict_api`, `dict_config`, `dict_status`, `dict_is_builtin`, `dict_sort`, `dict_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'user_status', '用户状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 1, '用户账户状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sex', '性别', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 2, '性别字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'yes_no', '是否', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 3, '是否字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'status', '状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 4, '通用状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'menu_type', '菜单类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 5, '菜单类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('20', 'customer_level', '客户级别', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 1, '客户级别ABC分类', '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'customer_source', '客户来源', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 2, '客户来源渠道', '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'order_status', '订单状态', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 4, '订单状态', '1', NOW(), '1', NULL, NULL, 0, 0);

-- 字典项
INSERT INTO `core_dictionary_item` (`item_id`, `dict_id`, `item_code`, `item_name`, `item_value`, `item_parent_id`, `item_level`, `item_path`, `item_icon`, `item_color`, `item_css_class`, `item_status`, `item_is_default`, `item_sort`, `item_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`) VALUES
('1', '1', 'ENABLED', '启用', '1', NULL, 1, '/1', NULL, 'green', 'success', 1, 1, 1, '启用状态', '1', NOW(), '1', NULL, NULL, 0),
('2', '1', 'DISABLED', '禁用', '0', NULL, 1, '/2', NULL, 'red', 'danger', 1, 0, 2, '禁用状态', '1', NOW(), '1', NULL, NULL, 0),
('3', '2', 'MALE', '男', '1', NULL, 1, '/3', NULL, 'blue', 'primary', 1, 0, 1, '男性', '1', NOW(), '1', NULL, NULL, 0),
('4', '2', 'FEMALE', '女', '2', NULL, 1, '/4', NULL, 'pink', 'danger', 1, 0, 2, '女性', '1', NOW(), '1', NULL, NULL, 0),
('6', '3', 'YES', '是', '1', NULL, 1, '/6', NULL, 'green', 'success', 1, 0, 1, '是', '1', NOW(), '1', NULL, NULL, 0),
('7', '3', 'NO', '否', '0', NULL, 1, '/7', NULL, 'red', 'danger', 1, 0, 2, '否', '1', NOW(), '1', NULL, NULL, 0),
('8', '4', 'ENABLED', '启用', '1', NULL, 1, '/8', NULL, 'green', 'success', 1, 1, 1, '启用', '1', NOW(), '1', NULL, NULL, 0),
('9', '4', 'DISABLED', '禁用', '0', NULL, 1, '/9', NULL, 'red', 'danger', 1, 0, 2, '禁用', '1', NOW(), '1', NULL, NULL, 0),
('50', '20', 'A', 'A级客户', '1', NULL, 1, '/50', NULL, 'red', 'danger', 1, 0, 1, '重要客户', '1', NOW(), '1', NULL, NULL, 0),
('51', '20', 'B', 'B级客户', '2', NULL, 1, '/51', NULL, 'orange', 'warning', 1, 0, 2, '普通客户', '1', NOW(), '1', NULL, NULL, 0),
('52', '20', 'C', 'C级客户', '3', NULL, 1, '/52', NULL, 'green', 'success', 1, 0, 3, '潜在客户', '1', NOW(), '1', NULL, NULL, 0),
('63', '23', 'PENDING', '待处理', '1', NULL, 1, '/63', NULL, 'orange', 'warning', 1, 0, 1, '待处理', '1', NOW(), '1', NULL, NULL, 0),
('64', '23', 'CONFIRMED', '已确认', '2', NULL, 1, '/64', NULL, 'blue', 'primary', 1, 0, 2, '已确认', '1', NOW(), '1', NULL, NULL, 0),
('65', '23', 'COMPLETED', '已完成', '5', NULL, 1, '/67', NULL, 'green', 'success', 1, 0, 5, '已完成', '1', NOW(), '1', NULL, NULL, 0);

-- 系统配置
INSERT INTO `core_config` (`config_id`, `config_code`, `config_name`, `config_value`, `config_type`, `config_category`, `config_status`, `config_is_builtin`, `config_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'system.name', '系统名称', '思涛低代码平台', 'STRING', 'system', 1, 1, '系统名称配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'system.logo', '系统Logo', '/logo.png', 'STRING', 'system', 1, 1, '系统Logo路径', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'system.version', '系统版本', '2.0.0', 'STRING', 'system', 1, 1, '系统版本号', '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'security.password.salt', '密码盐值', 'ssitao2026', 'STRING', 'security', 1, 1, '密码加密盐值', '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'security.password.minLength', '密码最小长度', '6', 'NUMBER', 'security', 1, 1, '密码最小长度要求', '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'security.session.timeout', '会话超时时间', '7200', 'NUMBER', 'security', 1, 1, '会话超时时间(秒)', '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 三、Meta 模块初始化数据 (/meta/**)
-- ----------------------------

ALTER TABLE meta_column AUTO_INCREMENT = 1;
ALTER TABLE meta_form AUTO_INCREMENT = 1;
ALTER TABLE meta_list AUTO_INCREMENT = 1;
ALTER TABLE meta_table AUTO_INCREMENT = 1;

-- 元数据表配置
INSERT INTO `meta_table` (`table_id`, `table_name`, `table_desc`, `table_type`, `package_name`, `module_name`, `class_name`, `class_desc`, `entity_name`, `author`, `enabled`, `generated`, `gen_path`, `remark`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'sys_user', '系统用户', 1, 'com.ssitao.code', 'system', 'SysUser', '系统用户', 'SysUser', 'ssitao-code', 1, 1, '/gen/java/com/ssitao/code/system', '系统用户表', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sys_role', '系统角色', 1, 'com.ssitao.code', 'system', 'SysRole', '系统角色', 'SysRole', 'ssitao-code', 1, 1, '/gen/java/com/ssitao/code/system', '系统角色表', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'customer', '客户信息', 1, 'com.ssitao.code', 'crm', 'Customer', '客户信息', 'Customer', 'ssitao-code', 1, 0, NULL, '客户信息管理', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'product', '产品信息', 1, 'com.ssitao.code', 'product', 'Product', '产品信息', 'Product', 'ssitao-code', 1, 0, NULL, '产品信息管理', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'order', '订单信息', 1, 'com.ssitao.code', 'order', 'Order', '订单信息', 'Order', 'ssitao-code', 1, 0, NULL, '订单信息管理', '1', NOW(), '1', NULL, NULL, 0, 0);

-- 元数据字段定义
INSERT INTO `meta_column` (`column_id`, `table_id`, `column_name`, `column_desc`, `column_type`, `java_type`, `java_field`, `is_pk`, `is_increment`, `is_required`, `is_query`, `query_type`, `is_display`, `is_list`, `is_form`, `form_type`, `dict_type`, `default_value`, `column_sort`, `remark`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 客户信息表字段
('1', '3', 'id', '客户ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '客户ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', '3', 'customer_name', '客户名称', 'varchar(128)', 'String', 'customerName', 0, 0, 1, 1, 2, 1, 1, 1, 1, NULL, NULL, 1, '客户名称', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', '3', 'customer_code', '客户编码', 'varchar(64)', 'String', 'customerCode', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '客户编码', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', '3', 'customer_level', '客户级别', 'varchar(20)', 'String', 'customerLevel', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'customer_level', NULL, 3, '客户级别ABC', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', '3', 'phone', '联系电话', 'varchar(20)', 'String', 'phone', 0, 0, 0, 1, 1, 1, 1, 1, 1, NULL, NULL, 5, '联系电话', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', '3', 'status', '状态', 'tinyint', 'Integer', 'status', 0, 0, 0, 1, 1, 1, 1, 1, 4, 'yes_no', '1', 10, '客户状态', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 产品信息表字段
('20', '4', 'id', '产品ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '产品ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('21', '4', 'product_name', '产品名称', 'varchar(128)', 'String', 'productName', 0, 0, 1, 1, 2, 1, 1, 1, 1, NULL, NULL, 1, '产品名称', '1', NOW(), '1', NULL, NULL, 0, 0),
('22', '4', 'product_code', '产品编码', 'varchar(64)', 'String', 'productCode', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '产品编码', '1', NOW(), '1', NULL, NULL, 0, 0),
('23', '4', 'price', '价格', 'decimal(10,2)', 'BigDecimal', 'price', 0, 0, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 4, '产品单价', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 订单信息表字段
('30', '5', 'id', '订单ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '订单ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('31', '5', 'order_no', '订单编号', 'varchar(64)', 'String', 'orderNo', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 1, '订单编号', '1', NOW(), '1', NULL, NULL, 0, 0),
('32', '5', 'customer_id', '客户ID', 'bigint', 'Long', 'customerId', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '关联客户', '1', NOW(), '1', NULL, NULL, 0, 0),
('33', '5', 'order_status', '订单状态', 'varchar(20)', 'String', 'orderStatus', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'order_status', NULL, 3, '订单状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('34', '5', 'total_amount', '订单金额', 'decimal(12,2)', 'BigDecimal', 'totalAmount', 0, 0, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 5, '订单总金额', '1', NOW(), '1', NULL, NULL, 0, 0);

-- 元数据表单配置
INSERT INTO `meta_form` (`form_id`, `form_code`, `form_name`, `table_id`, `form_type`, `form_layout`, `form_config`, `form_status`, `form_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'customer_form', '客户信息表单', '3', 'NORMAL', 'single', '{"columns":1,"labelWidth":100}', 1, '客户信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'product_form', '产品信息表单', '4', 'NORMAL', 'double', '{"columns":2,"labelWidth":80}', 1, '产品信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'order_form', '订单信息表单', '5', 'NORMAL', 'single', '{"columns":1,"labelWidth":100}', 1, '订单信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0);

-- 元数据列表配置
INSERT INTO `meta_list` (`list_id`, `list_code`, `list_name`, `table_id`, `list_type`, `list_config`, `list_status`, `list_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'customer_list', '客户列表', '3', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '客户列表配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'product_list', '产品列表', '4', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '产品列表配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'order_list', '订单列表', '5', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '订单列表配置', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 执行完成
-- =============================================
SET FOREIGN_KEY_CHECKS = 1;

-- 默认管理员账户: admin / 123456
