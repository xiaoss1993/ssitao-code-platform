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
 * 开发日志 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_developlog")
public class TbCoreDeveloplog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreDeveloplogId;

    /**
     * 用户名称
     */
    private String developlogUsername;

    /**
     * 用户id
     */
    private String developlogUserid;

    /**
     * 类型_name
     */
    private String developlogTypeName;

    /**
     * 类型
     */
    private String developlogTypeCode;

    /**
     * 动作_name
     */
    private String developlogActName;

    /**
     * 动作
     */
    private String developlogActCode;

    /**
     * 名称
     */
    private String developlogName;

    /**
     * 编码
     */
    private String developlogCode;

    /**
     * 资源主键
     */
    private String developlogId;

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

    /**
     * 产品id
     */
    private String syProductId;

}
