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
 * 员工档案信息 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_hrms_staff_archives")
public class TbHrmsStaffArchives extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 档案编号
     */
    private String archivesNumber;

    /**
     * 管理单位
     */
    private String companyId;

    /**
     * 档案保管地
     */
    private String custodyPlace;

    /**
     * 档案室
     */
    private String archivesCenter;

    /**
     * 入档时间
     */
    private String archivesTime;

    /**
     * 档案学历
     */
    private String educationId;

    /**
     * 是否在档
     */
    private Integer whetherArchives;

    /**
     * 档案状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

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
