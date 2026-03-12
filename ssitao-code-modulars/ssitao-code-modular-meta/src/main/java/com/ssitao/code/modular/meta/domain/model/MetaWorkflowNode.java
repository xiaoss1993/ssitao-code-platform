package com.ssitao.code.modular.meta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 流程节点
 * 定义工作流中的各个节点信息，包括审批节点、路由节点等
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MetaWorkflowNode {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 流程定义ID
     */
    private String workflowId;

    /**
     * 节点标识
     */
    private String nodeId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 节点类型：
     * START - 开始节点
     * END - 结束节点
     * APPROVAL - 审批节点
     * CONDITION - 条件节点
     * PARALLEL - 并行网关
     * EXCLUSIVE - 排他网关
     */
    private String nodeType;

    /**
     * 审批人类型：
     * USER - 指定用户
     * ROLE - 指定角色
     * DEPARTMENT - 部门负责人
     * INITIATOR - 发起人
     * EXPRESSION - 表达式
     */
    private String assigneeType;

    /**
     * 审批人值（用户ID、角色ID、表达式等）
     */
    private String assigneeValue;

    /**
     * 操作类型：
     * APPROVE - 只审批
     * APPROVE_REJECT - 审批并可驳回
     * SIGN - 会签
     * OR_SIGN - 或签
     */
    private String optionType;

    /**
     * 条件表达式（用于条件节点）
     */
    private String condition;

    /**
     * 节点配置JSON
     */
    private String configJson;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建审批节点
     *
     * @param workflowId     流程定义ID
     * @param nodeId        节点标识
     * @param nodeName      节点名称
     * @param assigneeType  审批人类型
     * @param assigneeValue 审批人值
     * @return 流程节点
     */
    public static MetaWorkflowNode createApprovalNode(String workflowId, String nodeId, String nodeName,
                                                      String assigneeType, String assigneeValue) {
        MetaWorkflowNode node = new MetaWorkflowNode();
        node.setWorkflowId(workflowId);
        node.setNodeId(nodeId);
        node.setNodeName(nodeName);
        node.setNodeType("APPROVAL");
        node.setAssigneeType(assigneeType);
        node.setAssigneeValue(assigneeValue);
        node.setOptionType("APPROVE_REJECT");
        node.setCreateTime(LocalDateTime.now());
        return node;
    }

    /**
     * 创建条件节点
     *
     * @param workflowId 流程定义ID
     * @param nodeId     节点标识
     * @param nodeName   节点名称
     * @param condition  条件表达式
     * @return 流程节点
     */
    public static MetaWorkflowNode createConditionNode(String workflowId, String nodeId, String nodeName, String condition) {
        MetaWorkflowNode node = new MetaWorkflowNode();
        node.setWorkflowId(workflowId);
        node.setNodeId(nodeId);
        node.setNodeName(nodeName);
        node.setNodeType("CONDITION");
        node.setCondition(condition);
        node.setCreateTime(LocalDateTime.now());
        return node;
    }

    /**
     * 判断是否为开始节点
     *
     * @return 是否开始节点
     */
    public boolean isStartNode() {
        return "START".equals(nodeType);
    }

    /**
     * 判断是否为结束节点
     *
     * @return 是否结束节点
     */
    public boolean isEndNode() {
        return "END".equals(nodeType);
    }

    /**
     * 判断是否为审批节点
     *
     * @return 是否审批节点
     */
    public boolean isApprovalNode() {
        return "APPROVAL".equals(nodeType);
    }
}
