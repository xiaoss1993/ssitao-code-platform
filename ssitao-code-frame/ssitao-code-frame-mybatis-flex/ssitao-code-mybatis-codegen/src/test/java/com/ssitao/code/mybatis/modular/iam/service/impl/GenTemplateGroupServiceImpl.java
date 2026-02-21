package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateGroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.GenTemplateGroupMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplateGroupService;
import org.springframework.stereotype.Service;

/**
 * 模板组 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class GenTemplateGroupServiceImpl extends ServiceImpl<GenTemplateGroupMapper, GenTemplateGroup>  implements GenTemplateGroupService{

}
