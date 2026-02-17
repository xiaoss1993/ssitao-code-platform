package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 员工教育背景 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_hrms_staff_education")
public class TbHrmsStaffEducation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 学历id
     */
    private String educationId;

    /**
     * 入学时间
     */
    private String startTime;

    /**
     * 毕业时间
     */
    private String endTime;

    /**
     * 毕业学校
     */
    private String graductionSchool;

    /**
     * 专业
     */
    private String major;

    /**
     * 学习形式
     */
    private String learningModalityId;

    /**
     * 学校性质
     */
    private String schoolNature;

    /**
     * 所属第三方业务数据id(员工id)
     */
    private String objectId;

    /**
     * 所属第三方业务数据的key(员工key)
     */
    private String objectKey;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 录入时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新人
     */
    private String lastUpdateId;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;

}
