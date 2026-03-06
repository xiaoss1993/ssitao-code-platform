# Code Platform 全面优化方案

## 1. 项目概述

### 1.1 优化目标
对SSITAO Code Platform进行全面优化，提升代码质量、完善安全权限、实现前后端集成。

### 1.2 优化范围

| 阶段 | 内容 |
|------|------|
| 阶段1 | 代码质量优化 |
| 阶段2 | 安全权限完善 |
| 阶段3 | 前后端集成 |

---

## 2. 阶段1：代码质量优化

### 2.1 统一依赖注入

**问题**: 项目中混用@Autowired和@Resource

**解决方案**:
- 统一使用@Resource注解（Spring官方推荐）
- 影响文件：
  - IamGroupAppServiceImpl
  - IamUserOrgAppServiceImpl
  - WorkflowServiceImpl
  - 等

### 2.2 完善事务配置

**问题**: 部分Service缺少@Transactional注解

**解决方案**:
- 为所有Service方法添加事务注解
- 统一使用@Transactional(rollbackFor = Exception.class)

### 2.3 统一ID类型

**问题**: ID类型不一致（String/Long混用）

**解决方案**:
- 统一使用String类型（雪花算法生成）
- 避免Long类型在前端的精度丢失问题

---

## 3. 阶段2：安全权限完善

### 3.1 数据权限拦截器

**问题**: DataPermissionInterceptor有TODO未实现

**解决方案**:
- 实现行级数据权限过滤
- 支持部门、岗位、用户自定义数据范围
- 配置数据权限规则

### 3.2 租户拦截器完善

**问题**: TenantUtils有TODO

**解决方案**:
- 完善多租户数据隔离
- 实现租户自动识别（请求头/域名/参数）

---

## 4. 阶段3：前后端集成

### 4.1 动态API集成

**问题**: DynamicCrudController与MetaTable未完全连接

**解决方案**:
- MetaTableController调用DynamicCrudService
- 实现动态表的CRUD操作

### 4.2 前端模板集成

**问题**: 模板页面需要与后端API对接

**解决方案**:
- MetaList列表页面对接API
- MetaForm表单页面对接API

---

## 5. 实施计划

### Task 1.1: 统一依赖注入

**Files to modify:**
- IamGroupAppServiceImpl.java
- IamUserOrgAppServiceImpl.java
- WorkflowServiceImpl.java
- 其他混用@Autowired的Service

### Task 1.2: 完善事务配置

**Files to modify:**
- IamGroupAppServiceImpl.java
- IamUserOrgAppServiceImpl.java
- 所有缺少@Transactional的Service

### Task 1.3: 统一ID类型

**Files to modify:**
- IamOrganizationController.java
- MetaEntityController.java
- MetaTableController.java

### Task 2.1: 数据权限拦截器

**Files to modify:**
- DataPermissionInterceptor.java

### Task 2.2: 租户拦截器

**Files to modify:**
- TenantUtils.java

### Task 3.1: 动态API集成

**Files to modify:**
- MetaTableController.java
- MetaEntityController.java

### Task 3.2: 前端模板集成

**Files to modify:**
- templates/meta/list.html
- templates/meta/form.html

---

## 6. 验收标准

1. 所有编译通过
2. 代码风格统一
3. 前后端API正常通信
4. 数据权限正常工作

---

## 7. 技术要点

- @Resource vs @Autowired: 前者更明确，后者自动装配
- @Transactional: 事务传播行为
- MyBatis-Flex: QueryWrapper动态查询
- Sa-Token: 多租户token处理
