package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.controller;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SFileInfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SFileInfoService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 文件信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sFileInfo")
public class SFileInfoController {

    @Autowired
    private SFileInfoService sFileInfoService;

    /**
     * 保存文件信息。
     *
     * @param sFileInfo 文件信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SFileInfo sFileInfo) {
        return sFileInfoService.save(sFileInfo);
    }

    /**
     * 根据主键删除文件信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sFileInfoService.removeById(id);
    }

    /**
     * 根据主键更新文件信息。
     *
     * @param sFileInfo 文件信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SFileInfo sFileInfo) {
        return sFileInfoService.updateById(sFileInfo);
    }

    /**
     * 查询所有文件信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SFileInfo> list() {
        return sFileInfoService.list();
    }

    /**
     * 根据主键获取文件信息。
     *
     * @param id 文件信息主键
     * @return 文件信息详情
     */
    @GetMapping("getInfo/{id}")
    public SFileInfo getInfo(@PathVariable String id) {
        return sFileInfoService.getById(id);
    }

    /**
     * 分页查询文件信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SFileInfo> page(Page<SFileInfo> page) {
        return sFileInfoService.page(page);
    }

}
