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
 * 字段对应关系 实体类。
 *
 * @author ssitao
 * @since 2026-04-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_code_table_field")
public class TbCodeTableField implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 关联表ID
     */
    private Integer tableId;

    /**
     * 列名
     */
    private String fieldName;

    /**
     * 名称
     */
    private String name;

    /**
     * 功能
     */
    private String content;

    /**
     * 类型
     */
    private String type;

    /**
     * 是否主键
     */
    private String isKey;

    /**
     * 是否可以为空
     */
    private String isNull;

    /**
     * 是否显示新增
     */
    private Integer isShowAdd;

    /**
     * 是否显示编辑
     */
    private Integer isShowEdit;

    /**
     * 是否显示详情
     */
    private Integer isShowDetail;

    /**
     * 是否列表显示
     */
    private Integer isShowList;

    /**
     * 是否Excel导入
     */
    private Integer isImport;

    /**
     * 是否导出Excel
     */
    private Integer isExport;

    /**
     * 是否查询
     */
    private Integer isQuery;

    /**
     * 查询类型
     */
    private String queryMode;

    /**
     * 显示类型
     */
    private String showType;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 字段名称
     */
    private String dictName;

    /**
     * 字段类型 1 枚举 2 字段 3 列表
     */
    private Integer dictType;

}
