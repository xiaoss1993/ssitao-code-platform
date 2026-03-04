-- ============================================
-- SSITAO Code Platform - IAM 初始化数据
-- 版本: 2.0.0
-- 日期: 2026-02-28
-- 描述: 初始化IAM模块基础数据，包括管理员账号、角色、权限等
-- ============================================

SET NAMES utf8mb4;

-- ============================================
-- 1. 初始化公司数据
-- ============================================
INSERT INTO `iam_company` (`company_id`, `company_code`, `company_name`, `company_short_name`, `company_type`, `parent_id`, `company_level`, `company_path`, `company_status`, `company_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('1001', 'HQ', '思涛科技有限公司', '思涛科技', 'HEADQUARTERS', NULL, 1, '/1001', 1, '总部', 'default', NOW(), 0),
('1002', 'BJ', '北京分公司', '北京分', 'BRANCH', '1001', 2, '/1001/1002', 1, '北京分公司', 'default', NOW(), 0),
('1003', 'SH', '上海分公司', '上海分', 'BRANCH', '1001', 2, '/1001/1003', 1, '上海分公司', 'default', NOW(), 0);

-- ============================================
-- 2. 初始化部门数据
-- ============================================
INSERT INTO `iam_department` (`dept_id`, `dept_code`, `dept_name`, `dept_short_name`, `parent_id`, `company_id`, `dept_level`, `dept_path`, `dept_type`, `dept_status`, `dept_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('2001', 'TECH', '技术部', '技术', NULL, '1001', 1, '/2001', 'TECHNICAL', 1, '技术研发部门', 'default', NOW(), 0),
('2002', 'HR', '人力资源部', '人资', NULL, '1001', 1, '/2002', 'FUNCTIONAL', 1, '人力资源管理', 'default', NOW(), 0),
('2003', 'FIN', '财务部', '财务', NULL, '1001', 1, '/2003', 'FUNCTIONAL', 1, '财务管理', 'default', NOW(), 0),
('2004', 'MKT', '市场部', '市场', NULL, '1001', 1, '/2004', 'BUSINESS', 1, '市场营销', 'default', NOW(), 0),
('2005', 'SALES', '销售部', '销售', NULL, '1001', 1, '/2005', 'BUSINESS', 1, '销售部门', 'default', NOW(), 0),
('2006', 'OPS', '运维部', '运维', NULL, '1001', 1, '/2006', 'TECHNICAL', 1, '运维保障', 'default', NOW(), 0),
('2007', 'RD_FE', '前端开发组', '前端', '2001', '1001', 2, '/2001/2007', 'TECHNICAL', 1, '前端开发', 'default', NOW(), 0),
('2008', 'RD_BE', '后端开发组', '后端', '2001', '1001', 2, '/2001/2008', 'TECHNICAL', 1, '后端开发', 'default', NOW(), 0),
('2009', 'RD_QA', '测试组', '测试', '2001', '1001', 2, '/2001/2009', 'TECHNICAL', 1, '质量保障', 'default', NOW(), 0);

-- ============================================
-- 3. 初始化岗位数据
-- ============================================
INSERT INTO `iam_post` (`post_id`, `post_code`, `post_name`, `post_type`, `post_level`, `post_status`, `post_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('3001', 'CEO', '首席执行官', 'MANAGEMENT', 1, 1, '公司最高管理者', 'default', NOW(), 0),
('3002', 'CTO', '首席技术官', 'MANAGEMENT', 2, 1, '技术负责人', 'default', NOW(), 0),
('3003', 'CFO', '首席财务官', 'MANAGEMENT', 2, 1, '财务负责人', 'default', NOW(), 0),
('3004', 'PM', '项目经理', 'MANAGEMENT', 3, 1, '项目管理', 'default', NOW(), 0),
('3005', 'DEV_SENIOR', '高级开发工程师', 'TECHNICAL', 4, 1, '高级开发', 'default', NOW(), 0),
('3006', 'DEV_JUNIOR', '初级开发工程师', 'TECHNICAL', 5, 1, '初级开发', 'default', NOW(), 0),
('3007', 'QA', '测试工程师', 'TECHNICAL', 5, 1, '测试人员', 'default', NOW(), 0),
('3008', 'OPS', '运维工程师', 'TECHNICAL', 5, 1, '运维人员', 'default', NOW(), 0);

-- ============================================
-- 4. 初始化角色数据
-- ============================================
INSERT INTO `iam_role` (`role_id`, `role_code`, `role_name`, `role_type`, `role_level`, `role_status`, `role_is_builtin`, `role_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('4001', 'SUPER_ADMIN', '超级管理员', 'SYSTEM', 1, 1, 1, '系统超级管理员，拥有所有权限', 'default', NOW(), 0),
('4002', 'ADMIN', '系统管理员', 'SYSTEM', 2, 1, 1, '系统管理员，拥有大部分管理权限', 'default', NOW(), 0),
('4003', 'DEPT_ADMIN', '部门管理员', 'BUSINESS', 3, 1, 0, '部门管理员，管理部门内用户', 'default', NOW(), 0),
('4004', 'USER', '普通用户', 'BUSINESS', 4, 1, 1, '普通用户，基础权限', 'default', NOW(), 0);

-- ============================================
-- 5. 初始化权限数据
-- ============================================
INSERT INTO `iam_permission` (`permission_id`, `permission_code`, `permission_name`, `permission_type`, `permission_resource`, `parent_id`, `permission_sort`, `permission_status`, `permission_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
-- 系统管理
('5001', 'system', '系统管理', 'MENU', '/admin', NULL, 1, 1, '系统管理菜单', 'default', NOW(), 0),
('5002', 'system:config', '系统配置', 'MENU', '/admin/config', '5001', 1, 1, '系统配置管理', 'default', NOW(), 0),
('5003', 'system:config:view', '查看配置', 'BUTTON', 'system:config:view', '5002', 1, 1, '查看系统配置', 'default', NOW(), 0),
('5004', 'system:config:edit', '编辑配置', 'BUTTON', 'system:config:edit', '5002', 2, 1, '编辑系统配置', 'default', NOW(), 0),

-- 权限管理
('5101', 'auth', '权限管理', 'MENU', '/admin/auth', NULL, 2, 1, '权限管理菜单', 'default', NOW(), 0),
('5102', 'auth:admin', '管理员管理', 'MENU', '/admin/admin', '5101', 1, 1, '管理员管理', 'default', NOW(), 0),
('5103', 'auth:admin:view', '查看管理员', 'BUTTON', 'auth:admin:view', '5102', 1, 1, '查看管理员信息', 'default', NOW(), 0),
('5104', 'auth:admin:add', '新增管理员', 'BUTTON', 'auth:admin:add', '5102', 2, 1, '新增管理员', 'default', NOW(), 0),
('5105', 'auth:admin:edit', '编辑管理员', 'BUTTON', 'auth:admin:edit', '5102', 3, 1, '编辑管理员', 'default', NOW(), 0),
('5106', 'auth:admin:delete', '删除管理员', 'BUTTON', 'auth:admin:delete', '5102', 4, 1, '删除管理员', 'default', NOW(), 0),
('5111', 'auth:role', '角色管理', 'MENU', '/admin/group', '5101', 2, 1, '角色管理', 'default', NOW(), 0),
('5112', 'auth:role:view', '查看角色', 'BUTTON', 'auth:role:view', '5111', 1, 1, '查看角色', 'default', NOW(), 0),
('5113', 'auth:role:add', '新增角色', 'BUTTON', 'auth:role:add', '5111', 2, 1, '新增角色', 'default', NOW(), 0),
('5114', 'auth:role:edit', '编辑角色', 'BUTTON', 'auth:role:edit', '5111', 3, 1, '编辑角色', 'default', NOW(), 0),
('5115', 'auth:role:delete', '删除角色', 'BUTTON', 'auth:role:delete', '5111', 4, 1, '删除角色', 'default', NOW(), 0),
('5121', 'auth:permission', '权限管理', 'MENU', '/admin/rule', '5101', 3, 1, '权限规则管理', 'default', NOW(), 0),

-- 组织管理
('5201', 'org', '组织管理', 'MENU', '/admin/org', NULL, 3, 1, '组织管理菜单', 'default', NOW(), 0),
('5202', 'org:company', '公司管理', 'MENU', '/admin/company', '5201', 1, 1, '公司管理', 'default', NOW(), 0),
('5203', 'org:dept', '部门管理', 'MENU', '/admin/department', '5201', 2, 1, '部门管理', 'default', NOW(), 0),
('5204', 'org:post', '岗位管理', 'MENU', '/admin/post', '5201', 3, 1, '岗位管理', 'default', NOW(), 0),

-- 内容管理
('5301', 'content', '内容管理', 'MENU', '/admin/content', NULL, 4, 1, '内容管理菜单', 'default', NOW(), 0),
('5302', 'content:page', '单页管理', 'MENU', '/admin/page', '5301', 1, 1, '单页管理', 'default', NOW(), 0),
('5303', 'content:category', '分类管理', 'MENU', '/admin/category', '5301', 2, 1, '分类管理', 'default', NOW(), 0),

-- 控制台
('5401', 'dashboard', '控制台', 'MENU', '/dashboard', NULL, 0, 1, '控制台', 'default', NOW(), 0);

-- ============================================
-- 6. 初始化角色权限关联
-- ============================================
-- 超级管理员拥有所有权限
INSERT INTO `iam_role_permission` (`role_id`, `permission_id`, `create_time`) VALUES
('4001', '5001', NOW()), ('4001', '5002', NOW()), ('4001', '5003', NOW()), ('4001', '5004', NOW()),
('4001', '5101', NOW()), ('4001', '5102', NOW()), ('4001', '5103', NOW()), ('4001', '5104', NOW()),
('4001', '5105', NOW()), ('4001', '5106', NOW()), ('4001', '5111', NOW()), ('4001', '5112', NOW()),
('4001', '5113', NOW()), ('4001', '5114', NOW()), ('4001', '5115', NOW()), ('4001', '5121', NOW()),
('4001', '5201', NOW()), ('4001', '5202', NOW()), ('4001', '5203', NOW()), ('4001', '5204', NOW()),
('4001', '5301', NOW()), ('4001', '5302', NOW()), ('4001', '5303', NOW()),
('4001', '5401', NOW());

-- 系统管理员权限
INSERT INTO `iam_role_permission` (`role_id`, `permission_id`, `create_time`) VALUES
('4002', '5001', NOW()), ('4002', '5002', NOW()), ('4002', '5003', NOW()), ('4002', '5004', NOW()),
('4002', '5101', NOW()), ('4002', '5102', NOW()), ('4002', '5103', NOW()), ('4002', '5104', NOW()),
('4002', '5105', NOW()), ('4002', '5111', NOW()), ('4002', '5112', NOW()), ('4002', '5113', NOW()),
('4002', '5114', NOW()), ('4002', '5201', NOW()), ('4002', '5202', NOW()), ('4002', '5203', NOW()),
('4002', '5301', NOW()), ('4002', '5302', NOW()), ('4002', '5303', NOW()),
('4002', '5401', NOW());

-- 部门管理员权限
INSERT INTO `iam_role_permission` (`role_id`, `permission_id`, `create_time`) VALUES
('4003', '5101', NOW()), ('4003', '5102', NOW()), ('4003', '5103', NOW()),
('4003', '5201', NOW()), ('4003', '5203', NOW()),
('4003', '5301', NOW()), ('4003', '5302', NOW()),
('4003', '5401', NOW());

-- 普通用户权限
INSERT INTO `iam_role_permission` (`role_id`, `permission_id`, `create_time`) VALUES
('4004', '5401', NOW());

-- ============================================
-- 7. 初始化菜单数据
-- ============================================
INSERT INTO `iam_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_type`, `parent_id`, `menu_path`, `menu_component`, `menu_icon`, `menu_sort`, `menu_status`, `menu_visible`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('6001', 'dashboard', '控制台', 'MENU', NULL, '/dashboard', 'dashboard/index', 'fa fa-dashboard', 0, 1, 1, 'default', NOW(), 0),
('6002', 'system', '系统管理', 'DIR', NULL, '/system', NULL, 'fa fa-cogs', 1, 1, 1, 'default', NOW(), 0),
('6003', 'system:config', '系统配置', 'MENU', '6002', '/system/config', 'system/config/index', 'fa fa-cog', 1, 1, 1, 'default', NOW(), 0),
('6004', 'system:profile', '个人配置', 'MENU', '6002', '/system/profile', 'system/profile/index', 'fa fa-user', 2, 1, 1, 'default', NOW(), 0),
('6005', 'auth', '权限管理', 'DIR', NULL, '/auth', NULL, 'fa fa-group', 2, 1, 1, 'default', NOW(), 0),
('6006', 'auth:admin', '管理员管理', 'MENU', '6005', '/auth/admin', 'auth/admin/index', 'fa fa-user', 1, 1, 1, 'default', NOW(), 0),
('6007', 'auth:role', '角色管理', 'MENU', '6005', '/auth/role', 'auth/role/index', 'fa fa-group', 2, 1, 1, 'default', NOW(), 0),
('6008', 'auth:permission', '权限管理', 'MENU', '6005', '/auth/permission', 'auth/permission/index', 'fa fa-lock', 3, 1, 1, 'default', NOW(), 0),
('6009', 'org', '组织管理', 'DIR', NULL, '/org', NULL, 'fa fa-sitemap', 3, 1, 1, 'default', NOW(), 0),
('6010', 'org:company', '公司管理', 'MENU', '6009', '/org/company', 'org/company/index', 'fa fa-building', 1, 1, 1, 'default', NOW(), 0),
('6011', 'org:dept', '部门管理', 'MENU', '6009', '/org/dept', 'org/dept/index', 'fa fa-users', 2, 1, 1, 'default', NOW(), 0),
('6012', 'org:post', '岗位管理', 'MENU', '6009', '/org/post', 'org/post/index', 'fa fa-briefcase', 3, 1, 1, 'default', NOW(), 0),
('6013', 'content', '内容管理', 'DIR', NULL, '/content', NULL, 'fa fa-file-text', 4, 1, 1, 'default', NOW(), 0),
('6014', 'content:page', '单页管理', 'MENU', '6013', '/content/page', 'content/page/index', 'fa fa-file', 1, 1, 1, 'default', NOW(), 0),
('6015', 'content:category', '分类管理', 'MENU', '6013', '/content/category', 'content/category/index', 'fa fa-list', 2, 1, 1, 'default', NOW(), 0);

-- ============================================
-- 8. 初始化管理员账号 (密码: admin123)
-- BCrypt加密后的密码
-- ============================================
INSERT INTO `iam_account` (`account_id`, `account_code`, `username`, `password`, `account_type`, `account_source`, `account_status`, `is_admin`, `is_super_admin`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('8001', 'ADMIN001', 'admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 'ADMIN', 'LOCAL', 1, 1, 1, 'default', NOW(), 0);

INSERT INTO `iam_user` (`user_id`, `account_id`, `username`, `real_name`, `user_type`, `gender`, `email`, `mobile`, `user_status`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('9001', '8001', 'admin', '系统管理员', 'ADMIN', 'MALE', 'admin@ssitao.com', '13800138000', 1, 'default', NOW(), 0);

-- 关联管理员和角色
INSERT INTO `iam_account_role` (`account_id`, `role_id`, `create_time`) VALUES
('8001', '4001', NOW());

-- 关联用户和部门
INSERT INTO `iam_dept_user` (`dept_id`, `user_id`, `is_leader`, `create_time`) VALUES
('2001', '9001', 1, NOW());

-- ============================================
-- 9. 初始化系统配置
-- ============================================
INSERT INTO `core_config` (`config_id`, `config_code`, `config_name`, `config_value`, `config_type`, `config_category`, `config_status`, `config_is_builtin`, `config_desc`, `tenant_id`, `create_time`, `is_deleted`) VALUES
('7001', 'site.name', '站点名称', 'SSITAO Code Platform', 'STRING', 'site', 1, 1, '网站名称', 'default', NOW(), 0),
('7002', 'site.logo', '站点Logo', '/assets/img/logo.png', 'STRING', 'site', 1, 0, '网站Logo', 'default', NOW(), 0),
('7003', 'site.copyright', '版权信息', 'Copyright © 2024-2025 SSITAO', 'STRING', 'site', 1, 0, '版权信息', 'default', NOW(), 0),
('7004', 'site.icp', 'ICP备案号', '', 'STRING', 'site', 1, 0, 'ICP备案号', 'default', NOW(), 0),
('7101', 'upload.maxSize', '上传大小限制', '10485760', 'NUMBER', 'upload', 1, 1, '文件上传大小限制(字节)', 'default', NOW(), 0),
('7102', 'upload.allowTypes', '允许上传类型', 'jpg,jpeg,png,gif,pdf,doc,docx,xls,xlsx', 'STRING', 'upload', 1, 1, '允许上传的文件类型', 'default', NOW(), 0),
('7201', 'security.loginFailLimit', '登录失败限制', '5', 'NUMBER', 'security', 1, 1, '登录失败次数限制', 'default', NOW(), 0),
('7202', 'security.loginLockTime', '锁定时间', '1800', 'NUMBER', 'security', 1, 1, '账号锁定时间(秒)', 'default', NOW(), 0);

-- ============================================
-- 完成初始化
-- ============================================
SELECT 'IAM初始化数据导入完成!' AS Message;
