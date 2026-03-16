package com.ssitao.code.modular.dynamic.form.entity;

import com.ssitao.code.commons.entity.Entity;

import java.util.List;

/**
 * 动态表单和表单列关联实体
 *
 *
 * @since 3.0
 */
public class DynamicFormColumnBindEntity implements Entity {
    private DynamicFormEntity form;

    private List<DynamicFormColumnEntity> columns;


    public DynamicFormColumnBindEntity() {
    }

    public DynamicFormColumnBindEntity(DynamicFormEntity form, List<DynamicFormColumnEntity> columns) {
        this.form = form;
        this.columns = columns;
    }

    public DynamicFormEntity getForm() {
        return form;
    }

    public void setForm(DynamicFormEntity form) {
        this.form = form;
    }

    public List<DynamicFormColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<DynamicFormColumnEntity> columns) {
        this.columns = columns;
    }
}
