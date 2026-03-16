package com.ssitao.code.common.entity;

/**
 *
 */
public interface CloneableEntity extends Entity, Cloneable {
    CloneableEntity clone();
}
