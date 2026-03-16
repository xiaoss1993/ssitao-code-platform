package com.ssitao.code.common.entity;

/**
 * 逻辑删除
 *
 *
 * @since 3.0.6
 */
public interface LogicalDeleteEntity {

    Boolean getDeleted();

    void setDeleted(Boolean deleted);

    Long getDeleteTime();

    void setDeleteTime(Long deleteTime);

}
