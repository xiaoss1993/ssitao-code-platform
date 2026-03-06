package com.ssitao.code.modular.iam.menu.application.service;

import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;

import java.util.List;

/**
 * IAM菜单应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamMenuAppService {

    /**
     * 获取当前用户的菜单
     *
     * @return 菜单列表
     */
    List<IamMenuDTO> getMyMenus();

    /**
     * 获取菜单列表
     *
     * @param parentId 父菜单ID（可选，用于获取子菜单）
     * @param menuType 菜单类型
     * @param status   状态
     * @return 菜单列表
     */
    List<IamMenuDTO> listMenus(String parentId, String menuType, Integer status);

    /**
     * 获取菜单树
     *
     * @return 菜单树
     */
    List<IamMenuDTO> getMenuTree();

    /**
     * 根据ID获取菜单
     *
     * @param id 菜单ID
     * @return 菜单DTO
     */
    IamMenuDTO getMenuById(String id);

    /**
     * 创建菜单
     *
     * @param menuDTO 菜单DTO
     * @return 创建的菜单ID
     */
    String createMenu(IamMenuDTO menuDTO);

    /**
     * 更新菜单
     *
     * @param menuDTO 菜单DTO
     */
    void updateMenu(IamMenuDTO menuDTO);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     */
    void deleteMenu(String id);

    /**
     * 批量删除菜单
     *
     * @param ids 菜单ID列表
     */
    void batchDeleteMenus(List<String> ids);
}
