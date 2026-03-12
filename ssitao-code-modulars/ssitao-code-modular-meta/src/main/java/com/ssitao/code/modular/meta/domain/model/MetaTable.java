package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据表聚合根
 * Meta领域的核心聚合根
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaTable {

    /**
     * 表ID
     */
    private String id;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableDesc;

    /**
     * 表类型：1-业务表 2-字典表 3-树形表 4-关联表
     */
    private Integer tableType;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 类名(首字母大写)
     */
    private String className;

    /**
     * 类描述
     */
    private String classDesc;

    /**
     * 实体类名称
     */
    private String entityName;

    /**
     * 作者
     */
    private String author;

    /**
     * 是否启用：1-是 0-否
     */
    private Boolean enabled;

    /**
     * 是否生成：1-是 0-否
     */
    private Boolean generated;

    /**
     * 生成路径
     */
    private String genPath;

    /**
     * 备注
     */
    private String remark;

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
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建元数据表
     *
     * @param tableName 表名称
     * @param tableDesc 表描述
     * @param tableType 表类型
     * @return 元数据表聚合根
     */
    public static MetaTable create(String tableName, String tableDesc, Integer tableType) {
        MetaTable table = new MetaTable();
        table.setTableName(tableName);
        table.setTableDesc(tableDesc);
        table.setTableType(tableType);
        table.setEnabled(true);
        table.setGenerated(false);
        table.setDeleted(false);
        table.setCreateTime(LocalDateTime.now());
        return table;
    }

    /**
     * 生成代码
     */
    public void generate() {
        this.generated = true;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 启用表
     */
    public void enable() {
        this.enabled = true;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 禁用表
     */
    public void disable() {
        this.enabled = false;
        this.updateTime = LocalDateTime.now();
    }

}
