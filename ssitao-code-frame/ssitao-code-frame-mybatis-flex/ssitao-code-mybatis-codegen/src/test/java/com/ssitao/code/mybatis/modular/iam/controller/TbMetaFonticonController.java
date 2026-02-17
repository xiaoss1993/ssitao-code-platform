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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaFonticon;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaFonticonService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 字体图标库 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaFonticon")
public class TbMetaFonticonController {

    @Autowired
    private TbMetaFonticonService tbMetaFonticonService;

    /**
     * 保存字体图标库。
     *
     * @param tbMetaFonticon 字体图标库
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaFonticon tbMetaFonticon) {
        return tbMetaFonticonService.save(tbMetaFonticon);
    }

    /**
     * 根据主键删除字体图标库。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaFonticonService.removeById(id);
    }

    /**
     * 根据主键更新字体图标库。
     *
     * @param tbMetaFonticon 字体图标库
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaFonticon tbMetaFonticon) {
        return tbMetaFonticonService.updateById(tbMetaFonticon);
    }

    /**
     * 查询所有字体图标库。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaFonticon> list() {
        return tbMetaFonticonService.list();
    }

    /**
     * 根据主键获取字体图标库。
     *
     * @param id 字体图标库主键
     * @return 字体图标库详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaFonticon getInfo(@PathVariable String id) {
        return tbMetaFonticonService.getById(id);
    }

    /**
     * 分页查询字体图标库。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaFonticon> page(Page<TbMetaFonticon> page) {
        return tbMetaFonticonService.page(page);
    }

}
