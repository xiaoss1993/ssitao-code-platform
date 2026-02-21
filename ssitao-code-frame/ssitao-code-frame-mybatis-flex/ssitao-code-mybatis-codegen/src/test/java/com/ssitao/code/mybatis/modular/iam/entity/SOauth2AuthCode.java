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
 * OAuth2授权码信息 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_oauth2_auth_code")
public class SOauth2AuthCode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * client_id
     */
    private String clientId;

    /**
     * 授权对应的用户ID
     */
    private String userId;

    /**
     * 授权码
     */
    private String code;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 重定向URI
     */
    private String redirectUri;

}
