package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM字典数据数据对象
 * 对应数据库表：tb_iam_dictdata
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_iam_dictdata")
public class IamDictDataDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    private String tbIamDictdataId;

    /**
     * 字典类型ID
     */
    @Column(value = "tb_iam_dicttype_id")
    private String tbIamDicttypeId;

    /**
     * 字典标签
     */
    @Column(value = "dictdata_label")
    private String dictdataLabel;

    /**
     * 字典值
     */
    @Column(value = "dictdata_value")
    private String dictdataValue;

    /**
     * 排序
     */
    @Column(value = "sy_orderindex")
    private Integer syOrderindex;

    /**
     * 颜色类型
     */
    @Column(value = "color_type")
    private String colorType;

    /**
     * CSS样式
     */
    @Column(value = "css_class")
    private String cssClass;

    /**
     * 状态：1-启用 0-禁用
     */
    @Column(value = "status")
    private Boolean status;

    /**
     * 备注
     */
    @Column(value = "remark")
    private String remark;

    /**
     * 创建时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;

    /**
     * 创建人
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 更新人
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

}
