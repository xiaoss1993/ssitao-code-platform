package com.ssitao.code.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 树形基础实体类
 * 树形结构的实体类基类
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "树形基础实体")
public class TreeBaseEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 所在的层数
     */
    @Schema(description = "所在的层数")
    private Integer layer;

    /**
     * 节点类型
     */
    @Schema(description = "节点类型")
    private String nodeType;

    /**
     * 父对象ID
     */
    @Schema(description = "父对象ID")
    private String parentId;

    /**
     * 功能信息（URL或相对路径）
     */
    @Schema(description = "功能信息")
    private String nodeInfo;

    /**
     * 功能信息类型
     */
    @Schema(description = "功能信息类型")
    private String nodeInfoType;

    /**
     * 路径
     */
    @Schema(description = "路径")
    private String path;

    /**
     * 是否有子节点
     */
    @Schema(description = "是否有子节点")
    private Boolean hasChildren;

    /**
     * 子节点列表
     */
    @Schema(description = "子节点列表")
    private java.util.List<TreeBaseEntity> children;
}
