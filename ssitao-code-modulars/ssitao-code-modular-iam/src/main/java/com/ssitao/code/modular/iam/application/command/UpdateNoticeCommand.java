package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 更新通知公告命令
 */
@Data
public class UpdateNoticeCommand {

    /** 公告ID */
    @NotNull(message = "公告ID不能为空")
    private Long noticeId;

    /** 公告标题 */
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    private String noticeTitle;

    /** 公告类型（1通知 2公告） */
    @NotBlank(message = "公告类型不能为空")
    private String noticeType;

    /** 公告内容 */
    private String noticeContent;

    /** 公告状态（0正常 1关闭） */
    private String status;

    /** 备注 */
    private String remark;
}
