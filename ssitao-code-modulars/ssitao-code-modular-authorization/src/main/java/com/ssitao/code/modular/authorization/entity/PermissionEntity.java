package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.GenericEntity;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 *
 */
public interface PermissionEntity extends GenericEntity<String> {

    @Override
    @Pattern(regexp = "[a-zA-Z0-9_\\-]+")
    String getId();

    String getName();

    String getDescribe();

    Byte getStatus();

    void setStatus(Byte status);

    String getType();

    void setType(String type);

    void setName(String name);

    void setDescribe(String comment);

    List<ActionEntity> getActions();

    void setActions(List<ActionEntity> actions);

    /**
     * 此权限支持的数据权限类型,此字段只用于前端使用,在分配权限的时候,可以通过此字段来展示相应的数据权限设置,后台并没有使用此字段
     * @return 支持的数据权限类型
     */
    List<String> getSupportDataAccessTypes();

    void setSupportDataAccessTypes(List<String> supportDataAccessTypes);

    void setOptionalFields(List<OptionalField> fields);

    List<OptionalField> getOptionalFields();

    //直接关联其他权限
    List<ParentPermission> getParents();

}
