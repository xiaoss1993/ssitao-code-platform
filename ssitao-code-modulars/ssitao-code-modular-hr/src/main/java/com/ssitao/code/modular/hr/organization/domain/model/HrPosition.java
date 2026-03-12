package com.ssitao.code.modular.hr.organization.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HrPosition {

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
    private String tenantId;
    private String creator;
    private LocalDateTime createTime;
    private String updater;
    private LocalDateTime updateTime;
    private Integer deleted;

    public static HrPosition create(String positionCode, String positionName, String tenantId) {
        HrPosition position = new HrPosition();
        position.id = java.util.UUID.randomUUID().toString().replace("-", "");
        position.positionCode = positionCode;
        position.positionName = positionName;
        position.tenantId = tenantId;
        position.status = "NORMAL";
        position.deleted = 0;
        position.positionSort = 0;
        position.createTime = LocalDateTime.now();
        return position;
    }

    public void enable() {
        this.status = "NORMAL";
    }

    public void disable() {
        this.status = "DISABLED";
    }
}
