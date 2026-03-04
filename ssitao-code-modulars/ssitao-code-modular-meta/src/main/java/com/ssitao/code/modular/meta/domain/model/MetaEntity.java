package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 实体定义聚合根
 * Meta领域的核心聚合根，用于定义业务实体
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaEntity {

    /**
     * 实体ID
     */
    private String id;

    /**
     * 实体编码（英文，用于代码生成）
     */
    private String entityCode;

    /**
     * 实体名称（中文，用于显示）
     */
    private String entityName;

    /**
     * 物理表名
     */
    private String tableName;

    /**
     * 实体类型：SYSTEM-系统 USER-自定义
     */
    private String entityType;

    /**
     * 分类
     */
    private String category;

    /**
     * 描述
     */
    private String description;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 模板类型：CRUD-增删改查 TREE-树形 MASTER_DETAIL-主明细
     */
    private String templateType;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除：1-是 0-否
     */
    private Boolean deleted;

    /**
     * 创建实体定义
     *
     * @param entityCode  实体编码
     * @param entityName  实体名称
     * @param tableName   物理表名
     * @param entityType  实体类型
     * @return 实体定义聚合根
     */
    public static MetaEntity create(String entityCode, String entityName, String tableName, String entityType) {
        MetaEntity entity = new MetaEntity();
        entity.setEntityCode(entityCode);
        entity.setEntityName(entityName);
        entity.setTableName(tableName);
        entity.setEntityType(entityType);
        entity.setStatus(1);
        entity.setDeleted(false);
        entity.setCreateTime(LocalDateTime.now());
        return entity;
    }

    /**
     * 更新实体定义
     *
     * @param entityName 实体名称
     * @param tableName  物理表名
     * @param category   分类
     * @param description 描述
     */
    public void update(String entityName, String tableName, String category, String description) {
        this.setEntityName(entityName);
        this.setTableName(tableName);
        this.setCategory(category);
        this.setDescription(description);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 启用实体
     */
    public void enable() {
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 禁用实体
     */
    public void disable() {
        this.setStatus(0);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除实体（逻辑删除）
     */
    public void delete() {
        this.setDeleted(true);
        this.setUpdateTime(LocalDateTime.now());
    }

}
