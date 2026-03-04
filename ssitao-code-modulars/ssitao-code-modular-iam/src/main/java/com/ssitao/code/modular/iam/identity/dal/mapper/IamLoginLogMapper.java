package com.ssitao.code.modular.iam.identity.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamLoginLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录日志Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamLoginLogMapper extends BaseMapper<IamLoginLogDO> {

}
