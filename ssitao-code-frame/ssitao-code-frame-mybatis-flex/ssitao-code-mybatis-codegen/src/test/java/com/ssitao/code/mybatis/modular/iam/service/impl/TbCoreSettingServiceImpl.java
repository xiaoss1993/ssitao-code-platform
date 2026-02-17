package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreSetting;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.TbCoreSettingMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreSettingService;
import org.springframework.stereotype.Service;

/**
 * 系统设置 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class TbCoreSettingServiceImpl extends ServiceImpl<TbCoreSettingMapper, TbCoreSetting>  implements TbCoreSettingService{

}
