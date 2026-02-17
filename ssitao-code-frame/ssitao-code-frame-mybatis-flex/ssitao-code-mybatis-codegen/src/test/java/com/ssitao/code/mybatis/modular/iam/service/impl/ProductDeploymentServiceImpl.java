package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ProductDeployment;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.ProductDeploymentMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ProductDeploymentService;
import org.springframework.stereotype.Service;

/**
 * 产品部署管理 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class ProductDeploymentServiceImpl extends ServiceImpl<ProductDeploymentMapper, ProductDeployment>  implements ProductDeploymentService{

}
