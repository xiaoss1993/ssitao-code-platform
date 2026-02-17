package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbSysCalendar;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbSysCalendarMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbSysCalendarService;
import org.springframework.stereotype.Service;

/**
 * 工作日历 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbSysCalendarServiceImpl extends ServiceImpl<TbSysCalendarMapper, TbSysCalendar>  implements TbSysCalendarService{

}
