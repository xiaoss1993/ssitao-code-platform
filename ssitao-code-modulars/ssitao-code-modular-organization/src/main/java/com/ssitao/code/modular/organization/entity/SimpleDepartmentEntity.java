
package com.ssitao.code.modular.organization.entity;

import lombok.*;
import com.ssitao.code.modular.common.entity.SimpleTreeSortSupportEntity;

import java.util.List;

/**
 * 部门
 *
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleDepartmentEntity extends SimpleTreeSortSupportEntity<String> implements DepartmentEntity {
    private static final long serialVersionUID = -2137579829759620323L;
    //名称
    private String name;
    //所在组织id
    private String orgId;
    //部门编码
    private String code;
    //是否启用
    private Byte   status;

    private List<DepartmentEntity> children;

}
