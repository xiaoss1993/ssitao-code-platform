package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.FlywaySchemaHistory;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.FlywaySchemaHistoryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.FlywaySchemaHistoryService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class FlywaySchemaHistoryServiceImpl extends ServiceImpl<FlywaySchemaHistoryMapper, FlywaySchemaHistory>  implements FlywaySchemaHistoryService{

}
