package com.ssitao.code.modular.hr.organization.api.dto;

import lombok.Data;

@Data
public class HrPositionDTO {
    private String id;
    private String positionCode;
    private String positionName;
    private String deptId;
    private String positionLevel;
    private String positionType;
    private String positionSummary;
    private String positionRequirement;
    private Integer positionSort;
    private String status;

    // 扩展字段
    private String deptName;
    private String label;
    private String value;
}
