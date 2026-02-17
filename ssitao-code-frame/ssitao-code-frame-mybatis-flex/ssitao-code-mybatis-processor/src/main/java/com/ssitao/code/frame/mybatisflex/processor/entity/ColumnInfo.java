

package com.ssitao.code.frame.mybatisflex.processor.entity;

import java.util.Objects;

/**
 * 列详细信息。
 *
 * @author 王帅
 * @since 2023-07-01
 */
public class ColumnInfo implements Comparable<ColumnInfo> {

    /**
     * 属性名。
     */
    private String property;

    /**
     * 注释。
     */
    private String comment;

    /**
     * 列名。
     */
    private String column;

    /**
     * 别名。
     */
    private String[] alias;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ColumnInfo that = (ColumnInfo) o;
        return Objects.equals(property, that.property);
    }

    @Override
    public int hashCode() {
        return property != null ? property.hashCode() : 0;
    }

    @Override
    public int compareTo(ColumnInfo o) {
        // 先根据属性长度排序，属性名短的在上
        int compare = Integer.compare(property.length(), o.property.length());
        // 属性名长度一样，再按字母排序
        return compare == 0 ? property.compareTo(o.property) : compare;
    }

}
