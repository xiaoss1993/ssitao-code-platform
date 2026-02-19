package com.ssitao.code.modular.iam.controller.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 更新角色请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "更新角色请求")
public class RoleUpdateReqVO {

    /**
     * 角色ID
     */
    @Schema(description = "角色ID", required = true)
    @NotNull(message = "角色ID不能为空")
    private Long id;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    @Size(max = 50, message = "角色编码长度不能超过50")
    private String code;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    @Size(max = 50, message = "角色名称长度不能超过50")
    private String name;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态：1-启用 0-禁用")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

}
