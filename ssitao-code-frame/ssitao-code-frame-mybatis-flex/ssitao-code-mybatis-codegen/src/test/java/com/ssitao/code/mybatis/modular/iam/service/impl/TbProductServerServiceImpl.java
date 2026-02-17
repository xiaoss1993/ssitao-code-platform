package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbProductServer;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbProductServerMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbProductServerService;
import org.springframework.stereotype.Service;

/**
 * 产品应用服务器管理 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbProductServerServiceImpl extends ServiceImpl<TbProductServerMapper, TbProductServer>  implements TbProductServerService{

}
