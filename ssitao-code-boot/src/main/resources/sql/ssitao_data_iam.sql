-- =============================================
-- SSITAO 低代码平台 IAM 模块初始化数据
-- 数据库: MySQL 8.0
-- 作者: ssitao-code
-- 日期: 2026-03-07
-- 说明: 包含公司、部门、岗位、用户、账户、角色、权限、菜单等初始化数据
-- 执行顺序: 第二位（在 ssitao_schema.sql 之后）
-- =============================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================
-- 数据清理（为支持重复执行，按外键依赖顺序删除）
-- =============================================
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

-- =============================================
-- 一、公司数据 - 思涛科技集团
-- =============================================
INSERT INTO `iam_company` (`company_id`, `company_code`, `company_name`, `company_short_name`, `company_type`, `company_level`, `company_parent_id`, `company_address`, `company_phone`, `company_mail`, `company_website`, `company_legal_rep`, `company_registration_no`, `company_tax_no`, `company_status`, `company_desc`, `company_sort`, `company_tree_path`, `company_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
('1', 'HQ', '思涛科技集团', '思涛科技', 'ENTERPRISE', 1, NULL, '北京市海淀区中关村大街1号', '010-88888888', 'contact@ssitao.com', 'https://www.ssitao.com', '李思涛', '91110108MA0198XXXX', '91110108MA0198XXXX', 1, '思涛科技集团总部，专注于企业级软件开发', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'BJ', '北京分公司', '北京思涛', 'BRANCH', 2, '1', '北京市朝阳区建国路88号SOHO现代城', '010-66666666', 'bj@ssitao.com', 'https://bj.ssitao.com', '王建国', '110105MA0198YYYY', '110105MA0198YYYY', 1, '北京分公司，负责北方区域业务', 2, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'SH', '上海分公司', '上海思涛', 'BRANCH', 2, '1', '上海市浦东新区陆家嘴金融中心A座', '021-55555555', 'sh@ssitao.com', 'https://sh.ssitao.com', '张海', '310115MA0198ZZZZ', '310115MA0198ZZZZ', 1, '上海分公司，负责华东区域业务', 3, '/1/3', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'SZ', '深圳分公司', '深圳思涛', 'BRANCH', 2, '1', '深圳市南山区科技园南区', '0755-88888888', 'sz@ssitao.com', 'https://sz.ssitao.com', '陈深圳', '440305MA0198AAAA', '440305MA0198AAAA', 1, '深圳分公司，负责华南区域业务', 4, '/1/4', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'CD', '成都分公司', '成都思涛', 'BRANCH', 2, '1', '成都市高新区天府软件园C区', '028-66666666', 'cd@ssitao.com', 'https://cd.ssitao.com', '刘成都', '510107MA0198BBBB', '510107MA0198BBBB', 1, '成都分公司，负责西南区域业务', 5, '/1/5', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 二、部门数据
-- =============================================
INSERT INTO `iam_department` (`dept_id`, `dept_code`, `dept_name`, `dept_type`, `dept_parent_id`, `dept_company_id`, `dept_leader_id`, `dept_phone`, `dept_address`, `dept_status`, `dept_desc`, `dept_sort`, `dept_tree_path`, `dept_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 集团总部
('1', 'ROOT', '集团总部', 'HEADQUARTERS', NULL, '1', '1', '010-88888881', '总部大楼1层', 1, '集团总部部门', 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'TECH', '技术研发中心', 'DEPARTMENT', '1', '1', '2', '010-88888882', '总部大楼5层', 1, '负责公司技术研发和产品创新', 1, '/1/2', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'DEV_TEAM', '软件开发部', 'DEPARTMENT', '2', '1', '3', '010-88888883', '总部大楼5层A区', 1, '负责各业务线软件开发', 1, '/1/2/3', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'FRONTEND', '前端开发组', 'TEAM', '3', '1', NULL, '010-88888883', '总部大楼5层A1区', 1, '负责前端开发', 1, '/1/2/3/4', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'BACKEND', '后端开发组', 'TEAM', '3', '1', NULL, '010-88888884', '总部大楼5层A2区', 1, '负责后端开发', 2, '/1/2/3/5', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'QA', '质量保障部', 'DEPARTMENT', '2', '1', '4', '010-88888885', '总部大楼5层B区', 1, '负责软件测试和质量保证', 2, '/1/2/6', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'OPS', '运维部', 'DEPARTMENT', '2', '1', '5', '010-88888886', '总部大楼地下一层', 1, '负责系统运维和DevOps', 3, '/1/2/7', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'SECURITY', '安全运维组', 'TEAM', '7', '1', NULL, '010-88888887', '总部大楼地下一层A区', 1, '负责信息安全', 1, '/1/2/7/8', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 产品中心
('9', 'PRODUCT', '产品中心', 'DEPARTMENT', '1', '1', '6', '010-88888888', '总部大楼4层', 1, '负责产品规划与设计', 2, '/1/9', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'PM', '产品经理部', 'DEPARTMENT', '9', '1', NULL, '010-88888889', '总部大楼4层A区', 1, '负责产品需求分析', 1, '/1/9/10', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'UX', '用户体验部', 'DEPARTMENT', '9', '1', NULL, '010-88888890', '总部大楼4层B区', 1, '负责UI/UX设计', 2, '/1/9/11', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 销售中心
('12', 'SALES', '销售中心', 'DEPARTMENT', '1', '1', '7', '010-88888891', '总部大楼3层', 1, '负责市场销售和客户拓展', 3, '/1/12', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'SALES_BJ', '北方销售部', 'DEPARTMENT', '12', '2', NULL, '010-66666661', '北京分公司3层', 1, '负责北方区域销售', 1, '/1/12/13', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'SALES_SH', '华东销售部', 'DEPARTMENT', '12', '3', NULL, '021-55555551', '上海分公司3层', 1, '负责华东区域销售', 2, '/1/12/14', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 人力资源
('15', 'HR', '人力资源部', 'DEPARTMENT', '1', '1', '8', '010-88888892', '总部大楼2层', 1, '负责人力资源管理', 4, '/1/15', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('16', 'HR_RECRUIT', '招聘培训部', 'DEPARTMENT', '15', '1', NULL, '010-88888893', '总部大楼2层A区', 1, '负责招聘和培训', 1, '/1/15/16', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('17', 'HR_SALARY', '薪酬福利部', 'DEPARTMENT', '15', '1', NULL, '010-88888894', '总部大楼2层B区', 1, '负责薪酬和福利', 2, '/1/15/17', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 财务部
('18', 'FIN', '财务部', 'DEPARTMENT', '1', '1', '9', '010-88888895', '总部大楼2层', 1, '负责财务管理', 5, '/1/18', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 行政部
('19', 'ADMIN', '行政部', 'DEPARTMENT', '1', '1', NULL, '010-88888896', '总部大楼1层', 1, '负责行政后勤', 6, '/1/19', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 三、岗位数据
-- =============================================
INSERT INTO `iam_post` (`post_id`, `post_code`, `post_name`, `post_level`, `post_type`, `post_status`, `post_desc`, `post_sort`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 管理岗
('1', 'CEO', '首席执行官', 1, 'MANAGEMENT', 1, '公司最高管理者，负责公司整体战略和运营', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'CTO', '首席技术官', 2, 'MANAGEMENT', 1, '技术负责人，负责技术研发方向', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'CFO', '首席财务官', 2, 'MANAGEMENT', 1, '财务负责人，负责公司财务运作', 3, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'VP', '副总裁', 3, 'MANAGEMENT', 1, '分管副总裁', 4, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'DIRECTOR', '总监', 4, 'MANAGEMENT', 1, '部门总监', 5, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'MGR', '部门经理', 5, 'MANAGEMENT', 1, '部门经理', 6, '1', NOW(), '1', NULL, NULL, 0, 0),
('7', 'TL', '技术经理', 5, 'MANAGEMENT', 1, '技术团队管理', 7, '1', NOW(), '1', NULL, NULL, 0, 0),
('8', 'PM', '项目经理', 5, 'MANAGEMENT', 1, '项目管理', 8, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 技术岗
('9', 'ARCHITECT', '架构师', 5, 'TECHNICAL', 1, '系统架构设计', 9, '1', NOW(), '1', NULL, NULL, 0, 0),
('10', 'SE', '高级工程师', 6, 'TECHNICAL', 1, '高级技术专家', 10, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'SENIOR_DEV', '开发工程师', 7, 'TECHNICAL', 1, '开发工程师', 11, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'DEV', '初级工程师', 8, 'TECHNICAL', 1, '初级开发人员', 12, '1', NOW(), '1', NULL, NULL, 0, 0),
('13', 'QA_LEAD', '测试主管', 5, 'TECHNICAL', 1, '测试团队管理', 13, '1', NOW(), '1', NULL, NULL, 0, 0),
('14', 'QA', '测试工程师', 7, 'TECHNICAL', 1, '软件测试', 14, '1', NOW(), '1', NULL, NULL, 0, 0),
('15', 'DBA', '数据库管理员', 6, 'TECHNICAL', 1, '数据库管理', 15, '1', NOW(), '1', NULL, NULL, 0, 0),
('16', 'DEVOPS', '运维工程师', 7, 'TECHNICAL', 1, '系统运维', 16, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 产品岗
('17', 'PRODUCT_DIRECTOR', '产品总监', 4, 'BUSINESS', 1, '产品规划', 17, '1', NOW(), '1', NULL, NULL, 0, 0),
('18', 'PRODUCT_MGR', '产品经理', 6, 'BUSINESS', 1, '产品需求管理', 18, '1', NOW(), '1', NULL, NULL, 0, 0),
('19', 'PRODUCT_ASSIST', '产品助理', 8, 'BUSINESS', 1, '产品辅助工作', 19, '1', NOW(), '1', NULL, NULL, 0, 0),
('20', 'UI_LEAD', 'UI主管', 5, 'BUSINESS', 1, 'UI设计管理', 20, '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'UI', 'UI设计师', 7, 'BUSINESS', 1, '界面设计', 21, '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'UE', 'UE设计师', 7, 'BUSINESS', 1, '用户体验设计', 22, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 销售岗
('23', 'SALES_DIRECTOR', '销售总监', 4, 'BUSINESS', 1, '销售管理', 23, '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'SALES_MGR', '销售经理', 6, 'BUSINESS', 1, '销售团队管理', 24, '1', NOW(), '1', NULL, NULL, 0, 0),
('25', 'SALES', '销售代表', 8, 'BUSINESS', 1, '客户销售', 25, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 职能岗
('26', 'HR_MGR', '人事经理', 5, 'FUNCTIONAL', 1, '人力资源管理', 26, '1', NOW(), '1', NULL, NULL, 0, 0),
('27', 'HR', '人事专员', 7, 'FUNCTIONAL', 1, '人事工作', 27, '1', NOW(), '1', NULL, NULL, 0, 0),
('28', 'FIN_MGR', '财务经理', 5, 'FUNCTIONAL', 1, '财务管理', 28, '1', NOW(), '1', NULL, NULL, 0, 0),
('29', 'FIN', '会计', 7, 'FUNCTIONAL', 1, '会计工作', 29, '1', NOW(), '1', NULL, NULL, 0, 0),
('30', 'ADMIN_MGR', '行政经理', 5, 'FUNCTIONAL', 1, '行政管理', 30, '1', NOW(), '1', NULL, NULL, 0, 0),
('31', 'ADMIN', '行政专员', 7, 'FUNCTIONAL', 1, '行政工作', 31, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 四、用户数据 - 员工信息
-- =============================================
INSERT INTO `iam_user` (`user_id`, `user_code`, `user_name`, `user_sex`, `user_birthday`, `user_id_card`, `user_phone`, `user_mail`, `user_address`, `user_native_place`, `user_nation`, `user_marital_status`, `user_political_status`, `user_work_number`, `user_entry_date`, `user_probation_end_date`, `user_employment_type`, `user_education`, `user_status`, `tenant_id`, `create_org_id`, `create_user_id`, `create_time`, `modify_user_id`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'ADMIN', '李思涛', 'MALE', '1985-03-15', '110108198503150011', '13800000001', 'lisi@ssitao.com', '北京市海淀区知春路', '北京', '汉', 'MARRIED', 'MEMBER', 'EMP001', '2015-01-01', '2015-04-01', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('2', 'ZHANGWEI', '张伟', 'MALE', '1988-07-22', '110108198807220012', '13800000002', 'zhangwei@ssitao.com', '北京市朝阳区望京', '山东', '汉', 'MARRIED', 'MEMBER', 'EMP002', '2016-03-15', '2016-06-15', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('3', 'LIHUA', '李华', 'MALE', '1990-05-10', '110108199005100013', '13800000003', 'lihua@ssitao.com', '北京市海淀区上地', '河北', '汉', 'SINGLE', 'LEAGUE', 'EMP003', '2017-06-01', '2017-09-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('4', 'WANGMEI', '王梅', 'FEMALE', '1992-11-25', '110108199211250014', '13800000004', 'wangmei@ssitao.com', '北京市西城区金融街', '北京', '汉', 'SINGLE', 'MEMBER', 'EMP004', '2018-01-10', '2018-04-10', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('5', 'ZHAOQiang', '赵强', 'MALE', '1991-08-08', '110108199108080015', '13800000005', 'zhaoqiang@ssitao.com', '北京市东城区王府井', '河南', '汉', 'MARRIED', 'MEMBER', 'EMP005', '2018-07-01', '2018-10-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('6', 'SUNLI', '孙莉', 'FEMALE', '1993-02-14', '110108199302140016', '13800000006', 'sunli@ssitao.com', '北京市海淀区中关村', '山西', '汉', 'SINGLE', 'MEMBER', 'EMP006', '2019-03-01', '2019-06-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('7', 'ZHOUMIN', '周敏', 'MALE', '1989-12-01', '110108198912010017', '13800000007', 'zhoumin@ssitao.com', '北京市朝阳区国贸', '辽宁', '汉', 'MARRIED', 'MEMBER', 'EMP007', '2019-08-01', NULL, 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('8', 'WUQING', '吴青', 'FEMALE', '1994-06-20', '110108199406200018', '13800000008', 'wuqing@ssitao.com', '北京市海淀区五道口', '江苏', '汉', 'SINGLE', 'LEAGUE', 'EMP008', '2020-01-15', '2020-04-15', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('9', 'ZHENGFU', '郑福', 'MALE', '1987-09-12', '110108198709120019', '13800000009', 'zhengfu@ssitao.com', '北京市丰台区', '四川', '汉', 'MARRIED', 'MEMBER', 'EMP009', '2020-06-01', NULL, 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('10', 'CHENJIE', '陈洁', 'FEMALE', '1995-04-05', '110108199504050020', '13800000010', 'chenjie@ssitao.com', '北京市石景山区', '北京', '汉', 'SINGLE', 'MASS', 'EMP010', '2021-03-01', '2021-06-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('11', 'LIUYANG', '刘洋', 'MALE', '1992-10-18', '110108199210180021', '13800000011', 'liuyang@ssitao.com', '北京市通州区', '河北', '汉', 'SINGLE', 'MEMBER', 'EMP011', '2021-07-01', '2021-10-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('12', 'HUANGLI', '黄丽', 'FEMALE', '1993-07-30', '110108199307300022', '13800000012', 'huanglili@ssitao.com', '北京市大兴区', '湖南', '汉', 'SINGLE', 'LEAGUE', 'EMP012', '2022-01-10', '2022-04-10', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('13', 'XUWEI', '徐伟', 'MALE', '1991-03-25', '110108199103250023', '13800000013', 'xuwei@ssitao.com', '北京市昌平区', '山东', '汉', 'MARRIED', 'MEMBER', 'EMP013', '2022-05-15', '2022-08-15', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('14', 'GAOXIAO', '高晓', 'FEMALE', '1996-01-08', '110108199601080024', '13800000014', 'gaoxiao@ssitao.com', '北京市顺义区', '北京', '汉', 'SINGLE', 'MASS', 'EMP014', '2023-02-01', '2023-05-01', 'FULL_TIME', 'BACHELOR', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0),
('15', 'MAQIANG', '马强', 'MALE', '1990-12-12', '110108199012120025', '13800000015', 'maqiang@ssitao.com', '北京市房山区', '河南', '汉', 'SINGLE', 'MEMBER', 'EMP015', '2023-06-01', '2023-09-01', 'FULL_TIME', 'MASTER', 'ON_JOB', '1', '1', '1', NOW(), NULL, NULL, 0, 0);

-- =============================================
-- 五、账户数据（密码均为 123456，使用BCrypt加密）
-- BCrypt加密后的密码: $2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi
-- =============================================
INSERT INTO `iam_account` (`account_id`, `account_code`, `account_name`, `account_password`, `account_phone`, `account_mail`, `account_avatar`, `account_type`, `account_source`, `account_status`, `account_is_admin`, `account_last_login_time`, `account_last_login_ip`, `account_init_password`, `account_init_password_reset_time`, `tenant_id`, `create_org_id`, `create_org_name`, `create_user_id`, `create_user_name`, `create_time`, `modify_org_id`, `modify_org_name`, `modify_user_id`, `modify_user_name`, `modify_time`, `is_deleted`, `version`) VALUES
('1', 'admin', '李思涛', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000001', 'lisi@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 1, NOW(), '127.0.0.1', 0, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('2', 'zhangwei', '张伟', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000002', 'zhangwei@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '127.0.0.1', 1, NULL, '1', '1', '集团总部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('3', 'lihua', '李华', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000003', 'lihua@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.100', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('4', 'wangmei', '王梅', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000004', 'wangmei@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.101', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('5', 'zhaoqiang', '赵强', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000005', 'zhaoqiang@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.102', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('6', 'sunli', '孙莉', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000006', 'sunli@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.103', 1, NULL, '1', '9', '产品中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('7', 'zhoumin', '周敏', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000007', 'zhoumin@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.104', 1, NULL, '1', '12', '销售中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('8', 'wuqing', '吴青', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000008', 'wuqing@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.105', 1, NULL, '1', '15', '人力资源部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('9', 'zhengfu', '郑福', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000009', 'zhengfu@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.106', 1, NULL, '1', '18', '财务部', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('10', 'chenjie', '陈洁', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000010', 'chenjie@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.107', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('11', 'liuyang', '刘洋', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000011', 'liuyang@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.108', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('12', 'huanglili', '黄丽', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000012', 'huanglili@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.109', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('13', 'xuwei', '徐伟', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000013', 'xuwei@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.110', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('14', 'gaoxiao', '高晓', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000014', 'gaoxiao@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.111', 1, NULL, '1', '9', '产品中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0),
('15', 'maqiang', '马强', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', '13800000015', 'maqiang@ssitao.com', NULL, 'SYSTEM', 'LOCAL', 1, 0, NOW(), '192.168.1.112', 1, NULL, '1', '2', '技术研发中心', '1', '系统管理员', NOW(), NULL, NULL, NULL, NULL, NULL, 0, 0);

-- =============================================
-- 六、角色数据
-- =============================================
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

-- =============================================
-- 七、权限数据
-- =============================================
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
('15', 'org:post:view', '岗位查看', 'BUTTON', '/org/post', 'VIEW', '查看岗位', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
-- 菜单管理权限
('16', 'menu:manage', '菜单管理', 'MENU', '/menu', 'VIEW', '菜单管理模块', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
('17', 'menu:view', '菜单查看', 'BUTTON', '/menu', 'VIEW', '查看菜单', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('18', 'menu:add', '菜单新增', 'BUTTON', '/menu', 'ADD', '新增菜单', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
-- 字典管理权限
('20', 'dict:manage', '字典管理', 'MENU', '/dict', 'VIEW', '字典管理模块', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
('21', 'dict:view', '字典查看', 'BUTTON', '/dict', 'VIEW', '查看字典', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('22', 'dict:add', '字典新增', 'BUTTON', '/dict', 'ADD', '新增字典', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
-- 日志管理权限
('30', 'log:manage', '日志管理', 'MENU', '/log', 'VIEW', '日志管理模块', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
('31', 'log:login:view', '登录日志查看', 'BUTTON', '/log/login', 'VIEW', '查看登录日志', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('32', 'log:operate:view', '操作日志查看', 'BUTTON', '/log/operate', 'VIEW', '查看操作日志', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
-- 低代码平台权限
('40', 'meta:manage', '低代码平台', 'MENU', '/meta', 'VIEW', '低代码平台管理', 1, 1, 6, '1', NOW(), '1', NULL, NULL, 0),
('41', 'meta:table:view', '表管理查看', 'BUTTON', '/meta/table', 'VIEW', '查看表管理', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('42', 'meta:table:add', '表管理新增', 'BUTTON', '/meta/table', 'ADD', '新增表', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0),
('43', 'meta:table:edit', '表管理编辑', 'BUTTON', '/meta/table', 'EDIT', '编辑表', 1, 1, 3, '1', NOW(), '1', NULL, NULL, 0),
('44', 'meta:form:view', '表单管理查看', 'BUTTON', '/meta/form', 'VIEW', '查看表单', 1, 1, 4, '1', NOW(), '1', NULL, NULL, 0),
('45', 'meta:list:view', '列表管理查看', 'BUTTON', '/meta/list', 'VIEW', '查看列表', 1, 1, 5, '1', NOW(), '1', NULL, NULL, 0),
-- 工作流权限
('50', 'workflow:manage', '工作流管理', 'MENU', '/workflow', 'VIEW', '工作流管理模块', 1, 1, 7, '1', NOW(), '1', NULL, NULL, 0),
('51', 'workflow:view', '工作流查看', 'BUTTON', '/workflow', 'VIEW', '查看工作流', 1, 1, 1, '1', NOW(), '1', NULL, NULL, 0),
('52', 'workflow:design', '工作流设计', 'BUTTON', '/workflow/design', 'DESIGN', '设计工作流', 1, 1, 2, '1', NOW(), '1', NULL, NULL, 0);

-- =============================================
-- 八、菜单数据
-- =============================================
INSERT INTO `iam_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_type`, `menu_parent_id`, `menu_path`, `menu_component`, `menu_icon`, `menu_sort`, `menu_is_visible`, `menu_is_cached`, `menu_is_affix`, `menu_permission`, `menu_redirect`, `menu_desc`, `menu_status`, `menu_is_builtin`, `menu_tree_path`, `menu_tree_level`, `tenant_id`, `create_time`, `create_user_id`, `modify_time`, `modify_user_id`, `is_deleted`, `version`) VALUES
-- 一级菜单
('1', 'dashboard', '工作台', 'DIRECTORY', NULL, '/console', NULL, 'DashboardOutlined', 1, 1, 0, 0, NULL, '/console', '工作台', 1, 1, '/1', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('2', 'system', '系统管理', 'DIRECTORY', NULL, '/admin', NULL, 'SettingOutlined', 2, 1, 0, 0, NULL, '/admin', '系统管理', 1, 1, '/2', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('3', 'org', '组织管理', 'DIRECTORY', NULL, '/admin/org', NULL, 'ApartmentOutlined', 3, 1, 0, 0, NULL, '/admin/org', '组织管理', 1, 1, '/3', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('4', 'meta', '低代码平台', 'DIRECTORY', NULL, '/admin/meta/table', NULL, 'BuildOutlined', 4, 1, 0, 0, NULL, '/admin/meta/table', '低代码平台', 1, 1, '/4', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('5', 'workflow', '工作流', 'DIRECTORY', NULL, '/admin/workflow', NULL, 'BranchOutlined', 5, 1, 0, 0, NULL, '/admin/workflow', '工作流管理', 1, 1, '/5', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
('6', 'monitor', '系统监控', 'DIRECTORY', NULL, '/admin/monitor', NULL, 'MonitorOutlined', 6, 1, 0, 0, NULL, '/admin/monitor', '系统监控', 1, 1, '/6', 1, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 工作台子菜单
('10', 'dashboard:analysis', '分析页', 'MENU', '1', '/console', 'dashboard/Analysis', 'AreaChartOutlined', 1, 1, 0, 0, NULL, NULL, '分析页', 1, 1, '/1/10', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('11', 'dashboard:workbench', '工作台', 'MENU', '1', '/console', 'dashboard/Workbench', 'AppstoreOutlined', 2, 1, 0, 0, NULL, NULL, '工作台', 1, 1, '/1/11', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('12', 'dashboard:notice', '通知公告', 'MENU', '1', '/admin/notice', 'dashboard/Notice', 'NotificationOutlined', 3, 1, 0, 0, NULL, NULL, '通知公告', 1, 1, '/1/12', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统管理子菜单
('20', 'system:user', '用户管理', 'MENU', '2', '/admin', 'system/user/UserList', 'UserOutlined', 1, 1, 0, 0, 'system:user:view', NULL, '用户管理', 1, 1, '/2/20', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('21', 'system:role', '角色管理', 'MENU', '2', '/admin', 'system/role/RoleList', 'TeamOutlined', 2, 1, 0, 0, 'system:role:view', NULL, '角色管理', 1, 1, '/2/21', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('22', 'system:menu', '菜单管理', 'MENU', '2', '/admin', 'system/menu/MenuList', 'MenuOutlined', 3, 1, 0, 0, 'menu:view', NULL, '菜单管理', 1, 1, '/2/22', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('23', 'system:dict', '字典管理', 'MENU', '2', '/config', 'system/dict/DictList', 'BookOutlined', 4, 1, 0, 0, 'dict:view', NULL, '字典管理', 1, 1, '/2/23', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('24', 'system:config', '系统配置', 'MENU', '2', '/config', 'system/config/ConfigList', 'SettingOutlined', 5, 1, 0, 0, NULL, NULL, '系统配置', 1, 1, '/2/24', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 组织管理子菜单
('30', 'org:company', '公司管理', 'MENU', '3', '/admin/org', 'org/company/CompanyList', 'HomeOutlined', 1, 1, 0, 0, 'org:company:view', NULL, '公司管理', 1, 1, '/3/30', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('31', 'org:dept', '部门管理', 'MENU', '3', '/admin/org', 'org/dept/DeptList', 'ApartmentOutlined', 2, 1, 0, 0, 'org:dept:view', NULL, '部门管理', 1, 1, '/3/31', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('32', 'org:post', '岗位管理', 'MENU', '3', '/admin/org', 'org/post/PostList', 'IdcardOutlined', 3, 1, 0, 0, 'org:post:view', NULL, '岗位管理', 1, 1, '/3/32', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 低代码平台子菜单
('40', 'meta:table', '表管理', 'MENU', '4', '/admin/meta/table', 'meta/table/TableList', 'TableOutlined', 1, 1, 0, 0, 'meta:table:view', NULL, '表管理', 1, 1, '/4/40', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('41', 'meta:column', '字段管理', 'MENU', '4', '/admin/meta/column', 'meta/column/ColumnList', 'AppstoreOutlined', 2, 1, 0, 0, NULL, NULL, '字段管理', 1, 1, '/4/41', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('42', 'meta:form', '表单管理', 'MENU', '4', '/admin/meta/form', 'meta/form/FormList', 'FormOutlined', 3, 1, 0, 0, 'meta:form:view', NULL, '表单管理', 1, 1, '/4/42', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('43', 'meta:list', '列表管理', 'MENU', '4', '/admin/meta/list', 'meta/list/ListList', 'UnorderedListOutlined', 4, 1, 0, 0, 'meta:list:view', NULL, '列表管理', 1, 1, '/4/43', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('44', 'meta:code', '代码生成', 'MENU', '4', '/admin/meta/code', 'meta/code/CodeGen', 'CodeOutlined', 5, 1, 0, 0, NULL, NULL, '代码生成', 1, 1, '/4/44', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 工作流子菜单
('50', 'workflow:list', '流程列表', 'MENU', '5', '/admin/workflow', 'workflow/WorkflowList', 'OrderedListOutlined', 1, 1, 0, 0, 'workflow:view', NULL, '流程列表', 1, 1, '/5/50', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('51', 'workflow:design', '流程设计', 'MENU', '5', '/admin/workflow', 'workflow/WorkflowDesign', 'DragOutlined', 2, 1, 0, 0, 'workflow:design', NULL, '流程设计', 1, 1, '/5/51', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('52', 'workflow:task', '我的任务', 'MENU', '5', '/admin/workflow', 'workflow/MyTask', 'TaskOutlined', 3, 1, 0, 0, NULL, NULL, '我的任务', 1, 1, '/5/52', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
-- 系统监控子菜单
('60', 'monitor:login-log', '登录日志', 'MENU', '6', '/admin/monitor', 'monitor/LoginLogList', 'FileSearchOutlined', 1, 1, 0, 0, 'log:login:view', NULL, '登录日志', 1, 1, '/6/60', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('61', 'monitor:operate-log', '操作日志', 'MENU', '6', '/admin/monitor', 'monitor/OperateLogList', 'FileSyncOutlined', 2, 1, 0, 0, 'log:operate:view', NULL, '操作日志', 1, 1, '/6/61', 2, '1', NOW(), '1', NULL, NULL, 0, 0),
('62', 'monitor:online', '在线用户', 'MENU', '6', '/admin/monitor', 'monitor/OnlineUser', 'TeamOutlined', 3, 1, 0, 0, NULL, NULL, '在线用户', 1, 1, '/6/62', 2, '1', NOW(), '1', NULL, NULL, 0, 0);

-- =============================================
-- 九、账户角色关联数据
-- =============================================
INSERT INTO `iam_account_role` (`id`, `account_id`, `role_id`, `is_valid`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
-- 超级管理员
('1', '1', '1', 1, '1', NOW(), '1', 0),
-- 技术研发中心
('2', '2', '2', 1, '1', NOW(), '1', 0),
('3', '3', '4', 1, '1', NOW(), '1', 0),
('4', '4', '5', 1, '1', NOW(), '1', 0),
('5', '5', '5', 1, '1', NOW(), '1', 0),
('6', '10', '5', 1, '1', NOW(), '1', 0),
('7', '11', '5', 1, '1', NOW(), '1', 0),
('8', '12', '6', 1, '1', NOW(), '1', 0),
('9', '13', '7', 1, '1', NOW(), '1', 0),
('10', '15', '5', 1, '1', NOW(), '1', 0),
-- 产品中心
('11', '6', '8', 1, '1', NOW(), '1', 0),
('12', '14', '9', 1, '1', NOW(), '1', 0),
-- 销售中心
('13', '7', '10', 1, '1', NOW(), '1', 0),
-- 人力资源
('14', '8', '11', 1, '1', NOW(), '1', 0),
-- 财务部
('15', '9', '12', 1, '1', NOW(), '1', 0);

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
('18', '1', '18', 1, '1', NOW(), '1', 0),
('19', '1', '20', 1, '1', NOW(), '1', 0),
('20', '1', '21', 1, '1', NOW(), '1', 0),
('21', '1', '22', 1, '1', NOW(), '1', 0),
('22', '1', '30', 1, '1', NOW(), '1', 0),
('23', '1', '31', 1, '1', NOW(), '1', 0),
('24', '1', '32', 1, '1', NOW(), '1', 0),
('25', '1', '40', 1, '1', NOW(), '1', 0),
('26', '1', '41', 1, '1', NOW(), '1', 0),
('27', '1', '42', 1, '1', NOW(), '1', 0),
('28', '1', '43', 1, '1', NOW(), '1', 0),
('29', '1', '44', 1, '1', NOW(), '1', 0),
('30', '1', '45', 1, '1', NOW(), '1', 0),
('31', '1', '50', 1, '1', NOW(), '1', 0),
('32', '1', '51', 1, '1', NOW(), '1', 0),
('33', '1', '52', 1, '1', NOW(), '1', 0),
-- 系统管理员
('40', '2', '1', 1, '1', NOW(), '1', 0),
('41', '2', '2', 1, '1', NOW(), '1', 0),
('42', '2', '3', 1, '1', NOW(), '1', 0),
('43', '2', '4', 1, '1', NOW(), '1', 0),
('44', '2', '5', 1, '1', NOW(), '1', 0),
('45', '2', '6', 1, '1', NOW(), '1', 0),
('46', '2', '7', 1, '1', NOW(), '1', 0),
('47', '2', '8', 1, '1', NOW(), '1', 0),
('48', '2', '9', 1, '1', NOW(), '1', 0),
('49', '2', '10', 1, '1', NOW(), '1', 0),
('50', '2', '11', 1, '1', NOW(), '1', 0),
('51', '2', '12', 1, '1', NOW(), '1', 0),
('52', '2', '13', 1, '1', NOW(), '1', 0),
('53', '2', '14', 1, '1', NOW(), '1', 0),
('54', '2', '15', 1, '1', NOW(), '1', 0),
('55', '2', '16', 1, '1', NOW(), '1', 0),
('56', '2', '17', 1, '1', NOW(), '1', 0),
('57', '2', '18', 1, '1', NOW(), '1', 0),
('58', '2', '20', 1, '1', NOW(), '1', 0),
('59', '2', '21', 1, '1', NOW(), '1', 0),
('60', '2', '22', 1, '1', NOW(), '1', 0),
('61', '2', '30', 1, '1', NOW(), '1', 0),
('62', '2', '31', 1, '1', NOW(), '1', 0),
('63', '2', '32', 1, '1', NOW(), '1', 0),
-- 开发人员权限
('70', '5', '10', 1, '1', NOW(), '1', 0),
('71', '5', '13', 1, '1', NOW(), '1', 0),
('72', '5', '40', 1, '1', NOW(), '1', 0),
('73', '5', '41', 1, '1', NOW(), '1', 0),
-- 测试人员权限
('80', '6', '30', 1, '1', NOW(), '1', 0),
('81', '6', '31', 1, '1', NOW(), '1', 0),
('82', '6', '32', 1, '1', NOW(), '1', 0),
-- 运维人员权限
('90', '7', '30', 1, '1', NOW(), '1', 0),
('91', '7', '31', 1, '1', NOW(), '1', 0),
('92', '7', '32', 1, '1', NOW(), '1', 0),
('93', '7', '50', 1, '1', NOW(), '1', 0),
('94', '7', '51', 1, '1', NOW(), '1', 0),
-- 产品经理权限
('100', '8', '40', 1, '1', NOW(), '1', 0),
('101', '8', '41', 1, '1', NOW(), '1', 0),
('102', '8', '42', 1, '1', NOW(), '1', 0),
('103', '8', '43', 1, '1', NOW(), '1', 0),
('104', '8', '44', 1, '1', NOW(), '1', 0),
('105', '8', '45', 1, '1', NOW(), '1', 0),
('106', '8', '50', 1, '1', NOW(), '1', 0),
('107', '8', '52', 1, '1', NOW(), '1', 0);

-- =============================================
-- 十一、账户部门关联数据
-- =============================================
INSERT INTO `iam_account_dept` (`id`, `account_id`, `dept_id`, `is_primary`, `is_leader`, `tenant_id`, `create_time`, `create_user_id`, `is_deleted`) VALUES
-- 集团总部
('1', '1', '1', 1, 1, '1', NOW(), '1', 0),
-- 技术研发中心
('2', '2', '2', 1, 1, '1', NOW(), '1', 0),
('3', '3', '3', 1, 1, '1', NOW(), '1', 0),
('4', '4', '3', 1, 0, '1', NOW(), '1', 0),
('5', '5', '3', 1, 0, '1', NOW(), '1', 0),
('6', '10', '3', 1, 0, '1', NOW(), '1', 0),
('7', '11', '5', 1, 0, '1', NOW(), '1', 0),
('8', '12', '6', 1, 1, '1', NOW(), '1', 0),
('9', '13', '7', 1, 1, '1', NOW(), '1', 0),
('10', '15', '4', 1, 0, '1', NOW(), '1', 0),
-- 产品中心
('11', '6', '9', 1, 1, '1', NOW(), '1', 0),
('12', '14', '11', 1, 1, '1', NOW(), '1', 0),
-- 销售中心
('13', '7', '12', 1, 1, '1', NOW(), '1', 0),
-- 人力资源
('14', '8', '15', 1, 1, '1', NOW(), '1', 0),
-- 财务部
('15', '9', '18', 1, 1, '1', NOW(), '1', 0);

-- =============================================
-- 十二、登录日志测试数据
-- =============================================
INSERT INTO `iam_login_log` (`log_id`, `account_id`, `account_code`, `account_name`, `login_type`, `login_status`, `login_fail_reason`, `login_ip`, `login_location`, `login_device`, `login_browser`, `login_os`, `login_time`, `logout_time`, `online_duration`, `tenant_id`) VALUES
('1', '1', 'admin', '李思涛', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR), 3600, '1'),
('2', '1', 'admin', '李思涛', 'PASSWORD', 'SUCCESS', NULL, '127.0.0.1', '本地', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 1 HOUR), NULL, NULL, '1'),
('3', '2', 'zhangwei', '张伟', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.100', '北京市海淀区', 'PC', 'Firefox 121.0', 'macOS 14', DATE_SUB(NOW(), INTERVAL 30 MINUTE), NULL, NULL, '1'),
('4', '3', 'lihua', '李华', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.101', '北京市朝阳区', 'PC', 'Edge 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 45 MINUTE), NULL, NULL, '1'),
('5', '4', 'wangmei', '王梅', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.102', '北京市西城区', 'PC', 'Chrome 120.0', 'Ubuntu 22.04', DATE_SUB(NOW(), INTERVAL 20 MINUTE), NULL, NULL, '1'),
('6', '5', 'zhaoqiang', '赵强', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.103', '北京市东城区', 'PC', 'Safari 17.0', 'macOS 14', DATE_SUB(NOW(), INTERVAL 15 MINUTE), NULL, NULL, '1'),
('7', '6', 'sunli', '孙莉', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.104', '北京市海淀区', 'PC', 'Chrome 120.0', 'Windows 11', DATE_SUB(NOW(), INTERVAL 10 MINUTE), NULL, NULL, '1'),
('8', '7', 'zhoumin', '周敏', 'PASSWORD', 'SUCCESS', NULL, '192.168.1.105', '北京市朝阳区', 'PC', 'Firefox 121.0', 'Windows 10', DATE_SUB(NOW(), INTERVAL 5 MINUTE), NULL, NULL, '1'),
('9', NULL, 'unknown', '未知用户', 'PASSWORD', 'FAIL', '用户名或密码错误', '192.168.1.200', '北京市朝阳区', 'PC', 'Edge 120.0', 'Windows 10', DATE_SUB(NOW(), INTERVAL 15 MINUTE), NULL, NULL, '1'),
('10', NULL, 'hacker', '攻击者', 'PASSWORD', 'FAIL', '账户已被禁用', '45.33.32.156', '美国', 'PC', 'Chrome 120.0', 'Linux', DATE_SUB(NOW(), INTERVAL 1 DAY), NULL, NULL, '1');

-- =============================================
-- 十三、操作日志测试数据
-- =============================================
INSERT INTO `iam_operate_log` (`log_id`, `operate_type`, `module_name`, `business_type`, `business_id`, `method_name`, `request_url`, `request_method`, `request_params`, `response_result`, `operate_status`, `error_msg`, `execute_duration`, `operator_id`, `operator_name`, `operator_dept`, `operate_ip`, `operate_location`, `operate_time`, `tenant_id`) VALUES
('1', 'LOGIN', '认证模块', '用户登录', '1', 'login', '/api/auth/login', 'POST', '{"username":"admin"}', '{"code":200,"message":"登录成功","data":{"token":"xxxxx"}}', 'SUCCESS', NULL, 150, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('2', 'CREATE', '用户管理', '新增用户', '2', 'createUser', '/api/system/user', 'POST', '{"username":"zhangwei","realName":"张伟"}', '{"code":200,"message":"创建成功"}', 'SUCCESS', NULL, 80, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('3', 'UPDATE', '角色管理', '修改角色', '1', 'updateRole', '/api/system/role/1', 'PUT', '{"roleName":"超级管理员"}', '{"code":200,"message":"更新成功"}', 'SUCCESS', NULL, 60, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('4', 'DELETE', '部门管理', '删除部门', '10', 'deleteDept', '/api/org/dept/10', 'DELETE', '{}', '{"code":200,"message":"删除成功"}', 'SUCCESS', NULL, 45, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('5', 'CREATE', '低代码平台', '新增表', '1', 'createTable', '/api/meta/table', 'POST', '{"tableName":"customer","tableDesc":"客户信息表"}', '{"code":200,"message":"创建成功"}', 'SUCCESS', NULL, 120, '3', '李华', '技术研发中心', '192.168.1.101', '北京市朝阳区', NOW(), '1'),
('6', 'UPDATE', '低代码平台', '编辑字段', '5', 'updateColumn', '/api/meta/column/5', 'PUT', '{"columnName":"customerName","required":true}', '{"code":200,"message":"更新成功"}', 'SUCCESS', NULL, 55, '3', '李华', '技术研发中心', '192.168.1.101', '北京市朝阳区', NOW(), '1'),
('7', 'EXPORT', '系统监控', '导出日志', '30', 'exportLog', '/api/monitor/log/export', 'GET', '{startTime:"2026-01-01",endTime:"2026-03-07"}', '{"code":200,"message":"导出成功","data":{"filePath":"/export/logs.xlsx"}}', 'SUCCESS', NULL, 5200, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('8', 'LOGIN', '认证模块', '用户登录', '2', 'login', '/api/auth/login', 'POST', '{"username":"zhangwei"}', '{"code":200,"message":"登录成功"}', 'SUCCESS', NULL, 180, '2', '张伟', '技术研发中心', '192.168.1.100', '北京市海淀区', NOW(), '1'),
('9', 'DELETE', '用户管理', '删除用户', '15', 'deleteUser', '/api/system/user/15', 'DELETE', '{}', '{"code":500,"message":"用户有关联数据，无法删除"}', 'FAIL', '用户有关联数据，无法删除', 30, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1'),
('10', 'IMPORT', '用户管理', '导入用户', NULL, 'importUser', '/api/system/user/import', 'POST', '{"fileName":"users.xlsx"}', '{"code":200,"message":"导入成功","data":{"success":50,"fail":2}}', 'SUCCESS', NULL, 8500, '1', '李思涛', '集团总部', '127.0.0.1', '本地', NOW(), '1');

SET FOREIGN_KEY_CHECKS = 1;
