package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import java.io.Serializable;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 模板分享 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_template_share")
public class TbCodeTemplateShare implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板地址
     */
    private String templatePath;

    /**
     * 模板效果
     */
    private String templateEffect;

    private String templateDesc;

}
