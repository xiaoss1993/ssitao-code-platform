# Module Completeness Enhancement Implementation Plan

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 完善Workflow模块数据库持久化和IAM组织架构模块中未实现的Controller端点

**Architecture:**
- Workflow模块：创建数据库表、DO类、Mapper、Repository，将内存存储改为数据库持久化
- IAM组织架构：在IamOrganizationController中补充岗位(Post)管理的REST API端点，完善TODO实现

**Tech Stack:** Spring Boot 2.7, MyBatis-Flex, MySQL, Sa-Token

---

## Task 1: Workflow数据库表设计

**Files:**
- Create: `ssitao-code-boot/src/main/resources/sql/meta_workflow.sql`

**Step 1: Create workflow database tables SQL**

```sql
-- 流程定义表
CREATE TABLE IF NOT EXISTS meta_workflow (
    id VARCHAR(64) PRIMARY KEY COMMENT '流程定义ID',
    workflow_code VARCHAR(64) NOT NULL COMMENT '流程编码',
    workflow_name VARCHAR(128) NOT NULL COMMENT '流程名称',
    entity_id VARCHAR(64) COMMENT '关联的实体ID',
    category VARCHAR(64) COMMENT '分类',
    version INT DEFAULT 1 COMMENT '版本号',
    flow_json TEXT COMMENT '流程JSON（流程图结构）',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用 1-启用',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    UNIQUE KEY uk_tenant_code (tenant_id, workflow_code, deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程定义表';

-- 流程实例表
CREATE TABLE IF NOT EXISTS meta_process_instance (
    id VARCHAR(64) PRIMARY KEY COMMENT '流程实例ID',
    workflow_id VARCHAR(64) NOT NULL COMMENT '流程定义ID',
    business_key VARCHAR(128) COMMENT '业务键',
    title VARCHAR(256) NOT NULL COMMENT '流程标题',
    initiator VARCHAR(64) COMMENT '发起人',
    current_node_id VARCHAR(64) COMMENT '当前节点ID',
    current_node_name VARCHAR(128) COMMENT '当前节点名称',
    status VARCHAR(32) DEFAULT 'RUNNING' COMMENT '状态：RUNNING-运行中 COMPLETED-已完成 CANCELLED-已取消',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    KEY idx_workflow (workflow_id),
    KEY idx_business_key (business_key),
    KEY idx_initiator (initiator)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程实例表';

-- 任务实例表
CREATE TABLE IF NOT EXISTS meta_task_instance (
    id VARCHAR(64) PRIMARY KEY COMMENT '任务实例ID',
    process_id VARCHAR(64) NOT NULL COMMENT '流程实例ID',
    task_id VARCHAR(64) NOT NULL COMMENT '任务节点ID',
    task_name VARCHAR(128) NOT NULL COMMENT '任务节点名称',
    task_type VARCHAR(32) COMMENT '任务类型：APPROVAL-审批',
    assignee VARCHAR(64) COMMENT '处理人',
    status VARCHAR(32) DEFAULT 'PENDING' COMMENT '状态：PENDING-待签收 SIGNED-已签收 COMPLETED-已完成 REJECTED-已驳回 CANCELLED-已取消',
    priority INT DEFAULT 2 COMMENT '优先级',
    comment TEXT COMMENT '审批意见',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    claim_time DATETIME COMMENT '签收时间',
    complete_time DATETIME COMMENT '完成时间',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    KEY idx_process (process_id),
    KEY idx_assignee (assignee),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务实例表';
```

**Step 2: Commit**

```bash
git add ssitao-code-boot/src/main/resources/sql/meta_workflow.sql
git commit -m "feat(workflow): add workflow database tables"
```

---

## Task 2: Workflow DO类和Mapper

**Files:**
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/MetaWorkflowDO.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/MetaProcessInstanceDO.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/MetaTaskInstanceDO.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/MetaWorkflowMapper.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/MetaProcessInstanceMapper.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/MetaTaskInstanceMapper.java`

**Step 1: Create MetaWorkflowDO.java**

```java
package com.ssitao.code.modular.meta.dal.dataobject;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Key;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 流程定义DO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_workflow")
public class MetaWorkflowDO {

    @Id
    private String id;

    private String workflowCode;

    private String workflowName;

    private String entityId;

    private String category;

    private Integer version;

    private String flowJson;

    private Integer status;

    private String tenantId;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;
}
```

**Step 2: Create MetaProcessInstanceDO.java**

```java
package com.ssitao.code.modular.meta.dal.dataobject;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 流程实例DO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_process_instance")
public class MetaProcessInstanceDO {

    @Id
    private String id;

    private String workflowId;

    private String businessKey;

    private String title;

    private String initiator;

    private String currentNodeId;

    private String currentNodeName;

    private String status;

    private String tenantId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;
}
```

**Step 3: Create MetaTaskInstanceDO.java**

```java
package com.ssitao.code.modular.meta.dal.dataobject;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 任务实例DO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_task_instance")
public class MetaTaskInstanceDO {

