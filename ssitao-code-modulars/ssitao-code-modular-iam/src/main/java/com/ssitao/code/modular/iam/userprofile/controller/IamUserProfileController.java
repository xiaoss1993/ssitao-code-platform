package com.ssitao.code.modular.iam.userprofile.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.service.IamUserProfileAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/iam/user")
public class IamUserProfileController {

    private final IamUserProfileAppService userProfileAppService;
    private final ObjectMapper objectMapper;

    public IamUserProfileController(IamUserProfileAppService userProfileAppService, ObjectMapper objectMapper) {
        this.userProfileAppService = userProfileAppService;
        this.objectMapper = objectMapper;
    }

    // ==================== 页面跳转 ====================

    /**
     * 用户档案管理页面
     */
    @GetMapping
    @Operation(summary = "用户档案管理页面")
    public String userprofilePage(Model model) {
        addCommonModel(model, "用户档案管理", "userprofile");
        return "iam/userprofile";
    }

    /**
     * 用户档案添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "用户档案添加页面")
    public String userprofileAddPage(Model model) {
        addCommonModel(model, "添加用户档案", "userprofile");
        return "iam/userprofile-edit";
    }

    /**
     * 用户档案编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "用户档案编辑页面")
    public String userprofileEditPage(Model model,
                                       @RequestParam(required = false) String id,
                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        addCommonModel(model, "编辑用户档案", "userprofile");

        // 如果有 ID，获取数据并转换为 JSON
        if (id != null && !id.isEmpty()) {
            try {
                IamUserProfileDTO userProfile = userProfileAppService.getUserProfileById(id, tenantId);
                if (userProfile != null) {
                    String jsonData = objectMapper.writeValueAsString(userProfile);
                    model.addAttribute("rowData", jsonData);
                }
            } catch (JsonProcessingException e) {
                // 忽略转换错误
            }
        }

        return "iam/userprofile-edit";
    }

    // ==================== 用户档案 CRUD 接口 ====================

    /**
     * JSON格式创建用户（支持前端AJAX JSON提交）
     */
    @PostMapping(consumes = {"application/json"})
    @Operation(summary = "创建用户档案(JSON)", description = "创建新用户档案（JSON格式）")
    @ResponseBody
    public CommonResult<String> createUserProfileJson(@Valid @RequestBody IamUserProfileCreateCommand command) {
        String userId = userProfileAppService.createUserProfile(command);
        return success(userId);
    }

