package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.Ztree;
import com.ssitao.code.common.core.domain.entity.SysDictType;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysDictTypeDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictTypeCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDictTypeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictTypeCommand;
import com.ssitao.code.modular.iam.application.service.SysDictTypeApplicationService;
import com.ssitao.code.modular.iam.application.service.ISysDictTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController
{
    private String prefix = "system/dict/type";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @Autowired
    private SysDictTypeApplicationService dictTypeApplicationService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictType()
    {
        return prefix + "/type";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictType dictType)
    {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictType dictType)
    {

        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "字典类型");
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictType dict)
    {
        if (!dictTypeService.checkDictTypeUnique(dict))
        {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(getLoginName());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictType dict)
    {
        if (!dictTypeService.checkDictTypeUnique(dict))
        {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(getLoginName());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        dictTypeService.deleteDictTypeByIds(ids);
        return success();
    }

    /**
     * 刷新字典缓存
     */
    @RequiresPermissions("system:dict:remove")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    @ResponseBody
    public AjaxResult refreshCache()
    {
        dictTypeService.resetDictCache();
        return success();
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap)
    {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        mmap.put("dictList", dictTypeService.selectDictTypeAll());
        return "system/dict/data/data";
    }

    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public boolean checkDictTypeUnique(SysDictType dictType)
    {
        return dictTypeService.checkDictTypeUnique(dictType);
    }

    /**
     * 选择字典树
     */
    @GetMapping("/selectDictTree/{columnId}/{dictType}")
    public String selectDictTree(@PathVariable("columnId") Long columnId, @PathVariable("dictType") String dictType, ModelMap mmap)
    {
        mmap.put("columnId", columnId);
        mmap.put("dict", dictTypeService.selectDictTypeByType(dictType));
        return prefix + "/tree";
    }

    /**
     * 加载字典列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = dictTypeService.selectDictTree(new SysDictType());
        return ztrees;
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:dict:list")
    @GetMapping("/api/iam/dict/type/list")
    @ResponseBody
    public TableDataInfo apiList(SysDictTypeDTO query) {
        startPage();
        List<SysDictTypeDTO> list = dictTypeApplicationService.listDictTypes(query);
        return getDataTable(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/api/iam/dict/type/export")
    @ResponseBody
    public void apiExport(SysDictTypeDTO query, HttpServletResponse response) {
        List<SysDictTypeDTO> list = dictTypeApplicationService.listDictTypes(query);
        ExcelUtil<SysDictTypeDTO> util = new ExcelUtil<>(SysDictTypeDTO.class);
        util.exportExcel(response, list, "字典类型");
    }

    @RequiresPermissions("system:dict:list")
    @GetMapping("/api/iam/dict/type/{dictId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("dictId") Long dictId) {
        SysDictTypeDTO dictType = dictTypeApplicationService.getDictTypeById(dictId);
        return success(dictType);
    }

    @GetMapping("/api/iam/dict/type/type/{dictType}")
    @ResponseBody
    public AjaxResult apiGetByType(@PathVariable("dictType") String dictType) {
        SysDictTypeDTO dto = dictTypeApplicationService.getDictTypeByType(dictType);
        return success(dto);
    }

    @GetMapping("/api/iam/dict/type/normalList")
    @ResponseBody
    public AjaxResult apiListAllNormal() {
        List<SysDictTypeDTO> list = dictTypeApplicationService.listAllNormalDictTypes();
        return success(list);
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/api/iam/dict/type")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateDictTypeCommand command) {
        try {
            Long dictId = dictTypeApplicationService.createDictType(command);
            return success(dictId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PutMapping("/api/iam/dict/type")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateDictTypeCommand command) {
        try {
            dictTypeApplicationService.updateDictType(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @DeleteMapping("/api/iam/dict/type/{dictIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] dictIds) {
        DeleteDictTypeCommand command = new DeleteDictTypeCommand();
        command.setDictIds(dictIds);
        try {
            dictTypeApplicationService.deleteDictTypes(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @RequiresPermissions("system:dict:remove")
    @GetMapping("/api/iam/dict/type/refreshCache")
    @ResponseBody
    public AjaxResult apiRefreshCache() {
        try {
            dictTypeApplicationService.refreshCache();
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }
}
