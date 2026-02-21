# IAM模块代码清理总结

## 清理概述

对IAM模块进行了全面的代码整理和清理，删除了重复的旧架构代码和重复的DDD模块，统一采用DDD架构。

## 清理前后对比

| 项目 | 清理前 | 清理后 | 减少比例 |
|------|--------|--------|----------|
| Java文件总数 | 171个 | 77个 | 55% |
| Controller | 10个旧架构 | 5个DDD架构 | 统一 |
| Service | 22个(接口+实现) | 使用DDD应用服务 | 统一 |
| DDD模块 | 11个(有重复) | 5个(无重复) | 55% |
| VO文件 | 30个 | 使用Command/Query模式 | - |

## 清理内容

### 1. 删除的重复DDD模块

| 原模块 | 合并到 | 说明 |
|--------|--------|------|
| `account` | `identity` | 账号功能合并到认证领域 |
| `loginlog` | `identity` | 登录日志合并到认证领域 |
| `role` | `authorization` | 角色功能合并到授权领域 |
| `permission` | `authorization` | 权限功能合并到授权领域 |
| `user` | `userprofile` | 用户功能合并到用户档案领域 |
| `department` | `organization` | 部门功能合并到组织领域 |

### 2. 删除的旧架构文件

- `controller/*.java` - 10个旧架构Controller
- `service/*.java` - 11个旧架构Service接口
- `service/impl/*.java` - 11个旧架构Service实现
- `controller/vo/` - 30个VO文件目录

### 3. 删除的空目录

- `config/` - 空配置目录
- `controller/` - 清空后删除

## 最终保留的DDD领域

```
com.ssitao.code.modular.iam
├── dal/                            # 数据访问层（共享）
│   ├── dataobject/                 # 数据对象DO
│   └── mapper/                     # MyBatis Mapper
├── core/                           # 核心配置
│   └── SsitaoStpInterface.java
├── shared/                         # 共享组件
│   ├── config/                     # 缓存配置
│   ├── enums/                      # 枚举类
│   ├── exception/                  # 异常处理
│   ├── mapper/                     # 基础Mapper
│   └── query/                      # 查询相关
├── identity/                       # 认证领域（完整）
│   ├── api/dto/                    # DTO
│   ├── application/                # 应用服务
│   │   ├── command/                # 命令
│   │   ├── query/                  # 查询
│   │   └── service/                # 服务接口
│   ├── domain/                     # 领域层
│   │   ├── event/                  # 领域事件
│   │   ├── model/                  # 聚合根
│   │   └── repository/             # 仓储接口
│   ├── infrastructure/             # 基础设施层
│   │   ├── converter/              # 转换器
│   │   └── repository/             # 仓储实现
│   └── controller/                 # 控制器
├── authorization/                  # 授权领域
│   └── ...                         # 结构同identity
├── userprofile/                    # 用户档案领域
│   └── ...                         # 结构同identity
├── organization/                   # 组织领域
│   └── ...                         # 结构同identity
└── menu/                           # 菜单领域
    └── ...                         # 结构同identity
```

## 各领域现有文件

### Identity 认证领域 (21个文件)
- **Controller**: `IamIdentityAccountController`, `IamIdentityLoginController`
- **Domain Model**: `IamIdentityAccount`, `IamIdentityLoginLog`
- **Domain Event**: 5个事件类
- **Repository**: 2个仓储接口 + 1个实现
- **Application Service**: 2个服务接口
- **Command**: 5个命令类
- **Query**: 2个查询类
- **DTO**: 3个DTO类
- **Converter**: 1个转换器

### Authorization 授权领域 (6个文件)
- **Controller**: `IamAuthorizationRoleController`
- **Domain Model**: 3个聚合根
- **Repository**: 2个仓储接口

### Organization 组织领域 (3个文件)
- **Controller**: `IamOrganizationDepartmentController`
- **Domain Model**: 2个聚合根

### UserProfile 用户档案领域 (2个文件)
- **Controller**: `IamUserProfileController`
- **Domain Model**: 1个聚合根

### Menu 菜单领域 (2个文件)
- **Controller**: `IamMenuHeadMenuController`
- **Domain Model**: 1个聚合根

### DAL 数据访问层 (33个文件)
- **Data Object**: 14个DO文件
- **Mapper**: 19个Mapper文件

### Shared 共享组件 (9个文件)
- **Config**: 2个
- **Enums**: 3个
- **Exception**: 3个
- **Mapper/Query**: 2个

## 命名规范

所有DDD类均使用 `Iam` 前缀：

- **聚合根**：`Iam{Domain}{Aggregate}` （如 `IamIdentityAccount`）
- **仓储**：`Iam{Domain}{Aggregate}Repository`
- **应用服务**：`Iam{Domain}{Aggregate}AppService`
- **控制器**：`Iam{Domain}{Aggregate}Controller`
- **DTO**：`Iam{Domain}{Aggregate}DTO`
- **命令**：`Iam{Domain}{Aggregate}{Action}Command`
- **查询**：`Iam{Domain}{Aggregate}Query`

## API路径规范

- **Identity**: `/iam/identity/account/*`, `/iam/identity/login/*`
- **Authorization**: `/iam/authorization/role/*`
- **UserProfile**: `/iam/userprofile/*`
- **Organization**: `/iam/organization/department/*`
- **Menu**: `/iam/menu/headmenu/*`

## 待完善工作

1. **Authorization领域**：补全应用服务、仓储实现、DTO、Command/Query
2. **UserProfile领域**：补全应用服务、仓储实现、DTO、Command/Query
3. **Organization领域**：补全应用服务、仓储实现、DTO、Command/Query
4. **Menu领域**：补全应用服务、仓储实现、DTO、Command/Query
5. **单元测试**：为各领域编写单元测试
