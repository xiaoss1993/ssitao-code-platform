package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkBaseResource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbFrameworkBaseResourceMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkBaseResourceService;
import org.springframework.stereotype.Service;

/**
 * 基础资源 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbFrameworkBaseResourceServiceImpl extends ServiceImpl<TbFrameworkBaseResourceMapper, TbFrameworkBaseResource>  implements TbFrameworkBaseResourceService{

}
