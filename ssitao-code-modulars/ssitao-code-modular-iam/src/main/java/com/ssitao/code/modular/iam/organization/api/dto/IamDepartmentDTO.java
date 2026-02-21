package com.ssitao.code.modular.iam.organization.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IAM部门DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamDepartmentDTO {

    /**
     * 部门ID
     */
    private String id;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 父部门名称
     */
    private String parentName;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 路径
     */
    private String path;

    /**
     * 负责人ID
     */
    private String leaderId;

    /**
     * 负责人姓名
     */
    private String leaderName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人编码
     */
    private String createUser;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人编码
     */
    private String modifyUser;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 子部门列表
     */
    private List<IamDepartmentDTO> children;

}
