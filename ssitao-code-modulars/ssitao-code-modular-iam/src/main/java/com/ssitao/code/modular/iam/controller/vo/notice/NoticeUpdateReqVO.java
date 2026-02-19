package com.ssitao.code.modular.iam.controller.vo.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 通知公告更新请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 通知公告更新请求")
public class NoticeUpdateReqVO {

    @Schema(description = "公告ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公告ID不能为空")
    private Long id;

    @Schema(description = "公告标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "公告标题不能为空")
    private String title;

    @Schema(description = "公告内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "公告内容不能为空")
    private String content;

    @Schema(description = "公告类型：1-通知 2-公告", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "公告类型不能为空")
    private Integer type;

    @Schema(description = "状态：1-已发布 0-草稿", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer status;

    @Schema(description = "是否置顶：1-是 0-否")
    private Integer isTop;

    @Schema(description = "是否关闭：1-是 0-否")
    private Integer isClose;

    @Schema(description = "目标用户类型：1-全部用户 2-指定用户")
    private Integer targetUserType;

    @Schema(description = "目标用户ID列表（JSON格式）")
    private String targetUserIds;

}
