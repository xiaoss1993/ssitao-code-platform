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
 * 模板组 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_template_group")
public class TbCodeTemplateGroup implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 组名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 版本 1 正常版本 2 历史版本
     */
    private Integer version;

    /**
     * 分享状态
     */
    private Integer shareStatus;

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
