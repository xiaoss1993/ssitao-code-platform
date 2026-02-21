package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamSupplier;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamSupplierMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamSupplierService;
import org.springframework.stereotype.Service;

/**
 * 供应商管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamSupplierServiceImpl extends ServiceImpl<TbIamSupplierMapper, TbIamSupplier>  implements TbIamSupplierService{

}
