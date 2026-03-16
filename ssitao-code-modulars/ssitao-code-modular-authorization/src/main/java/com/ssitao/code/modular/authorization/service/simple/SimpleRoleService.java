

package com.ssitao.code.modular.authorization.service.simple;

import com.ssitao.code.modular.authorization.service.RoleService;
import com.ssitao.code.commons.entity.DataStatus;
import com.ssitao.code.modular.authorization.dao.RoleDao;
import com.ssitao.code.modular.authorization.entity.RoleEntity;
import com.ssitao.code.commons.utils.id.IDGenerator;
import com.ssitao.code.commons.service.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * TODO 完成注释
 *
 *
 */
@Service("roleService")
public class SimpleRoleService extends GenericEntityService<RoleEntity, String> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    protected IDGenerator<String> getIDGenerator() {
        return IDGenerator.MD5;
    }

    @Override
    public RoleDao getDao() {
        return roleDao;
    }

    @Override
    public String insert(RoleEntity entity) {
        entity.setStatus(DataStatus.STATUS_ENABLED);
        return super.insert(entity);
    }

    @Override
    public int updateByPk(String s, RoleEntity entity) {
        entity.setStatus(null);
        return super.updateByPk(s, entity);
    }

    @Override
    public void enable(String roleId) {
        tryValidateProperty(StringUtils.hasLength(roleId), RoleEntity.id, "{id_is_null}");
        createUpdate()
                .set(RoleEntity.status, DataStatus.STATUS_ENABLED)
                .where(RoleEntity.id, roleId)
                .exec();
    }

    @Override
    public void disable(String roleId) {
        tryValidateProperty(StringUtils.hasLength(roleId), RoleEntity.id, "{id_is_null}");
       createUpdate()
                .set(RoleEntity.status, DataStatus.STATUS_DISABLED)
                .where(RoleEntity.id, roleId)
                .exec();
    }
}
