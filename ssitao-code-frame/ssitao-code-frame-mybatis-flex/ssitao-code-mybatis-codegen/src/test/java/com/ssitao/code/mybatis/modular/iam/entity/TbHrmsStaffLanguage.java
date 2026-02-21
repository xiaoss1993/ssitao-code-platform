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
 * 员工语言能力 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_hrms_staff_language")
public class TbHrmsStaffLanguage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 语种等级id
     */
    private String levelId;

    /**
     * 获取时间
     */
    private String getTime;

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
