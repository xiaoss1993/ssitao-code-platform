package com.ssitao.code.modular.iam.application.dto;

import lombok.Data;

import java.util.Date;

/**
 * 字典类型DTO
 */
@Data
public class SysDictTypeDTO {

    /** 字典类型ID */
    private Long dictId;

    /** 字典名称 */
    private String dictName;

    /** 字典类型 */
    private String dictType;

    /** 状态（0正常 1停用） */
    private String status;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;
}
