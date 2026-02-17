package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateProperty;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.GenTemplatePropertyMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplatePropertyService;
import org.springframework.stereotype.Service;

/**
 * 模板属性配置 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class GenTemplatePropertyServiceImpl extends ServiceImpl<GenTemplatePropertyMapper, GenTemplateProperty>  implements GenTemplatePropertyService{

}
