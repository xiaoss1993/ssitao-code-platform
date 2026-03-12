# 低代码配置平台实施计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development 或 superpowers:executing-plans 来实施此计划

**目标:** 基于现有 Meta 模块框架，开发完整的低代码配置平台，支持在线配置功能列表和表单，并自动生成动态 CRUD 页面

**架构:** 复用现有的 MetaTable、MetaColumn、MetaForm、MetaList 领域模型，完善 Controller、Service 层，添加前端配置页面和动态渲染器

**技术栈:** Spring Boot + MyBatis-Flex + Bootstrap-Table + Vue/JS

---

## 第一阶段：完善后端基础功能

### Task 1: 检查并完善 MetaList 后端 API

**目标:** 确保 MetaList 的 CRUD 接口完整

**Files:**
- 检查: `ssitao-code-modular-meta/.../controller/MetaListController.java`
- 检查: `ssitao-code-modular-meta/.../service/MetaListAppService.java`
- 检查: `ssitao-code-modular-meta/.../repository/MetaListRepository.java`

**Step 1: 检查 MetaListController**
```bash
# 查看现有接口
grep -n "@GetMapping\|@PostMapping\|@PutMapping\|@DeleteMapping" \
  ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaListController.java
```

**Step 2: 补全缺失的接口**
如果缺少分页接口，添加：
```java
@GetMapping("/page")
public CommonResult<Page<MetaListDTO>> pageLists(
    @RequestParam(defaultValue = "1") int current,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(required = false) String entityId)
```

**Step 3: 编译测试**
```bash
mvn compile -pl ssitao-code-modulars/ssitao-code-modular-meta -am -q
```

---

### Task 2: 检查并完善 MetaForm 后端 API

**目标:** 确保 MetaForm 的 CRUD 接口完整

**Files:**
- 检查: `ssitao-code-modular-meta/.../controller/MetaFormController.java`
- 检查: `ssitao-code-modular-meta/.../service/MetaFormAppService.java`

**Step 1: 检查 MetaFormController**
```bash
grep -n "@GetMapping\|@PostMapping\|@PutMapping\|@DeleteMapping" \
  ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaFormController.java
```

**Step 2: 补全缺失的接口**
确保有以下接口：
- 分页列表
- 按实体查询

---

### Task 3: 检查并完善 MetaColumn 后端 API

**目标:** 确保字段配置的 CRUD 接口完整

**Files:**
- 检查: `ssitao-code-modular-meta/.../controller/MetaColumnController.java`

---

### Task 4: 开发动态 CRUD 引擎

**目标:** 实现通用的动态增删改查接口

**Files:**
- 创建: `ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/DynamicCrudController.java`

**Step 1: 创建 DynamicCrudController**
```java
@RestController
@RequestMapping("/api/dynamic")
public class DynamicCrudController {

    @GetMapping("/{entityCode}")
    public CommonResult<PageResult<Map<String, Object>>> query(
        @PathVariable String entityCode,
        @RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "10") int size)

    @GetMapping("/{entityCode}/{id}")
    public CommonResult<Map<String, Object>> getById(
        @PathVariable String entityCode,
        @PathVariable String id)

    @PostMapping("/{entityCode}")
    public CommonResult<String> create(
        @PathVariable String entityCode,
        @RequestBody Map<String, Object> data)

    @PutMapping("/{entityCode}/{id}")
    public CommonResult<Boolean> update(
        @PathVariable String entityCode,
        @PathVariable String id,
        @RequestBody Map<String, Object> data)

    @DeleteMapping("/{entityCode}/{id}")
    public CommonResult<Boolean> delete(
        @PathVariable String entityCode,
        @PathVariable String id)
}
```

**Step 2: 创建动态查询服务**
- 创建: `ssitao-code-modular-meta/.../service/DynamicCrudService.java`
- 实现根据 entityCode 获取配置，构建动态 SQL

**Step 3: 编译测试**
```bash
mvn compile -pl ssitao-code-modulars/ssitao-code-modular-meta -am -q
```

---

## 第二阶段：前端配置页面

### Task 5: 创建列表配置页面

**目标:** 创建元数据表配置管理页面

**Files:**
- 创建: `ssitao-code-boot/.../templates/meta/table.html`
- 创建: `ssitao-code-boot/.../assets/js/backend/meta/table.js`

**Step 1: 创建 table.html**
参考现有模板 `ssitao-code-boot/.../templates/admin/admin.html`

**Step 2: 创建 table.js**
```javascript
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {
    var Controller = {
        index: function () {
            Table.api.init({
                extend: {
                    index_url: '/admin/meta/table/page',
                    add_url: '/admin/meta/table/add',
                    edit_url: '/admin/meta/table/edit',
                    del_url: '/admin/meta/table',
                    table: 'meta_table',
                }
            });
            // ...
        }
    };
    return Controller;
});
```

