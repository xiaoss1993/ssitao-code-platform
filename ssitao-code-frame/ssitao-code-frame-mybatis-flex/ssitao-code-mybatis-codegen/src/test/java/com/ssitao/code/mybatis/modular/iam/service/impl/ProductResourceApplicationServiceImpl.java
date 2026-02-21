package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ProductResourceApplication;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.ProductResourceApplicationMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ProductResourceApplicationService;
import org.springframework.stereotype.Service;

/**
 * 产品资源申请 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class ProductResourceApplicationServiceImpl extends ServiceImpl<ProductResourceApplicationMapper, ProductResourceApplication>  implements ProductResourceApplicationService{

}
