package com.ssitao.code.modular.iam.authorization.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermGroupPermDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限组权限关联Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamPermGroupPermMapper extends BaseMapper<IamPermGroupPermDO> {

}
