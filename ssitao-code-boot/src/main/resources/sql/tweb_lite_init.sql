-- =============================================
-- tweb-lite 系统数据库初始化脚本
-- =============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `tweb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `tweb`;

-- =============================================
-- 用户表
-- =============================================
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `user_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `name` VARCHAR(50) NOT NULL COMMENT '姓名',
    `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
    `pic_url` VARCHAR(200) DEFAULT NULL COMMENT '用户头像',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '用户邮箱',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `gender` TINYINT(1) DEFAULT 1 COMMENT '用户性别(0=女,1=男)',
    `dept_id` BIGINT(20) DEFAULT NULL COMMENT '部门ID',
    `dept_name` VARCHAR(100) DEFAULT NULL COMMENT '部门名称',
    `position_id` BIGINT(20) DEFAULT NULL COMMENT '职位ID',
    `position_name` VARCHAR(100) DEFAULT NULL COMMENT '职位名称',
    `role_ids` VARCHAR(500) DEFAULT NULL COMMENT '角色ID(多个角色用逗号分隔)',
    `skin` VARCHAR(20) DEFAULT 'blue' COMMENT '皮肤主题',
    `theme` VARCHAR(20) DEFAULT 'light' COMMENT '主题模式',
    `status` TINYINT(1) DEFAULT 1 COMMENT '用户状态(0=禁用,1=正常)',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标志(0=正常,1=删除)',
    `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` VARCHAR(50) DEFAULT NULL COMMENT '最后登录IP',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id` BIGINT(20) DEFAULT NULL COMMENT '更新者ID',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_dept_id` (`dept_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';

-- =============================================
-- 角色表
-- =============================================
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
    `role_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
    `sort_no` INT(11) DEFAULT 0 COMMENT '排序号',
    `status` TINYINT(1) DEFAULT 1 COMMENT '角色状态(0=禁用,1=正常)',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标志(0=正常,1=删除)',
    `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id` BIGINT(20) DEFAULT NULL COMMENT '更新者ID',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色表';

-- =============================================
-- 部门表
-- =============================================
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
    `dept_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
    `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父部门ID',
    `dept_name` VARCHAR(50) NOT NULL COMMENT '部门名称',
    `dept_code` VARCHAR(50) DEFAULT NULL COMMENT '部门编码',
    `leader` VARCHAR(50) DEFAULT NULL COMMENT '部门负责人',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '部门邮箱',
    `sort_no` INT(11) DEFAULT 0 COMMENT '显示顺序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '部门状态(0=禁用,1=正常)',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标志(0=正常,1=删除)',
    `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id` BIGINT(20) DEFAULT NULL COMMENT '更新者ID',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`dept_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统部门表';

-- =============================================
-- 菜单表
-- =============================================
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
    `menu_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `parent_id` BIGINT(20) DEFAULT 0 COMMENT '父菜单ID',
    `menu_name` VARCHAR(50) NOT NULL COMMENT '菜单名称',
    `menu_code` VARCHAR(50) DEFAULT NULL COMMENT '菜单编码',
    `menu_type` CHAR(1) DEFAULT 'C' COMMENT '菜单类型(M=目录,C=菜单,F=按钮)',
    `url` VARCHAR(200) DEFAULT NULL COMMENT '菜单URL',
    `icon` VARCHAR(100) DEFAULT NULL COMMENT '菜单图标',
    `permission` VARCHAR(100) DEFAULT NULL COMMENT '权限标识',
    `sort_no` INT(11) DEFAULT 0 COMMENT '显示顺序',
    `status` TINYINT(1) DEFAULT 1 COMMENT '菜单状态(0=禁用,1=正常)',
    `visible` TINYINT(1) DEFAULT 1 COMMENT '是否可见(0=隐藏,1=显示)',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标志(0=正常,1=删除)',
    `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id` BIGINT(20) DEFAULT NULL COMMENT '更新者ID',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`menu_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统菜单表';

