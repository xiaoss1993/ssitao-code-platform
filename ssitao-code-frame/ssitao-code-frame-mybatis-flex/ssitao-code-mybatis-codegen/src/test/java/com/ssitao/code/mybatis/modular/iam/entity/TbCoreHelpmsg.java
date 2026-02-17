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
 * 平台帮助信息说明 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_helpmsg")
public class TbCoreHelpmsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreHelpmsgId;

    /**
     * 编码
     */
    private String helpmsgCode;

    /**
     * 描述
     */
    private String helpmsgMsg;

    /**
     * 名称
     */
    private String helpmsgName;

    /**
     * 类型
     */
    private String helpmsgType;

    /**
     * 数据
     */
    private String helpmsgSj;

    /**
     * 数据配置描述
     */
    private String helpmsgSjpzms;

    /**
     * 数据项
     */
    private String helpmsgSjx;

    /**
     * 批量描述
     */
    private String helpmsgPlms;

    /**
     * 类型名称
     */
    private String helpmsgTypeName;

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
