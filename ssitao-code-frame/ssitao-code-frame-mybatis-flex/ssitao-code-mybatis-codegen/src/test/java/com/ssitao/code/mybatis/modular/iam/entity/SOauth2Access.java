package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

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
 * OAuth2授权认证信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_oauth2_access")
public class SOauth2Access extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * client_id
     */
    private String clientId;

    /**
     * 授权对应的用户ID
     */
    private String ownerId;

    /**
     * 授权码
     */
    private String accessToken;

    /**
     * 有效期
     */
    private String expiresIn;

    /**
     * 用于更新授权的token
     */
    private String refreshToken;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 更新时间
     */
    private BigDecimal updateTime;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 授权类型
     */
    private String grantType;

}
