package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.Ztree;
import com.ssitao.code.common.core.domain.entity.SysDept;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.StringUtils;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysDeptDTO;
import com.ssitao.code.modular.iam.application.command.CreateDeptCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDeptCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDeptCommand;
import com.ssitao.code.modular.iam.application.service.SysDeptApplicationService;
import com.ssitao.code.modular.iam.application.service.ISysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 部门信息
 *
 * 合并后的Controller，同时支持：
 * - 页面跳转：/system/dept/*
 * - REST API：/api/iam/dept/*
 */
@Controller
public class SysDeptController extends BaseController {

    private String prefix = "system/dept";

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private SysDeptApplicationService deptApplicationService;

    // ==================== 页面跳转接口 ====================

    @RequiresPermissions("system:dept:view")
    @GetMapping("/system/dept")
    public String dept() {
        return prefix + "/dept";
    }

    @RequiresPermissions("system:dept:add")
    @GetMapping("/system/dept/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap) {
        if (!getSysUser().isAdmin()) {
            parentId = getSysUser().getDeptId();
        }
        mmap.put("dept", deptService.selectDeptById(parentId));
        return prefix + "/add";
    }

    @RequiresPermissions("system:dept:edit")
    @GetMapping("/system/dept/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        deptService.checkDeptDataScope(deptId);
        SysDept dept = deptService.selectDeptById(deptId);
        if (StringUtils.isNotNull(dept) && 100L == deptId) {
            dept.setParentName("无");
        }
        mmap.put("dept", dept);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:dept:list")
    @GetMapping(value = {"/system/dept/selectDeptTree/{deptId}", "/system/dept/selectDeptTree/{deptId}/{excludeId}"})
    public String selectDeptTree(@PathVariable("deptId") Long deptId,
                                  @PathVariable(value = "excludeId", required = false) Long excludeId,
                                  ModelMap mmap) {
        mmap.put("dept", deptService.selectDeptById(deptId));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    // ==================== 传统业务接口 (返回JSON) ====================

    @RequiresPermissions("system:dept:list")
    @PostMapping("/system/dept/list")
    @ResponseBody
    public List<SysDept> list(SysDept dept) {
        return deptService.selectDeptList(dept);
    }

    @Log(title = "部门管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dept:export")
    @PostMapping("/system/dept/export")
    @ResponseBody
    public AjaxResult export(SysDept dept) {
        List<SysDept> list = deptService.selectDeptList(dept);
        ExcelUtil<SysDept> util = new ExcelUtil<>(SysDept.class);
        return util.exportExcel(list, "部门数据");
    }

    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/system/dept/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDept dept) {
        dept.setCreateBy(getLoginName());
        return toAjax(deptService.createDept(dept));
    }

    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PostMapping("/system/dept/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDept dept) {
        dept.setUpdateBy(getLoginName());
        return toAjax(deptService.createOrUpdateDept(dept));
    }

    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @GetMapping("/system/dept/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId) {
        return toAjax(deptService.createOrDeleteDept(deptId));
    }

    @PostMapping("/system/dept/checkDeptNameUnique")
    @ResponseBody
    public boolean checkDeptNameUnique(SysDept dept) {
        return deptService.checkDeptNameUnique(dept);
    }

    @RequiresPermissions("system:dept:list")
    @GetMapping("/system/dept/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId) {
        SysDept dept = new SysDept();
        dept.setExcludeId(excludeId);
        return deptService.selectDeptTreeExcludeChild(dept);
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:dept:list")
    @GetMapping("/api/iam/dept/list")
    @ResponseBody
    public TableDataInfo apiList(SysDeptDTO query) {
        startPage();
        List<SysDeptDTO> list = deptApplicationService.listDepts(query);
        return getDataTable(list);
    }

    @Log(title = "部门管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dept:export")
    @PostMapping("/api/iam/dept/export")
    @ResponseBody
    public void apiExport(SysDeptDTO query, HttpServletResponse response) {
        List<SysDeptDTO> list = deptApplicationService.listDepts(query);
        ExcelUtil<SysDeptDTO> util = new ExcelUtil<>(SysDeptDTO.class);
        util.exportExcel(response, list, "部门数据");
    }

    @RequiresPermissions("system:dept:list")
    @GetMapping("/api/iam/dept/{deptId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("deptId") Long deptId) {
        SysDeptDTO dept = deptApplicationService.getDeptById(deptId);
        return success(dept);
    }

    @Log(title = "部门管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dept:add")
    @PostMapping("/api/iam/dept")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateDeptCommand command) {
        try {
            Long deptId = deptApplicationService.createDept(command);
            return success(deptId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping("/api/iam/dept")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateDeptCommand command) {
        try {
            deptApplicationService.updateDept(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "部门管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @DeleteMapping("/api/iam/dept/{deptIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] deptIds) {
        DeleteDeptCommand command = new DeleteDeptCommand();
        command.setDeptIds(deptIds);
        try {
            deptApplicationService.deleteDepts(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping("/api/iam/dept/move")
    @ResponseBody
    public AjaxResult apiMove(@RequestParam Long deptId, @RequestParam Long newParentId, @RequestParam String ancestors) {
        try {
            deptApplicationService.moveDept(deptId, newParentId, ancestors);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "部门管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dept:edit")
    @PutMapping("/api/iam/dept/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long deptId, @RequestParam String status) {
        try {
            deptApplicationService.changeStatus(deptId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/api/iam/dept/normalList")
    @ResponseBody
    public AjaxResult apiListAllNormal() {
        List<SysDeptDTO> list = deptApplicationService.listAllNormalDepts();
        return success(list);
    }

    @GetMapping("/api/iam/dept/children/{parentId}")
    @ResponseBody
    public AjaxResult apiListByParentId(@PathVariable Long parentId) {
        List<SysDeptDTO> list = deptApplicationService.listDeptsByParentId(parentId);
        return success(list);
    }
}