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
 * 元数据表单字段配置数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_form_field")
public class MetaFormFieldDO {

    @Id(keyType = KeyType.None)
    private String fieldId;

    private String formId;

    private String fieldCode;

    private String fieldName;

    private String fieldType;

    private Integer controlType;

    private Integer controlWidth;

    private String placeholder;

    private String defaultValue;

    private Integer required;

    private Integer editable;

    private Integer visible;

    private Integer copyable;

    private String rules;

    private String dictTypeCode;

    private String dictData;

    private String remoteUrl;

    private String labelField;

    private String valueField;

    private String groupName;

    private Integer sortOrder;

    private String config;

    private String tenantId;

    private LocalDateTime createTime;

    private String createUserId;

    private LocalDateTime modifyTime;

    private String modifyUserId;

    private Integer isDeleted;

    private Integer version;
}
