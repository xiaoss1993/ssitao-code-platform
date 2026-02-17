package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SScript;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SScriptMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SScriptService;
import org.springframework.stereotype.Service;

/**
 * 动态脚本 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SScriptServiceImpl extends ServiceImpl<SScriptMapper, SScript>  implements SScriptService{

}
