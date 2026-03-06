package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaListDTO;
import com.ssitao.code.modular.meta.application.command.MetaListCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListUpdateCommand;

import java.util.List;

/**
 * 元数据列表配置应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaListAppService {

    // ==================== 列表操作 ====================

    /**
     * 创建列表配置
     */
    String create(MetaListCreateCommand command, String tenantId);

    /**
     * 更新列表配置
     */
    void update(MetaListUpdateCommand command, String tenantId);

    /**
     * 删除列表配置
     */
    void delete(String listId, String tenantId);

    /**
     * 根据ID获取列表配置
     */
    MetaListDTO getById(String listId, String tenantId);

    /**
     * 根据实体ID获取列表配置列表
     */
    List<MetaListDTO> listByEntityId(String entityId, String tenantId);

    /**
     * 检查列表编码是否存在
     */
    boolean checkExists(String entityId, String listCode, String tenantId, String excludeId);
}
