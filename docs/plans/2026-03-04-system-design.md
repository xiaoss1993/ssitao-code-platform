# SSITAO Code Platform 系统完善设计方案

## 1. 项目概述

### 1.1 项目背景
SSITAO Code Platform 是一个基于 Spring Cloud + Vue.js 的企业级开发平台，需要完善权限管理体系和低代码管理功能。

### 1.2 需求汇总

| 模块 | 需求 |
|------|------|
| **多租户** | 支持多租户数据隔离 |
| **数据权限** | 精细行级权限（可配置到具体数据） |
| **功能权限** | 菜单/按钮/接口权限 |
| **低代码** | 动态表单/表格、工作流、动态API、自定义实体 |
| **组织机构** | 五级结构：集团→公司→部门→岗位→用户 |
| **认证** | Sa-Token |
| **前端** | Vue3 + Element Plus / Thymeleaf 双前端并行 |

---

## 2. 系统架构

### 2.1 整体架构

```
┌─────────────────────────────────────────────────────────────┐
│                        前端层                                │
│  ┌─────────────────────┐    ┌─────────────────────┐       │
│  │  ssitao-code-web   │    │  templates(Thymeleaf)│      │
│  │   (Vue3 + Element)│    │   (FastAdmin)       │       │
│  └─────────┬───────────┘    └──────────┬──────────┘       │
└────────────┼──────────────────────────────┼──────────────────┘
             │                              │
┌────────────┼──────────────────────────────┼──────────────────┐
│            │         API 网关层            │                  │
│  ┌─────────▼──────────────────────────────▼─────────────┐   │
│  │              Spring Cloud Gateway                   │   │
│  └────────────────────────┬────────────────────────────┘   │
└───────────────────────────┼──────────────────────────────────┘
                            │
┌───────────────────────────┼──────────────────────────────────┐
│                    │  服务层  │                               │
│  ┌─────────────────▼──────▼───────────────────────────┐   │
│  │               IAM 服务 (身份权限)                    │   │
│  │  - 认证授权  - 角色权限  - 组织机构  - 租户管理      │   │
│  └────────────────────┬───────────────────────────────┘   │
│  ┌────────────────────▼───────────────────────────────┐   │
│  │            META 服务 (元数据/低代码)                 │   │
│  │  - 实体管理  - 字段配置  - 表单配置  - 工作流       │   │
│  └────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

### 2.2 技术栈

| 层级 | 技术选型 |
|------|----------|
| 前端框架 | Vue 3.2 + Element Plus 2.2 |
| 后端框架 | Spring Boot 2.7.18 |
| 微服务 | Spring Cloud 2021.0.8 |
| ORM | MyBatis-Flex 1.9.5 |
| 认证授权 | Sa-Token 1.37.0 |
| 数据库 | MySQL 8.0 |
| 缓存 | Redis |

---

## 3. 多租户模块设计

### 3.1 租户模型

```java
IamTenant {
    id: String              // 租户ID
    tenantCode: String      // 租户编码(唯一)
    tenantName: String      // 租户名称
    tenantType: String      // 租户类型: SYSTEM/ENTERPRISE/PERSONAL
    packageId: String       // 套餐ID
    expireTime: Date        // 到期时间
    maxUsers: Integer       // 最大用户数
    maxStorage: Long        // 最大存储(MB)
    status: Boolean         // 状态: 1启用 0禁用
    logo: String            // logo
    description: String     // 描述
    // 租户配置(JSON)
    config: {
        allowRegister: Boolean,
        allowCustomDomain: Boolean,
        theme: String,
        language: String
    }
}
```

### 3.2 租户识别方式

1. **请求头识别** (推荐): `X-Tenant-Id`
2. **域名识别**: 子域名映射
3. **参数识别**: `?tenant=xxx`

### 3.3 数据隔离策略

- 所有业务表添加 `tenant_id` 字段
- 全局租户拦截器自动注入租户ID
- 租户超级管理员可管理所有租户

---

## 4. 组织机构模块设计

### 4.1 组织架构(五级)

```
集团(Group)
  └── 公司(Company)
        └── 部门(Department)
              └── 岗位(Post)
                    └── 用户(User)
