

package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleTreeSortSupportEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单实体
 *
 *
 * @since 3.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleMenuEntity extends SimpleTreeSortSupportEntity<String>
        implements MenuEntity {

    private static final long serialVersionUID = 6942822850955576468L;
    //菜单名称
    private String name;

    //备注
    private String describe;

    //权限ID
    private String permissionId;

    //菜单对应的url
    private String url;

    //图标
    private String icon;

    //状态
    private Byte status;

    //子菜单
    private List<MenuEntity> children;

    @Override
    public SimpleMenuEntity clone() {
        SimpleMenuEntity target = (SimpleMenuEntity) super.clone();
        target.setProperties(cloneProperties());
        if (null != getChildren()) {
            target.setChildren(getChildren().stream()
                    .map(MenuEntity::clone)
                    .collect(Collectors.toList()));
        }
        return target;
    }
}
