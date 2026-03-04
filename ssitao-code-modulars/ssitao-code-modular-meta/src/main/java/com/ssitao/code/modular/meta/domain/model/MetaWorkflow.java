package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 流程定义
 * 工作流的元数据，定义流程的基本信息和流程图
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaWorkflow {

    /**
     * 流程定义ID
     */
    private String id;

    /**
     * 流程编码
     */
    private String workflowCode;

    /**
     * 流程名称
     */
    private String workflowName;

    /**
     * 关联的实体ID
     */
    private String entityId;

    /**
     * 分类
     */
    private String category;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 流程JSON（流程图结构）
     */
    private String flowJson;

    /**
     * 状态：0-禁用 1-启用
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

    /**
     * 创建流程定义
     *
     * @param workflowCode 流程编码
     * @param workflowName 流程名称
     * @param entityId     实体ID
     * @param category     分类
     * @param flowJson     流程JSON
     * @param tenantId     租户ID
     * @return 流程定义
     */
    public static MetaWorkflow create(String workflowCode, String workflowName, String entityId,
                                       String category, String flowJson, String tenantId) {
        MetaWorkflow workflow = new MetaWorkflow();
        workflow.setWorkflowCode(workflowCode);
        workflow.setWorkflowName(workflowName);
        workflow.setEntityId(entityId);
        workflow.setCategory(category);
        workflow.setFlowJson(flowJson);
        workflow.setVersion(1);
        workflow.setStatus(1);
        workflow.setTenantId(tenantId);
        workflow.setDeleted(false);
        workflow.setCreateTime(LocalDateTime.now());
        return workflow;
    }

    /**
     * 更新流程定义
     *
     * @param workflowName 流程名称
     * @param category     分类
     * @param flowJson     流程JSON
     */
    public void update(String workflowName, String category, String flowJson) {
        this.setWorkflowName(workflowName);
        this.setCategory(category);
        this.setFlowJson(flowJson);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 发布新版本
     *
     * @param flowJson 新的流程JSON
     */
    public void publishNewVersion(String flowJson) {
        this.setVersion(this.getVersion() + 1);
        this.setFlowJson(flowJson);
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 启用流程
     */
    public void enable() {
        this.setStatus(1);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 禁用流程
     */
    public void disable() {
        this.setStatus(0);
        this.setUpdateTime(LocalDateTime.now());
    }

    /**
     * 删除流程
     */
    public void delete() {
        this.setDeleted(true);
        this.setUpdateTime(LocalDateTime.now());
    }
}
