package com.ssitao.code.modular.iam.controller.vo.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 岗位更新请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
public class PostUpdateReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     */
    @NotNull(message = "岗位ID不能为空")
    private Long id;

    /**
     * 岗位编码
     */
    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 50, message = "岗位编码长度不能超过50")
    private String code;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
    @Size(max = 50, message = "岗位名称长度不能超过50")
    private String name;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

    /**
     * 备注
     */
    @Size(max = 500, message = "备注长度不能超过500")
    private String remark;

}
