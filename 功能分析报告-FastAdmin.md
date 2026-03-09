# FastAdmin 前端与后端 API 对比分析

## 一、前端技术栈
- **框架**: FastAdmin (jQuery + Bootstrap + RequireJS)
- **模板位置**: `ssitao-code-boot/src/main/resources/templates/`
- **JS位置**: `ssitao-code-boot/src/main/resources/static/assets/js/backend/`

## 二、API对比与修复状态

### 已修复的问题

| 模块 | 文件 | 问题 | 修复内容 | 状态 |
|------|------|------|----------|------|
| 权限 | permission.js | 前端用 `/permission/page`，后端无 | 改为 `/permission/list` | ✅ 已修复 |
| 租户 | tenant.js | 前端用 `/tenant/page`，后端无 | 改为 `/tenant/list` | ✅ 已修复 |

### 已确认匹配的模块

| 模块 | 前端API | 后端API | 状态 |
|------|---------|---------|------|
| 用户 | `/iam/user/page` | `/iam/user/page` | ✅ |
| 角色 | `/iam/role/page` | `/iam/role/page` | ✅ |
| 菜单 | `/iam/menu/list` | `/iam/menu/list` | ✅ |
| 账户 | `/iam/account/page` | `/iam/account/page` | ✅ |
| 集团 | `/iam/org/group/list` | `/iam/org/group/list` | ✅ |
| 公司 | `/iam/org/company/list` | `/iam/org/company/list` | ✅ |
| 部门 | `/iam/org/department/list` | `/iam/org/department/list` | ✅ |
| 字典 | `/meta/dictionary/list` | `/meta/dictionary/list` | ✅ |
| 字典项 | `/meta/dictionary-item/list` | `/meta/dictionary-item/list` | ✅ |
| 列管理 | `/meta/column/page` | `/meta/column/page` | ✅ |
| 实体 | `/meta/entity/page` | `/meta/entity/page` | ✅ |
| 表单 | `/meta/form/page` | `/meta/form/page` | ✅ |
| 列表 | `/meta/list/page` | `/meta/list/page` | ✅ |
| 表格 | `/meta/table/page` | `/meta/table/page` | ✅ |

### 待确认问题

1. **field.js**: 使用 `'meta/field/index'` - 需确认后端是否有对应API
2. **字段名映射**: 后端DTO字段名与前端表格列名可能不一致
3. **删除API**: 前端 del_url 路径与后端 DELETE 接口的参数传递方式

## 三、修改的文件列表

1. `ssitao-code-boot/src/main/resources/static/assets/js/backend/iam/permission.js`
   - index_url: `/iam/permission/page` → `/iam/permission/list`

2. `ssitao-code-boot/src/main/resources/static/assets/js/backend/iam/tenant.js`
   - index_url: `/iam/tenant/page` → `/iam/tenant/list`

---

*最后更新: 2026-03-09*
