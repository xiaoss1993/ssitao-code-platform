package com.ssitao.code.modular.iam.userprofile.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.service.IamUserProfileAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM用户档案管理控制器
 * 基于 tb_iam_user 表，管理用户的人事档案信息
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM用户档案管理", description = "IAM用户档案相关接口，基于 tb_iam_user 表")
@RestController
@RequestMapping("/iam/user-profile")
public class IamUserProfileController {

    private final IamUserProfileAppService userProfileAppService;

    public IamUserProfileController(IamUserProfileAppService userProfileAppService) {
        this.userProfileAppService = userProfileAppService;
    }

    // ==================== 用户档案 CRUD 接口 ====================

    @PostMapping
    @Operation(summary = "创建用户档案", description = "创建新用户档案（HR人员档案信息）")
    public CommonResult<String> createUserProfile(@Valid @RequestBody IamUserProfileCreateCommand command) {
        String userId = userProfileAppService.createUserProfile(command);
        return success(userId);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建用户档案", description = "批量创建用户档案")
    public CommonResult<List<String>> batchCreateUserProfiles(@Valid @RequestBody List<IamUserProfileCreateCommand> commands) {
        List<String> userIds = userProfileAppService.batchCreateUserProfiles(commands);
        return success(userIds);
    }

    @PutMapping
    @Operation(summary = "更新用户档案", description = "更新用户档案信息")
    public CommonResult<Void> updateUserProfile(@Valid @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateUserProfile(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户档案", description = "删除指定用户档案")
    public CommonResult<Void> deleteUserProfile(@PathVariable String id,
                                                  @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.deleteUserProfile(id, tenantId);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取用户档案详情", description = "根据ID获取用户档案详情")
    public CommonResult<IamUserProfileDTO> getUserProfileById(@PathVariable String id,
                                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamUserProfileDTO userProfile = userProfileAppService.getUserProfileById(id, tenantId);
        return success(userProfile);
    }

    @GetMapping("/code/{userCode}")
    @Operation(summary = "根据用户编码获取档案", description = "根据用户编码获取用户档案信息")
    public CommonResult<IamUserProfileDTO> getUserProfileByCode(@PathVariable String userCode,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamUserProfileDTO userProfile = userProfileAppService.getUserProfileByCode(userCode, tenantId);
        return success(userProfile);
    }

    @PostMapping("/list")
    @Operation(summary = "获取用户档案列表", description = "根据条件查询用户档案列表")
    public CommonResult<List<IamUserProfileDTO>> listUserProfiles(@RequestBody IamUserProfileQueryCommand command) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.listUserProfiles(command);
        return success(userProfiles);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询用户档案", description = "分页查询用户档案列表")
    public CommonResult<List<IamUserProfileDTO>> pageUserProfiles(@RequestBody IamUserProfileQueryCommand command,
                                                                   @RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.pageUserProfiles(command, page, size);
        return success(userProfiles);
    }

    // ==================== 用户档案状态管理接口 ====================

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用用户档案", description = "启用指定用户档案")
    public CommonResult<Void> enableUserProfile(@PathVariable String id,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.enableUserProfile(id, tenantId);
        return success();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用用户档案", description = "禁用指定用户档案")
    public CommonResult<Void> disableUserProfile(@PathVariable String id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.disableUserProfile(id, tenantId);
        return success();
    }

    // ==================== 用户档案组织关系接口 ====================

    @PutMapping("/{id}/assign-department")
    @Operation(summary = "分配部门", description = "为用户档案分配部门")
    public CommonResult<Void> assignDepartment(@PathVariable String id,
                                                @RequestParam String deptId,
                                                @RequestParam String deptName,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.assignDepartment(id, deptId, deptName, tenantId);
        return success();
    }

    @PutMapping("/{id}/assign-post")
    @Operation(summary = "分配岗位", description = "为用户档案分配岗位")
    public CommonResult<Void> assignPost(@PathVariable String id,
                                          @RequestParam String postCode,
                                          @RequestParam String postName,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.assignPost(id, postCode, postName, tenantId);
        return success();
    }

    @PutMapping("/{id}/assign-role")
    @Operation(summary = "分配角色", description = "为用户档案分配角色")
    public CommonResult<Void> assignRole(@PathVariable String id,
                                          @RequestParam String roleId,
                                          @RequestParam String roleName,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        userProfileAppService.assignRole(id, roleId, roleName, tenantId);
        return success();
    }

    // ==================== 用户档案信息更新接口 ====================

    @PutMapping("/{id}/info/basic")
    @Operation(summary = "更新基本信息", description = "更新用户档案基本信息")
    public CommonResult<Void> updateBasicInfo(@PathVariable String id,
                                               @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateBasicInfo(id, command);
        return success();
    }

    @PutMapping("/{id}/info/contact")
    @Operation(summary = "更新联系信息", description = "更新用户档案联系信息")
    public CommonResult<Void> updateContactInfo(@PathVariable String id,
                                                 @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateContactInfo(id, command);
        return success();
    }

    @PutMapping("/{id}/info/job")
    @Operation(summary = "更新工作信息", description = "更新用户档案工作信息")
    public CommonResult<Void> updateJobInfo(@PathVariable String id,
                                             @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateJobInfo(id, command);
        return success();
    }

    // ==================== 用户档案查询接口 ====================

    @GetMapping("/department/{deptId}")
    @Operation(summary = "根据部门查询用户档案", description = "获取指定部门的用户档案列表")
    public CommonResult<List<IamUserProfileDTO>> getUserProfilesByDepartment(@PathVariable String deptId,
                                                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.getUserProfilesByDepartment(deptId, tenantId);
        return success(userProfiles);
    }

    @GetMapping("/post/{postCode}")
    @Operation(summary = "根据岗位查询用户档案", description = "获取指定岗位的用户档案列表")
    public CommonResult<List<IamUserProfileDTO>> getUserProfilesByPost(@PathVariable String postCode,
                                                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.getUserProfilesByPost(postCode, tenantId);
        return success(userProfiles);
    }

    @GetMapping("/search")
    @Operation(summary = "搜索用户档案", description = "根据关键字搜索用户档案")
    public CommonResult<List<IamUserProfileDTO>> searchUserProfiles(@RequestParam String keyword,
                                                                     @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.searchUserProfiles(keyword, tenantId);
        return success(userProfiles);
    }

}
