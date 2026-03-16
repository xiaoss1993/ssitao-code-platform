package com.ssitao.code.common.entity;


import lombok.ToString;

import java.io.Serializable;

/**
 * 支持树形结构，排序的实体类，要使用树形结构，排序功能的实体类直接继承该类
 */
@ToString
public abstract class SimpleTreeSortSupportEntity<PK> extends SimpleGenericEntity<PK>
        implements TreeSortSupportEntity<PK>, Serializable {

    private static final long serialVersionUID = 9137049174600768473L;
    /**
     * 父级类别
     */
    private PK parentId;

    /**
     * 树结构编码,用于快速查找, 每一层由4位字符组成,用-分割
     * 如第一层:0001 第二层:0001-0001 第三层:0001-0001-0001
     */
    private String path;

    /**
     * 排序索引
     */
    private Long sortIndex;

    private Integer level;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public PK getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(PK parentId) {
        this.parentId = parentId;
    }

    @Override
    public Long getSortIndex() {
        return sortIndex;
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }

}
