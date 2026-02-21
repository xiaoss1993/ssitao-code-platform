package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamRoleperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamRolepermMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamRolepermService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamRolepermServiceImpl extends ServiceImpl<TbIamRolepermMapper, TbIamRoleperm>  implements TbIamRolepermService{

}
