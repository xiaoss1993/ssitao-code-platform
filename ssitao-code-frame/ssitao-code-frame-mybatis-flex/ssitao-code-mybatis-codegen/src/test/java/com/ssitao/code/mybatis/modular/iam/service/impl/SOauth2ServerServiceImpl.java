package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2Server;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SOauth2ServerMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2ServerService;
import org.springframework.stereotype.Service;

/**
 * OAuth2 服务配置 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SOauth2ServerServiceImpl extends ServiceImpl<SOauth2ServerMapper, SOauth2Server>  implements SOauth2ServerService{

}
