package com.ssitao.code.modular.iam.menu.application.service.impl;

import com.ssitao.code.frame.security.tenant.utils.TenantUtils;
import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
import com.ssitao.code.modular.iam.menu.domain.model.IamMenu;
import com.ssitao.code.modular.iam.menu.domain.repository.IamMenuRepository;
import com.ssitao.code.modular.iam.menu.infrastructure.converter.IamMenuConverter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * IAM菜单应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamMenuAppServiceImpl implements IamMenuAppService {

    @Resource
    private IamMenuRepository menuRepository;

    @Resource
    private IamMenuConverter menuConverter;

    @Override
    public List<IamMenuDTO> getMyMenus() {
        // TODO: 从当前用户获取权限后过滤菜单
        // 暂时返回所有菜单
        String tenantId = TenantUtils.getTenantId();
        List<IamMenu> menus = menuRepository.findAll(tenantId);

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为DTO并设置前端兼容字段
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
        dtoList.forEach(this::populateFrontendFields);

        // 构建菜单树
        return buildMenuTree(dtoList, "0");
    }

    @Override
    public List<IamMenuDTO> listMenus(String menuType, Integer status) {
        String tenantId = TenantUtils.getTenantId();
        List<IamMenu> menus;

        if (menuType != null && !menuType.isEmpty()) {
            menus = menuRepository.findByMenuType(menuType, tenantId);
        } else if (status != null) {
            menus = menuRepository.findByStatus(status, tenantId);
        } else {
            menus = menuRepository.findAll(tenantId);
        }

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为DTO并设置前端兼容字段（扁平结构，用于表格展示）
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
        dtoList.forEach(this::populateFrontendFields);

        // 规范化 parent_id：根节点设置为 null（tree-column 扩展需要）
        normalizeParentIds(dtoList);

        // 计算层级用于前端显示缩进
        calculateLevels(dtoList);

        return dtoList;
    }

    /**
     * 规范化 parent_id：根节点的 parent_id 设置为 null
     * tree-column 扩展需要 parent_id 为 null/undefined/空字符串才能识别根节点
     */
    private void normalizeParentIds(List<IamMenuDTO> menus) {
        if (menus == null || menus.isEmpty()) {
            return;
        }
        for (IamMenuDTO menu : menus) {
            String parentId = menu.getParentId();
            if (parentId == null || "0".equals(parentId) || "".equals(parentId)) {
                menu.setParentId(null);
            }
        }
    }

    /**
     * 计算菜单层级
     */
    private void calculateLevels(List<IamMenuDTO> menus) {
        if (menus == null || menus.isEmpty()) {
            return;
        }

        // 构建ID到菜单的映射
        Map<String, IamMenuDTO> menuMap = new HashMap<>();
        for (IamMenuDTO menu : menus) {
            if (menu.getId() != null) {
                menuMap.put(menu.getId(), menu);
            }
        }

        // 计算每个菜单的层级
        for (IamMenuDTO menu : menus) {
            int level = calculateMenuLevel(menu, menuMap, 0);
            menu.setLevel(level);
        }
    }

    /**
     * 递归计算菜单层级
     */
    private int calculateMenuLevel(IamMenuDTO menu, Map<String, IamMenuDTO> menuMap, int currentLevel) {
        String parentId = menu.getParentId();

        // 根节点
        if (parentId == null || "0".equals(parentId) || "".equals(parentId)) {
            return currentLevel;
        }

        // 查找父节点
        IamMenuDTO parent = menuMap.get(parentId);
        if (parent == null) {
            return currentLevel;
        }

        // 如果父节点已经有层级，直接使用
        if (parent.getLevel() != null) {
            return parent.getLevel() + 1;
        }

        // 递归计算父节点层级
        return calculateMenuLevel(parent, menuMap, currentLevel + 1);
    }

    @Override
    public List<IamMenuDTO> listMenusTree(String menuType, Integer status) {
        String tenantId = TenantUtils.getTenantId();
        List<IamMenu> menus;

        if (menuType != null && !menuType.isEmpty()) {
            menus = menuRepository.findByMenuType(menuType, tenantId);
        } else if (status != null) {
            menus = menuRepository.findByStatus(status, tenantId);
        } else {
            menus = menuRepository.findAll(tenantId);
        }

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为DTO并设置前端兼容字段
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
        dtoList.forEach(this::populateFrontendFields);

        // 构建菜单树
        return buildMenuTree(dtoList, "0");
    }

    @Override
    public List<IamMenuDTO> getMenuTree() {
        String tenantId = TenantUtils.getTenantId();
        List<IamMenu> menus = menuRepository.findAll(tenantId);

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为DTO并设置前端兼容字段
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
        dtoList.forEach(this::populateFrontendFields);

        // 构建菜单树
        return buildMenuTree(dtoList, "0");
    }

    @Override
    public IamMenuDTO getMenuById(String id) {
        String tenantId = TenantUtils.getTenantId();
        return menuRepository.findById(id, tenantId)
                .map(menu -> {
                    IamMenuDTO dto = menuConverter.toDTO(menu);
                    populateFrontendFields(dto);
                    return dto;
                })
                .orElse(null);
    }

    @Override
    public String createMenu(IamMenuDTO menuDTO) {
        String tenantId = TenantUtils.getTenantId();

        // 将DTO转换为领域模型
        IamMenu menu = convertDtoToDomain(menuDTO);
        menu.setTenantId(tenantId);

        // 保存菜单
        String menuId = menuRepository.save(menu);

        return menuId;
    }

    @Override
    public void updateMenu(IamMenuDTO menuDTO) {
        String tenantId = TenantUtils.getTenantId();

        // 将DTO转换为领域模型
        IamMenu menu = convertDtoToDomain(menuDTO);
        menu.setId(menuDTO.getId());
        menu.setTenantId(tenantId);

        // 更新菜单
        menuRepository.update(menu);
    }

    @Override
    public void deleteMenu(String id) {
        String tenantId = TenantUtils.getTenantId();
        menuRepository.deleteById(id, tenantId);
    }

    @Override
    public void batchDeleteMenus(List<String> ids) {
        String tenantId = TenantUtils.getTenantId();
        for (String id : ids) {
            menuRepository.deleteById(id, tenantId);
        }
    }

    /**
     * 将DTO转换为领域模型（处理前端兼容字段）
     */
    private IamMenu convertDtoToDomain(IamMenuDTO dto) {
        IamMenu menu = new IamMenu();

        menu.setId(dto.getId());
        menu.setParentId(dto.getParentId());
        menu.setMenuName(dto.getMenuName());
        menu.setMenuType(dto.getMenuType());
        menu.setPath(dto.getPath());
        menu.setComponent(dto.getComponent());
        menu.setPermission(dto.getPermission());
        menu.setIcon(dto.getIcon());
        menu.setSortOrder(dto.getSortOrder());
        menu.setVisible(dto.getVisible());
        menu.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);

        // 前端扩展字段
        menu.setRedirect(dto.getRedirect());
        menu.setActive(dto.getActive());
        menu.setColor(dto.getColor());
        menu.setFullpage(dto.getFullpage());
        menu.setTag(dto.getTag());
        menu.setHiddenBreadcrumb(dto.getHiddenBreadcrumb());

        // 从meta中提取字段
        if (dto.getMeta() != null) {
            if (dto.getMeta().getTitle() != null) {
                menu.setMenuName(dto.getMeta().getTitle());
            }
            if (dto.getMeta().getType() != null) {
                menu.setMenuType(dto.getMeta().getType());
            }
            if (dto.getMeta().getIcon() != null) {
                menu.setIcon(dto.getMeta().getIcon());
            }
            if (dto.getMeta().getHidden() != null) {
                menu.setVisible(dto.getMeta().getHidden() ? 0 : 1);
            }
        }

        return menu;
    }

    /**
     * 填充前端兼容字段
     */
    private void populateFrontendFields(IamMenuDTO dto) {
        // 确保 children 字段不为 null
        if (dto.getChildren() == null) {
            dto.setChildren(new ArrayList<>());
        }

        // 确保 path 和 component 不为 null
        if (dto.getPath() == null) {
            dto.setPath("");
        }
        if (dto.getComponent() == null) {
            dto.setComponent("");
        }

        if (dto.getPath() != null && !dto.getPath().isEmpty()) {
            dto.setName(generateRouteName(dto.getPath(), dto.getId()));
        } else {
            dto.setName("menu_" + dto.getId());
        }

        // 创建Meta信息
        IamMenuDTO.MetaInfo meta = new IamMenuDTO.MetaInfo();
        meta.setTitle(dto.getMenuName() != null ? dto.getMenuName() : "");
        meta.setIcon(dto.getIcon() != null ? dto.getIcon() : "");
        meta.setHidden(dto.getVisible() != null && dto.getVisible() == 0);
        meta.setType(dto.getMenuType() != null ? dto.getMenuType() : "menu");
        meta.setColor(dto.getColor());
        meta.setFullpage(dto.getFullpage() != null ? dto.getFullpage() : false);
        meta.setTag(dto.getTag());
        meta.setHiddenBreadcrumb(dto.getHiddenBreadcrumb() != null ? dto.getHiddenBreadcrumb() : false);
        meta.setActive(dto.getActive());

        if (dto.getPermission() != null && !dto.getPermission().isEmpty()) {
            meta.setAuth(Collections.singletonList(dto.getPermission()));
        }

        dto.setMeta(meta);

        // 确保 apiList 不为 null
        if (dto.getApiList() == null) {
            dto.setApiList(new ArrayList<>());
        }
    }

    /**
     * 构建菜单树
     */
    private List<IamMenuDTO> buildMenuTree(List<IamMenuDTO> menus, String parentId) {
        List<IamMenuDTO> tree = new ArrayList<>();

        if (menus == null || menus.isEmpty()) {
            return tree;
        }

        for (IamMenuDTO menu : menus) {
            // 跳过无效的菜单项
            if (menu == null || menu.getId() == null) {
                continue;
            }

            String menuParentId = menu.getParentId();

            // 判断是否匹配当前节点的子节点
            boolean isMatch = false;

            if ("0".equals(parentId)) {
                // 查找根节点：menuParentId 为 null、"0"、空字符串或 "ROOT" 都视为根节点
                isMatch = (menuParentId == null || "0".equals(menuParentId) ||
                           "".equals(menuParentId) || "ROOT".equalsIgnoreCase(menuParentId));
            } else {
                // 查找子节点：menuParentId 等于 parentId
                isMatch = (parentId != null && parentId.equals(menuParentId));
            }

            if (isMatch) {
                // 递归查找子菜单
                List<IamMenuDTO> children = buildMenuTree(menus, menu.getId());
                menu.setChildren(children != null ? children : new ArrayList<>());

                // 确保 meta 对象存在且有效
                if (menu.getMeta() == null) {
                    menu.setMeta(new IamMenuDTO.MetaInfo());
                }
                if (menu.getMeta().getTitle() == null || menu.getMeta().getTitle().isEmpty()) {
                    menu.getMeta().setTitle(menu.getMenuName() != null ? menu.getMenuName() : "未命名菜单");
                }

                tree.add(menu);
            }
        }

        return tree;
    }

    /**
     * 生成路由名称
     */
    private String generateRouteName(String path, String id) {
        if (path == null || path.isEmpty()) {
            return "menu_" + id;
        }
        // 将路径转换为驼峰命名
        String name = path.replaceAll("^/|/$", "").replaceAll("/", "_");
        return name.isEmpty() ? "menu_" + id : name;
    }

}
