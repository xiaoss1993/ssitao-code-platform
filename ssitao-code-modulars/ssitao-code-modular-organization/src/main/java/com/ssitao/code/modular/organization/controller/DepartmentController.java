
package com.ssitao.code.modular.organization.controller;

import com.ssitao.code.modular.common.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import com.ssitao.code.modular.iam.authorization.annotation.Authorize;
import com.ssitao.code.modular.iam.authorization.annotation.RequiresDataAccess;
import com.ssitao.code.modular.common.entity.param.QueryParamEntity;
import com.ssitao.code.modular.organization.entity.DepartmentEntity;
import com.ssitao.code.modular.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门
 *
 *
 */
@RestController
@RequestMapping("${ssitao.code.web.mappings.department:department}")
@Authorize(permission = "department", description = "部门管理", dataAccess = @RequiresDataAccess)
@Api(value = "部门管理",tags = "组织架构-部门管理")
public class DepartmentController implements SimpleGenericEntityController<DepartmentEntity, String, QueryParamEntity> {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public DepartmentService getService() {
        return departmentService;
    }

}
