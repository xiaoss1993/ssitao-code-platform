package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDeptuser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamDeptuserMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDeptuserService;
import org.springframework.stereotype.Service;

/**
 * 部门人员关联 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamDeptuserServiceImpl extends ServiceImpl<TbIamDeptuserMapper, TbIamDeptuser>  implements TbIamDeptuserService{

}
