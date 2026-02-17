package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkDatabaseResource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbFrameworkDatabaseResourceMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkDatabaseResourceService;
import org.springframework.stereotype.Service;

/**
 * 数据库资源 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbFrameworkDatabaseResourceServiceImpl extends ServiceImpl<TbFrameworkDatabaseResourceMapper, TbFrameworkDatabaseResource>  implements TbFrameworkDatabaseResourceService{

}
