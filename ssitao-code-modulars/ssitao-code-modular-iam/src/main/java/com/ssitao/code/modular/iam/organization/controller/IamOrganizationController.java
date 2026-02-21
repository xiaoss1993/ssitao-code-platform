package com.ssitao.code.modular.iam.organization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.organization.api.dto.IamCompanyDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamDepartmentDTO;
import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamCompanyCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamCompanyUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamDepartmentUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamCompanyAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamDepartmentAppService;
import com.ssitao.code.modular.iam.organization.application.service.IamPostAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM组织管理控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM组织管理", description = "IAM公司、部门、岗位相关接口")
@RestController
@RequestMapping("/iam/organization")
public class IamOrganizationController {

    private final IamCompanyAppService companyAppService;
    private final IamDepartmentAppService departmentAppService;
    private final IamPostAppService postAppService;

    public IamOrganizationController(IamCompanyAppService companyAppService,
                                     IamDepartmentAppService departmentAppService,
                                     IamPostAppService postAppService) {
        this.companyAppService = companyAppService;
        this.departmentAppService = departmentAppService;
        this.postAppService = postAppService;
    }

    // ==================== 公司管理接口 ====================

    @PostMapping("/company")
    @Operation(summary = "创建公司", description = "创建新公司")
    public CommonResult<String> createCompany(@Valid @RequestBody IamCompanyCreateCommand command) {
        String companyId = companyAppService.createCompany(command);
        return success(companyId);
    }

    @PutMapping("/company")
    @Operation(summary = "更新公司", description = "更新公司信息")
    public CommonResult<Void> updateCompany(@Valid @RequestBody IamCompanyUpdateCommand command) {
        companyAppService.updateCompany(command);
        return success();
    }

    @DeleteMapping("/company/{id}")
    @Operation(summary = "删除公司", description = "删除指定公司")
    public CommonResult<Void> deleteCompany(@PathVariable String id) {
        companyAppService.deleteCompany(id);
        return success();
    }

    @GetMapping("/company/{id}")
    @Operation(summary = "获取公司详情", description = "根据ID获取公司详情")
    public CommonResult<IamCompanyDTO> getCompany(@PathVariable String id) {
        IamCompanyDTO company = companyAppService.getCompany(id);
        return success(company);
    }

    @GetMapping("/company/code/{companyCode}")
    @Operation(summary = "根据编码获取公司", description = "根据公司编码获取公司详情")
    public CommonResult<IamCompanyDTO> getCompanyByCode(@PathVariable String companyCode,
                                                        @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamCompanyDTO company = companyAppService.getCompanyByCode(companyCode, tenantId);
        return success(company);
    }

    @GetMapping("/companies")
    @Operation(summary = "获取所有公司", description = "获取所有公司列表")
    public CommonResult<List<IamCompanyDTO>> listCompanies(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamCompanyDTO> companies = companyAppService.listCompanies(tenantId);
        return success(companies);
    }

    @GetMapping("/company/search")
    @Operation(summary = "搜索公司", description = "根据条件搜索公司")
    public CommonResult<List<IamCompanyDTO>> searchCompanies(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String companyType,
            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamCompanyDTO> companies = companyAppService.searchCompanies(companyName, companyType, tenantId);
        return success(companies);
    }

    // ==================== 部门管理接口 ====================

    @PostMapping("/department")
    @Operation(summary = "创建部门", description = "创建新部门")
    public CommonResult<Long> createDepartment(@Valid @RequestBody IamDepartmentCreateCommand command) {
        Long deptId = departmentAppService.createDepartment(command);
        return success(deptId);
    }

    @PostMapping("/department/batch")
    @Operation(summary = "批量创建部门", description = "批量创建部门")
    public CommonResult<List<Long>> batchCreateDepartments(@Valid @RequestBody List<IamDepartmentCreateCommand> commands) {
        List<Long> deptIds = departmentAppService.batchCreateDepartments(commands);
        return success(deptIds);
    }

    @PutMapping("/department")
    @Operation(summary = "更新部门", description = "更新部门信息")
    public CommonResult<Void> updateDepartment(@Valid @RequestBody IamDepartmentUpdateCommand command) {
        departmentAppService.updateDepartment(command);
        return success();
    }