---

### Task 6: 创建列表配置（MetaList）页面

**目标:** 创建列表配置管理页面

**Files:**
- 创建: `ssitao-code-boot/.../templates/meta/list.html`
- 创建: `ssitao-code-boot/.../assets/js/backend/meta/list.js`

**Step 1: 创建 list.html**
基于 `table.html` 模板

**Step 2: 创建 list.js**
实现列表配置的增删改查

---

### Task 7: 创建表单配置（MetaForm）页面

**目标:** 创建表单配置管理页面

**Files:**
- 创建: `ssitao-code-boot/.../templates/meta/form.html`
- 创建: `ssitao-code-boot/.../assets/js/backend/meta/form.js`

---

## 第三阶段：动态渲染器

### Task 8: 开发前端动态表格渲染器

**目标:** 根据 MetaList 配置动态渲染表格

**Files:**
- 创建: `ssitao-code-boot/.../assets/js/backend/dynamic-table.js`

**Step 1: 创建 dynamic-table.js**
```javascript
var DynamicTable = {
    // 根据配置渲染表格
    render: function(container, config) {
        // 1. 获取列表配置
        // 2. 构建 columns
        // 3. 初始化 bootstrapTable
    },

    // 获取配置并渲染
    loadConfig: function(entityCode, listCode, callback) {
        $.get('/api/meta/list config?entityCode=' + entityCode + '&listCode=' + listCode, function(res) {
            if (res.code === 200) {
                callback(res.data);
            }
        });
    }
};
```

---

### Task 9: 开发前端动态表单渲染器

**目标:** 根据 MetaForm 配置动态渲染表单

**Files:**
- 创建: `ssitao-code-boot/.../assets/js/backend/dynamic-form.js`

**Step 1: 创建 dynamic-form.js**
```javascript
var DynamicForm = {
    // 根据配置渲染表单
    render: function(container, config) {
        // 1. 获取表单配置
        // 2. 构建字段
        // 3. 初始化表单
    },

    // 渲染字段
    renderField: function(fieldConfig) {
        var html = '';
        switch(fieldConfig.type) {
            case 'text':
                html = this.renderText(fieldConfig);
                break;
            case 'select':
                html = this.renderSelect(fieldConfig);
                break;
            // ... 其他类型
        }
        return html;
    }
};
```

---

### Task 10: 创建通用动态页面

**目标:** 创建可复用的动态 CRUD 页面

**Files:**
- 创建: `ssitao-code-boot/.../templates/common/dynamic.html`
- 创建: `ssitao-code-boot/.../assets/js/backend/dynamic.js`

**Step 1: 创建 dynamic.html**
```html
<div class="panel panel-default panel-intro">
    <div class="panel-heading">
        <div class="panel-lead">配置信息</div>
    </div>
    <div class="panel-body">
        <div id="toolbar" class="toolbar">
            <!-- 工具栏按钮 -->
        </div>
        <table id="table" class="table table-striped table-bordered table-hover"></table>
    </div>
</div>
```

**Step 2: 创建 dynamic.js**
```javascript
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {
    var Controller = {
        index: function () {
            // 从URL获取 entityCode 和 listCode
            var entityCode = Backend.api.getQuery('entityCode');
            var listCode = Backend.api.getQuery('listCode') || 'default';

            // 动态加载配置并渲染
            DynamicTable.loadConfig(entityCode, listCode, function(config) {
                // 渲染表格
            });
        }
    };
    return Controller;
});
```

---

## 第四阶段：集成与测试

### Task 11: 菜单配置

**目标:** 在系统菜单中添加低代码配置入口

**Files:**
- 修改: 数据库菜单表或通过后端添加

**Step 1: 添加菜单数据**
```json
{
  "menu_name": "低代码配置",
  "menu_type": "DIRECTORY",
  "path": "/meta",
  "children": [
    {"menu_name": "数据表管理", "path": "/admin/meta/table"},
    {"menu_name": "列表配置", "path": "/admin/meta/list"},
    {"menu_name": "表单配置", "path": "/admin/meta/form"},
    {"menu_name": "实体管理", "path": "/admin/meta/entity"}
  ]
}
```

---

### Task 12: 集成测试

**目标:** 验证完整功能

**Step 1: 启动应用**
```bash
mvn spring-boot:run -pl ssitao-code-boot
```

**Step 2: 测试流程**
1. 访问低代码配置菜单
2. 创建数据表配置
3. 配置列表（列、排序、筛选）
4. 配置表单（字段、验证）
5. 使用动态页面查看效果

---

## 实施顺序

1. Task 1-4: 后端 API 完善
2. Task 5-7: 前端配置页面
3. Task 8-10: 动态渲染器
4. Task 11-12: 集成测试
