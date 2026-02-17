package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 首页新闻公告 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_notice")
public class TbCoreNotice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreNoticeId;

    /**
     * 公告栏目_name
     */
    private String noticeTypeName;

    /**
     * 公告栏目
     */
    private String noticeTypeCode;

    /**
     * 内容
     */
    private String noticeContext;

    /**
     * 标题
     */
    private String noticeTitle;

    /**
     * 看过的人员
     */
    private String noticeLookuserids;

    /**
     * 启用
     */
    private String noticeQy;

    /**
     * 封面
     */
    private String noticeFm;

    /**
     * 点赞的人
     */
    private String noticeDzuserids;

    /**
     * 阅读量
     */
    private Integer noticeYdl;

    /**
     * 点赞量
     */
    private Integer noticeDzl;

    /**
     * 摘要
     */
    private String noticeZy;

    /**
     * 展示方式_name
     */
    private String noticeTpfsName;

    /**
     * 展示方式
     */
    private String noticeTpfsCode;

    /**
     * pdf文件
     */
    private String noticePdfwj;

    /**
     * 新闻类型
     */
    private String noticeXwlxCode;

    /**
     * 新闻类型_name
     */
    private String noticeXwlxName;

    /**
     * 附件
     */
    private String noticeFj;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 所属公司id
     */
    private String syCompanyId;

    /**
     * 所属公司名称
     */
    private String syCompanyName;

    /**
     * 所属集团公司id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司名称
     */
    private String syGroupCompanyName;

    /**
     * 所属机构id
     */
    private String syOrgId;

    /**
     * 租户id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
