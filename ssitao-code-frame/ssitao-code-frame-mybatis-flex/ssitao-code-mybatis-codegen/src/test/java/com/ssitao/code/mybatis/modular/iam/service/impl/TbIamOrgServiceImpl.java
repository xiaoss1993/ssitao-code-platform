package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamOrg;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamOrgMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamOrgService;
import org.springframework.stereotype.Service;

/**
 * 机构管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamOrgServiceImpl extends ServiceImpl<TbIamOrgMapper, TbIamOrg>  implements TbIamOrgService{

}
