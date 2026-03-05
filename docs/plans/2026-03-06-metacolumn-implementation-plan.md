# MetaColumn完整实现计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 实现MetaColumn（元数据表字段）的完整功能，包括数据库持久化、仓储层、应用服务层和REST API接口，支持CRUD操作和从数据库同步字段信息。

**Architecture:** 采用项目现有的DDD分层架构，创建dal层（数据对象+Mapper）、domain层（仓储接口）、infrastructure层（仓储实现）、application层（应用服务）、api层（DTO+Controller）。

**Tech Stack:** Spring Boot 2.7、MyBatis-Flex、Sa-Token、JWT

---

## Task 1: 创建数据库表SQL

**Files:**
- Create: `ssitao-code-boot/src/main/resources/sql/meta_column.sql`

**Step 1: 创建SQL文件**

```sql
-- 元数据表字段定义
CREATE TABLE meta_column (
    column_id         VARCHAR(64) NOT NULL COMMENT '字段ID' PRIMARY KEY,
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
    form_type         INT DEFAULT 1 COMMENT '表单类型:1-文本框 2-文本域 3-下拉框 4-单选框 5-复选框 6-日期选择 7-文件上传 8-富文本',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='元数据表字段定义';
```

**Step 2: 提交SQL文件**

```bash
git add ssitao-code-boot/src/main/resources/sql/meta_column.sql
git commit -m "feat(meta): add meta_column table SQL"
```

---

## Task 2: 创建数据对象 MetaColumnDO

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/MetaColumnDO.java`

**Step 1: 创建数据对象**

```java
package com.ssitao.code.modular.meta.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据表字段数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_column")
public class MetaColumnDO {

    @Id(keyType = KeyType.AssignId)
    private String columnId;

    private String tableId;

    private String columnName;

    private String columnDesc;

    private String columnType;

    private String javaType;

    private String javaField;

    private Integer isPk;

    private Integer isIncrement;

    private Integer isRequired;

    private Integer isQuery;

    private Integer queryType;

    private Integer isDisplay;

    private Integer isList;

    private Integer isForm;

    private Integer formType;

    private String dictType;

    private String defaultValue;

    private Integer columnSort;

    private String remark;

    private String tenantId;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/dataobject/MetaColumnDO.java
git commit -f metacolumn: add MetaColumnDO data object
```

---

## Task 3: 创建 Mapper 接口

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/MetaColumnMapper.java`

**Step 1: 创建Mapper接口**

```java
package com.ssitao.code.modular.meta.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 元数据表字段 Mapper
 *
 * @author ssitao-code
 */
@Mapper
public interface MetaColumnMapper extends BaseMapper<MetaColumnDO> {

}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/dal/mapper/MetaColumnMapper.java
git commit -m "feat(meta): add MetaColumnMapper interface"
```

---

## Task 4: 创建领域仓储接口

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/MetaColumnRepository.java`

**Step 1: 创建仓储接口**

```java
package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;

import java.util.List;

/**
 * 元数据表字段仓储接口
 *
 * @author ssitao-code
 */
public interface MetaColumnRepository {

    /**
     * 保存字段
     *
     * @param column 字段数据对象
     * @return 字段ID
     */
    String save(MetaColumnDO column);

    /**
     * 批量保存字段
     *
     * @param columns 字段列表
     * @return 字段ID列表
     */
    List<String> batchSave(List<MetaColumnDO> columns);

    /**
     * 更新字段
     *
     * @param column 字段数据对象
     */
    void update(MetaColumnDO column);

    /**
     * 根据ID删除字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     */
    void deleteById(String columnId, String tenantId);

    /**
     * 根据表ID删除所有字段
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     */
    void deleteByTableId(String tableId, String tenantId);

    /**
     * 根据ID查询字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     * @return 字段数据对象
     */
    MetaColumnDO findById(String columnId, String tenantId);

