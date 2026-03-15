-- =============================================
-- ssitao-code-platform 数据库初始化脚本
-- 包含表结构和模拟测试数据
-- 生成时间: 2026-03-15
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- IAM 模块 - 表结构
-- =============================================

-- ----------------------------
-- 1. 公司表
-- ----------------------------
DROP TABLE IF EXISTS `iam_company`;
CREATE TABLE `iam_company` (
  `company_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司ID',
  `company_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司编码',
  `company_name` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `company_short_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简称',
  `company_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ENTERPRISE' COMMENT '公司类型',
  `company_level` int DEFAULT '1' COMMENT '公司级别',
  `company_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父公司ID',
  `company_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司地址',
  `company_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司电话',
  `company_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司邮箱',
  `company_website` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司网站',
  `company_logo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司LOGO',
  `company_legal_rep` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '法人代表',
  `company_registration_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工商注册号',
  `company_tax_no` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '税号',
  `company_status` tinyint(1) DEFAULT '1' COMMENT '公司状态: 0-停用, 1-启用',
  `company_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司描述',
  `company_sort` int DEFAULT '0' COMMENT '排序号',
  `company_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树形路径',
  `company_tree_level` int DEFAULT '1' COMMENT '树形层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`company_id`),
  UNIQUE KEY `uk_company_code` (`company_code`,`tenant_id`,`is_deleted`),
  KEY `idx_company_name` (`company_name`),
  KEY `idx_parent_id` (`company_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='公司表';

-- ----------------------------
-- 2. 部门表
-- ----------------------------
DROP TABLE IF EXISTS `iam_department`;
CREATE TABLE `iam_department` (
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `dept_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编码',
  `dept_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `dept_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'DEPARTMENT' COMMENT '部门类型',
  `dept_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父部门ID',
  `dept_company_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司ID',
  `dept_leader_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人ID',
  `dept_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `dept_address` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门地址',
  `dept_status` tinyint(1) DEFAULT '1' COMMENT '部门状态: 0-停用, 1-启用',
  `dept_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '部门描述',
  `dept_sort` int DEFAULT '0' COMMENT '排序号',
  `dept_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树形路径',
  `dept_tree_level` int DEFAULT '1' COMMENT '树形层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
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
-- 3. 岗位表
-- ----------------------------
DROP TABLE IF EXISTS `iam_post`;
CREATE TABLE `iam_post` (
  `post_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_level` int DEFAULT '0' COMMENT '岗位级别',
  `post_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '岗位类型',
  `post_status` tinyint(1) DEFAULT '1' COMMENT '岗位状态: 0-停用, 1-启用',
  `post_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位描述',
  `post_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `uk_post_code` (`post_code`,`tenant_id`,`is_deleted`),
  KEY `idx_post_name` (`post_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='岗位表';

-- ----------------------------
-- 4. 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `iam_user`;
CREATE TABLE `iam_user` (
  `user_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `user_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户编码',
  `user_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `user_sex` varchar(10) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '性别',
  `user_birthday` date DEFAULT NULL COMMENT '生日',
  `user_id_card` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '身份证号',
  `user_phone` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `user_mail` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `user_photo` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '照片URL',
  `user_address` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '居住地址',
  `user_native_place` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '籍贯',
  `user_nation` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'HAN' COMMENT '民族',
  `user_marital_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '婚姻状况',
  `user_political_status` varchar(32) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '政治面貌',
  `user_work_number` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工号',
  `user_entry_date` date DEFAULT NULL COMMENT '入职日期',
  `user_probation_end_date` date DEFAULT NULL COMMENT '试用期结束日期',
  `user_employment_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'FULL_TIME' COMMENT '用工性质',
  `user_education` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'UNKNOWN' COMMENT '学历',
  `user_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'ON_JOB' COMMENT '员工状态',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_org_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建组织ID',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_user_code` (`user_code`,`tenant_id`,`is_deleted`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_phone` (`user_phone`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

-- ----------------------------
-- 5. 账户表
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
  `account_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SYSTEM' COMMENT '账户类型',
  `account_source` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'LOCAL' COMMENT '账户来源',
  `account_status` tinyint(1) DEFAULT '1' COMMENT '账户状态: 0-禁用, 1-启用',
  `account_is_admin` tinyint(1) DEFAULT '0' COMMENT '是否管理员: 0-否, 1-是',
  `account_last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `account_last_login_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '最后登录IP',
  `account_init_password` tinyint(1) DEFAULT '0' COMMENT '是否初始密码: 0-否, 1-是',
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
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号(乐观锁)',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `uk_account_code` (`account_code`,`tenant_id`,`is_deleted`),
  KEY `idx_account_name` (`account_name`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_status` (`account_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户账户表';

-- ----------------------------
-- 6. 角色表
-- ----------------------------
DROP TABLE IF EXISTS `iam_role`;
CREATE TABLE `iam_role` (
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `role_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '角色类型',
  `role_level` int DEFAULT '0' COMMENT '角色级别',
  `role_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `role_status` tinyint(1) DEFAULT '1' COMMENT '角色状态: 0-禁用, 1-启用',
  `role_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `role_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uk_role_code` (`role_code`,`tenant_id`,`is_deleted`),
  KEY `idx_role_name` (`role_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- 7. 权限表
-- ----------------------------
DROP TABLE IF EXISTS `iam_permission`;
CREATE TABLE `iam_permission` (
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `permission_code` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码',
  `permission_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `permission_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUTTON' COMMENT '权限类型',
  `permission_resource` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限资源',
  `permission_action` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限动作',
  `permission_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限描述',
  `permission_status` tinyint(1) DEFAULT '1' COMMENT '权限状态: 0-禁用, 1-启用',
  `permission_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `permission_sort` int DEFAULT '0' COMMENT '排序号',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`,`tenant_id`,`is_deleted`),
  KEY `idx_permission_name` (`permission_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

-- ----------------------------
-- 8. 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `iam_menu`;
CREATE TABLE `iam_menu` (
  `menu_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
  `menu_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码',
  `menu_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'MENU' COMMENT '菜单类型',
  `menu_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父菜单ID',
  `menu_path` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由路径',
  `menu_component` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组件路径',
  `menu_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单图标',
  `menu_sort` int DEFAULT '0' COMMENT '排序号',
  `menu_is_visible` tinyint(1) DEFAULT '1' COMMENT '是否可见: 0-隐藏, 1-显示',
  `menu_is_cached` tinyint(1) DEFAULT '0' COMMENT '是否缓存: 0-否, 1-是',
  `menu_is_affix` tinyint(1) DEFAULT '0' COMMENT '是否固定: 0-否, 1-是',
  `menu_permission` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '权限标识',
  `menu_redirect` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '重定向路径',
  `menu_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `menu_status` tinyint(1) DEFAULT '1' COMMENT '菜单状态: 0-禁用, 1-启用',
  `menu_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `menu_tree_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '树形路径',
  `menu_tree_level` int DEFAULT '1' COMMENT '树形层级',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `uk_menu_code` (`menu_code`,`tenant_id`,`is_deleted`),
  KEY `idx_menu_name` (`menu_name`),
  KEY `idx_parent_id` (`menu_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';

-- ----------------------------
-- 9. 账户角色关联表
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_role`;
CREATE TABLE `iam_account_role` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_role` (`account_id`,`role_id`,`is_deleted`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户角色关联表';

-- ----------------------------
-- 10. 角色权限关联表
-- ----------------------------
DROP TABLE IF EXISTS `iam_role_permission`;
CREATE TABLE `iam_role_permission` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `role_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
  `permission_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `is_valid` tinyint(1) DEFAULT '1' COMMENT '是否有效: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`,`is_deleted`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- 11. 账户部门关联表
-- ----------------------------
DROP TABLE IF EXISTS `iam_account_dept`;
CREATE TABLE `iam_account_dept` (
  `id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户ID',
  `dept_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `is_primary` tinyint(1) DEFAULT '0' COMMENT '是否主部门: 0-否, 1-是',
  `is_leader` tinyint(1) DEFAULT '0' COMMENT '是否负责人: 0-否, 1-是',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account_dept` (`account_id`,`dept_id`,`is_deleted`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_dept_id` (`dept_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='账户部门关联表';

-- ----------------------------
-- 12. 登录日志表
-- ----------------------------
DROP TABLE IF EXISTS `iam_login_log`;
CREATE TABLE `iam_login_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `account_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户ID',
  `account_code` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户编码',
  `account_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '账户名称',
  `login_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'PASSWORD' COMMENT '登录类型',
  `login_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SUCCESS' COMMENT '登录状态',
  `login_fail_reason` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '失败原因',
  `login_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录IP',
  `login_location` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录地点',
  `login_device` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录设备',
  `login_browser` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '浏览器',
  `login_os` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作系统',
  `login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `logout_time` datetime DEFAULT NULL COMMENT '退出时间',
  `online_duration` int DEFAULT NULL COMMENT '在线时长(秒)',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_account_id` (`account_id`),
  KEY `idx_login_time` (`login_time`),
  KEY `idx_status` (`login_status`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='登录日志表';

-- ----------------------------
-- 13. 操作日志表
-- ----------------------------
DROP TABLE IF EXISTS `iam_operate_log`;
CREATE TABLE `iam_operate_log` (
  `log_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志ID',
  `operate_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作类型',
  `module_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '模块名称',
  `business_type` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务类型',
  `business_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '业务ID',
  `method_name` varchar(256) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '方法名称',
  `request_url` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URL',
  `request_method` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求方式',
  `request_params` text COLLATE utf8mb4_general_ci COMMENT '请求参数',
  `response_result` text COLLATE utf8mb4_general_ci COMMENT '返回结果',
  `operate_status` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'SUCCESS' COMMENT '操作状态',
  `error_msg` text COLLATE utf8mb4_general_ci COMMENT '错误信息',
  `execute_duration` int DEFAULT NULL COMMENT '执行时长(毫秒)',
  `operator_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人姓名',
  `operator_dept` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作部门',
  `operate_ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP',
  `operate_location` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作地点',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`log_id`),
  KEY `idx_operator_id` (`operator_id`),
  KEY `idx_operate_time` (`operate_time`),
  KEY `idx_business_id` (`business_id`),
  KEY `idx_module` (`module_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';

-- =============================================
-- Core 模块 - 表结构
-- =============================================

-- ----------------------------
-- 14. 数据字典表
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary`;
CREATE TABLE `core_dictionary` (
  `dict_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `dict_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_type` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'BUSINESS' COMMENT '字典类型',
  `dict_source` varchar(20) COLLATE utf8mb4_general_ci DEFAULT 'CUSTOM' COMMENT '字典来源',
  `dict_sql` text COLLATE utf8mb4_general_ci COMMENT '字典SQL',
  `dict_api` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典API',
  `dict_config` text COLLATE utf8mb4_general_ci COMMENT '字典配置(JSON)',
  `dict_status` tinyint(1) DEFAULT '1' COMMENT '字典状态: 0-停用, 1-启用',
  `dict_is_builtin` tinyint(1) DEFAULT '0' COMMENT '是否内置: 0-否, 1-是',
  `dict_sort` int DEFAULT '0' COMMENT '排序号',
  `dict_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  `version` int DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `uk_dict_code` (`dict_code`,`tenant_id`,`is_deleted`),
  KEY `idx_dict_name` (`dict_name`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典表';

-- ----------------------------
-- 15. 数据字典项表
-- ----------------------------
DROP TABLE IF EXISTS `core_dictionary_item`;
CREATE TABLE `core_dictionary_item` (
  `item_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项ID',
  `dict_id` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典ID',
  `item_code` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项编码',
  `item_name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `item_value` varchar(256) COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项值',
  `item_parent_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父项ID',
  `item_level` int DEFAULT '1' COMMENT '项层级',
  `item_path` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项路径',
  `item_icon` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项图标',
  `item_color` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '项颜色',
  `item_css_class` varchar(128) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'CSS样式',
  `item_status` tinyint(1) DEFAULT '1' COMMENT '项状态: 0-停用, 1-启用',
  `item_is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认: 0-否, 1-是',
  `item_sort` int DEFAULT '0' COMMENT '排序号',
  `item_desc` varchar(512) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '字典项描述',
  `tenant_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人ID',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modify_user_id` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人ID',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否删除: 0-否, 1-是',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `uk_dict_item` (`dict_id`,`item_code`,`is_deleted`),
  KEY `idx_item_code` (`item_code`),
  KEY `idx_item_value` (`item_value`),
  KEY `idx_parent_id` (`item_parent_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典项表';

-- =============================================
-- 模拟测试数据
-- =============================================

-- ----------------------------
-- 一、公司数据
-- ----------------------------
INSERT INTO `iam_company` (`company_id`, `company_code`, `company_name`, `company_short_name`, `company_type`, `company_level`, `company_parent_id`, `company_address`, `company_phone`, `company_mail`, `company_website`, `company_logo`, `company_legal_rep`, `company_registration_no`, `company_tax_no`, `company_status`, `company_desc`, `company_sort`, `company_tree_path`, `company_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'HQ', '思涛科技集团', '思涛科技', 'ENTERPRISE', 1, NULL, '北京市海淀区中关村大街1号', '010-88888888', 'contact@ssitao.com', 'https://www.ssitao.com', NULL, '李思涛', '91110108MA01XXXXX', '91110108MA01XXXXX', 1, '思涛科技集团总部', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'BJ', '北京分公司', '北京分', 'BRANCH', 2, '1', '北京市朝阳区建国路88号', '010-66666666', 'bj@ssitao.com', NULL, NULL, '王北京', NULL, NULL, 1, '北京分公司', 2, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'SH', '上海分公司', '上海分', 'BRANCH', 2, '1', '上海市浦东新区陆家嘴金融中心', '021-99999999', 'sh@ssitao.com', NULL, NULL, '赵上海', NULL, NULL, 1, '上海分公司', 3, '/1/3', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'GZ', '广州分公司', '广州分', 'BRANCH', 2, '1', '广州市天河区珠江新城', '020-88888889', 'gz@ssitao.com', NULL, NULL, '陈广州', NULL, NULL, 1, '广州分公司', 4, '/1/4', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'SZ', '深圳分公司', '深圳分', 'BRANCH', 2, '1', '深圳市南山区科技园', '0755-88888890', 'sz@ssitao.com', NULL, NULL, '周深圳', NULL, NULL, 1, '深圳分公司', 5, '/1/5', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'CD', '成都分公司', '成都分', 'BRANCH', 2, '1', '成都市高新区天府大道', '028-88888891', 'cd@ssitao.com', NULL, NULL, '刘成都', NULL, NULL, 1, '成都分公司', 6, '/1/6', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'WH', '武汉分公司', '武汉分', 'BRANCH', 2, '1', '武汉市东湖新技术开发区', '027-88888892', 'wh@ssitao.com', NULL, NULL, '吴武汉', NULL, NULL, 1, '武汉分公司', 7, '/1/7', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'HZ', '杭州分公司', '杭州分', 'BRANCH', 2, '1', '杭州市滨江区高新园区', '0571-88888893', 'hz@ssitao.com', NULL, NULL, '郑杭州', NULL, NULL, 1, '杭州分公司', 8, '/1/8', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'NJ', '南京分公司', '南京分', 'BRANCH', 2, '1', '南京市建邺区河西新城', '025-88888894', 'nj@ssitao.com', NULL, NULL, '王南京', NULL, NULL, 1, '南京分公司', 9, '/1/9', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'XA', '西安分公司', '西安分', 'BRANCH', 2, '1', '西安市高新区锦业路', '029-88888895', 'xa@ssitao.com', NULL, NULL, '孙西安', NULL, NULL, 1, '西安分公司', 10, '/1/10', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 二、部门数据
-- ----------------------------
INSERT INTO `iam_department` (`dept_id`, `dept_code`, `dept_name`, `dept_type`, `dept_parent_id`, `dept_company_id`, `dept_leader_id`, `dept_phone`, `dept_address`, `dept_status`, `dept_desc`, `dept_sort`, `dept_tree_path`, `dept_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'ROOT', '集团总部', 'HEADQUARTERS', NULL, '1', '1', '010-88888881', '总部大楼1层', 1, '集团总部部门', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'TECH', '技术研发中心', 'DEPARTMENT', '1', '1', '2', '010-88888882', '总部大楼5层', 1, '负责公司技术研发', 1, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEV', '软件开发部', 'DEPARTMENT', '2', '1', '3', '010-88888883', '总部大楼5层A区', 1, '负责软件开发', 1, '/1/2/3', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'QA', '质量保障部', 'DEPARTMENT', '2', '1', '4', '010-88888884', '总部大楼5层B区', 1, '负责质量保障', 2, '/1/2/4', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'OPS', '运维部', 'DEPARTMENT', '2', '1', '5', '010-88888885', '总部大楼地下一层', 1, '负责系统运维', 3, '/1/2/5', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'PRODUCT', '产品中心', 'DEPARTMENT', '1', '1', '6', '010-88888886', '总部大楼4层', 1, '负责产品设计', 2, '/1/6', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'SALES', '销售中心', 'DEPARTMENT', '1', '1', '7', '010-88888887', '总部大楼3层', 1, '负责市场销售', 3, '/1/7', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'HR', '人力资源部', 'DEPARTMENT', '1', '1', '8', '010-88888888', '总部大楼2层', 1, '负责人力资源', 4, '/1/8', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'FIN', '财务部', 'DEPARTMENT', '1', '1', '9', '010-88888889', '总部大楼2层', 1, '负责财务管理', 5, '/1/9', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 北京分公司部门
('10', 'BJ_TECH', '北京技术部', 'DEPARTMENT', '2', '2', NULL, '010-66666601', '北京分公司3层', 1, '北京技术研发', 1, '/1/2/10', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'BJ_SALES', '北京销售部', 'DEPARTMENT', '7', '2', NULL, '010-66666602', '北京分公司2层', 1, '北京销售', 1, '/1/7/11', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'BJ_HR', '北京人事部', 'DEPARTMENT', '8', '2', NULL, '010-66666603', '北京分公司4层', 1, '北京人事', 1, '/1/8/12', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 上海分公司部门
('13', 'SH_TECH', '上海技术部', 'DEPARTMENT', '2', '3', NULL, '021-99999901', '上海分公司3层', 1, '上海技术研发', 1, '/1/2/13', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'SH_SALES', '上海销售部', 'DEPARTMENT', '7', '3', NULL, '021-99999902', '上海分公司2层', 1, '上海销售', 1, '/1/7/14', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('15', 'SH_OPS', '上海运维部', 'DEPARTMENT', '5', '3', NULL, '021-99999903', '上海分公司地下1层', 1, '上海运维', 1, '/1/5/15', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 广州分公司部门
('16', 'GZ_TECH', '广州技术部', 'DEPARTMENT', '2', '4', NULL, '020-88888801', '广州分公司3层', 1, '广州技术研发', 1, '/1/2/16', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('17', 'GZ_SALES', '广州销售部', 'DEPARTMENT', '7', '4', NULL, '020-88888802', '广州分公司2层', 1, '广州销售', 1, '/1/7/17', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('18', 'GZ_PRODUCT', '广州产品部', 'DEPARTMENT', '6', '4', NULL, '020-88888803', '广州分公司4层', 1, '广州产品设计', 1, '/1/6/18', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 深圳分公司部门
('19', 'SZ_TECH', '深圳技术部', 'DEPARTMENT', '2', '5', NULL, '0755-88888801', '深圳分公司3层', 1, '深圳技术研发', 1, '/1/2/19', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('20', 'SZ_SALES', '深圳销售部', 'DEPARTMENT', '7', '5', NULL, '0755-88888802', '深圳分公司2层', 1, '深圳销售', 1, '/1/7/20', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'SZ_OPS', '深圳运维部', 'DEPARTMENT', '5', '5', NULL, '0755-88888803', '深圳分公司地下1层', 1, '深圳运维', 1, '/1/5/21', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 成都分公司部门
('22', 'CD_TECH', '成都技术部', 'DEPARTMENT', '2', '6', NULL, '028-88888801', '成都分公司3层', 1, '成都技术研发', 1, '/1/2/22', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'CD_SALES', '成都销售部', 'DEPARTMENT', '7', '6', NULL, '028-88888802', '成都分公司2层', 1, '成都销售', 1, '/1/7/23', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'CD_PRODUCT', '成都产品部', 'DEPARTMENT', '6', '6', NULL, '028-88888803', '成都分公司4层', 1, '成都产品设计', 1, '/1/6/24', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 武汉分公司部门
('25', 'WH_TECH', '武汉技术部', 'DEPARTMENT', '2', '7', NULL, '027-88888801', '武汉分公司3层', 1, '武汉技术研发', 1, '/1/2/25', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('26', 'WH_SALES', '武汉销售部', 'DEPARTMENT', '7', '7', NULL, '027-88888802', '武汉分公司2层', 1, '武汉销售', 1, '/1/7/26', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('27', 'WH_OPS', '武汉运维部', 'DEPARTMENT', '5', '7', NULL, '027-88888803', '武汉分公司地下1层', 1, '武汉运维', 1, '/1/5/27', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 杭州分公司部门
('28', 'HZ_TECH', '杭州技术部', 'DEPARTMENT', '2', '8', NULL, '0571-88888801', '杭州分公司3层', 1, '杭州技术研发', 1, '/1/2/28', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('29', 'HZ_SALES', '杭州销售部', 'DEPARTMENT', '7', '8', NULL, '0571-88888802', '杭州分公司2层', 1, '杭州销售', 1, '/1/7/29', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('30', 'HZ_PRODUCT', '杭州产品部', 'DEPARTMENT', '6', '8', NULL, '0571-88888803', '杭州分公司4层', 1, '杭州产品设计', 1, '/1/6/30', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 南京分公司部门
('31', 'NJ_TECH', '南京技术部', 'DEPARTMENT', '2', '9', NULL, '025-88888801', '南京分公司3层', 1, '南京技术研发', 1, '/1/2/31', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('32', 'NJ_SALES', '南京销售部', 'DEPARTMENT', '7', '9', NULL, '025-88888802', '南京分公司2层', 1, '南京销售', 1, '/1/7/32', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('33', 'NJ_OPS', '南京运维部', 'DEPARTMENT', '5', '9', NULL, '025-88888803', '南京分公司地下1层', 1, '南京运维', 1, '/1/5/33', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 西安分公司部门
('34', 'XA_TECH', '西安技术部', 'DEPARTMENT', '2', '10', NULL, '029-88888801', '西安分公司3层', 1, '西安技术研发', 1, '/1/2/34', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('35', 'XA_SALES', '西安销售部', 'DEPARTMENT', '7', '10', NULL, '029-88888802', '西安分公司2层', 1, '西安销售', 1, '/1/7/35', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('36', 'XA_PRODUCT', '西安产品部', 'DEPARTMENT', '6', '10', NULL, '029-88888803', '西安分公司4层', 1, '西安产品设计', 1, '/1/6/36', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 额外扩展部门（每个技术部门添加子部门）
('37', 'DEV_JAVA', 'Java开发组', 'DEPARTMENT', '3', '1', NULL, '010-88888890', '总部大楼5层C区', 1, 'Java开发', 1, '/1/2/3/37', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('38', 'DEV_PYTHON', 'Python开发组', 'DEPARTMENT', '3', '1', NULL, '010-88888891', '总部大楼5层D区', 1, 'Python开发', 1, '/1/2/3/38', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('39', 'DEV_VUE', '前端开发组', 'DEPARTMENT', '3', '1', NULL, '010-88888892', '总部大楼5层E区', 1, '前端开发', 1, '/1/2/3/39', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('40', 'QA_FUNC', '功能测试组', 'DEPARTMENT', '4', '1', NULL, '010-88888893', '总部大楼5层F区', 1, '功能测试', 1, '/1/2/4/40', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('41', 'QA_AUTO', '自动化测试组', 'DEPARTMENT', '4', '1', NULL, '010-88888894', '总部大楼5层G区', 1, '自动化测试', 1, '/1/2/4/41', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('42', 'OPS_DBA', '数据库运维组', 'DEPARTMENT', '5', '1', NULL, '010-88888895', '总部大楼地下二层', 1, '数据库运维', 1, '/1/2/5/42', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('43', 'OPS_NET', '网络运维组', 'DEPARTMENT', '5', '1', NULL, '010-88888896', '总部大楼地下三层', 1, '网络运维', 1, '/1/2/5/43', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('44', 'PRODUCT_UX', '产品设计组', 'DEPARTMENT', '6', '1', NULL, '010-88888897', '总部大楼4层A区', 1, '产品设计', 1, '/1/6/44', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('45', 'PRODUCT_BI', '产品BI组', 'DEPARTMENT', '6', '1', NULL, '010-88888898', '总部大楼4层B区', 1, '产品BI分析', 1, '/1/6/45', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('46', 'SALES_B2B', 'B2B销售组', 'DEPARTMENT', '7', '1', NULL, '010-88888899', '总部大楼3层A区', 1, 'B2B销售', 1, '/1/7/46', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('47', 'SALES_B2C', 'B2C销售组', 'DEPARTMENT', '7', '1', NULL, '010-88888900', '总部大楼3层B区', 1, 'B2C销售', 1, '/1/7/47', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('48', 'HR_RECRUIT', '招聘组', 'DEPARTMENT', '8', '1', NULL, '010-88888901', '总部大楼2层A区', 1, '人员招聘', 1, '/1/8/48', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('49', 'HR_TRAIN', '培训组', 'DEPARTMENT', '8', '1', NULL, '010-88888902', '总部大楼2层B区', 1, '员工培训', 1, '/1/8/49', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('50', 'FIN_ACCOUNT', '会计组', 'DEPARTMENT', '9', '1', NULL, '010-88888903', '总部大楼2层C区', 1, '会计核算', 1, '/1/9/50', 4, '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 三、岗位数据
-- ----------------------------
INSERT INTO `iam_post` (`post_id`, `post_code`, `post_name`, `post_level`, `post_type`, `post_status`, `post_desc`, `post_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'CEO', '首席执行官', 1, 'MANAGEMENT', 1, '公司最高管理者', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'CTO', '首席技术官', 2, 'MANAGEMENT', 1, '技术负责人', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'PM', '项目经理', 3, 'MANAGEMENT', 1, '项目管理', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'TL', '技术经理', 4, 'MANAGEMENT', 1, '技术管理', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'SE', '高级工程师', 5, 'TECHNICAL', 1, '高级技术岗位', 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'DEV', '开发工程师', 6, 'TECHNICAL', 1, '开发岗位', 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'QA', '测试工程师', 6, 'TECHNICAL', 1, '测试岗位', 7, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'OPS', '运维工程师', 6, 'TECHNICAL', 1, '运维岗位', 8, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'SALES', '销售经理', 4, 'BUSINESS', 1, '销售岗位', 9, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'HR', '人事专员', 6, 'FUNCTIONAL', 1, '人事岗位', 10, '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 四、用户数据
-- ----------------------------
INSERT INTO `iam_user` (`user_id`, `user_code`, `user_name`, `user_sex`, `user_birthday`, `user_id_card`, `user_phone`, `user_mail`, `user_photo`, `user_address`, `user_native_place`, `user_nation`, `user_marital_status`, `user_political_status`, `user_work_number`, `user_entry_date`, `user_probation_end_date`, `user_employment_type`, `user_education`, `user_status`, `tenant_id`, `create_org_id`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'ADMIN', '系统管理员', 'MALE', '1990-01-01', '110101199001010001', '13800138001', 'admin@ssitao.com', NULL, '北京市海淀区', '北京', 'HAN', 'MARRIED', 'MEMBER', 'EMP001', '2020-01-01', '2020-04-01', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('2', 'ZHANGSAN', '张三', 'MALE', '1992-05-15', '110101199205150011', '13800138002', 'zhangsan@ssitao.com', NULL, '北京市朝阳区', '山东', 'HAN', 'SINGLE', 'MEMBER', 'EMP002', '2021-03-15', '2021-06-15', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('3', 'LISI', '李四', 'MALE', '1993-08-20', '110101199308200021', '13800138003', 'lisi@ssitao.com', NULL, '北京市海淀区', '河北', 'HAN', 'SINGLE', 'MEMBER', 'EMP003', '2021-06-01', '2021-09-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('4', 'WANGWU', '王五', 'FEMALE', '1994-03-10', '110101199403100031', '13800138004', 'wangwu@ssitao.com', NULL, '北京市西城区', '北京', 'HAN', 'SINGLE', 'MEMBER', 'EMP004', '2022-01-10', '2022-04-10', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('5', 'ZHAOLIU', '赵六', 'MALE', '1995-12-25', '110101199512250041', '13800138005', 'zhaoliu@ssitao.com', NULL, '北京市东城区', '河南', 'HAN', 'SINGLE', 'MEMBER', 'EMP005', '2022-07-01', '2022-10-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0);

-- ----------------------------
-- 五、账户数据（密码均为 123456，使用BCrypt加密）
-- ----------------------------
INSERT INTO `iam_account` (`account_id`, `account_code`, `account_name`, `account_password`, `account_phone`, `account_mail`, `account_avatar`, `account_type`, `account_source`, `account_status`, `account_is_admin`, `account_last_login_time`, `account_last_login_ip`, `account_init_password`, `account_init_password_reset_time`, `tenant_id`, `create_org_id`, `create_org_name`, `create_user_id`, `create_user_name`, `create_time`, `modify_org_id`, `modify_org_name`, `modify_user_id`, `modify_user_name`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'admin', '系统管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138001', 'admin@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 1, NOW(), '127.0.0.1', 0, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('2', 'zhangsan', '张三', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138002', 'zhangsan@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('3', 'lisi', '李四', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138003', 'lisi@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('4', 'wangwu', '王五', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138004', 'wangwu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('5', 'zhaoliu', '赵六', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138005', 'zhaoliu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0);

-- ----------------------------
-- 六、角色数据
-- ----------------------------
INSERT INTO `iam_role` (`role_id`, `role_code`, `role_name`, `role_type`, `role_level`, `role_desc`, `role_status`, `role_is_builtin`, `role_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'SUPER_ADMIN', '超级管理员', 'SYSTEM', 1, '系统超级管理员，拥有所有权限', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'ADMIN', '系统管理员', 'SYSTEM', 2, '系统管理员，拥有大部分权限', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEPT_ADMIN', '部门管理员', 'BUSINESS', 3, '部门管理员，管理本部门', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'DEVELOPER', '开发人员', 'BUSINESS', 4, '开发人员角色', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'TESTER', '测试人员', 'BUSINESS', 4, '测试人员角色', 1, 0, 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'OPERATOR', '运维人员', 'BUSINESS', 4, '运维人员角色', 1, 0, 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'USER', '普通用户', 'BUSINESS', 5, '普通用户角色', 1, 1, 7, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'PRODUCT_MANAGER', '产品经理', 'BUSINESS', 4, '产品经理角色', 1, 0, 8, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'UI_DESIGNER', 'UI设计师', 'BUSINESS', 5, 'UI设计师角色', 1, 0, 9, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'DATA_ANALYST', '数据分析师', 'BUSINESS', 5, '数据分析师角色', 1, 0, 10, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'DBA', '数据库管理员', 'BUSINESS', 4, '数据库管理员角色', 1, 0, 11, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'SECURITY', '安全工程师', 'BUSINESS', 4, '安全工程师角色', 1, 0, 12, '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'DEVOPS', 'DevOps工程师', 'BUSINESS', 4, 'DevOps工程师角色', 1, 0, 13, '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'ARCHITECT', '架构师', 'BUSINESS', 3, '系统架构师角色', 1, 0, 14, '1', NOW(), '1', NULL, NULL, 0, 0),
('15', 'PMO', '项目经理办公室', 'BUSINESS', 3, 'PMO角色', 1, 0, 15, '1', NOW(), '1', NULL, NULL, 0, 0),
('16', 'AUDITOR', '审计员', 'BUSINESS', 4, '审计员角色', 1, 0, 16, '1', NOW(), '1', NULL, NULL, 0, 0),
('17', 'GUEST', '访客', 'BUSINESS', 6, '访客角色，只有查看权限', 1, 0, 17, '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 七、权限数据
-- ----------------------------
INSERT INTO `iam_permission` (`permission_id`, `permission_code`, `permission_name`, `permission_type`, `permission_resource`, `permission_action`, `permission_desc`, `permission_status`, `permission_is_builtin`, `permission_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`) VALUES
-- 系统管理权限
('1', 'system:manage', '系统管理', 'MENU', '/system', 'VIEW', '系统管理模块', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('2', 'system:user:view', '用户查看', 'BUTTON', '/system/user', 'VIEW', '查看用户信息', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('3', 'system:user:add', '用户新增', 'BUTTON', '/system/user', 'ADD', '新增用户', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('4', 'system:user:edit', '用户编辑', 'BUTTON', '/system/user', 'EDIT', '编辑用户', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
('5', 'system:user:delete', '用户删除', 'BUTTON', '/system/user', 'DELETE', '删除用户', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
('6', 'system:role:view', '角色查看', 'BUTTON', '/system/role', 'VIEW', '查看角色', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
('7', 'system:role:add', '角色新增', 'BUTTON', '/system/role', 'ADD', '新增角色', 1, 1, 6, '1', NOW(), '1', NULL, NULL, 0),
('8', 'system:role:edit', '角色编辑', 'BUTTON', '/system/role', 'EDIT', '编辑角色', 1, 1, 7, '1', NOW(), '1', NULL, NULL, 0),
('9', 'system:role:delete', '角色删除', 'BUTTON', '/system/role', 'DELETE', '删除角色', 1, 1, 8, '1', NOW(), '1', NULL, NULL, 0),
-- 组织管理权限
('10', 'org:manage', '组织管理', 'MENU', '/org', 'VIEW', '组织管理模块', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('11', 'org:company:view', '公司查看', 'BUTTON', '/org/company', 'VIEW', '查看公司', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('12', 'org:company:add', '公司新增', 'BUTTON', '/org/company', 'ADD', '新增公司', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('13', 'org:dept:view', '部门查看', 'BUTTON', '/org/dept', 'VIEW', '查看部门', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
('14', 'org:dept:add', '部门新增', 'BUTTON', '/org/dept', 'ADD', '新增部门', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 菜单管理权限
('15', 'menu:manage', '菜单管理', 'MENU', '/menu', 'VIEW', '菜单管理模块', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
('16', 'menu:view', '菜单查看', 'BUTTON', '/menu', 'VIEW', '查看菜单', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('17', 'menu:add', '菜单新增', 'BUTTON', '/menu', 'ADD', '新增菜单', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
-- 字典管理权限
('20', 'dict:manage', '字典管理', 'MENU', '/dict', 'VIEW', '字典管理模块', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
('21', 'dict:view', '字典查看', 'BUTTON', '/dict', 'VIEW', '查看字典', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
-- 日志管理权限
('30', 'log:manage', '日志管理', 'MENU', '/log', 'VIEW', '日志管理模块', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
('31', 'log:login:view', '登录日志查看', 'BUTTON', '/log/login', 'VIEW', '查看登录日志', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('32', 'log:operate:view', '操作日志查看', 'BUTTON', '/log/operate', 'VIEW', '查看操作日志', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
-- 组织管理扩展权限
('33', 'org:company:edit', '公司编辑', 'BUTTON', '/org/company', 'EDIT', '编辑公司', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('34', 'org:company:delete', '公司删除', 'BUTTON', '/org/company', 'DELETE', '删除公司', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
('35', 'org:dept:edit', '部门编辑', 'BUTTON', '/org/dept', 'EDIT', '编辑部门', 1, 0, 5, '1', NOW(), '1', NULL, NULL, 0),
('36', 'org:dept:delete', '部门删除', 'BUTTON', '/org/dept', 'DELETE', '删除部门', 1, 0, 6, '1', NOW(), '1', NULL, NULL, 0),
('37', 'org:post:view', '岗位查看', 'BUTTON', '/org/post', 'VIEW', '查看岗位', 1, 0, 7, '1', NOW(), '1', NULL, NULL, 0),
('38', 'org:post:add', '岗位新增', 'BUTTON', '/org/post', 'ADD', '新增岗位', 1, 0, 8, '1', NOW(), '1', NULL, NULL, 0),
('39', 'org:post:edit', '岗位编辑', 'BUTTON', '/org/post', 'EDIT', '编辑岗位', 1, 0, 9, '1', NOW(), '1', NULL, NULL, 0),
('40', 'org:post:delete', '岗位删除', 'BUTTON', '/org/post', 'DELETE', '删除岗位', 1, 0, 10, '1', NOW(), '1', NULL, NULL, 0),
-- 菜单管理扩展权限
('41', 'menu:edit', '菜单编辑', 'BUTTON', '/menu', 'EDIT', '编辑菜单', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('42', 'menu:delete', '菜单删除', 'BUTTON', '/menu', 'DELETE', '删除菜单', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 字典管理扩展权限
('43', 'dict:add', '字典新增', 'BUTTON', '/dict', 'ADD', '新增字典', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('44', 'dict:edit', '字典编辑', 'BUTTON', '/dict', 'EDIT', '编辑字典', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('45', 'dict:delete', '字典删除', 'BUTTON', '/dict', 'DELETE', '删除字典', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 监控管理权限
('50', 'monitor:manage', '系统监控', 'MENU', '/monitor', 'VIEW', '系统监控模块', 1, 1, 6, '1', NOW(), '1', NULL, NULL, 0),
('51', 'monitor:server', '服务器监控', 'BUTTON', '/monitor/server', 'VIEW', '查看服务器状态', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('52', 'monitor:redis', 'Redis监控', 'BUTTON', '/monitor/redis', 'VIEW', '查看Redis状态', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('53', 'monitor:rabbitmq', 'RabbitMQ监控', 'BUTTON', '/monitor/rabbitmq', 'VIEW', '查看消息队列', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('54', 'monitor:sql', 'SQL监控', 'BUTTON', '/monitor/sql', 'VIEW', '查看SQL执行情况', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 消息中心权限
('60', 'message:manage', '消息管理', 'MENU', '/message', 'VIEW', '消息管理模块', 1, 0, 7, '1', NOW(), '1', NULL, NULL, 0),
('61', 'message:view', '消息查看', 'BUTTON', '/message', 'VIEW', '查看消息', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('62', 'message:add', '消息发送', 'BUTTON', '/message', 'ADD', '发送消息', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('63', 'message:delete', '消息删除', 'BUTTON', '/message', 'DELETE', '删除消息', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
-- 文件管理权限
('70', 'file:manage', '文件管理', 'MENU', '/file', 'VIEW', '文件管理模块', 1, 0, 8, '1', NOW(), '1', NULL, NULL, 0),
('71', 'file:view', '文件查看', 'BUTTON', '/file', 'VIEW', '查看文件', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('72', 'file:upload', '文件上传', 'BUTTON', '/file', 'UPLOAD', '上传文件', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('73', 'file:download', '文件下载', 'BUTTON', '/file', 'DOWNLOAD', '下载文件', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('74', 'file:delete', '文件删除', 'BUTTON', '/file', 'DELETE', '删除文件', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 通知公告权限
('80', 'notice:manage', '通知公告', 'MENU', '/notice', 'VIEW', '通知公告模块', 1, 0, 9, '1', NOW(), '1', NULL, NULL, 0),
('81', 'notice:view', '通知查看', 'BUTTON', '/notice', 'VIEW', '查看通知', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('82', 'notice:add', '通知新增', 'BUTTON', '/notice', 'ADD', '发布通知', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('83', 'notice:edit', '通知编辑', 'BUTTON', '/notice', 'EDIT', '编辑通知', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('84', 'notice:delete', '通知删除', 'BUTTON', '/notice', 'DELETE', '删除通知', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 工作流权限
('90', 'workflow:manage', '工作流管理', 'MENU', '/workflow', 'VIEW', '工作流管理模块', 1, 0, 10, '1', NOW(), '1', NULL, NULL, 0),
('91', 'workflow:view', '工作流查看', 'BUTTON', '/workflow', 'VIEW', '查看工作流', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('92', 'workflow:add', '工作流新增', 'BUTTON', '/workflow', 'ADD', '新增工作流', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('93', 'workflow:edit', '工作流编辑', 'BUTTON', '/workflow', 'EDIT', '编辑工作流', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0),
('94', 'workflow:delete', '工作流删除', 'BUTTON', '/workflow', 'DELETE', '删除工作流', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0),
-- 系统配置权限
('100', 'config:manage', '系统配置', 'MENU', '/config', 'VIEW', '系统配置模块', 1, 0, 11, '1', NOW(), '1', NULL, NULL, 0),
('101', 'config:view', '配置查看', 'BUTTON', '/config', 'VIEW', '查看系统配置', 1, 0, 1, '1', NOW(), '1', NULL, NULL, 0),
('102', 'config:edit', '配置编辑', 'BUTTON', '/config', 'EDIT', '编辑系统配置', 1, 0, 2, '1', NOW(), '1', NULL, NULL, 0),
('103', 'config:delete', '配置删除', 'BUTTON', '/config', 'DELETE', '删除系统配置', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0);

-- ----------------------------
-- 八、菜单数据
-- ----------------------------
INSERT INTO `iam_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_type`, `menu_parent_id`, `menu_path`, `menu_component`, `menu_icon`, `menu_sort`, `menu_is_visible`, `menu_is_cached`, `menu_is_affix`, `menu_permission`, `menu_redirect`, `menu_desc`, `menu_status`, `menu_is_builtin`, `menu_tree_path`, `menu_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 一级菜单
('1', 'dashboard', '工作台', 'DIRECTORY', NULL, '/dashboard', NULL, 'DashboardOutlined', 1, 1, 0, 0, NULL, '/dashboard/analysis', '工作台', 1, 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'system', '系统管理', 'DIRECTORY', NULL, '/system', NULL, 'SettingOutlined', 2, 1, 0, 0, NULL, '/system/user', '系统管理', 1, 1, '/2', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'org', '组织管理', 'DIRECTORY', NULL, '/org', NULL, 'ApartmentOutlined', 3, 1, 0, 0, NULL, '/org/company', '组织管理', 1, 1, '/3', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'monitor', '系统监控', 'DIRECTORY', NULL, '/monitor', NULL, 'MonitorOutlined', 4, 1, 0, 0, NULL, '/monitor/log', '系统监控', 1, 1, '/4', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 工作台子菜单
('10', 'dashboard:analysis', '分析页', 'MENU', '1', '/dashboard/analysis', 'dashboard/Analysis', 'AreaChartOutlined', 1, 1, 0, 0, NULL, NULL, '分析页', 1, 1, '/1/10', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'dashboard:workbench', '工作台', 'MENU', '1', '/dashboard/workbench', 'dashboard/Workbench', 'AppstoreOutlined', 2, 1, 0, 0, NULL, NULL, '工作台', 1, 1, '/1/11', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统管理子菜单
('20', 'system:user', '用户管理', 'MENU', '2', '/system/user', 'system/user/UserList', 'UserOutlined', 1, 1, 0, 0, 'system:user:view', NULL, '用户管理', 1, 1, '/2/20', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'system:role', '角色管理', 'MENU', '2', '/system/role', 'system/role/RoleList', 'TeamOutlined', 2, 1, 0, 0, 'system:role:view', NULL, '角色管理', 1, 1, '/2/21', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'system:menu', '菜单管理', 'MENU', '2', '/system/menu', 'system/menu/MenuList', 'MenuOutlined', 3, 1, 0, 0, 'menu:view', NULL, '菜单管理', 1, 1, '/2/22', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'system:dict', '字典管理', 'MENU', '2', '/system/dict', 'system/dict/DictList', 'BookOutlined', 4, 1, 0, 0, 'dict:view', NULL, '字典管理', 1, 1, '/2/23', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 组织管理子菜单
('30', 'org:company', '公司管理', 'MENU', '3', '/org/company', 'org/company/CompanyList', 'HomeOutlined', 1, 1, 0, 0, 'org:company:view', NULL, '公司管理', 1, 1, '/3/30', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('31', 'org:dept', '部门管理', 'MENU', '3', '/org/dept', 'org/dept/DeptList', 'ApartmentOutlined', 2, 1, 0, 0, 'org:dept:view', NULL, '部门管理', 1, 1, '/3/31', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('32', 'org:post', '岗位管理', 'MENU', '3', '/org/post', 'org/post/PostList', 'IdcardOutlined', 3, 1, 0, 0, NULL, NULL, '岗位管理', 1, 1, '/3/32', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统监控子菜单
('40', 'monitor:login-log', '登录日志', 'MENU', '4', '/monitor/login-log', 'monitor/LoginLogList', 'FileSearchOutlined', 1, 1, 0, 0, 'log:login:view', NULL, '登录日志', 1, 1, '/4/40', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('41', 'monitor:operate-log', '操作日志', 'MENU', '4', '/monitor/operate-log', 'monitor/OperateLogList', 'FileSyncOutlined', 2, 1, 0, 0, 'log:operate:view', NULL, '操作日志', 1, 1, '/4/41', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 九、账户角色关联数据
-- ----------------------------
INSERT INTO `iam_account_role` (`id`, `account_id`, `role_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, '1', NOW(), '1', 0),
('2', '2', '4', 1, '1', NOW(), '1', 0),
('3', '3', '4', 1, '1', NOW(), '1', 0),
('4', '4', '5', 1, '1', NOW(), '1', 0),
('5', '5', '6', 1, '1', NOW(), '1', 0);

-- ----------------------------
-- 十、角色权限关联数据
-- ----------------------------
INSERT INTO `iam_role_permission` (`id`, `role_id`, `permission_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
-- 超级管理员拥有所有权限
('1', '1', '1', 1, '1', NOW(), '1', 0),
('2', '1', '2', 1, '1', NOW(), '1', 0),
('3', '1', '3', 1, '1', NOW(), '1', 0),
('4', '1', '4', 1, '1', NOW(), '1', 0),
('5', '1', '5', 1, '1', NOW(), '1', 0),
('6', '1', '6', 1, '1', NOW(), '1', 0),
('7', '1', '7', 1, '1', NOW(), '1', 0),
('8', '1', '8', 1, '1', NOW(), '1', 0),
('9', '1', '9', 1, '1', NOW(), '1', 0),
('10', '1', '10', 1, '1', NOW(), '1', 0),
('11', '1', '11', 1, '1', NOW(), '1', 0),
('12', '1', '12', 1, '1', NOW(), '1', 0),
('13', '1', '13', 1, '1', NOW(), '1', 0),
('14', '1', '14', 1, '1', NOW(), '1', 0),
('15', '1', '15', 1, '1', NOW(), '1', 0),
('16', '1', '16', 1, '1', NOW(), '1', 0),
('17', '1', '17', 1, '1', NOW(), '1', 0),
('18', '1', '20', 1, '1', NOW(), '1', 0),
('19', '1', '21', 1, '1', NOW(), '1', 0),
('20', '1', '30', 1, '1', NOW(), '1', 0),
('21', '1', '31', 1, '1', NOW(), '1', 0),
('22', '1', '32', 1, '1', NOW(), '1', 0),
-- 开发人员权限
('30', '4', '1', 1, '1', NOW(), '1', 0),
('31', '4', '10', 1, '1', NOW(), '1', 0),
('32', '4', '13', 1, '1', NOW(), '1', 0),
('33', '4', '15', 1, '1', NOW(), '1', 0),
('34', '4', '16', 1, '1', NOW(), '1', 0),
-- 测试人员权限
('40', '5', '1', 1, '1', NOW(), '1', 0),
('41', '5', '30', 1, '1', NOW(), '1', 0),
('42', '5', '31', 1, '1', NOW(), '1', 0),
('43', '5', '32', 1, '1', NOW(), '1', 0),
-- 运维人员权限
('50', '6', '1', 1, '1', NOW(), '1', 0),
('51', '6', '30', 1, '1', NOW(), '1', 0),
('52', '6', '31', 1, '1', NOW(), '1', 0),
('53', '6', '32', 1, '1', NOW(), '1', 0);

-- ----------------------------
-- 十一、账户部门关联数据
-- ----------------------------
INSERT INTO `iam_account_dept` (`id`, `account_id`, `dept_id`, `is_primary`, `is_leader`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, 1, '1', NOW(), '1', 0),
('2', '2', '3', 1, 1, '1', NOW(), '1', 0),
('3', '3', '3', 1, 0, '1', NOW(), '1', 0),
('4', '4', '4', 1, 0, '1', NOW(), '1', 0),
('5', '5', '5', 1, 0, '1', NOW(), '1', 0);

-- ----------------------------
-- 十二、登录日志测试数据
-- ----------------------------
INSERT INTO `iam_login_log` (`log_id`, `account_id`, `account_code`, `account_name`, `login_type`, `login_status`, `login_fail_reason`, `login_ip`, `login_location`, `login_device`, `login_browser`, `login_os`, `login_time`, `logout_time`, `online_duration`, `tenant_id`) VALUES
('1', '1', 'admin', '系统管理员', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR), 3600, '1'),
('2', '1', 'admin', '系统管理员', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 1 HOUR), NULL, NULL, '1'),
('3', '2', 'zhangsan', '张三', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.100', '北京市海淀区', 'PC', 'Firefox 121.0', 'macOS 14', DATE_SUB(NOW(), INTERVAL 30 MINUTE), NULL, NULL, '1'),
('4', NULL, 'unknown', '未知用户', 'PASSWORD', 'FAIL', '用户名或密码错误', '192.168.1.200', '北京市朝阳区', 'PC', 'Edge 120.0', 'Windows 10', DATE_SUB(NOW(), INTERVAL 15 MINUTE), NULL, NULL, '1');

-- ----------------------------
-- 十三、操作日志测试数据
-- ----------------------------
INSERT INTO `iam_operate_log` (`log_id`, `operate_type`, `module_name`, `business_type`, `business_id`, `method_name`, `request_url`, `request_method`, `request_params`, `response_result`, `operate_status`, `error_msg`, `execute_duration`, `operator_id`, `operator_name`, `operator_dept`, `operate_ip`, `operate_location`, `operate_time`, `tenant_id`) VALUES
('1', 'LOGIN', '认证模块', '用户登录', '1', 'login', '/api/auth/login', 'POST', '{"username":"admin"}', '{"code":200,"message":"登录成功"}', 'SUCCESS', NULL, 150, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('2', 'CREATE', '用户管理', '新增用户', '2', 'createUser', '/api/system/user', 'POST', '{"username":"zhangsan","realName":"张三"}', '{"code":200,"message":"创建成功"}', 'SUCCESS', NULL, 80, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('3', 'UPDATE', '角色管理', '修改角色', '1', 'updateRole', '/api/system/role/1', 'PUT', '{"roleName":"超级管理员"}', '{"code":200,"message":"更新成功"}', 'SUCCESS', NULL, 60, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('4', 'DELETE', '部门管理', '删除部门', '10', 'deleteDept', '/api/org/dept/10', 'DELETE', '{}', '{"code":200,"message":"删除成功"}', 'SUCCESS', NULL, 45, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1');

-- ----------------------------
-- 十四、字典类型测试数据
-- ----------------------------
INSERT INTO `core_dictionary` (`dict_id`, `dict_code`, `dict_name`, `dict_type`, `dict_source`, `dict_sql`, `dict_api`, `dict_config`, `dict_status`, `dict_is_builtin`, `dict_sort`, `dict_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'user_status', '用户状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 1, '用户账户状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sex', '性别', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 2, '性别字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'yes_no', '是否', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 3, '是否字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'status', '状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 4, '通用状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'menu_type', '菜单类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 5, '菜单类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'account_type', '账户类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 6, '账户类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'permission_type', '权限类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 7, '权限类型字典', '1', NOW(), '1', NULL, NULL, 0, 0);

-- ----------------------------
-- 十五、字典数据测试数据
-- ----------------------------
INSERT INTO `core_dictionary_item` (`item_id`, `dict_id`, `item_code`, `item_name`, `item_value`, `item_parent_id`, `item_level`, `item_path`, `item_icon`, `item_color`, `item_css_class`, `item_status`, `item_is_default`, `item_sort`, `item_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`) VALUES
-- 用户状态
('1', '1', 'ENABLED', '启用', '1', NULL, 1, '/1', NULL, 'green', 'success', 1, 1, 1, '启用状态', '1', NOW(), '1', NULL, NULL, 0),
('2', '1', 'DISABLED', '禁用', '0', NULL, 1, '/2', NULL, 'red', 'danger', 1, 0, 2, '禁用状态', '1', NOW(), '1', NULL, NULL, 0),
-- 性别
('3', '2', 'MALE', '男', '1', NULL, 1, '/3', NULL, 'blue', 'primary', 1, 0, 1, '男性', '1', NOW(), '1', NULL, NULL, 0),
('4', '2', 'FEMALE', '女', '2', NULL, 1, '/4', NULL, 'pink', 'danger', 1, 0, 2, '女性', '1', NOW(), '1', NULL, NULL, 0),
('5', '2', 'UNKNOWN', '未知', '0', NULL, 1, '/5', NULL, 'gray', 'default', 1, 1, 3, '未知', '1', NOW(), '1', NULL, NULL, 0),
-- 是否
('6', '3', 'YES', '是', '1', NULL, 1, '/6', NULL, 'green', 'success', 1, 0, 1, '是', '1', NOW(), '1', NULL, NULL, 0),
('7', '3', 'NO', '否', '0', NULL, 1, '/7', NULL, 'red', 'danger', 1, 0, 2, '否', '1', NOW(), '1', NULL, NULL, 0),
-- 状态
('8', '4', 'ENABLED', '启用', '1', NULL, 1, '/8', NULL, 'green', 'success', 1, 1, 1, '启用', '1', NOW(), '1', NULL, NULL, 0),
('9', '4', 'DISABLED', '禁用', '0', NULL, 1, '/9', NULL, 'red', 'danger', 1, 0, 2, '禁用', '1', NOW(), '1', NULL, NULL, 0),
-- 菜单类型
('10', '5', 'DIRECTORY', '目录', '1', NULL, 1, '/10', NULL, 'blue', 'primary', 1, 0, 1, '目录类型', '1', NOW(), '1', NULL, NULL, 0),
('11', '5', 'MENU', '菜单', '2', NULL, 1, '/11', NULL, 'green', 'success', 1, 0, 2, '菜单类型', '1', NOW(), '1', NULL, NULL, 0),
('12', '5', 'BUTTON', '按钮', '3', NULL, 1, '/12', NULL, 'orange', 'warning', 1, 0, 3, '按钮类型', '1', NOW(), '1', NULL, NULL, 0),
-- 账户类型
('13', '6', 'SYSTEM', '系统账户', '1', NULL, 1, '/13', NULL, 'blue', 'primary', 1, 1, 1, '系统账户', '1', NOW(), '1', NULL, NULL, 0),
('14', '6', 'THIRD', '第三方账户', '2', NULL, 1, '/14', NULL, 'green', 'success', 1, 0, 2, '第三方账户', '1', NOW(), '1', NULL, NULL, 0),
-- 权限类型
('15', '7', 'MENU', '菜单权限', '1', NULL, 1, '/15', NULL, 'blue', 'primary', 1, 0, 1, '菜单权限', '1', NOW(), '1', NULL, NULL, 0),
('16', '7', 'BUTTON', '按钮权限', '2', NULL, 1, '/16', NULL, 'green', 'success', 1, 0, 2, '按钮权限', '1', NOW(), '1', NULL, NULL, 0),
('17', '7', 'API', '接口权限', '3', NULL, 1, '/17', NULL, 'orange', 'warning', 1, 0, 3, '接口权限', '1', NOW(), '1', NULL, NULL, 0),
('18', '7', 'DATA', '数据权限', '4', NULL, 1, '/18', NULL, 'purple', 'info', 1, 0, 4, '数据权限', '1', NOW(), '1', NULL, NULL, 0);

-- =============================================
-- 十六、使用存储过程生成大量测试数据
-- =============================================

-- 删除已存在的存储过程
DROP PROCEDURE IF EXISTS generate_test_users;
DROP PROCEDURE IF EXISTS generate_login_logs;
DROP PROCEDURE IF EXISTS generate_operate_logs;

-- 存储过程1：生成测试用户和账户（每次生成500条）
DELIMITER //
CREATE PROCEDURE generate_test_users()
BEGIN
    DECLARE i INT DEFAULT 6;
    DECLARE dept_count INT;
    DECLARE post_count INT;
    DECLARE rand_dept_id VARCHAR(50);
    DECLARE rand_post_id VARCHAR(50);
    DECLARE rand_role_id VARCHAR(50);

    -- 获取部门数量
    SELECT COUNT(*) INTO dept_count FROM iam_department WHERE is_deleted = 0;
    -- 获取岗位数量
    SELECT COUNT(*) INTO post_count FROM iam_post WHERE is_deleted = 0;

    -- 循环生成500个用户和账户
    WHILE i <= 505 DO
        -- 随机分配部门和岗位
        SET rand_dept_id = (SELECT dept_id FROM iam_department WHERE is_deleted = 0 ORDER BY RAND() LIMIT 1);
        SET rand_post_id = (SELECT post_id FROM iam_post WHERE is_deleted = 0 ORDER BY RAND() LIMIT 1);
        SET rand_role_id = (SELECT role_id FROM iam_role WHERE is_deleted = 0 ORDER BY RAND() LIMIT 1);

        -- 插入用户数据
        INSERT INTO `iam_user` (`user_id`, `user_code`, `user_name`, `user_sex`, `user_birthday`, `user_id_card`, `user_phone`, `user_mail`, `user_photo`, `user_address`, `user_native_place`, `user_nation`, `user_marital_status`, `user_political_status`, `user_work_number`, `user_entry_date`, `user_probation_end_date`, `user_employment_type`, `user_education`, `user_status`, `tenant_id`, `create_org_id`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`, `is_deleted`, `version`)
        VALUES (
            CONCAT('U', LPAD(i, 6, '0')),
            CONCAT('USER', LPAD(i, 5, '0')),
            CONCAT('用户', i),
            IF(RAND() > 0.5, 'MALE', 'FEMALE'),
            DATE_SUB(CURDATE(), INTERVAL FLOOR(20 + RAND() * 30) YEAR),
            CONCAT('11010119', FLOOR(70 + RAND() * 30), LPAD(FLOOR(RAND() * 10000), 4, '0'), LPAD(FLOOR(RAND() * 10000), 4, '0')),
            CONCAT('138', LPAD(FLOOR(RAND() * 100000000), 8, '0')),
            CONCAT('user', i, '@ssitao.com'),
            NULL,
            CONCAT('北京市', ELT(FLOOR(1 + RAND() * 8), '海淀区', '朝阳区', '西城区', '东城区', '丰台区', '石景山区', '通州区', '昌平区')),
            ELT(FLOOR(1 + RAND() * 10), '北京', '河北', '山东', '山西', '河南', '辽宁', '吉林', '黑龙江', '江苏', '浙江'),
            'HAN',
            IF(RAND() > 0.7, 'MARRIED', 'SINGLE'),
            'MEMBER',
            CONCAT('EMP', LPAD(i, 5, '0')),
            DATE_SUB(CURDATE(), INTERVAL FLOOR(1 + RAND() * 36) MONTH),
            DATE_ADD(DATE_SUB(CURDATE(), INTERVAL FLOOR(1 + RAND() * 36) MONTH), INTERVAL 3 MONTH),
            'FULL_TIME',
            ELT(FLOOR(1 + RAND() * 4), 'HIGH_SCHOOL', 'COLLEGE', 'BACHELOR', 'MASTER'),
            'ON_JOB',
            '1',
            rand_dept_id,
            '1',
            NOW(),
            NULL,
            NULL,
            0,
            0
        );

        -- 插入账户数据
        INSERT INTO `iam_account` (`account_id`, `account_code`, `account_name`, `account_password`, `account_phone`, `account_mail`, `account_avatar`, `account_type`, `account_source`, `account_status`, `account_is_admin`, `account_last_login_time`, `account_last_login_ip`, `account_init_password`, `account_init_password_reset_time`, `tenant_id`, `create_org_id`, `create_org_name`, `create_user_id`, `create_user_name`, `create_time`, `modify_org_id`, `modify_org_name`, `modify_user_id`, `modify_user_name`, `modify_time`, `is_deleted`, `version`)
        VALUES (
            CONCAT('A', LPAD(i, 6, '0')),
            CONCAT('user', i),
            CONCAT('用户', i),
            '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi',
            CONCAT('138', LPAD(FLOOR(RAND() * 100000000), 8, '0')),
            CONCAT('user', i, '@ssitao.com'),
            NULL,
            'SYSTEM',
            'LOCAL',
            1,
            0,
            NOW(),
            CONCAT('192.168.', FLOOR(1 + RAND() * 255), '.', FLOOR(1 + RAND() * 255)),
            1,
            NULL,
            '1',
            rand_dept_id,
            (SELECT dept_name FROM iam_department WHERE dept_id = rand_dept_id),
            '1',
            '系统管理员',
            NOW(),
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            0,
            0
        );

        -- 插入账户角色关联
        INSERT INTO `iam_account_role` (`id`, `account_id`, `role_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`)
        VALUES (CONCAT('AR', LPAD(i, 5, '0')), CONCAT('A', LPAD(i, 6, '0')), rand_role_id, 1, '1', NOW(), '1', 0);

        -- 插入账户部门关联
        INSERT INTO `iam_account_dept` (`id`, `account_id`, `dept_id`, `is_primary`, `is_leader`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`)
        VALUES (CONCAT('AD', LPAD(i, 5, '0')), CONCAT('A', LPAD(i, 6, '0')), rand_dept_id, 1, 0, '1', NOW(), '1', 0);

        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

-- 存储过程2：生成登录日志（每次生成1000条）
DELIMITER //
CREATE PROCEDURE generate_login_logs()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE account_count INT;
    DECLARE rand_account_id VARCHAR(50);
    DECLARE rand_account_code VARCHAR(64);
    DECLARE rand_account_name VARCHAR(128);
    DECLARE login_status_val VARCHAR(10);
    DECLARE fail_reason VARCHAR(200);
    DECLARE ip1 INT;
    DECLARE ip2 INT;
    DECLARE ip3 INT;
    DECLARE ip4 INT;

    -- 获取账户数量
    SELECT COUNT(*) INTO account_count FROM iam_account WHERE is_deleted = 0;

    -- 循环生成1000条登录日志
    WHILE i <= 1000 DO
        -- 随机选择账户
        SET rand_account_id = (SELECT account_id FROM iam_account WHERE is_deleted = 0 ORDER BY RAND() LIMIT 1);
        SET rand_account_code = (SELECT account_code FROM iam_account WHERE account_id = rand_account_id);
        SET rand_account_name = (SELECT account_name FROM iam_account WHERE account_id = rand_account_id);

        -- 随机登录状态（90%成功，10%失败）
        IF RAND() > 0.1 THEN
            SET login_status_val = 'SUCCESS';
            SET fail_reason = NULL;
        ELSE
            SET login_status_val = 'FAIL';
            SET fail_reason = ELT(FLOOR(1 + RAND() * 3), '用户名或密码错误', '账号已被禁用', '验证码错误');
        END IF;

        -- 随机生成IP地址
        SET ip1 = FLOOR(1 + RAND() * 223);
        SET ip2 = FLOOR(RAND() * 256);
        SET ip3 = FLOOR(RAND() * 256);
        SET ip4 = FLOOR(RAND() * 256);

        -- 插入登录日志
        INSERT INTO `iam_login_log` (`log_id`, `account_id`, `account_code`, `account_name`, `login_type`, `login_status`, `login_fail_reason`, `login_ip`, `login_location`, `login_device`, `login_browser`, `login_os`, `login_time`, `logout_time`, `online_duration`, `tenant_id`)
        VALUES (
            CONCAT('L', LPAD(i + 100, 7, '0')),
            IF(login_status_val = 'FAIL' AND RAND() > 0.5, NULL, rand_account_id),
            IF(login_status_val = 'FAIL' AND RAND() > 0.5, 'unknown', rand_account_code),
            IF(login_status_val = 'FAIL' AND RAND() > 0.5, '未知用户', rand_account_name),
            'PASSWORD',
            login_status_val,
            fail_reason,
            CONCAT(ip1, '.', ip2, '.', ip3, '.', ip4),
            ELT(FLOOR(1 + RAND() * 10), '北京市', '上海市', '广州市', '深圳市', '杭州市', '成都市', '武汉市', '西安市', '南京市', '天津市'),
            ELT(FLOOR(1 + RAND() * 3), 'PC', 'Mobile', 'Tablet'),
            ELT(FLOOR(1 + RAND() * 5), 'Chrome 120', 'Firefox 121', 'Edge 120', 'Safari 17', 'Opera 105'),
            ELT(FLOOR(1 + RAND() * 5), 'Windows 11', 'Windows 10', 'macOS 14', 'Ubuntu 22.04', 'CentOS 8'),
            DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 720) HOUR),
            IF(login_status_val = 'SUCCESS', DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 360) HOUR), NULL),
            IF(login_status_val = 'SUCCESS', FLOOR(60 + RAND() * 3600), NULL),
            '1'
        );

        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

-- 存储过程3：生成操作日志（每次生成500条）
DELIMITER //
CREATE PROCEDURE generate_operate_logs()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE account_count INT;
    DECLARE rand_account_id VARCHAR(50);
    DECLARE rand_account_name VARCHAR(128);
    DECLARE rand_dept_name VARCHAR(128);
    DECLARE operate_type_val VARCHAR(20);
    DECLARE module_val VARCHAR(50);
    DECLARE business_type_val VARCHAR(50);
    DECLARE method_val VARCHAR(50);
    DECLARE url_val VARCHAR(200);
    DECLARE http_method_val VARCHAR(10);
    DECLARE status_val VARCHAR(20);

    -- 获取账户数量
    SELECT COUNT(*) INTO account_count FROM iam_account WHERE is_deleted = 0;

    -- 循环生成500条操作日志
    WHILE i <= 500 DO
        -- 随机选择账户
        SET rand_account_id = (SELECT account_id FROM iam_account WHERE is_deleted = 0 ORDER BY RAND() LIMIT 1);
        SET rand_account_name = (SELECT account_name FROM iam_account WHERE account_id = rand_account_id);
        SET rand_dept_name = (SELECT create_org_name FROM iam_account WHERE account_id = rand_account_id);

        -- 随机操作类型
        SET operate_type_val = ELT(FLOOR(1 + RAND() * 5), 'CREATE', 'UPDATE', 'DELETE', 'QUERY', 'LOGIN');
        SET module_val = ELT(FLOOR(1 + RAND() * 8), '用户管理', '角色管理', '菜单管理', '部门管理', '公司管理', '岗位管理', '字典管理', '系统配置');
        SET business_type_val = ELT(FLOOR(1 + RAND() * 10), '新增用户', '编辑用户', '删除用户', '查询用户', '新增角色', '编辑角色', '删除角色', '菜单配置', '数据导出', '数据导入');
        SET method_val = ELT(FLOOR(1 + RAND() * 8), 'create', 'update', 'delete', 'query', 'export', 'import', 'save', 'remove');
        SET url_val = CONCAT('/api/', ELT(FLOOR(1 + RAND() * 5), 'system', 'org', 'workflow', 'file', 'message'), '/', ELT(FLOOR(1 + RAND() * 5), 'user', 'role', 'menu', 'dept', 'post'));
        SET http_method_val = ELT(FLOOR(1 + RAND() * 4), 'GET', 'POST', 'PUT', 'DELETE');

        -- 随机状态（95%成功，5%失败）
        IF RAND() > 0.05 THEN
            SET status_val = 'SUCCESS';
        ELSE
            SET status_val = 'FAIL';
        END IF;

        -- 插入操作日志
        INSERT INTO `iam_operate_log` (`log_id`, `operate_type`, `module_name`, `business_type`, `business_id`, `method_name`, `request_url`, `request_method`, `request_params`, `response_result`, `operate_status`, `error_msg`, `execute_duration`, `operator_id`, `operator_name`, `operator_dept`, `operate_ip`, `operate_location`, `operate_time`, `tenant_id`)
        VALUES (
            CONCAT('O', LPAD(i + 500, 7, '0')),
            operate_type_val,
            module_val,
            business_type_val,
            CONCAT('ID', FLOOR(10000 + RAND() * 90000)),
            method_val,
            url_val,
            http_method_val,
            CONCAT('{"id": "', FLOOR(10000 + RAND() * 90000), '", "name": "test', i, '"}'),
            CONCAT('{"code": 200, "message": "操作成功"}'),
            status_val,
            IF(status_val = 'FAIL', ELT(FLOOR(1 + RAND() * 3), '数据不存在', '权限不足', '系统错误'), NULL),
            FLOOR(10 + RAND() * 500),
            rand_account_id,
            rand_account_name,
            rand_dept_name,
            CONCAT('192.168.', FLOOR(1 + RAND() * 255), '.', FLOOR(1 + RAND() * 255)),
            '北京市海淀区',
            DATE_SUB(NOW(), INTERVAL FLOOR(RAND() * 720) HOUR),
            '1'
        );

        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;

-- 执行存储过程生成测试数据
CALL generate_test_users();
CALL generate_login_logs();
CALL generate_operate_logs();

-- 清理存储过程
DROP PROCEDURE IF EXISTS generate_test_users;
DROP PROCEDURE IF EXISTS generate_login_logs;
DROP PROCEDURE IF EXISTS generate_operate_logs;

-- 查看生成的数据统计
SELECT '公司数量' AS data_type, COUNT(*) AS count FROM iam_company WHERE is_deleted = 0
UNION ALL
SELECT '部门数量', COUNT(*) FROM iam_department WHERE is_deleted = 0
UNION ALL
SELECT '岗位数量', COUNT(*) FROM iam_post WHERE is_deleted = 0
UNION ALL
SELECT '用户数量', COUNT(*) FROM iam_user WHERE is_deleted = 0
UNION ALL
SELECT '账户数量', COUNT(*) FROM iam_account WHERE is_deleted = 0
UNION ALL
SELECT '角色数量', COUNT(*) FROM iam_role WHERE is_deleted = 0
UNION ALL
SELECT '权限数量', COUNT(*) FROM iam_permission WHERE is_deleted = 0
UNION ALL
SELECT '菜单数量', COUNT(*) FROM iam_menu WHERE is_deleted = 0
UNION ALL
SELECT '登录日志数量', COUNT(*) FROM iam_login_log
UNION ALL
SELECT '操作日志数量', COUNT(*) FROM iam_operate_log;

SET FOREIGN_KEY_CHECKS = 1;
