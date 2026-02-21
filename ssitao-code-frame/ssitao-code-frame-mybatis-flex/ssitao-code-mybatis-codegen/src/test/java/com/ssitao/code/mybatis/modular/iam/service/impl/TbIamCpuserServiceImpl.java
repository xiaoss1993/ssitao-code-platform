package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamCpuser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamCpuserMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamCpuserService;
import org.springframework.stereotype.Service;

/**
 * 三方人员管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamCpuserServiceImpl extends ServiceImpl<TbIamCpuserMapper, TbIamCpuser>  implements TbIamCpuserService{

}
