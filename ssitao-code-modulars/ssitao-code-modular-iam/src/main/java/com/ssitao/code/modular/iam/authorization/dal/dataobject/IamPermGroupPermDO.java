package com.ssitao.code.modular.iam.authorization.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 权限组权限关联数据对象
 * 对应表：iam_permission_group_perm
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_permission_group_perm")
public class IamPermGroupPermDO {

    /**
     * 主键ID
     */
    @Id(keyType = KeyType.None)
    private String id;

    /**
     * 权限组ID
     */
    private String groupId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 是否有效: 0-否, 1-是
     */
    private Integer isValid;

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
