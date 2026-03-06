package com.ssitao.code.modular.iam.menu.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.menu.dal.dataobject.IamMenuDO;
import com.ssitao.code.modular.iam.menu.dal.mapper.IamMenuMapper;
import com.ssitao.code.modular.iam.menu.domain.model.IamMenu;
import com.ssitao.code.modular.iam.menu.domain.repository.IamMenuRepository;
import com.ssitao.code.modular.iam.menu.infrastructure.converter.IamMenuConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM菜单仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamMenuRepositoryImpl implements IamMenuRepository {

    @Resource
    private IamMenuMapper menuMapper;

    @Resource
    private IamMenuConverter menuConverter;

    @Override
    public List<IamMenu> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("is_deleted", 0)
                .orderBy("menu_sort", true);

        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }

        List<IamMenuDO> menuDOList = menuMapper.selectListByQuery(query);
        return menuConverter.toDomainList(menuDOList);
    }

    @Override
    public Optional<IamMenu> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("menu_id", id)
                .eq("is_deleted", 0);

        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }

        IamMenuDO menuDO = menuMapper.selectOneByQuery(query);
        if (menuDO == null) {
            return Optional.empty();
        }
        IamMenu menu = menuConverter.toDomain(menuDO);
        return Optional.of(menu);
    }

    @Override
    public String save(IamMenu menu) {
        IamMenuDO menuDO = menuConverter.toDO(menu);
        menuDO.setCreateTime(LocalDateTime.now());
        menuDO.setModifyTime(LocalDateTime.now());
        menuDO.setIsDeleted(0);
        menuMapper.insert(menuDO);
        return menuDO.getMenuId();
    }

    @Override
    public void update(IamMenu menu) {
        IamMenuDO menuDO = menuConverter.toDO(menu);
        menuDO.setModifyTime(LocalDateTime.now());
        menuMapper.update(menuDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("menu_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }
        menuMapper.deleteByQuery(query);
    }

    @Override
    public List<IamMenu> findByMenuType(String menuType, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("menu_type", menuType)
                .eq("is_deleted", 0)
                .orderBy("menu_sort", true);

        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }

        List<IamMenuDO> menuDOList = menuMapper.selectListByQuery(query);
        return menuConverter.toDomainList(menuDOList);
    }

    @Override
    public List<IamMenu> findByStatus(Integer status, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("menu_status", status)
                .eq("is_deleted", 0)
                .orderBy("menu_sort", true);

        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }

        List<IamMenuDO> menuDOList = menuMapper.selectListByQuery(query);
        return menuConverter.toDomainList(menuDOList);
    }

    @Override
    public List<IamMenu> findByParentId(String parentId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("menu_parent_id", parentId)
                .eq("is_deleted", 0)
                .orderBy("menu_sort", true);

        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("tenant_id", tenantId);
        }

        List<IamMenuDO> menuDOList = menuMapper.selectListByQuery(query);
        return menuConverter.toDomainList(menuDOList);
    }

}
