# IAM模块DDD重构总结

## 重构概述

本次重构使用 `aggregate-framework` 框架，采用 DDD（领域驱动设计）模式，按业务功能将IAM模块划分为6个独立领域。所有类名均带有 `Iam` 前缀，便于识别和区分。

## 领域划分

### 1. Identity 认证领域 (iam/identity)
负责用户认证、账号管理、登录日志等核心认证功能。

**聚合根：**
- `IamIdentityAccount` - 认证账号聚合根
- `IamIdentityLoginLog` - 登录日志实体

**仓储接口：**
- `IamIdentityAccountRepository` - 账号仓储
- `IamIdentityLoginLogRepository` - 登录日志仓储

**领域事件：**
- `IdentityAccountCreatedEvent` - 账号创建事件
- `IdentityAccountLockedEvent` - 账号锁定事件
- `IdentityPasswordChangedEvent` - 密码修改事件
- `IdentityLoginFailedEvent` - 登录失败事件
- `IdentityLoginSuccessEvent` - 登录成功事件

**应用服务：**
- `IamIdentityAccountAppService` - 账号应用服务
- `IamIdentityLoginAppService` - 登录应用服务

**控制器：**
- `IamIdentityAccountController` - 账号管理接口
- `IamIdentityLoginController` - 登录认证接口

### 2. Authorization 授权领域 (iam/authorization)
负责角色管理、权限管理、权限组管理等功能。

**聚合根：**
- `IamAuthorizationRole` - 角色聚合根（支持树形结构）
- `IamAuthorizationPermission` - 权限聚合根
- `IamAuthorizationPermGroup` - 权限组聚合根

**仓储接口：**
- `IamAuthorizationRoleRepository` - 角色仓储
- `IamAuthorizationPermissionRepository` - 权限仓储

**应用服务：**
- `IamAuthorizationRoleAppService` - 角色应用服务

**控制器：**
- `IamAuthorizationRoleController` - 角色管理接口

### 3. UserProfile 用户档案领域 (iam/userprofile)
负责用户基本信息管理，包括个人信息、联系方式、工作信息等。

**聚合根：**
- `IamUserProfile` - 用户档案聚合根

**仓储接口：**
- `IamUserProfileRepository` - 用户档案仓储

**应用服务：**
- `IamUserProfileAppService` - 用户档案应用服务

**控制器：**
- `IamUserProfileController` - 用户档案管理接口

### 4. Organization 组织领域 (iam/organization)
负责部门管理、组织架构管理等功能。

**聚合根：**
- `IamOrganizationDepartment` - 部门聚合根（支持树形结构）
- `IamOrganization` - 组织聚合根

**仓储接口：**
- `IamOrganizationDepartmentRepository` - 部门仓储
- `IamOrganizationRepository` - 组织仓储

**应用服务：**
- `IamOrganizationDepartmentAppService` - 部门应用服务

**控制器：**
- `IamOrganizationDepartmentController` - 部门管理接口

### 5. Menu 菜单领域 (iam/menu)
负责系统菜单管理，包括头部菜单、用户菜单等功能。

**聚合根：**
- `IamMenuHeadMenu` - 头部菜单聚合根（支持树形结构和可见性控制）

**仓储接口：**
- `IamMenuHeadMenuRepository` - 菜单仓储

**应用服务：**
- `IamMenuAppService` - 菜单应用服务

**控制器：**
- `IamMenuHeadMenuController` - 菜单管理接口

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

## 命名规范

所有DDD相关类均使用 `Iam` 前缀，格式如下：

- **聚合根**：`Iam{Domain}{Aggregate}`（如 `IamIdentityAccount`）
- **仓储接口**：`Iam{Domain}{Aggregate}Repository`（如 `IamIdentityAccountRepository`）
- **应用服务**：`Iam{Domain}{Aggregate}AppService`（如 `IamIdentityAccountAppService`）
- **控制器**：`Iam{Domain}{Aggregate}Controller`（如 `IamIdentityAccountController`）
- **DTO**：`Iam{Domain}{Aggregate}DTO`（如 `IamIdentityAccountDTO`）
- **命令**：`Iam{Domain}{Aggregate}{Action}Command`（如 `IamIdentityAccountCreateCommand`）
- **查询**：`Iam{Domain}{Aggregate}Query`（如 `IamIdentityAccountQuery`）

## 重构特点

1. **按业务功能划分领域**：将IAM模块划分为6个独立领域，每个领域有明确的业务边界
2. **聚合根封装业务逻辑**：业务规则封装在聚合根中，避免贫血模型
3. **仓储模式隔离数据访问**：通过仓储接口隔离领域层和基础设施层
4. **应用服务编排业务用例**：应用服务负责跨聚合根的业务编排
5. **领域事件驱动**：使用领域事件实现领域间的解耦和协作
6. **统一命名规范**：所有类都使用 `Iam` 前缀，便于识别和查找

## API路径规范

每个领域的REST API路径遵循以下规范：

- Identity认证：`/iam/identity/account/*`、`/iam/identity/login/*`
- Authorization授权：`/iam/authorization/role/*`
- UserProfile用户：`/iam/userprofile/*`
- Organization组织：`/iam/organization/department/*`
- Menu菜单：`/iam/menu/headmenu/*`

## 后续工作

1. 完成各领域的应用服务实现（AppServiceImpl）
2. 完成各领域的仓储实现（RepositoryImpl）
3. 完成各领域的对象转换器（Converter）
4. 实现领域事件处理器（EventHandler）
5. 完善单元测试和集成测试
6. 逐步将旧版Controller和Service迁移到新架构

## 兼容性说明

- 保留了原有的 `controller`、`service`、`dal` 目录结构
- 新旧架构并存，逐步迁移
- 新功能使用DDD架构开发
- 旧功能保持不变，确保系统稳定性
