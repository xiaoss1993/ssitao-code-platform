package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SPermission;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SPermissionMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SPermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SPermissionServiceImpl extends ServiceImpl<SPermissionMapper, SPermission>  implements SPermissionService{

}
