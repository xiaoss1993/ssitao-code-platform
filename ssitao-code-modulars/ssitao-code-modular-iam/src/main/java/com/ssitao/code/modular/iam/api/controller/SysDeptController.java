package com.ssitao.code.modular.iam.api.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.api.dto.SysDeptDTO;
import com.ssitao.code.modular.iam.application.command.CreateDeptCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDeptCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDeptCommand;
import com.ssitao.code.modular.iam.application.service.SysDeptApplicationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DDD架构下的部门管理控制器
 */
@RestController
@RequestMapping("/api/iam/dept")
public class SysDeptController extends BaseController {

    @Autowired
    private SysDeptApplicationService deptApplicationService;

    /**
     * 查询部门列表
     */
    @RequiresPermissions("system:dept:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDeptDTO query) {
        startPage();
        List<SysDeptDTO> list = deptApplicationService.listDepts(query);
        return getDataTable(list);
    }

    /**
     * 导出部门列表
     */
    @Log(title = "部门管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dept:export")
    @PostMapping("/export")
    public void export(SysDeptDTO query, HttpServletResponse response) {
        List<SysDeptDTO> list = deptApplicationService.listDepts(query);
        ExcelUtil<SysDeptDTO> util = new ExcelUtil<>(SysDeptDTO.class);
        util.exportExcel(response, list, "部门数据");
    }

    /**
     * 获取部门详细信息
     */
    @RequiresPermissions("system:dept:list")
    @GetMapping("/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId) {
        SysDeptDTO dept = deptApplicationService.getDeptById(deptId);
        return success(dept);
    }

    /**
     * 新增部门
     */
    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CreateDeptCommand command) {
        try {
            Long deptId = deptApplicationService.createDept(command);
            return success(deptId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改部门
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UpdateDeptCommand command) {
        try {
            deptApplicationService.updateDept(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 删除部门
     */
    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds) {
        DeleteDeptCommand command = new DeleteDeptCommand();
        command.setDeptIds(deptIds);
        try {
            deptApplicationService.deleteDepts(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 移动部门
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping("/move")
    public AjaxResult move(@RequestParam Long deptId, @RequestParam Long newParentId, @RequestParam String ancestors) {
        try {
            deptApplicationService.moveDept(deptId, newParentId, ancestors);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改部门状态
     */
    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping("/status")
    public AjaxResult changeStatus(@RequestParam Long deptId, @RequestParam String status) {
        try {
            deptApplicationService.changeStatus(deptId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 获取所有正常状态的部门列表
     */
    @GetMapping("/normalList")
    public AjaxResult listAllNormal() {
        List<SysDeptDTO> list = deptApplicationService.listAllNormalDepts();
        return success(list);
    }

    /**
     * 根据父部门ID查询子部门列表
     */
    @GetMapping("/children/{parentId}")
    public AjaxResult listByParentId(@PathVariable Long parentId) {
        List<SysDeptDTO> list = deptApplicationService.listDeptsByParentId(parentId);
        return success(list);
    }
}
