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
 * 页面字段 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_page_field")
public class TbMetaPageField extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段ID（主键）
     */
    @Id
    private String id;

    /**
     * 页面配置ID
     */
    private String pageId;

    /**
     * 字段ID
     */
    private String fid;

    /**
     * 字段键
     */
    private String key;

    /**
     * 字段文本
     */
    private String text;

    /**
     * 字段类型（TEXT/NUMBER/DATE/SELECT/USER/AREA等）
     */
    private String type;

    /**
     * 字段宽度
     */
    private String width;

    /**
     * 是否可搜索（1-true，0-false）
     */
    private Boolean search;

    /**
     * 是否可排序（1-true，0-false）
     */
    private Boolean sorting;

    /**
     * 是否必填（1-true，0-false）
     */
    private Boolean required;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
