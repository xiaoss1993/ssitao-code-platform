-- =============================================
-- SSITAO 低代码平台数据库表结构定义
-- 数据库: MySQL 8.0
-- 作者: ssitao-code
-- 日期: 2026-03-07
-- 说明: 包含完整的表结构定义，执行顺序第一位
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 数据清理（为支持重复执行）
-- =============================================
-- 删除所有相关表数据（按外键依赖顺序）
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

DELETE FROM core_dictionary_item WHERE 1=1;
DELETE FROM core_dictionary WHERE 1=1;
DELETE FROM core_config WHERE 1=1;

DELETE FROM meta_column WHERE 1=1;
DELETE FROM meta_form WHERE 1=1;
DELETE FROM meta_list WHERE 1=1;
DELETE FROM meta_table WHERE 1=1;

-- 删除所有表（为支持重新创建）
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

DROP TABLE IF EXISTS `core_dictionary_item`;
DROP TABLE IF EXISTS `core_dictionary`;
DROP TABLE IF EXISTS `core_config`;

DROP TABLE IF EXISTS `meta_column`;
DROP TABLE IF EXISTS `meta_form`;
DROP TABLE IF EXISTS `meta_list`;
DROP TABLE IF EXISTS `meta_table`;

-- =============================================
-- 第一部分: IAM 身份与访问管理模块
-- =============================================

-- ----------------------------
-- 1. 公司表 (iam_company)
-- ----------------------------
DROP TABLE IF EXISTS `iam_company`;
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
DROP TABLE IF EXISTS `iam_department`;
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
DROP TABLE IF EXISTS `iam_post`;
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
DROP TABLE IF EXISTS `iam_user`;
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
DROP TABLE IF EXISTS `iam_account`;
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
DROP TABLE IF EXISTS `iam_role`;
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
DROP TABLE IF EXISTS `iam_permission`;
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
DROP TABLE IF EXISTS `iam_menu`;
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
DROP TABLE IF EXISTS `iam_account_role`;
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
DROP TABLE IF EXISTS `iam_role_permission`;
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
DROP TABLE IF EXISTS `iam_account_dept`;
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
DROP TABLE IF EXISTS `iam_login_log`;
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
DROP TABLE IF EXISTS `iam_operate_log`;
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
-- 第二部分: Core 核心模块
-- =============================================

-- ----------------------------
-- 14. 数据字典表 (core_dictionary)
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary`;
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
DROP TABLE IF EXISTS `core_dictionary_item`;
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
DROP TABLE IF EXISTS `core_config`;
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
-- 第三部分: Meta 元数据模块 (低代码平台)
-- =============================================

-- ----------------------------
-- 17. 元数据表配置 (meta_table)
-- ----------------------------
DROP TABLE IF EXISTS `meta_table`;
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
DROP TABLE IF EXISTS `meta_column`;
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
DROP TABLE IF EXISTS `meta_form`;
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
DROP TABLE IF EXISTS `meta_list`;
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

SET FOREIGN_KEY_CHECKS = 1;
