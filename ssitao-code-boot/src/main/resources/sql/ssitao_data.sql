-- =============================================
-- SSITAO 低代码平台 Core 和 Meta 模块初始化数据
-- 数据库: MySQL 8.0
-- 作者: ssitao-code
-- 日期: 2026-03-07
-- 说明: 包含字典、系统配置，元数据表、字段、表单、列表等配置数据
-- 执行顺序: 第三位（在 ssitao_schema.sql 和 ssitao_data_iam.sql 之后）
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 数据清理（为支持重复执行，按外键依赖顺序删除）
-- =============================================
DELETE FROM meta_column WHERE 1=1;
DELETE FROM meta_form WHERE 1=1;
DELETE FROM meta_list WHERE 1=1;
DELETE FROM meta_table WHERE 1=1;

DELETE FROM core_dictionary_item WHERE 1=1;
DELETE FROM core_dictionary WHERE 1=1;
DELETE FROM core_config WHERE 1=1;

-- 重置自增ID
ALTER TABLE meta_column AUTO_INCREMENT = 1;
ALTER TABLE meta_form AUTO_INCREMENT = 1;
ALTER TABLE meta_list AUTO_INCREMENT = 1;
ALTER TABLE meta_table AUTO_INCREMENT = 1;
ALTER TABLE core_dictionary_item AUTO_INCREMENT = 1;
ALTER TABLE core_dictionary AUTO_INCREMENT = 1;
ALTER TABLE core_config AUTO_INCREMENT = 1;

