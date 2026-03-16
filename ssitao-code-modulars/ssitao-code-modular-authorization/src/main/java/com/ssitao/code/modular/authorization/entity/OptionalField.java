package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.CloneableEntity;

/**
 * TODO 完成注释
 *
 *
 */
public class OptionalField implements CloneableEntity {
    private String name;

    private String describe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public OptionalField clone() {
        OptionalField optionalField = new OptionalField();
        optionalField.setName(name);
        optionalField.setDescribe(describe);
        return optionalField;
    }
}
