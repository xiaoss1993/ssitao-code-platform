package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreExecutionlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreExecutionlogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExecutionlogService;
import org.springframework.stereotype.Service;

/**
 * 执行日志 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreExecutionlogServiceImpl extends ServiceImpl<TbCoreExecutionlogMapper, TbCoreExecutionlog>  implements TbCoreExecutionlogService{

}
