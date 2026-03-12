package com.ssitao.code.modular.iam.authorization.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamAccountRoleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账户角色关联Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamAccountRoleMapper extends BaseMapper<IamAccountRoleDO> {

}
