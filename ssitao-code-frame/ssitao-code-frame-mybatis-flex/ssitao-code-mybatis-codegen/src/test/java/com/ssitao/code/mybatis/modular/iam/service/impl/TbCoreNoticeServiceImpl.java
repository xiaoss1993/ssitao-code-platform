package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreNotice;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreNoticeMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreNoticeService;
import org.springframework.stereotype.Service;

/**
 * 首页新闻公告 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreNoticeServiceImpl extends ServiceImpl<TbCoreNoticeMapper, TbCoreNotice>  implements TbCoreNoticeService{

}
