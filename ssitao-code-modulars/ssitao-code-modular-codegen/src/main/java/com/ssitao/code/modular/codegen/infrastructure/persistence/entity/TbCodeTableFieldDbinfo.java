package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字段的数据库信息 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_field_dbinfo")
public class TbCodeTableFieldDbinfo implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 字段ID
     */
    private Integer fieldId;

    /**
     * 列名
     */
    private String fieldName;

    /**
     * 字段默认值
     */
    private String fieldDefault;

    /**
     *  字段注释
     */
    private String fieldContent;

    /**
     * 字段长度
     */
    private Integer fieldLength;

    /**
     * 字段类型
     */
    private String fieldType;

    /**
     * 小数点位数
     */
    private Integer fieldPointLength;

}
