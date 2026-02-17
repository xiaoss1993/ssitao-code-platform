package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDepartment;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamDepartmentMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门管理 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class TbIamDepartmentServiceImpl extends ServiceImpl<TbIamDepartmentMapper, TbIamDepartment>  implements TbIamDepartmentService{

}
