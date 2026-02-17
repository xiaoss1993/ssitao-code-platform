package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SPosition;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SPositionMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SPositionService;
import org.springframework.stereotype.Service;

/**
 * 职位 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SPositionServiceImpl extends ServiceImpl<SPositionMapper, SPosition>  implements SPositionService{

}
