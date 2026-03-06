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
 * 元数据表单配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_form")
public class MetaFormDO {

    @Id(keyType = KeyType.None)
    private String formId;

    private String entityId;

    private String formCode;

    private String formName;

    private Integer formType;

    private Integer layout;

    private Integer width;

    private Integer labelPosition;

    private Integer labelWidth;

    private Integer showButtons;

    private Integer showResetButton;

    private Integer showCancelButton;

    private String submitButtonText;

    private String resetButtonText;

    private String cancelButtonText;

    private Integer submitAction;

    private String redirectPath;

    private String config;

    private String remark;

    private Integer status;

    private String tenantId;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
