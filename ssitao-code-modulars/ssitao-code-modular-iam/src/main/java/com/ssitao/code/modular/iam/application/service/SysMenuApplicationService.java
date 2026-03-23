package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysMenu;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysMenuDTO;
import com.ssitao.code.modular.iam.application.command.CreateMenuCommand;
import com.ssitao.code.modular.iam.application.command.DeleteMenuCommand;
import com.ssitao.code.modular.iam.application.command.UpdateMenuCommand;
import com.ssitao.code.modular.iam.domain.model.SysMenuAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysMenuRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysMenuConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysMenuApplicationService {

    private final SysMenuRepository menuRepository;
    private final SysMenuConverter menuConverter;
    private final ISysMenuService menuService;

    public List<SysMenuDTO> listMenus(SysMenuDTO query) {
        SysMenu menu = new SysMenu();
        if (query != null && query.getMenuName() != null) {
            menu.setMenuName(query.getMenuName());
        }
        if (query != null && query.getVisible() != null) {
            menu.setVisible(query.getVisible());
        }
        List<SysMenu> list = menuService.selectMenuList(menu, ShiroUtils.getUserId());
        return menuConverter.toDTOListFromMenu(list);
    }

    public SysMenuDTO getMenuById(Long menuId) {
        SysMenuAggregate aggregate = menuRepository.findOne(menuId);
        if (aggregate == null) {
            throw new ServiceException("菜单不存在");
        }
        return menuConverter.toDTO(aggregate);
    }

    public List<SysMenuDTO> listAllMenus() {
        List<SysMenu> menus = menuService.selectMenuAll(ShiroUtils.getUserId());
        return menuConverter.toDTOListFromMenu(menus);
    }

    @Transactional
    public Long createMenu(CreateMenuCommand command) {
        // 创建菜单聚合根
        SysMenuAggregate aggregate = new SysMenuAggregate();
        aggregate.createMenu(
                command.getMenuName(),
                command.getParentId(),
                command.getOrderNum(),
                command.getUrl(),
                command.getTarget(),
                command.getMenuType(),
                command.getVisible(),
                command.getIsRefresh(),
                command.getPerms(),
                command.getIcon(),
                ShiroUtils.getLoginName()
        );

        // 保存
        menuRepository.save(aggregate);

        log.info("创建菜单: {}", aggregate.getMenuId());
        return aggregate.getMenuId();
    }

    @Transactional
    public void updateMenu(UpdateMenuCommand command) {
        SysMenuAggregate aggregate = menuRepository.findOne(command.getMenuId());
        if (aggregate == null) {
            throw new ServiceException("菜单不存在");
        }

        // 更新菜单信息
        aggregate.updateMenu(
                command.getMenuName(),
                command.getParentId(),
                command.getOrderNum(),
                command.getUrl(),
                command.getTarget(),
                command.getMenuType(),
                command.getVisible(),
                command.getIsRefresh(),
                command.getPerms(),
                command.getIcon(),
                ShiroUtils.getLoginName()
        );

        // 保存
        menuRepository.save(aggregate);

        log.info("更新菜单: {}", command.getMenuId());
    }

    @Transactional
    public void deleteMenus(DeleteMenuCommand command) {
        for (Long menuId : command.getMenuIds()) {
            SysMenuAggregate aggregate = menuRepository.findOne(menuId);
            if (aggregate != null) {
                // 检查是否有子菜单
                if (menuService.selectCountMenuByParentId(menuId) > 0) {
                    throw new ServiceException("存在子菜单，不允许删除");
                }
                // 检查菜单是否已分配给角色
                if (menuService.selectCountRoleMenuByMenuId(menuId) > 0) {
                    throw new ServiceException("菜单已分配，不允许删除");
                }
                menuRepository.delete(aggregate);
                log.info("删除菜单: {}", menuId);
            }
        }
    }

    @Transactional
    public void changeStatus(Long menuId, String visible) {
        SysMenuAggregate aggregate = menuRepository.findOne(menuId);
        if (aggregate == null) {
            throw new ServiceException("菜单不存在");
        }

        aggregate.changeStatus(visible, ShiroUtils.getLoginName());
        menuRepository.save(aggregate);

        log.info("修改菜单状态: {} -> {}", menuId, visible);
    }

    @Transactional
    public void updateSort(Long menuId, String orderNum) {
        SysMenuAggregate aggregate = menuRepository.findOne(menuId);
        if (aggregate == null) {
            throw new ServiceException("菜单不存在");
        }

        aggregate.setOrderNum(orderNum);
        aggregate.setUpdateBy(ShiroUtils.getLoginName());
        aggregate.setUpdateTime(new java.util.Date());
        menuRepository.save(aggregate);

        log.info("修改菜单排序: {} -> {}", menuId, orderNum);
    }
}
