
package com.ssitao.code.frame.mybatisflex.core.relation;

import com.ssitao.code.frame.mybatisflex.annotation.RelationOneToOne;

import java.lang.reflect.Field;

public class OneToOne<SelfEntity> extends ToOneRelation<SelfEntity> {

    public OneToOne(RelationOneToOne annotation, Class<SelfEntity> entityClass, Field relationField) {
        super(getDefaultPrimaryProperty(annotation.selfField(), entityClass
                , "@RelationOneToOne.selfField can not be empty in field: \"" + entityClass.getName() + "." + relationField.getName() + "\"")
            , annotation.targetSchema()
            , annotation.targetTable()
            , annotation.targetField()
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
