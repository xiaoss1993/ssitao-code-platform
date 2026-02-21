package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkServer;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbFrameworkServerMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkServerService;
import org.springframework.stereotype.Service;

/**
 * 服务器维护 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbFrameworkServerServiceImpl extends ServiceImpl<TbFrameworkServerMapper, TbFrameworkServer>  implements TbFrameworkServerService{

}
