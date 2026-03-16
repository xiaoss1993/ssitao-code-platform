

package com.ssitao.code.modular.authorization.service.simple;

import com.ssitao.code.frame.authorization.AuthenticationManager;

/**
 * 缓存所需常量
 *
 *
 */
public interface CacheConstants {
    String MENU_CACHE_NAME = "hsweb-menu-";

    String USER_MENU_CACHE_NAME = "hsweb-user-menu-";

    String USER_CACHE_NAME = "user-";

    String USER_AUTH_CACHE_NAME = AuthenticationManager.USER_AUTH_CACHE_NAME;

}
