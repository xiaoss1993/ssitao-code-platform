package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamBury;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbIamBuryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamBuryService;
import org.springframework.stereotype.Service;

/**
 * 操作埋点记录 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbIamBuryServiceImpl extends ServiceImpl<TbIamBuryMapper, TbIamBury>  implements TbIamBuryService{

}
