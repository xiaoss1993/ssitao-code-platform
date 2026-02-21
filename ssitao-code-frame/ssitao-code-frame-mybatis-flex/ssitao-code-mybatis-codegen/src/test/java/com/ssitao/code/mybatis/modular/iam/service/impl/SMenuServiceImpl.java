package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SMenuMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuService;
import org.springframework.stereotype.Service;

/**
 * 系统菜单 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SMenuServiceImpl extends ServiceImpl<SMenuMapper, SMenu>  implements SMenuService{

}
