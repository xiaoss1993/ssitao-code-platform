package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysMenuAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysMenuRepository;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysMenuMapper;
import com.ssitao.code.common.core.domain.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 菜单仓储实现
 */
@Repository
public class SysMenuRepositoryImpl extends AbstractAggregateRepository<SysMenuAggregate, Long> implements SysMenuRepository {

    @Autowired
    private SysMenuMapper menuMapper;

    public SysMenuRepositoryImpl() {
        super(SysMenuAggregate.class);
    }

    @Override
    protected Collection<SysMenuAggregate> doSave(Collection<SysMenuAggregate> aggregates) {
        List<SysMenuAggregate> saved = new ArrayList<>();
        for (SysMenuAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                menuMapper.insertMenu(toEntity(aggregate));
            } else {
                menuMapper.updateMenu(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysMenuAggregate> aggregates) {
        for (SysMenuAggregate aggregate : aggregates) {
            menuMapper.deleteMenuById(aggregate.getMenuId());
        }
    }

    @Override
    protected SysMenuAggregate doFindOne(Long id) {
        SysMenu menu = menuMapper.selectMenuById(id);
        if (menu == null) {
            return null;
        }
        return toAggregate(menu);
    }

    @Override
    protected boolean doExists(Long id) {
        return menuMapper.selectMenuById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysMenu> menus = menuMapper.selectMenuAll();
        List<Long> ids = new ArrayList<>();
        if (menus != null) {
            for (SysMenu menu : menus) {
                ids.add(menu.getMenuId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysMenuAggregate> doFindAll(Collection<Long> ids) {
        List<SysMenuAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysMenuAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return menuMapper.selectMenuAll().size();
    }

    @Override
    public List<SysMenuAggregate> findByParentId(Long parentId) {
        List<SysMenu> menus = menuMapper.selectMenuList(new SysMenu());
        List<SysMenuAggregate> aggregates = new ArrayList<>();
        if (menus != null) {
            for (SysMenu menu : menus) {
                if (parentId != null && parentId.equals(menu.getParentId())) {
                    aggregates.add(toAggregate(menu));
                }
            }
        }
        return aggregates;
    }

    @Override
    public boolean existsByMenuNameAndParentId(String menuName, Long parentId) {
        return menuMapper.checkMenuNameUnique(menuName, parentId) != null;
    }

    private SysMenu toEntity(SysMenuAggregate aggregate) {
        SysMenu menu = new SysMenu();
        menu.setMenuId(aggregate.getMenuId());
        menu.setMenuName(aggregate.getMenuName());
        menu.setParentId(aggregate.getParentId());
        menu.setOrderNum(aggregate.getOrderNum());
        menu.setUrl(aggregate.getUrl());
        menu.setTarget(aggregate.getTarget());
        menu.setMenuType(aggregate.getMenuType());
        menu.setVisible(aggregate.getVisible());
        menu.setIsRefresh(aggregate.getIsRefresh());
        menu.setPerms(aggregate.getPerms());
        menu.setIcon(aggregate.getIcon());
        menu.setCreateBy(aggregate.getCreateBy());
        menu.setCreateTime(aggregate.getCreateTime());
        menu.setUpdateBy(aggregate.getUpdateBy());
        menu.setUpdateTime(aggregate.getUpdateTime());
        menu.setRemark(aggregate.getRemark());
        return menu;
    }

    private SysMenuAggregate toAggregate(SysMenu menu) {
        SysMenuAggregate aggregate = new SysMenuAggregate();
        aggregate.setMenuId(menu.getMenuId());
        aggregate.setMenuName(menu.getMenuName());
        aggregate.setParentId(menu.getParentId());
        aggregate.setOrderNum(menu.getOrderNum());
        aggregate.setUrl(menu.getUrl());
        aggregate.setTarget(menu.getTarget());
        aggregate.setMenuType(menu.getMenuType());
        aggregate.setVisible(menu.getVisible());
        aggregate.setIsRefresh(menu.getIsRefresh());
        aggregate.setPerms(menu.getPerms());
        aggregate.setIcon(menu.getIcon());
        aggregate.setCreateBy(menu.getCreateBy());
        aggregate.setCreateTime(menu.getCreateTime());
        aggregate.setUpdateBy(menu.getUpdateBy());
        aggregate.setUpdateTime(menu.getUpdateTime());
        aggregate.setRemark(menu.getRemark());
        return aggregate;
    }
}
