package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM字典类型数据对象
 * 对应数据库表：tb_core_dictionary
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_core_dictionary")
public class IamDictTypeDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    @Column(value = "tb_core_dictionary_id")
    private String tbCoreDictionaryId;

    /**
     * 字典编码
     */
    @Column(value = "dictionary_ddcode")
    private String dictionaryDdcode;

    /**
     * 字典名称
     */
    @Column(value = "dictionary_ddname")
    private String dictionaryDdname;

    /**
     * 字典类型
     */
    @Column(value = "dictionary_ddtype")
    private String dictionaryDdtype;

    /**
     * 所属子系统
     */
    @Column(value = "dictionary_belongsto")
    private String dictionaryBelongsto;

    /**
     * 所属子系统名称
     */
    @Column(value = "dictionary_belongstoname")
    private String dictionaryBelongstoname;

    /**
     * 类型
     */
    @Column(value = "dictionary_dictype")
    private String dictionaryDictype;

    /**
     * 外部自定实体名称
     */
    @Column(value = "dictionary_classname")
    private String dictionaryClassname;

    /**
     * 创建时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 创建人ID
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 创建人名称
     */
    @Column(value = "sy_createusername")
    private String syCreateusername;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;

    /**
     * 修改人ID
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 数据状态
     */
    @Column(value = "sy_status")
    private String syStatus;

    /**
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户名称
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

}
