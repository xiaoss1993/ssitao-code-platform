package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTableview;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreTableviewMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTableviewService;
import org.springframework.stereotype.Service;

/**
 * 资源-->视图管理 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreTableviewServiceImpl extends ServiceImpl<TbCoreTableviewMapper, TbCoreTableview>  implements TbCoreTableviewService{

}
