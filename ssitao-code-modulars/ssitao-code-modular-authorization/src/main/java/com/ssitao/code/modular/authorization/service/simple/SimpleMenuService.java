

package com.ssitao.code.modular.authorization.service.simple;

import com.ssitao.code.modular.authorization.dao.MenuDao;
import com.ssitao.code.modular.authorization.entity.MenuEntity;
import com.ssitao.code.modular.authorization.service.MenuService;
import com.ssitao.code.commons.utils.id.IDGenerator;
import com.ssitao.code.commons.service.AbstractTreeSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 *
 */
@Service("menuService")
public class SimpleMenuService
        extends AbstractTreeSortService<MenuEntity, String>
        implements MenuService {

    private MenuDao menuDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Autowired
    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public MenuDao getDao() {
        return menuDao;
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public int updateByPk(MenuEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public String saveOrUpdate(MenuEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public int updateBatch(Collection<MenuEntity> data) {
        return super.updateBatch(data);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public int updateByPk(String id, MenuEntity entity) {
        return super.updateByPk(id, entity);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public int updateByPk(List<MenuEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public String insert(MenuEntity entity) {
        if (entity.getStatus() == null) {
            entity.setStatus((byte) 1);
        }
        return super.insert(entity);
    }

    @Override
    @Cacheable(cacheNames = CacheConstants.MENU_CACHE_NAME, key = "'ids:'+(#id==null?'0':#id.hashCode())")
    public List<MenuEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @CacheEvict(cacheNames = {CacheConstants.MENU_CACHE_NAME, CacheConstants.USER_MENU_CACHE_NAME}, allEntries = true)
    public MenuEntity deleteByPk(String id) {
        return super.deleteByPk(id);
    }
}