-- =============================================
-- 职位表
-- =============================================
DROP TABLE IF EXISTS `sys_position`;
CREATE TABLE `sys_position` (
    `position_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '职位ID',
    `position_name` VARCHAR(50) NOT NULL COMMENT '职位名称',
    `position_code` VARCHAR(50) NOT NULL COMMENT '职位编码',
    `position_level` INT(11) DEFAULT 1 COMMENT '职位级别',
    `sort_no` INT(11) DEFAULT 0 COMMENT '排序号',
    `status` TINYINT(1) DEFAULT 1 COMMENT '职位状态(0=禁用,1=正常)',
    `deleted` TINYINT(1) DEFAULT 0 COMMENT '删除标志(0=正常,1=删除)',
    `create_id` BIGINT(20) DEFAULT NULL COMMENT '创建者ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_id` BIGINT(20) DEFAULT NULL COMMENT '更新者ID',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`position_id`),
    UNIQUE KEY `uk_position_code` (`position_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统职位表';

-- =============================================
-- 角色菜单关联表
-- =============================================
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    `menu_id` BIGINT(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';

-- =============================================
-- 用户角色关联表
-- =============================================
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
    `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
    `role_id` BIGINT(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- =============================================
-- 操作日志表
-- =============================================
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
    `log_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
    `username` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
    `operation` VARCHAR(100) DEFAULT NULL COMMENT '操作内容',
    `method` VARCHAR(200) DEFAULT NULL COMMENT '请求方法',
    `params` TEXT DEFAULT NULL COMMENT '请求参数',
    `ip` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `status` TINYINT(1) DEFAULT 1 COMMENT '操作状态(0=失败,1=成功)',
    `error_msg` VARCHAR(2000) DEFAULT NULL COMMENT '错误消息',
    `cost_time` BIGINT(20) DEFAULT NULL COMMENT '耗时(毫秒)',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`log_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

-- =============================================
-- 初始化数据
-- =============================================

-- 初始化部门数据
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `dept_name`, `dept_code`, `leader`, `sort_no`) VALUES
(1, 0, 'EWeb科技', 'EWEB', 'admin', 1),
(2, 1, '研发部', 'DEV', 'zhangsan', 1),
(3, 1, '产品部', 'PROD', 'lisi', 2),
(4, 1, '市场部', 'MARKET', 'wangwu', 3),
(5, 2, '前端组', 'FRONTEND', 'zhangsan', 1),
(6, 2, '后端组', 'BACKEND', 'zhangsan', 2);

-- 初始化职位数据
INSERT INTO `sys_position` (`position_id`, `position_name`, `position_code`, `position_level`, `sort_no`) VALUES
(1, '系统管理员', 'ADMIN', 1, 1),
(2, '部门经理', 'MANAGER', 2, 2),
(3, '高级工程师', 'SENIOR', 3, 3),
(4, '中级工程师', 'MIDDLE', 4, 4),
(5, '初级工程师', 'JUNIOR', 5, 5),
(6, '实习生', 'INTERN', 6, 6);

-- 初始化角色数据
INSERT INTO `sys_role` (`role_id`, `role_name`, `role_code`, `description`, `sort_no`) VALUES
(1, '超级管理员', 'ROLE_ADMIN', '拥有系统所有权限', 1),
(2, '部门经理', 'ROLE_MANAGER', '部门管理权限', 2),
(3, '普通用户', 'ROLE_USER', '普通用户权限', 3),
(4, '访客', 'ROLE_GUEST', '访客只读权限', 4);

-- 初始化用户数据 (密码为123456的加密形式，这里使用明文，实际应该使用BCrypt等加密)
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `name`, `nickname`, `pic_url`, `email`, `phone`, `dept_id`, `dept_name`, `position_id`, `position_name`, `role_ids`, `skin`, `status`) VALUES
(1, 'admin', 'admin123', '系统管理员', 'admin', '/img/common/user-avatar.png', 'admin@tweb.com', '13800138000', 1, 'EWeb科技', 1, '系统管理员', '1', 'blue', 1),
(2, 'zhangsan', '123456', '张三', '张三', '/img/common/user-avatar.png', 'zhangsan@tweb.com', '13800138001', 2, '研发部', 3, '高级工程师', '2,3', 'blue', 1),
(3, 'lisi', '123456', '李四', '李四', '/img/common/user-avatar.png', 'lisi@tweb.com', '13800138002', 3, '产品部', 3, '高级工程师', '2,3', 'green', 1),
(4, 'wangwu', '123456', '王五', '王五', '/img/common/user-avatar.png', 'wangwu@tweb.com', '13800138003', 4, '市场部', 4, '中级工程师', '3', 'red', 1),
(5, 'zhaoliu', '123456', '赵六', '赵六', '/img/common/user-avatar.png', 'zhaoliu@tweb.com', '13800138004', 2, '研发部', 5, '初级工程师', '3', 'blue', 1);

