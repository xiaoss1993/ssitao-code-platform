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
 * 资源_留痕 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_tabletrace")
public class TbCoreTabletrace extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreTabletraceId;

    /**
     * 操作时间
     */
    private String tabletraceCzsj;

    /**
     * 修改内容
     */
    private String tabletraceXgnr;

    /**
     * 修改列类型
     */
    private String tabletraceFieldtype;

    /**
     * 修改列名称
     */
    private String tabletraceFieldname;

    /**
     * 资源表主键
     */
    private String tabletraceResourcetableId;

    /**
     * 操作人编码
     */
    private String tabletraceCzrcode;

    /**
     * 是否应用
     */
    private String tabletraceSfyy;

    /**
     * 操作人
     */
    private String tabletraceCzrname;

    /**
     * 操作表编码
     */
    private String tabletraceTablecode;

    /**
     * 操作类型
     */
    private String tabletraceOper;

    /**
     * 操作表
     */
    private String tabletraceTablename;

    /**
     * 修改内容json
     */
    private String tabletraceXgnrjson;

    /**
     * 修改列编码
     */
    private String tabletraceFieldcode;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 修改人部门编码
     */
    private String syModifyorg;

    /**
     * 修改人编码
     */
    private String syModifyuser;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 表单上传虚字段
     */
    private String syFormuploadfiles;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 拼音简写
     */
    private String syPyjz;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 拼音全称
     */
    private String syPyqc;

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

}
