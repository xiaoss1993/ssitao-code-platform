package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.post.PostCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostListReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PostDO;

import java.util.List;

/**
 * 岗位服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface PostService {

    /**
     * 创建岗位
     *
     * @param createReqVO 创建请求
     * @return 岗位ID
     */
    Long createPost(PostCreateReqVO createReqVO);

    /**
     * 更新岗位
     *
     * @param updateReqVO 更新请求
     */
    void updatePost(PostUpdateReqVO updateReqVO);

    /**
     * 删除岗位
     *
     * @param id 岗位ID
     */
    void deletePost(Long id);

    /**
     * 获取岗位详情
     *
     * @param id 岗位ID
     * @return 岗位信息
     */
    PostDO getPost(Long id);

    /**
     * 获取岗位列表
     *
     * @param reqVO 查询请求
     * @return 岗位列表
     */
    List<PostDO> getPostList(PostListReqVO reqVO);

}
