package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 页面配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_page_config")
public class TbMetaPageConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置ID
     */
    @Id
    private String tbCorePageConfigId;

    /**
     * 父级ID
     */
    private String syParent;

    /**
     * 层级
     */
    private String syLayer;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 排序索引
     */
    private String syOrderindex;

    /**
     * 父级路径
     */
    private String syParentpath;

    /**
     * 路径
     */
    private String syPath;

    /**
     * 状态
     */
    private String syStatus;

    /**
     * 对象类型
     */
    private String objectType;

    /**
     * 页面类型（FORM/LIST）
     */
    private String pageType;

    /**
     * 子类型（ADD/EDIT/VIEW/QUERY）
     */
    private String subType;

    /**
     * 页面标题
     */
    private String pageTitle;

    /**
     * 工作流ID
     */
    private String workflowId;

    /**
     * 功能主键
     */
    private String tbCoreFuncinfoId;

    /**
     * 页面字段参数
     */
    private String pageFieldParams;

    /**
     * 实体类型ID
     */
    private String entityTypeId;

    /**
     * 节点ID
     */
    private String nodeId;

    /**
     * 转换ID
     */
    private String transitionId;

    /**
     * 拥有者字段IDs
     */
    private String owerFieldIds;

    /**
     * 操作者字段IDs
     */
    private String actorFieldIds;

    /**
     * 阅读者字段IDs
     */
    private String readerFieldIds;

    /**
     * 是否全部只读（Y/N）
     */
    private String isAllOnlyRead;

    /**
     * 是否实体（true/false）
     */
    private String isEntity;

    /**
     * 映射字段
     */
    private String mapField;

    /**
     * 是否保存转换或节点页面（Y/N）
     */
    private String isSaveTranOrNodePage;

    /**
     * 配置名称
     */
    private String name;

    /**
     * 列数据（JSON格式）
     */
    private String colData;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
