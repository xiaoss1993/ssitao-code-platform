package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;

import java.util.List;

/**
 * 元数据字段应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaColumnAppService {

    String create(MetaColumnCreateCommand command, String tenantId);

    List<String> batchCreate(MetaColumnBatchCreateCommand command, String tenantId);

    void update(MetaColumnUpdateCommand command, String tenantId);

    void delete(String columnId, String tenantId);

    MetaColumnDTO getById(String columnId, String tenantId);

    List<MetaColumnDTO> listByTableId(String tableId, String tenantId);

    List<MetaColumnDTO> page(String tableId, String keyword, int page, int limit, String sort, String order, String tenantId);

    boolean checkExists(String tableId, String columnName, String tenantId, String excludeId);
}
