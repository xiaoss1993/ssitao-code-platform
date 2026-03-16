
package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleTreeSortSupportEntity;

import java.util.List;

/**
 * 权限菜单
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleAuthorizationSettingMenuEntity extends SimpleTreeSortSupportEntity<String> implements AuthorizationSettingMenuEntity {
    private static final long serialVersionUID = 4311480526446922229L;
    //菜单id
    private String menuId;
    //设置id
    private String settingId;
    //状态
    private Byte   status;
    //其他配置内容
    private String config;
    private List<AuthorizationSettingMenuEntity> children;

}
