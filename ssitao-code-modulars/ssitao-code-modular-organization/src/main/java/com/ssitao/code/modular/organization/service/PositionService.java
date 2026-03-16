
package com.ssitao.code.modular.organization.service;

import com.ssitao.code.modular.organization.entity.PositionEntity;
import com.ssitao.code.modular.common.service.CrudService;
import com.ssitao.code.modular.common.service.TreeService;

/**
 * 职位 服务类
 *
 *
 */
public interface PositionService extends TreeService<PositionEntity, String>, CrudService<PositionEntity, String> {

}
