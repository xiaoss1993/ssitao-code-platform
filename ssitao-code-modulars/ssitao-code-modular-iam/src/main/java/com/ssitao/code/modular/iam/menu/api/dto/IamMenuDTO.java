package com.ssitao.code.modular.iam.menu.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * IAM菜单DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "IAM菜单DTO")
public class IamMenuDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单ID")
    private String id;

    @JsonProperty("parent_id")
    @Schema(description = "父菜单ID")
    private String parentId;

    @JsonProperty("menu_name")
    @Schema(description = "菜单名称")
    private String menuName;

    @JsonProperty("menu_type")
    @Schema(description = "菜单类型：directory-目录 menu-菜单 button-按钮")
    private String menuType;

    @Schema(description = "路由路径")
    private String path;

    @Schema(description = "组件路径")
    private String component;

    @JsonProperty("perms")
    @Schema(description = "权限标识")
    private String permission;

    @Schema(description = "图标")
    private String icon;

    @JsonProperty("sort_order")
    @Schema(description = "排序")
    private Integer sortOrder;

    @JsonProperty("visible")
    @Schema(description = "是否可见：0-隐藏 1-显示")
    private Integer visible;

    @Schema(description = "状态：0-禁用 1-启用")
    private Integer status;

    @JsonProperty("create_time")
    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "子菜单列表")
    private List<IamMenuDTO> children;

    // ==================== 前端兼容字段 ====================

    @Schema(description = "路由名称（前端兼容）")
    private String name;

    @Schema(description = "重定向地址")
    private String redirect;

    @Schema(description = "菜单高亮地址")
    private String active;

    @Schema(description = "颜色")
    private String color;

    @Schema(description = "是否全屏")
    private Boolean fullpage;

    @Schema(description = "标签")
    private String tag;

    @JsonProperty("hidden_breadcrumb")
    @Schema(description = "隐藏面包屑")
    private Boolean hiddenBreadcrumb;

    @JsonProperty("api_list")
    @Schema(description = "API权限列表")
    private List<ApiPermission> apiList;

    @Schema(description = "路由元信息（前端兼容）")
    private MetaInfo meta;

    /**
     * 路由元信息（前端兼容格式）
     */
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "路由元信息")
    public static class MetaInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "菜单标题")
        private String title;

        @Schema(description = "图标")
        private String icon;

        @Schema(description = "是否隐藏")
        private Boolean hidden;

        @Schema(description = "类型")
        private String type;

        @Schema(description = "权限")
        private List<String> auth;

        @Schema(description = "颜色")
        private String color;

        @Schema(description = "是否全屏")
        private Boolean fullpage;

        @Schema(description = "标签")
        private String tag;

        @JsonProperty("hidden_breadcrumb")
        @Schema(description = "隐藏面包屑")
        private Boolean hiddenBreadcrumb;

        @Schema(description = "激活菜单")
        private String active;
    }

    /**
     * API权限
     */
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "API权限")
    public static class ApiPermission implements Serializable {
        private static final long serialVersionUID = 1L;

        @Schema(description = "标识")
        private String code;

        @Schema(description = "API URL")
        private String url;
    }
}
