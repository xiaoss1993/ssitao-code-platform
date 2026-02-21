package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamPerm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamPermMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamPermService;
import org.springframework.stereotype.Service;

/**
 * 权限管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamPermServiceImpl extends ServiceImpl<TbIamPermMapper, TbIamPerm>  implements TbIamPermService{

}
