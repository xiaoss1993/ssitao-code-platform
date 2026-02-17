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
 * 权限管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_perm")
public class TbIamPerm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    private String permName;

    /**
     * 权限编码
     */
    private String permCode;

    /**
     * 权限类型_code
     */
    private String permTypeCode;

    /**
     * 权限类型_name
     */
    private String permTypeName;

    /**
     * 是否输出权限_code
     */
    private String permOutputCode;

    /**
     * 是否输出权限_name
     */
    private String permOutputName;

    /**
     * 输出模板
     */
    private String permOutputTemplate;

    /**
     * 备注信息
     */
    private String permRemark;

    /**
     * 所属对象
     */
    private String permObject;

    /**
     * 操作类型_code
     */
    private String permOperateCode;

    /**
     * 操作类型_name
     */
    private String permOperateName;

    /**
     * 目标对象_id
     */
    private String permTargetId;

    /**
     * 目标对象_code
     */
    private String permTargetCode;

    /**
     * 目标对象_name
     */
    private String permTargetName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

    /**
     * 主键id
     */
    @Id
    private String tbIamPermId;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

}
