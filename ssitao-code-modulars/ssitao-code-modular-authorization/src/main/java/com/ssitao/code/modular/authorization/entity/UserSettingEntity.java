package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import com.ssitao.code.frame.authorization.setting.UserSettingPermission;
import com.ssitao.code.commons.entity.SimpleGenericEntity;
import com.ssitao.code.commons.utils.validator.group.CreateGroup;

import java.util.Date;

/**
 *
 * @since 3.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserSettingEntity extends SimpleGenericEntity<String> {
    @NotBlank(groups = CreateGroup.class)
    private String userId;

    @NotBlank(groups = CreateGroup.class)
    private String key;

    @NotBlank(groups = CreateGroup.class)
    private String settingId;

    @NotBlank(groups = CreateGroup.class)
    private String setting;

    private String describe;

    private String name;

    private Date createTime;

    private Date updateTime;

    private UserSettingPermission permission;

    public boolean hasPermission(UserSettingPermission... permissions) {
        if (permission == null) {
            return true;
        }
        if (permission == UserSettingPermission.NONE) {
            return false;
        }

        return permission.in(permissions);

    }
}
