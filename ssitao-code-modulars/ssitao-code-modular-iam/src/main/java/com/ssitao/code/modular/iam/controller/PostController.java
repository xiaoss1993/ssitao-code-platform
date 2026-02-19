package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.controller.vo.post.PostCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostListReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PostDO;
import com.ssitao.code.modular.iam.service.PostService;
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
 * 岗位管理 Controller
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Tag(name = "岗位管理")
@RestController
@RequestMapping("/iam/post")
@RequiredArgsConstructor
@Validated
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    @Operation(summary = "创建岗位")
    public CommonResult<Long> createPost(@Valid @RequestBody PostCreateReqVO createReqVO) {
        return success(postService.createPost(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新岗位")
    public CommonResult<Void> updatePost(@Valid @RequestBody PostUpdateReqVO updateReqVO) {
        postService.updatePost(updateReqVO);
        return success();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除岗位")
    @Parameter(name = "id", description = "岗位ID", required = true)
    public CommonResult<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return success();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取岗位详情")
    @Parameter(name = "id", description = "岗位ID", required = true)
    public CommonResult<PostDO> getPost(@PathVariable Long id) {
        return success(postService.getPost(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获取岗位列表")
    public CommonResult<List<PostDO>> getPostList(PostListReqVO reqVO) {
        return success(postService.getPostList(reqVO));
    }

}
