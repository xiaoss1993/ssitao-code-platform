package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamSyncLog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamSyncLogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamSyncLogService;
import org.springframework.stereotype.Service;

/**
 * 同步日志 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamSyncLogServiceImpl extends ServiceImpl<TbIamSyncLogMapper, TbIamSyncLog>  implements TbIamSyncLogService{

}
