package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaEntityUpdateCommand;

import java.util.List;

/**
 * 元数据实体配置应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaEntityAppService {

    String create(MetaEntityCreateCommand command, String tenantId);

    void update(MetaEntityUpdateCommand command, String tenantId);

    void delete(String id, String tenantId);

    MetaEntityDTO getById(String id, String tenantId);

    List<MetaEntityDTO> list(String tenantId);

    /**
     * 分页获取实体列表
     *
     * @param keyword 关键词
     * @param page 页码
     * @param limit 每页条数
     * @param sort 排序字段
     * @param order 排序方式
     * @param tenantId 租户ID
     * @return 实体列表
     */
    List<MetaEntityDTO> page(String keyword, int page, int limit, String sort, String order, String tenantId);

    boolean checkExists(String entityCode, String tenantId, String excludeId);
}
