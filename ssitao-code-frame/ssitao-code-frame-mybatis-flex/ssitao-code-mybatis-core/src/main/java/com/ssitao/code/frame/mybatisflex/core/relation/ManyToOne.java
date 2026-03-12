
package com.ssitao.code.frame.mybatisflex.core.relation;

import com.ssitao.code.frame.mybatisflex.annotation.RelationManyToOne;

import java.lang.reflect.Field;

public class ManyToOne<SelfEntity> extends ToOneRelation<SelfEntity> {

    public ManyToOne(RelationManyToOne annotation, Class<SelfEntity> entityClass, Field relationField) {
        super(annotation.selfField()
            , annotation.targetSchema()
            , annotation.targetTable()
            , getDefaultPrimaryProperty(annotation.targetField(), getTargetEntityClass(entityClass, relationField)
                , "@RelationManyToOne.selfField can not be empty in field: \"" + entityClass.getName() + "." + relationField.getName() + "\"")
            , annotation.valueField()
            , annotation.joinTable()
            , annotation.joinSelfColumn()
            , annotation.joinTargetColumn()
            , annotation.dataSource()
            , entityClass
            , relationField
            , annotation.extraCondition()
            , annotation.selectColumns());
    }


}
