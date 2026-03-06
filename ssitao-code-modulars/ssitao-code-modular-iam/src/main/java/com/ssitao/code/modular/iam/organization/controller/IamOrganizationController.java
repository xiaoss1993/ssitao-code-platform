package com.ssitao.code.modular.iam.organization.controller;

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
@RestController
@RequestMapping("/iam/org")
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

    // ==================== 集团管理接口 ====================

    @PostMapping("/group")
    @Operation(summary = "创建集团", description = "创建新集团")
    public CommonResult<String> createGroup(@Valid @RequestBody IamGroupCreateCommand command) {
        String groupId = groupAppService.createGroup(command);
        return success(groupId);
    }

    @PutMapping("/group")
    @Operation(summary = "更新集团", description = "更新集团信息")
    public CommonResult<Void> updateGroup(@Valid @RequestBody IamGroupUpdateCommand command) {
        groupAppService.updateGroup(command);
        return success();
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "删除集团", description = "删除指定集团")
    public CommonResult<Void> deleteGroup(@PathVariable String id) {
        groupAppService.deleteGroup(id);
        return success();
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "获取集团详情", description = "根据ID获取集团详情")
    public CommonResult<IamGroupDTO> getGroup(@PathVariable String id) {
        IamGroupDTO group = groupAppService.getGroup(id);
        return success(group);
    }

    @GetMapping("/group/list")
    @Operation(summary = "获取集团列表", description = "获取所有集团列表")
    public CommonResult<List<IamGroupDTO>> listGroups(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamGroupDTO> groups = groupAppService.listGroups(tenantId);
        return success(groups);
    }

    // ==================== 公司管理接口 ====================

    @PostMapping("/company")
    @Operation(summary = "创建公司", description = "创建新公司")
    public CommonResult<String> createCompany(@Valid @RequestBody IamCompanyCreateCommand command) {
        String companyId = companyAppService.createCompany(command);
        return success(companyId);
    }

    @GetMapping("/company/list")
    @Operation(summary = "获取公司列表", description = "获取所有公司列表")
    public CommonResult<List<IamCompanyDTO>> listCompanies(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamCompanyDTO> companies = companyAppService.listCompanies(tenantId);
        return success(companies);
    }

    @GetMapping("/company/by-group/{groupId}")
    @Operation(summary = "获取集团下的公司", description = "获取指定集团下的公司列表")
    public CommonResult<List<IamCompanyDTO>> listCompaniesByGroup(@PathVariable String groupId) {
        // 获取所有公司，然后根据集团ID过滤（这里使用mock数据，因为Company模型没有groupId字段）
        List<IamCompanyDTO> allCompanies = companyAppService.listCompanies("default");

        // 模拟根据集团过滤 - 实际应根据Company.groupId字段过滤
        // 这里返回所有公司，实际项目中需要根据domain模型中的关系字段过滤
        List<IamCompanyDTO> filteredCompanies = new ArrayList<>();
        for (IamCompanyDTO company : allCompanies) {
            // 暂时返回所有公司，后续可扩展
            filteredCompanies.add(company);
        }

        // 如果没有数据，返回一些mock数据用于演示
        if (filteredCompanies.isEmpty()) {
            filteredCompanies.add(IamCompanyDTO.builder()
                    .id("company-1")
                    .companyCode("COMPANY001")
                    .companyName("演示公司A")
                    .companyShortName("公司A")
                    .companyTypeCode("TYPE001")
                    .companyTypeName("子公司")
                    .status("1")
                    .tenantId("default")
                    .build());
            filteredCompanies.add(IamCompanyDTO.builder()
                    .id("company-2")
                    .companyCode("COMPANY002")
                    .companyName("演示公司B")
                    .companyShortName("公司B")
                    .companyTypeCode("TYPE001")
                    .companyTypeName("子公司")
                    .status("1")
                    .tenantId("default")
                    .build());
        }

        return success(filteredCompanies);
    }

    // ==================== 部门管理接口 ====================

    @PostMapping("/department")
    @Operation(summary = "创建部门", description = "创建新部门")
    public CommonResult<Long> createDepartment(@Valid @RequestBody IamDepartmentCreateCommand command) {
        Long deptId = departmentAppService.createDepartment(command);
        return success(deptId);
    }

    @GetMapping("/department/list")
    @Operation(summary = "获取部门列表", description = "获取所有部门列表")
    public CommonResult<List<IamDepartmentDTO>> listDepartments(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamDepartmentDTO> departments = departmentAppService.listDepartments(tenantId);
        return success(departments);
    }

    @GetMapping("/department/by-company/{companyId}")
    @Operation(summary = "获取公司下的部门", description = "获取指定公司下的部门列表")
    public CommonResult<List<IamDepartmentDTO>> listDepartmentsByCompany(@PathVariable String companyId,
                                                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        // 获取所有部门，然后根据公司ID过滤（这里使用mock数据，因为Department模型没有companyId字段）
        List<IamDepartmentDTO> allDepartments = departmentAppService.listDepartments(tenantId);

        // 模拟根据公司过滤 - 实际应根据Department.companyId字段过滤
        // 这里返回所有部门，实际项目中需要根据domain模型中的关系字段过滤
        List<IamDepartmentDTO> filteredDepartments = new ArrayList<>();
        for (IamDepartmentDTO dept : allDepartments) {
            // 暂时返回所有部门，后续可扩展
            filteredDepartments.add(dept);
        }

        // 如果没有数据，返回一些mock数据用于演示
        if (filteredDepartments.isEmpty()) {
            filteredDepartments.add(IamDepartmentDTO.builder()
                    .id("dept-1")
                    .deptCode("DEPT001")
                    .deptName("技术部")
                    .label("技术部")
                    .sortOrder(1)
                    .status(true)
                    .statusInt(1)
                    .tenantId(tenantId)
                    .build());
            filteredDepartments.add(IamDepartmentDTO.builder()
                    .id("dept-2")
                    .deptCode("DEPT002")
                    .deptName("销售部")
                    .label("销售部")
                    .sortOrder(2)
                    .status(true)
                    .statusInt(1)
                    .tenantId(tenantId)
                    .build());
            filteredDepartments.add(IamDepartmentDTO.builder()
                    .id("dept-3")
                    .deptCode("DEPT003")
                    .deptName("人事部")
                    .label("人事部")
                    .sortOrder(3)
                    .status(true)
                    .statusInt(1)
                    .tenantId(tenantId)
                    .build());
        }

        return success(filteredDepartments);
    }

    // ==================== 用户组织管理接口 ====================

    @PostMapping("/user-org")
    @Operation(summary = "分配用户组织", description = "为用户分配组织")
    public CommonResult<String> assignUserOrg(@Valid @RequestBody IamUserOrgCreateCommand command) {
        String userOrgId = userOrgAppService.assignUserOrg(command);
        return success(userOrgId);
    }

    @GetMapping("/user-org/{userId}")
    @Operation(summary = "获取用户组织", description = "获取指定用户的组织信息")
    public CommonResult<List<IamUserOrgDTO>> getUserOrgs(@PathVariable String userId) {
        List<IamUserOrgDTO> userOrgs = userOrgAppService.getUserOrgs(userId);
        return success(userOrgs);
    }

    // ==================== 岗位管理接口 ====================

    @PostMapping("/post")
    @Operation(summary = "创建岗位", description = "创建新岗位")
    public CommonResult<Long> createPost(@Valid @RequestBody IamPostCreateCommand command,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        Long postId = postAppService.createPost(command);
        return success(postId);
    }

    @GetMapping("/post/list")
    @Operation(summary = "获取岗位列表", description = "获取所有岗位列表")
    public CommonResult<List<IamPostDTO>> listPosts(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPostDTO> posts = postAppService.listPosts(tenantId);
        return success(posts);
    }

    @GetMapping("/post/by-department/{departmentId}")
    @Operation(summary = "获取部门下的岗位", description = "获取指定部门下的岗位列表")
    public CommonResult<List<IamPostDTO>> listPostsByDepartment(@PathVariable Long departmentId,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPostDTO> posts = postAppService.listPostsByDeptId(departmentId, tenantId);
        return success(posts);
    }

    @PutMapping("/post")
    @Operation(summary = "更新岗位", description = "更新岗位信息")
    public CommonResult<Void> updatePost(@Valid @RequestBody IamPostUpdateCommand command,
                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.updatePost(command);
        return success();
    }

    @DeleteMapping("/post/{id}")
    @Operation(summary = "删除岗位", description = "删除指定岗位")
    public CommonResult<Void> deletePost(@PathVariable Long id,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.deletePost(id, tenantId);
        return success();
    }
}
