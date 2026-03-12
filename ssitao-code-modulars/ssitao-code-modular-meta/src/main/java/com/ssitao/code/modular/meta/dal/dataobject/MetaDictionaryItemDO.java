package com.ssitao.code.modular.meta.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典项数据对象
 * 对应表：core_dictionary_item
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("core_dictionary_item")
public class MetaDictionaryItemDO {

    /**
     * 字典项ID
     */
    @Id(keyType = KeyType.None)
    @Column("item_id")
    private String itemId;

    /**
     * 字典ID
     */
    @Column("dict_id")
    private String dictId;

    /**
     * 字典项编码
     */
    @Column("item_code")
    private String itemCode;

    /**
     * 字典项名称
     */
    @Column("item_name")
    private String itemName;

    /**
     * 字典项值
     */
    @Column("item_value")
    private String itemValue;

    /**
     * 父项ID
     */
    @Column("item_parent_id")
    private String itemParentId;

    /**
     * 项层级
     */
    @Column("item_level")
    private Integer itemLevel;

    /**
     * 项路径
     */
    @Column("item_path")
    private String itemPath;

    /**
     * 项图标
     */
    @Column("item_icon")
    private String itemIcon;

    /**
     * 项颜色
     */
    @Column("item_color")
    private String itemColor;

    /**
     * CSS样式
     */
    @Column("item_css_class")
    private String itemCssClass;

    /**
     * 项状态: 0-停用, 1-启用
     */
    @Column("item_status")
    private Integer itemStatus;

    /**
     * 是否默认: 0-否, 1-是
     */
    @Column("item_is_default")
    private Integer itemIsDefault;

    /**
     * 排序号
     */
    @Column("item_sort")
    private Integer itemSort;

    /**
     * 字典项描述
     */
    @Column("item_desc")
    private String itemDesc;

    /**
     * 租户ID
     */
    @Column("tenant_id")
    private String tenantId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column("create_user_id")
    private String createUserId;

    /**
     * 修改时间
     */
    @Column("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 修改人ID
     */
    @Column("modify_user_id")
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    @Column("is_deleted")
    private Integer isDeleted;
}
