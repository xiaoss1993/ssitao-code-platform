package com.ssitao.code.modular.hr.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class HrPositionCreateCommand {

    @NotBlank(message = "岗位编码不能为空")
    private String positionCode;

    @NotBlank(message = "岗位名称不能为空")
    private String positionName;

    private String deptId;
    private String positionLevel;
    private String positionType;
    private String positionSummary;
    private String positionRequirement;
    private Integer positionSort;
    private String tenantId;
    private String creator;
}
