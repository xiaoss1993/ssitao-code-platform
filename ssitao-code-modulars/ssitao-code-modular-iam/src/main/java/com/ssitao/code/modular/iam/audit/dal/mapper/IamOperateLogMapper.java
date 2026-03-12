package com.ssitao.code.modular.iam.audit.dal.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.audit.dal.dataobject.IamOperateLogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作日志Mapper接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Mapper
public interface IamOperateLogMapper extends BaseMapper<IamOperateLogDO> {

}
