package com.ssitao.code.modular.iam.menu.application.service.impl;

import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IAM菜单应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamMenuAppServiceImpl implements IamMenuAppService {

    @Override
    public List<IamMenuDTO> getMyMenus() {
        // TODO: 从当前用户获取权限后过滤菜单
        // 这里暂时返回模拟数据
        List<IamMenuDTO> menus = new ArrayList<>();

        // 首页
        IamMenuDTO dashboard = createMenu(1L, 0L, "控制台", "menu", "/dashboard",
                "dashboard/index", "el-icon-monitor", 1, "system:user:view");
        menus.add(dashboard);

        // 系统管理
        IamMenuDTO system = createMenu(100L, 0L, "系统管理", "directory", "/system",
                null, "el-icon-setting", 100, null);

        List<IamMenuDTO> systemChildren = new ArrayList<>();
        systemChildren.add(createMenu(101L, 100L, "用户管理", "menu", "/system/user",
                "setting/user/index", "el-icon-user", 1, "system:user:view"));
        systemChildren.add(createMenu(102L, 100L, "角色管理", "menu", "/system/role",
                "setting/role/index", "el-icon-user-solid", 2, "system:role:view"));
        systemChildren.add(createMenu(103L, 100L, "菜单管理", "menu", "/system/menu",
                "setting/menu/index", "el-icon-menu", 3, "system:menu:view"));
        systemChildren.add(createMenu(104L, 100L, "部门管理", "menu", "/system/dept",
                "setting/dept/index", "el-icon-s-cooperation", 4, "system:dept:view"));

        system.setChildren(systemChildren);
        menus.add(system);

        return buildMenuTree(menus, 0L);
    }

    /**
     * 创建菜单对象
     */
    private IamMenuDTO createMenu(Long id, Long parentId, String menuName, String menuType,
                                  String path, String component, String icon, Integer sortOrder,
                                  String permission) {
        IamMenuDTO menu = new IamMenuDTO();
        menu.setId(id);
        menu.setParentId(parentId);
        menu.setMenuName(menuName);
        menu.setMenuType(menuType);
        menu.setPath(path);
        menu.setComponent(component);
        menu.setIcon(icon);
        menu.setSortOrder(sortOrder);
        menu.setVisible(1);
        menu.setStatus(1);
        menu.setPermission(permission);

        // 设置前端兼容字段
        menu.setName(generateRouteName(path, id));

        IamMenuDTO.MetaInfo meta = new IamMenuDTO.MetaInfo();
        meta.setTitle(menuName);
        meta.setIcon(icon);
        meta.setHidden(false);
        meta.setType(menuType);
        if (permission != null) {
            meta.setAuth(Collections.singletonList(permission));
        }
        menu.setMeta(meta);

        return menu;
    }

    /**
     * 生成路由名称
     */
    private String generateRouteName(String path, Long id) {
        if (path == null || path.isEmpty()) {
            return "menu_" + id;
        }
        // 将路径转换为驼峰命名
        String name = path.replaceAll("^/|/$", "").replaceAll("/", "_");
        return name.isEmpty() ? "menu_" + id : name;
    }

    @Override
    public List<IamMenuDTO> listMenus(String menuType, Integer status) {
        // TODO: 从数据库查询菜单列表
        return getMyMenus();
    }

    @Override
    public List<IamMenuDTO> getMenuTree() {
        return getMyMenus();
    }

    @Override
    public IamMenuDTO getMenuById(Long id) {
        // TODO: 从数据库查询菜单详情
        IamMenuDTO menu = new IamMenuDTO();
        menu.setId(id);
        menu.setMenuName("菜单" + id);
        return menu;
    }

    /**
     * 构建菜单树
     */
    private List<IamMenuDTO> buildMenuTree(List<IamMenuDTO> menus, Long parentId) {
        List<IamMenuDTO> tree = new ArrayList<>();
        for (IamMenuDTO menu : menus) {
            if (parentId.equals(menu.getParentId())) {
                tree.add(menu);
                // 递归查找子菜单
                List<IamMenuDTO> children = buildMenuTree(menus, menu.getId());
                if (!children.isEmpty()) {
                    menu.setChildren(children);
                }
            }
        }
        return tree;
    }
}
