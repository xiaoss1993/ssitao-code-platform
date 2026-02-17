

package com.ssitao.code.frame.mybatisflex.processor.entity;

/**
 * 表详细信息。
 *
 * @author 王帅
 * @since 2023-07-13
 */
public class TableInfo {

    /**
     * 实体类全类名。
     */
    private String entityName;

    /**
     * 实体类简单类名。
     */
    private String entitySimpleName;

    /**
     * 实体类注释。
     */
    private String entityComment;

    /**
     * 表名称。
     */
    private String tableName;

    /**
     * Schema 模式。
     */
    private String schema;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntitySimpleName() {
        return entitySimpleName;
    }

    public void setEntitySimpleName(String entitySimpleName) {
        this.entitySimpleName = entitySimpleName;
    }

    public String getEntityComment() {
        return entityComment;
    }

    public void setEntityComment(String entityComment) {
        this.entityComment = entityComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        int indexOf = tableName.indexOf(".");
        if (indexOf > 0) {
            if (schema == null || schema.trim().length() == 0) {
                this.schema = tableName.substring(0, indexOf);
                this.tableName = tableName.substring(indexOf + 1);
            } else {
                this.tableName = tableName;
            }
        } else {
            this.tableName = tableName;
        }
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

}
