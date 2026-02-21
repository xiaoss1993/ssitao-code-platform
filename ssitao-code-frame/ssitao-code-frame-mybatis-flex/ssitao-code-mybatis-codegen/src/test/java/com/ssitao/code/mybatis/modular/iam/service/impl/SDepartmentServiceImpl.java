package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDepartment;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SDepartmentMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDepartmentService;
import org.springframework.stereotype.Service;

/**
 * 部门 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SDepartmentServiceImpl extends ServiceImpl<SDepartmentMapper, SDepartment>  implements SDepartmentService{

}
