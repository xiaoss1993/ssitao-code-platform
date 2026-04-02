package com.ssitao.code.modular.meta.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库链接信息 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_dbinfo")
public class TbCodeDbinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String alias;

    /**
     * 数据库驱动
     */
    private String dbDriver;

    /**
     * 数据库地址
     */
    private String dbUrl;

    /**
     * 数据库账户
     */
    private String dbUserName;

    /**
     * 连接密码
     */
    private String dbPassword;

    private String userId;

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * 创建人
     */
    private String crtUserId;

    /**
     * 创建时间
     */
    private LocalDateTime crtTime;

    /**
     * 修改人
     */
    private String mdfUserId;

    /**
     * 修改时间
     */
    private LocalDateTime mdfTime;

}
