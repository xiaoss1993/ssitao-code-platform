package com.ssitao.code.modular.iam.controller.vo.notice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 通知公告列表查询请求VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Schema(description = "管理后台 - 通知公告列表查询请求")
public class NoticeListReqVO {

    @Schema(description = "公告标题")
    private String title;

    @Schema(description = "公告类型：1-通知 2-公告")
    private Integer type;

    @Schema(description = "状态：1-已发布 0-草稿")
    private Integer status;

}
