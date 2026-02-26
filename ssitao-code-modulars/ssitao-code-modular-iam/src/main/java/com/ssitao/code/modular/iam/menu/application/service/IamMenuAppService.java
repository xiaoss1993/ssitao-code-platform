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
     * @param menuType 菜单类型
     * @param status   状态
     * @return 菜单列表
     */
    List<IamMenuDTO> listMenus(String menuType, Integer status);

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
    IamMenuDTO getMenuById(Long id);
}
