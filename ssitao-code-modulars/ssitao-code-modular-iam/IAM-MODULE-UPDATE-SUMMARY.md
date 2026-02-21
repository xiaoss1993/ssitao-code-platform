# IAM模块DDD架构更新总结

## 更新时间
2026-02-20

## 更新内容

### 1. Controller迁移完成

已将顶层Controller迁移到对应的DDD模块目录：

| 模块 | Controller | 应用服务 | 说明 |
|------|-----------|---------|------|
| user | UserController | UserAppService | 用户管理，包含CRUD、状态管理、密码管理、角色分配 |
| role | RoleController | RoleAppService | 角色管理，包含CRUD、权限分配 |
| permission | PermissionController | PermissionAppService | 权限管理，包含CRUD、树形结构查询 |
| dict | DictDataController | DictDataAppService | 字典数据管理，按类型查询 |
| dict | DictTypeController | DictTypeAppService | 字典类型管理 |

### 2. 修复的问题

#### 2.1 PermissionAppService - buildTree方法修复
**问题**: 原方法使用peek尝试修改DTO，但实际返回的是新的DTO对象
**修复**:
```java
// 修复前
.peek(p -> {
    PermissionDTO dto = toDTO(p);
    dto.setChildren(buildTree(permissions, p.getId()));
})
.map(this::toDTO)

// 修复后
.map(p -> {
    PermissionDTO dto = toDTO(p);
    dto.setChildren(buildTree(permissions, p.getId()));
    return dto;
})
```

#### 2.2 PermissionDTO - 添加children字段
**问题**: DTO缺少children属性，无法构建树形结构
**修复**: 添加`private List<PermissionDTO> children;`字段

#### 2.3 DictDataAppService - 类型错误修复
**问题**: 第126行将DictType赋值给DictData类型变量
**修复**: 使用完全限定名区分两个同名类
```java
// 修复前
DictData dictType = dictTypeOptional.get();

// 修复后
com.ssitao.code.modular.iam.dict.domain.model.DictType dictTypeModel = dictTypeOptional.get();
```

#### 2.4 DictDataAppService - existsById方法不存在
**问题**: DictTypeRepository没有existsById方法
**修复**: 使用findById().isEmpty()代替
```java
// 修复前
if (!dictTypeRepository.existsById(command.getDictTypeId()))

// 修复后
if (dictTypeRepository.findById(command.getDictTypeId()).isEmpty())
```

### 3. 模块结构

```
iam/
├── dict/              # 字典模块
│   ├── api/           # DTO
│   │   ├── DictDataDTO.java
│   │   └── DictTypeDTO.java
│   ├── application/   # 应用服务
│   │   ├── DictDataAppService.java
│   │   ├── DictTypeAppService.java
│   │   └── command/
│   ├── controller/    # 控制器
│   │   ├── DictDataController.java
│   │   └── DictTypeController.java
│   ├── domain/        # 领域模型
│   │   ├── model/
│   │   ├── repository/
│   │   └── event/
│   └── infrastructure/ # 基础设施
│
├── user/              # 用户模块
│   ├── api/
│   │   └── UserDTO.java
│   ├── application/
│   │   ├── UserAppService.java
│   │   └── command/
│   ├── controller/
│   │   └── UserController.java
│   ├── domain/
│   └── infrastructure/
│
├── role/              # 角色模块
│   ├── api/
│   │   └── RoleDTO.java
│   ├── application/
│   │   └── RoleAppService.java
│   ├── controller/
│   │   └── RoleController.java
│   ├── domain/
│   └── infrastructure/
│
├── permission/        # 权限模块
│   ├── api/
│   │   └── PermissionDTO.java
│   ├── application/
│   │   └── PermissionAppService.java
│   ├── controller/
│   │   └── PermissionController.java
│   ├── domain/
│   └── infrastructure/
│
└── shared/            # 共享组件
    ├── config/       # 配置（缓存等）
    ├── enums/        # 枚举
    ├── exception/    # 异常处理
    └── util/         # 工具类
```

### 4. 已实现的完整功能

#### UserAppService
- createUser - 创建用户
- updateUser - 更新用户
- deleteUser - 删除用户
- getUser - 根据ID获取用户（带缓存）
- getUserByUsername - 根据用户名获取用户（带缓存）
- getUserList - 获取用户列表
- enableUser - 启用用户
- disableUser - 禁用用户
- changePassword - 修改密码
- resetPassword - 重置密码
- assignRoles - 分配角色
- clearUserCache - 清除用户缓存

#### RoleAppService
- createRole - 创建角色
- updateRole - 更新角色
- deleteRole - 删除角色
- getRole - 获取角色
- getRoleList - 获取角色列表
- assignPermissions - 分配权限

#### PermissionAppService
- createPermission - 创建权限
- updatePermission - 更新权限
- deletePermission - 删除权限
- getPermission - 获取权限
- getPermissionList - 获取权限列表
- getPermissionTree - 获取权限树

#### DictTypeAppService
- createDictType - 创建字典类型
- updateDictType - 更新字典类型
- deleteDictType - 删除字典类型
- getDictType - 获取字典类型
- getDictTypeList - 获取字典类型列表

#### DictDataAppService
- createDictData - 创建字典数据
- updateDictData - 更新字典数据
- deleteDictData - 删除字典数据
- getDictData - 获取字典数据
- getDictDataList - 获取字典数据列表
- getDictDataByType - 根据类型获取字典数据

### 5. 共享组件

#### CacheConfig - 缓存配置
- USER_CACHE - 用户缓存
- ROLE_CACHE - 角色缓存
- PERMISSION_CACHE - 权限缓存
- DICT_CACHE - 字典缓存
- USER_PERMISSION_CACHE - 用户权限缓存

#### 枚举类
- CommonStatus - 通用状态（启用/禁用）
- UserGender - 用户性别
- PermissionType - 权限类型
- RoleType - 角色类型

#### 异常处理
- IamErrorCode - 错误码枚举
- IamException - IAM业务异常
- IamExceptionHandler - 全局异常处理器

### 6. 待完成功能

以下模块需要创建完整的DDD结构：
- dept - 部门模块
- post - 岗位模块
- auth - 认证模块
- log - 日志模块（登录日志、操作日志）
- notice - 通知模块
- file - 文件模块

### 7. API路径

| 模块 | 基础路径 | 说明 |
|------|---------|------|
| user | /iam/user | 用户管理 |
| role | /api/role | 角色管理 |
| permission | /api/permission | 权限管理 |
| dict | /iam/dict-data | 字典数据 |
| dict | /iam/dict-type | 字典类型 |

### 8. 缓存策略

- 使用Caffeine作为本地缓存
- 用户信息缓存30分钟
- 支持按ID和用户名缓存
- 更新/删除操作自动清除缓存

### 9. 领域事件

已实现的领域事件：
- UserCreatedEvent, UserUpdatedEvent, UserDeletedEvent
- RoleCreatedEvent, RoleUpdatedEvent, RoleDeletedEvent
- DictTypeCreatedEvent, DictTypeUpdatedEvent, DictTypeDeletedEvent
- PermissionCreatedEvent, PermissionUpdatedEvent, PermissionDeletedEvent

### 10. 数据校验

- 使用javax.validation进行参数校验
- 领域层进行业务规则校验
- 统一异常处理
