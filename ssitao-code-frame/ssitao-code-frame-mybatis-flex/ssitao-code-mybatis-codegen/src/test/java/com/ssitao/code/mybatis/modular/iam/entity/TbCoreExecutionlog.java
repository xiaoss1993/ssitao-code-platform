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
 * 执行日志 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_executionlog")
public class TbCoreExecutionlog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreExecutionlogId;

    /**
     * 请求耗时
     */
    private Integer executionlogDuration;

    /**
     * 耗时描述
     */
    private String executionlogDurationstr;

    /**
     * 执行时间
     */
    private String executionlogExectime;

    /**
     * 执行人
     */
    private String executionlogExecuuser;

    /**
     * 执行人编码
     */
    private String executionlogExecuusercode;

    /**
     * 请求参数
     */
    private String executionlogRequestparams;

    /**
     * 请求url
     */
    private String executionlogRequesturl;

    /**
     * 表名
     */
    private String executionlogTablecode;

    /**
     * 执行人id
     */
    private String executionlogExecuuserid;

    /**
     * tokenid
     */
    private String executionlogTokenid;

    /**
     * 数据源
     */
    private String executionlogDsname;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 审核标记
     */
    private String syAudflag;

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
