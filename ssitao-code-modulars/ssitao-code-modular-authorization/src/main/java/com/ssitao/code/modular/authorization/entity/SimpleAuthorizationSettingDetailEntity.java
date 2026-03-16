
package com.ssitao.code.modular.authorization.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.ssitao.code.commons.entity.SimpleGenericEntity;

import java.util.List;
import java.util.Set;

/**
 * 权限设置详情
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SimpleAuthorizationSettingDetailEntity extends SimpleGenericEntity<String> implements AuthorizationSettingDetailEntity {
    private static final long serialVersionUID = -4284551748747749521L;
    //权限id
    private String                 permissionId;
    //设置id
    private String                 settingId;
    //可操作类型
    private Set<String>            actions;
    //数据权限控制
    private List<DataAccessEntity> dataAccesses;
    //状态
    private Byte                   status;
    //优先级
    private Long                   priority;
    //是否合并
    private Boolean                merge;
}
