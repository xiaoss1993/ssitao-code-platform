package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbSysCalendarPush;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbSysCalendarPushMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbSysCalendarPushService;
import org.springframework.stereotype.Service;

/**
 * 工作日历消息推送 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbSysCalendarPushServiceImpl extends ServiceImpl<TbSysCalendarPushMapper, TbSysCalendarPush>  implements TbSysCalendarPushService{

}
