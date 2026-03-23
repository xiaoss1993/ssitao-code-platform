package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysConfigDTO;
import com.ssitao.code.modular.iam.application.command.CreateConfigCommand;
import com.ssitao.code.modular.iam.application.command.DeleteConfigCommand;
import com.ssitao.code.modular.iam.application.command.UpdateConfigCommand;
import com.ssitao.code.modular.iam.application.service.SysConfigApplicationService;
import com.ssitao.code.modular.iam.domain.SysConfig;
import com.ssitao.code.modular.iam.application.service.ISysConfigService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 参数配置 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    private String prefix = "system/config";

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysConfigApplicationService configApplicationService;

    @RequiresPermissions("system:config:view")
    @GetMapping()
    public String config()
    {
        return prefix + "/config";
    }

    /**
     * 查询参数配置列表
     */
    @RequiresPermissions("system:config:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysConfig config)
    {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysConfig config)
    {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 新增参数配置
     */
    @RequiresPermissions("system:config:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存参数配置
     */
    @RequiresPermissions("system:config:add")
    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysConfig config)
    {
        if (!configService.checkConfigKeyUnique(config))
        {
            return error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(getLoginName());
        return toAjax(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @RequiresPermissions("system:config:edit")
    @GetMapping("/edit/{configId}")
    public String edit(@PathVariable("configId") Long configId, ModelMap mmap)
    {
        mmap.put("config", configService.selectConfigById(configId));
        return prefix + "/edit";
    }

    /**
     * 修改保存参数配置
     */
    @RequiresPermissions("system:config:edit")
    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysConfig config)
    {
        if (!configService.checkConfigKeyUnique(config))
        {
            return error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(getLoginName());
        return toAjax(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        configService.deleteConfigByIds(ids);
        return success();
    }

    /**
     * 刷新参数缓存
     */
    @RequiresPermissions("system:config:remove")
    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @GetMapping("/refreshCache")
    @ResponseBody
    public AjaxResult refreshCache()
    {
        configService.resetConfigCache();
        return success();
    }

    /**
     * 校验参数键名
     */
    @PostMapping("/checkConfigKeyUnique")
    @ResponseBody
    public boolean checkConfigKeyUnique(SysConfig config)
    {
        return configService.checkConfigKeyUnique(config);
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:config:list")
    @GetMapping("/api/iam/config/list")
    @ResponseBody
    public TableDataInfo apiList(SysConfigDTO query) {
        startPage();
        List<SysConfigDTO> list = configApplicationService.listConfigs(query);
        return getDataTable(list);
    }

    @Log(title = "参数管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:config:export")
    @PostMapping("/api/iam/config/export")
    @ResponseBody
    public void apiExport(SysConfigDTO query, HttpServletResponse response) {
        List<SysConfigDTO> list = configApplicationService.listConfigs(query);
        ExcelUtil<SysConfigDTO> util = new ExcelUtil<>(SysConfigDTO.class);
        util.exportExcel(response, list, "参数数据");
    }

    @RequiresPermissions("system:config:list")
    @GetMapping("/api/iam/config/{configId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("configId") Long configId) {
        SysConfigDTO config = configApplicationService.getConfigById(configId);
        return success(config);
    }

    @GetMapping("/api/iam/config/key/{configKey}")
    @ResponseBody
    public AjaxResult apiGetByKey(@PathVariable("configKey") String configKey) {
        SysConfigDTO config = configApplicationService.getConfigByKey(configKey);
        return success(config);
    }

    @Log(title = "参数管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:config:add")
    @PostMapping("/api/iam/config")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateConfigCommand command) {
        try {
            Long configId = configApplicationService.createConfig(command);
            return success(configId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "参数管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:config:edit")
    @PutMapping("/api/iam/config")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateConfigCommand command) {
        try {
            configApplicationService.updateConfig(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "参数管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:config:remove")
    @DeleteMapping("/api/iam/config/{configIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] configIds) {
        DeleteConfigCommand command = new DeleteConfigCommand();
        command.setConfigIds(configIds);
        try {
            configApplicationService.deleteConfigs(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "参数管理", businessType = BusinessType.CLEAN)
    @RequiresPermissions("system:config:remove")
    @GetMapping("/api/iam/config/refreshCache")
    @ResponseBody
    public AjaxResult apiRefreshCache() {
        try {
            configApplicationService.refreshCache();
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }
}
