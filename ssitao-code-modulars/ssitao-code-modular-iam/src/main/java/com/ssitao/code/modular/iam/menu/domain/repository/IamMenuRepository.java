package com.ssitao.code.modular.iam.menu.domain.repository;

import com.ssitao.code.modular.iam.menu.domain.model.IamMenu;

import java.util.List;
import java.util.Optional;

/**
 * IAM菜单仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamMenuRepository {

    /**
     * 查询所有菜单
     *
     * @param tenantId 租户ID
     * @return 菜单列表
     */
    List<IamMenu> findAll(String tenantId);

    /**
     * 根据ID查询菜单
     *
     * @param id 菜单ID
     * @param tenantId 租户ID
     * @return 菜单对象
     */
    Optional<IamMenu> findById(String id, String tenantId);

    /**
     * 保存菜单
     *
     * @param menu 菜单对象
     * @return 菜单ID
     */
    String save(IamMenu menu);

    /**
     * 更新菜单
     *
     * @param menu 菜单对象
     */
    void update(IamMenu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据菜单类型查询菜单
     *
     * @param menuType 菜单类型
     * @param tenantId 租户ID
     * @return 菜单列表
     */
    List<IamMenu> findByMenuType(String menuType, String tenantId);

    /**
     * 根据状态查询菜单
     *
     * @param status 状态
     * @param tenantId 租户ID
     * @return 菜单列表
     */
    List<IamMenu> findByStatus(Integer status, String tenantId);

    /**
     * 根据父菜单ID查询子菜单
     *
     * @param parentId 父菜单ID
     * @param tenantId 租户ID
     * @return 子菜单列表
     */
    List<IamMenu> findByParentId(String parentId, String tenantId);

}
