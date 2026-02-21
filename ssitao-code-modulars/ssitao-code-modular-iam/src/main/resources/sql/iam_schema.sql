-- =============================================
-- SSITAO 基础权限平台 - 数据库初始化脚本
-- 版本: 1.1.1
-- 作者: ssitao-code
-- 说明: IAM 认证授权相关表结构
-- =============================================

-- =============================================
-- 1. 用户表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_user`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`    VARCHAR(50)  NOT NULL COMMENT '用户名',
    `password`    VARCHAR(100) NOT NULL COMMENT '密码',
    `nickname`    VARCHAR(50) COMMENT '昵称',
    `avatar`      VARCHAR(255) COMMENT '头像',
    `email`       VARCHAR(100) COMMENT '邮箱',
    `mobile`      VARCHAR(20) COMMENT '手机号',
    `gender`      TINYINT  DEFAULT 0 COMMENT '性别：0-未知 1-男 2-女',
    `status`      TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `dept_id`     BIGINT COMMENT '部门ID',
    `super_admin` TINYINT  DEFAULT 0 COMMENT '是否超管：1-是 0-否',
    `remark`      VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     VARCHAR(50) COMMENT '创建人',
    `updater`     VARCHAR(50) COMMENT '更新人',
    `deleted`     TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_dept_id` (`dept_id`),
    KEY `idx_mobile` (`mobile`),
    KEY `idx_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

-- =============================================
-- 2. 角色表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `code`        VARCHAR(50) NOT NULL COMMENT '角色编码',
    `name`        VARCHAR(50) NOT NULL COMMENT '角色名称',
    `sort`        INT      DEFAULT 0 COMMENT '排序',
    `status`      TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `remark`      VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     VARCHAR(50) COMMENT '创建人',
    `updater`     VARCHAR(50) COMMENT '更新人',
    `deleted`     TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

