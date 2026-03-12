package com.ssitao.code.modular.meta.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 元数据列表按钮配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_list_button")
public class MetaListButtonDO {

    @Id(keyType = KeyType.None)
    private String buttonId;

    private String listId;

    private String buttonCode;

    private String buttonName;

    private Integer buttonType;

    private Integer buttonStyle;

    private String icon;

    private Integer sortOrder;

    private Integer visible;

    private Integer disabled;

    private String clickEvent;

    private String permissionCode;

    private String config;

    private String tenantId;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
