# IAM模块包结构说明

## 概述

IAM模块采用按功能模块分包的DDD架构，同时保留部分旧版结构以兼容现有功能。

## 当前包结构

```
com.ssitao.code.modular.iam
│
├── dict/                           # 字典模块（DDD新结构）
│   ├── api/                        # 对外接口DTO
│   │   ├── DictTypeDTO.java
│   │   └── DictDataDTO.java
│   ├── application/                # 应用层
│   │   ├── DictTypeAppService.java
│   │   ├── DictDataAppService.java
│   │   └── command/                # 命令对象
│   ├── domain/                     # 领域层
│   │   ├── model/                  # 聚合根和实体
│   │   │   ├── DictType.java
│   │   │   └── DictData.java
│   │   ├── repository/             # 仓储接口
│   │   └── event/                  # 领域事件
│   ├── infrastructure/             # 基础设施层
│   │   ├── repository/             # 仓储实现
│   │   ├── converter/              # 对象转换器
│   │   └── event/                  # 事件处理器
│   └── controller/                 # 控制器
│       ├── DictTypeController.java
│       └── DictDataController.java
│
├── user/                           # 用户模块（DDD新结构）
│   ├── api/
│   ├── application/
│   │   ├── UserAppService.java
│   │   └── command/
│   ├── domain/
│   │   ├── model/
│   │   │   └── User.java           # 用户聚合根
│   │   ├── repository/
│   │   └── event/
│   ├── infrastructure/
│   │   ├── repository/
│   │   │   └── UserRepositoryImpl.java
│   │   └── converter/
│   │       └── UserConverter.java
│   └── controller/                # 待创建
│
├── role/                           # 角色模块（DDD新结构）
│   ├── api/
│   ├── application/
│   ├── domain/
│   │   ├── model/
│   │   │   └── Role.java           # 角色聚合根
│   │   ├── repository/
│   │   └── event/
│   └── infrastructure/
│
├── permission/                     # 权限模块（DDD新结构）
│   ├── api/
│   ├── application/
│   ├── domain/
│   │   ├── model/
│   │   │   └── Permission.java     # 权限聚合根
│   │   └── repository/
│   └── infrastructure/
│
├── controller/                     # 旧版Controller（兼容保留）
│   ├── account/
│   ├── menu/
│   ├── permission/
│   ├── role/
│   ├── AuthController.java
│   ├── ConfigController.java
│   ├── DeptController.java
│   ├── DictDataController.java
│   ├── FileController.java
│   ├── LoginLogController.java
│   ├── NoticeController.java
│   ├── OnlineUserController.java
│   ├── OperateLogController.java
│   ├── PermissionController.java
│   ├── PostController.java
│   ├── RoleController.java
│   └── UserController.java
│
├── service/                        # 旧版Service（兼容保留）
│   ├── AuthService.java
│   ├── DictDataService.java
│   ├── DictTypeService.java
│   ├── MenuService.java
│   ├── NoticeService.java
│   ├── OnlineUserService.java
│   ├── PermissionService.java
│   ├── PostService.java
│   ├── RoleService.java
│   └── impl/
│
├── dal/                            # 数据访问层（共享）
│   ├── dataobject/                 # 数据对象DO
│   │   ├── UserDO.java
│   │   ├── RoleDO.java
│   │   ├── PermissionDO.java
│   │   └── ...
│   └── mapper/                     # MyBatis Mapper
│       ├── UserMapper.java
│       ├── RoleMapper.java
│       └── ...
│
├── controller/vo/                  # 视图对象（共享）
│   ├── auth/
│   ├── config/
│   ├── dept/
│   ├── dict/
│   ├── log/
│   ├── notice/
│   ├── oauth2/
│   ├── online/
│   ├── permission/
│   ├── post/
│   ├── role/
│   ├── sms/
│   └── user/
│
├── core/                           # 核心配置
│   └── SsitaoStpInterface.java     # Sa-Token权限接口
│
└── shared/                         # 共享组件（新增）
    ├── config/                     # 配置类
    │   ├── CacheConfig.java
    │   └── CaffeineCacheManager.java
    ├── enums/                      # 枚举类
    │   ├── CommonStatus.java
    │   ├── UserGender.java
    │   ├── PermissionType.java
    │   └── RoleType.java
    ├── exception/                  # 异常处理
    │   ├── IamErrorCode.java
    │   ├── IamException.java
    │   └── IamExceptionHandler.java
    └── query/                      # 查询相关
        ├── QueryCriteria.java
        └── BaseQuery.java
```

## 迁移指南

### 新功能开发

新功能应采用DDD结构，在对应的模块目录下开发：

1. 在 `{module}/domain/model/` 创建聚合根
2. 在 `{module}/domain/repository/` 创建仓储接口
3. 在 `{module}/application/` 创建应用服务
4. 在 `{module}/infrastructure/repository/` 实现仓储
5. 在 `{module}/controller/` 创建控制器

### 旧功能迁移

将旧功能迁移到DDD结构：

1. 在对应模块下创建DDD分层结构
2. 将业务逻辑从Service移至领域模型
3. 更新Controller调用新的应用服务
4. 逐步废弃旧的Service实现

## 注意事项

1. **dal目录**：作为数据访问层，所有模块共享，包含DO和Mapper
2. **controller/vo目录**：VO对象共享使用，不按模块拆分
3. **shared目录**：存放跨模块共享的组件，如枚举、异常、工具类等
4. **旧版controller和service**：保留兼容，逐步迁移到新结构
