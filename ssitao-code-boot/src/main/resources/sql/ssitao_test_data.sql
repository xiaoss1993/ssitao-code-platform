/*
 SSITAO 测试数据初始化脚本

 Source Server Type    : MySQL
 Source Server Version : 80023 (8.0.23)
 Source Schema         : ssitao

 Date: 27/02/2026
 Description: 参考jecloud库结构，为ssitao库生成测试数据
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 一、公司数据
-- =============================================
INSERT INTO `iam_company` (`company_id`, `company_code`, `company_name`, `company_short_name`, `company_type`, `company_level`, `company_parent_id`, `company_address`, `company_phone`, `company_mail`, `company_website`, `company_logo`, `company_legal_rep`, `company_registration_no`, `company_tax_no`, `company_status`, `company_desc`, `company_sort`, `company_tree_path`, `company_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'HQ', '思涛科技集团', '思涛科技', 'ENTERPRISE', 1, NULL, '北京市海淀区中关村大街1号', '010-88888888', 'contact@ssitao.com', 'https://www.ssitao.com', NULL, '李思涛', '91110108MA01XXXXX', '91110108MA01XXXXX', 1, '思涛科技集团总部', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'BJ', '北京分公司', '北京分', 'BRANCH', 2, '1', '北京市朝阳区建国路88号', '010-66666666', 'bj@ssitao.com', NULL, NULL, '王北京', NULL, NULL, 1, '北京分公司', 2, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'SH', '上海分公司', '上海分', 'BRANCH', 2, '1', '上海市浦东新区陆家嘴金融中心', '021-99999999', 'sh@ssitao.com', NULL, NULL, '赵上海', NULL, NULL, 1, '上海分公司', 3, '/1/3', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 二、部门数据
-- =============================================
INSERT INTO `iam_department` (`dept_id`, `dept_code`, `dept_name`, `dept_type`, `dept_parent_id`, `dept_company_id`, `dept_leader_id`, `dept_phone`, `dept_address`, `dept_status`, `dept_desc`, `dept_sort`, `dept_tree_path`, `dept_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'ROOT', '集团总部', 'HEADQUARTERS', NULL, '1', '1', '010-88888881', '总部大楼1层', 1, '集团总部部门', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'TECH', '技术研发中心', 'DEPARTMENT', '1', '1', '2', '010-88888882', '总部大楼5层', 1, '负责公司技术研发', 1, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEV', '软件开发部', 'DEPARTMENT', '2', '1', '3', '010-88888883', '总部大楼5层A区', 1, '负责软件开发', 1, '/1/2/3', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'QA', '质量保障部', 'DEPARTMENT', '2', '1', '4', '010-88888884', '总部大楼5层B区', 1, '负责质量保障', 2, '/1/2/4', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'OPS', '运维部', 'DEPARTMENT', '2', '1', '5', '010-88888885', '总部大楼地下一层', 1, '负责系统运维', 3, '/1/2/5', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'PRODUCT', '产品中心', 'DEPARTMENT', '1', '1', '6', '010-88888886', '总部大楼4层', 1, '负责产品设计', 2, '/1/6', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'SALES', '销售中心', 'DEPARTMENT', '1', '1', '7', '010-88888887', '总部大楼3层', 1, '负责市场销售', 3, '/1/7', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'HR', '人力资源部', 'DEPARTMENT', '1', '1', '8', '010-88888888', '总部大楼2层', 1, '负责人力资源', 4, '/1/8', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'FIN', '财务部', 'DEPARTMENT', '1', '1', '9', '010-88888889', '总部大楼2层', 1, '负责财务管理', 5, '/1/9', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 三、岗位数据
-- =============================================
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

-- =============================================
-- 四、用户数据
-- =============================================
INSERT INTO `iam_user` (`user_id`, `user_code`, `user_name`, `user_sex`, `user_birthday`, `user_id_card`, `user_phone`, `user_mail`, `user_photo`, `user_address`, `user_native_place`, `user_nation`, `user_marital_status`, `user_political_status`, `user_work_number`, `user_entry_date`, `user_probation_end_date`, `user_employment_type`, `user_education`, `user_status`, `tenant_id`, `create_org_id`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'ADMIN', '系统管理员', 'MALE', '1990-01-01', '110101199001010001', '13800138001', 'admin@ssitao.com', NULL, '北京市海淀区', '北京', 'HAN', 'MARRIED', 'MEMBER', 'EMP001', '2020-01-01', '2020-04-01', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('2', 'ZHANGSAN', '张三', 'MALE', '1992-05-15', '110101199205150011', '13800138002', 'zhangsan@ssitao.com', NULL, '北京市朝阳区', '山东', 'HAN', 'SINGLE', 'MEMBER', 'EMP002', '2021-03-15', '2021-06-15', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('3', 'LISI', '李四', 'MALE', '1993-08-20', '110101199308200021', '13800138003', 'lisi@ssitao.com', NULL, '北京市海淀区', '河北', 'HAN', 'SINGLE', 'MEMBER', 'EMP003', '2021-06-01', '2021-09-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('4', 'WANGWU', '王五', 'FEMALE', '1994-03-10', '110101199403100031', '13800138004', 'wangwu@ssitao.com', NULL, '北京市西城区', '北京', 'HAN', 'SINGLE', 'MEMBER', 'EMP004', '2022-01-10', '2022-04-10', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('5', 'ZHAOLIU', '赵六', 'MALE', '1995-12-25', '110101199512250041', '13800138005', 'zhaoliu@ssitao.com', NULL, '北京市东城区', '河南', 'HAN', 'SINGLE', 'MEMBER', 'EMP005', '2022-07-01', '2022-10-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0);

-- =============================================
-- 五、账户数据（密码均为 123456，使用BCrypt加密）
-- =============================================
INSERT INTO `iam_account` (`account_id`, `account_code`, `account_name`, `account_password`, `account_phone`, `account_mail`, `account_avatar`, `account_type`, `account_source`, `account_status`, `account_is_admin`, `account_last_login_time`, `account_last_login_ip`, `account_init_password`, `account_init_password_reset_time`, `tenant_id`, `create_org_id`, `create_org_name`, `create_user_id`, `create_user_name`, `create_time`, `modify_org_id`, `modify_org_name`, `modify_user_id`, `modify_user_name`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'admin', '系统管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138001', 'admin@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 1, NOW(), '127.0.0.1', 0, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('2', 'zhangsan', '张三', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138002', 'zhangsan@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('3', 'lisi', '李四', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138003', 'lisi@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('4', 'wangwu', '王五', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138004', 'wangwu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('5', 'zhaoliu', '赵六', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800138005', 'zhaoliu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0);

-- =============================================
-- 六、角色数据
-- =============================================
INSERT INTO `iam_role` (`role_id`, `role_code`, `role_name`, `role_type`, `role_level`, `role_desc`, `role_status`, `role_is_builtin`, `role_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'SUPER_ADMIN', '超级管理员', 'SYSTEM', 1, '系统超级管理员，拥有所有权限', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'ADMIN', '系统管理员', 'SYSTEM', 2, '系统管理员，拥有大部分权限', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEPT_ADMIN', '部门管理员', 'BUSINESS', 3, '部门管理员，管理本部门', 1, 0, 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'DEVELOPER', '开发人员', 'BUSINESS', 4, '开发人员角色', 1, 0, 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'TESTER', '测试人员', 'BUSINESS', 4, '测试人员角色', 1, 0, 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'OPERATOR', '运维人员', 'BUSINESS', 4, '运维人员角色', 1, 0, 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'USER', '普通用户', 'BUSINESS', 5, '普通用户角色', 1, 1, 7, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 七、权限数据
-- =============================================
INSERT INTO `iam_permission` (`permission_id`, `permission_code`, `permission_name`, `permission_type`, `permission_resource`, `permission_action`, `permission_desc`, `permission_status`, `permission_is_builtin`, `permission_sort`, `tenant_id`, `create_time`, `modify_time`, `is_deleted`) VALUES
-- 系统管理权限
('1', 'system:manage', '系统管理', 'MENU', '/system', 'VIEW', '系统管理模块', 1, 1, 1, '1', NOW(), NULL, 0),
('2', 'system:user:view', '用户查看', 'BUTTON', '/system/user', 'VIEW', '查看用户信息', 1, 1, 1, '1', NOW(), NULL, 0),
('3', 'system:user:add', '用户新增', 'BUTTON', '/system/user', 'ADD', '新增用户', 1, 1, 2, '1', NOW(), NULL, 0),
('4', 'system:user:edit', '用户编辑', 'BUTTON', '/system/user', 'EDIT', '编辑用户', 1, 1, 3, '1', NOW(), NULL, 0),
('5', 'system:user:delete', '用户删除', 'BUTTON', '/system/user', 'DELETE', '删除用户', 1, 1, 4, '1', NOW(), NULL, 0),
('6', 'system:role:view', '角色查看', 'BUTTON', '/system/role', 'VIEW', '查看角色', 1, 1, 5, '1', NOW(), NULL, 0),
('7', 'system:role:add', '角色新增', 'BUTTON', '/system/role', 'ADD', '新增角色', 1, 1, 6, '1', NOW(), NULL, 0),
('8', 'system:role:edit', '角色编辑', 'BUTTON', '/system/role', 'EDIT', '编辑角色', 1, 1, 7, '1', NOW(), NULL, 0),
('9', 'system:role:delete', '角色删除', 'BUTTON', '/system/role', 'DELETE', '删除角色', 1, 1, 8, '1', NOW(), NULL, 0),
-- 组织管理权限
('10', 'org:manage', '组织管理', 'MENU', '/org', 'VIEW', '组织管理模块', 1, 1, 2, '1', NOW(), NULL, 0),
('11', 'org:company:view', '公司查看', 'BUTTON', '/org/company', 'VIEW', '查看公司', 1, 1, 1, '1', NOW(), NULL, 0),
('12', 'org:company:add', '公司新增', 'BUTTON', '/org/company', 'ADD', '新增公司', 1, 1, 2, '1', NOW(), NULL, 0),
('13', 'org:dept:view', '部门查看', 'BUTTON', '/org/dept', 'VIEW', '查看部门', 1, 1, 3, '1', NOW(), NULL, 0),
('14', 'org:dept:add', '部门新增', 'BUTTON', '/org/dept', 'ADD', '新增部门', 1, 1, 4, '1', NOW(), NULL, 0),
-- 菜单管理权限
('15', 'menu:manage', '菜单管理', 'MENU', '/menu', 'VIEW', '菜单管理模块', 1, 1, 3, '1', NOW(), NULL, 0),
('16', 'menu:view', '菜单查看', 'BUTTON', '/menu', 'VIEW', '查看菜单', 1, 1, 1, '1', NOW(), NULL, 0),
('17', 'menu:add', '菜单新增', 'BUTTON', '/menu', 'ADD', '新增菜单', 1, 1, 2, '1', NOW(), NULL, 0),
-- 字典管理权限
('20', 'dict:manage', '字典管理', 'MENU', '/dict', 'VIEW', '字典管理模块', 1, 1, 4, '1', NOW(), NULL, 0),
('21', 'dict:view', '字典查看', 'BUTTON', '/dict', 'VIEW', '查看字典', 1, 1, 1, '1', NOW(), NULL, 0),
-- 日志管理权限
('30', 'log:manage', '日志管理', 'MENU', '/log', 'VIEW', '日志管理模块', 1, 1, 5, '1', NOW(), NULL, 0),
('31', 'log:login:view', '登录日志查看', 'BUTTON', '/log/login', 'VIEW', '查看登录日志', 1, 1, 1, '1', NOW(), NULL, 0),
('32', 'log:operate:view', '操作日志查看', 'BUTTON', '/log/operate', 'VIEW', '查看操作日志', 1, 1, 2, '1', NOW(), NULL, 0);

-- =============================================
-- 八、菜单数据
-- =============================================
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

-- =============================================
-- 九、账户角色关联数据
-- =============================================
INSERT INTO `iam_account_role` (`id`, `account_id`, `role_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, '1', NOW(), '1', 0),  -- admin -> 超级管理员
('2', '2', '4', 1, '1', NOW(), '1', 0),  -- zhangsan -> 开发人员
('3', '3', '4', 1, '1', NOW(), '1', 0),  -- lisi -> 开发人员
('4', '4', '5', 1, '1', NOW(), '1', 0),  -- wangwu -> 测试人员
('5', '5', '6', 1, '1', NOW(), '1', 0);  -- zhaoliu -> 运维人员

-- =============================================
-- 十、角色权限关联数据
-- =============================================
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

-- =============================================
-- 十一、账户部门关联数据
-- =============================================
INSERT INTO `iam_account_dept` (`id`, `account_id`, `dept_id`, `is_primary`, `is_leader`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
('1', '1', '1', 1, 1, '1', NOW(), '1', 0),  -- admin -> 集团总部(主部门，负责人)
('2', '2', '3', 1, 1, '1', NOW(), '1', 0),  -- zhangsan -> 软件开发部(主部门，负责人)
('3', '3', '3', 1, 0, '1', NOW(), '1', 0),  -- lisi -> 软件开发部(主部门)
('4', '4', '4', 1, 0, '1', NOW(), '1', 0),  -- wangwu -> 质量保障部(主部门)
('5', '5', '5', 1, 0, '1', NOW(), '1', 0);  -- zhaoliu -> 运维部(主部门)

-- =============================================
-- 十二、登录日志测试数据
-- =============================================
INSERT INTO `iam_login_log` (`log_id`, `account_id`, `account_code`, `account_name`, `login_type`, `login_status`, `login_fail_reason`, `login_ip`, `login_location`, `login_device`, `login_browser`, `login_os`, `login_time`, `logout_time`, `online_duration`, `tenant_id`) VALUES
('1', '1', 'admin', '系统管理员', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR), 3600, '1'),
('2', '1', 'admin', '系统管理员', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 1 HOUR), NULL, NULL, '1'),
('3', '2', 'zhangsan', '张三', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.100', '北京市海淀区', 'PC', 'Firefox 121.0', 'macOS 14', DATE_SUB(NOW(), INTERVAL 30 MINUTE), NULL, NULL, '1'),
('4', NULL, 'unknown', '未知用户', 'PASSWORD', 'FAIL', '用户名或密码错误', '192.168.1.200', '北京市朝阳区', 'PC', 'Edge 120.0', 'Windows 10', DATE_SUB(NOW(), INTERVAL 15 MINUTE), NULL, NULL, '1');

-- =============================================
-- 十三、操作日志测试数据
-- =============================================
INSERT INTO `iam_operate_log` (`log_id`, `operate_type`, `module_name`, `business_type`, `business_id`, `method_name`, `request_url`, `request_method`, `request_params`, `response_result`, `operate_status`, `error_msg`, `execute_duration`, `operator_id`, `operator_name`, `operator_dept`, `operate_ip`, `operate_location`, `operate_time`, `tenant_id`) VALUES
('1', 'LOGIN', '认证模块', '用户登录', '1', 'login', '/api/auth/login', 'POST', '{"username":"admin"}', '{"code":200,"message":"登录成功"}', 'SUCCESS', NULL, 150, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('2', 'CREATE', '用户管理', '新增用户', '2', 'createUser', '/api/system/user', 'POST', '{"username":"zhangsan","realName":"张三"}', '{"code":200,"message":"创建成功"}', 'SUCCESS', NULL, 80, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('3', 'UPDATE', '角色管理', '修改角色', '1', 'updateRole', '/api/system/role/1', 'PUT', '{"roleName":"超级管理员"}', '{"code":200,"message":"更新成功"}', 'SUCCESS', NULL, 60, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('4', 'DELETE', '部门管理', '删除部门', '10', 'deleteDept', '/api/org/dept/10', 'DELETE', '{}', '{"code":200,"message":"删除成功"}', 'SUCCESS', NULL, 45, '1', '系统管理员', '集团总部', '127.0.0.1', '本地', NOW(), '1');

-- =============================================
-- 十四、字典类型测试数据（core_dictionary）
-- =============================================
INSERT INTO `core_dictionary` (`dict_id`, `dict_code`, `dict_name`, `dict_type`, `dict_source`, `dict_sql`, `dict_api`, `dict_config`, `dict_status`, `dict_is_builtin`, `dict_sort`, `dict_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'user_status', '用户状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 1, '用户账户状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sex', '性别', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 2, '性别字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'yes_no', '是否', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 3, '是否字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'status', '状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 4, '通用状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'menu_type', '菜单类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 5, '菜单类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'account_type', '账户类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 6, '账户类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'permission_type', '权限类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 7, '权限类型字典', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 十五、字典数据测试数据（core_dictionary_item）
-- =============================================
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

SET FOREIGN_KEY_CHECKS = 1;
