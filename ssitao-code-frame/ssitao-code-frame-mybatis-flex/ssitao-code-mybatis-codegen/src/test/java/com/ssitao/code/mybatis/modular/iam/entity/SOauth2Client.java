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
 * OAuth2客户端 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_oauth2_client")
public class SOauth2Client extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * client_id
     */
    @Id
    private String uId;

    /**
     * client_secret
     */
    private String secret;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 备注
     */
    private String describe;

    /**
     * 客户端类型
     */
    private String type;

    /**
     * 绑定的用户ID
     */
    private String ownerId;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * redirect_uri
     */
    private String redirectUri;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 支持的授权列表
     */
    private String supportGrantTypes;

    /**
     * 默认认证过期时间
     */
    private BigDecimal defaultExpiresIn;

    /**
     * 默认认证范围
     */
    private String defaultGrantScope;

    /**
     * 状态
     */
    private BigDecimal status;

}
