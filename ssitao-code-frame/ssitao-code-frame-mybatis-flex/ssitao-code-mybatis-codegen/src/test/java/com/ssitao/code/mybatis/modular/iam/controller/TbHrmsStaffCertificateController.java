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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffCertificate;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffCertificateService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工证书 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbHrmsStaffCertificate")
public class TbHrmsStaffCertificateController {

    @Autowired
    private TbHrmsStaffCertificateService tbHrmsStaffCertificateService;

    /**
     * 保存员工证书。
     *
     * @param tbHrmsStaffCertificate 员工证书
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffCertificate tbHrmsStaffCertificate) {
        return tbHrmsStaffCertificateService.save(tbHrmsStaffCertificate);
    }

    /**
     * 根据主键删除员工证书。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffCertificateService.removeById(id);
    }

    /**
     * 根据主键更新员工证书。
     *
     * @param tbHrmsStaffCertificate 员工证书
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffCertificate tbHrmsStaffCertificate) {
        return tbHrmsStaffCertificateService.updateById(tbHrmsStaffCertificate);
    }

    /**
     * 查询所有员工证书。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffCertificate> list() {
        return tbHrmsStaffCertificateService.list();
    }

    /**
     * 根据主键获取员工证书。
     *
     * @param id 员工证书主键
     * @return 员工证书详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffCertificate getInfo(@PathVariable String id) {
        return tbHrmsStaffCertificateService.getById(id);
    }

    /**
     * 分页查询员工证书。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffCertificate> page(Page<TbHrmsStaffCertificate> page) {
        return tbHrmsStaffCertificateService.page(page);
    }

}
