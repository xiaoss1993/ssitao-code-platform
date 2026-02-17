package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SPerson;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SPersonMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SPersonService;
import org.springframework.stereotype.Service;

/**
 * 人员 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SPersonServiceImpl extends ServiceImpl<SPersonMapper, SPerson>  implements SPersonService{

}
