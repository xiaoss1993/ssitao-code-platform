package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysPostDTO;
import com.ssitao.code.modular.iam.application.command.CreatePostCommand;
import com.ssitao.code.modular.iam.application.command.DeletePostCommand;
import com.ssitao.code.modular.iam.application.command.UpdatePostCommand;
import com.ssitao.code.modular.iam.application.service.SysPostApplicationService;
import com.ssitao.code.modular.iam.domain.SysPost;
import com.ssitao.code.modular.iam.application.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 岗位信息操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/post")
public class IamPostController extends BaseController
{
    private String prefix = "system/post";

    @Autowired
    private ISysPostService postService;

    @Autowired
    private SysPostApplicationService postApplicationService;

    @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPost post)
    {
        startPage();
        List<SysPost> list = postService.selectPostList(post);
        return getDataTable(list);
    }

    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPost post)
    {
        List<SysPost> list = postService.selectPostList(post);
        ExcelUtil<SysPost> util = new ExcelUtil<SysPost>(SysPost.class);
        return util.exportExcel(list, "岗位数据");
    }

    @RequiresPermissions("system:post:remove")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(postService.deletePostByIds(ids));
    }

    /**
     * 新增岗位
     */
    @RequiresPermissions("system:post:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存岗位
     */
    @RequiresPermissions("system:post:add")
    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysPost post)
    {
        if (!postService.checkPostNameUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setCreateBy(getLoginName());
        return toAjax(postService.insertPost(post));
    }

    /**
     * 修改岗位
     */
    @RequiresPermissions("system:post:edit")
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, ModelMap mmap)
    {
        mmap.put("post", postService.selectPostById(postId));
        return prefix + "/edit";
    }

    /**
     * 修改保存岗位
     */
    @RequiresPermissions("system:post:edit")
    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysPost post)
    {
        if (!postService.checkPostNameUnique(post))
        {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        else if (!postService.checkPostCodeUnique(post))
        {
            return error("修改岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        post.setUpdateBy(getLoginName());
        return toAjax(postService.updatePost(post));
    }

    /**
     * 校验岗位名称
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public boolean checkPostNameUnique(SysPost post)
    {
        return postService.checkPostNameUnique(post);
    }

    /**
     * 校验岗位编码
     */
    @PostMapping("/checkPostCodeUnique")
    @ResponseBody
    public boolean checkPostCodeUnique(SysPost post)
    {
        return postService.checkPostCodeUnique(post);
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:post:list")
    @GetMapping("/api/iam/post/list")
    @ResponseBody
    public TableDataInfo apiList(SysPostDTO query) {
        startPage();
        List<SysPostDTO> list = postApplicationService.listPosts(query);
        return getDataTable(list);
    }

    @Log(title = "岗位管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:post:export")
    @PostMapping("/api/iam/post/export")
    @ResponseBody
    public void apiExport(SysPostDTO query, HttpServletResponse response) {
        List<SysPostDTO> list = postApplicationService.listPosts(query);
        ExcelUtil<SysPostDTO> util = new ExcelUtil<>(SysPostDTO.class);
        util.exportExcel(response, list, "岗位数据");
    }

    @RequiresPermissions("system:post:list")
    @GetMapping("/api/iam/post/{postId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("postId") Long postId) {
        SysPostDTO post = postApplicationService.getPostById(postId);
        return success(post);
    }

    @Log(title = "岗位管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:post:add")
    @PostMapping("/api/iam/post")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreatePostCommand command) {
        try {
            Long postId = postApplicationService.createPost(command);
            return success(postId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:post:edit")
    @PutMapping("/api/iam/post")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdatePostCommand command) {
        try {
            postApplicationService.updatePost(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:post:remove")
    @DeleteMapping("/api/iam/post/{postIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] postIds) {
        DeletePostCommand command = new DeletePostCommand();
        command.setPostIds(postIds);
        try {
            postApplicationService.deletePosts(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:post:edit")
    @PutMapping("/api/iam/post/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long postId, @RequestParam String status) {
        try {
            postApplicationService.changeStatus(postId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/api/iam/post/normalList")
    @ResponseBody
    public AjaxResult apiListAllNormal() {
        List<SysPostDTO> list = postApplicationService.listAllNormalPosts();
        return success(list);
    }
}
