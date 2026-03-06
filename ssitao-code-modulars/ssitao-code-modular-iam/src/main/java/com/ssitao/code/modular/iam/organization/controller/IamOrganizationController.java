package com.ssitao.code.modular.iam.organization.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.organization.api.dto.IamCompanyDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamGroupDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamUserOrgDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamCompanyCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamUserOrgCreateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamCompanyAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamGroupAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamPostAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamUserOrgAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM组织机构管理控制器
 * 提供集团、公司、部门、用户组织的 CRUD API
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "组织机构管理", description = "集团/公司/部门/岗位管理")
@Controller
@RequestMapping("/admin/org")
public class IamOrganizationController {

    private final IamCompanyAppService companyAppService;
    private final IamDepartmentAppService departmentAppService;
    private final IamPostAppService postAppService;
    private final IamGroupAppService groupAppService;
    private final IamUserOrgAppService userOrgAppService;

    public IamOrganizationController(IamCompanyAppService companyAppService,
                                     IamDepartmentAppService departmentAppService,
                                     IamPostAppService postAppService,
                                     IamGroupAppService groupAppService,
                                     IamUserOrgAppService userOrgAppService) {
        this.companyAppService = companyAppService;
        this.departmentAppService = departmentAppService;
        this.postAppService = postAppService;
        this.groupAppService = groupAppService;
        this.userOrgAppService = userOrgAppService;
    }

    // ==================== 页面跳转 ====================

    /**
     * 组织管理页面
     */
    @GetMapping
    @Operation(summary = "组织管理页面")
    public String orgPage(Model model) {
        addCommonModel(model, "组织管理", "org");
        return "iam/org";
    }

    /**
     * 组织添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "组织添加页面")
    public String orgAddPage(Model model) {
        addCommonModel(model, "添加组织", "org");
        return "iam/org-edit";
    }

    /**
     * 组织编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "组织编辑页面")
    public String orgEditPage(Model model) {
        addCommonModel(model, "编辑组织", "org");
        return "iam/org-edit";
    }

    // ==================== 集团管理接口 ====================

    @PostMapping("/group")
    @Operation(summary = "创建集团", description = "创建新集团")
    @ResponseBody
    public CommonResult<String> createGroup(@Valid @RequestBody IamGroupCreateCommand command) {
        String groupId = groupAppService.createGroup(command);
        return success(groupId);
    }

    @PutMapping("/group")
    @Operation(summary = "更新集团", description = "更新集团信息")
    @ResponseBody
    public CommonResult<Void> updateGroup(@Valid @RequestBody IamGroupUpdateCommand command) {
        groupAppService.updateGroup(command);
        return success();
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "删除集团", description = "删除指定集团")
    @ResponseBody
    public CommonResult<Void> deleteGroup(@PathVariable String id) {
        groupAppService.deleteGroup(id);
        return success();
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "获取集团详情", description = "根据ID获取集团详情")
    @ResponseBody
    public CommonResult<IamGroupDTO> getGroup(@PathVariable String id) {
        IamGroupDTO group = groupAppService.getGroup(id);
        return success(group);
    }

    @GetMapping("/group/list")
    @Operation(summary = "获取集团列表", description = "获取所有集团列表")
    @ResponseBody
    public CommonResult<List<IamGroupDTO>> listGroups(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamGroupDTO> groups = groupAppService.listGroups(tenantId);
        return success(groups);
    }

    // ==================== 公司管理接口 ====================

    @PostMapping("/company")
    @Operation(summary = "创建公司", description = "创建新公司")
    @ResponseBody
    public CommonResult<String> createCompany(@Valid @RequestBody IamCompanyCreateCommand command) {
        String companyId = companyAppService.createCompany(command);
        return success(companyId);
    }

    @GetMapping("/company/list")
    @Operation(summary = "获取公司列表", description = "获取所有公司列表")
    @ResponseBody
    public CommonResult<List<IamCompanyDTO>> listCompanies(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamCompanyDTO> companies = companyAppService.listCompanies(tenantId);
        return success(companies);
    }

    // ==================== 部门管理接口 ====================

    @PostMapping("/department")
    @Operation(summary = "创建部门", description = "创建新部门")
    @ResponseBody
    public CommonResult<Long> createDepartment(@Valid @RequestBody IamDepartmentCreateCommand command) {
        Long deptId = departmentAppService.createDepartment(command);
        return success(deptId);
    }

    @GetMapping("/department/list")
    @Operation(summary = "获取部门列表", description = "获取所有部门列表")
    @ResponseBody
    public CommonResult<List<IamDepartmentDTO>> listDepartments(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamDepartmentDTO> departments = departmentAppService.listDepartments(tenantId);
        return success(departments);
    }

    // ==================== 用户组织管理接口 ====================

    @PostMapping("/user-org")
    @Operation(summary = "分配用户组织", description = "为用户分配组织")
    @ResponseBody
    public CommonResult<String> assignUserOrg(@Valid @RequestBody IamUserOrgCreateCommand command) {
        String userOrgId = userOrgAppService.assignUserOrg(command);
        return success(userOrgId);
    }

    @GetMapping("/user-org/{userId}")
    @Operation(summary = "获取用户组织", description = "获取指定用户的组织信息")
    @ResponseBody
    public CommonResult<List<IamUserOrgDTO>> getUserOrgs(@PathVariable String userId) {
        List<IamUserOrgDTO> userOrgs = userOrgAppService.getUserOrgs(userId);
        return success(userOrgs);
    }

    // ==================== 岗位管理接口 ====================

    @PostMapping("/post")
    @Operation(summary = "创建岗位", description = "创建新岗位")
    @ResponseBody
    public CommonResult<Long> createPost(@Valid @RequestBody IamPostCreateCommand command,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        Long postId = postAppService.createPost(command);
        return success(postId);
    }

    @GetMapping("/post/list")
    @Operation(summary = "获取岗位列表", description = "获取所有岗位列表")
    @ResponseBody
    public CommonResult<List<IamPostDTO>> listPosts(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPostDTO> posts = postAppService.listPosts(tenantId);
        return success(posts);
    }

    @PutMapping("/post")
    @Operation(summary = "更新岗位", description = "更新岗位信息")
    @ResponseBody
    public CommonResult<Void> updatePost(@Valid @RequestBody IamPostUpdateCommand command,
                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.updatePost(command);
        return success();
    }

    @DeleteMapping("/post/{id}")
    @Operation(summary = "删除岗位", description = "删除指定岗位")
    @ResponseBody
    public CommonResult<Void> deletePost(@PathVariable Long id,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.deletePost(id, tenantId);
        return success();
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
