package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDictionary;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SDictionaryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDictionaryService;
import org.springframework.stereotype.Service;

/**
 * 数据字典 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SDictionaryServiceImpl extends ServiceImpl<SDictionaryMapper, SDictionary>  implements SDictionaryService{

}
