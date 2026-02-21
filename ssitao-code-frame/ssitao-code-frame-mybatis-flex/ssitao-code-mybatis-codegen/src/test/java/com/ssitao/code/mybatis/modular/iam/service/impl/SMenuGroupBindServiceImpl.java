package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenuGroupBind;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SMenuGroupBindMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuGroupBindService;
import org.springframework.stereotype.Service;

/**
 * 菜单分组关联 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SMenuGroupBindServiceImpl extends ServiceImpl<SMenuGroupBindMapper, SMenuGroupBind>  implements SMenuGroupBindService{

}
