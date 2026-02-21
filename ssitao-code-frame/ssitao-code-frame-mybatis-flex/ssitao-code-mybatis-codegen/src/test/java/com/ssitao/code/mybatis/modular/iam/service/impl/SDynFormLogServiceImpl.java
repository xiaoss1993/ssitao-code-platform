package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDynFormLog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SDynFormLogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDynFormLogService;
import org.springframework.stereotype.Service;

/**
 * 单发布日志 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SDynFormLogServiceImpl extends ServiceImpl<SDynFormLogMapper, SDynFormLog>  implements SDynFormLogService{

}
