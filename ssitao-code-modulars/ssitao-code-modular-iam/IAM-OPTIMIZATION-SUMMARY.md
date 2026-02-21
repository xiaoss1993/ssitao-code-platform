# IAM模块代码优化改进总结

## 一、优化概览

本次对IAM模块进行了全面的代码优化和改进，主要涉及异常处理、缓存支持、性能优化、代码质量提升等方面。

## 二、优化内容

### 1. 异常处理机制完善

#### 1.1 统一错误码定义

创建了 `IamErrorCode` 枚举类，统一管理IAM模块的错误码：

```java
public enum IamErrorCode {
    // 通用错误 10xxx
    SUCCESS(0, "操作成功"),
    SYSTEM_ERROR(10000, "系统异常"),
    PARAM_ERROR(10001, "参数错误"),

    // 用户相关错误 11xxx
    USER_NOT_FOUND(11001, "用户不存在"),
    USER_ALREADY_EXISTS(11002, "用户名已存在"),
    USER_PASSWORD_ERROR(11003, "密码错误"),
    USER_DISABLED(11004, "用户已被禁用"),

    // 角色相关错误 12xxx
    ROLE_NOT_FOUND(12001, "角色不存在"),
    ROLE_CANNOT_DELETE(12003, "系统角色不能删除"),

    // 权限相关错误 13xxx
    PERMISSION_NOT_FOUND(13001, "权限不存在"),
    NO_PERMISSION(13004, "无权限访问"),

    // ... 更多错误码
}
```

**位置**: `shared/exception/IamErrorCode.java`

#### 1.2 领域异常类

创建了 `IamException` 领域异常类：

```java
public class IamException extends RuntimeException {
    private final int code;

    public IamException(IamErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }
}
```

**位置**: `shared/exception/IamException.java`

#### 1.3 全局异常处理器

创建了 `IamExceptionHandler` 全局异常处理器，统一处理各种异常：

- IAM业务异常
- 参数校验异常
- 系统异常

**位置**: `shared/exception/IamExceptionHandler.java`

### 2. 消除硬编码 - 创建枚举类

#### 2.1 通用状态枚举

```java
public enum CommonStatus {
    ENABLED(1, "启用"),
    DISABLED(0, "禁用");
}
```

#### 2.2 用户性别枚举

```java
public enum UserGender {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");
}
```

#### 2.3 权限类型枚举

```java
public enum PermissionType {
    DIRECTORY(1, "目录"),
    MENU(2, "菜单"),
    BUTTON(3, "按钮");
}
```

#### 2.4 角色类型枚举

```java
public enum RoleType {
    SYSTEM(1, "系统角色"),
    CUSTOM(2, "自定义角色");
}
```

**位置**: `shared/enums/`

### 3. 领域模型优化

#### 3.1 用户聚合根增强

增强了 `User` 聚合根，添加以下功能：

1. **参数校验**：在领域层进行参数校验
   - 用户名长度校验
   - 邮箱格式校验
   - 手机号格式校验

2. **业务规则封装**：
   - `changePassword()` - 修改密码（验证旧密码）
   - `resetPassword()` - 重置密码（管理员操作）
   - `verifyPassword()` - 验证密码
   - `assignRoles()` - 分配角色
   - `addRole()` / `removeRole()` - 增删角色

3. **状态断言**：
   - `assertNotDeleted()` - 断言用户未删除
   - `assertEnabled()` - 断言用户已启用

**位置**: `user/domain/model/User.java`

### 4. 缓存支持

#### 4.1 缓存配置

创建了 `CacheConfig` 配置类，定义多种缓存：

```java
public static final String USER_CACHE = "user";
public static final String ROLE_CACHE = "role";
public static final String PERMISSION_CACHE = "permission";
public static final String DICT_CACHE = "dict";
public static final String USER_PERMISSION_CACHE = "userPermission";
```

缓存配置：
- 用户缓存：30分钟过期，最大1000条
- 角色缓存：1小时过期，最大100条
- 权限缓存：1小时过期，最大500条
- 字典缓存：2小时过期，最大200条
- 用户权限缓存：15分钟过期，最大5000条

**位置**: `shared/config/CacheConfig.java`

#### 4.2 缓存管理器

创建了 `CaffeineCacheManager` 提供更灵活的缓存操作。

