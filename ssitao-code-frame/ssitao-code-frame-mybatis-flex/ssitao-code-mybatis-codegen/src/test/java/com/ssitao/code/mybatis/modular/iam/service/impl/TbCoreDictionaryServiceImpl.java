package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreDictionary;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreDictionaryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreDictionaryService;
import org.springframework.stereotype.Service;

/**
 * 数据字典 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbCoreDictionaryServiceImpl extends ServiceImpl<TbCoreDictionaryMapper, TbCoreDictionary>  implements TbCoreDictionaryService{

}
