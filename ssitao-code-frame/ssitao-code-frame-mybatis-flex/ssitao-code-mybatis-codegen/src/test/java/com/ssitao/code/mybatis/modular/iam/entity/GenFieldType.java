package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
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
 * DB和后端数据类型 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("gen_field_type")
public class GenFieldType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 模板组id
     */
    private String groupKey;

    /**
     * DB属性类型
     */
    private String columnKey;

    /**
     * 对应属性类型
     */
    private String columnValue;

    /**
     * 数据库类型（1:MySQL，2:Oracle，3:PostGreSql，4:SqlServer）
     */
    private String dbType;

    /**
     * 属性包路径+类名
     */
    private String packageName;

    /**
     * 逻辑删除字段（1删除0正常）
     */
    private String deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
