package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbProductManage;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbProductManageMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbProductManageService;
import org.springframework.stereotype.Service;

/**
 * 产品管理 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbProductManageServiceImpl extends ServiceImpl<TbProductManageMapper, TbProductManage>  implements TbProductManageService{

}
