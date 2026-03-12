# 日志配置说明

## 概述

本项目使用 Logback 作为日志框架，通过 `logback-spring.xml` 配置文件实现不同环境的日志隔离。

## 日志文件

| 日志文件 | 说明 | 保留时间 | 大小限制 |
|---------|------|----------|----------|
| tweb-lite-all.log | 所有日志 | 30天 | 10GB |
| tweb-lite-error.log | 错误日志 | 60天 | 5GB |
| tweb-lite-sql.log | SQL日志 | 7天 | 2GB |
| tweb-lite-business.log | 业务日志 | 30天 | 10GB |
| tweb-lite-performance.log | 性能日志 | 7天 | 2GB |

## 环境配置

### 开发环境 (dev)

**启动方式：**
```bash
java -jar tweb-lite-1.1.1.jar --spring.profiles.active=dev
```

**日志特性：**
- 控制台彩色输出
- 日志级别：DEBUG（业务代码）、INFO（框架）
- SQL 日志：输出到控制台和文件
- 业务日志：输出到控制台和文件
- 性能日志：输出到控制台和文件

### 测试环境 (test)

**启动方式：**
```bash
java -jar tweb-lite-1.1.1.jar --spring.profiles.active=test
```

**日志特性：**
- 控制台输出
- 日志级别：DEBUG（业务代码）、INFO（框架）
- SQL 日志：输出到控制台和文件
- 业务日志：仅输出到文件
- 性能日志：仅输出到文件
- 慢SQL阈值：500ms

### 生产环境 (prod)

**启动方式：**
```bash
java -jar tweb-lite-1.1.1.jar --spring.profiles.active=prod
```

**日志特性：**
- 控制台不输出
- 日志级别：INFO（业务代码）、WARN（框架）
- SQL 日志：仅记录 WARN 级别
- 业务日志：仅输出到文件
- 性能日志：仅输出到文件
- 使用异步日志提升性能
- 慢操作阈值：1000ms

## 日志级别

| 级别 | 描述 | 使用场景 |
|------|------|----------|
| TRACE | 最详细 | 问题诊断 |
| DEBUG | 调试信息 | 开发、测试 |
| INFO | 一般信息 | 生产环境默认 |
| WARN | 警告信息 | 需要注意的情况 |
| ERROR | 错误信息 | 异常和错误 |
| FATAL | 致命错误 | 系统级错误 |

## 使用示例

### 1. 基础日志记录

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void createUser(User user) {
        logger.debug("Creating user: {}", user.getUsername());
        // 业务逻辑
        logger.info("User created successfully: {}", user.getId());
    }
}
```

### 2. 使用 @BusinessLog 注解

```java
import com.tweb.lite.annotation.BusinessLog;

@BusinessLog(
    module = "用户管理",
    operation = "新增用户",
    description = "创建新用户",
    logParams = true,
    logResult = true,
    costThreshold = 500
)
public Long createUser(UserDto dto) {
    // 业务逻辑
    return userId;
}
```

### 3. 使用 LoggerUtil

```java
import com.tweb.lite.util.LoggerUtil;

// 记录业务日志
LoggerUtil.logBusiness("用户模块", "用户登录", "username=admin", "ip=127.0.0.1");

// 记录性能日志
LoggerUtil.logPerformance("批量导入", () -> {
    return importUsers(file);
});

// 条件日志
LoggerUtil.logIf(logger,.isDebugEnabled(), () -> "Debug info: " + expensiveOperation());
```

## 日志配置文件

- `logback-spring.xml` - Logback 主配置文件
- `application-dev.yml` - 开发环境配置
- `application-test.yml` - 测试环境配置
- `application-prod.yml` - 生产环境配置
