package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOrganization;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SOrganizationMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOrganizationService;
import org.springframework.stereotype.Service;

/**
 * 组织,公司 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SOrganizationServiceImpl extends ServiceImpl<SOrganizationMapper, SOrganization>  implements SOrganizationService{

}
