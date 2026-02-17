package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamLogcaptcha;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamLogcaptchaMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamLogcaptchaService;
import org.springframework.stereotype.Service;

/**
 * 登录验证码 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbIamLogcaptchaServiceImpl extends ServiceImpl<TbIamLogcaptchaMapper, TbIamLogcaptcha>  implements TbIamLogcaptchaService{

}
