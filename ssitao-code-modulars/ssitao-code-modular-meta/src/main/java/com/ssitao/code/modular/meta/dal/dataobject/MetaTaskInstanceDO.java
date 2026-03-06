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
 * 元数据任务实例数据对象
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("meta_task_instance")
public class MetaTaskInstanceDO {

    @Id(keyType = KeyType.None)
    private String id;

    private String processId;

    private String taskId;

    private String taskName;

    private String taskType;

    private String assignee;

    private String status;

    private Integer priority;

    private String comment;

    private String tenantId;

    private LocalDateTime claimTime;

    private LocalDateTime completeTime;

    private String creator;

    private LocalDateTime createTime;

    private String updater;

    private LocalDateTime updateTime;

    private Integer deleted;
}
