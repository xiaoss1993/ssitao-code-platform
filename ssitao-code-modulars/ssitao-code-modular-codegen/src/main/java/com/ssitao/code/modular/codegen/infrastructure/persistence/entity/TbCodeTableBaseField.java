package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础字段 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_base_field")
public class TbCodeTableBaseField implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 用户
     */
    private Integer userId;

    /**
     * 别名
     */
    private String alias;

    /**
     * 字段名(检验用)
     */
    private String fieldNameCheck;

    /**
     * 字段ID
     */
    private Integer fieldId;

    /**
     * 创建人
     */
    private Integer crtUserId;

    /**
     * 创建时间
     */
    private LocalDateTime crtTime;

    /**
     * 修改人
     */
    private Integer mdfUserId;

    /**
     * 修改时间
     */
    private LocalDateTime mdfTime;

}
