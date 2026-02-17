package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ReleaseHistory;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.ReleaseHistoryMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ReleaseHistoryService;
import org.springframework.stereotype.Service;

/**
 * 发布历史记录 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class ReleaseHistoryServiceImpl extends ServiceImpl<ReleaseHistoryMapper, ReleaseHistory>  implements ReleaseHistoryService{

}
