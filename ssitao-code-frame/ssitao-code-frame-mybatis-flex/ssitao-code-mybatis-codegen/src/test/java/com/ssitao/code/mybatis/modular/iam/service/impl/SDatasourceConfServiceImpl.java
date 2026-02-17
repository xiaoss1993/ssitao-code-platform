package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDatasourceConf;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SDatasourceConfMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDatasourceConfService;
import org.springframework.stereotype.Service;

/**
 * 数据源配置 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SDatasourceConfServiceImpl extends ServiceImpl<SDatasourceConfMapper, SDatasourceConf>  implements SDatasourceConfService{

}
