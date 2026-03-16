
package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleTreeSortSupportEntity;

import java.util.List;

/**
 * 菜单分组关联
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleMenuGroupBindEntity extends SimpleTreeSortSupportEntity<String> implements MenuGroupBindEntity {
    private static final long serialVersionUID = -8671671135008425741L;
    //状态
    private Byte                      status;
    //菜单id
    private String                    menuId;
    //分组id
    private String                    groupId;
    //子节点
    private List<MenuGroupBindEntity> children;
}
