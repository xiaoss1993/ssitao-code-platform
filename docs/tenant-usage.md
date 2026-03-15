# 租户ID处理方案

## 问题描述

原代码中所有 Controller 都硬编码了 `defaultValue = "default"` 来处理 tenantId，没有根据租户配置 `TenantProperties.enabled` 来处理。

## 解决方案

提供了三种方式来获取租户ID：

### 方式一：使用 @CurrentTenantId 注解（推荐）

```java
@RestController
@RequestMapping("/iam/authorization")
public class IamAuthorizationController {

    @PutMapping("/role/{id}/disable")
    @Operation(summary = "禁用角色", description = "禁用指定角色")
    public CommonResult<Void> disableRole(@PathVariable String id,
                                          @CurrentTenantId String tenantId) {
        roleAppService.disableRole(id, tenantId);
        return success();
    }
}
```

### 方式二：使用 TenantUtils 工具类

```java
@RestController
@RequestMapping("/iam/authorization")
public class IamAuthorizationController {

    @PutMapping("/role/{id}/disable")
    @Operation(summary = "禁用角色", description = "禁用指定角色")
    public CommonResult<Void> disableRole(@PathVariable String id) {
        String tenantId = TenantUtils.getTenantId();
        roleAppService.disableRole(id, tenantId);
        return success();
    }
}
```

### 方式三：允许为空的租户ID

如果需要在租户功能禁用时传入 null：

```java
@PutMapping("/role/{id}/disable")
public CommonResult<Void> disableRole(@PathVariable String id,
                                      @CurrentTenantId(required = false) String tenantId) {
    // 当租户功能未启用时，tenantId 为 null
    roleAppService.disableRole(id, tenantId);
    return success();
}
```

## 处理逻辑

`TenantUtils.getTenantId()` 的处理逻辑：

1. **检查租户功能是否启用**
   - 如果 `tenantProperties.enabled == false`，返回 `null`

2. **从上下文获取租户ID**
   - 从 `TenantContextHolder` 获取当前请求的租户ID
   - 如果存在且不为空，直接返回

3. **使用默认租户ID**
   - 如果上下文中没有租户ID，返回配置的 `defaultTenantId`（默认为 "0"）

## 配置示例

```yaml
ssitao:
  tenant:
    enabled: true                      # 是否启用多租户
    header-name: Tenant-Id             # 请求头名称
    param-name: tenantId               # 请求参数名称
    default-tenant-id: "0"             # 默认租户ID
    ignore-tables:                     # 忽略租户隔离的表
      - iam_tenant
      - iam_permission
    ignore-urls:                       # 忽略租户隔离的URL
      - /public/*
```

## 迁移指南

将原有的代码：

```java
// 修改前
@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId
```

替换为：

```java
// 修改后（方式一：推荐）
@CurrentTenantId String tenantId

// 或者（方式二）
// 在方法体中使用：String tenantId = TenantUtils.getTenantId();
```

## 注意事项

1. **租户功能禁用时**：`TenantUtils.getTenantId()` 返回 `null`，Service 层需要处理 null 的情况
2. **参数解析器**：`@CurrentTenantId` 注解的参数解析器会自动注册，无需手动配置
3. **请求头处理**：`TenantContextWebFilter` 会自动从请求头或参数中提取租户ID并设置到上下文中
