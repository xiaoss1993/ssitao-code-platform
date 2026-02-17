package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreException;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreExceptionMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExceptionService;
import org.springframework.stereotype.Service;

/**
 * 执行异常 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbCoreExceptionServiceImpl extends ServiceImpl<TbCoreExceptionMapper, TbCoreException>  implements TbCoreExceptionService{

}
