package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * OAuth2用户授权信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_oauth2_user_token")
public class SOauth2UserToken extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 客户端用户id
     */
    private String clientUserId;

    /**
     * 服务端用户id
     */
    private String serverUserId;

    /**
     * 服务端id
     */
    private String serverId;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 授权码
     */
    private String accessToken;

    /**
     * 更新码
     */
    private String refreshToken;

    /**
     * 有效期
     */
    private BigDecimal expiresIn;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 更新时间
     */
    private BigDecimal updateTime;

    /**
     * 授权方式
     */
    private String grantType;

}
