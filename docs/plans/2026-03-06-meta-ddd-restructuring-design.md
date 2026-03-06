# Meta 模块 DDD 重构设计文档

## 1. 需求概述

将 Meta 模块重构为与 IAM 模块一致的 DDD 架构，使用仓储模式（Repository Pattern）解耦数据访问层。

## 2. 现状分析

### 当前结构
- `domain/model` - 领域模型 ✅
- `domain/repository` - 仓储接口（部分）
- `infrastructure/repository` - 仓储实现（部分）
- `dal/dataobject` - 数据对象 ✅
- `dal/mapper` - 数据映射器 ✅

### 问题
1. 部分 Service 直接使用 Mapper 而非 Repository
2. 缺少部分 Repository 接口和实现类
3. 代码结构与 IAM 模块不一致

## 3. 目标结构

与 IAM 模块完全对齐：

```
meta/
├── api/
│   └── dto/              # API 传输对象
├── application/
│   ├── command/          # 命令对象
│   ├── query/            # 查询对象
│   └── service/          # 应用服务接口
│       └── impl/          # 应用服务实现
├── domain/
│   ├── model/             # 领域模型
│   └── repository/       # 仓储接口
├── infrastructure/
│   ├── converter/        # 对象转换器
│   └── repository/        # 仓储实现
├── dal/
│   ├── dataobject/        # 数据对象
│   └── mapper/            # 数据映射器
└── controller/            # 控制器
```

## 4. 重构内容

### 4.1 需要创建/修复的 Repository

| 领域 | Repository 接口 | Repository 实现 | 状态 |
|------|-----------------|-----------------|------|
| Entity | MetaEntityRepository | MetaEntityRepositoryImpl | 需创建 |
| Table | MetaTableRepository | MetaTableRepositoryImpl | 需修复 |
| Column | MetaColumnRepository | MetaColumnRepositoryImpl | 已有 |
| Form | MetaFormRepository | MetaFormRepositoryImpl | 已有 |
| List | MetaListRepository | MetaListRepositoryImpl | 已有 |
| Workflow | MetaWorkflowRepository | MetaWorkflowRepositoryImpl | 需修复 |

### 4.2 需要改造的 Service

| Service | 当前使用 | 改造后使用 |
|---------|---------|-----------|
| MetaEntityAppService | MetaEntityMapper | MetaEntityRepository |
| MetaTableAppService | MetaTableMapper | MetaTableRepository |
| MetaColumnAppService | MetaColumnRepository | (已使用) |
| MetaFormAppService | MetaFormRepository | (已使用) |
| MetaListAppService | MetaListRepository | (已使用) |
| WorkflowService | - | MetaWorkflowRepository |

### 4.3 关键设计

**仓储接口设计（参考 IAM）:**
```java
public interface MetaEntityRepository {
    String save(MetaEntity entity);
    void update(MetaEntity entity);
    void delete(String id);
    MetaEntity findById(String id);
    List<MetaEntity> findAll(String tenantId);
    boolean existsByCode(String entityCode, String tenantId, String excludeId);
}
```

**应用服务改造:**
- 使用 @Resource 注入 Repository
- 领域对象与 DO 对象通过 Converter 转换
- 事务管理在应用服务层

## 5. 接口设计

### 5.1 Entity 领域

**MetaEntityRepository:**
- `save(entity)` - 保存实体
- `update(entity)` - 更新实体
- `delete(id)` - 删除实体
- `findById(id)` - 根据ID查询
- `findAll(tenantId)` - 查询所有
- `existsByCode(code, tenantId, excludeId)` - 检查编码是否存在

### 5.2 Table 领域

**MetaTableRepository:**
- `save(table)` - 保存表配置
- `update(table)` - 更新表配置
- `delete(id)` - 删除表配置
- `findById(id)` - 根据ID查询
- `findByEntityId(entityId)` - 根据实体ID查询

### 5.3 Workflow 领域

**MetaWorkflowRepository:**
- `save(workflow)` - 保存流程
- `update(workflow)` - 更新流程
- `delete(id)` - 删除流程
- `findById(id)` - 根据ID查询
- `findByEntityId(entityId)` - 根据实体ID查询

## 6. 测试验证

- 编译检查通过
- 启动应用无 Bean 注入错误
- 现有功能正常
