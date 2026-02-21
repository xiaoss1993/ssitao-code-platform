package com.ssitao.code.modular.iam.organization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM部门用户关联管理控制器
 * 管理 tb_iam_department_user 表，处理部门与用户的关联关系
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM部门用户关联", description = "IAM部门与用户关联管理接口")
@RestController
@RequestMapping("/iam/department-user")
public class IamDepartmentUserController {

    private final IamDepartmentUserService departmentUserService;

    public IamDepartmentUserController(IamDepartmentUserService departmentUserService) {
        this.departmentUserService = departmentUserService;
    }

    // ==================== 部门用户关联管理接口 ====================

    @PostMapping("/add-users")
    @Operation(summary = "添加用户到部门", description = "将一个或多个用户添加到指定部门")
    public CommonResult<Void> addUsersToDepartment(@RequestParam String departmentId,
                                                   @RequestBody List<String> userIds,
                                                   @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentUserService.addUsersToDepartment(departmentId, userIds, tenantId);
        return success();
    }

    @PostMapping("/remove-users")
    @Operation(summary = "从部门移除用户", description = "从指定部门移除一个或多个用户")
    public CommonResult<Void> removeUsersFromDepartment(@RequestParam String departmentId,
                                                        @RequestBody List<String> userIds,
                                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentUserService.removeUsersFromDepartment(departmentId, userIds, tenantId);
        return success();
    }

    @PostMapping("/transfer-users")
    @Operation(summary = "转移用户到其他部门", description = "将用户从一个部门转移到另一个部门")
    public CommonResult<Void> transferUsers(@RequestParam String fromDepartmentId,
                                            @RequestParam String toDepartmentId,
                                            @RequestBody List<String> userIds,
                                            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentUserService.transferUsers(fromDepartmentId, toDepartmentId, userIds, tenantId);
        return success();
    }

    @GetMapping("/department/{departmentId}/users")
    @Operation(summary = "获取部门用户列表", description = "获取指定部门下的所有用户")
    public CommonResult<List<IamUserProfileDTO>> getDepartmentUsers(@PathVariable String departmentId,
                                                                    @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamUserProfileDTO> users = departmentUserService.getDepartmentUsers(departmentId, tenantId);
        return success(users);
    }

    @GetMapping("/user/{userId}/departments")
    @Operation(summary = "获取用户的部门列表", description = "获取用户所属的所有部门")
    public CommonResult<List<IamDepartmentDTO>> getUserDepartments(@PathVariable String userId,
                                                                    @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamDepartmentDTO> departments = departmentUserService.getUserDepartments(userId, tenantId);
        return success(departments);
    }

    @GetMapping("/user/{userId}/main-department")
    @Operation(summary = "获取用户的主部门", description = "获取用户的主部门")
    public CommonResult<IamDepartmentDTO> getUserMainDepartment(@PathVariable String userId,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamDepartmentDTO department = departmentUserService.getUserMainDepartment(userId, tenantId);
        return success(department);
    }

    @PutMapping("/user/{userId}/set-main-department")
    @Operation(summary = "设置用户的主部门", description = "设置用户的主部门")
    public CommonResult<Void> setUserMainDepartment(@PathVariable String userId,
                                                    @RequestParam String departmentId,
                                                    @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentUserService.setUserMainDepartment(userId, departmentId, tenantId);
        return success();
    }

}
