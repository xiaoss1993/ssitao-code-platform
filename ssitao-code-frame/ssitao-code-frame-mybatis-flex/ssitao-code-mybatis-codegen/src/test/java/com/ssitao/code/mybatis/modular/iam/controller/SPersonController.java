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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SPerson;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SPersonService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 人员 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sPerson")
public class SPersonController {

    @Autowired
    private SPersonService sPersonService;

    /**
     * 保存人员。
     *
     * @param sPerson 人员
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SPerson sPerson) {
        return sPersonService.save(sPerson);
    }

    /**
     * 根据主键删除人员。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sPersonService.removeById(id);
    }

    /**
     * 根据主键更新人员。
     *
     * @param sPerson 人员
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SPerson sPerson) {
        return sPersonService.updateById(sPerson);
    }

    /**
     * 查询所有人员。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SPerson> list() {
        return sPersonService.list();
    }

    /**
     * 根据主键获取人员。
     *
     * @param id 人员主键
     * @return 人员详情
     */
    @GetMapping("getInfo/{id}")
    public SPerson getInfo(@PathVariable String id) {
        return sPersonService.getById(id);
    }

    /**
     * 分页查询人员。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SPerson> page(Page<SPerson> page) {
        return sPersonService.page(page);
    }

}
