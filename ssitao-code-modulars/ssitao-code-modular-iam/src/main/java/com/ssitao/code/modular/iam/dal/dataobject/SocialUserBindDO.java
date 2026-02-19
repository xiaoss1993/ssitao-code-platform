package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 社交用户绑定数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_social_user_bind")
public class SocialUserBindDO {

    /**
     * 绑定ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 社交类型：1-微信 2-QQ 3-微博 4-支付宝
     */
    private Integer socialType;

    /**
     * 社交OpenID
     */
    private String openid;

    /**
     * 社交UnionID
     */
    private String unionid;

    /**
     * 社交昵称
     */
    private String nickname;

    /**
     * 社交头像
     */
    private String avatar;

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

}
