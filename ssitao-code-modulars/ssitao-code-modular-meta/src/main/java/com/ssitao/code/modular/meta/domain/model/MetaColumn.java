package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据字段聚合根
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaColumn {

    /**
     * 字段ID
     */
    private String id;

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
     * 是否主键：1-是 0-否
     */
    private Boolean isPk;

    /**
     * 是否自增：1-是 0-否
     */
    private Boolean isIncrement;

    /**
     * 是否必填：1-是 0-否
     */
    private Boolean isRequired;

    /**
     * 是否为查询条件：1-是 0-否
     */
    private Boolean isQuery;

    /**
     * 查询方式：1-精确 2-模糊 3-范围
     */
    private Integer queryType;

    /**
     * 是否为显示字段：1-是 0-否
     */
    private Boolean isDisplay;

    /**
     * 是否为列表显示：1-是 0-否
     */
    private Boolean isList;

    /**
     * 是否为表单字段：1-是 0-否
     */
    private Boolean isForm;

    /**
     * 表单类型：1-文本框 2-文本域 3-下拉框 4-单选框 5-复选框 6-日期选择 7-文件上传 8-富文本
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
    private Integer sort;

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
     * 创建元数据字段
     *
     * @param columnName 字段名称
     * @param columnType 字段类型
     * @param javaType   Java类型
     * @param javaField  Java字段名
     * @return 元数据字段聚合根
     */
    public static MetaColumn create(String columnName, String columnType,
                                     String javaType, String javaField) {
        MetaColumn column = new MetaColumn();
        column.setColumnName(columnName);
        column.setColumnType(columnType);
        column.setJavaType(javaType);
        column.setJavaField(javaField);
        column.setIsPk(false);
        column.setIsIncrement(false);
        column.setIsRequired(false);
        column.setIsQuery(false);
        column.setIsDisplay(false);
        column.setIsList(false);
        column.setIsForm(false);
        column.setDeleted(false);
        column.setCreateTime(LocalDateTime.now());
        return column;
    }

    /**
     * 设为主键
     */
    public void setAsPk() {
        this.isPk = true;
        this.isRequired = true;
    }

    /**
     * 设为自增
     */
    public void setAsIncrement() {
        this.isIncrement = true;
    }

}