```

### 4.2 核心模型

#### 4.2.1 集团(Group)
```java
IamGroup {
    id: String
    groupCode: String      // 集团编码
    groupName: String      // 集团名称
    leader: String         // 负责人
    phone: String          // 电话
    email: String          // 邮箱
    sortOrder: Integer     // 排序
    status: Boolean        // 状态
    tenantId: String       // 租户ID
}
```

#### 4.2.2 公司(Company)
```java
IamCompany {
    id: String
    groupId: String        // 集团ID
    companyCode: String    // 公司编码
    companyName: String    // 公司名称
    leader: String         // 负责人
    phone: String
    email: String
    sortOrder: Integer
    status: Boolean
    tenantId: String
}
```

#### 4.2.3 部门(Department)
```java
IamDepartment {
    id: String
    companyId: String      // 公司ID
    parentId: String       // 父部门ID
    deptName: String       // 部门名称
    deptCode: String       // 部门编码
    leader: String
    managerId: String      // 负责人用户ID
    phone: String
    email: String
    sortOrder: Integer
    status: Boolean
    tenantId: String
}
```

#### 4.2.4 岗位(Post)
```java
IamPost {
    id: String
    postCode: String       // 岗位编码
    postName: String      // 岗位名称
    postRank: String      // 岗位职级
    companyId: String     // 公司ID
    deptId: String        // 部门ID
    sortOrder: Integer
    status: Boolean
    tenantId: String
}
```

### 4.3 用户-组织关联

```java
IamUserOrg {
    userId: String         // 用户ID
    companyId: String     // 所属公司
    deptId: String        // 所属部门
    postId: String        // 岗位ID
    isMain: Boolean       // 是否主组织(1是0否)
}
```

---

## 5. 权限体系设计

### 5.1 权限类型

| 类型 | 说明 | 示例 |
|------|------|------|
| MENU | 菜单权限 | 控制页面显示 |
| BUTTON | 按钮权限 | 控制操作按钮 |
| API | 接口权限 | 控制API访问 |
| DATA | 数据权限 | 控制数据可见范围 |
| FIELD | 字段权限 | 控制字段可见/可编辑 |

### 5.2 权限模型

#### 5.2.1 权限(Permission)
```java
IamPermission {
    id: String
    permCode: String       // 权限编码
    permName: String      // 权限名称
    permType: String      // MENU/BUTTON/API/DATA/FIELD
    parentId: String      // 父权限ID
    path: String          // 路由路径
    component: String     // 组件路径
    perms: String         // 权限标识(user:add)
    icon: String          // 图标
    sortOrder: Integer
    status: Boolean
    tenantId: String
}
```

#### 5.2.2 角色(Role)
```java
IamRole {
    id: String
    roleCode: String      // 角色编码
    roleName: String      // 角色名称
    roleType: String      // SYSTEM/CUSTOM
    dataScope: String     // 数据权限范围
    dataScopeDepts: String // 指定部门(逗号分隔)
    status: Boolean
    tenantId: String
}
```

#### 5.2.3 数据权限范围
| 值 | 说明 |
|----|------|
| ALL | 全部数据 |
| DEPT | 本部门数据 |
| DEPT_AND_CHILD | 本部门及子部门 |
| SELF | 仅本人数据 |
| CUSTOM | 自定义(指定部门) |

### 5.3 行级数据权限

#### 5.3.1 数据权限规则
```java
IamDataRule {
    id: String
    ruleName: String      // 规则名称
    ruleCode: String      // 规则编码
    entityName: String    // 实体名称(如: Order)
    fieldName: String    // 字段名称(如: owner_id)
    ruleType: String     // 规则类型
    ruleValue: String     // 规则值(JSON)
    status: Boolean
}
```

#### 5.3.2 规则类型
| 类型 | 说明 | 示例 |
|------|------|------|
| EQ | 等于 | field=value |
| NE | 不等于 | field!=value |
| IN | 包含 | field in (1,2,3) |
| LIKE | 模糊匹配 | field like %xxx% |
| BETWEEN | 范围 | field between a and b |
| SCRIPT | 脚本 | 自定义脚本 |

---

## 6. 低代码模块设计

### 6.1 模块架构

```
┌─────────────────────────────────────────────────────────────┐
│                     低代码引擎                               │
│  ┌──────────────┬──────────────┬──────────────┐            │
│  │  实体管理    │  表单设计    │  列表设计    │            │
│  └──────────────┴──────────────┴──────────────┘            │
│  ┌──────────────┬──────────────┬──────────────┐            │
│  │  流程设计    │  视图设计    │  API发布    │            │
│  └──────────────┴──────────────┴──────────────┘            │
└─────────────────────────────────────────────────────────────┘
```

### 6.2 实体管理(MetaEntity)

#### 6.2.1 实体定义
```java
MetaEntity {
    id: String
    entityCode: String    // 实体编码(英文)
    entityName: String    // 实体名称(中文)
    tableName: String     // 物理表名
    entityType: String   // SYSTEM/USER // 系统/自定义
    category: String     // 分类
    description: String
    status: Boolean
    tenantId: String
}
```

#### 6.2.2 字段定义
```java
MetaField {
    id: String
    entityId: String      // 实体ID
    fieldCode: String     // 字段编码
    fieldName: String     // 字段名称
    fieldType: String     // 字段类型
    // 类型: varchar/int/decimal/date/datetime/text/rich_text/
    //       select/multi_select/radio/checkbox/switch/
    //       file/image/user/dept/organization
    javaType: String      // Java类型
    dbType: String        // 数据库类型
    length: Integer       // 长度
    decimalLength: Integer // 小数位
    defaultValue: String
    required: Boolean     // 是否必填
    searchable: Boolean  // 是否可搜索
    sortable: Boolean    // 是否可排序
    hidden: Boolean      // 是否隐藏
    width: Integer       // 列表宽度
    align: String        // 对齐方式
    // 扩展配置(JSON)
    config: {
        placeholder: String,
        min: Number,
        max: Number,
        precision: Number,
        options: [{label, value}],
        dictType: String,
        uploadType: String,
        ...
    }
    sortOrder: Integer
    status: Boolean
}
```

### 6.3 表单配置(MetaForm)

```java
MetaForm {
    id: String
    formCode: String      // 表单编码
    formName: String      // 表单名称
    entityId: String      // 关联实体
    formType: String      // DESIGN/NORMAL
    layout: String        // SINGLE/CARD/STEPS
    config: {             // 表单配置(JSON)
        labelWidth: Number,
        labelPosition: String,
        size: String,
        ...
    }
    tenantId: String
}
```

#### 表单字段
```java
MetaFormField {
    id: String
    formId: String
    fieldId: String       // 关联字段
    fieldName: String
    fieldType: String
    sortOrder: Integer
    config: {             // 字段配置(JSON)
        required: Boolean,
        readonly: Boolean,
        placeholder: String,
        defaultValue: String,
        ...
    }
}
```

### 6.4 列表配置(MetaList)

```java
MetaList {
    id: String
    listCode: String       // 列表编码
    listName: String      // 列表名称
    entityId: String
    listType: String      // TABLE/TREE/TAB
    config: {             // 列表配置(JSON)
        pageSize: Number,
        showRowNumber: Boolean,
        showCheckbox: Boolean,
        ...
    }
    tenantId: String
}
```

#### 列表列
```java
MetaListColumn {
    id: String
    listId: String
    fieldId: String
    fieldName: String
    sortOrder: Integer
    config: {             // 列配置(JSON)
        width: Number,
        align: String,
        sortable: Boolean,
        formatter: String,
        ...
    }
}
```

### 6.5 工作流引擎(MetaWorkflow)

#### 6.5.1 流程定义
```java
MetaWorkflow {
    id: String
    workflowCode: String   // 流程编码
    workflowName: String   // 流程名称
    entityId: String       // 关联实体
    category: String       // 分类
    version: String        // 版本号
    flowJson: String       // 流程定义JSON(BPMN)
    status: String        // DRAFT/ACTIVE/SUSPENDED
    tenantId: String
}
```

#### 6.5.2 流程节点
```java
MetaWorkflowNode {
    id: String
    workflowId: String
    nodeId: String         // 节点ID
    nodeName: String       // 节点名称
    nodeType: String       // START/END/TASK/SCRIPT/GATEWAY
    assigneeType: String   // ASSIGNEE/ROLE/DEPT/EXPR
    assigneeValue: String  //  assigneeId: String
    optionType: String     // NONE/SIGNLE/MULTI
    condition: String      // 路由条件
    config: {              // 节点配置
        allowDelegate: Boolean,
        allowReject: Boolean,
        ...
    }
}
```

#### 6.5.3 流程实例
```java
MetaProcessInstance {
    id: String
    processId: String      // 流程定义ID
    businessKey: String    // 业务ID
    title: String          // 流程标题
    initiator: String      // 发起人
    currentNodeId: String  // 当前节点
    status: String         // RUNNING/COMPLETED/TERMINATED
    startTime: Date
    endTime: Date
    tenantId: String
}
```

### 6.6 动态API

```java
MetaApi {
    id: String
    apiCode: String        // API编码
    apiName: String        // API名称
    entityId: String       // 关联实体
    apiMethod: String      // GET/POST/PUT/DELETE
    apiPath: String        // /api/meta/{entity}/{action}
    action: String         // list/page/add/update/delete/detail
    authType: String       // 认证类型
    status: Boolean
    tenantId: String
}
```

---

## 7. 数据库设计

### 7.1 核心表结构

#### IAM模块
- `iam_tenant` - 租户表
- `iam_group` - 集团表
- `iam_company` - 公司表
- `iam_department` - 部门表
- `iam_post` - 岗位表
- `iam_user` - 用户表
- `iam_user_org` - 用户组织关联
- `iam_role` - 角色表
- `iam_permission` - 权限表
- `iam_user_role` - 用户角色关联
- `iam_role_permission` - 角色权限关联
- `iam_data_rule` - 数据权限规则
- `iam_role_data_rule` - 角色数据规则

#### META模块
- `meta_entity` - 实体定义
- `meta_field` - 字段定义
- `meta_form` - 表单配置
- `meta_form_field` - 表单字段
- `meta_list` - 列表配置
- `meta_list_column` - 列表列
- `meta_workflow` - 流程定义
- `meta_workflow_node` - 流程节点
- `meta_process_instance` - 流程实例
- `meta_task_instance` - 任务实例
- `meta_api` - API配置

---

## 8. 实施计划

### 阶段一：基础设施(第1周)
1. 完善多租户模块
2. 创建租户拦截器和数据隔离
3. 组织机构管理(五级)

### 阶段二：权限体系(第2-3周)
1. 功能权限完善
2. 数据权限(行级)实现
3. 权限验证拦截器

### 阶段三：低代码基础(第4-5周)
1. 实体管理模块
2. 字段管理
3. 动态CRUD API

### 阶段四：表单与列表(第6-7周)
1. 表单设计器
2. 列表设计器
3. 前端动态渲染

### 阶段五：工作流(第8周)
1. 流程引擎集成
2. 流程设计器
3. 任务处理

---

## 9. 风险与对策

| 风险 | 对策 |
|------|------|
| 低代码复杂度高 | 分阶段实现，优先CRUD |
| 性能问题 | 缓存+异步处理 |
| 动态API安全 | 严格权限验证+限流 |

---

## 10. 后续扩展

- 自定义页面设计器
- 报表配置
- 大屏可视化
- 移动端适配
