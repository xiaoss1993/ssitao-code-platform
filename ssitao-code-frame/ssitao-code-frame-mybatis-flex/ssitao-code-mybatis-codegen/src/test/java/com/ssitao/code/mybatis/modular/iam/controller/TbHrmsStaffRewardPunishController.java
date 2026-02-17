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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffRewardPunish;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffRewardPunishService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工奖惩信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbHrmsStaffRewardPunish")
public class TbHrmsStaffRewardPunishController {

    @Autowired
    private TbHrmsStaffRewardPunishService tbHrmsStaffRewardPunishService;

    /**
     * 保存员工奖惩信息。
     *
     * @param tbHrmsStaffRewardPunish 员工奖惩信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffRewardPunish tbHrmsStaffRewardPunish) {
        return tbHrmsStaffRewardPunishService.save(tbHrmsStaffRewardPunish);
    }

    /**
     * 根据主键删除员工奖惩信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffRewardPunishService.removeById(id);
    }

    /**
     * 根据主键更新员工奖惩信息。
     *
     * @param tbHrmsStaffRewardPunish 员工奖惩信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffRewardPunish tbHrmsStaffRewardPunish) {
        return tbHrmsStaffRewardPunishService.updateById(tbHrmsStaffRewardPunish);
    }

    /**
     * 查询所有员工奖惩信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffRewardPunish> list() {
        return tbHrmsStaffRewardPunishService.list();
    }

    /**
     * 根据主键获取员工奖惩信息。
     *
     * @param id 员工奖惩信息主键
     * @return 员工奖惩信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffRewardPunish getInfo(@PathVariable String id) {
        return tbHrmsStaffRewardPunishService.getById(id);
    }

    /**
     * 分页查询员工奖惩信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffRewardPunish> page(Page<TbHrmsStaffRewardPunish> page) {
        return tbHrmsStaffRewardPunishService.page(page);
    }

}
