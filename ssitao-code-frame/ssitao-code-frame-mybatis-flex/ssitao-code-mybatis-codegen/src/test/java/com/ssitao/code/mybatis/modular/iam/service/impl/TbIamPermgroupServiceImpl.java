package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamPermgroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamPermgroupMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamPermgroupService;
import org.springframework.stereotype.Service;

/**
 * 权限组 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamPermgroupServiceImpl extends ServiceImpl<TbIamPermgroupMapper, TbIamPermgroup>  implements TbIamPermgroupService{

}
