package com.ssitao.code.modular.codegen.infrastructure.persistence.entity;

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
 * 生成参数 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_gen_params")
public class TbCodeGenParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 别名
     */
    private String alias;

    /**
     * 作者
     */
    private String author;

    /**
     * code 包
     */
    private String codePackage;

    /**
     * xml 路径
     */
    private String xmlPackage;

    /**
     * js 目录
     */
    private String jsPackage;

    /**
     * html 目录
     */
    private String htmlPackage;

    /**
     * 本地路径
     */
    private String localPath;

    /**
     * 编码
     */
    private String encoded;

    /**
     * 版权信息
     */
    private String copyright;

    private Integer userId;

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
