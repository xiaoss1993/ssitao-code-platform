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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ReleaseHistory;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ReleaseHistoryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 发布历史记录 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/releaseHistory")
public class ReleaseHistoryController {

    @Autowired
    private ReleaseHistoryService releaseHistoryService;

    /**
     * 保存发布历史记录。
     *
     * @param releaseHistory 发布历史记录
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody ReleaseHistory releaseHistory) {
        return releaseHistoryService.save(releaseHistory);
    }

    /**
     * 根据主键删除发布历史记录。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return releaseHistoryService.removeById(id);
    }

    /**
     * 根据主键更新发布历史记录。
     *
     * @param releaseHistory 发布历史记录
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody ReleaseHistory releaseHistory) {
        return releaseHistoryService.updateById(releaseHistory);
    }

    /**
     * 查询所有发布历史记录。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<ReleaseHistory> list() {
        return releaseHistoryService.list();
    }

    /**
     * 根据主键获取发布历史记录。
     *
     * @param id 发布历史记录主键
     * @return 发布历史记录详情
     */
    @GetMapping("getInfo/{id}")
    public ReleaseHistory getInfo(@PathVariable String id) {
        return releaseHistoryService.getById(id);
    }

    /**
     * 分页查询发布历史记录。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<ReleaseHistory> page(Page<ReleaseHistory> page) {
        return releaseHistoryService.page(page);
    }

}
