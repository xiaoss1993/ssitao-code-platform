package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbProductResources;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbProductResourcesMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbProductResourcesService;
import org.springframework.stereotype.Service;

/**
 * 产品资源管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbProductResourcesServiceImpl extends ServiceImpl<TbProductResourcesMapper, TbProductResources>  implements TbProductResourcesService{

}