    @Id
    private String id;

    private String processId;

    private String taskId;

    private String taskName;

    private String taskType;

    private String assignee;

    private String status;

    private Integer priority;

    private String comment;

    private String tenantId;

    private LocalDateTime claimTime;

    private LocalDateTime completeTime;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;
}
```

**Step 4: Create Mappers**

```java
package com.ssitao.code.modular.meta.dal.mapper;

import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.mybatisflex.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MetaWorkflowMapper extends BaseMapper<MetaWorkflowDO> {
}
```

(类似创建ProcessInstanceMapper和TaskInstanceMapper)

**Step 5: Commit**

```bash
git add ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/
git add ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/
git commit -m "feat(workflow): add workflow DO and Mapper classes"
```

---

## Task 3: Workflow Repository层

**Files:**
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/MetaWorkflowRepository.java`
- Create: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaWorkflowRepositoryImpl.java`

**Step 1: Create Repository interface and implementation**

```java
package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaWorkflowDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaProcessInstanceDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTaskInstanceDO;

import java.util.List;

public interface MetaWorkflowRepository {
    // Workflow operations
    String saveWorkflow(MetaWorkflowDO workflow);
    void updateWorkflow(MetaWorkflowDO workflow);
    void deleteWorkflow(String id, String tenantId);
    MetaWorkflowDO findWorkflowById(String id, String tenantId);
    MetaWorkflowDO findWorkflowByCode(String workflowCode, String tenantId);
    List<MetaWorkflowDO> findWorkflows(String tenantId);

    // Process Instance operations
    String saveProcessInstance(MetaProcessInstanceDO processInstance);
    void updateProcessInstance(MetaProcessInstanceDO processInstance);
    void deleteProcessInstance(String id, String tenantId);
    MetaProcessInstanceDO findProcessInstanceById(String id, String tenantId);
    MetaProcessInstanceDO findProcessInstanceByBusinessKey(String businessKey, String tenantId);
    List<MetaProcessInstanceDO> findProcessInstancesByWorkflowId(String workflowId, String tenantId);
    List<MetaProcessInstanceDO> findProcessInstancesByInitiator(String initiator, String tenantId);

    // Task Instance operations
    String saveTaskInstance(MetaTaskInstanceDO taskInstance);
    void updateTaskInstance(MetaTaskInstanceDO taskInstance);
    void deleteTaskInstance(String id, String tenantId);
    MetaTaskInstanceDO findTaskInstanceById(String id, String tenantId);
    List<MetaTaskInstanceDO> findTaskInstancesByProcessId(String processId, String tenantId);
    List<MetaTaskInstanceDO> findTodoTasks(String assignee, String tenantId);
    List<MetaTaskInstanceDO> findDoneTasks(String assignee, String tenantId);
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/
git add ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/
git commit -m "feat(workflow): add workflow Repository layer"
```

---

## Task 4: 修改WorkflowServiceImpl使用数据库

**Files:**
- Modify: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/WorkflowServiceImpl.java`

**Step 1: Replace in-memory storage with database**

替换原有的ConcurrentHashMap存储，注入Repository并使用数据库操作。

**Step 2: Commit**

```bash
git commit -m "feat(workflow): use database persistence instead of in-memory storage"
```

---

## Task 5: IAM组织架构-岗位管理API

**Files:**
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/controller/IamOrganizationController.java`

**Step 1: Add Post management endpoints**

在IamOrganizationController中添加岗位(POST)管理的REST API端点：
- POST /iam/org/post - 创建岗位
- GET /iam/org/post/list - 获取岗位列表
- GET /iam/org/post/by-department/{departmentId} - 获取部门下的岗位
- PUT /iam/org/post - 更新岗位
- DELETE /iam/org/post/{id} - 删除岗位

**Step 2: Commit**

```bash
git commit -m "feat(iam): add post management endpoints to IamOrganizationController"
```

---

## Task 6: 完善Organization Controller TODO实现

**Files:**
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/controller/IamOrganizationController.java`

**Step 1: Implement TODO endpoints**

完善以下端点的实现：
- 集团(Group)的CRUD完整实现
- 公司下部门列表
- 用户组织分配和查询

**Step 2: Commit**

```bash
git commit -m "feat(iam): implement remaining organization endpoints"
```

---

## Task 7: 编译验证

**Step 1: Run Maven build**

```bash
mvn clean compile -DskipTests
```

**Step 2: Verify no compilation errors**

---

## Execution Options

**Plan complete and saved to `docs/plans/2026-03-06-module-completeness-enhancement.md`. Two execution options:**

**1. Subagent-Driven (this session)** - I dispatch fresh subagent per task, review between tasks, fast iteration

**2. Parallel Session (separate)** - Open new session with executing-plans, batch execution with checkpoints

**Which approach?**
