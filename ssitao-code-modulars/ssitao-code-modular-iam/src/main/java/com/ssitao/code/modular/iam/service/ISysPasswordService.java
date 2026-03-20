package com.ssitao.code.modular.iam.service;

import com.ssitao.code.common.core.domain.entity.SysUser;

public interface ISysPasswordService {
    boolean matches(SysUser user, String password);
    public String encryptPassword(String loginName, String password, String salt);
}
