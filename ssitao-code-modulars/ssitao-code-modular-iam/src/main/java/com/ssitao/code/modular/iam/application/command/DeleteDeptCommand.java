package com.ssitao.code.modular.iam.application.command;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除部门命令
 */
@Data
public class DeleteDeptCommand {

    /** 部门ID数组 */
    @NotEmpty(message = "部门ID不能为空")
    private Long[] deptIds;
}
