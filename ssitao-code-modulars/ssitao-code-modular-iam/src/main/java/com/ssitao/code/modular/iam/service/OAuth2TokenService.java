package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.dal.dataobject.OAuth2AccessTokenDO;

/**
 * OAuth2令牌服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface OAuth2TokenService {

    /**
     * 创建访问令牌
     *
     * @param accessToken  访问令牌
     * @param refreshToken 刷新令牌
     * @param userId       用户ID
     * @param clientId     客户端ID
     * @param scopes       授权范围
     */
    void createAccessToken(String accessToken, String refreshToken, Long userId, String clientId, String scopes);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 新的访问令牌
     */
    String refreshAccessToken(String refreshToken);

    /**
     * 删除访问令牌
     *
     * @param accessToken 访问令牌
     */
    void removeAccessToken(String accessToken);

    /**
     * 根据访问令牌获取令牌信息
     *
     * @param accessToken 访问令牌
     * @return OAuth2访问令牌
     */
    OAuth2AccessTokenDO getAccessToken(String accessToken);

}
