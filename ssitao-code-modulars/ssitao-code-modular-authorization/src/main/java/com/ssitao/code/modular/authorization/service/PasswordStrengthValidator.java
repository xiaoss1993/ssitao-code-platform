package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.commons.service.Validator;

/**
 * 密码强度验证器,在保存用户信息,需要修改密码的时候,会调用此接口来验证密码强度.
 *
 *
 * @since 3.0
 */
public interface PasswordStrengthValidator extends Validator<String> {
}
