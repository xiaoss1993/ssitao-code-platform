package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDictItem;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SDictItemMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDictItemService;
import org.springframework.stereotype.Service;

/**
 * 数据字典选项配置 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SDictItemServiceImpl extends ServiceImpl<SDictItemMapper, SDictItem>  implements SDictItemService{

}
