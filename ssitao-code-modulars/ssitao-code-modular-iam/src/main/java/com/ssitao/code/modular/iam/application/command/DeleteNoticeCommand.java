package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 删除通知公告命令
 */
@Data
public class DeleteNoticeCommand {

    /** 公告ID数组 */
    @NotEmpty(message = "公告ID不能为空")
    private Long[] noticeIds;
}
