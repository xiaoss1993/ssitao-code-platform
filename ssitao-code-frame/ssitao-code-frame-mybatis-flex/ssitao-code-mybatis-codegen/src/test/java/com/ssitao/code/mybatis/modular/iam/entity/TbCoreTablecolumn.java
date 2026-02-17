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
 * 资源_列管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_tablecolumn")
public class TbCoreTablecolumn extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreTablecolumnId;

    /**
     * 列编码
     */
    private String tablecolumnCode;

    /**
     * 列名称
     */
    private String tablecolumnName;

    /**
     * 类型
     */
    private String tablecolumnType;

    /**
     * 长度
     */
    private String tablecolumnLength;

    /**
     * 注解
     */
    private String tablecolumnNote;

    /**
     * 是否允许是空
     */
    private String tablecolumnIsnull;

    /**
     * 是否已创建
     */
    private String tablecolumnIscreate;

    /**
     * 默认数值
     */
    private String tablecolumnDefaultvalue;

    /**
     * 树形类型
     */
    private String tablecolumnTreetype;

    /**
     * 表资源外键
     */
    private String tablecolumnResourcetableId;

    /**
     * 表编码
     */
    private String tablecolumnTablecode;

    /**
     * 分类
     */
    private String tablecolumnClassify;

    /**
     * 是否唯一
     */
    private String tablecolumnUnique;

    /**
     * 原编码
     */
    private String tablecolumnOldcode;

    /**
     * 原是否唯一
     */
    private String tablecolumnOldunique;

    /**
     * 数据字典配置
     */
    private String tablecolumnDicconfig;

    /**
     * 查询选择配置
     */
    private String tablecolumnQueryconfig;

    /**
     * 子功能配置
     */
    private String tablecolumnChildconfig;

    /**
     * 数据字典查询字段
     */
    private String tablecolumnDicqueryfield;

    /**
     * 视图配置
     */
    private String tablecolumnViewconfig;

    /**
     * 唯一约束编码
     */
    private String tablecolumnUniquecode;

    /**
     * 描述
     */
    private String tablecolumnRemark;

    /**
     * 英文名称
     */
    private String tablecolumnNameEn;

    /**
     * 字段来源
     */
    private String tablecolumnSource;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 修改人部门id
     */
    private String syModifyorgid;

    /**
     * 修改人id
     */
    private String syModifyuserid;

    /**
     * 审核标记
     */
    private String syAudflag;

    /**
     * 登记者所在部门编码
     */
    private String syCreateorg;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人编码
     */
    private String syCreateuser;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人部门编码
     */
    private String syModifyorg;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 修改人编码
     */
    private String syModifyuser;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

}
