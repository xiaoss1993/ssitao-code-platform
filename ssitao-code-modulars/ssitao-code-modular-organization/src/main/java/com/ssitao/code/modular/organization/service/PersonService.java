
package com.ssitao.code.modular.organization.service;

import com.ssitao.code.modular.organization.entity.PersonAuthBindEntity;
import com.ssitao.code.modular.organization.entity.PersonEntity;
import com.ssitao.code.modular.common.service.CrudService;

import java.util.List;

/**
 * 人员 服务类
 *
 *
 */
public interface PersonService extends CrudService<PersonEntity, String> {

    String insert(PersonAuthBindEntity authBindEntity);

    int updateByPk(PersonAuthBindEntity authBindEntity);

    List<PersonEntity> selectByName(String name);

    PersonAuthBindEntity selectAuthBindByPk(String id);

    List<PersonEntity> selectByPositionId(String positionId);

    List<PersonEntity> selectByPositionIds(List<String> positionId);

    List<PersonEntity> selectByDepartmentId(List<String> departmentId);

    List<PersonEntity> selectByOrgId(List<String> departmentId);

    PersonEntity selectByUserId(String userId);

    List<String> selectAllDepartmentId(List<String> personId);

    List<String> selectAllOrgId(List<String> personId);

    List<PersonEntity> selectByRoleId(String roleId);
}
