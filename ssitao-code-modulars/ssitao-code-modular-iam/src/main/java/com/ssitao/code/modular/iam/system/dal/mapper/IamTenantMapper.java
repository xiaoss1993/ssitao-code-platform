package com.ssitao.code.modular.iam.system.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.system.dal.dataobject.IamTenantDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 租户Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamTenantMapper extends BaseMapper<IamTenantDO> {

}
