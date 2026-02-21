package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreUserinfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreUserinfoMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreUserinfoService;
import org.springframework.stereotype.Service;

/**
 * 用户个性化（用户态） 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreUserinfoServiceImpl extends ServiceImpl<TbCoreUserinfoMapper, TbCoreUserinfo>  implements TbCoreUserinfoService{

}
