package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.STemplate;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.STemplateMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.STemplateService;
import org.springframework.stereotype.Service;

/**
 * 模板 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class STemplateServiceImpl extends ServiceImpl<STemplateMapper, STemplate>  implements STemplateService{

}
