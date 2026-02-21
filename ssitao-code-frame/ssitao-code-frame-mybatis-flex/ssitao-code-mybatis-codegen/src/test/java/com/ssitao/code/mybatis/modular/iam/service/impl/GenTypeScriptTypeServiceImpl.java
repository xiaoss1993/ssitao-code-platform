package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTypeScriptType;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.GenTypeScriptTypeMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTypeScriptTypeService;
import org.springframework.stereotype.Service;

/**
 * 前端和后端数据类型管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class GenTypeScriptTypeServiceImpl extends ServiceImpl<GenTypeScriptTypeMapper, GenTypeScriptType>  implements GenTypeScriptTypeService{

}
