package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * OAuth2访问令牌数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_oauth2_access_token")
public class OAuth2AccessTokenDO {

    /**
     * 令牌ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 授权范围
     */
    private String scopes;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
