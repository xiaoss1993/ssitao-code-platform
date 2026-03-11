package com.ssitao.code.modular.hr.organization.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 岗位数据对象
 * 对应表：hr_position
 *
 * @author ssitao
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("hr_position")
public class HrPositionDO {

    @Id
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
}
