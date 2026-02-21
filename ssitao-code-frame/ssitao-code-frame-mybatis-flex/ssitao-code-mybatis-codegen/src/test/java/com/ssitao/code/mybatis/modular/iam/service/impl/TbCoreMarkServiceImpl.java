package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreMark;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreMarkMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreMarkService;
import org.springframework.stereotype.Service;

/**
 * 功能数据标记 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreMarkServiceImpl extends ServiceImpl<TbCoreMarkMapper, TbCoreMark>  implements TbCoreMarkService{

}
