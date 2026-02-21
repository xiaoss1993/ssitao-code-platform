package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamCompany;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamCompanyMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamCompanyService;
import org.springframework.stereotype.Service;

/**
 * 公司管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamCompanyServiceImpl extends ServiceImpl<TbIamCompanyMapper, TbIamCompany>  implements TbIamCompanyService{

}
