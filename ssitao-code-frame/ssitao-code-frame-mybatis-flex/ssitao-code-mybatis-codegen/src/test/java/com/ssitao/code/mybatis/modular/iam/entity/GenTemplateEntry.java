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
 * 模板文件目录项 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("gen_template_entry")
public class GenTemplateEntry extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 模板组标识
     */
    private String groupKey;

    /**
     * 文件夹路径/模板文件名称（支持占位符）
     */
    private String filename;

    /**
     * 文件类型 1：文件夹 2：模板文件 3: 二进制文件
     */
    private Boolean type;

    /**
     * 父级Id
     */
    private String parentId;

    /**
     * 模板内容
     */
    private byte[] fileContent;

    /**
     * 模板引擎类型 1：velocity
     */
    private Boolean engineType;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 逻辑删除标识
     */
    private Long deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
