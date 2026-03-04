package com.ssitao.code.modular.iam.authorization.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关联Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamRolePermissionMapper extends BaseMapper<IamRolePermissionDO> {

}
