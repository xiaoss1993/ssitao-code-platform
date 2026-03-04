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
 * 权限数据对象
 * 对应表：iam_permission
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_permission")
public class IamPermissionDO {

    /**
     * 权限ID
     */
    @Id(keyType = KeyType.Auto)
    private String permissionId;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限类型: MENU-菜单, BUTTON-按钮, API-接口, DATA-数据
     */
    private String permissionType;

    /**
     * 资源标识
     */
    private String permissionResource;

    /**
     * 操作类型
     */
    private String permissionAction;

    /**
     * 权限描述
     */
    private String permissionDesc;

    /**
     * 权限状态: 0-禁用, 1-启用
     */
    private Integer permissionStatus;

    /**
     * 是否内置: 0-否, 1-是
     */
    private Integer permissionIsBuiltin;

    /**
     * 排序号
     */
    private Integer permissionSort;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

}
