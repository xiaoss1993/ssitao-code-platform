package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字段定义
 * 用于定义实体的字段属性
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaField {

    /**
     * 字段ID
     */
    private String id;

    /**
     * 实体ID
     */
    private String entityId;

    /**
     * 字段编码（英文）
     */
    private String fieldCode;

    /**
     * 字段名称（中文）
     */
    private String fieldName;

    /**
     * 字段类型
     * varchar/int/decimal/date/datetime/text/rich_text/select/multi_select/radio/checkbox/switch/file/image/user/dept/organization
     */
    private String fieldType;

    /**
     * Java类型
     * String/Integer/Long/BigDecimal/LocalDateTime/LocalDate
     */
    private String javaType;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 长度
     */
    private Integer length;

    /**
     * 小数位
     */
    private Integer decimalLength;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 是否必填
     */
    private Boolean required;

    /**
     * 是否可搜索
     */
    private Boolean searchable;

    /**
     * 是否可排序
     */
    private Boolean sortable;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 列表宽度
     */
    private Integer width;

    /**
     * 对齐方式
     * left/center/right
     */
    private String align;

    /**
     * 扩展配置(JSON)
     */
    private String config;

    /**
     * 排序
     */
    private Integer sortOrder;

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
     * 创建字段定义
     *
     * @param entityId  实体ID
     * @param fieldCode 字段编码
     * @param fieldName 字段名称
     * @param fieldType 字段类型
     * @return 字段定义
     */
    public static MetaField create(String entityId, String fieldCode, String fieldName, String fieldType) {
        MetaField field = new MetaField();
        field.setEntityId(entityId);
        field.setFieldCode(fieldCode);
        field.setFieldName(fieldName);
        field.setFieldType(fieldType);
        field.setStatus(1);
        field.setDeleted(false);
        field.setRequired(false);
        field.setSearchable(false);
        field.setSortable(false);
        field.setHidden(false);
        field.setSortOrder(0);
        field.setCreateTime(LocalDateTime.now());
        return field;
    }

    /**
     * 更新字段定义
     *
     * @param fieldName 字段名称
     * @param fieldType 字段类型
     * @param javaType Java类型
     * @param dbType 数据库类型
     * @param length 长度
     * @param decimalLength 小数位
     * @param defaultValue 默认值
     * @param required 是否必填
     * @param searchable 是否可搜索
     * @param sortable 是否可排序
     * @param hidden 是否隐藏
     * @param width 列表宽度
     * @param align 对齐方式
     * @param config 扩展配置
     */
    public void update(String fieldName, String fieldType, String javaType, String dbType,
                       Integer length, Integer decimalLength, String defaultValue,
                       Boolean required, Boolean searchable, Boolean sortable, Boolean hidden,
                       Integer width, String align, String config) {
        this.setFieldName(fieldName);
        this.setFieldType(fieldType);
        this.setJavaType(javaType);
        this.setDbType(dbType);
        this.setLength(length);
        this.setDecimalLength(decimalLength);
        this.setDefaultValue(defaultValue);
        this.setRequired(required);
        this.setSearchable(searchable);
        this.setSortable(sortable);
        this.setHidden(hidden);
        this.setWidth(width);
        this.setAlign(align);
        this.setConfig(config);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 启用字段
     */
    public void enable() {
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 禁用字段
     */
    public void disable() {
        this.setStatus(0);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除字段（逻辑删除）
     */
    public void delete() {
        this.setDeleted(true);
        this.setUpdateTime(LocalDateTime.now());
    }

}
