-- 流程定义表
CREATE TABLE IF NOT EXISTS meta_workflow (
    id VARCHAR(64) PRIMARY KEY COMMENT '流程定义ID',
    workflow_code VARCHAR(64) NOT NULL COMMENT '流程编码',
    workflow_name VARCHAR(128) NOT NULL COMMENT '流程名称',
    entity_id VARCHAR(64) COMMENT '关联的实体ID',
    category VARCHAR(64) COMMENT '分类',
    version INT DEFAULT 1 COMMENT '版本号',
    flow_json TEXT COMMENT '流程JSON（流程图结构）',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用 1-启用',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    UNIQUE KEY uk_tenant_code (tenant_id, workflow_code, deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程定义表';

-- 流程实例表
CREATE TABLE IF NOT EXISTS meta_process_instance (
    id VARCHAR(64) PRIMARY KEY COMMENT '流程实例ID',
    workflow_id VARCHAR(64) NOT NULL COMMENT '流程定义ID',
    business_key VARCHAR(128) COMMENT '业务键',
    title VARCHAR(256) NOT NULL COMMENT '流程标题',
    initiator VARCHAR(64) COMMENT '发起人',
    current_node_id VARCHAR(64) COMMENT '当前节点ID',
    current_node_name VARCHAR(128) COMMENT '当前节点名称',
    status VARCHAR(32) DEFAULT 'RUNNING' COMMENT '状态：RUNNING-运行中 COMPLETED-已完成 CANCELLED-已取消',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    KEY idx_workflow (workflow_id),
    KEY idx_business_key (business_key),
    KEY idx_initiator (initiator)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='流程实例表';

-- 任务实例表
CREATE TABLE IF NOT EXISTS meta_task_instance (
    id VARCHAR(64) PRIMARY KEY COMMENT '任务实例ID',
    process_id VARCHAR(64) NOT NULL COMMENT '流程实例ID',
    task_id VARCHAR(64) NOT NULL COMMENT '任务节点ID',
    task_name VARCHAR(128) NOT NULL COMMENT '任务节点名称',
    task_type VARCHAR(32) COMMENT '任务类型：APPROVAL-审批',
    assignee VARCHAR(64) COMMENT '处理人',
    status VARCHAR(32) DEFAULT 'PENDING' COMMENT '状态：PENDING-待签收 SIGNED-已签收 COMPLETED-已完成 REJECTED-已驳回 CANCELLED-已取消',
    priority INT DEFAULT 2 COMMENT '优先级',
    comment TEXT COMMENT '审批意见',
    tenant_id VARCHAR(64) DEFAULT 'default' COMMENT '租户ID',
    claim_time DATETIME COMMENT '签收时间',
    complete_time DATETIME COMMENT '完成时间',
    creator VARCHAR(64) COMMENT '创建人',
    create_time DATETIME COMMENT '创建时间',
    updater VARCHAR(64) COMMENT '更新人',
    update_time DATETIME COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '是否删除',
    KEY idx_process (process_id),
    KEY idx_assignee (assignee),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务实例表';