    /**
     * 表单格式创建用户（支持FastAdmin表单提交）
     */
    @PostMapping(consumes = {"application/x-www-form-urlencoded"})
    @Operation(summary = "创建用户档案(表单)", description = "创建新用户档案（表单格式）")
    @ResponseBody
    public CommonResult<String> createUserProfileForm(
            @RequestParam String userCode,
            @RequestParam String userName,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String userSexCode,
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) String userMail,
            @RequestParam(required = false) String userAvatar,
            @RequestParam(required = false) String deptId,
            @RequestParam(required = false) String deptName,
            @RequestParam(required = false) String userPostCode,
            @RequestParam(required = false) String userPostName,
            @RequestParam(required = false) String userRoleId,
            @RequestParam(required = false) String userRoleName,
            @RequestParam(required = false) String userEntryDate,
            @RequestParam(required = false) String userCardnum,
            @RequestParam(required = false) String userEmployeeStatus,
            @RequestParam(required = false) String userAddress,
            @RequestParam(required = false) String userRemark,
            @RequestParam(defaultValue = "1") String syTenantId) {
        
        IamUserProfileCreateCommand command = new IamUserProfileCreateCommand();
        command.setUserCode(userCode);
        command.setUserName(userName);
        command.setNickname(nickname);
        command.setRealName(realName);
        command.setUserSexCode(userSexCode);
        command.setUserPhone(userPhone);
        command.setUserMail(userMail);
        command.setUserAvatar(userAvatar);
        command.setDeptId(deptId);
        command.setDeptName(deptName);
        command.setUserPostCode(userPostCode);
        command.setUserPostName(userPostName);
        command.setUserRoleId(userRoleId);
        command.setUserRoleName(userRoleName);
        command.setUserEntryDate(userEntryDate);
        command.setUserCardnum(userCardnum);
        command.setUserEmployeeStatus(userEmployeeStatus);
        command.setUserAddress(userAddress);
        command.setUserRemark(userRemark);
        command.setSyTenantId(syTenantId);
        
        String userId = userProfileAppService.createUserProfile(command);
        return success(userId);
    }

    @PostMapping("/batch")
    @Operation(summary = "批量创建用户档案", description = "批量创建用户档案")
    @ResponseBody
    public CommonResult<List<String>> batchCreateUserProfiles(@Valid @RequestBody List<IamUserProfileCreateCommand> commands) {
        List<String> userIds = userProfileAppService.batchCreateUserProfiles(commands);
        return success(userIds);
    }

    @PutMapping(consumes = {"application/json"})
    @Operation(summary = "更新用户档案(JSON)", description = "更新用户档案信息（JSON格式）")
    @ResponseBody
    public CommonResult<Void> updateUserProfileJson(@Valid @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateUserProfile(command);
        return success();
    }

    @PutMapping(consumes = {"application/x-www-form-urlencoded"})
    @Operation(summary = "更新用户档案(表单)", description = "更新用户档案信息（表单格式）")
    @ResponseBody
    public CommonResult<Void> updateUserProfileForm(
            @RequestParam String id,
            @RequestParam(required = false) String userCode,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String userSexCode,
            @RequestParam(required = false) String userPhone,
            @RequestParam(required = false) String userMail,
            @RequestParam(required = false) String userAvatar,
            @RequestParam(required = false) String deptId,
            @RequestParam(required = false) String deptName,
            @RequestParam(required = false) String userPostCode,
            @RequestParam(required = false) String userPostName,
            @RequestParam(required = false) String userRoleId,
            @RequestParam(required = false) String userRoleName,
            @RequestParam(required = false) String userEntryDate,
            @RequestParam(required = false) String userCardnum,
            @RequestParam(required = false) String userEmployeeStatus,
            @RequestParam(required = false) String userAddress,
            @RequestParam(required = false) String userRemark) {
        
        IamUserProfileUpdateCommand command = new IamUserProfileUpdateCommand();
        command.setUserId(id);
        command.setUserCode(userCode);
        command.setUserName(userName);
        command.setNickname(nickname);
        command.setRealName(realName);
        command.setUserSexCode(userSexCode);
        command.setUserPhone(userPhone);
        command.setUserMail(userMail);
        command.setUserAvatar(userAvatar);
        command.setDeptId(deptId);
        command.setDeptName(deptName);
        command.setUserPostCode(userPostCode);
        command.setUserPostName(userPostName);
        command.setUserRoleId(userRoleId);
        command.setUserRoleName(userRoleName);
        command.setUserEntryDate(userEntryDate);
        command.setUserCardnum(userCardnum);
        command.setUserEmployeeStatus(userEmployeeStatus);
        command.setUserAddress(userAddress);
        command.setUserRemark(userRemark);
        
        userProfileAppService.updateUserProfile(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户档案", description = "删除指定用户档案")
    @ResponseBody
    public CommonResult<Void> deleteUserProfile(@PathVariable String id,
                                                  @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.deleteUserProfile(id, tenantId);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取用户档案详情", description = "根据ID获取用户档案详情")
    @ResponseBody
    public CommonResult<IamUserProfileDTO> getUserProfileById(@PathVariable String id,
                                                               @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamUserProfileDTO userProfile = userProfileAppService.getUserProfileById(id, tenantId);
        return success(userProfile);
    }

    @GetMapping("/code/{userCode}")
    @Operation(summary = "根据用户编码获取档案", description = "根据用户编码获取用户档案信息")
    @ResponseBody
    public CommonResult<IamUserProfileDTO> getUserProfileByCode(@PathVariable String userCode,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamUserProfileDTO userProfile = userProfileAppService.getUserProfileByCode(userCode, tenantId);
        return success(userProfile);
    }

    @GetMapping("/list")
    @Operation(summary = "获取用户档案列表", description = "根据条件查询用户档案列表")
    @ResponseBody
    public CommonResult<PageResult<IamUserProfileDTO>> listUserProfiles(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamUserProfileQueryCommand command = new IamUserProfileQueryCommand();
        command.setKeyword(keyword);
        command.setSyTenantId(tenantId);

        List<IamUserProfileDTO> userProfiles = userProfileAppService.listUserProfiles(command);

        PageResult<IamUserProfileDTO> result = new PageResult<>();
        result.setRows(userProfiles);
        result.setTotal(userProfiles.size());
        return success(result);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询用户档案", description = "分页查询用户档案列表")
    @ResponseBody
    public CommonResult<List<IamUserProfileDTO>> pageUserProfiles(@RequestBody IamUserProfileQueryCommand command,
                                                                   @RequestParam(defaultValue = "1") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.pageUserProfiles(command, page, size);
        return success(userProfiles);
    }

    /**
     * GET分页查询用户档案（支持前端Bootstrap Table）
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户档案", description = "GET分页查询用户档案列表，支持前端Bootstrap Table")
    @ResponseBody
    public CommonResult<PageResult<IamUserProfileDTO>> pageUserProfilesGet(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) String realName,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String deptId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String offset,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        IamUserProfileQueryCommand command = new IamUserProfileQueryCommand();
        command.setKeyword(keyword);
        command.setSyTenantId(tenantId);
        command.setUserName(userName);
        command.setNickname(nickname);
        command.setRealName(realName);
        command.setUserPhone(phone);
        command.setUserMail(email);

        if (status != null && !status.isEmpty()) {
            command.setStatus(Integer.parseInt(status));
        }
        if (deptId != null && !deptId.isEmpty()) {
            command.setDeptId(deptId);
        }

        List<IamUserProfileDTO> userProfiles = userProfileAppService.pageUserProfiles(command, page, limit);

        PageResult<IamUserProfileDTO> result = new PageResult<>();
        result.setRows(userProfiles);
        result.setTotal(userProfiles.size());
        return success(result);
    }

    // ==================== 用户档案状态管理接口 ====================

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用用户档案", description = "启用指定用户档案")
    @ResponseBody
    public CommonResult<Void> enableUserProfile(@PathVariable String id,
                                                @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.enableUserProfile(id, tenantId);
        return success();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用用户档案", description = "禁用指定用户档案")
    @ResponseBody
    public CommonResult<Void> disableUserProfile(@PathVariable String id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.disableUserProfile(id, tenantId);
        return success();
    }

    // ==================== 用户档案组织关系接口 ====================

    @PutMapping("/{id}/assign-department")
    @Operation(summary = "分配部门", description = "为用户档案分配部门")
    @ResponseBody
    public CommonResult<Void> assignDepartment(@PathVariable String id,
                                                @RequestParam String deptId,
                                                @RequestParam String deptName,
                                                @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.assignDepartment(id, deptId, deptName, tenantId);
        return success();
    }

    @PutMapping("/{id}/assign-post")
    @Operation(summary = "分配岗位", description = "为用户档案分配岗位")
    @ResponseBody
    public CommonResult<Void> assignPost(@PathVariable String id,
                                          @RequestParam String postCode,
                                          @RequestParam String postName,
                                          @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.assignPost(id, postCode, postName, tenantId);
        return success();
    }

    @PutMapping("/{id}/assign-role")
    @Operation(summary = "分配角色", description = "为用户档案分配角色")
    @ResponseBody
    public CommonResult<Void> assignRole(@PathVariable String id,
                                          @RequestParam String roleId,
                                          @RequestParam String roleName,
                                          @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        userProfileAppService.assignRole(id, roleId, roleName, tenantId);
        return success();
    }

    // ==================== 用户档案信息更新接口 ====================

    @PutMapping("/{id}/info/basic")
    @Operation(summary = "更新基本信息", description = "更新用户档案基本信息")
    @ResponseBody
    public CommonResult<Void> updateBasicInfo(@PathVariable String id,
                                               @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateBasicInfo(id, command);
        return success();
    }

    @PutMapping("/{id}/info/contact")
    @Operation(summary = "更新联系信息", description = "更新用户档案联系信息")
    @ResponseBody
    public CommonResult<Void> updateContactInfo(@PathVariable String id,
                                                 @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateContactInfo(id, command);
        return success();
    }

    @PutMapping("/{id}/info/job")
    @Operation(summary = "更新工作信息", description = "更新用户档案工作信息")
    @ResponseBody
    public CommonResult<Void> updateJobInfo(@PathVariable String id,
                                             @RequestBody IamUserProfileUpdateCommand command) {
        userProfileAppService.updateJobInfo(id, command);
        return success();
    }

    // ==================== 用户档案查询接口 ====================

    @GetMapping("/department/{deptId}")
    @Operation(summary = "根据部门查询用户档案", description = "获取指定部门的用户档案列表")
    @ResponseBody
    public CommonResult<List<IamUserProfileDTO>> getUserProfilesByDepartment(@PathVariable String deptId,
                                                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.getUserProfilesByDepartment(deptId, tenantId);
        return success(userProfiles);
    }

    @GetMapping("/post/{postCode}")
    @Operation(summary = "根据岗位查询用户档案", description = "获取指定岗位的用户档案列表")
    @ResponseBody
    public CommonResult<List<IamUserProfileDTO>> getUserProfilesByPost(@PathVariable String postCode,
                                                                       @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.getUserProfilesByPost(postCode, tenantId);
        return success(userProfiles);
    }

    @GetMapping("/search")
    @Operation(summary = "搜索用户档案", description = "根据关键字搜索用户档案")
    @ResponseBody
    public CommonResult<List<IamUserProfileDTO>> searchUserProfiles(@RequestParam String keyword,
                                                                     @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        List<IamUserProfileDTO> userProfiles = userProfileAppService.searchUserProfiles(keyword, tenantId);
        return success(userProfiles);
    }

    // ==================== 通用方法 ====================

    /**
     * 添加通用模板变量
     */
    private void addCommonModel(Model model, String title, String controllerName) {
        model.addAttribute("title", title);
        model.addAttribute("controllerName", controllerName);
        model.addAttribute("moduleName", "iam");

        if (StpUtil.isLogin()) {
            model.addAttribute("isLogin", true);
            model.addAttribute("userId", StpUtil.getLoginId());
            model.addAttribute("userName", StpUtil.getLoginIdAsString());
        } else {
            model.addAttribute("isLogin", false);
        }
    }

}
