package com.ssitao.code.common.entity;

/**
 * 支持树形结构，排序的实体类，要使用树形结构，排序功能的实体类直接继承该类
 */
public interface TreeSortSupportEntity<PK> extends TreeSupportEntity<PK>, SortSupportEntity {
}
