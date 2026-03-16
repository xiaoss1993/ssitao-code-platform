
package com.ssitao.code.modular.authorization.entity;

import org.hibernate.validator.constraints.NotBlank;
import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.utils.validator.group.CreateGroup;

import java.util.List;
import java.util.Set;

/**
 * 权限设置详情 实体
 *
 *
 */
public interface AuthorizationSettingDetailEntity extends GenericEntity<String>, Comparable<AuthorizationSettingDetailEntity> {

    Byte STATE_OK = 1;

   /*-------------------------------------------
    |               属性名常量                 |
    ===========================================*/
    /**
     * 权限id
     */
    String permissionId = "permissionId";
    /**
     * 设置id
     */
    String settingId    = "settingId";
    /**
     * 可操作类型
     */
    String actions      = "actions";
    /**
     * 数据权限控制
     */
    String dataAccesses = "dataAccesses";
    /**
     * 状态
     */
    String status        = "status";

    /**
     * @return 权限id
     */
    @NotBlank(groups = CreateGroup.class)
    String getPermissionId();

    /**
     * 设置 权限id
     */
    void setPermissionId(String permissionId);

    /**
     * @return 设置id
     */
    @NotBlank(groups = CreateGroup.class)
    String getSettingId();

    /**
     * 设置 设置id
     */
    void setSettingId(String settingId);

    /**
     * @return 可操作类型
     */
    Set<String> getActions();

    /**
     * 设置 可操作类型
     */
    void setActions(Set<String> actions);

    /**
     * @return 数据权限控制
     */
    List<DataAccessEntity> getDataAccesses();

    /**
     * 设置 数据权限控制
     */
    void setDataAccesses(List<DataAccessEntity> dataAccesses);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    Long getPriority();

    void setPriority(Long priority);

    Boolean getMerge();

    void setMerge(Boolean merge);

    @Override
    default int compareTo(AuthorizationSettingDetailEntity target) {
        return Long.compare(getPriority() == null ? 0 : getPriority(), target.getPriority() == null ? 0 : target.getPriority());
    }
}
