
package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleGenericEntity;

import java.util.List;

/**
 * 权限设置
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleAuthorizationSettingEntity extends SimpleGenericEntity<String> implements AuthorizationSettingEntity {
    private static final long serialVersionUID = -6036823477895044483L;
    //类型
    private String type;
    //设置给谁
    private String settingFor;
    //状态
    private Byte   status;
    //备注
    private String describe;

    private List<AuthorizationSettingMenuEntity> menus;

    private List<AuthorizationSettingDetailEntity> details;


}
