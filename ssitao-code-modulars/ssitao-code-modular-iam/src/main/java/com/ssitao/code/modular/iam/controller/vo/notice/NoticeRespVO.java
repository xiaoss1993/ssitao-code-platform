package com.ssitao.code.modular.iam.controller.vo.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知公告响应VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 通知公告响应")
public class NoticeRespVO {

    @Schema(description = "公告ID")
    private Long id;

    @Schema(description = "公告标题")
    private String title;

    @Schema(description = "公告内容")
    private String content;

    @Schema(description = "公告类型：1-通知 2-公告")
    private Integer type;

    @Schema(description = "状态：1-已发布 0-草稿")
    private Integer status;

    @Schema(description = "是否置顶：1-是 0-否")
    private Integer isTop;

    @Schema(description = "是否关闭：1-是 0-否")
    private Integer isClose;

    @Schema(description = "目标用户类型：1-全部用户 2-指定用户")
    private Integer targetUserType;

    @Schema(description = "目标用户ID列表（JSON格式）")
    private String targetUserIds;

    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

    @Schema(description = "发布人")
    private String publisher;

    @Schema(description = "租户ID")
    private String tenantId;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "更新人")
    private String updater;

}
