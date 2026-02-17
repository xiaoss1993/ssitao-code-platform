package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRelationDef;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SRelationDefMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRelationDefService;
import org.springframework.stereotype.Service;

/**
 * 关系定义 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SRelationDefServiceImpl extends ServiceImpl<SRelationDefMapper, SRelationDef>  implements SRelationDefService{

}