-- =============================================
-- 一、字典类型数据
-- =============================================
INSERT INTO `core_dictionary` (`dict_id`, `dict_code`, `dict_name`, `dict_type`, `dict_source`, `dict_sql`, `dict_api`, `dict_config`, `dict_status`, `dict_is_builtin`, `dict_sort`, `dict_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 系统字典
('1', 'user_status', '用户状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 1, '用户账户状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sex', '性别', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 2, '性别字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'yes_no', '是否', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 3, '是否字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'status', '状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 4, '通用状态字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'menu_type', '菜单类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 5, '菜单类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'account_type', '账户类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 6, '账户类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'permission_type', '权限类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 7, '权限类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'role_type', '角色类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 8, '角色类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('9', 'post_type', '岗位类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 9, '岗位类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'company_type', '公司类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 10, '公司类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'dept_type', '部门类型', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 11, '部门类型字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'marital_status', '婚姻状况', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 12, '婚姻状况字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'political_status', '政治面貌', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 13, '政治面貌字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'education', '学历', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 14, '学历字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('15', 'employment_type', '用工性质', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 15, '用工性质字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('16', 'user_employee_status', '员工状态', 'SYSTEM', 'CUSTOM', NULL, NULL, NULL, 1, 1, 16, '员工在职状态', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 业务字典
('20', 'customer_level', '客户级别', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 1, '客户级别ABC分类', '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'customer_source', '客户来源', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 2, '客户来源渠道', '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'industry', '行业分类', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 3, '客户行业分类', '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'order_status', '订单状态', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 4, '订单状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'payment_status', '支付状态', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 5, '支付状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('25', 'invoice_type', '发票类型', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 6, '发票类型', '1', NOW(), '1', NULL, NULL, 0, 0),
('26', 'product_category', '产品分类', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 7, '产品分类', '1', NOW(), '1', NULL, NULL, 0, 0),
('27', 'warehouse', '仓库', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 8, '仓库字典', '1', NOW(), '1', NULL, NULL, 0, 0),
('28', 'unit', '计量单位', 'BUSINESS', 'CUSTOM', NULL, NULL, NULL, 1, 0, 9, '计量单位', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 二、字典数据
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
('10', '4', 'PENDING', '待处理', '2', NULL, 1, '/10', NULL, 'orange', 'warning', 1, 0, 3, '待处理', '1', NOW(), '1', NULL, NULL, 0),
-- 菜单类型
('11', '5', 'DIRECTORY', '目录', '1', NULL, 1, '/11', NULL, 'blue', 'primary', 1, 0, 1, '目录类型', '1', NOW(), '1', NULL, NULL, 0),
('12', '5', 'MENU', '菜单', '2', NULL, 1, '/12', NULL, 'green', 'success', 1, 0, 2, '菜单类型', '1', NOW(), '1', NULL, NULL, 0),
('13', '5', 'BUTTON', '按钮', '3', NULL, 1, '/13', NULL, 'orange', 'warning', 1, 0, 3, '按钮类型', '1', NOW(), '1', NULL, NULL, 0),
-- 账户类型
('14', '6', 'SYSTEM', '系统账户', '1', NULL, 1, '/14', NULL, 'blue', 'primary', 1, 1, 1, '系统账户', '1', NOW(), '1', NULL, NULL, 0),
('15', '6', 'THIRD', '第三方账户', '2', NULL, 1, '/15', NULL, 'green', 'success', 1, 0, 2, '第三方账户', '1', NOW(), '1', NULL, NULL, 0),
-- 权限类型
('16', '7', 'MENU', '菜单权限', '1', NULL, 1, '/16', NULL, 'blue', 'primary', 1, 0, 1, '菜单权限', '1', NOW(), '1', NULL, NULL, 0),
('17', '7', 'BUTTON', '按钮权限', '2', NULL, 1, '/17', NULL, 'green', 'success', 1, 0, 2, '按钮权限', '1', NOW(), '1', NULL, NULL, 0),
('18', '7', 'API', '接口权限', '3', NULL, 1, '/18', NULL, 'orange', 'warning', 1, 0, 3, '接口权限', '1', NOW(), '1', NULL, NULL, 0),
('19', '7', 'DATA', '数据权限', '4', NULL, 1, '/19', NULL, 'purple', 'info', 1, 0, 4, '数据权限', '1', NOW(), '1', NULL, NULL, 0),
-- 角色类型
('20', '8', 'SYSTEM', '系统角色', '1', NULL, 1, '/20', NULL, 'blue', 'primary', 1, 0, 1, '系统角色', '1', NOW(), '1', NULL, NULL, 0),
('21', '8', 'BUSINESS', '业务角色', '2', NULL, 1, '/21', NULL, 'green', 'success', 1, 0, 2, '业务角色', '1', NOW(), '1', NULL, NULL, 0),
-- 岗位类型
('22', '9', 'MANAGEMENT', '管理岗', '1', NULL, 1, '/22', NULL, 'gold', 'warning', 1, 0, 1, '管理岗位', '1', NOW(), '1', NULL, NULL, 0),
('23', '9', 'TECHNICAL', '技术岗', '2', NULL, 1, '/23', NULL, 'blue', 'primary', 1, 0, 2, '技术岗位', '1', NOW(), '1', NULL, NULL, 0),
('24', '9', 'BUSINESS', '业务岗', '3', NULL, 1, '/24', NULL, 'green', 'success', 1, 0, 3, '业务岗位', '1', NOW(), '1', NULL, NULL, 0),
('25', '9', 'FUNCTIONAL', '职能岗', '4', NULL, 1, '/25', NULL, 'purple', 'info', 1, 0, 4, '职能岗位', '1', NOW(), '1', NULL, NULL, 0),
-- 公司类型
('26', '10', 'ENTERPRISE', '企业', '1', NULL, 1, '/26', NULL, 'blue', 'primary', 1, 1, 1, '企业', '1', NOW(), '1', NULL, NULL, 0),
('27', '10', 'BRANCH', '分公司', '2', NULL, 1, '/27', NULL, 'green', 'success', 1, 0, 2, '分公司', '1', NOW(), '1', NULL, NULL, 0),
('28', '10', 'HOLDING', '控股公司', '3', NULL, 1, '/28', NULL, 'orange', 'warning', 1, 0, 3, '控股公司', '1', NOW(), '1', NULL, NULL, 0),
-- 部门类型
('29', '11', 'HEADQUARTERS', '总部', '1', NULL, 1, '/29', NULL, 'red', 'danger', 1, 0, 1, '总部', '1', NOW(), '1', NULL, NULL, 0),
('30', '11', 'DEPARTMENT', '部门', '2', NULL, 1, '/30', NULL, 'blue', 'primary', 1, 1, 2, '部门', '1', NOW(), '1', NULL, NULL, 0),
('31', '11', 'TEAM', '小组', '3', NULL, 1, '/31', NULL, 'green', 'success', 1, 0, 3, '小组', '1', NOW(), '1', NULL, NULL, 0),
-- 婚姻状况
('32', '12', 'SINGLE', '未婚', '1', NULL, 1, '/32', NULL, 'blue', 'primary', 1, 0, 1, '未婚', '1', NOW(), '1', NULL, NULL, 0),
('33', '12', 'MARRIED', '已婚', '2', NULL, 1, '/33', NULL, 'red', 'danger', 1, 0, 2, '已婚', '1', NOW(), '1', NULL, NULL, 0),
('34', '12', 'DIVORCED', '离异', '3', NULL, 1, '/34', NULL, 'orange', 'warning', 1, 0, 3, '离异', '1', NOW(), '1', NULL, NULL, 0),
-- 政治面貌
('35', '13', 'MEMBER', '党员', '1', NULL, 1, '/35', NULL, 'red', 'danger', 1, 0, 1, '党员', '1', NOW(), '1', NULL, NULL, 0),
('36', '13', 'LEAGUE', '团员', '2', NULL, 1, '/36', NULL, 'orange', 'warning', 1, 0, 2, '团员', '1', NOW(), '1', NULL, NULL, 0),
('37', '13', 'MASS', '群众', '3', NULL, 1, '/37', NULL, 'gray', 'default', 1, 1, 3, '群众', '1', NOW(), '1', NULL, NULL, 0),
-- 学历
('38', '14', 'DOCTOR', '博士', '1', NULL, 1, '/38', NULL, 'purple', 'info', 1, 0, 1, '博士', '1', NOW(), '1', NULL, NULL, 0),
('39', '14', 'MASTER', '硕士', '2', NULL, 1, '/39', NULL, 'blue', 'primary', 1, 0, 2, '硕士', '1', NOW(), '1', NULL, NULL, 0),
('40', '14', 'BACHELOR', '本科', '3', NULL, 1, '/40', NULL, 'green', 'success', 1, 0, 3, '本科', '1', NOW(), '1', NULL, NULL, 0),
('41', '14', 'ASSOCIATE', '大专', '4', NULL, 1, '/41', NULL, 'orange', 'warning', 1, 0, 4, '大专', '1', NOW(), '1', NULL, NULL, 0),
('42', '14', 'HIGH_SCHOOL', '高中', '5', NULL, 1, '/42', NULL, 'gray', 'default', 1, 0, 5, '高中', '1', NOW(), '1', NULL, NULL, 0),
-- 用工性质
('43', '15', 'FULL_TIME', '全职', '1', NULL, 1, '/43', NULL, 'blue', 'primary', 1, 1, 1, '全职', '1', NOW(), '1', NULL, NULL, 0),
('44', '15', 'PART_TIME', '兼职', '2', NULL, 1, '/44', NULL, 'green', 'success', 1, 0, 2, '兼职', '1', NOW(), '1', NULL, NULL, 0),
('45', '15', 'INTERNSHIP', '实习', '3', NULL, 1, '/45', NULL, 'orange', 'warning', 1, 0, 3, '实习', '1', NOW(), '1', NULL, NULL, 0),
('46', '15', 'OUTSOURCING', '外包', '4', NULL, 1, '/46', NULL, 'purple', 'info', 1, 0, 4, '外包', '1', NOW(), '1', NULL, NULL, 0),
-- 员工状态
('47', '16', 'ON_JOB', '在职', '1', NULL, 1, '/47', NULL, 'green', 'success', 1, 1, 1, '在职', '1', NOW(), '1', NULL, NULL, 0),
('48', '16', 'RESIGN', '离职', '2', NULL, 1, '/48', NULL, 'red', 'danger', 1, 0, 2, '离职', '1', NOW(), '1', NULL, NULL, 0),
('49', '16', 'PROBATION', '试用期', '3', NULL, 1, '/49', NULL, 'orange', 'warning', 1, 0, 3, '试用期', '1', NOW(), '1', NULL, NULL, 0),
-- 客户级别
('50', '20', 'A', 'A级客户', '1', NULL, 1, '/50', NULL, 'red', 'danger', 1, 0, 1, '重要客户', '1', NOW(), '1', NULL, NULL, 0),
('51', '20', 'B', 'B级客户', '2', NULL, 1, '/51', NULL, 'orange', 'warning', 1, 0, 2, '普通客户', '1', NOW(), '1', NULL, NULL, 0),
('52', '20', 'C', 'C级客户', '3', NULL, 1, '/52', NULL, 'green', 'success', 1, 0, 3, '潜在客户', '1', NOW(), '1', NULL, NULL, 0),
-- 客户来源
('53', '21', 'RECOMMEND', '推荐', '1', NULL, 1, '/53', NULL, 'blue', 'primary', 1, 0, 1, '客户推荐', '1', NOW(), '1', NULL, NULL, 0),
('54', '21', 'ONLINE', '线上推广', '2', NULL, 1, '/54', NULL, 'green', 'success', 1, 0, 2, '线上推广', '1', NOW(), '1', NULL, NULL, 0),
('55', '21', 'EXHIBITION', '展会', '3', NULL, 1, '/55', NULL, 'orange', 'warning', 1, 0, 3, '参加展会', '1', NOW(), '1', NULL, NULL, 0),
('56', '21', 'COLD_CALL', '电话营销', '4', NULL, 1, '/56', NULL, 'purple', 'info', 1, 0, 4, '电话营销', '1', NOW(), '1', NULL, NULL, 0),
('57', '21', 'OTHER', '其他', '5', NULL, 1, '/57', NULL, 'gray', 'default', 1, 0, 5, '其他渠道', '1', NOW(), '1', NULL, NULL, 0),
-- 行业分类
('58', '22', 'IT', '互联网/IT', '1', NULL, 1, '/58', NULL, 'blue', 'primary', 1, 0, 1, '互联网IT行业', '1', NOW(), '1', NULL, NULL, 0),
('59', '22', 'FINANCE', '金融', '2', NULL, 1, '/59', NULL, 'green', 'success', 1, 0, 2, '金融行业', '1', NOW(), '1', NULL, NULL, 0),
('60', '22', 'MANUFACTURING', '制造业', '3', NULL, 1, '/60', NULL, 'orange', 'warning', 1, 0, 3, '制造业', '1', NOW(), '1', NULL, NULL, 0),
('61', '22', 'RETAIL', '零售', '4', NULL, 1, '/61', NULL, 'purple', 'info', 1, 0, 4, '零售行业', '1', NOW(), '1', NULL, NULL, 0),
('62', '22', 'EDUCATION', '教育', '5', NULL, 1, '/62', NULL, 'red', 'danger', 1, 0, 5, '教育行业', '1', NOW(), '1', NULL, NULL, 0),
-- 订单状态
('63', '23', 'PENDING', '待处理', '1', NULL, 1, '/63', NULL, 'orange', 'warning', 1, 0, 1, '待处理', '1', NOW(), '1', NULL, NULL, 0),
('64', '23', 'CONFIRMED', '已确认', '2', NULL, 1, '/64', NULL, 'blue', 'primary', 1, 0, 2, '已确认', '1', NOW(), '1', NULL, NULL, 0),
('65', '23', 'PROCESSING', '处理中', '3', NULL, 1, '/65', NULL, 'cyan', 'processing', 1, 0, 3, '处理中', '1', NOW(), '1', NULL, NULL, 0),
('66', '23', 'SHIPPED', '已发货', '4', NULL, 1, '/66', NULL, 'green', 'success', 1, 0, 4, '已发货', '1', NOW(), '1', NULL, NULL, 0),
('67', '23', 'COMPLETED', '已完成', '5', NULL, 1, '/67', NULL, 'green', 'success', 1, 0, 5, '已完成', '1', NOW(), '1', NULL, NULL, 0),
('68', '23', 'CANCELLED', '已取消', '6', NULL, 1, '/68', NULL, 'red', 'danger', 1, 0, 6, '已取消', '1', NOW(), '1', NULL, NULL, 0),
-- 支付状态
('69', '24', 'UNPAID', '未支付', '1', NULL, 1, '/69', NULL, 'red', 'danger', 1, 0, 1, '未支付', '1', NOW(), '1', NULL, NULL, 0),
('70', '24', 'PAID', '已支付', '2', NULL, 1, '/70', NULL, 'green', 'success', 1, 0, 2, '已支付', '1', NOW(), '1', NULL, NULL, 0),
('71', '24', 'REFUNDING', '退款中', '3', NULL, 1, '/71', NULL, 'orange', 'warning', 1, 0, 3, '退款中', '1', NOW(), '1', NULL, NULL, 0),
('72', '24', 'REFUNDED', '已退款', '4', NULL, 1, '/72', NULL, 'gray', 'default', 1, 0, 4, '已退款', '1', NOW(), '1', NULL, NULL, 0),
-- 发票类型
('73', '25', 'VAT', '增值税专用发票', '1', NULL, 1, '/73', NULL, 'blue', 'primary', 1, 0, 1, '增值税专用发票', '1', NOW(), '1', NULL, NULL, 0),
('74', '25', 'NORMAL', '普通发票', '2', NULL, 1, '/74', NULL, 'green', 'success', 1, 0, 2, '普通发票', '1', NOW(), '1', NULL, NULL, 0),
('75', '25', 'ELECTRON', '电子发票', '3', NULL, 1, '/75', NULL, 'cyan', 'processing', 1, 0, 3, '电子发票', '1', NOW(), '1', NULL, NULL, 0),
-- 计量单位
('80', '28', 'PIECE', '个', '1', NULL, 1, '/80', NULL, 'blue', 'primary', 1, 1, 1, '个', '1', NOW(), '1', NULL, NULL, 0),
('81', '28', 'BOX', '箱', '2', NULL, 1, '/81', NULL, 'green', 'success', 1, 0, 2, '箱', '1', NOW(), '1', NULL, NULL, 0),
('82', '28', 'KG', '千克', '3', NULL, 1, '/82', NULL, 'orange', 'warning', 1, 0, 3, '千克', '1', NOW(), '1', NULL, NULL, 0),
('83', '28', 'TON', '吨', '4', NULL, 1, '/83', NULL, 'purple', 'info', 1, 0, 4, '吨', '1', NOW(), '1', NULL, NULL, 0),
('84', '28', 'SET', '套', '5', NULL, 1, '/84', NULL, 'red', 'danger', 1, 0, 5, '套', '1', NOW(), '1', NULL, NULL, 0);

-- =============================================
-- 三、系统配置数据
-- =============================================
INSERT INTO `core_config` (`config_id`, `config_code`, `config_name`, `config_value`, `config_type`, `config_category`, `config_status`, `config_is_builtin`, `config_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 系统配置
('1', 'system.name', '系统名称', '思涛低代码平台', 'STRING', 'system', 1, 1, '系统名称配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'system.logo', '系统Logo', '/logo.png', 'STRING', 'system', 1, 1, '系统Logo路径', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'system.version', '系统版本', '2.0.0', 'STRING', 'system', 1, 1, '系统版本号', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 安全配置
('10', 'security.password.salt', '密码盐值', 'ssitao2026', 'STRING', 'security', 1, 1, '密码加密盐值', '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'security.password.minLength', '密码最小长度', '6', 'NUMBER', 'security', 1, 1, '密码最小长度要求', '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'security.session.timeout', '会话超时时间', '7200', 'NUMBER', 'security', 1, 1, '会话超时时间(秒)', '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'security.captcha.enabled', '验证码开关', 'true', 'BOOLEAN', 'security', 1, 1, '是否启用验证码', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 文件配置
('20', 'file.upload.path', '文件上传路径', '/uploads', 'STRING', 'file', 1, 1, '文件上传根目录', '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'file.upload.maxSize', '文件最大大小', '10485760', 'NUMBER', 'file', 1, 1, '文件上传最大大小(字节)', '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'file.allowed.types', '允许的文件类型', 'jpg,png,gif,xls,xlsx,doc,docx,pdf', 'STRING', 'file', 1, 1, '允许上传的文件类型', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 邮件配置
('30', 'mail.enabled', '邮件服务开关', 'false', 'BOOLEAN', 'mail', 1, 1, '是否启用邮件服务', '1', NOW(), '1', NULL, NULL, 0, 0),
('31', 'mail.host', '邮件服务器', 'smtp.example.com', 'STRING', 'mail', 1, 0, 'SMTP服务器地址', '1', NOW(), '1', NULL, NULL, 0, 0),
('32', 'mail.port', '邮件服务器端口', '465', 'NUMBER', 'mail', 1, 0, 'SMTP服务器端口', '1', NOW(), '1', NULL, NULL, 0, 0),
('33', 'mail.username', '邮件用户名', 'noreply@ssitao.com', 'STRING', 'mail', 1, 0, '邮件发送用户名', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 低代码配置
('40', 'codegen.package', '代码生成包名', 'com.ssitao.code', 'STRING', 'codegen', 1, 1, '代码生成默认包名', '1', NOW(), '1', NULL, NULL, 0, 0),
('41', 'codegen.author', '代码生成作者', 'ssitao-code', 'STRING', 'codegen', 1, 1, '代码生成作者', '1', NOW(), '1', NULL, NULL, 0, 0),
('42', 'codegen.backend', '后端代码路径', '/gen/backend', 'STRING', 'codegen', 1, 1, '后端代码生成路径', '1', NOW(), '1', NULL, NULL, 0, 0),
('43', 'codegen.frontend', '前端代码路径', '/gen/frontend', 'STRING', 'codegen', 1, 1, '前端代码生成路径', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 四、元数据表配置
-- =============================================
INSERT INTO `meta_table` (`table_id`, `table_name`, `table_desc`, `table_type`, `package_name`, `module_name`, `class_name`, `class_desc`, `entity_name`, `author`, `enabled`, `generated`, `gen_path`, `remark`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'sys_user', '系统用户', 1, 'com.ssitao.code', 'system', 'SysUser', '系统用户', 'SysUser', 'ssitao-code', 1, 1, '/gen/java/com/ssitao/code/system', '系统用户表', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'sys_role', '系统角色', 1, 'com.ssitao.code', 'system', 'SysRole', '系统角色', 'SysRole', 'ssitao-code', 1, 1, '/gen/java/com/ssitao/code/system', '系统角色表', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'customer', '客户信息', 1, 'com.ssitao.code', 'crm', 'Customer', '客户信息', 'Customer', 'ssitao-code', 1, 0, NULL, '客户信息管理', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'product', '产品信息', 1, 'com.ssitao.code', 'product', 'Product', '产品信息', 'Product', 'ssitao-code', 1, 0, NULL, '产品信息管理', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'order', '订单信息', 1, 'com.ssitao.code', 'order', 'Order', '订单信息', 'Order', 'ssitao-code', 1, 0, NULL, '订单信息管理', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'contract', '合同信息', 1, 'com.ssitao.code', 'contract', 'Contract', '合同信息', 'Contract', 'ssitao-code', 1, 0, NULL, '合同信息管理', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 五、元数据字段定义
-- =============================================
INSERT INTO `meta_column` (`column_id`, `table_id`, `column_name`, `column_desc`, `column_type`, `java_type`, `java_field`, `is_pk`, `is_increment`, `is_required`, `is_query`, `query_type`, `is_display`, `is_list`, `is_form`, `form_type`, `dict_type`, `default_value`, `column_sort`, `remark`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 客户信息表字段
('1', '3', 'id', '客户ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '客户ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', '3', 'customer_name', '客户名称', 'varchar(128)', 'String', 'customerName', 0, 0, 1, 1, 2, 1, 1, 1, 1, NULL, NULL, 1, '客户名称', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', '3', 'customer_code', '客户编码', 'varchar(64)', 'String', 'customerCode', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '客户编码', '1', NOW(), '1', NULL, NULL, 0, 0),
('4', '3', 'customer_level', '客户级别', 'varchar(20)', 'String', 'customerLevel', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'customer_level', NULL, 3, '客户级别ABC', '1', NOW(), '1', NULL, NULL, 0, 0),
('5', '3', 'industry', '所属行业', 'varchar(64)', 'String', 'industry', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'industry', NULL, 4, '所属行业', '1', NOW(), '1', NULL, NULL, 0, 0),
('6', '3', 'contact', '联系人', 'varchar(64)', 'String', 'contact', 0, 0, 0, 0, 1, 1, 1, 1, 1, NULL, NULL, 5, '联系人', '1', NOW(), '1', NULL, NULL, 0, 0),
('7', '3', 'phone', '联系电话', 'varchar(20)', 'String', 'phone', 0, 0, 0, 1, 1, 1, 1, 1, 1, NULL, NULL, 6, '联系电话', '1', NOW(), '1', NULL, NULL, 0, 0),
('8', '3', 'email', '邮箱', 'varchar(128)', 'String', 'email', 0, 0, 0, 0, 1, 1, 1, 1, 1, NULL, NULL, 7, '邮箱地址', '1', NOW(), '1', NULL, NULL, 0, 0),
('9', '3', 'address', '地址', 'varchar(256)', 'String', 'address', 0, 0, 0, 0, 1, 0, 0, 1, 2, NULL, NULL, 8, '详细地址', '1', NOW(), '1', NULL, NULL, 0, 0),
('10', '3', 'source', '客户来源', 'varchar(32)', 'String', 'source', 0, 0, 0, 0, 1, 1, 1, 1, 3, 'customer_source', NULL, 9, '客户来源渠道', '1', NOW(), '1', NULL, NULL, 0, 0),
('11', '3', 'status', '状态', 'tinyint', 'Integer', 'status', 0, 0, 0, 1, 1, 1, 1, 1, 4, 'yes_no', '1', 10, '客户状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('12', '3', 'create_time', '创建时间', 'datetime', 'LocalDateTime', 'createTime', 0, 0, 0, 0, 1, 1, 1, 0, 6, NULL, NULL, 11, '创建时间', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 产品信息表字段
('20', '4', 'id', '产品ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '产品ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('21', '4', 'product_name', '产品名称', 'varchar(128)', 'String', 'productName', 0, 0, 1, 1, 2, 1, 1, 1, 1, NULL, NULL, 1, '产品名称', '1', NOW(), '1', NULL, NULL, 0, 0),
('22', '4', 'product_code', '产品编码', 'varchar(64)', 'String', 'productCode', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '产品编码', '1', NOW(), '1', NULL, NULL, 0, 0),
('23', '4', 'category', '产品分类', 'varchar(64)', 'String', 'category', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'product_category', NULL, 3, '产品分类', '1', NOW(), '1', NULL, NULL, 0, 0),
('24', '4', 'price', '价格', 'decimal(10,2)', 'BigDecimal', 'price', 0, 0, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 4, '产品单价', '1', NOW(), '1', NULL, NULL, 0, 0),
('25', '4', 'unit', '单位', 'varchar(20)', 'String', 'unit', 0, 0, 0, 0, 1, 1, 1, 1, 3, 'unit', NULL, 5, '计量单位', '1', NOW(), '1', NULL, NULL, 0, 0),
('26', '4', 'stock', '库存数量', 'int', 'Integer', 'stock', 0, 0, 0, 0, 1, 1, 1, 1, 1, NULL, '0', 6, '库存数量', '1', NOW(), '1', NULL, NULL, 0, 0),
('27', '4', 'description', '产品描述', 'varchar(512)', 'String', 'description', 0, 0, 0, 0, 1, 0, 0, 1, 2, NULL, NULL, 7, '产品详细描述', '1', NOW(), '1', NULL, NULL, 0, 0),
-- 订单信息表字段
('30', '5', 'id', '订单ID', 'bigint', 'Long', 'id', 1, 1, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 0, '订单ID', '1', NOW(), '1', NULL, NULL, 0, 0),
('31', '5', 'order_no', '订单编号', 'varchar(64)', 'String', 'orderNo', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 1, '订单编号', '1', NOW(), '1', NULL, NULL, 0, 0),
('32', '5', 'customer_id', '客户ID', 'bigint', 'Long', 'customerId', 0, 0, 1, 1, 1, 1, 1, 1, 1, NULL, NULL, 2, '关联客户', '1', NOW(), '1', NULL, NULL, 0, 0),
('33', '5', 'order_status', '订单状态', 'varchar(20)', 'String', 'orderStatus', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'order_status', NULL, 3, '订单状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('34', '5', 'payment_status', '支付状态', 'varchar(20)', 'String', 'paymentStatus', 0, 0, 0, 1, 1, 1, 1, 1, 3, 'payment_status', NULL, 4, '支付状态', '1', NOW(), '1', NULL, NULL, 0, 0),
('35', '5', 'total_amount', '订单金额', 'decimal(12,2)', 'BigDecimal', 'totalAmount', 0, 0, 1, 0, 1, 1, 1, 1, 1, NULL, NULL, 5, '订单总金额', '1', NOW(), '1', NULL, NULL, 0, 0),
('36', '5', 'order_date', '下单日期', 'date', 'LocalDate', 'orderDate', 0, 0, 0, 1, 3, 1, 1, 1, 6, NULL, NULL, 6, '下单日期', '1', NOW(), '1', NULL, NULL, 0, 0),
('37', '5', 'delivery_date', '交付日期', 'date', 'LocalDate', 'deliveryDate', 0, 0, 0, 0, 1, 1, 1, 1, 6, NULL, NULL, 7, '预计交付日期', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 六、元数据表单配置
-- =============================================
INSERT INTO `meta_form` (`form_id`, `form_code`, `form_name`, `table_id`, `form_type`, `form_layout`, `form_config`, `form_status`, `form_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'customer_form', '客户信息表单', '3', 'NORMAL', 'single', '{"columns":1,"labelWidth":100}', 1, '客户信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'product_form', '产品信息表单', '4', 'NORMAL', 'double', '{"columns":2,"labelWidth":80}', 1, '产品信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'order_form', '订单信息表单', '5', 'NORMAL', 'single', '{"columns":1,"labelWidth":100}', 1, '订单信息表单配置', '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 七、元数据列表配置
-- =============================================
INSERT INTO `meta_list` (`list_id`, `list_code`, `list_name`, `table_id`, `list_type`, `list_config`, `list_status`, `list_desc`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'customer_list', '客户列表', '3', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '客户列表配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'product_list', '产品列表', '4', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '产品列表配置', '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'order_list', '订单列表', '5', 'TABLE', '{"showCheckbox":true,"showRowNumber":true,"pageSize":10,"showPagination":true}', 1, '订单列表配置', '1', NOW(), '1', NULL, NULL, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
