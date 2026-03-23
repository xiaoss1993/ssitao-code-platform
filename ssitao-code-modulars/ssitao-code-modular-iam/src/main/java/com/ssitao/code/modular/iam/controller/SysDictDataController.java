package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.entity.SysDictData;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysDictDataDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictDataCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDictDataCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictDataCommand;
import com.ssitao.code.modular.iam.application.service.SysDictDataApplicationService;
import com.ssitao.code.modular.iam.application.service.ISysDictDataService;
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
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    private String prefix = "system/dict/data";

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private SysDictDataApplicationService dictDataApplicationService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictData()
    {
        return prefix + "/data";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictData dictData)
    {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictData dictData)
    {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 新增字典类型
     */
    @RequiresPermissions("system:dict:add")
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, ModelMap mmap)
    {
        mmap.put("dictType", dictType);
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictData dict)
    {
        dict.setCreateBy(getLoginName());
        return toAjax(dictDataService.insertDictData(dict));
    }

    /**
     * 修改字典类型
     */
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, ModelMap mmap)
    {
        mmap.put("dict", dictDataService.selectDictDataById(dictCode));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictData dict)
    {
        dict.setUpdateBy(getLoginName());
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        dictDataService.deleteDictDataByIds(ids);
        return success();
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:dict:list")
    @GetMapping("/api/iam/dict/data/list")
    @ResponseBody
    public TableDataInfo apiList(SysDictDataDTO query) {
        startPage();
        List<SysDictDataDTO> list = dictDataApplicationService.listDictDatas(query);
        return getDataTable(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/api/iam/dict/data/export")
    @ResponseBody
    public void apiExport(SysDictDataDTO query, HttpServletResponse response) {
        List<SysDictDataDTO> list = dictDataApplicationService.listDictDatas(query);
        ExcelUtil<SysDictDataDTO> util = new ExcelUtil<>(SysDictDataDTO.class);
        util.exportExcel(response, list, "字典数据");
    }

    @RequiresPermissions("system:dict:list")
    @GetMapping("/api/iam/dict/data/{dictCode}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("dictCode") Long dictCode) {
        SysDictDataDTO dictData = dictDataApplicationService.getDictDataById(dictCode);
        return success(dictData);
    }

    @GetMapping("/api/iam/dict/data/type/{dictType}")
    @ResponseBody
    public AjaxResult apiGetByType(@PathVariable("dictType") String dictType) {
        List<SysDictDataDTO> list = dictDataApplicationService.listDictDatasByType(dictType);
        return success(list);
    }

    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/api/iam/dict/data")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateDictDataCommand command) {
        try {
            Long dictCode = dictDataApplicationService.createDictData(command);
            return success(dictCode);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PutMapping("/api/iam/dict/data")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateDictDataCommand command) {
        try {
            dictDataApplicationService.updateDictData(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @DeleteMapping("/api/iam/dict/data/{dictCodes}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] dictCodes) {
        DeleteDictDataCommand command = new DeleteDictDataCommand();
        command.setDictCodes(dictCodes);
        try {
            dictDataApplicationService.deleteDictDatas(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PutMapping("/api/iam/dict/data/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long dictCode, @RequestParam String status) {
        try {
            dictDataApplicationService.changeStatus(dictCode, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }
}
