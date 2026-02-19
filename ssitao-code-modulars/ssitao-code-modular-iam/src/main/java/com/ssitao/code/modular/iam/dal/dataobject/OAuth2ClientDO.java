package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * OAuth2客户端数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_oauth2_client")
public class OAuth2ClientDO {

    /**
     * 客户端ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 客户端标识
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 客户端类型：1-Web应用 2-移动应用 3-桌面应用
     */
    private Integer clientType;

    /**
     * 授权类型：1-授权码 2-密码 3-客户端凭证 4-刷新令牌
     */
    private String grantTypes;

    /**
     * 重定向URI
     */
    private String redirectUris;

    /**
     * 授权范围
     */
    private String scopes;

    /**
     * 自动授权的授权范围
     */
    private String autoApproveScopes;

    /**
     * 访问令牌有效期（秒）
     */
    private Integer accessTokenValidity;

    /**
     * 刷新令牌有效期（秒）
     */
    private Integer refreshTokenValidity;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除：0-否 1-是
     */
    private Integer deleted;

}
