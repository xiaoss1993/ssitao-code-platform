package com.ssitao.code.modular.hr.organization.api.dto;

import lombok.Data;

import java.util.List;

/**
 * 部门DTO
 *
 * @author ssitao
 */
@Data
public class HrDepartmentDTO {

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
     * 部门层级
     */
    private Integer deptLevel;

    /**
     * 部门路径
     */
    private String deptPath;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 部门负责人
     */
    private String deptLeader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态: NORMAL-正常, DISABLED-停用
     */
    private String status;

    /**
     * 树形显示用
     */
    private String label;
    private String value;
    private List<HrDepartmentDTO> children;
}
