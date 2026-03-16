package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.Validator;

/**
 * 用户名验证器,在保存用户信息的时候,用于验证用户名是否合法
 *
 *
 * @since 3.0
 */
public interface UsernameValidator extends Validator<String> {
}
