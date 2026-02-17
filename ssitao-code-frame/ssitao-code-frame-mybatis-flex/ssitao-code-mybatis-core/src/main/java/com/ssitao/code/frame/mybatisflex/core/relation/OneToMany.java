
package com.ssitao.code.frame.mybatisflex.core.relation;

import com.ssitao.code.frame.mybatisflex.annotation.RelationOneToMany;

import java.lang.reflect.Field;

public class OneToMany<SelfEntity> extends ToManyRelation<SelfEntity> {


    public OneToMany(RelationOneToMany annotation, Class<SelfEntity> entityClass, Field relationField) {
        super(getDefaultPrimaryProperty(annotation.selfField(), entityClass, "@RelationOneToMany.selfField can not be empty in field: \"" + entityClass.getName() + "." + relationField.getName() + "\"")
            , annotation.targetSchema()
            , annotation.targetTable()
            , annotation.targetField()
            , annotation.valueField()
            , annotation.joinTable()
            , annotation.joinSelfColumn()
            , annotation.joinTargetColumn()
            , annotation.dataSource(), entityClass, relationField
            , annotation.extraCondition()
            , annotation.selectColumns());

        this.selfValueSplitBy = annotation.selfValueSplitBy();
        this.orderBy = annotation.orderBy();
        this.limit = annotation.limit();

        this.setMapKeyField(annotation.mapKeyField());
    }


}
