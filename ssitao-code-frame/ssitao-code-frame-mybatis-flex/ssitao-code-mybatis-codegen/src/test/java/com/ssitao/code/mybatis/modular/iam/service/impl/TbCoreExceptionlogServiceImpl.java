package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreExceptionlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreExceptionlogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExceptionlogService;
import org.springframework.stereotype.Service;

/**
 * 异常日志 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreExceptionlogServiceImpl extends ServiceImpl<TbCoreExceptionlogMapper, TbCoreExceptionlog>  implements TbCoreExceptionlogService{

}
