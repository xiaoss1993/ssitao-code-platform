# MetaColumn完整实现设计方案

## 1. 功能概述

MetaColumn（元数据表字段）用于描述数据库表的字段信息，作为代码生成器的前置配置。用户可以从数据库同步表结构，然后在MetaColumn中微调配置，最后生成完整的Java代码。

## 2. 架构设计

采用项目现有的DDD分层架构：

```
ssitao-code-modular-meta/
├── dal/
│   ├── dataobject/MetaColumnDO.java    # 数据对象
│   └── mapper/MetaColumnMapper.java    # MyBatis-Flex Mapper
├── domain/
│   ├── model/MetaColumn.java           # 领域模型（已有）
│   └── repository/MetaColumnRepository.java  # 仓储接口
├── infrastructure/
│   └── repository/MetaColumnRepositoryImpl.java  # 仓储实现
├── application/
│   ├── command/                        # 命令对象
│   ├── query/                          # 查询对象
│   └── service/                       # 应用服务
└── api/dto/MetaColumnDTO.java         # 数据传输对象
```

## 3. 数据库表设计

### 3.1 表结构

```sql
CREATE TABLE meta_column (
    column_id         VARCHAR(64) PRIMARY KEY COMMENT '字段ID',
    table_id          VARCHAR(64) NOT NULL COMMENT '表ID',
    column_name       VARCHAR(64) NOT NULL COMMENT '字段名称',
    column_desc       VARCHAR(256) COMMENT '字段描述',
    column_type       VARCHAR(32) NOT NULL COMMENT '字段类型',
    java_type         VARCHAR(64) NOT NULL COMMENT 'Java类型',
    java_field        VARCHAR(64) NOT NULL COMMENT 'Java字段名',
    is_pk             TINYINT(1) DEFAULT 0 COMMENT '是否主键',
    is_increment      TINYINT(1) DEFAULT 0 COMMENT '是否自增',
    is_required       TINYINT(1) DEFAULT 0 COMMENT '是否必填',
    is_query          TINYINT(1) DEFAULT 0 COMMENT '是否为查询条件',
    query_type        INT DEFAULT 1 COMMENT '查询方式:1-精确 2-模糊 3-范围',
    is_display        TINYINT(1) DEFAULT 0 COMMENT '是否为显示字段',
    is_list           TINYINT(1) DEFAULT 0 COMMENT '是否为列表显示',
    is_form           TINYINT(1) DEFAULT 0 COMMENT '是否为表单字段',
    form_type         INT DEFAULT 1 COMMENT '表单类型:1-文本框 2-文本域...',
    dict_type         VARCHAR(64) COMMENT '字典类型',
    default_value     VARCHAR(256) COMMENT '默认值',
    column_sort       INT DEFAULT 0 COMMENT '排序',
    remark            VARCHAR(512) COMMENT '备注',
    tenant_id         VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    create_time       DATETIME COMMENT '创建时间',
    create_user_id    VARCHAR(64) COMMENT '创建人ID',
    modify_time       DATETIME COMMENT '修改时间',
    modify_user_id    VARCHAR(64) COMMENT '修改人ID',
    is_deleted        TINYINT(1) DEFAULT 0 COMMENT '是否删除',
    version           INT DEFAULT 0 COMMENT '版本号',
    INDEX idx_table_id (table_id),
    INDEX idx_tenant_id (tenant_id)
) COMMENT '元数据表字段定义';
```

## 4. API接口设计

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | /meta/column/create | 创建字段 |
| PUT | /meta/column/update | 更新字段 |
| DELETE | /meta/column/delete | 删除字段 |
| GET | /meta/column/get | 获取详情 |
| GET | /meta/column/list | 获取列表 |
| GET | /meta/column/page | 分页查询 |
| GET | /meta/column/list-by-table/{tableId} | 根据表ID获取字段列表 |
| POST | /meta/column/batch-create | 批量创建字段 |
| POST | /meta/column/sync-from-db | 从数据库同步字段 |

## 5. 核心功能

1. **CRUD操作** - 字段的创建、查询、更新、删除
2. **批量操作** - 支持批量创建字段
3. **数据库同步** - 从数据库表读取字段信息
4. **租户隔离** - 多租户数据隔离

## 6. 技术特性

- 使用MyBatis-Flex作为ORM框架
- 遵循项目现有的DDD分层规范
- 使用CommonResult统一响应格式
- 使用Swagger/OpenAPI注解文档化

## 7. 依赖关系

- ssitao-code-commons - 公共模块
- ssitao-code-frame-mybatis-flex - MyBatis-Flex框架
