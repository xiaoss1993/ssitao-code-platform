package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 资源_索引管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_tableindex")
public class TbCoreTableindex extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreTableindexId;

    /**
     * 备注
     */
    private String tableindexRemark;

    /**
     * 字段编码
     */
    private String tableindexFieldcode;

    /**
     * 字段名称
     */
    private String tableindexFieldname;

    private String tableindexType;

    /**
     * 名称
     */
    private String tableindexName;

    /**
     * 外键
     */
    private String tableindexResourcetableId;

    /**
     * 唯一
     */
    private String tableindexUnique;

    /**
     * 创建
     */
    private String tableindexIscreate;

    /**
     * 表名
     */
    private String tableindexTablecode;

    /**
     * 分类
     */
    private String tableindexClassify;

    /**
     * 产品id
     */
    private String syProductId;

    /**
     * 产品名称
     */
    private String syProductName;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 数据状态
     */
    private String syStatus;

}
