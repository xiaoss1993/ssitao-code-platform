package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2Client;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SOauth2ClientMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2ClientService;
import org.springframework.stereotype.Service;

/**
 * OAuth2客户端 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SOauth2ClientServiceImpl extends ServiceImpl<SOauth2ClientMapper, SOauth2Client>  implements SOauth2ClientService{

}
