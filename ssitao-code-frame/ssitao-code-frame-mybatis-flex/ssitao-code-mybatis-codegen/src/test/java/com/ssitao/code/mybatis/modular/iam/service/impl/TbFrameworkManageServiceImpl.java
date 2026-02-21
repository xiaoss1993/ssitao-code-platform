package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkManage;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbFrameworkManageMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkManageService;
import org.springframework.stereotype.Service;

/**
 * 架构管理（暂时没用） 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbFrameworkManageServiceImpl extends ServiceImpl<TbFrameworkManageMapper, TbFrameworkManage>  implements TbFrameworkManageService{

}
