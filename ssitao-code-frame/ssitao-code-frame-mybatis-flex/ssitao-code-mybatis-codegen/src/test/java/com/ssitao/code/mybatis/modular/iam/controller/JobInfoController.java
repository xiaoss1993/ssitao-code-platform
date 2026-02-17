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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.JobInfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.JobInfoService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 作业信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/jobInfo")
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    /**
     * 保存作业信息。
     *
     * @param jobInfo 作业信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody JobInfo jobInfo) {
        return jobInfoService.save(jobInfo);
    }

    /**
     * 根据主键删除作业信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return jobInfoService.removeById(id);
    }

    /**
     * 根据主键更新作业信息。
     *
     * @param jobInfo 作业信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody JobInfo jobInfo) {
        return jobInfoService.updateById(jobInfo);
    }

    /**
     * 查询所有作业信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<JobInfo> list() {
        return jobInfoService.list();
    }

    /**
     * 根据主键获取作业信息。
     *
     * @param id 作业信息主键
     * @return 作业信息详情
     */
    @GetMapping("getInfo/{id}")
    public JobInfo getInfo(@PathVariable String id) {
        return jobInfoService.getById(id);
    }

    /**
     * 分页查询作业信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<JobInfo> page(Page<JobInfo> page) {
        return jobInfoService.page(page);
    }

}
