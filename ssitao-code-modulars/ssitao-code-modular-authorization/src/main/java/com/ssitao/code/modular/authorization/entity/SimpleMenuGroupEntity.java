
package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleGenericEntity;

import java.util.List;

/**
 * 菜单分组
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleMenuGroupEntity extends SimpleGenericEntity<String> implements MenuGroupEntity {
    private static final long serialVersionUID = 349586524046467254L;
    //分组名称
    private String  name;
    //分组描述
    private String  describe;
    //是否默认
    private Boolean defaultGroup;
    //状态
    private Byte    status;

    private List<MenuGroupEntity> children;

    private List<MenuGroupBindEntity> bindInfo;
}
