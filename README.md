# SSITAO Code Platform

基于 jecloud 源码分析，使用 Spring Boot 2.7 + MyBatis-Flex 构建的企业级微服务开发平台。

## 项目概述

本项目是对 jecloud 微服务平台的现代化重构，保留了原有架构的核心功能，同时采用了更先进的技术栈：

- **Spring Boot 2.7.18** - 稳定的企业级框架
- **MyBatis-Flex 1.9.5** - 新一代 MyBatis 增强框架
- **Spring Cloud Gateway** - 替代 ServiceComb 网关
- **JWT + Spring Security** - 认证授权
- **Redis** - 分布式缓存

## 项目结构

```
ssitao-code-platform/
├── ssitao-code-dependencies/      # 依赖管理
├── ssitao-code-parents/           # 父模块
├── ssitao-code-commons/           # 公共模块
│   ├── entity/                   # 基础实体类
│   ├── result/                   # 统一响应结果
│   ├── constant/                 # 常量定义
│   ├── util/                     # 工具类
│   └── exception/                # 异常处理
├── ssitao-code-frame/             # 框架核心
│   └── ssitao-code-mybatis-easy/ # MyBatis 增强
├── ssitao-code-modulars/          # 业务模块
│   ├── ssitao-code-modular-iam/  # 身份认证与授权 (IAM)
│   ├── ssitao-code-modular-meta/ # 元数据管理
│   ├── ssitao-code-modular-common/ # 模块公共组件
│   └── ssitao-code-modular-devtools/ # 开发工具
├── ssitao-code-boot/              # 启动模块
└── ssitao-code-boot-starters/     # Starter 模块
```

## 核心功能

### 1. 公共模块 (ssitao-code-commons)

#### 基础实体类
- `BaseEntity` - 基础实体，包含通用字段（创建人、修改人、审核状态等）
- `TreeBaseEntity` - 树形实体基类

#### 工具类
- `StringUtil` - 字符串工具类（空判断、格式转换、URL编码等）
- `DateUtils` - 日期工具类（格式化、计算、转换等）

#### 统一响应
- `Result<T>` - 统一响应结果封装

#### 异常处理
- `BusinessException` - 业务异常
- `UnauthorizedException` - 未授权异常
- `ForbiddenException` - 禁止访问异常
- `NotFoundException` - 资源未找到异常
- `GlobalExceptionHandler` - 全局异常处理器

### 2. 认证授权模块 (ssitao-code-modular-iam)

#### JWT 认证
- `JwtUtils` - JWT Token 生成、解析、验证工具类
- 支持访问令牌和刷新令牌

#### 认证服务
- `AuthService` - 认证服务接口
- `AuthServiceImpl` - 认证服务实现
- `AuthController` - 认证控制器
- `AuthInterceptor` - 认证拦截器

#### 登录模型
- `LoginRequest` - 登录请求
- `LoginResponse` - 登录响应
- `LoginUser` - 登录用户信息

#### RBAC 权限管理

**实体类**
- `User` - 用户实体
- `Role` - 角色实体
- `Permission` - 权限实体（菜单/按钮）
- `Department` - 部门实体
- `UserRole` - 用户角色关联
- `RolePermission` - 角色权限关联

**服务接口**
- `UserService` - 用户服务
- `RoleService` - 角色服务
- `PermissionService` - 权限服务

**权限注解**
- `@HasPermission` - 权限校验注解
- `@HasRole` - 角色校验注解
- `PermissionAspect` - 权限校验切面

**控制器**
- `UserController` - 用户管理接口

### 3. 网关模块

#### 网关过滤器
- `AuthFilter` - 认证过滤器，验证 Token 并传递用户信息

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.7.18 | 应用框架 |
| Spring Cloud | 2021.0.8 | 微服务框架 |
| MyBatis-Flex | 1.9.5 | ORM 框架 |
| Druid | 1.2.25 | 数据库连接池 |
| JWT | 0.12.3 | Token 生成 |
| Hutool | 5.8.25 | 工具类库 |
| FastJSON2 | 2.0.52 | JSON 处理 |
| Knife4j | 4.5.0 | API 文档 |

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 5.7+
- Redis 6.0+

### 编译运行

```bash
# 编译项目
mvn clean install

# 运行启动类
java -jar ssitao-code-boot/target/ssitao-code-boot.jar
```

### 配置说明

主要配置文件位于 `ssitao-code-boot/src/main/resources/application.yml`

## 开发指南

### 实体类定义

继承 `BaseEntity`，使用 MyBatis-Flex 注解：

```java
@Data
@EqualsAndHashCode(callSuper = true)
@Table("table_name")
public class MyEntity extends BaseEntity {
    @Id(keyType = KeyType.Auto)
    private String id;
    // 其他字段...
}
```

### 权限控制

使用注解进行权限控制：

```java
// 需要 user:view 权限
@HasPermission("user:view")
public Result<User> getById(String id) {
    // ...
}

// 需要 admin 或 manager 角色
@HasRole(value = {"admin", "manager"}, logic = LogicType.OR)
public Result<Void> delete(String id) {
    // ...
}
```

### 获取当前用户

```java
// 在 Controller 中
HttpServletRequest request = ...;
String userId = (String) request.getAttribute("userId");
String userCode = (String) request.getAttribute("userCode");
```

## 从 jecloud 迁移的主要变化

1. **ORM 框架**: MyBatis iBatis 扩展 → MyBatis-Flex
2. **服务治理**: Apache ServiceComb → Spring Cloud
3. **网关**: ServiceComb Edge → Spring Cloud Gateway
4. **配置中心**: Apollo → Nacos (可选)
5. **代码规范**: 统一代码风格和包结构

## 待完善功能

以下功能已规划但尚未完成实现：

- [ ] 元数据模块完整实现
- [ ] 工作流引擎
- [ ] BPM 业务流程
- [ ] 消息推送服务
- [ ] 定时任务管理
- [ ] 文档管理
- [ ] 第三方登录集成（钉钉、微信、飞书）
- [ ] 数据权限控制
- [ ] 多租户支持
- [ ] 操作日志

## 许可证

[待定]

## 联系方式

- 项目地址: [GitHub](https://github.com/your-org/ssitao-code-platform)
- 问题反馈: [Issues](https://github.com/your-org/ssitao-code-platform/issues)
