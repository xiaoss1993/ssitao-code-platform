package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DeptDO;
import com.ssitao.code.modular.iam.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * 部门管理 Controller
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "部门管理")
@RestController
@RequestMapping("/iam/dept")
@RequiredArgsConstructor
@Validated
public class DeptController {

    private final DeptService deptService;

    @PostMapping("/create")
    @Operation(summary = "创建部门")
    public CommonResult<Long> createDept(@Valid @RequestBody DeptCreateReqVO createReqVO) {
        return success(deptService.createDept(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新部门")
    public CommonResult<Void> updateDept(@Valid @RequestBody DeptUpdateReqVO updateReqVO) {
        deptService.updateDept(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除部门")
    @Parameter(name = "id", description = "部门ID", required = true)
    public CommonResult<Void> deleteDept(@PathVariable Long id) {
        deptService.deleteDept(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取部门详情")
    @Parameter(name = "id", description = "部门ID", required = true)
    public CommonResult<DeptDO> getDept(@PathVariable Long id) {
        return success(deptService.getDept(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获取部门列表")
    public CommonResult<List<DeptDO>> getDeptList(DeptListReqVO reqVO) {
        return success(deptService.getDeptList(reqVO));
    }

    @GetMapping("/tree")
    @Operation(summary = "获取部门树")
    public CommonResult<List<DeptDO>> getDeptTree() {
        return success(deptService.getDeptTree());
    }

}
