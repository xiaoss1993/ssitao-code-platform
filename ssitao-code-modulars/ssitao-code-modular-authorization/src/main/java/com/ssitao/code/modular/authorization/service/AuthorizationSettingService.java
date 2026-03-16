
package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.frame.authorization.AuthenticationInitializeService;
import com.ssitao.code.frame.authorization.Permission;
import com.ssitao.code.modular.authorization.entity.AuthorizationSettingEntity;

import java.util.List;

/**
 * 权限设置 服务类,提供通用的权限设置. 通过此服务,可实现对用户权限的多维度,自定义,可拓展的权限设置.<br>
 * 例如: 可对用户自身设置权限信息,可对角色设置权限信息,可对机构,部门设置权限信息。各个维度的权限使用{@link AuthorizationSettingTypeSupplier}进行绑定.
 *
 *
 * @see AuthorizationSettingTypeSupplier
 * @see AuthenticationInitializeService
 * @since 3.0
 */
public interface AuthorizationSettingService extends CrudService<AuthorizationSettingEntity, String> {
    /**
     * 根据类型和被设置者获取配置
     *
     * @param type       设置类型 {@link AuthorizationSettingEntity#getType()}
     * @param settingFor {@link AuthorizationSettingEntity#getSettingFor()}
     * @return 设置内容, 不存在时返回 <code>null</code>
     */
    AuthorizationSettingEntity select(String type, String settingFor);

    /**
     * 根据权限ID获取所有维度的权限设置,{@link AuthorizationSettingEntity#getDetails()}中只包含对应权限的信息,不会包含全部信息
     *
     * @param permissionId 权限ID
     * @return 配置了权限的全部权限设置信息
     * @since 3.0.9
     */
    List<AuthorizationSettingEntity> selectByPermissionId(String permissionId);

    /**
     * 合并保存权限信息,如果权限信息不存在则新增,如果已存在,则合并,而不是覆盖
     *
     * @param settings 权限信息集合
     * @since 3.0.9
     */
    void mergeSetting(List<AuthorizationSettingEntity> settings);

    /**
     * 删除权限设置的单个权限
     *
     * @param settingId    权限设置ID
     * @param permissionId 权限ID
     * @since 3.0.9
     */
    void deleteDetail(String settingId, String permissionId);

    /**
     * 根据类型和被设置者初始化对应的权限信息
     *
     * @param type       设置类型 {@link AuthorizationSettingEntity#getType()}
     * @param settingFor {@link AuthorizationSettingEntity#getSettingFor()}
     * @return 权限信息, 如果没有设置则返回<code>new java.util.ArrayList</code>
     * @see Permission
     * @since 3.0.3
     */
    List<Permission> initPermission(String type, String settingFor);
}
