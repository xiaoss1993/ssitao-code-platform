package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaMicroapp;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbMetaMicroappMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaMicroappService;
import org.springframework.stereotype.Service;

/**
 * 微应用管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbMetaMicroappServiceImpl extends ServiceImpl<TbMetaMicroappMapper, TbMetaMicroapp>  implements TbMetaMicroappService{

}
