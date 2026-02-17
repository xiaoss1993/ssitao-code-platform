package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SConfigMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统配置文件 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SConfigServiceImpl extends ServiceImpl<SConfigMapper, SConfig>  implements SConfigService{

}
