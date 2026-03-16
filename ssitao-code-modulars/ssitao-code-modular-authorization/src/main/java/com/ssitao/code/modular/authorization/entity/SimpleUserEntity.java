package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.ssitao.code.commons.utils.bean.ToString;
import com.ssitao.code.commons.entity.SimpleGenericEntity;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@lombok.ToString
public class SimpleUserEntity extends SimpleGenericEntity<String> implements UserEntity {
    private static final long serialVersionUID = -2625681326256009807L;

    @Override
    public String toString() {
        return "SimpleUserEntity{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", createTime=" + createTime +
                ", creatorId='" + creatorId + '\'' +
                ", status=" + status +
                '}';
    }

    private String name;

    private String username;

    @ToString.Ignore
    private String password;

    @ToString.Ignore(cover = false)
    private String salt;

    private Long createTime;

    private String creatorId;

    private Byte status;

    @Override
    public SimpleUserEntity clone() {
        return ((SimpleUserEntity) super.clone());
    }
}
