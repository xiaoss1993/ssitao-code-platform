package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知公告数据对象
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@Table(value = "iam_notice")
public class NoticeDO {

    /**
     * 公告ID
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 公告类型：1-通知 2-公告
     */
    private Integer type;

    /**
     * 状态：1-已发布 0-草稿
     */
    private Integer status;

    /**
     * 是否置顶：1-是 0-否
     */
    private Integer isTop;

    /**
     * 是否关闭：1-是 0-否
     */
    private Integer isClose;

    /**
     * 目标用户类型：1-全部用户 2-指定用户
     */
    private Integer targetUserType;

    /**
     * 目标用户ID列表（JSON格式）
     */
    private String targetUserIds;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除：0-否 1-是
     */
    private Integer deleted;

}
