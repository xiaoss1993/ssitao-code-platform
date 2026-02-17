package com.ssitao.code.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.table.ColumnInfo;
import com.mybatisflex.core.table.TableInfo;
import com.mybatisflex.core.table.TableInfoFactory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础实体类
 * 所有实体类的基类，包含通用字段
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Schema(description = "基础实体")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核标记
     */
    @Schema(description = "审核标记")
    private String audFlag;

    /**
     * 创建人所在部门编码
     */
    @Schema(description = "创建人所在部门编码")
    private String createOrg;

    /**
     * 创建人所在部门名称
     */
    @Schema(description = "创建人所在部门名称")
    private String createOrgName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人编码
     */
    @Schema(description = "创建人编码")
    private String createUser;

    /**
     * 创建人姓名
     */
    @Schema(description = "创建人姓名")
    private String createUserName;

    /**
     * 启用标记
     */
    @Schema(description = "启用标记")
    private String flag;

    /**
     * 数据状态
     */
    @Schema(description = "数据状态")
    private String status;

    /**
     * 修改人所在部门编码
     */
    @Schema(description = "修改人所在部门编码")
    private String modifyOrg;

    /**
     * 修改人所在部门名称
     */
    @Schema(description = "修改人所在部门名称")
    private String modifyOrgName;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 修改人编码
     */
    @Schema(description = "修改人编码")
    private String modifyUser;

    /**
     * 修改人姓名
     */
    @Schema(description = "修改人姓名")
    private String modifyUserName;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer orderIndex;

    /**
     * 流程实例ID
     */
    @Schema(description = "流程实例ID")
    private String PIID;

    /**
     * 流程定义ID
     */
    @Schema(description = "流程定义ID")
    private String PDID;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 扩展参数
     */
    @Schema(description = "扩展参数")
    @com.mybatisflex.annotation.Column(ignore = true)
    private Map<String, Object> extParams;

    /**
     * 获取表信息
     */
    public TableInfo getTableInfo() {
        return TableInfoFactory.ofEntityClass(this.getClass());
    }

    /**
     * 获取主键值
     */
    public Object getPrimaryKeyValue() {
        TableInfo tableInfo = getTableInfo();
        if (tableInfo == null) {
            return null;
        }
        java.util.List<com.mybatisflex.core.table.IdInfo> primaryKeyList = tableInfo.getPrimaryKeyList();
        if (primaryKeyList == null || primaryKeyList.isEmpty()) {
            return null;
        }
        return getPropertyValue(primaryKeyList.get(0).getProperty());
    }

    /**
     * 获取属性值
     */
    public Object getPropertyValue(String property) {
        if (extParams == null) {
            extParams = new HashMap<>();
            // 添加所有字段到扩展参数
            extParams.put("audFlag", audFlag);
            extParams.put("createOrg", createOrg);
            extParams.put("createOrgName", createOrgName);
            extParams.put("createTime", createTime);
            extParams.put("createUser", createUser);
            extParams.put("createUserName", createUserName);
            extParams.put("flag", flag);
            extParams.put("status", status);
            extParams.put("modifyOrg", modifyOrg);
            extParams.put("modifyOrgName", modifyOrgName);
            extParams.put("modifyTime", modifyTime);
            extParams.put("modifyUser", modifyUser);
            extParams.put("modifyUserName", modifyUserName);
            extParams.put("orderIndex", orderIndex);
            extParams.put("PIID", PIID);
            extParams.put("PDID", PDID);
            extParams.put("tenantId", tenantId);
        }
        return extParams.get(property);
    }

    /**
     * 创建分页对象
     */
    public static <T> Page<T> page(Integer pageNumber, Integer pageSize) {
        return Page.of(pageNumber, pageSize);
    }

    /**
     * 创建分页对象（带排序）
     * 注意：MyBatis-Flex 1.8.6 的 Page.of() 不再支持 orderBy 参数
     * 排序需要在查询时通过 orderBy() 方法设置
     */
    public static <T> Page<T> page(Integer pageNumber, Integer pageSize, String orderBy) {
        // orderBy 参数暂不支持，需要在查询时设置
        return Page.of(pageNumber, pageSize);
    }
}
