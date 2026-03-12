package com.ssitao.code.modular.iam.menu.application.service.impl;

import com.ssitao.code.common.pojo.PageResult;
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
import java.util.List;
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
        // 如果租户功能未启用，使用默认租户ID "1"
        String tenantId = TenantUtils.getTenantId();
        if (tenantId == null || tenantId.isEmpty()) {
            tenantId = "1";
        }
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
    public List<IamMenuDTO> listMenus(String parentId, String menuType, Integer status) {
        String tenantId = TenantUtils.getTenantId();
        List<IamMenu> menus;

        // 如果指定了parentId，查询该父节点下的子菜单（平面列表）
        if (parentId != null && !parentId.isEmpty()) {
            menus = menuRepository.findByParentId(parentId, tenantId);
        } else if (menuType != null && !menuType.isEmpty()) {
            menus = menuRepository.findByMenuType(menuType, tenantId);
        } else if (status != null) {
            menus = menuRepository.findByStatus(status, tenantId);
        } else {
            // 默认返回树形结构
            menus = menuRepository.findAll(tenantId);
            if (menus == null || menus.isEmpty()) {
                return new ArrayList<>();
            }
            List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
            dtoList.forEach(this::populateFrontendFields);
            return buildMenuTree(dtoList, "0");
        }

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 转换为DTO并设置前端兼容字段
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(menus);
        dtoList.forEach(this::populateFrontendFields);

        // 如果有parentId，返回平面列表；否则返回树形结构
        if (parentId != null && !parentId.isEmpty()) {
            return dtoList;
        }
        return buildMenuTree(dtoList, "0");
    }

    @Override
    public List<IamMenuDTO> pageMenus(int page, int size, String parentId, String menuType, Integer status) {
        String tenantId = TenantUtils.getTenantId();

        // 使用分页查询
        List<IamMenu> menus = menuRepository.findAll(tenantId);

        if (menus == null || menus.isEmpty()) {
            return new ArrayList<>();
        }

        // 过滤数据
        List<IamMenu> filteredMenus = menus;
        if (parentId != null && !parentId.isEmpty()) {
            filteredMenus = menus.stream()
                    .filter(m -> parentId.equals(m.getParentId()))
                    .collect(java.util.stream.Collectors.toList());
        } else if (menuType != null && !menuType.isEmpty()) {
            filteredMenus = menus.stream()
                    .filter(m -> menuType.equals(m.getMenuType().toString()))
                    .collect(java.util.stream.Collectors.toList());
        } else if (status != null) {
            filteredMenus = menus.stream()
                    .filter(m -> status.equals(m.getStatus()))
                    .collect(java.util.stream.Collectors.toList());
        }

        // 分页
        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredMenus.size());
        if (start >= filteredMenus.size()) {
            return new ArrayList<>();
        }
        List<IamMenu> pagedMenus = filteredMenus.subList(start, end);

        // 转换为DTO
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(pagedMenus);
        dtoList.forEach(this::populateFrontendFields);

        return dtoList;
    }

    @Override
    public PageResult<IamMenuDTO> listMenusPage(Integer page, Integer size, String parentId, String menuType, Integer status, String sort, String order) {
        String tenantId = TenantUtils.getTenantId();

        // 获取所有菜单
        List<IamMenu> menus = menuRepository.findAll(tenantId);

        if (menus == null || menus.isEmpty()) {
            return PageResult.of(new ArrayList<>(), 0);
        }

        // 过滤数据
        List<IamMenu> filteredMenus = menus;
        if (parentId != null && !parentId.isEmpty()) {
            filteredMenus = menus.stream()
                    .filter(m -> parentId.equals(m.getParentId()))
                    .collect(java.util.stream.Collectors.toList());
        } else if (menuType != null && !menuType.isEmpty()) {
            filteredMenus = menus.stream()
                    .filter(m -> menuType.equals(m.getMenuType().toString()))
                    .collect(java.util.stream.Collectors.toList());
        } else if (status != null) {
            filteredMenus = menus.stream()
                    .filter(m -> status.equals(m.getStatus()))
                    .collect(java.util.stream.Collectors.toList());
        }

        // 记录总数
        int total = filteredMenus.size();

        // 排序
        if (sort != null && !sort.isEmpty()) {
            String finalSort = convertSortField(sort);
            String finalOrder = order != null ? order.toLowerCase() : "asc";
            filteredMenus.sort((a, b) -> {
                int cmp = 0;
                switch (finalSort) {
                    case "sort_order":
                        cmp = Integer.compare(a.getMenuSort() != null ? a.getMenuSort() : 0,
                                             b.getMenuSort() != null ? b.getMenuSort() : 0);
                        break;
                    case "menu_name":
                        cmp = (a.getMenuName() != null ? a.getMenuName() : "").compareTo(
                              b.getMenuName() != null ? b.getMenuName() : "");
                        break;
                    case "create_time":
                        cmp = (a.getCreateTime() != null ? a.getCreateTime().toString() : "").compareTo(
                              b.getCreateTime() != null ? b.getCreateTime().toString() : "");
                        break;
                    default:
                        cmp = Integer.compare(a.getMenuSort() != null ? a.getMenuSort() : 0,
                                             b.getMenuSort() != null ? b.getMenuSort() : 0);
                }
                return "desc".equals(finalOrder) ? -cmp : cmp;
            });
        }

        // 分页
        int pageNum = page != null && page > 0 ? page : 1;
        int pageSize = size != null && size > 0 ? size : 10;
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, filteredMenus.size());
        if (start >= filteredMenus.size()) {
            return PageResult.of(new ArrayList<>(), total);
        }
        List<IamMenu> pagedMenus = filteredMenus.subList(start, end);

        // 转换为DTO
        List<IamMenuDTO> dtoList = menuConverter.toDTOList(pagedMenus);
        dtoList.forEach(this::populateFrontendFields);

        return PageResult.of(dtoList, total);
    }

    /**
     * 转换排序字段名
     */
    private String convertSortField(String sort) {
        if (sort == null) return "sort_order";
        switch (sort) {
            case "sortOrder":
            case "sort_order":
                return "sort_order";
            case "menuName":
            case "menu_name":
                return "menu_name";
            case "createTime":
            case "create_time":
                return "create_time";
            default:
                return sort;
        }
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

        // 如果没有ID，生成一个新的UUID
        if (dto.getId() == null || dto.getId().isEmpty()) {
            menu.setId(java.util.UUID.randomUUID().toString().replace("-", ""));
        } else {
            menu.setId(dto.getId());
        }
        // 处理 parentId：空字符串转为 null
        String parentId = dto.getParentId();
        menu.setParentId(parentId != null && !parentId.isEmpty() ? parentId : null);
        menu.setMenuCode(dto.getMenuCode());
        menu.setMenuName(dto.getMenuName());
        // 转换菜单类型：大写 DIRECTORY/MENU/BUTTON
        menu.setMenuType(convertMenuType(dto.getMenuType()));
        menu.setPath(dto.getPath());
        menu.setComponent(dto.getComponent());
        menu.setPermission(dto.getPermission());
        menu.setIcon(dto.getIcon());
        menu.setSortOrder(dto.getSortOrder());
        
        // 处理可见性：优先使用 meta.hidden，其次使用 visible
        if (dto.getMeta() != null && dto.getMeta().getHidden() != null) {
            menu.setVisible(dto.getMeta().getHidden() ? 0 : 1);
        } else if (dto.getVisible() != null) {
            menu.setVisible(dto.getVisible());
        } else {
            menu.setVisible(1);
        }
        
        menu.setStatus(dto.getStatus() != null ? dto.getStatus() : 1);

        // 前端扩展字段
        menu.setRedirect(dto.getRedirect());
        menu.setActive(dto.getActive());
        menu.setColor(dto.getColor());
        menu.setFullpage(dto.getFullpage());
        menu.setTag(dto.getTag());
        menu.setHiddenBreadcrumb(dto.getHiddenBreadcrumb());

        // 从meta中提取字段（优先级高于直接字段）
        if (dto.getMeta() != null) {
            if (dto.getMeta().getTitle() != null && !dto.getMeta().getTitle().isEmpty()) {
                menu.setMenuName(dto.getMeta().getTitle());
            }
            if (dto.getMeta().getType() != null && !dto.getMeta().getType().isEmpty()) {
                menu.setMenuType(convertMenuType(dto.getMeta().getType()));
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
     * 转换菜单类型：前端小写 -> 数据库大写
     */
    private String convertMenuType(String menuType) {
        if (menuType == null || menuType.isEmpty()) {
            return "MENU";
        }
        switch (menuType.toLowerCase()) {
            case "directory":
                return "DIRECTORY";
            case "menu":
                return "MENU";
            case "button":
                return "BUTTON";
            default:
                return menuType.toUpperCase();
        }
    }

    /**
     * 转换菜单类型为小写（数据库大写 -> 前端小写）
     */
    private String convertMenuTypeToLower(String menuType) {
        if (menuType == null || menuType.isEmpty()) {
            return "menu";
        }
        return menuType.toLowerCase();
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
        // 转换 menuType 为小写格式返回给前端
        String menuTypeLower = convertMenuTypeToLower(dto.getMenuType());
        dto.setMenuType(menuTypeLower);
        meta.setType(menuTypeLower);
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
