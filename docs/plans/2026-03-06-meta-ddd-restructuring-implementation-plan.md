# Meta 模块 DDD 重构实现计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 将 Meta 模块重构为与 IAM 模块一致的 DDD 架构，使用仓储模式解耦数据访问层

**Architecture:** 采用 DDD 分层架构，应用服务层调用领域仓储接口，仓储实现层负责数据持久化，通过 Converter 进行对象转换

**Tech Stack:** Spring Boot, Mybatis-Flex, DDD 仓储模式

---

## 阶段一：修复 Entity 领域

### Task 1: 创建 MetaEntityRepository 仓储接口

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/MetaEntityRepository.java`

**Step 1: 创建接口**

```java
package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;

import java.util.List;
import java.util.Optional;

/**
 * 元数据实体仓储接口
 *
 * @author ssitao-code
 */
public interface MetaEntityRepository {

    /**
     * 保存实体
     *
     * @param entity 实体
     * @return 实体ID
     */
    String save(MetaEntity entity);

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    void update(MetaEntity entity);

    /**
     * 删除实体
     *
     * @param id 实体ID
     * @param tenantId 租户ID
     */
    void delete(String id, String tenantId);

    /**
     * 根据ID查询
     *
     * @param id 实体ID
     * @param tenantId 租户ID
     * @return 实体
     */
    Optional<MetaEntity> findById(String id, String tenantId);

    /**
     * 查询所有
     *
     * @param tenantId 租户ID
     * @return 实体列表
     */
    List<MetaEntity> findAll(String tenantId);

    /**
     * 检查编码是否存在
     *
     * @param entityCode 实体编码
     * @param tenantId 租户ID
     * @param excludeId 排除的ID
     * @return 是否存在
     */
    boolean existsByCode(String entityCode, String tenantId, String excludeId);
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/MetaEntityRepository.java
git commit -m "feat(meta): add MetaEntityRepository interface"
```

---

### Task 2: 创建 MetaEntityRepositoryImpl 仓储实现

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaEntityRepositoryImpl.java`

**Step 1: 创建实现类**

```java
package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaEntityDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaEntityMapper;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.repository.MetaEntityRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaEntityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 元数据实体仓储实现
 *
 * @author ssitao-code
 */
@Repository
@RequiredArgsConstructor
public class MetaEntityRepositoryImpl implements MetaEntityRepository {

    private final MetaEntityMapper metaEntityMapper;

    @Override
    @Transactional
    public String save(MetaEntity entity) {
        MetaEntityDO metaEntityDO = MetaEntityConverter.INSTANCE.toDO(entity);
        metaEntityMapper.insert(metaEntityDO);
        return metaEntityDO.getId();
    }

    @Override
    @Transactional
    public void update(MetaEntity entity) {
        MetaEntityDO metaEntityDO = MetaEntityConverter.INSTANCE.toDO(entity);
        metaEntityMapper.update(metaEntityDO);
    }

    @Override
    @Transactional
    public void delete(String id, String tenantId) {
        MetaEntityDO entity = new MetaEntityDO();
        entity.setId(id);
        entity.setIsDeleted(1);
        // 使用 QueryWrapper 构建条件
    }

    @Override
    public Optional<MetaEntity> findById(String id, String tenantId) {
        MetaEntityDO metaEntityDO = metaEntityMapper.selectOneById(id);
        if (metaEntityDO == null) {
            return Optional.empty();
        }
        return Optional.of(MetaEntityConverter.INSTANCE.toDomain(metaEntityDO));
    }

    @Override
    public List<MetaEntity> findAll(String tenantId) {
        List<MetaEntityDO> entities = metaEntityMapper.selectAll();
        return entities.stream()
                .map(MetaEntityConverter.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCode(String entityCode, String tenantId, String excludeId) {
        // 实现exists逻辑
        return false;
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaEntityRepositoryImpl.java
git commit -m "feat(meta): add MetaEntityRepositoryImpl"
```

---

### Task 3: 改造 MetaEntityAppServiceImpl 使用 Repository

**Files:**
- Modify: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/impl/MetaEntityAppServiceImpl.java`

**Step 1: 修改依赖注入**

```java
// 将
@Resource
private MetaEntityMapper metaEntityMapper;

// 改为
@Resource
private MetaEntityRepository metaEntityRepository;
```

**Step 2: 修改方法实现**

修改 create, update, delete, getById, list, checkExists 方法使用 Repository

**Step 3: Commit**

```bash
git commit -m "refactor(meta): refactor MetaEntityAppServiceImpl to use Repository"
```

---

## 阶段二：修复 Table 领域

### Task 4: 创建 MetaTableRepository 仓储实现

**Files:**
- Modify: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaTableRepositoryImpl.java`

**Step 1: 修改实现为数据库存储**

修改 MetaTableRepositoryImpl 使用 Mapper 进行数据库持久化，而非内存存储

**Step 2: Commit**

```bash
git commit -f meta "refactor(meta): update MetaTableRepositoryImpl to use database"
```

---

### Task 5: 改造 MetaTableAppServiceImpl 使用 Repository

**Files:**
- Modify: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/impl/MetaTableAppServiceImpl.java`

**Step 1: 修改依赖注入**

```java
// 将
@Resource
private MetaTableMapper metaTableMapper;

// 改为
@Resource
private MetaTableRepository metaTableRepository;
```

**Step 2: Commit**

```bash
git commit -m "refactor(meta): refactor MetaTableAppServiceImpl to use Repository"
```

---

## 阶段三：修复 Workflow 领域

### Task 6: 创建 MetaWorkflowRepositoryImpl 仓储实现

**Files:**
- Modify: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaWorkflowRepositoryImpl.java`

**Step 1: 添加 @Repository 注解**

确保实现类有 @Repository 注解，Spring 能扫描到

**Step 2: Commit**

```bash
git commit -m "fix(meta): add @Repository annotation to MetaWorkflowRepositoryImpl"
```

---

### Task 7: 改造 WorkflowServiceImpl 使用 Repository

**Files:**
- Modify: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/WorkflowServiceImpl.java`

**Step 1: 修改依赖注入**

添加 MetaWorkflowRepository 依赖

**Step 2: Commit**

```bash
git commit -m "refactor(meta): refactor WorkflowServiceImpl to use Repository"
```

---

## 阶段四：验证

### Task 8: 编译验证

**Step 1: 编译项目**

```bash
mvn compile -pl ssitao-code-modulars/ssitao-code-modular-meta -am
```

**Step 2: 验证编译通过**

---

## 总结

本计划包含 8 个任务：

1. Task 1: 创建 MetaEntityRepository 接口
2. Task 2: 创建 MetaEntityRepositoryImpl 实现
3. Task 3: 改造 MetaEntityAppServiceImpl
4. Task 4: 修改 MetaTableRepositoryImpl
5. Task 5: 改造 MetaTableAppServiceImpl
6. Task 6: 修复 MetaWorkflowRepositoryImpl
7. Task 7: 改造 WorkflowServiceImpl
8. Task 8: 编译验证
