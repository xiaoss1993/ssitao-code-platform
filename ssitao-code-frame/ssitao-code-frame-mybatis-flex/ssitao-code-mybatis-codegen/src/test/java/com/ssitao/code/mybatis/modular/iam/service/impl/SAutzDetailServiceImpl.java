package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzDetail;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SAutzDetailMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzDetailService;
import org.springframework.stereotype.Service;

/**
 * 权限设置详情 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SAutzDetailServiceImpl extends ServiceImpl<SAutzDetailMapper, SAutzDetail>  implements SAutzDetailService{

}
