
package com.ssitao.code.modular.organization.service;

import com.ssitao.code.modular.organization.entity.DepartmentEntity;
import com.ssitao.code.modular.common.service.CrudService;
import com.ssitao.code.modular.common.service.TreeService;

import java.util.List;

/**
 * 部门 服务类
 *
 *
 */
public interface DepartmentService extends
        TreeService<DepartmentEntity, String>
        , CrudService<DepartmentEntity, String> {

    List<DepartmentEntity> selectByOrgId(String orgId);

    List<DepartmentEntity> selectByOrgIds(List<String> orgId,boolean children,boolean parent);

    DepartmentEntity selectByCode(String code);

    List<DepartmentEntity> selectByName(String name);

}