    @DeleteMapping("/department/{id}")
    @Operation(summary = "删除部门", description = "删除指定部门")
    public CommonResult<Void> deleteDepartment(@PathVariable Long id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentAppService.deleteDepartment(id, tenantId);
        return success();
    }

    @GetMapping("/department/{id}")
    @Operation(summary = "获取部门详情", description = "根据ID获取部门详情")
    public CommonResult<IamDepartmentDTO> getDepartment(@PathVariable Long id,
                                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamDepartmentDTO department = departmentAppService.getDepartmentById(id, tenantId);
        return success(department);
    }

    @GetMapping("/departments")
    @Operation(summary = "获取部门列表", description = "获取所有部门列表")
    public CommonResult<List<IamDepartmentDTO>> listDepartments(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamDepartmentDTO> departments = departmentAppService.listDepartments(tenantId);
        return success(departments);
    }

    @GetMapping("/department/tree")
    @Operation(summary = "获取部门树", description = "获取部门树形结构")
    public CommonResult<List<IamDepartmentDTO>> getDepartmentTree(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamDepartmentDTO> tree = departmentAppService.getDepartmentTree(tenantId);
        return success(tree);
    }

    @PutMapping("/department/{id}/enable")
    @Operation(summary = "启用部门", description = "启用指定部门")
    public CommonResult<Void> enableDepartment(@PathVariable Long id,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentAppService.enableDepartment(id, tenantId);
        return success();
    }

    @PutMapping("/department/{id}/disable")
    @Operation(summary = "禁用部门", description = "禁用指定部门")
    public CommonResult<Void> disableDepartment(@PathVariable Long id,
                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        departmentAppService.disableDepartment(id, tenantId);
        return success();
    }

    // ==================== 岗位管理接口 ====================

    @PostMapping("/post")
    @Operation(summary = "创建岗位", description = "创建新岗位")
    public CommonResult<Long> createPost(@Valid @RequestBody IamPostCreateCommand command) {
        Long postId = postAppService.createPost(command);
        return success(postId);
    }

    @PostMapping("/post/batch")
    @Operation(summary = "批量创建岗位", description = "批量创建岗位")
    public CommonResult<List<Long>> batchCreatePosts(@Valid @RequestBody List<IamPostCreateCommand> commands) {
        List<Long> postIds = postAppService.batchCreatePosts(commands);
        return success(postIds);
    }

    @PutMapping("/post")
    @Operation(summary = "更新岗位", description = "更新岗位信息")
    public CommonResult<Void> updatePost(@Valid @RequestBody IamPostUpdateCommand command) {
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

    @GetMapping("/post/{id}")
    @Operation(summary = "获取岗位详情", description = "根据ID获取岗位详情")
    public CommonResult<IamPostDTO> getPost(@PathVariable Long id,
                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamPostDTO post = postAppService.getPostById(id, tenantId);
        return success(post);
    }

    @GetMapping("/posts")
    @Operation(summary = "获取岗位列表", description = "获取所有岗位列表")
    public CommonResult<List<IamPostDTO>> listPosts(@RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPostDTO> posts = postAppService.listPosts(tenantId);
        return success(posts);
    }

    @GetMapping("/post/department/{deptId}")
    @Operation(summary = "根据部门获取岗位", description = "获取指定部门的岗位列表")
    public CommonResult<List<IamPostDTO>> listPostsByDeptId(@PathVariable Long deptId,
                                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamPostDTO> posts = postAppService.listPostsByDeptId(deptId, tenantId);
        return success(posts);
    }

    @PutMapping("/post/{id}/enable")
    @Operation(summary = "启用岗位", description = "启用指定岗位")
    public CommonResult<Void> enablePost(@PathVariable Long id,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.enablePost(id, tenantId);
        return success();
    }

    @PutMapping("/post/{id}/disable")
    @Operation(summary = "禁用岗位", description = "禁用指定岗位")
    public CommonResult<Void> disablePost(@PathVariable Long id,
                                           @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        postAppService.disablePost(id, tenantId);
        return success();
    }

}
