package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenuGroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SMenuGroupMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuGroupService;
import org.springframework.stereotype.Service;

/**
 * 菜单分组 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SMenuGroupServiceImpl extends ServiceImpl<SMenuGroupMapper, SMenuGroup>  implements SMenuGroupService{

}
