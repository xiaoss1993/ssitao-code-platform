package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ProductResourceApplicationDetails;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.ProductResourceApplicationDetailsMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ProductResourceApplicationDetailsService;
import org.springframework.stereotype.Service;

/**
 * 产品资源申请明细 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class ProductResourceApplicationDetailsServiceImpl extends ServiceImpl<ProductResourceApplicationDetailsMapper, ProductResourceApplicationDetails>  implements ProductResourceApplicationDetailsService{

}
