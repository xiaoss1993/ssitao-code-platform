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
 * 用户 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_user")
public class SUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 用户状态
     */
    private BigDecimal status;

    /**
     * 上一次登录的ip地址
     */
    private String lastLoginIp;

    /**
     * 上一次登录时间
     */
    private BigDecimal lastLoginTime;

    /**
     * 创建者ID
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

}
