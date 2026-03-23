package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysPostDTO;
import com.ssitao.code.modular.iam.application.command.CreatePostCommand;
import com.ssitao.code.modular.iam.application.command.DeletePostCommand;
import com.ssitao.code.modular.iam.application.command.UpdatePostCommand;
import com.ssitao.code.modular.iam.domain.SysPost;
import com.ssitao.code.modular.iam.domain.model.SysPostAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysPostRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysPostConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 岗位应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysPostApplicationService {

    private final SysPostRepository postRepository;
    private final SysPostConverter postConverter;
    private final ISysPostService postService;

    /**
     * 查询岗位列表
     */
    public List<SysPostDTO> listPosts(SysPostDTO query) {
        SysPost post = new SysPost();
        if (query != null && query.getPostCode() != null) {
            post.setPostCode(query.getPostCode());
        }
        if (query != null && query.getPostName() != null) {
            post.setPostName(query.getPostName());
        }
        if (query != null && query.getStatus() != null) {
            post.setStatus(query.getStatus());
        }
        List<SysPost> list = postService.selectPostList(post);
        return postConverter.toDTOListFromPost(list);
    }

    /**
     * 根据ID查询岗位
     */
    public SysPostDTO getPostById(Long postId) {
        SysPostAggregate aggregate = postRepository.findOne(postId);
        if (aggregate == null) {
            throw new ServiceException("岗位不存在");
        }
        return postConverter.toDTO(aggregate);
    }

    /**
     * 查询所有正常状态的岗位
     */
    public List<SysPostDTO> listAllNormalPosts() {
        List<SysPost> posts = postService.selectPostAll();
        return postConverter.toDTOListFromPost(posts);
    }

    /**
     * 创建岗位
     */
    @Transactional
    public Long createPost(CreatePostCommand command) {
        // 检查岗位编码是否已存在
        if (postRepository.existsByPostCode(command.getPostCode())) {
            throw new ServiceException("岗位编码已存在");
        }

        // 检查岗位名称是否已存在
        if (postRepository.existsByPostName(command.getPostName())) {
            throw new ServiceException("岗位名称已存在");
        }

        // 创建岗位聚合根
        SysPostAggregate aggregate = new SysPostAggregate();
        aggregate.createPost(
                command.getPostCode(),
                command.getPostName(),
                command.getPostSort(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        postRepository.save(aggregate);

        log.info("创建岗位: {}", aggregate.getPostId());
        return aggregate.getPostId();
    }

    /**
     * 更新岗位
     */
    @Transactional
    public void updatePost(UpdatePostCommand command) {
        SysPostAggregate aggregate = postRepository.findOne(command.getPostId());
        if (aggregate == null) {
            throw new ServiceException("岗位不存在");
        }

        // 检查岗位编码是否已存在（排除自己）
        SysPostAggregate existingByCode = postRepository.findByPostCode(command.getPostCode());
        if (existingByCode != null && !existingByCode.getPostId().equals(command.getPostId())) {
            throw new ServiceException("岗位编码已存在");
        }

        // 检查岗位名称是否已存在（排除自己）
        SysPostAggregate existingByName = postRepository.findByPostName(command.getPostName());
        if (existingByName != null && !existingByName.getPostId().equals(command.getPostId())) {
            throw new ServiceException("岗位名称已存在");
        }

        // 更新岗位信息
        aggregate.updatePost(
                command.getPostCode(),
                command.getPostName(),
                command.getPostSort(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        postRepository.save(aggregate);

        log.info("更新岗位: {}", command.getPostId());
    }

    /**
     * 删除岗位
     */
    @Transactional
    public void deletePosts(DeletePostCommand command) {
        for (Long postId : command.getPostIds()) {
            SysPostAggregate aggregate = postRepository.findOne(postId);
            if (aggregate != null) {
                // 检查是否有用户使用此岗位
                if (postService.countUserPostById(postId) > 0) {
                    throw new ServiceException("岗位已被用户使用，不允许删除");
                }
                postRepository.delete(aggregate);
                log.info("删除岗位: {}", postId);
            }
        }
    }

    /**
     * 修改岗位状态
     */
    @Transactional
    public void changeStatus(Long postId, String status) {
        SysPostAggregate aggregate = postRepository.findOne(postId);
        if (aggregate == null) {
            throw new ServiceException("岗位不存在");
        }

        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        postRepository.save(aggregate);

        log.info("修改岗位状态: {} -> {}", postId, status);
    }
}
