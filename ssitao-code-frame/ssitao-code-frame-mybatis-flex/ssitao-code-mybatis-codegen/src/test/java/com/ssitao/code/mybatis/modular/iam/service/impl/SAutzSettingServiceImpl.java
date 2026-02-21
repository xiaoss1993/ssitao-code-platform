package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzSetting;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SAutzSettingMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzSettingService;
import org.springframework.stereotype.Service;

/**
 * 权限设置 服务层实现。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Service
public class SAutzSettingServiceImpl extends ServiceImpl<SAutzSettingMapper, SAutzSetting>  implements SAutzSettingService{

}
