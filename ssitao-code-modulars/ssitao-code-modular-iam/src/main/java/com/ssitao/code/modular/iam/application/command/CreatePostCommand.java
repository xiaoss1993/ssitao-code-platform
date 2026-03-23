package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 创建岗位命令
 */
@Data
public class CreatePostCommand {

    /** 岗位编码 */
    @NotBlank(message = "岗位编码不能为空")
    @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
    private String postCode;

    /** 岗位名称 */
    @NotBlank(message = "岗位名称不能为空")
    @Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
    private String postName;

    /** 岗位排序 */
    @NotBlank(message = "显示顺序不能为空")
    private String postSort;

    /** 状态（0正常 1停用） */
    private String status;

    /** 备注 */
    private String remark;
}