-- =============================================
-- 3. 权限表（菜单表）
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_permission`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `code`        VARCHAR(100) NOT NULL COMMENT '权限编码',
    `name`        VARCHAR(50)  NOT NULL COMMENT '权限名称',
    `type`        VARCHAR(10)  NOT NULL COMMENT '类型：menu-菜单 button-按钮 api-接口',
    `parent_id`   BIGINT   DEFAULT 0 COMMENT '父权限ID',
    `path`        VARCHAR(255) COMMENT '路由路径',
    `component`   VARCHAR(255) COMMENT '组件路径',
    `icon`        VARCHAR(50) COMMENT '图标',
    `sort`        INT      DEFAULT 0 COMMENT '排序',
    `visible`     TINYINT  DEFAULT 1 COMMENT '是否可见：1-显示 0-隐藏',
    `status`      TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `remark`      VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     VARCHAR(50) COMMENT '创建人',
    `updater`     VARCHAR(50) COMMENT '更新人',
    `deleted`     TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表（菜单表）';

-- =============================================
-- 4. 部门表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_dept`
(
    `id`             BIGINT      NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `parent_id`      BIGINT   DEFAULT 0 COMMENT '父部门ID',
    `name`           VARCHAR(50) NOT NULL COMMENT '部门名称',
    `code`           VARCHAR(50) COMMENT '部门编码',
    `sort`           INT      DEFAULT 0 COMMENT '排序',
    `leader_user_id` BIGINT COMMENT '负责人用户ID',
    `phone`          VARCHAR(20) COMMENT '联系电话',
    `email`          VARCHAR(100) COMMENT '邮箱',
    `status`         TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `tenant_id`      VARCHAR(20) COMMENT '租户ID',
    `remark`         VARCHAR(500) COMMENT '备注',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`        VARCHAR(50) COMMENT '创建人',
    `updater`        VARCHAR(50) COMMENT '更新人',
    `deleted`        TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='部门表';

-- =============================================
-- 5. 岗位表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_post`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
    `code`        VARCHAR(50) NOT NULL COMMENT '岗位编码',
    `name`        VARCHAR(50) NOT NULL COMMENT '岗位名称',
    `sort`        INT      DEFAULT 0 COMMENT '排序',
    `status`      TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `remark`      VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     VARCHAR(50) COMMENT '创建人',
    `updater`     VARCHAR(50) COMMENT '更新人',
    `deleted`     TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='岗位表';

-- =============================================
-- 6. 用户角色关联表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_user_role`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     BIGINT NOT NULL COMMENT '用户ID',
    `role_id`     BIGINT NOT NULL COMMENT '角色ID',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色关联表';

-- =============================================
-- 7. 角色权限关联表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_role_permission`
(
    `id`            BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_id`       BIGINT NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT NOT NULL COMMENT '权限ID',
    `tenant_id`     VARCHAR(20) COMMENT '租户ID',
    `create_time`   DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
    KEY `idx_permission_id` (`permission_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色权限关联表';

-- =============================================
-- 8. 用户岗位关联表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_user_post`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     BIGINT NOT NULL COMMENT '用户ID',
    `post_id`     BIGINT NOT NULL COMMENT '岗位ID',
    `tenant_id`   VARCHAR(20) COMMENT '租户ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_post` (`user_id`, `post_id`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户岗位关联表';

-- =============================================
-- 9. 登录日志表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_login_log`
(
    `id`             BIGINT NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `username`       VARCHAR(50) COMMENT '用户名',
    `user_id`        BIGINT COMMENT '用户ID',
    `login_type`     VARCHAR(20) COMMENT '登录类型：password-密码 mobile-手机号 social-第三方',
    `login_ip`       VARCHAR(50) COMMENT '登录IP',
    `login_location` VARCHAR(255) COMMENT '登录地点',
    `user_agent`     VARCHAR(500) COMMENT '用户代理',
    `status`         TINYINT COMMENT '登录状态：1-成功 0-失败',
    `error_message`  VARCHAR(500) COMMENT '错误信息',
    `login_time`     DATETIME COMMENT '登录时间',
    `tenant_id`      VARCHAR(20) COMMENT '租户ID',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_login_time` (`login_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='登录日志表';

-- =============================================
-- 10. 操作日志表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_operate_log`
(
    `id`             BIGINT NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `module`         VARCHAR(50) COMMENT '操作模块',
    `operation`      VARCHAR(100) COMMENT '操作名称',
    `type`           VARCHAR(20) COMMENT '操作类型：CREATE-创建 UPDATE-更新 DELETE-删除 QUERY-查询 EXPORT-导出 IMPORT-导入 OTHER-其他',
    `method`         VARCHAR(200) COMMENT '请求方法',
    `request_uri`    VARCHAR(255) COMMENT '请求URI',
    `request_method` VARCHAR(10) COMMENT '请求方式',
    `request_args`   TEXT COMMENT '请求参数',
    `result`         TEXT COMMENT '返回结果',
    `status`         TINYINT COMMENT '操作状态：1-成功 0-失败',
    `error_msg`      TEXT COMMENT '错误信息',
    `duration`       BIGINT COMMENT '执行时长(ms)',
    `user_id`        BIGINT COMMENT '用户ID',
    `username`       VARCHAR(50) COMMENT '用户名',
    `ip`             VARCHAR(50) COMMENT '操作IP',
    `location`       VARCHAR(255) COMMENT '操作地点',
    `user_agent`     VARCHAR(500) COMMENT '用户代理',
    `operate_time`   DATETIME COMMENT '操作时间',
    `tenant_id`      VARCHAR(20) COMMENT '租户ID',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_tenant_id` (`tenant_id`),
    KEY `idx_operate_time` (`operate_time`),
    KEY `idx_module` (`module`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='操作日志表';

-- =============================================
-- 11. 租户表
-- =============================================
CREATE TABLE IF NOT EXISTS `iam_tenant`
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT COMMENT '租户ID',
    `code`           VARCHAR(50)  NOT NULL COMMENT '租户编码',
    `name`           VARCHAR(100) NOT NULL COMMENT '租户名称',
    `contact_user`   VARCHAR(50) COMMENT '联系人',
    `contact_mobile` VARCHAR(20) COMMENT '联系电话',
    `status`         TINYINT  DEFAULT 1 COMMENT '状态：1-正常 0-停用',
    `expire_time`    DATETIME COMMENT '过期时间',
    `max_users`      INT      DEFAULT -1 COMMENT '最大用户数，-1表示不限制',
    `remark`         VARCHAR(500) COMMENT '备注',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`        VARCHAR(50) COMMENT '创建人',
    `updater`        VARCHAR(50) COMMENT '更新人',
    `deleted`        TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='租户表';

-- =============================================
-- 12. 字典类型表
-- =============================================
CREATE TABLE IF NOT EXISTS `system_dict_type`
(
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '字典类型ID',
    `code`        VARCHAR(50)  NOT NULL COMMENT '字典编码',
    `name`        VARCHAR(100) NOT NULL COMMENT '字典名称',
    `sort`        INT      DEFAULT 0 COMMENT '排序',
    `status`      TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `remark`      VARCHAR(500) COMMENT '备注',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`     VARCHAR(50) COMMENT '创建人',
    `updater`     VARCHAR(50) COMMENT '更新人',
    `deleted`     TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典类型表';

-- =============================================
-- 13. 字典数据表
-- =============================================
CREATE TABLE IF NOT EXISTS `system_dict_data`
(
    `id`             BIGINT       NOT NULL AUTO_INCREMENT COMMENT '字典数据ID',
    `dict_type_code` VARCHAR(50)  NOT NULL COMMENT '字典类型编码',
    `label`          VARCHAR(100) NOT NULL COMMENT '字典标签',
    `value`          VARCHAR(100) NOT NULL COMMENT '字典值',
    `sort`           INT      DEFAULT 0 COMMENT '排序',
    `color_type`     VARCHAR(20) COMMENT '颜色类型',
    `css_class`      VARCHAR(100) COMMENT 'CSS样式',
    `status`         TINYINT  DEFAULT 1 COMMENT '状态：1-启用 0-禁用',
    `remark`         VARCHAR(500) COMMENT '备注',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`        VARCHAR(50) COMMENT '创建人',
    `updater`        VARCHAR(50) COMMENT '更新人',
    `deleted`        TINYINT  DEFAULT 0 COMMENT '是否删除：0-否 1-是',
    PRIMARY KEY (`id`),
    KEY `idx_dict_type_code` (`dict_type_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='字典数据表';

-- =============================================
-- 初始化数据
-- =============================================

-- 初始化超级管理员用户（密码：admin123）
INSERT INTO `iam_user` (`id`, `username`, `password`, `nickname`, `super_admin`, `status`, `tenant_id`)
VALUES (1, 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHdHmAjQJtQ2ux4yWNTWoRi', '管理员', 1, 1, 'default')
ON DUPLICATE KEY UPDATE `password` = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z2EHdHmAjQJtQ2ux4yWNTWoRi';

-- 初始化超管角色
INSERT INTO `iam_role` (`id`, `code`, `name`, `sort`, `status`, `tenant_id`)
VALUES (1, 'super_admin', '超级管理员', 0, 1, 'default')
ON DUPLICATE KEY UPDATE `name` = '超级管理员';

-- 初始化用户角色关联
INSERT INTO `iam_user_role` (`user_id`, `role_id`, `tenant_id`)
VALUES (1, 1, 'default')
ON DUPLICATE KEY UPDATE `tenant_id` = 'default';

-- 初始化默认租户
INSERT INTO `iam_tenant` (`id`, `code`, `name`, `status`, `max_users`)
VALUES (1, 'default', '默认租户', 1, -1)
ON DUPLICATE KEY UPDATE `name` = '默认租户';
