

package com.ssitao.code.modular.authorization.service;

/**
 * 密码编码器,用于将明文密码编码成密文
 *
 *
 * @since 3.0
 */
public interface PasswordEncoder {

    /**
     * 编码,相同的参数,编码的结果永远相同.
     *
     * @param password 明文密码,不能为<code>null</code>
     * @param salt     加密盐
     * @return 加密结果
     */
    String encode(String password, String salt);
}
