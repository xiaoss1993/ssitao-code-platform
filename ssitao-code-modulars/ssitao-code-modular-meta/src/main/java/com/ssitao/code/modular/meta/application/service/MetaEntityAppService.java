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

    boolean checkExists(String entityCode, String tenantId, String excludeId);
}
