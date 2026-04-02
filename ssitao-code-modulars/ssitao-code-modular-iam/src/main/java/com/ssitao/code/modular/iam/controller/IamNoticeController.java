package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysNoticeDTO;
import com.ssitao.code.modular.iam.application.command.CreateNoticeCommand;
import com.ssitao.code.modular.iam.application.command.DeleteNoticeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateNoticeCommand;
import com.ssitao.code.modular.iam.application.service.SysNoticeApplicationService;
import com.ssitao.code.modular.iam.domain.SysNotice;
import com.ssitao.code.modular.iam.application.service.ISysNoticeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/notice")
public class IamNoticeController extends BaseController
{
    private String prefix = "system/notice";

    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private SysNoticeApplicationService noticeApplicationService;

    @RequiresPermissions("system:notice:view")
    @GetMapping()
    public String notice()
    {
        return prefix + "/notice";
    }

    /**
     * 查询公告列表
     */
    @RequiresPermissions("system:notice:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 新增公告
     */
    @RequiresPermissions("system:notice:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公告
     */
    @RequiresPermissions("system:notice:add")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysNotice notice)
    {
        notice.setCreateBy(getLoginName());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改公告
     */
    @RequiresPermissions("system:notice:edit")
    @GetMapping("/edit/{noticeId}")
    public String edit(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/edit";
    }

    /**
     * 修改保存公告
     */
    @RequiresPermissions("system:notice:edit")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysNotice notice)
    {
        notice.setUpdateBy(getLoginName());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 查询公告详细
     */
    @RequiresPermissions("system:notice:list")
    @GetMapping("/view/{noticeId}")
    public String view(@PathVariable("noticeId") Long noticeId, ModelMap mmap)
    {
        mmap.put("notice", noticeService.selectNoticeById(noticeId));
        return prefix + "/view";
    }

    /**
     * 删除公告
     */
    @RequiresPermissions("system:notice:remove")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(noticeService.deleteNoticeByIds(ids));
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:notice:list")
    @GetMapping("/api/iam/notice/list")
    @ResponseBody
    public TableDataInfo apiList(SysNoticeDTO query) {
        startPage();
        List<SysNoticeDTO> list = noticeApplicationService.listNotices(query);
        return getDataTable(list);
    }

    @Log(title = "通知公告", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:notice:export")
    @PostMapping("/api/iam/notice/export")
    @ResponseBody
    public void apiExport(SysNoticeDTO query, HttpServletResponse response) {
        List<SysNoticeDTO> list = noticeApplicationService.listNotices(query);
        ExcelUtil<SysNoticeDTO> util = new ExcelUtil<>(SysNoticeDTO.class);
        util.exportExcel(response, list, "通知公告数据");
    }

    @RequiresPermissions("system:notice:list")
    @GetMapping("/api/iam/notice/{noticeId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("noticeId") Long noticeId) {
        SysNoticeDTO notice = noticeApplicationService.getNoticeById(noticeId);
        return success(notice);
    }

    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:notice:add")
    @PostMapping("/api/iam/notice")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateNoticeCommand command) {
        try {
            Long noticeId = noticeApplicationService.createNotice(command);
            return success(noticeId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:notice:edit")
    @PutMapping("/api/iam/notice")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateNoticeCommand command) {
        try {
            noticeApplicationService.updateNotice(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:notice:remove")
    @DeleteMapping("/api/iam/notice/{noticeIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] noticeIds) {
        DeleteNoticeCommand command = new DeleteNoticeCommand();
        command.setNoticeIds(noticeIds);
        try {
            noticeApplicationService.deleteNotices(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:notice:edit")
    @PutMapping("/api/iam/notice/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long noticeId, @RequestParam String status) {
        try {
            noticeApplicationService.changeStatus(noticeId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }
}
