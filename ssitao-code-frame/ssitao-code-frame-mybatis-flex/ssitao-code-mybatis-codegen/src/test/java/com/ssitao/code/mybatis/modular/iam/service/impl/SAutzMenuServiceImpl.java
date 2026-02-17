package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SAutzMenuMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzMenuService;
import org.springframework.stereotype.Service;

/**
 * 权限设置菜单 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SAutzMenuServiceImpl extends ServiceImpl<SAutzMenuMapper, SAutzMenu>  implements SAutzMenuService{

}
