package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreMenuMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreMenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单信息 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreMenuServiceImpl extends ServiceImpl<TbCoreMenuMapper, TbCoreMenu>  implements TbCoreMenuService{

}
