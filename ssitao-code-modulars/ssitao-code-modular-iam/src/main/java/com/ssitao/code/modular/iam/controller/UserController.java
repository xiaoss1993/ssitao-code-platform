package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.user.UserCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserListReqVO;
import com.ssitao.code.modular.iam.controller.vo.user.UserUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.UserDO;
import com.ssitao.code.modular.iam.service.UserService;
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
 * 用户管理 Controller
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/iam/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建用户")
    public CommonResult<Long> createUser(@Valid @RequestBody UserCreateReqVO createReqVO) {
        return success(userService.createUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户")
    public CommonResult<Void> updateUser(@Valid @RequestBody UserUpdateReqVO updateReqVO) {
        userService.updateUser(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除用户")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取用户详情")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<UserDO> getUser(@PathVariable Long id) {
        return success(userService.getUser(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获取用户列表")
    public CommonResult<List<UserDO>> getUserList(UserListReqVO reqVO) {
        return success(userService.getUserList(reqVO));
    }

    @PutMapping("/status/{id}")
    @Operation(summary = "更新用户状态")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<Void> updateUserStatus(
            @PathVariable Long id,
            @Parameter(description = "状态", required = true)
            @RequestParam Integer status) {
        userService.updateUserStatus(id, status);
        return success();
    }

    @PutMapping("/password/{id}")
    @Operation(summary = "更新用户密码")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<Void> updateUserPassword(
            @PathVariable Long id,
            @Parameter(description = "密码", required = true)
            @RequestParam String password) {
        userService.updateUserPassword(id, password);
        return success();
    }

    @PutMapping("/roles/{id}")
    @Operation(summary = "分配用户角色")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<Void> assignRoles(
            @PathVariable Long id,
            @Parameter(description = "角色ID列表", required = true)
            @RequestBody List<Long> roleIds) {
        userService.assignRoles(id, roleIds);
        return success();
    }

    @GetMapping("/roles/{id}")
    @Operation(summary = "获取用户角色列表")
    @Parameter(name = "id", description = "用户ID", required = true)
    public CommonResult<List<Long>> getUserRoles(@PathVariable Long id) {
        return success(userService.getUserRoles(id));
    }

}
