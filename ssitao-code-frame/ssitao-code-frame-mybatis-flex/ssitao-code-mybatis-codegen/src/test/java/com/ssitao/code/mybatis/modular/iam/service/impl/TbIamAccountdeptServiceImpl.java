package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccountdept;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamAccountdeptMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountdeptService;
import org.springframework.stereotype.Service;

/**
 * 账号部门关联 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamAccountdeptServiceImpl extends ServiceImpl<TbIamAccountdeptMapper, TbIamAccountdept>  implements TbIamAccountdeptService{

}
