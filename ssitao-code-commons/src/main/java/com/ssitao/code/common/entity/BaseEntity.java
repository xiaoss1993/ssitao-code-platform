package com.ssitao.code.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础实体类
 * 所有实体类的基类，包含通用字段
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Schema(description = "基础实体")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核标记
     */
    @Schema(description = "审核标记")
    private String audFlag;

    /**
     * 创建人所在部门编码
     */
    @Schema(description = "创建人所在部门编码")
    private String createOrg;

    /**
     * 创建人所在部门名称
     */
    @Schema(description = "创建人所在部门名称")
    private String createOrgName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人编码
     */
    @Schema(description = "创建人编码")
    private String createUser;

    /**
     * 创建人姓名
     */
    @Schema(description = "创建人姓名")
    private String createUserName;

    /**
     * 启用标记
     */
    @Schema(description = "启用标记")
    private String flag;

    /**
     * 数据状态
     */
    @Schema(description = "数据状态")
    private String status;

    /**
     * 修改人所在部门编码
     */
    @Schema(description = "修改人所在部门编码")
    private String modifyOrg;

    /**
     * 修改人所在部门名称
     */
    @Schema(description = "修改人所在部门名称")
    private String modifyOrgName;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 修改人编码
     */
    @Schema(description = "修改人编码")
    private String modifyUser;

    /**
     * 修改人姓名
     */
    @Schema(description = "修改人姓名")
    private String modifyUserName;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer orderIndex;

    /**
     * 流程实例ID
     */
    @Schema(description = "流程实例ID")
    private String PIID;

    /**
     * 流程定义ID
     */
    @Schema(description = "流程定义ID")
    private String PDID;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private String tenantId;

}
