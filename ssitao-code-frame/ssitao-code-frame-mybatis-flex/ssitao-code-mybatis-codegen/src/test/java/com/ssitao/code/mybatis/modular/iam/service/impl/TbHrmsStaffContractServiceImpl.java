package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffContract;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbHrmsStaffContractMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffContractService;
import org.springframework.stereotype.Service;

/**
 * 员工合同信息 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbHrmsStaffContractServiceImpl extends ServiceImpl<TbHrmsStaffContractMapper, TbHrmsStaffContract>  implements TbHrmsStaffContractService{

}
