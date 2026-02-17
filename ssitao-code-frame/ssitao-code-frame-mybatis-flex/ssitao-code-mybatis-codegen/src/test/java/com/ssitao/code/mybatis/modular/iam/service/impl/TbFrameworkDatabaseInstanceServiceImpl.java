package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkDatabaseInstance;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbFrameworkDatabaseInstanceMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkDatabaseInstanceService;
import org.springframework.stereotype.Service;

/**
 * 数据库实例 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbFrameworkDatabaseInstanceServiceImpl extends ServiceImpl<TbFrameworkDatabaseInstanceMapper, TbFrameworkDatabaseInstance>  implements TbFrameworkDatabaseInstanceService{

}
