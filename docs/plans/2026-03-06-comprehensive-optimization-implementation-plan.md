# Code Platform 全面优化实施计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development to implement this plan task-by-task.

**Goal:** 对SSITAO Code Platform进行全面优化，包括代码质量、安全权限和前后端集成

**Architecture:** 分三个阶段实施：1)代码质量优化(统一依赖注入、事务配置) 2)安全权限完善(数据权限、租户拦截器) 3)前后端集成(DynamicCrud、模板对接)

**Tech Stack:** Spring Boot 2.7, MyBatis-Flex, Sa-Token, Thymeleaf

---

## Task 1: 统一依赖注入(@Resource)

**Files:**
- Modify: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/WorkflowServiceImpl.java`
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/impl/IamGroupAppServiceImpl.java`
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/impl/IamUserOrgAppServiceImpl.java`

**Step 1: 修改WorkflowServiceImpl**

将 `@Autowired` 改为 `@Resource`:
```java
// 原来
@Autowired
private MetaWorkflowRepository workflowRepository;

// 修改为
@Resource
private MetaWorkflowRepository workflowRepository;
```

**Step 2: 修改IamGroupAppServiceImpl**

添加依赖注入:
```java
@Resource
private IamGroupRepository groupRepository;
```

**Step 3: 修改IamUserOrgAppServiceImpl**

添加依赖注入:
```java
@Resource
private IamUserOrgRepository userOrgRepository;
```

**Step 4: Commit**

```bash
git add -A && git commit -m "refactor:统一使用@Resource注解进行依赖注入"
```

---

## Task 2: 完善事务配置

**Files:**
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/impl/IamGroupAppServiceImpl.java`
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/impl/IamUserOrgAppServiceImpl.java`

**Step 1: 添加事务注解**

在类上添加 `@Transactional`:
```java
@Service
@Transactional(rollbackFor = Exception.class)
public class IamGroupAppServiceImpl {
```

**Step 2: Commit**

```bash
git commit -m "refactor:添加@Transactional事务注解"
```

---

## Task 3: 完善数据权限拦截器

**Files:**
- Modify: `ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/authorization/infrastructure/interceptor/DataPermissionInterceptor.java`

**Step 1: 检查现有实现**

查看当前TODO内容，理解需要实现的功能:
- 行级数据权限过滤
- 部门、岗位、用户自定义数据范围

**Step 2: 实现核心逻辑**

```java
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 1. 获取当前登录用户
    // 2. 获取用户的数据权限范围
    // 3. 构建数据权限过滤条件
    // 4. 将过滤条件存入ThreadLocal供后续使用

    // 示例：获取用户可访问的部门IDs
    List<String> deptIds = getDeptIdsByUser(userId);
    TenantUtils.setDataScope(deptIds);

    return true;
}
```

**Step 3: Commit**

```bash
git commit -m "feat:完善数据权限拦截器实现"
```

---

## Task 4: 完善租户拦截器

**Files:**
- Modify: `ssitao-code-frame/ssitao-code-frame-security/ssitao-code-security-tenant/src/main/java/com/ssitao/code/frame/security/tenant/utils/TenantUtils.java`

**Step 1: 检查现有TODO**

**Step 2: 实现多租户识别**

支持三种方式:
- 请求头: X-Tenant-Id
- 域名: xxx.tenant.com
- 参数: ?tenant=xxx

**Step 3: Commit**

```bash
git commit -m "feat:完善多租户识别机制"
```

---

## Task 5: 前后端集成-MetaList

**Files:**
- Modify: `ssitao-code-boot/src/main/resources/templates/meta/list.html`
- Modify: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaListController.java`

**Step 1: 检查现有API**

确认MetaListController提供的端点:
- GET /meta/list/list-by-entity/{entityId} - 获取列表配置

**Step 2: 对接前端页面**

修改list.html中的API调用:
```javascript
// 获取列表配置
function loadListConfig(entityId) {
    $.get('/meta/list/list-by-entity/' + entityId, function(res) {
        if (res.success) {
            renderTable(res.data);
        }
    });
}
```

**Step 3: Commit**

```bash
git commit -m "feat:MetaList前后端集成"
```

---

## Task 6: 前后端集成-MetaForm

**Files:**
- Modify: `ssitao-code-boot/src/main/resources/templates/meta/form.html`
- Modify: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaFormController.java`

**Step 1: 检查现有API**

确认MetaFormController提供的端点:
- GET /meta/form/list-by-entity/{entityId} - 获取表单配置

**Step 2: 对接前端页面**

修改form.html中的API调用:
```javascript
// 获取表单配置
function loadFormConfig(entityId) {
    $.get('/meta/form/list-by-entity/' + entityId, function(res) {
        if (res.success) {
            renderForm(res.data);
        }
    });
}
```

**Step 3: Commit**

```bash
git commit -m "feat:MetaForm前后端集成"
```

---

## Task 7: 编译验证

**Step 1: 运行Maven编译**

```bash
mvn clean compile -DskipTests
```

**Step 2: 运行测试**

```bash
mvn test
```

---

## Execution Options

**Plan complete and saved to `docs/plans/2026-03-06-comprehensive-optimization-design.md`. Two execution options:**

**1. Subagent-Driven (this session)** - I dispatch fresh subagent per task, review between tasks, fast iteration

**2. Parallel Session (separate)** - Open new session with executing-plans, batch execution with checkpoints

**Which approach?**
