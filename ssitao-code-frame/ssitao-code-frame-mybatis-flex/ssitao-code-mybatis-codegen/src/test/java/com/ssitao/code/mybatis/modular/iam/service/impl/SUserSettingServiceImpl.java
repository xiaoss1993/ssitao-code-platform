package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SUserSetting;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SUserSettingMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SUserSettingService;
import org.springframework.stereotype.Service;

/**
 * 用户设置 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SUserSettingServiceImpl extends ServiceImpl<SUserSettingMapper, SUserSetting>  implements SUserSettingService{

}
