
package com.ssitao.code.modular.authorization.entity;

import com.ssitao.code.modular.authorization.service.AuthorizationSettingTypeSupplier;
import org.hibernate.validator.constraints.NotBlank;
import com.ssitao.code.commons.entity.GenericEntity;
import com.ssitao.code.commons.utils.validator.group.CreateGroup;

import java.util.List;

/**
 * 权限设置 实体
 *
 *
 */
public interface AuthorizationSettingEntity extends GenericEntity<String> {
  /*-------------------------------------------
    |                属性名常量                |
    ===========================================*/
    /**
     * 设置类型(维度),如: role
     *
     * @see AuthorizationSettingTypeSupplier
     */
    String type = "type";
    /**
     * 设置给谁,通常是{@link this#type}对应的id
     * @see AuthorizationSettingTypeSupplier
     */
    String settingFor = "settingFor";
    /**
     * 状态
     */
    String status = "status";
    /**
     * 备注
     */
    String describe = "describe";

    /**
     * @return 类型
     */
    @NotBlank(groups = CreateGroup.class)
    String getType();

    /**
     * 设置 类型
     */
    void setType(String type);

    /**
     * @return 设置给谁
     */
    @NotBlank(groups = CreateGroup.class)
    String getSettingFor();

    /**
     * 设置 设置给谁
     */
    void setSettingFor(String settingFor);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    /**
     * @return 备注
     */
    String getDescribe();

    /**
     * 设置 备注
     */
    void setDescribe(String describe);

    List<AuthorizationSettingDetailEntity> getDetails();

    void setDetails(List<AuthorizationSettingDetailEntity> details);

    List<AuthorizationSettingMenuEntity> getMenus();

    void setMenus(List<AuthorizationSettingMenuEntity> menus);
}
