package com.ssitao.code.modular.iam.organization.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 部门用户关联数据对象
 * 对应表：iam_dept_user
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_dept_user")
public class IamDeptUserDO {

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.None)
    private String id;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 是否主部门: 0-否, 1-是
     */
    private Integer isPrimary;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

}
