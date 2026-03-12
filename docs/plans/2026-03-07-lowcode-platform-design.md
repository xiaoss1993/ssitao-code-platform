# 低代码配置平台设计方案

## 一、概述

基于现有 Meta 模块框架，开发完整的低代码配置平台，支持在线配置功能列表和表单，并自动生成动态 CRUD 页面。

## 二、整体架构

```
┌─────────────────────────────────────────────────────────────┐
│                    低代码配置平台                            │
├─────────────────────────────────────────────────────────────┤
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────┐     │
│  │  元数据管理  │  │  功能配置   │  │   动态渲染     │     │
│  │  (Meta)    │  │  (Config)  │  │  (Renderer)   │     │
│  └─────────────┘  └─────────────┘  └─────────────────┘     │
│         ↑               ↑                ↑                │
│  ┌─────────────────────────────────────────────────────┐   │
│  │              现有 Meta 模块                         │   │
│  │   MetaTable | MetaColumn | MetaForm | MetaList    │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

## 三、核心模块

| 模块 | 功能 | 状态 |
|------|------|------|
| **MetaTable** | 数据表配置 | 已有基础 |
| **MetaColumn** | 字段配置 | 已有基础 |
| **MetaForm** | 表单配置 | 已有基础 |
| **MetaList** | 列表配置 | 已有基础 |
| **DynamicCrud** | 动态 CRUD | 待完善 |
| **ConfigApi** | 配置 API | 待开发 |
| **Renderer** | 前端渲染器 | 待开发 |

## 四、数据模型

### 4.1 MetaList（列表配置）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | String | 主键 |
| entityId | String | 关联实体ID |
| listName | String | 列表名称 |
| listCode | String | 列表编码 |
| columns | JSON | 列配置 |
| sortRules | JSON | 排序规则 |
| filterRules | JSON | 筛选规则 |
| pagination | JSON | 分页配置 |
| status | Integer | 状态 |

### 4.2 MetaForm（表单配置）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | String | 主键 |
| entityId | String | 关联实体ID |
| formName | String | 表单名称 |
| formCode | String | 表单编码 |
| fields | JSON | 字段配置 |
| layout | JSON | 布局配置 |
| validation | JSON | 验证规则 |
| status | Integer | 状态 |

### 4.3 列配置 (columns) JSON 格式

```json
{
  "columns": [
    {
      "field": "id",
      "title": "ID",
      "visible": true,
      "sortable": true,
      "width": 80
    },
    {
      "field": "name",
      "title": "名称",
      "visible": true,
      "width": 150
    }
  ]
}
```

### 4.4 字段配置 (fields) JSON 格式

```json
{
  "fields": [
    {
      "name": "username",
      "label": "用户名",
      "type": "text",
      "required": true,
      "placeholder": "请输入用户名"
    },
    {
      "name": "email",
      "label": "邮箱",
      "type": "email",
      "required": true
    },
    {
      "name": "status",
      "label": "状态",
      "type": "select",
      "options": [
        {"value": "1", "label": "启用"},
        {"value": "0", "label": "禁用"}
      ]
    }
  ]
}
```

## 五、字段类型

### 5.1 列表字段类型

| 类型 | 说明 |
|------|------|
| text | 文本 |
| number | 数字 |
| date | 日期 |
| datetime | 日期时间 |
| switch | 开关 |
| image | 图片 |
| select | 下拉框 |
| tags | 标签 |

### 5.2 表单字段类型

| 类型 | 说明 | 额外配置 |
|------|------|----------|
| text | 单行文本 | maxlength, placeholder |
| textarea | 多行文本 | rows, maxlength |
| number | 数字 | min, max, step |
| email | 邮箱 | - |
| phone | 手机号 | - |
| password | 密码 | - |
| url | 链接 | - |
| select | 下拉框 | options |
| radio | 单选框 | options |
| checkbox | 复选框 | options |
| switch | 开关 | - |
| date | 日期 | format |
| datetime | 日期时间 | format |
| time | 时间 | - |
| file | 文件上传 | maxSize, accept |
| image | 图片上传 | maxSize, maxCount |
| editor | 富文本 | height |
| slider | 滑块 | min, max, step |
| rate | 评分 | max |

## 六、API 设计

### 6.1 列表配置 API

| 接口 | 方法 | 功能 |
|------|------|------|
| `/api/meta/list` | GET | 获取列表配置 |
| `/api/meta/list/save` | POST | 保存列表配置 |
| `/api/meta/list/delete` | DELETE | 删除列表配置 |

### 6.2 表单配置 API

| 接口 | 方法 | 功能 |
|------|------|------|
| `/api/meta/form` | GET | 获取表单配置 |
| `/api/meta/form/save` | POST | 保存表单配置 |
| `/api/meta/form/delete` | DELETE | 删除表单配置 |

### 6.3 动态 CRUD API

| 接口 | 方法 | 功能 |
|------|------|------|
| `/api/dynamic/{entityCode}` | GET | 查询列表 |
| `/api/dynamic/{entityCode}/{id}` | GET | 查询详情 |
| `/api/dynamic/{entityCode}` | POST | 创建 |
| `/api/dynamic/{entityCode}/{id}` | PUT | 更新 |
| `/api/dynamic/{entityCode}/{id}` | DELETE | 删除 |

## 七、前端设计

### 7.1 列表配置页面

- 拖拽调整列顺序
- 配置列属性（标题、宽度、排序、对齐）
- 设置筛选条件
- 配置分页参数
- 预览效果

### 7.2 表单配置页面

- 拖拽添加字段
- 配置字段属性（类型、标签、默认值、必填、验证）
- 布局设置（栅格、一列/两列）
- 预览效果

### 7.3 动态渲染器

- 根据配置动态生成表格
- 根据配置动态生成表单

## 八、实施步骤

### 第一阶段：完善 Meta 基础功能

1. 修复现有 bug
2. 添加缺失的 API
3. 完善数据库表结构

### 第二阶段：配置管理功能

1. 列表配置 CRUD
2. 表单配置 CRUD
3. 配置预览功能

### 第三阶段：动态渲染引擎

1. 后端动态查询
2. 前端动态渲染
3. 动态表单生成

### 第四阶段：集成测试

1. 单元测试
2. 集成测试
3. 性能优化
