package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2UserToken;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SOauth2UserTokenMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2UserTokenService;
import org.springframework.stereotype.Service;

/**
 * OAuth2用户授权信息 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SOauth2UserTokenServiceImpl extends ServiceImpl<SOauth2UserTokenMapper, SOauth2UserToken>  implements SOauth2UserTokenService{

}
