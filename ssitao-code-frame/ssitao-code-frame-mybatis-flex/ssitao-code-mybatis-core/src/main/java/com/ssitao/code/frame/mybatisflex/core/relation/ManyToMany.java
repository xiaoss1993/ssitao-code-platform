
package com.ssitao.code.frame.mybatisflex.core.relation;

import com.ssitao.code.frame.mybatisflex.annotation.RelationManyToMany;

import java.lang.reflect.Field;

public class ManyToMany<SelfEntity> extends ToManyRelation<SelfEntity> {

    public ManyToMany(RelationManyToMany annotation, Class<SelfEntity> entityClass, Field relationField) {
        super(getDefaultPrimaryProperty(annotation.selfField(), entityClass, "@RelationManyToMany.selfField can not be empty in field: \"" + entityClass.getName() + "." + relationField.getName() + "\"")
            , annotation.targetSchema()
            , annotation.targetTable()
            , getDefaultPrimaryProperty(annotation.targetField(), getTargetEntityClass(entityClass, relationField), "@RelationManyToMany.targetField can not be empty in field: \"" + entityClass.getName() + "." + relationField.getName() + "\"")
            , annotation.valueField()
            , annotation.joinTable()
            , annotation.joinSelfColumn()
            , annotation.joinTargetColumn()
            , annotation.dataSource(), entityClass, relationField
            , annotation.extraCondition()
            , annotation.selectColumns());

        this.orderBy = annotation.orderBy();
        this.setMapKeyField(annotation.mapKeyField());
    }


}
