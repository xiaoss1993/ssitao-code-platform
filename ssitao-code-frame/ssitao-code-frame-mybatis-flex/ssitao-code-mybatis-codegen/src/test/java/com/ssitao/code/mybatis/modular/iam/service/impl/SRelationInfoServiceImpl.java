package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRelationInfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SRelationInfoMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRelationInfoService;
import org.springframework.stereotype.Service;

/**
 * 关系信息 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SRelationInfoServiceImpl extends ServiceImpl<SRelationInfoMapper, SRelationInfo>  implements SRelationInfoService{

}
