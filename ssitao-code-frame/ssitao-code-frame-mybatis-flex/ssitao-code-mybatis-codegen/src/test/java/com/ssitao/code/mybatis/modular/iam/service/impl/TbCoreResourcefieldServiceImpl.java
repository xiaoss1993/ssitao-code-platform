package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcefield;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreResourcefieldMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcefieldService;
import org.springframework.stereotype.Service;

/**
 * 单字段 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreResourcefieldServiceImpl extends ServiceImpl<TbCoreResourcefieldMapper, TbCoreResourcefield>  implements TbCoreResourcefieldService{

}
