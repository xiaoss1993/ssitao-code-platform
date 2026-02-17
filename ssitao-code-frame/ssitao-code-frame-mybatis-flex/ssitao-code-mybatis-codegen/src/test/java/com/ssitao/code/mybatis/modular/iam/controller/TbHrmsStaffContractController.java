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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffContract;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffContractService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工合同信息 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbHrmsStaffContract")
public class TbHrmsStaffContractController {

    @Autowired
    private TbHrmsStaffContractService tbHrmsStaffContractService;

    /**
     * 保存员工合同信息。
     *
     * @param tbHrmsStaffContract 员工合同信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffContract tbHrmsStaffContract) {
        return tbHrmsStaffContractService.save(tbHrmsStaffContract);
    }

    /**
     * 根据主键删除员工合同信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffContractService.removeById(id);
    }

    /**
     * 根据主键更新员工合同信息。
     *
     * @param tbHrmsStaffContract 员工合同信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffContract tbHrmsStaffContract) {
        return tbHrmsStaffContractService.updateById(tbHrmsStaffContract);
    }

    /**
     * 查询所有员工合同信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffContract> list() {
        return tbHrmsStaffContractService.list();
    }

    /**
     * 根据主键获取员工合同信息。
     *
     * @param id 员工合同信息主键
     * @return 员工合同信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffContract getInfo(@PathVariable String id) {
        return tbHrmsStaffContractService.getById(id);
    }

    /**
     * 分页查询员工合同信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffContract> page(Page<TbHrmsStaffContract> page) {
        return tbHrmsStaffContractService.page(page);
    }

}
