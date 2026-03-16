
package com.ssitao.code.modular.organization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.ssitao.code.modular.common.entity.SimpleTreeSortSupportEntity;

import java.util.List;

/**
 * 职位
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplePositionEntity extends SimpleTreeSortSupportEntity<String> implements PositionEntity {
    private static final long serialVersionUID = -8912215943657734192L;
    //职位名称
    private String name;
    //部门id
    private String departmentId;
    //持有的角色
    private List<String> roles;
    //备注
    private String remark;

    private List<PositionEntity> children;

}
