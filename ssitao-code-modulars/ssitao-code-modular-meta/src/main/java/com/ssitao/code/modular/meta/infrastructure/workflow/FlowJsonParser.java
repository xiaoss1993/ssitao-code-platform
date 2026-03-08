package com.ssitao.code.modular.meta.infrastructure.workflow;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.modular.meta.domain.model.MetaWorkflowNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程JSON解析器
 * 解析流程定义的JSON结构，确定节点关系和下一个节点
 *
 * @author ssitao-code
 */
@Slf4j
@Component
public class FlowJsonParser {

    /**
     * 解析流程JSON，获取所有节点
     *
     * @param flowJson 流程JSON
     * @return 节点列表
     */
    public List<FlowNode> parseFlowJson(String flowJson) {
        if (StrUtil.isBlank(flowJson)) {
            return Collections.emptyList();
        }

        try {
            // 简单的JSON解析（实际项目中可以使用Jackson）
            List<FlowNode> nodes = new ArrayList<>();

            // 解析节点
            if (flowJson.contains("\"nodes\"")) {
                nodes.addAll(parseNodes(flowJson));
            }

            // 解析连线
            if (flowJson.contains("\"edges\"")) {
                List<FlowEdge> edges = parseEdges(flowJson);
                // 建立节点关系
                for (FlowNode node : nodes) {
                    List<String> nextNodeIds = edges.stream()
                            .filter(e -> e.getSourceId().equals(node.getId()))
                            .map(FlowEdge::getTargetId)
                            .collect(Collectors.toList());
                    node.setNextNodeIds(nextNodeIds);
                }
            }

            return nodes;
        } catch (Exception e) {
            log.error("解析流程JSON失败: {}", flowJson, e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取开始节点
     *
     * @param flowJson 流程JSON
     * @return 开始节点
     */
    public FlowNode getStartNode(String flowJson) {
        List<FlowNode> nodes = parseFlowJson(flowJson);
        return nodes.stream()
                .filter(n -> "start".equalsIgnoreCase(n.getType()) || "startEvent".equalsIgnoreCase(n.getType()))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取下一个节点
     *
     * @param flowJson    流程JSON
     * @param currentNodeId 当前节点ID
     * @param variables 流程变量（用于条件判断）
     * @return 下一个节点列表
     */
    public List<FlowNode> getNextNodes(String flowJson, String currentNodeId, Map<String, Object> variables) {
        List<FlowNode> allNodes = parseFlowJson(flowJson);
        if (CollUtil.isEmpty(allNodes)) {
            return Collections.emptyList();
        }

        // 找到当前节点
        FlowNode currentNode = allNodes.stream()
                .filter(n -> n.getId().equals(currentNodeId))
                .findFirst()
                .orElse(null);

        if (currentNode == null) {
            return Collections.emptyList();
        }

        // 获取下一个节点ID列表
        List<String> nextNodeIds = currentNode.getNextNodeIds();
        if (CollUtil.isEmpty(nextNodeIds)) {
            return Collections.emptyList();
        }

        // 过滤下一个节点（处理条件节点）
        List<FlowNode> nextNodes = allNodes.stream()
                .filter(n -> nextNodeIds.contains(n.getId()))
                .filter(n -> {
                    // 如果是条件节点，需要判断条件
                    if ("condition".equalsIgnoreCase(n.getType()) || "exclusiveGateway".equalsIgnoreCase(n.getType())) {
                        return evaluateCondition(n.getCondition(), variables);
                    }
                    return true;
                })
                .collect(Collectors.toList());

        return nextNodes;
    }

    /**
     * 判断是否为结束节点
     *
     * @param flowJson    流程JSON
     * @param nodeId     节点ID
     * @return 是否结束
     */
    public boolean isEndNode(String flowJson, String nodeId) {
        List<FlowNode> nodes = parseFlowJson(flowJson);
        return nodes.stream()
                .filter(n -> n.getId().equals(nodeId))
                .anyMatch(n -> "end".equalsIgnoreCase(n.getType()) || "endEvent".equalsIgnoreCase(n.getType()));
    }

    /**
     * 解析节点列表
     */
    private List<FlowNode> parseNodes(String flowJson) {
        List<FlowNode> nodes = new ArrayList<>();

        try {
            // 简单的解析方式，实际项目中应该使用JSON库
            int nodesStart = flowJson.indexOf("\"nodes\"");
            if (nodesStart == -1) {
                return nodes;
            }

            int arrayStart = flowJson.indexOf("[", nodesStart);
            int arrayEnd = flowJson.lastIndexOf("]");
            if (arrayStart == -1 || arrayEnd == -1) {
                return nodes;
            }

            String nodesJson = flowJson.substring(arrayStart, arrayEnd + 1);

            // 提取每个节点对象
            int depth = 0;
            int objectStart = -1;
            for (int i = 0; i < nodesJson.length(); i++) {
                char c = nodesJson.charAt(i);
                if (c == '{') {
                    if (depth == 0) {
                        objectStart = i;
                    }
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0 && objectStart != -1) {
                        String nodeJson = nodesJson.substring(objectStart, i + 1);
                        FlowNode node = parseNode(nodeJson);
                        if (node != null) {
                            nodes.add(node);
                        }
                        objectStart = -1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析节点列表失败", e);
        }

        return nodes;
    }

    /**
     * 解析单个节点
     */
    private FlowNode parseNode(String nodeJson) {
        try {
            FlowNode node = new FlowNode();

            // 解析ID
            node.setId(extractValue(nodeJson, "id"));

            // 解析类型
            node.setType(extractValue(nodeJson, "type"));

            // 解析名称
            node.setName(extractValue(nodeJson, "name"));

            // 解析审批人类型
            node.setAssigneeType(extractValue(nodeJson, "assigneeType"));

            // 解析审批人值
            node.setAssigneeValue(extractValue(nodeJson, "assigneeValue"));

            // 解析条件
            node.setCondition(extractValue(nodeJson, "condition"));

            return node;
        } catch (Exception e) {
            log.debug("解析节点失败: {}", nodeJson, e);
            return null;
        }
    }

    /**
     * 解析连线列表
     */
    private List<FlowEdge> parseEdges(String flowJson) {
        List<FlowEdge> edges = new ArrayList<>();

        try {
            int edgesStart = flowJson.indexOf("\"edges\"");
            if (edgesStart == -1) {
                return edges;
            }

            int arrayStart = flowJson.indexOf("[", edgesStart);
            int arrayEnd = flowJson.lastIndexOf("]");
            if (arrayStart == -1 || arrayEnd == -1) {
                return edges;
            }

            String edgesJson = flowJson.substring(arrayStart, arrayEnd + 1);

            // 提取每个连线对象
            int depth = 0;
            int objectStart = -1;
            for (int i = 0; i < edgesJson.length(); i++) {
                char c = edgesJson.charAt(i);
                if (c == '{') {
                    if (depth == 0) {
                        objectStart = i;
                    }
                    depth++;
                } else if (c == '}') {
                    depth--;
                    if (depth == 0 && objectStart != -1) {
                        String edgeJson = edgesJson.substring(objectStart, i + 1);
                        FlowEdge edge = parseEdge(edgeJson);
                        if (edge != null) {
                            edges.add(edge);
                        }
                        objectStart = -1;
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析连线列表失败", e);
        }

        return edges;
    }

    /**
     * 解析单个连线
     */
    private FlowEdge parseEdge(String edgeJson) {
        try {
            FlowEdge edge = new FlowEdge();
            edge.setSourceId(extractValue(edgeJson, "source"));
            edge.setTargetId(extractValue(edgeJson, "target"));
            edge.setCondition(extractValue(edgeJson, "condition"));
            return edge;
        } catch (Exception e) {
            log.debug("解析连线失败: {}", edgeJson, e);
            return null;
        }
    }

    /**
     * 从JSON中提取值
     */
    private String extractValue(String json, String key) {
        String pattern = "\"" + key + "\"";
        int keyIndex = json.indexOf(pattern);
        if (keyIndex == -1) {
            return null;
        }

        int colonIndex = json.indexOf(":", keyIndex);
        if (colonIndex == -1) {
            return null;
        }

        // 跳过冒号后的空格
        int valueStart = colonIndex + 1;
        while (valueStart < json.length() && json.charAt(valueStart) == ' ') {
            valueStart++;
        }

        if (valueStart >= json.length()) {
            return null;
        }

        char firstChar = json.charAt(valueStart);
        if (firstChar == '"') {
            // 字符串类型
            int valueEnd = json.indexOf("\"", valueStart + 1);
            if (valueEnd == -1) {
                return null;
            }
            return json.substring(valueStart + 1, valueEnd);
        } else if (firstChar == '{' || firstChar == '[') {
            // 对象或数组类型，返回null或整个内容
            return null;
        } else {
            // 数字、布尔类型
            int valueEnd = valueStart;
            while (valueEnd < json.length()) {
                char c = json.charAt(valueEnd);
                if (c == ',' || c == '}' || c == ']' || c == ' ') {
                    break;
                }
                valueEnd++;
            }
            return json.substring(valueStart, valueEnd).trim();
        }
    }

    /**
     * 评估条件表达式
     * 简化实现，实际项目中应该使用规则引擎
     */
    private boolean evaluateCondition(String condition, Map<String, Object> variables) {
        if (StrUtil.isBlank(condition) || CollUtil.isEmpty(variables)) {
            return true;
        }

        try {
            // 简单条件解析：支持 ==, !=, >, <, >=, <=
            for (Map.Entry<String, String> operator : getOperators().entrySet()) {
                int opIndex = condition.indexOf(operator.getKey());
                if (opIndex > 0) {
                    String varName = condition.substring(0, opIndex).trim();
                    String value = condition.substring(opIndex + operator.getKey().length()).trim();

                    Object varValue = variables.get(varName);
                    if (varValue == null) {
                        return false;
                    }

                    String varValueStr = varValue.toString();

                    switch (operator.getValue()) {
                        case "==":
                            return varValueStr.equals(value);
                        case "!=":
                            return !varValueStr.equals(value);
                        case ">":
                            return Double.parseDouble(varValueStr) > Double.parseDouble(value);
                        case "<":
                            return Double.parseDouble(varValueStr) < Double.parseDouble(value);
                        case ">=":
                            return Double.parseDouble(varValueStr) >= Double.parseDouble(value);
                        case "<=":
                            return Double.parseDouble(varValueStr) <= Double.parseDouble(value);
                        default:
                            return true;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.warn("评估条件失败: {}", condition, e);
            return true;
        }
    }

    private Map<String, String> getOperators() {
        Map<String, String> operators = new LinkedHashMap<>();
        operators.put(">=", ">=");
        operators.put("<=", "<=");
        operators.put("!=", "!=");
        operators.put("==", "==");
        operators.put(">", ">");
        operators.put("<", "<");
        return operators;
    }

    // ==================== 内部类 ====================

    /**
     * 流程节点
     */
    @Data
    public static class FlowNode {
        private String id;
        private String type;
        private String name;
        private String assigneeType;
        private String assigneeValue;
        private String condition;
        private List<String> nextNodeIds = new ArrayList<>();
    }

    /**
     * 流程连线
     */
    @Data
    public static class FlowEdge {
        private String sourceId;
        private String targetId;
        private String condition;
    }
}
