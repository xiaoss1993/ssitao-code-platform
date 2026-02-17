package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 用户设置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_user_setting")
public class SUserSetting extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 说明
     */
    private String describe;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 配置标识
     */
    private String key;

    /**
     * 配置内容
     */
    private String setting;

    /**
     * 自定义配置id
     */
    private String settingId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;

    /**
     * 用户可操作权限
     */
    private String permission;

}
