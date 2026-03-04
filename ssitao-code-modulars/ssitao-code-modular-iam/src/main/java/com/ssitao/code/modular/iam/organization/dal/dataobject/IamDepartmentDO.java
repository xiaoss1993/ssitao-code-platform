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
 * 部门数据对象
 * 对应表：iam_department
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_department")
public class IamDepartmentDO {

    /**
     * 部门ID
     */
    @Id(keyType = KeyType.Auto)
    private String deptId;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门类型
     */
    private String deptType;

    /**
     * 父部门ID
     */
    private String deptParentId;

    /**
     * 所属公司ID
     */
    private String deptCompanyId;

    /**
     * 部门负责人ID
     */
    private String deptLeaderId;

    /**
     * 部门电话
     */
    private String deptPhone;

    /**
     * 部门地址
     */
    private String deptAddress;

    /**
     * 部门状态: 0-停用, 1-启用
     */
    private Integer deptStatus;

    /**
     * 部门描述
     */
    private String deptDesc;

    /**
     * 排序号
     */
    private Integer deptSort;

    /**
     * 树形路径
     */
    private String deptTreePath;

    /**
     * 树形层级
     */
    private Integer deptTreeLevel;

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
