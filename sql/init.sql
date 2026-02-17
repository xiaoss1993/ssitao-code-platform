-- SSITAO Code Platform 数据库初始化脚本
-- 基于 jecloud 分析结果设计

-- ==================== 用户相关表 ====================

-- 用户表
CREATE TABLE `sys_user` (
    `id` varchar(32) NOT NULL COMMENT '用户ID',
    `user_code` varchar(64) NOT NULL COMMENT '用户编码',
    `user_name` varchar(128) NOT NULL COMMENT '用户名',
    `nickname` varchar(128) COMMENT '昵称',
    `password` varchar(256) NOT NULL COMMENT '密码',
    `email` varchar(128) COMMENT '邮箱',
    `phone` varchar(32) COMMENT '手机号',
    `avatar` varchar(512) COMMENT '头像',
    `gender` char(1) DEFAULT '0' COMMENT '性别（0-未知,1-男,2-女）',
    `birthday` varchar(32) COMMENT '生日',
    `dept_id` varchar(32) COMMENT '部门ID',
    `dept_name` varchar(128) COMMENT '部门名称',
    `post_id` varchar(32) COMMENT '岗位ID',
    `post_name` varchar(128) COMMENT '岗位名称',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `user_type` char(2) DEFAULT '01' COMMENT '用户类型（00-系统用户,01-租户用户）',
    `is_admin` char(1) DEFAULT '0' COMMENT '是否超级管理员（0-否,1-是）',
    `account_status` char(1) DEFAULT '0' COMMENT '账号状态（0-正常,1-停用,2-锁定）',
    `last_login_ip` varchar(64) COMMENT '最后登录IP',
    `last_login_time` varchar(32) COMMENT '最后登录时间',
    `password_modify_time` varchar(32) COMMENT '密码最后修改时间',
    `password_need_modify` char(1) DEFAULT '0' COMMENT '密码是否需要修改（0-否,1-是）',
    `user_inited_code` char(1) DEFAULT '0' COMMENT '初始化标记（0-未初始化,1-已初始化）',
    `aud_flag` char(1) DEFAULT '0' COMMENT '审核标记',
    `create_org` varchar(64) COMMENT '创建人部门编码',
    `create_org_name` varchar(128) COMMENT '创建人部门名称',
    `create_time` datetime COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人编码',
    `create_user_name` varchar(128) COMMENT '创建人姓名',
    `flag` char(1) DEFAULT '1' COMMENT '启用标记',
    `status` char(1) DEFAULT '1' COMMENT '数据状态',
    `modify_org` varchar(64) COMMENT '修改人部门编码',
    `modify_org_name` varchar(128) COMMENT '修改人部门名称',
    `modify_time` datetime COMMENT '修改时间',
    `modify_user` varchar(32) COMMENT '修改人编码',
    `modify_user_name` varchar(128) COMMENT '修改人姓名',
    `order_index` int COMMENT '排序字段',
    `PIID` varchar(64) COMMENT '流程实例ID',
    `PDID` varchar(64) COMMENT '流程定义ID',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `remark` varchar(512) COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_code` (`user_code`),
    UNIQUE KEY `uk_user_name` (`user_name`),
    KEY `idx_dept_id` (`dept_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 角色表
CREATE TABLE `sys_role` (
    `id` varchar(32) NOT NULL COMMENT '角色ID',
    `role_code` varchar(64) NOT NULL COMMENT '角色编码',
    `role_name` varchar(128) NOT NULL COMMENT '角色名称',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `role_type` char(2) DEFAULT '02' COMMENT '角色类型（00-系统角色,01-租户角色,02-自定义角色）',
    `data_scope` char(2) DEFAULT '00' COMMENT '数据权限类型（00-全部,01-本部门,02-本部门及子部门,03-本人）',
    `role_status` char(1) DEFAULT '0' COMMENT '角色状态（0-正常,1-停用）',
    `role_sort` int DEFAULT 0 COMMENT '排序',
    `aud_flag` char(1) DEFAULT '0' COMMENT '审核标记',
    `create_org` varchar(64) COMMENT '创建人部门编码',
    `create_org_name` varchar(128) COMMENT '创建人部门名称',
    `create_time` datetime COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人编码',
    `create_user_name` varchar(128) COMMENT '创建人姓名',
    `flag` char(1) DEFAULT '1' COMMENT '启用标记',
    `status` char(1) DEFAULT '1' COMMENT '数据状态',
    `modify_org` varchar(64) COMMENT '修改人部门编码',
    `modify_org_name` varchar(128) COMMENT '修改人部门名称',
    `modify_time` datetime COMMENT '修改时间',
    `modify_user` varchar(32) COMMENT '修改人编码',
    `modify_user_name` varchar(128) COMMENT '修改人姓名',
    `order_index` int COMMENT '排序字段',
    `PIID` varchar(64) COMMENT '流程实例ID',
    `PDID` varchar(64) COMMENT '流程定义ID',
    `remark` varchar(512) COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_code` (`role_code`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 权限（菜单/按钮）表
CREATE TABLE `sys_permission` (
    `id` varchar(32) NOT NULL COMMENT '权限ID',
    `parent_id` varchar(32) DEFAULT '0' COMMENT '父权限ID',
    `permission_code` varchar(128) NOT NULL COMMENT '权限编码',
    `permission_name` varchar(128) NOT NULL COMMENT '权限名称',
    `permission_type` char(2) COMMENT '权限类型（00-目录,01-菜单,02-按钮）',
    `path` varchar(256) COMMENT '权限路径',
    `component` varchar(256) COMMENT '组件路径',
    `perms` varchar(256) COMMENT '权限标识（如：user:add）',
    `icon` varchar(128) COMMENT '图标',
    `is_frame` char(1) DEFAULT '0' COMMENT '是否外链（0-否,1-是）',
    `is_cache` char(1) DEFAULT '0' COMMENT '是否缓存（0-不缓存,1-缓存）',
    `visible` char(1) DEFAULT '1' COMMENT '是否显示（0-隐藏,1-显示）',
    `status` char(1) DEFAULT '0' COMMENT '状态（0-正常,1-停用）',
    `redirect` varchar(256) COMMENT '重定向地址',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `layer` int COMMENT '所在的层数',
    `node_type` varchar(32) COMMENT '节点类型',
    `node_info` varchar(512) COMMENT '功能信息',
    `node_info_type` varchar(32) COMMENT '功能信息类型',
    `path` varchar(512) COMMENT '路径',
    `aud_flag` char(1) DEFAULT '0' COMMENT '审核标记',
    `create_org` varchar(64) COMMENT '创建人部门编码',
    `create_org_name` varchar(128) COMMENT '创建人部门名称',
    `create_time` datetime COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人编码',
    `create_user_name` varchar(128) COMMENT '创建人姓名',
    `flag` char(1) DEFAULT '1' COMMENT '启用标记',
    `modify_org` varchar(64) COMMENT '修改人部门编码',
    `modify_org_name` varchar(128) COMMENT '修改人部门名称',
    `modify_time` datetime COMMENT '修改时间',
    `modify_user` varchar(32) COMMENT '修改人编码',
    `modify_user_name` varchar(128) COMMENT '修改人姓名',
    `order_index` int COMMENT '排序字段',
    `PIID` varchar(64) COMMENT '流程实例ID',
    `PDID` varchar(64) COMMENT '流程定义ID',
    `remark` varchar(512) COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_code` (`permission_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- 部门表
CREATE TABLE `sys_department` (
    `id` varchar(32) NOT NULL COMMENT '部门ID',
    `parent_id` varchar(32) DEFAULT '0' COMMENT '父部门ID',
    `dept_code` varchar(64) NOT NULL COMMENT '部门编码',
    `dept_name` varchar(128) NOT NULL COMMENT '部门名称',
    `layer` int COMMENT '部门层级',
    `path` varchar(512) COMMENT '部门路径',
    `leader_id` varchar(32) COMMENT '负责人ID',
    `leader_name` varchar(128) COMMENT '负责人姓名',
    `phone` varchar(32) COMMENT '联系电话',
    `email` varchar(128) COMMENT '邮箱',
    `order_index` int DEFAULT 0 COMMENT '排序',
    `status` char(1) DEFAULT '0' COMMENT '状态（0-正常,1-停用）',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `node_type` varchar(32) COMMENT '节点类型',
    `node_info` varchar(512) COMMENT '功能信息',
    `node_info_type` varchar(32) COMMENT '功能信息类型',
    `aud_flag` char(1) DEFAULT '0' COMMENT '审核标记',
    `create_org` varchar(64) COMMENT '创建人部门编码',
    `create_org_name` varchar(128) COMMENT '创建人部门名称',
    `create_time` datetime COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人编码',
    `create_user_name` varchar(128) COMMENT '创建人姓名',
    `flag` char(1) DEFAULT '1' COMMENT '启用标记',
    `modify_org` varchar(64) COMMENT '修改人部门编码',
    `modify_org_name` varchar(128) COMMENT '修改人部门名称',
    `modify_time` datetime COMMENT '修改时间',
    `modify_user` varchar(32) COMMENT '修改人编码',
    `modify_user_name` varchar(128) COMMENT '修改人姓名',
    `PIID` varchar(64) COMMENT '流程实例ID',
    `PDID` varchar(64) COMMENT '流程定义ID',
    `remark` varchar(512) COMMENT '备注',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dept_code` (`dept_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 用户角色关联表
CREATE TABLE `sys_user_role` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` varchar(32) NOT NULL COMMENT '用户ID',
    `role_id` varchar(32) NOT NULL COMMENT '角色ID',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- 角色权限关联表
CREATE TABLE `sys_role_permission` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id` varchar(32) NOT NULL COMMENT '角色ID',
    `permission_id` varchar(32) NOT NULL COMMENT '权限ID',
    `tenant_id` varchar(32) COMMENT '租户ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_user` varchar(32) COMMENT '创建人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
    KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ==================== 初始化数据 ====================

-- 初始化超级管理员用户（密码: 123456）
INSERT INTO `sys_user` (`id`, `user_code`, `user_name`, `nickname`, `password`, `is_admin`, `account_status`, `user_inited_code`)
VALUES ('1', 'admin', 'admin', '超级管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '1', '0', '1');

-- 初始化默认角色
INSERT INTO `sys_role` (`id`, `role_code`, `role_name`, `role_type`, `data_scope`, `role_status`, `role_sort`)
VALUES
    ('1', 'admin', '超级管理员', '00', '00', '0', 1),
    ('2', 'manager', '管理员', '01', '01', '0', 2),
    ('3', 'user', '普通用户', '02', '03', '0', 3);

-- 初始化超级管理员角色关联
INSERT INTO `sys_user_role` (`user_id`, `role_id`)
VALUES ('1', '1');

-- 初始化默认权限（菜单）
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_code`, `permission_name`, `permission_type`, `path`, `icon`, `order_index`)
VALUES
    ('1', '0', 'system', '系统管理', '00', '/system', 'setting', 1),
    ('2', '1', 'user', '用户管理', '01', '/system/user', 'user', 1),
    ('3', '1', 'role', '角色管理', '01', '/system/role', 'team', 2),
    ('4', '1', 'permission', '权限管理', '01', '/system/permission', 'lock', 3),
    ('5', '1', 'dept', '部门管理', '01', '/system/dept', 'apartment', 4);

-- 初始化按钮权限
INSERT INTO `sys_permission` (`id`, `parent_id`, `permission_code`, `permission_name`, `permission_type`, `perms`, `order_index`)
VALUES
    ('101', '2', 'user:view', '查看用户', '02', 'user:view', 1),
    ('102', '2', 'user:add', '新增用户', '02', 'user:add', 2),
    ('103', '2', 'user:edit', '编辑用户', '02', 'user:edit', 3),
    ('104', '2', 'user:delete', '删除用户', '02', 'user:delete', 4),
    ('105', '2', 'user:assign', '分配角色', '02', 'user:assign', 5),
    ('106', '2', 'user:reset-password', '重置密码', '02', 'user:reset-password', 6);