-- 初始化菜单数据
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `menu_name`, `menu_code`, `menu_type`, `url`, `icon`, `sort_no`, `visible`) VALUES
-- 一级菜单
(1, 0, '工作台', 'WORKBENCH', 'M', '', 'zmdi zmdi-home', 1, 1),
(2, 0, '项目管理', 'PROJECT', 'M', '', 'zmdi zmdi-folder', 2, 1),
(3, 0, '实体管理', 'ENTITY', 'M', '', 'zmdi zmdi-file', 3, 1),
(4, 0, '任务管理', 'TASK', 'M', '', 'zmdi zmdi-check-square', 4, 1),
(5, 0, '流程管理', 'WORKFLOW', 'M', '', 'zmdi zmdi-shape', 5, 1),
(6, 0, '报表中心', 'REPORT', 'M', '', 'zmdi zmdi-chart', 6, 1),
(7, 0, '仪表盘', 'DASHBOARD', 'M', '', 'zmdi zmdi-dashboard', 7, 1),
(8, 0, '系统设置', 'SYSTEM', 'M', '', 'zmdi zmdi-settings', 8, 1),

-- 工作台子菜单
(101, 1, '我的桌面', 'MY_DESKTOP', 'C', 'myDesktop', '', 1, 1),
(102, 1, '我的日历', 'MY_CALENDAR', 'C', 'common/resource/myCalendar', '', 2, 1),
(103, 1, '周报', 'WEEKLY', 'C', 'common/resource/weekly', '', 3, 1),
(104, 1, '月报', 'MONTHLY', 'C', 'common/resource/monthly', '', 4, 1),
(105, 1, '资源日历', 'RESOURCE_CALENDAR', 'C', 'common/resource/resourceCalendarList', '', 5, 1),

-- 项目管理子菜单
(201, 2, '项目列表', 'PROJECT_LIST', 'C', 'search/projectList', '', 1, 1),
(202, 2, '我的项目', 'MY_PROJECT', 'C', 'entity/pjt/planList', '', 2, 1),
(203, 2, '项目阶段', 'PROJECT_PHASE', 'C', 'entity/pjt/pjtPhaseList', '', 3, 1),
(204, 2, '项目里程碑', 'PROJECT_MILESTONE', 'C', 'entity/pjt/pjtMileList', '', 4, 1),
(205, 2, '项目动态', 'PROJECT_DYNAMIC', 'C', 'entity/pjt/pjtDynamicsList', '', 5, 1),

-- 实体管理子菜单
(301, 3, '实体列表', 'ENTITY_LIST', 'C', 'entity/entityList', '', 1, 1),
(302, 3, '我的实体', 'MY_ENTITY', 'C', 'entity/myEntityList', '', 2, 1),
(303, 3, '字段管理', 'FIELD_LIST', 'C', 'field/fieldList', '', 3, 1),
(304, 3, '数据源', 'DATA_SOURCE', 'C', 'field/dataSourceList', '', 4, 1),
(305, 3, '类型管理', 'TYPE_LIST', 'C', 'common/type/typeList', '', 5, 1),

