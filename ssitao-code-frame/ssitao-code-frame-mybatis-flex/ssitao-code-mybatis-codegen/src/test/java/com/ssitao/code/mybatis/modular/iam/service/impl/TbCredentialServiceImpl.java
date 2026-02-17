package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCredential;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCredentialMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCredentialService;
import org.springframework.stereotype.Service;

/**
 * 凭据 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCredentialServiceImpl extends ServiceImpl<TbCredentialMapper, TbCredential>  implements TbCredentialService{

}
