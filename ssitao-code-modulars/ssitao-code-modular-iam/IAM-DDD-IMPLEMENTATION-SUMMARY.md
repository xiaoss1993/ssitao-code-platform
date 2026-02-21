# IAM模块DDD重构完成总结

## 重构概述

本次重构使用 DDD（领域驱动设计）模式，按业务功能将IAM模块划分为6个独立领域。所有类名均带有 `Iam` 前缀，便于识别和区分。

## 领域划分

### 1. Identity 认证领域 (iam/identity)

**职责**：用户认证、账号管理、登录日志

**聚合根**：
- `IamAccount` - 认证账号聚合根
- `IamLoginLog` - 登录日志实体

**仓储接口**：
- `IamAccountRepository` - 账号仓储
- `IamLoginLogRepository` - 登录日志仓储

**应用服务**：
- `IamAccountAppService` - 账号应用服务
- `IamLoginAppService` - 登录应用服务

**控制器**：
- `IamIdentityController` - 认证管理接口

**API路径**：`/iam/identity/account/*`, `/iam/identity/login/*`

---

### 2. Authorization 授权领域 (iam/authorization)

**职责**：角色管理、权限管理、权限组管理

**聚合根**：
- `IamRole` - 角色聚合根（支持树形结构）
- `IamPermission` - 权限聚合根（支持MENU/BUTTON/API/DATA四种类型）
- `IamPermGroup` - 权限组聚合根

**仓储接口**：
- `IamRoleRepository` - 角色仓储
- `IamPermissionRepository` - 权限仓储
- `IamPermGroupRepository` - 权限组仓储

**应用服务**：
- `IamRoleAppService` - 角色应用服务
- `IamPermissionAppService` - 权限应用服务
- `IamPermGroupAppService` - 权限组应用服务

**控制器**：
- `IamAuthorizationController` - 授权管理接口

**API路径**：`/iam/authorization/role/*`, `/iam/authorization/permission/*`, `/iam/authorization/perm-group/*`

---

### 3. UserProfile 用户档案领域 (iam/userprofile)

**职责**：用户基本信息管理

**聚合根**：
- `IamUser` - 用户档案聚合根

**仓储接口**：
- `IamUserRepository` - 用户档案仓储

**应用服务**：
- `IamUserAppService` - 用户档案应用服务

**控制器**：
- `IamUserController` - 用户档案管理接口

**API路径**：`/iam/user/*`

---

### 4. Organization 组织领域 (iam/organization)

**职责**：部门管理、岗位管理、组织架构管理

**聚合根**：
- `IamDepartment` - 部门聚合根（支持树形结构）
- `IamPost` - 岗位聚合根
- `IamOrganization` - 组织聚合根

**仓储接口**：
- `IamDepartmentRepository` - 部门仓储
- `IamPostRepository` - 岗位仓储
- `IamOrganizationRepository` - 组织仓储

**应用服务**：
- `IamDepartmentAppService` - 部门应用服务
- `IamPostAppService` - 岗位应用服务

**控制器**：
- `IamOrganizationController` - 组织管理接口

**API路径**：`/iam/organization/department/*`, `/iam/organization/post/*`

---

### 5. System 系统领域 (iam/system)

**职责**：租户管理、字典管理

**聚合根**：
- `IamTenant` - 租户聚合根
- `IamDictType` - 字典类型聚合根
- `IamDictData` - 字典数据聚合根

**仓储接口**：
- `IamTenantRepository` - 租户仓储
- `IamDictTypeRepository` - 字典类型仓储
- `IamDictDataRepository` - 字典数据仓储

**应用服务**：
- `IamTenantAppService` - 租户应用服务
- `IamDictAppService` - 字典应用服务

**控制器**：
- `IamSystemController` - 系统管理接口

**API路径**：`/iam/system/tenant/*`, `/iam/system/dict-type/*`, `/iam/system/dict-data/*`

---

### 6. Audit 审计领域 (iam/audit)

**职责**：操作日志记录

**聚合根**：
- `IamOperateLog` - 操作日志聚合根

**仓储接口**：
- `IamOperateLogRepository` - 操作日志仓储

**应用服务**：
- `IamOperateLogAppService` - 操作日志应用服务

**控制器**：
- `IamAuditController` - 审计日志接口

**API路径**：`/iam/audit/operate-log/*`

---

## DDD分层架构

每个领域都遵循标准的DDD四层架构：

```
com.ssitao.code.modular.iam.{domain}
├── api/                    # API层
│   ├── dto/               # 数据传输对象
│   ├── command/           # 命令对象
│   └── query/             # 查询对象
├── application/           # 应用层
│   ├── service/           # 应用服务接口
│   ├── command/           # 命令对象
│   └── query/             # 查询对象
├── domain/                # 领域层
│   ├── model/             # 聚合根和实体
│   ├── repository/        # 仓储接口
│   └── event/             # 领域事件
├── infrastructure/        # 基础设施层
│   ├── repository/        # 仓储实现
│   ├── converter/         # 对象转换器
│   └── event/             # 事件处理器
└── controller/            # 接口层
    └── *.java            # REST控制器
```

