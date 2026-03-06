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
 * 元数据流程实例数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_process_instance")
public class MetaProcessInstanceDO {

    @Id(keyType = KeyType.None)
    private String id;

    private String workflowId;

    private String businessKey;

    private String title;

    private String initiator;

    private String currentNodeId;

    private String currentNodeName;

    private String status;

    private String tenantId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;
}
