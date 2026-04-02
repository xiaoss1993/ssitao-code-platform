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
 * 模板 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_template")
public class TbCodeTemplate implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板地址
     */
    private String templatePath;

    private String userId;

    private String templateDesc;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 组ID
     */
    private Integer groupId;

    /**
     * 本地路径
     */
    private String localPath;

    /**
     * 模板类型
     */
    private String templateType;

    /**
     * 创建人
     */
    private Integer crtUserId;

    /**
     * 原ID
     */
    private Integer originalId;

    /**
     * 版本 1 正常版本 2 历史版本
     */
    private Integer version;

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
