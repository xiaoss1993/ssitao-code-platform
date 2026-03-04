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
 * 权限组数据对象
 * 对应表：iam_permission_group
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_permission_group")
public class IamPermGroupDO {

    /**
     * 权限组ID
     */
    @Id(keyType = KeyType.None)
    private String groupId;

    /**
     * 权限组编码
     */
    private String groupCode;

    /**
     * 权限组名称
     */
    private String groupName;

    /**
     * 权限组描述
     */
    private String groupDesc;

    /**
     * 权限组状态: 0-禁用, 1-启用
     */
    private Integer groupStatus;

    /**
     * 排序号
     */
    private Integer groupSort;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

}
