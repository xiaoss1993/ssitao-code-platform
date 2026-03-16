
package com.ssitao.code.modular.organization.controller;

import com.ssitao.code.modular.common.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import com.ssitao.code.modular.iam.authorization.annotation.Authorize;
import com.ssitao.code.modular.iam.authorization.annotation.RequiresDataAccess;
import com.ssitao.code.modular.common.entity.param.QueryParamEntity;
import com.ssitao.code.modular.organization.entity.PositionEntity;
import com.ssitao.code.modular.organization.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 职位
 *
 *
 */
@RestController
@RequestMapping("${ssitao.code.web.mappings.position:position}")
@Authorize(permission = "position",description = "职位管理",dataAccess = @RequiresDataAccess)
@Api(value = "职位管理",tags = "组织架构-职位管理")
public class PositionController implements SimpleGenericEntityController<PositionEntity, String, QueryParamEntity> {

    private PositionService positionService;

    @Autowired
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public PositionService getService() {
        return positionService;
    }

}
