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
 * 执行异常 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_exception")
public class TbCoreException extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreExceptionId;

    /**
     * 执行时间
     */
    private String exceptionExecutime;

    /**
     * 执行人
     */
    private String exceptionExecuuser;

    /**
     * 执行人编码
     */
    private String exceptionExecuusercode;

    /**
     * 请求参数
     */
    private String exceptionRequestparams;

    /**
     * 请求url
     */
    private String exceptionRequesturl;

    /**
     * 异常栈信息
     */
    private String exceptionStacktrace;

    /**
     * 异常参数
     */
    private String exceptionExceptionparam;

    /**
     * 异常编号
     */
    private String exceptionCode;

    /**
     * 异常消息
     */
    private String exceptionMessage;

    /**
     * 数据源
     */
    private String exceptionDsname;

    /**
     * tokenid
     */
    private String exceptionTokenid;

    /**
     * 执行人id
     */
    private String exceptionExecuuserid;

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
