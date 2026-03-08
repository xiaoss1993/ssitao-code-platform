package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaTableDTO;
import com.ssitao.code.modular.meta.application.command.MetaTableCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaTableUpdateCommand;

import java.util.List;

/**
 * 元数据表配置应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaTableAppService {

    String create(MetaTableCreateCommand command, String tenantId);

    void update(MetaTableUpdateCommand command, String tenantId);

    void delete(String id, String tenantId);

    MetaTableDTO getById(String id, String tenantId);

    List<MetaTableDTO> list(String tenantId);

    boolean checkExists(String tableName, String tenantId, String excludeId);

    /**
     * 生成代码
     *
     * @param id      表配置ID
     * @param tenantId 租户ID
     */
    void generate(String id, String tenantId);
}
