package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.post.PostCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostListReqVO;
import com.ssitao.code.modular.iam.controller.vo.post.PostUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PostDO;
import com.ssitao.code.modular.iam.dal.mapper.PostMapper;
import com.ssitao.code.modular.iam.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 岗位服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPost(PostCreateReqVO createReqVO) {
        // 检查岗位编码是否已存在
        PostDO existPost = postMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("code", createReqVO.getCode())
                        .eq("deleted", 0)
        );
        if (existPost != null) {
            throw new BusinessException("岗位编码已存在: " + createReqVO.getCode());
        }

        // 创建岗位
        PostDO post = new PostDO();
        post.setCode(createReqVO.getCode());
        post.setName(createReqVO.getName());
        post.setSort(createReqVO.getSort());
        post.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : 1);
        post.setRemark(createReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            post.setTenantId(loginUser.getTenantId());
            post.setCreator(loginUser.getUsername());
        }
        post.setCreateTime(LocalDateTime.now());
        post.setUpdateTime(LocalDateTime.now());
        post.setDeleted(0);

        postMapper.insert(post);
        log.info("创建岗位成功: code={}, name={}", post.getCode(), post.getName());
        return post.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePost(PostUpdateReqVO updateReqVO) {
        PostDO post = postMapper.selectOneById(updateReqVO.getId());
        if (post == null || post.getDeleted() == 1) {
            throw new NotFoundException("岗位不存在");
        }

        // 检查岗位编码是否被其他岗位使用
        if (!post.getCode().equals(updateReqVO.getCode())) {
            PostDO existPost = postMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq("code", updateReqVO.getCode())
                            .ne("id", updateReqVO.getId())
                            .eq("deleted", 0)
            );
            if (existPost != null) {
                throw new BusinessException("岗位编码已存在: " + updateReqVO.getCode());
            }
        }

        // 更新岗位
        post.setCode(updateReqVO.getCode());
        post.setName(updateReqVO.getName());
        post.setSort(updateReqVO.getSort());
        if (updateReqVO.getStatus() != null) {
            post.setStatus(updateReqVO.getStatus());
        }
        post.setRemark(updateReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            post.setUpdater(loginUser.getUsername());
        }
        post.setUpdateTime(LocalDateTime.now());

        postMapper.update(post);
        log.info("更新岗位成功: id={}, code={}, name={}", post.getId(), post.getCode(), post.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long id) {
        PostDO post = postMapper.selectOneById(id);
        if (post == null || post.getDeleted() == 1) {
            throw new NotFoundException("岗位不存在");
        }

        // 逻辑删除
        post.setDeleted(1);
        post.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            post.setUpdater(loginUser.getUsername());
        }

        postMapper.update(post);
        log.info("删除岗位成功: id={}, code={}, name={}", post.getId(), post.getCode(), post.getName());
    }

    @Override
    public PostDO getPost(Long id) {
        PostDO post = postMapper.selectOneById(id);
        if (post == null || post.getDeleted() == 1) {
            throw new NotFoundException("岗位不存在");
        }
        return post;
    }

    @Override
    public List<PostDO> getPostList(PostListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (reqVO.getCode() != null && !reqVO.getCode().isEmpty()) {
            queryWrapper.like("code", reqVO.getCode());
        }
        if (reqVO.getName() != null && !reqVO.getName().isEmpty()) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("sort", true).orderBy("id", true);
        return postMapper.selectListByQuery(queryWrapper);
    }

}
