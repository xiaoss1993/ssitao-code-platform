package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbProductFlowlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbProductFlowlogMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbProductFlowlogService;
import org.springframework.stereotype.Service;

/**
 * 产品申请部署流转日志 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbProductFlowlogServiceImpl extends ServiceImpl<TbProductFlowlogMapper, TbProductFlowlog>  implements TbProductFlowlogService{

}