**位置**: `shared/config/CaffeineCacheManager.java`

#### 4.3 应用服务缓存注解

在 `UserAppService` 中添加缓存注解：

```java
@Cacheable(value = CacheConfig.USER_CACHE, key = "#id")
public UserDTO getUser(Long id) { ... }

@CacheEvict(value = CacheConfig.USER_CACHE, key = "#id")
public void updateUser(UserUpdateCommand command) { ... }
```

### 5. 查询性能优化

#### 5.1 查询规约（QueryCriteria）

创建了 `QueryCriteria` 类，支持链式调用构建查询条件：

```java
QueryCriteria criteria = new QueryCriteria()
    .eq("status", 1)
    .like("username", "admin")
    .in("deptId", 1L, 2L, 3L);
```

**位置**: `shared/query/QueryCriteria.java`

#### 5.2 基础查询类

创建了 `BaseQuery` 基础查询类，封装通用查询逻辑。

**位置**: `shared/query/BaseQuery.java`

#### 5.3 仓储接口扩展

扩展了 `UserRepository` 接口，添加条件查询方法：

```java
List<User> findByConditions(UserQueryCommand command);
List<User> findByConditions(UserQueryCommand command, int pageNum, int pageSize);
long countByConditions(UserQueryCommand command);
```

### 6. 文件组织

新增共享组件包结构：

```
shared/
├── config/              # 配置类
│   ├── CacheConfig.java
│   └── CaffeineCacheManager.java
├── enums/               # 枚举类
│   ├── CommonStatus.java
│   ├── UserGender.java
│   ├── PermissionType.java
│   └── RoleType.java
├── exception/           # 异常类
│   ├── IamErrorCode.java
│   ├── IamException.java
│   └── IamExceptionHandler.java
└── query/               # 查询相关
    ├── QueryCriteria.java
    └── BaseQuery.java
```

## 三、优化效果

### 3.1 代码质量提升

| 优化项 | 优化前 | 优化后 |
|--------|--------|--------|
| 硬编码数量 | 大量（status=1等） | 使用枚举 |
| 异常处理 | 不统一 | 统一错误码和异常处理器 |
| 参数校验 | 分散在Service层 | 封装在领域层 |
| 代码复用 | 重复代码多 | 提取共享组件 |

### 3.2 性能提升

| 优化项 | 优化前 | 优化后 |
|--------|--------|--------|
| 用户查询 | 每次查数据库 | 带缓存，30分钟有效期 |
| 权限验证 | 每次查数据库 | 带缓存，15分钟有效期 |
| 列表查询 | 全量加载后内存过滤 | 数据库层条件查询 |

### 3.3 安全性提升

| 优化项 | 说明 |
|--------|------|
| 密码校验 | 增加旧密码验证 |
| 用户名校验 | 增加格式和长度校验 |
| 邮箱/手机校验 | 增加格式校验 |
| 状态检查 | 操作前检查用户状态 |

## 四、使用示例

### 4.1 使用异常处理

```java
// 抛出业务异常
throw new IamException(IamErrorCode.USER_NOT_FOUND);

// 抛出带自定义消息的异常
throw new IamException(IamErrorCode.PARAM_ERROR, "用户名不能为空");
```

### 4.2 使用枚举

```java
// 使用状态枚举
user.setStatus(CommonStatus.ENABLED.getCode());
if (CommonStatus.ENABLED.getCode().equals(user.getStatus())) {
    // 处理启用状态
}

// 使用性别枚举
UserGender gender = UserGender.fromCode(user.getGender());
```

### 4.3 使用缓存

```java
// 应用服务方法会自动处理缓存
UserDTO user = userAppService.getUser(userId); // 从缓存获取

// 更新操作会自动清除缓存
userAppService.updateUser(command); // 自动清除对应缓存

// 手动清除缓存
userAppService.clearUserCache(userId);
userAppService.clearAllUserCache();
```

## 五、后续优化建议

1. **批量操作优化**：实现批量插入、批量更新的底层优化
2. **分布式缓存**：将本地缓存升级为Redis缓存
3. **事件溯源**：实现完整的事件溯源机制
4. **CQRS模式**：实现读写分离的查询模型
5. **监控告警**：添加性能监控和异常告警
