package com.ssitao.code.modular.iam.identity.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户账户Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamAccountMapper extends BaseMapper<IamAccountDO> {

}
