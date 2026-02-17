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
 * OAuth2 服务配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_oauth2_server")
public class SOauth2Server extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 备注
     */
    private String describe;

    /**
     * api根地址
     */
    private String apiBaseUrl;

    /**
     * 认证地址
     */
    private String authUrl;

    /**
     * 重定向地址
     */
    private String redirectUri;

    /**
     * token获取地址
     */
    private String accessTokenUrl;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 服务提供商
     */
    private String provider;

    /**
     * 其他配置
     */
    private String properties;

    /**
     * 状态
     */
    private BigDecimal status;

}
