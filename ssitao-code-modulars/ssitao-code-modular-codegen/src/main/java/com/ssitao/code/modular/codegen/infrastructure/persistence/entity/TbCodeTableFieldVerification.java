package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段校验规则 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_field_verification")
public class TbCodeTableFieldVerification implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 字段ID
     */
    private Integer fieldId;

    /**
     * 前端校验
     */
    private Integer viewVerification;

    /**
     * 后台校验
     */
    private Integer serverVerification;

    /**
     * 允许空
     */
    private Integer notNull;

    /**
     * 最小
     */
    private String minNum;

    /**
     * 最大
     */
    private String maxNum;

    /**
     * 正则
     */
    private String regex;

    /**
     * 正则类型
     */
    private Integer regexType;

}
