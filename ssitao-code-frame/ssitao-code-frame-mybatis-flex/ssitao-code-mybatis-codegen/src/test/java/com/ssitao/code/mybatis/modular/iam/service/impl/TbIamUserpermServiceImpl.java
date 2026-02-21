package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamUserperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamUserpermMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamUserpermService;
import org.springframework.stereotype.Service;

/**
 * 用户权限关联 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamUserpermServiceImpl extends ServiceImpl<TbIamUserpermMapper, TbIamUserperm>  implements TbIamUserpermService{

}
