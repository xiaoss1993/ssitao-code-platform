package com.ssitao.code.modular.hr.organization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.hr.organization.api.dto.HrDepartmentDTO;
import com.ssitao.code.modular.hr.organization.api.dto.HrPositionDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrDepartmentUpdateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionUpdateCommand;
import com.ssitao.code.modular.hr.organization.application.service.HrDepartmentAppService;
import com.ssitao.code.modular.hr.organization.application.service.HrPositionAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * HR组织架构管理控制器
 *
 * @author ssitao
 */
@Tag(name = "HR组织架构管理", description = "部门/岗位管理")
@Controller
@RequestMapping("/hr/org")
public class HrOrganizationController {

    @Resource
    private HrDepartmentAppService departmentAppService;

    @Resource
    private HrPositionAppService positionAppService;

    // ==================== 部门管理 ====================

    /**
     * 部门管理页面
     */
    @GetMapping("/department")
    @Operation(summary = "部门管理页面")
    public String departmentPage(Model model) {
        model.addAttribute("pageTitle", "部门管理");
        return "hr/department";
    }

    /**
     * 获取部门树
     */
    @GetMapping("/department/tree")
    @ResponseBody
    @Operation(summary = "获取部门树")
    public CommonResult<List<HrDepartmentDTO>> getDepartmentTree(
            @RequestParam String tenantId) {
        return success(departmentAppService.getDepartmentTree(tenantId));
    }

    /**
     * 获取部门列表
     */
    @GetMapping("/department/list")
    @ResponseBody
    @Operation(summary = "获取部门列表")
    public CommonResult<List<HrDepartmentDTO>> listDepartments(
            @RequestParam String tenantId) {
        return success(departmentAppService.listDepartments(tenantId));
    }

    /**
     * 获取部门详情
     */
    @GetMapping("/department/{id}")
    @ResponseBody
    @Operation(summary = "获取部门详情")
    public CommonResult<HrDepartmentDTO> getDepartment(
            @PathVariable String id,
            @RequestParam String tenantId) {
        return success(departmentAppService.getDepartmentById(id, tenantId));
    }

    /**
     * 创建部门
     */
    @PostMapping("/department")
    @ResponseBody
    @Operation(summary = "创建部门")
    public CommonResult<String> createDepartment(@Valid @RequestBody HrDepartmentCreateCommand command) {
        return success(departmentAppService.createDepartment(command));
    }

    /**
     * 更新部门
     */
    @PutMapping("/department")
    @ResponseBody
    @Operation(summary = "更新部门")
    public CommonResult<?> updateDepartment(@Valid @RequestBody HrDepartmentUpdateCommand command) {
        departmentAppService.updateDepartment(command);
        return success();
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/department/{id}")
    @ResponseBody
    @Operation(summary = "删除部门")
    public CommonResult<?> deleteDepartment(
            @PathVariable String id,
            @RequestParam String tenantId) {
        departmentAppService.deleteDepartment(id, tenantId);
        return success();
    }

    /**
     * 启用部门
     */
    @PostMapping("/department/{id}/enable")
    @ResponseBody
    @Operation(summary = "启用部门")
    public CommonResult<?> enableDepartment(
            @PathVariable String id,
            @RequestParam String tenantId) {
        departmentAppService.enableDepartment(id, tenantId);
        return success();
    }

    /**
     * 禁用部门
     */
    @PostMapping("/department/{id}/disable")
    @ResponseBody
    @Operation(summary = "禁用部门")
    public CommonResult<?> disableDepartment(
            @PathVariable String id,
            @RequestParam String tenantId) {
        departmentAppService.disableDepartment(id, tenantId);
        return success();
    }

    // ==================== 岗位管理 ====================

    /**
     * 岗位管理页面
     */
    @GetMapping("/position")
    @Operation(summary = "岗位管理页面")
    public String positionPage(Model model) {
        model.addAttribute("pageTitle", "岗位管理");
        return "hr/position";
    }

    /**
     * 获取岗位列表
     */
    @GetMapping("/position/list")
    @ResponseBody
    @Operation(summary = "获取岗位列表")
    public CommonResult<List<HrPositionDTO>> listPositions(@RequestParam String tenantId) {
        return success(positionAppService.listPositions(tenantId));
    }

    /**
     * 获取岗位列表（根据部门）
     */
    @GetMapping("/position/listByDept/{deptId}")
    @ResponseBody
    @Operation(summary = "获取岗位列表（根据部门）")
    public CommonResult<List<HrPositionDTO>> listPositionsByDept(
            @PathVariable String deptId,
            @RequestParam String tenantId) {
        return success(positionAppService.listPositionsByDept(deptId, tenantId));
    }

    /**
     * 获取岗位详情
     */
    @GetMapping("/position/{id}")
    @ResponseBody
    @Operation(summary = "获取岗位详情")
    public CommonResult<HrPositionDTO> getPosition(
            @PathVariable String id,
            @RequestParam String tenantId) {
        return success(positionAppService.getPositionById(id, tenantId));
    }

    /**
     * 创建岗位
     */
    @PostMapping("/position")
    @ResponseBody
    @Operation(summary = "创建岗位")
    public CommonResult<String> createPosition(@Valid @RequestBody HrPositionCreateCommand command) {
        return success(positionAppService.createPosition(command));
    }

    /**
     * 更新岗位
     */
    @PutMapping("/position")
    @ResponseBody
    @Operation(summary = "更新岗位")
    public CommonResult<?> updatePosition(@Valid @RequestBody HrPositionUpdateCommand command) {
        positionAppService.updatePosition(command);
        return success();
    }

    /**
     * 删除岗位
     */
    @DeleteMapping("/position/{id}")
    @ResponseBody
    @Operation(summary = "删除岗位")
    public CommonResult<?> deletePosition(
            @PathVariable String id,
            @RequestParam String tenantId) {
        positionAppService.deletePosition(id, tenantId);
        return success();
    }

    /**
     * 启用岗位
     */
    @PostMapping("/position/{id}/enable")
    @ResponseBody
    @Operation(summary = "启用岗位")
    public CommonResult<?> enablePosition(
            @PathVariable String id,
            @RequestParam String tenantId) {
        positionAppService.enablePosition(id, tenantId);
        return success();
    }

    /**
     * 禁用岗位
     */
    @PostMapping("/position/{id}/disable")
    @ResponseBody
    @Operation(summary = "禁用岗位")
    public CommonResult<?> disablePosition(
            @PathVariable String id,
            @RequestParam String tenantId) {
        positionAppService.disablePosition(id, tenantId);
        return success();
    }
}
