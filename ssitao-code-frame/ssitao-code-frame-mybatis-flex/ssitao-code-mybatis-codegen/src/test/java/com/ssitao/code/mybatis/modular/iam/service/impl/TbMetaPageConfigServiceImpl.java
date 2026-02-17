package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaPageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbMetaPageConfigMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaPageConfigService;
import org.springframework.stereotype.Service;

/**
 * 页面配置 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbMetaPageConfigServiceImpl extends ServiceImpl<TbMetaPageConfigMapper, TbMetaPageConfig>  implements TbMetaPageConfigService{

}
