package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表单配置聚合根
 * 用于定义实体的表单配置，包括表单布局、字段顺序、验证规则等
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaForm {

    /**
     * 表单配置ID
     */
    private String id;

    /**
     * 实体ID
     */
    private String entityId;

    /**
     * 表单编码
     */
    private String formCode;

    /**
     * 表单名称
     */
    private String formName;

    /**
     * 表单类型：1-默认表单 2-弹窗表单 3-抽屉表单 4-步骤表单
     */
    private Integer formType;

    /**
     * 表单布局：1-单列 2-双列 3-三列 4-四列
     */
    private Integer layout;

    /**
     * 表单宽度
     */
    private Integer width;

    /**
     * 表单标签位置：1-左侧 2-上方 3-右侧
     */
    private Integer labelPosition;

    /**
     * 表单标签宽度
     */
    private Integer labelWidth;

    /**
     * 是否显示操作按钮
     */
    private Boolean showButtons;

    /**
     * 是否显示重置按钮
     */
    private Boolean showResetButton;

    /**
     * 是否显示取消按钮
     */
    private Boolean showCancelButton;

    /**
     * 提交按钮文本
     */
    private String submitButtonText;

    /**
     * 重置按钮文本
     */
    private String resetButtonText;

    /**
     * 取消按钮文本
     */
    private String cancelButtonText;

    /**
     * 提交后行为：1-关闭弹窗 2-刷新列表 3-跳转页面
     */
    private Integer submitAction;

    /**
     * 跳转页面路径
     */
    private String redirectPath;

    /**
     * 扩展配置(JSON)
     */
    private String config;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

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
     * 是否删除
     */
    private Boolean deleted;

    // ==================== 非持久化字段 ====================

    /**
     * 表单字段配置列表
     */
    private List<MetaFormField> formFields;

    /**
     * 创建表单配置
     *
     * @param entityId   实体ID
     * @param formCode   表单编码
     * @param formName   表单名称
     * @param formType   表单类型
     * @return 表单配置
     */
    public static MetaForm create(String entityId, String formCode, String formName, Integer formType) {
        MetaForm form = new MetaForm();
        form.setEntityId(entityId);
        form.setFormCode(formCode);
        form.setFormName(formName);
        form.setFormType(formType);
        form.setLayout(1);
        form.setLabelPosition(1);
        form.setLabelWidth(100);
        form.setShowButtons(true);
        form.setShowResetButton(true);
        form.setShowCancelButton(true);
        form.setSubmitButtonText("提交");
        form.setResetButtonText("重置");
        form.setCancelButtonText("取消");
        form.setSubmitAction(1);
        form.setStatus(1);
        form.setDeleted(false);
        form.setCreateTime(LocalDateTime.now());
        return form;
    }

    /**
     * 启用表单
     */
    public void enable() {
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 禁用表单
     */
    public void disable() {
        this.setStatus(0);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除表单
     */
    public void delete() {
        this.setDeleted(true);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 表单字段配置
     * 用于定义表单中每个字段的配置
     */
    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetaFormField {

        /**
         * 字段ID
         */
        private String id;

        /**
         * 表单配置ID
         */
        private String formId;

        /**
         * 字段编码
         */
        private String fieldCode;

        /**
         * 字段名称
         */
        private String fieldName;

        /**
         * 字段类型
         */
        private String fieldType;

        /**
         * 表单控件类型：1-输入框 2-文本域 3-下拉框 4-单选框 5-复选框 6-开关 7-日期选择 8-时间选择 9-日期时间选择 10-文件上传 11-图片上传 12-富文本 13-评分 14-滑块 15-颜色选择 16-穿梭框 17-树选择
         */
        private Integer controlType;

        /**
         * 控件宽度
         */
        private Integer controlWidth;

        /**
         * 占位符
         */
        private String placeholder;

        /**
         * 默认值
         */
        private String defaultValue;

        /**
         * 是否必填
         */
        private Boolean required;

        /**
         * 是否可编辑
         */
        private Boolean editable;

        /**
         * 是否可见
         */
        private Boolean visible;

        /**
         * 是否可复制
         */
        private Boolean copyable;

        /**
         * 验证规则(JSON)
         * 如：{"required":true,"minLength":1,"maxLength":50,"pattern":"^\\w+$"}
         */
        private String rules;

        /**
         * 字典类型编码
         */
        private String dictTypeCode;

        /**
         * 字典数据(JSON)
         * 如：[{"label":"是","value":"1"},{"label":"否","value":"0"}]
         */
        private String dictData;

        /**
         * 远程数据URL
         */
        private String remoteUrl;

        /**
         * 显示字段
         */
        private String labelField;

        /**
         * 值字段
         */
        private String valueField;

        /**
         * 分组名称
         */
        private String groupName;

        /**
         * 排序
         */
        private Integer sortOrder;

        /**
         * 扩展配置(JSON)
         */
        private String config;

    }

}
