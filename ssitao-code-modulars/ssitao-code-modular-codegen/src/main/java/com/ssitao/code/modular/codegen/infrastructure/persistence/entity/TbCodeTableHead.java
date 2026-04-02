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
 * 表单管理 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_head")
public class TbCodeTableHead implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 表名
     */
    private String tableName;

    private String className;

    /**
     * 表名称
     */
    private String content;

    /**
     * 是否导入Excel
     */
    private Integer isImport;

    /**
     * 是否导出Excel
     */
    private Integer isExport;

    /**
     * 是否分页
     */
    private Integer isPagination;

    /**
     * 是否添加日志
     */
    private Integer isLog;

    /**
     * 是否添加协议
     */
    private Integer isProtocol;

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
