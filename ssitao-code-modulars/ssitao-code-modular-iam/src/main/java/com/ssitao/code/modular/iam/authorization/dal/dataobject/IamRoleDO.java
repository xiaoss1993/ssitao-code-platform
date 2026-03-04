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
 * 角色数据对象
 * 对应表：iam_role
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_role")
public class IamRoleDO {

    /**
     * 角色ID
     */
    @Id(keyType = KeyType.Auto)
    private String roleId;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色类型: SYSTEM-系统角色, BUSINESS-业务角色
     */
    private String roleType;

    /**
     * 角色级别
     */
    private Integer roleLevel;

    /**
     * 角色描述
     */
    private String roleDesc;

    /**
     * 角色状态: 0-禁用, 1-启用
     */
    private Integer roleStatus;

    /**
     * 是否内置: 0-否, 1-是
     */
    private Integer roleIsBuiltin;

    /**
     * 排序号
     */
    private Integer roleSort;

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
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;

}
