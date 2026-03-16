

package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.commons.entity.DataStatus;
import org.hibernate.validator.constraints.NotBlank;
import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.entity.RecordCreationEntity;
import com.ssitao.code.commons.utils.validator.group.CreateGroup;

/**
 *
 */
public interface UserEntity extends GenericEntity<String>, RecordCreationEntity {
    String name = "name";
    String username = "username";
    String salt = "salt";
    @SuppressWarnings("all")
    String password = "password";
    String status = "status";

    void setName(String name);

    /**
     * @return 用户名, 只读, 只能新增, 不能修改
     */
    String getUsername();

    void setUsername(String username);

    @NotBlank(groups = CreateGroup.class)
    String getName();

    void setPassword(String password);

    String getPassword();

    void setSalt(String salt);

    String getSalt();

    /**
     * @return 数据状态
     * @see DataStatus
     */
    Byte getStatus();

    void setStatus(Byte status);

    @Override
    UserEntity clone();
}
