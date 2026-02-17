package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SFileInfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.mapper.SFileInfoMapper;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SFileInfoService;
import org.springframework.stereotype.Service;

/**
 * 文件信息 服务层实现。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Service
public class SFileInfoServiceImpl extends ServiceImpl<SFileInfoMapper, SFileInfo>  implements SFileInfoService{

}