-- 任务管理子菜单
(401, 4, '我的任务', 'MY_TASK', 'C', 'task/myTask', '', 1, 1),
(402, 4, '任务列表', 'TASK_LIST', 'C', 'task/myTaskList', '', 2, 1),
(403, 4, '指派给我的', 'ASSIGN_TASK', 'C', 'task/myResTaskList', '', 3, 1),
(404, 4, '关联任务', 'LINK_TASK', 'C', 'task/linkTaskList', '', 4, 1),

-- 流程管理子菜单
(501, 5, '流程列表', 'WORKFLOW_LIST', 'C', 'workflow/workflowMngList', '', 1, 1),
(502, 5, '我的流程', 'MY_WORKFLOW', 'C', 'workflow/myWorkflowList', '', 2, 1),
(503, 5, '流程分组', 'WORKFLOW_GROUP', 'C', 'workflow/workflowGroupList', '', 3, 1),
(504, 5, '流程实例', 'WORKFLOW_INSTANCE', 'C', 'workflow/instanceList', '', 4, 1),
(505, 5, '生命周期', 'LIFECYCLE', 'C', 'common/manage/lifecycleList', '', 5, 1),

-- 报表中心子菜单
(601, 6, '报表列表', 'REPORT_LIST', 'C', 'common/report/reportChartList', '', 1, 1),
(602, 6, '项目报表', 'PROJECT_REPORT', 'C', 'common/report/pjtOverviewList', '', 2, 1),
(603, 6, '单据报表', 'BILL_REPORT', 'C', 'common/report/reportBillList', '', 3, 1),

-- 仪表盘子菜单
(701, 7, '仪表盘列表', 'DASHBOARD_LIST', 'C', 'dashboard/dashboardList', '', 1, 1),
(702, 7, '我的仪表盘', 'MY_DASHBOARD', 'C', 'dashboard/myDashboard', '', 2, 1),
(703, 7, '区块管理', 'DASHBOARD_BLOCK', 'C', 'dashboard/dashboardBlockList', '', 3, 1),

-- 系统设置子菜单
(801, 8, '用户列表', 'USER_LIST', 'C', 'system/user/userList', '', 1, 1),
(802, 8, '用户树', 'USER_TREE', 'C', 'system/user/userTree', '', 2, 1),
(803, 8, '职位管理', 'POSITION_LIST', 'C', 'system/user/positionList', '', 3, 1),
(804, 8, '角色列表', 'ROLE_LIST', 'C', 'system/role/roleList', '', 4, 1),
(805, 8, '菜单管理', 'MENU_LIST', 'C', 'system/menu/menuList', '', 5, 1),
(806, 8, '部门管理', 'DEPT_LIST', 'C', 'system/dept/deptList', '', 6, 1),
(807, 8, '公司信息', 'COMPANY', 'C', 'system/company/company', '', 7, 1),
(808, 8, '产品许可', 'LICENSE', 'C', 'system/company/licenseInfo', '', 8, 1),
(809, 8, '高级设置', 'ADVANCE_SET', 'C', 'system/advance/advanceSet', '', 9, 1),
(810, 8, '导航配置', 'NAVIGATE_SET', 'C', 'system/advance/navigateSet', '', 10, 1),
(811, 8, '定时器', 'TIMER', 'C', 'system/advance/timer', '', 11, 1),
(812, 8, '在线用户', 'ONLINE', 'C', 'system/online/online', '', 12, 1),
(813, 8, '操作日志', 'OPERATE_LOG', 'C', 'system/record/operateRecordList', '', 13, 1);

-- 初始化角色菜单关联 (超级管理员拥有所有菜单权限)
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`)
SELECT 1, menu_id FROM `sys_menu`;

-- 部门经理权限
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(2, 1), (2, 101), (2, 102), (2, 103), (2, 104), (2, 105),
(2, 2), (2, 201), (2, 202), (2, 203), (2, 204), (2, 205),
(2, 4), (2, 401), (2, 402), (2, 403), (2, 404);

-- 普通用户权限
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(3, 1), (3, 101), (3, 102), (3, 103),
(3, 4), (3, 401), (3, 402);

-- 初始化用户角色关联
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(2, 3),
(3, 2),
(3, 3),
(4, 3),
(5, 3);
