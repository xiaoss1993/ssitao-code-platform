package com.ssitao.code.modular.hr.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HrPositionUpdateCommand {

    @NotNull(message = "岗位ID不能为空")
    private String id;

    private String positionCode;
    private String positionName;
    private String deptId;
    private String positionLevel;
    private String positionType;
    private String positionSummary;
    private String positionRequirement;
    private Integer positionSort;
    private String tenantId;
    private String updater;
}