---

## 命名规范

所有DDD相关类均使用 `Iam` 前缀，格式如下：

- **聚合根**：`Iam{Domain}{Aggregate}`（如 `IamAccount`）
- **仓储接口**：`Iam{Domain}{Aggregate}Repository`（如 `IamAccountRepository`）
- **应用服务**：`Iam{Domain}{Aggregate}AppService`（如 `IamAccountAppService`）
- **控制器**：`Iam{Domain}{Aggregate}Controller`（如 `IamAccountController`）
- **DTO**：`Iam{Domain}{Aggregate}DTO`（如 `IamAccountDTO`）
- **命令**：`Iam{Domain}{Aggregate}{Action}Command`（如 `IamAccountCreateCommand`）
- **查询**：`Iam{Domain}{Aggregate}Query`（如 `IamAccountQuery`）

---

## 数据库表映射

| 领域 | 聚合根 | 数据库表 | 主键类型 |
|------|--------|----------|----------|
| Identity | IamAccount | iam_account | BIGINT |
| Identity | IamLoginLog | iam_login_log | BIGINT |
| Authorization | IamRole | iam_role | BIGINT |
| Authorization | IamPermission | iam_permission | BIGINT |
| Authorization | IamPermGroup | iam_perm_group | BIGINT |
| UserProfile | IamUser | iam_user | BIGINT |
| Organization | IamDepartment | iam_department | BIGINT |
| Organization | IamPost | iam_post | BIGINT |
| Organization | IamOrganization | iam_organization | BIGINT |
| System | IamTenant | iam_tenant | BIGINT |
| System | IamDictType | iam_dict_type | BIGINT |
| System | IamDictData | iam_dict_data | BIGINT |
| Audit | IamOperateLog | iam_operate_log | BIGINT |

**关联表**：
- iam_user_role - 用户角色关联
- iam_role_permission - 角色权限关联
- iam_user_permission - 用户权限关联
- iam_dept_permission - 部门权限关联
- iam_perm_group_permission - 权限组权限关联

---

## 创建的文件统计

| 领域 | 聚合根 | 仓储接口 | 应用服务 | DTO | 命令 | 控制器 | 总计 |
|------|--------|----------|----------|-----|------|--------|------|
| Identity | 2 | 2 | 2 | 3 | 2 | 1 | 12 |
| Authorization | 3 | 3 | 3 | 3 | 6 | 1 | 19 |
| UserProfile | 1 | 1 | 1 | 1 | 3 | 1 | 7 |
| Organization | 3 | 3 | 2 | 2 | 4 | 1 | 15 |
| System | 3 | 3 | 2 | 3 | 5 | 1 | 17 |
| Audit | 1 | 1 | 1 | 1 | 1 | 1 | 5 |
| **总计** | **13** | **13** | **11** | **13** | **21** | **6** | **75** |

---

## 待完善工作

1. **基础设施层实现**：
   - 各领域的仓储实现类（RepositoryImpl）
   - 对象转换器（Converter）
   - 领域事件处理器（EventHandler）

2. **MyBatis Mapper XML**：
   - 为每个Mapper创建对应的XML映射文件

3. **单元测试**：
   - 为各领域编写单元测试
   - 为应用服务编写集成测试

4. **领域事件实现**：
   - 实现跨领域的领域事件发布与订阅机制
   - 实现事件处理器

5. **缓存集成**：
   - 为字典、权限等高频查询数据添加缓存

6. **权限拦截器**：
   - 基于权限模型实现接口级别的权限校验

---

## API路径汇总

| 领域 | 路径前缀 | 功能 |
|------|----------|------|
| Identity | `/iam/identity/account` | 账号管理 |
| Identity | `/iam/identity/login` | 登录认证 |
| Authorization | `/iam/authorization/role` | 角色管理 |
| Authorization | `/iam/authorization/permission` | 权限管理 |
| Authorization | `/iam/authorization/perm-group` | 权限组管理 |
| UserProfile | `/iam/user` | 用户管理 |
| Organization | `/iam/organization/department` | 部门管理 |
| Organization | `/iam/organization/post` | 岗位管理 |
| System | `/iam/system/tenant` | 租户管理 |
| System | `/iam/system/dict-type` | 字典类型管理 |
| System | `/iam/system/dict-data` | 字典数据管理 |
| Audit | `/iam/audit/operate-log` | 操作日志 |

---

## 版本信息

- **版本号**：2.0.0
- **创建时间**：2026-02-21
- **作者**：ssitao-code
