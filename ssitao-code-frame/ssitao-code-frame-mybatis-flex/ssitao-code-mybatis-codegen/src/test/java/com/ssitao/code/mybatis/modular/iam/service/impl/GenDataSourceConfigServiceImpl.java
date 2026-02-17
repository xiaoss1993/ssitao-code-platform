package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenDataSourceConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.GenDataSourceConfigMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenDataSourceConfigService;
import org.springframework.stereotype.Service;

/**
 * 数据源 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class GenDataSourceConfigServiceImpl extends ServiceImpl<GenDataSourceConfigMapper, GenDataSourceConfig>  implements GenDataSourceConfigService{

}
