# IAM模块DDD架构说明

## 概述

IAM模块已按DDD（领域驱动设计）模式重构，并采用按功能模块分包的方式组织代码。

## 包结构设计

```
com.ssitao.code.modular.iam
├── dict/                           # 字典模块（独立功能域）
│   ├── api/                        # 对外接口层
│   │   ├── DictTypeDTO.java        # 字典类型DTO
│   │   └── DictDataDTO.java        # 字典数据DTO
│   ├── application/                # 应用层
│   │   ├── DictTypeAppService.java # 应用服务
│   │   └── command/                # 命令对象
│   ├── domain/                     # 领域层
│   │   ├── model/                  # 领域模型
│   │   │   ├── DictType.java       # 聚合根
│   │   │   └── DictData.java       # 实体
│   │   ├── repository/             # 仓储接口
│   │   └── event/                  # 领域事件
│   ├── infrastructure/             # 基础设施层
│   │   ├── repository/             # 仓储实现
│   │   ├── converter/              # 对象转换器
│   │   └── event/                  # 事件处理器
│   └── controller/                 # 控制器层
│
├── user/                           # 用户模块
│   ├── api/
│   ├── application/
│   ├── domain/
│   ├── infrastructure/
│   └── controller/
│
├── role/                           # 角色模块
│   ├── api/
│   ├── application/
│   ├── domain/
│   ├── infrastructure/
│   └── controller/
│
├── permission/                     # 权限模块
│   ├── api/
│   ├── application/
│   ├── domain/
│   ├── infrastructure/
│   └── controller/
│
├── dept/                           # 部门模块（待实现）
├── post/                           # 岗位模块（待实现）
├── notice/                         # 通知模块（待实现）
│
├── controller/                     # 旧版控制器（兼容）
│   └── vo/                         # 视图对象
│
├── dal/                            # 数据访问层（共享）
│   ├── dataobject/                 # 数据对象
│   └── mapper/                     # MyBatis Mapper
│
├── core/                           # 核心配置（共享）
│   └── SsitaoStpInterface.java
│
└── service/                        # 旧版服务（兼容）
    └── impl/
```

## DDD分层架构

```
┌─────────────────────────────────────────────────────┐
│                   Controller层                      │
│            接收HTTP请求，返回响应                      │
└──────────────────────┬──────────────────────────────┘
                       │ 调用
┌──────────────────────▼──────────────────────────────┐
│                 Application层                        │
│      应用服务 - 编排领域对象，协调业务用例              │
│   - AppService                                       │
│   - Command (命令对象)                               │
└──────────────────────┬──────────────────────────────┘
                       │ 调用
┌──────────────────────▼──────────────────────────────┐
│                   Domain层                          │
│     领域模型 - 封装核心业务逻辑                        │
│   - AggregateRoot (聚合根)                           │
│   - Entity (实体)                                    │
│   - DomainEvent (领域事件)                           │
│   - Repository (仓储接口)                            │
└──────────────────────┬──────────────────────────────┘
                       │ 实现
┌──────────────────────▼──────────────────────────────┐
│              Infrastructure层                        │
│    基础设施 - 数据持久化、外部服务集成                  │
│   - RepositoryImpl (仓储实现)                        │
│   - Converter (对象转换器)                           │
│   - EventHandler (事件处理器)                        │
└──────────────────────┬──────────────────────────────┘
                       │ 操作
┌──────────────────────▼──────────────────────────────┐
│                   数据存储                            │
└─────────────────────────────────────────────────────┘
```

## 模块划分原则

1. **按业务功能分包**: 每个业务模块（dict、user、role、permission等）独立包结构
2. **高内聚低耦合**: 模块内部各层职责清晰，模块间通过API层交互
3. **独立演进**: 各模块可独立开发和维护

## 核心概念

### 聚合根 (AggregateRoot)
- 封装业务规则和不变性条件
- 管理领域生命周期
- 维护聚合内部一致性

示例：`DictType`、`User`、`Role`、`Permission`

### 仓储 (Repository)
- 隔离领域模型与数据访问
- 提供聚合根的持久化操作
- 面向领域模型设计接口

### 领域事件 (DomainEvent)
- 实现最终一致性
- 解耦聚合间依赖
- 支持异步处理

### 应用服务 (ApplicationService)
- 编排业务用例
- 协调多个聚合
- 事务边界控制

## 使用示例

### 字典类型操作

```java
// 创建字典类型
DictTypeCreateCommand command = new DictTypeCreateCommand();
command.setName("用户状态");
command.setType("user_status");
command.setCreator("admin");

Long id = dictTypeAppService.createDictType(command);

// 查询字典类型
DictTypeDTO dto = dictTypeAppService.getDictType(id);
```

### 用户操作

```java
// 创建用户
UserCreateCommand command = new UserCreateCommand();
command.setUsername("zhangsan");
command.setPassword("encrypted_password");
command.setCreator("admin");

Long id = userAppService.createUser(command);
```

## 迁移指南

### 从旧代码迁移到DDD结构

1. **Controller层**: 调用新的应用服务而非旧版Service
2. **Service层**: 业务逻辑移至领域聚合根
3. **新增功能**: 直接使用DDD结构开发

### 兼容性

- 保留旧版`controller`和`service`包结构，确保平滑迁移
- 新旧代码可并存运行
- 逐步迁移现有功能

## 技术栈

- **DDD框架**: aggregate-framework
- **持久化**: MyBatis-Flex
- **验证**: javax.validation
- **日志**: slf4j
- **依赖注入**: Spring

## 后续计划

- [ ] 完成部门(Dept)模块DDD改造
- [ ] 完成岗位(Post)模块DDD改造
- [ ] 完成通知(Notice)模块DDD改造
- [ ] 实现CQRS读写分离
- [ ] 完善事件溯源机制
