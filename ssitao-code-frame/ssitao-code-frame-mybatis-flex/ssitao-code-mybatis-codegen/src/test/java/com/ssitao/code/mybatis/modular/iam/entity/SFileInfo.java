package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 文件信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_file_info")
public class SFileInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    private String uId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件相对路径
     */
    private String location;

    /**
     * 类型
     */
    private String type;

    /**
     * md5校验值
     */
    private String md5;

    /**
     * 文件大小
     */
    private BigDecimal size;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 分类
     */
    private String classified;

    /**
     * 创建时间
     */
    private BigDecimal createTime;

    /**
     * 创建人
     */
    private String creatorId;

}
