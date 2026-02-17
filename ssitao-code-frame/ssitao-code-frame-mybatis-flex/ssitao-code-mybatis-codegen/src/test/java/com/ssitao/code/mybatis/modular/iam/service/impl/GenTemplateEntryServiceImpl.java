package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateEntry;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.GenTemplateEntryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplateEntryService;
import org.springframework.stereotype.Service;

/**
 * 模板文件目录项 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class GenTemplateEntryServiceImpl extends ServiceImpl<GenTemplateEntryMapper, GenTemplateEntry>  implements GenTemplateEntryService{

}