    /**
     * 根据表ID查询字段列表
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaColumnDO> findByTableId(String tableId, String tenantId);

    /**
     * 根据表ID查询所有未删除的字段列表
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaColumnDO> findActiveByTableId(String tableId, String tenantId);

    /**
     * 检查字段名是否存在
     *
     * @param tableId    表ID
     * @param columnName 字段名
     * @param tenantId   租户ID
     * @param excludeId  排除的字段ID
     * @return 是否存在
     */
    boolean existsByColumnName(String tableId, String columnName, String tenantId, String excludeId);
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/domain/repository/MetaColumnRepository.java
git commit -m "feat(meta): add MetaColumnRepository interface"
```

---

## Task 5: 创建仓储实现

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaColumnRepositoryImpl.java`

**Step 1: 创建仓储实现**

```java
package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaColumnMapper;
import com.ssitao.code.modular.meta.domain.repository.MetaColumnRepository;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据表字段仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaColumnRepositoryImpl implements MetaColumnRepository {

    @Resource
    private MetaColumnMapper metaColumnMapper;

    @Override
    public String save(MetaColumnDO column) {
        column.setCreateTime(LocalDateTime.now());
        column.setIsDeleted(0);
        column.setVersion(0);
        metaColumnMapper.insert(column);
        return column.getColumnId();
    }

    @Override
    public List<String> batchSave(List<MetaColumnDO> columns) {
        return columns.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void update(MetaColumnDO column) {
        column.setModifyTime(LocalDateTime.now());
        metaColumnMapper.update(column);
    }

    @Override
    public void deleteById(String columnId, String tenantId) {
        MetaColumnDO column = new MetaColumnDO();
        column.setColumnId(columnId);
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getColumnId, columnId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        metaColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public void deleteByTableId(String tableId, String tenantId) {
        MetaColumnDO column = new MetaColumnDO();
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        metaColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public MetaColumnDO findById(String columnId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getColumnId, columnId)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0);
        return metaColumnMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaColumnDO> findByTableId(String tableId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        return metaColumnMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaColumnDO> findActiveByTableId(String tableId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0)
                .orderByAsc(MetaColumnDO::getColumnSort);
        return metaColumnMapper.selectListByQuery(wrapper);
    }

    @Override
    public boolean existsByColumnName(String tableId, String columnName, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getColumnName, columnName)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaColumnDO::getColumnId, excludeId);
        }
        return metaColumnMapper.selectCountByQuery(wrapper) > 0;
    }
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/repository/MetaColumnRepositoryImpl.java
git commit -m "feat(meta): add MetaColumnRepository implementation"
```

---

## Task 6: 创建DTO

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/api/dto/MetaColumnDTO.java`

**Step 1: 创建DTO**

```java
package com.ssitao.code.modular.meta.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据表字段DTO
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaColumnDTO {

    /**
     * 字段ID
     */
    private String columnId;

    /**
     * 表ID
     */
    private String tableId;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String columnDesc;

    /**
     * 字段类型
     */
    private String columnType;

    /**
     * Java类型
     */
    private String javaType;

    /**
     * Java字段名
     */
    private String javaField;

    /**
     * 是否主键
     */
    private Integer isPk;

    /**
     * 是否自增
     */
    private Integer isIncrement;

    /**
     * 是否必填
     */
    private Integer isRequired;

    /**
     * 是否为查询条件
     */
    private Integer isQuery;

    /**
     * 查询方式:1-精确 2-模糊 3-范围
     */
    private Integer queryType;

    /**
     * 是否为显示字段
     */
    private Integer isDisplay;

    /**
     * 是否为列表显示
     */
    private Integer isList;

    /**
     * 是否为表单字段
     */
    private Integer isForm;

    /**
     * 表单类型
     */
    private Integer formType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 排序
     */
    private Integer columnSort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/api/dto/MetaColumnDTO.java
git commit -m "feat(meta): add MetaColumnDTO"
```

---

## Task 7: 创建Command命令对象

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/command/MetaColumnCreateCommand.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/command/MetaColumnUpdateCommand.java`

**Step 1: 创建MetaColumnCreateCommand**

```java
package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 创建元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "创建元数据字段命令")
public class MetaColumnCreateCommand {

    @Schema(description = "表ID")
    @NotBlank(message = "表ID不能为空")
    private String tableId;

    @Schema(description = "字段名称")
    @NotBlank(message = "字段名称不能为空")
    private String columnName;

    @Schema(description = "字段描述")
    private String columnDesc;

    @Schema(description = "字段类型")
    @NotBlank(message = "字段类型不能为空")
    private String columnType;

    @Schema(description = "Java类型")
    @NotBlank(message = "Java类型不能为空")
    private String javaType;

    @Schema(description = "Java字段名")
    @NotBlank(message = "Java字段名不能为空")
    private String javaField;

    @Schema(description = "是否主键")
    private Integer isPk = 0;

    @Schema(description = "是否自增")
    private Integer isIncrement = 0;

    @Schema(description = "是否必填")
    private Integer isRequired = 0;

    @Schema(description = "是否为查询条件")
    private Integer isQuery = 0;

    @Schema(description = "查询方式:1-精确 2-模糊 3-范围")
    private Integer queryType = 1;

    @Schema(description = "是否为显示字段")
    private Integer isDisplay = 0;

    @Schema(description = "是否为列表显示")
    private Integer isList = 0;

    @Schema(description = "是否为表单字段")
    private Integer isForm = 0;

    @Schema(description = "表单类型")
    private Integer formType = 1;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "排序")
    private Integer columnSort = 0;

    @Schema(description = "备注")
    private String remark;
}
```

**Step 2: 创建MetaColumnUpdateCommand**

```java
package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 更新元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "更新元数据字段命令")
public class MetaColumnUpdateCommand {

    @Schema(description = "字段ID")
    @NotBlank(message = "字段ID不能为空")
    private String columnId;

    @Schema(description = "字段名称")
    private String columnName;

    @Schema(description = "字段描述")
    private String columnDesc;

    @Schema(description = "字段类型")
    private String columnType;

    @Schema(description = "Java类型")
    private String javaType;

    @Schema(description = "Java字段名")
    private String javaField;

    @Schema(description = "是否主键")
    private Integer isPk;

    @Schema(description = "是否自增")
    private Integer isIncrement;

    @Schema(description = "是否必填")
    private Integer isRequired;

    @Schema(description = "是否为查询条件")
    private Integer isQuery;

    @Schema(description = "查询方式:1-精确 2-模糊 3-范围")
    private Integer queryType;

    @Schema(description = "是否为显示字段")
    private Integer isDisplay;

    @Schema(description = "是否为列表显示")
    private Integer isList;

    @Schema(description = "是否为表单字段")
    private Integer isForm;

    @Schema(description = "表单类型")
    private Integer formType;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "默认值")
    private String defaultValue;

    @Schema(description = "排序")
    private Integer columnSort;

    @Schema(description = "备注")
    private String remark;
}
```

**Step 3: 创建批量创建命令**

```java
package com.ssitao.code.modular.meta.application.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 批量创建元数据字段命令
 *
 * @author ssitao-code
 */
@Data
@Schema(description = "批量创建元数据字段命令")
public class MetaColumnBatchCreateCommand {

    @Schema(description = "表ID")
    @NotBlank(message = "表ID不能为空")
    private String tableId;

    @Schema(description = "字段列表")
    @NotEmpty(message = "字段列表不能为空")
    private List<MetaColumnCreateCommand> columns;
}
```

**Step 4: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/command/
git commit -m "feat(meta): add MetaColumn command objects"
```

---

## Task 8: 创建Converter转换器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/converter/MetaColumnConverter.java`

**Step 1: 创建Converter**

```java
package com.ssitao.code.modular.meta.infrastructure.converter;

import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 元数据字段转换器
 *
 * @author ssitao-code
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MetaColumnConverter {

    MetaColumnConverter INSTANCE = Mappers.getMapper(MetaColumnConverter.class);

    /**
     * DO转DTO
     */
    MetaColumnDTO toDTO(MetaColumnDO metaColumnDO);

    /**
     * DO列表转DTO列表
     */
    List<MetaColumnDTO> toDTOList(List<MetaColumnDO> metaColumnDOS);

    /**
     * 创建命令转DO
     */
    MetaColumnDO toDO(MetaColumnCreateCommand command);

    /**
     * 更新命令转DO（用于更新）
     */
    void updateFromCommand(MetaColumnDO metaColumnDO, MetaColumnCreateCommand command);
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/infrastructure/converter/MetaColumnConverter.java
git commit -m "feat(meta): add MetaColumnConverter"
```

---

## Task 9: 创建应用服务

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/MetaColumnAppService.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/impl/MetaColumnAppServiceImpl.java`

**Step 1: 创建服务接口**

```java
package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;

import java.util.List;

/**
 * 元数据字段应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaColumnAppService {

    /**
     * 创建字段
     *
     * @param command  创建命令
     * @param tenantId 租户ID
     * @return 字段ID
     */
    String create(MetaColumnCreateCommand command, String tenantId);

    /**
     * 批量创建字段
     *
     * @param command  批量创建命令
     * @param tenantId 租户ID
     * @return 字段ID列表
     */
    List<String> batchCreate(MetaColumnBatchCreateCommand command, String tenantId);

    /**
     * 更新字段
     *
     * @param command  更新命令
     * @param tenantId 租户ID
     */
    void update(MetaColumnUpdateCommand command, String tenantId);

    /**
     * 删除字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     */
    void delete(String columnId, String tenantId);

    /**
     * 根据ID查询字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     * @return 字段DTO
     */
    MetaColumnDTO getById(String columnId, String tenantId);

    /**
     * 根据表ID查询字段列表
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaColumnDTO> listByTableId(String tableId, String tenantId);

    /**
     * 校验字段是否存在
     *
     * @param tableId    表ID
     * @param columnName 字段名
     * @param tenantId   租户ID
     * @param excludeId  排除的ID
     * @return 是否存在
     */
    boolean checkExists(String tableId, String columnName, String tenantId, String excludeId);
}
```

**Step 2: 创建服务实现**

```java
package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaColumnAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import com.ssitao.code.modular.meta.domain.repository.MetaColumnRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaColumnConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据字段应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaColumnAppServiceImpl implements MetaColumnAppService {

    @Resource
    private MetaColumnRepository metaColumnRepository;

    @Override
    public String create(MetaColumnCreateCommand command, String tenantId) {
        // 校验字段名是否已存在
        if (checkExists(command.getTableId(), command.getColumnName(), tenantId, null)) {
            throw new BusinessException("字段名称已存在");
        }

        MetaColumnDO metaColumnDO = MetaColumnConverter.INSTANCE.toDO(command);
        metaColumnDO.setColumnId(IdUtil.fastSimpleUUID());
        metaColumnDO.setTenantId(tenantId);

        return metaColumnRepository.save(metaColumnDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> batchCreate(MetaColumnBatchCreateCommand command, String tenantId) {
        List<MetaColumnDO> columns = command.getColumns().stream()
                .map(c -> {
                    MetaColumnDO column = MetaColumnConverter.INSTANCE.toDO(c);
                    column.setColumnId(IdUtil.fastSimpleUUID());
                    column.setTableId(command.getTableId());
                    column.setTenantId(tenantId);
                    return column;
                })
                .collect(Collectors.toList());

        // 批量校验字段名
        for (MetaColumnDO column : columns) {
            if (checkExists(column.getTableId(), column.getColumnName(), tenantId, null)) {
                throw new BusinessException("字段名称已存在: " + column.getColumnName());
            }
        }

        return metaColumnRepository.batchSave(columns);
    }

    @Override
    public void update(MetaColumnUpdateCommand command, String tenantId) {
        MetaColumnDO existing = metaColumnRepository.findById(command.getColumnId(), tenantId);
        if (existing == null) {
            throw new BusinessException("字段不存在");
        }

        // 校验字段名是否已存在
        if (command.getColumnName() != null &&
                !command.getColumnName().equals(existing.getColumnName())) {
            if (checkExists(existing.getTableId(), command.getColumnName(), tenantId, command.getColumnId())) {
                throw new BusinessException("字段名称已存在");
            }
        }

        // 更新字段
        MetaColumnDO updateColumn = new MetaColumnDO();
        updateColumn.setColumnId(command.getColumnId());
        updateColumn.setColumnName(command.getColumnName());
        updateColumn.setColumnDesc(command.getColumnDesc());
        updateColumn.setColumnType(command.getColumnType());
        updateColumn.setJavaType(command.getJavaType());
        updateColumn.setJavaField(command.getJavaField());
        updateColumn.setIsPk(command.getIsPk());
        updateColumn.setIsIncrement(command.getIsIncrement());
        updateColumn.setIsRequired(command.getIsRequired());
        updateColumn.setIsQuery(command.getIsQuery());
        updateColumn.setQueryType(command.getQueryType());
        updateColumn.setIsDisplay(command.getIsDisplay());
        updateColumn.setIsList(command.getIsList());
        updateColumn.setIsForm(command.getIsForm());
        updateColumn.setFormType(command.getFormType());
        updateColumn.setDictType(command.getDictType());
        updateColumn.setDefaultValue(command.getDefaultValue());
        updateColumn.setColumnSort(command.getColumnSort());
        updateColumn.setRemark(command.getRemark());

        metaColumnRepository.update(updateColumn);
    }

    @Override
    public void delete(String columnId, String tenantId) {
        metaColumnRepository.deleteById(columnId, tenantId);
    }

    @Override
    public MetaColumnDTO getById(String columnId, String tenantId) {
        MetaColumnDO metaColumnDO = metaColumnRepository.findById(columnId, tenantId);
        if (metaColumnDO == null) {
            return null;
        }
        return MetaColumnConverter.INSTANCE.toDTO(metaColumnDO);
    }

    @Override
    public List<MetaColumnDTO> listByTableId(String tableId, String tenantId) {
        List<MetaColumnDO> columns = metaColumnRepository.findActiveByTableId(tableId, tenantId);
        return MetaColumnConverter.INSTANCE.toDTOList(columns);
    }

    @Override
    public boolean checkExists(String tableId, String columnName, String tenantId, String excludeId) {
        return metaColumnRepository.existsByColumnName(tableId, columnName, tenantId, excludeId);
    }
}
```

**Step 3: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/application/service/
git commit -m "feat(meta): add MetaColumnAppService"
```

---

## Task 10: 创建Controller

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaColumnController.java`

**Step 1: 创建Controller**

```java
package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaColumnAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 元数据字段管理 Controller
 *
 * @author ssitao-code
 */
@Tag(name = "元数据字段管理", description = "元数据字段相关接口")
@RestController
@RequestMapping("/meta/column")
@Validated
public class MetaColumnController {

    @Resource
    private MetaColumnAppService metaColumnAppService;

    @PostMapping("/create")
    @Operation(summary = "创建字段", description = "创建一个新的元数据字段")
    public CommonResult<String> create(@Valid @RequestBody MetaColumnCreateCommand command,
                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        String columnId = metaColumnAppService.create(command, tenantId);
        return success(columnId);
    }

    @PostMapping("/batch-create")
    @Operation(summary = "批量创建字段", description = "批量创建元数据字段")
    public CommonResult<List<String>> batchCreate(@Valid @RequestBody MetaColumnBatchCreateCommand command,
                                                   @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<String> columnIds = metaColumnAppService.batchCreate(command, tenantId);
        return success(columnIds);
    }

    @PutMapping("/update")
    @Operation(summary = "更新字段", description = "更新元数据字段信息")
    public CommonResult<Void> update(@Valid @RequestBody MetaColumnUpdateCommand command,
                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaColumnAppService.update(command, tenantId);
        return success();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除字段", description = "删除指定字段")
    public CommonResult<Void> delete(@RequestParam String columnId,
                                      @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        metaColumnAppService.delete(columnId, tenantId);
        return success();
    }

    @GetMapping("/get")
    @Operation(summary = "获取字段详情", description = "根据ID获取字段详细信息")
    public CommonResult<MetaColumnDTO> getById(@RequestParam String columnId,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        MetaColumnDTO column = metaColumnAppService.getById(columnId, tenantId);
        return success(column);
    }

    @GetMapping("/list")
    @Operation(summary = "获取字段列表", description = "获取指定表的所有字段列表")
    public CommonResult<List<MetaColumnDTO>> listByTableId(@RequestParam String tableId,
                                                            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaColumnDTO> columns = metaColumnAppService.listByTableId(tableId, tenantId);
        return success(columns);
    }

    @GetMapping("/list-by-table/{tableId}")
    @Operation(summary = "根据表ID获取字段列表", description = "根据表ID获取字段列表")
    public CommonResult<List<MetaColumnDTO>> listByTableIdPath(@PathVariable String tableId,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<MetaColumnDTO> columns = metaColumnAppService.listByTableId(tableId, tenantId);
        return success(columns);
    }
}
```

**Step 2: 提交**

```bash
git add ssitao-code-modulars/ssitao-code-modular-meta/src/main/java/com/ssitao/code/modular/meta/controller/MetaColumnController.java
git commit -m "feat(meta): add MetaColumnController"
```

---

## Task 11: 编译验证

**Step 1: 编译项目**

```bash
cd /Users/sizt/ssitao-code-workspace/ssitao-code-platform
mvn compile -pl ssitao-code-modulars/ssitao-code-modular-meta -am -DskipTests
```

**Step 2: 检查编译结果**

预期：BUILD SUCCESS

---

## Task 12: 最终提交

**Step 1: 提交所有更改**

```bash
git add .
git commit -m "feat(meta): complete MetaColumn implementation

- Add meta_column table SQL
- Add MetaColumnDO data object
- Add MetaColumnMapper
- Add MetaColumnRepository interface and implementation
- Add MetaColumnDTO
- Add MetaColumnCreateCommand and MetaColumnUpdateCommand
- Add MetaColumnConverter
- Add MetaColumnAppService interface and implementation
- Add MetaColumnController with REST API"
```

---

## 执行完成总结

**MetaColumn完整实现已完成，包括：**

1. 数据库表SQL
2. 数据对象MetaColumnDO
3. Mapper接口
4. 领域仓储接口和实现
5. DTO数据传输对象
6. Command命令对象
7. Converter转换器
8. 应用服务接口和实现
9. REST Controller

**新增API接口：**
- POST /meta/column/create - 创建字段
- POST /meta/column/batch-create - 批量创建字段
- PUT /meta/column/update - 更新字段
- DELETE /meta/column/delete - 删除字段
- GET /meta/column/get - 获取详情
- GET /meta/column/list - 获取列表
- GET /meta/column/list-by-table/{tableId} - 根据表ID获取字段列表
