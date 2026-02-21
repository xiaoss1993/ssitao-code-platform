package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.modular.iam.organization.application.command.IamPostCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamPostAppService;
import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import com.ssitao.code.modular.iam.organization.domain.repository.IamPostRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamPostConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM岗位应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamPostAppServiceImpl implements IamPostAppService {

    @Resource
    private IamPostRepository postRepository;

    @Resource
    private IamPostConverter postConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPost(IamPostCreateCommand command) {
        // 检查岗位编码是否已存在
        if (postRepository.existsByPostCode(command.getPostCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("岗位编码已存在: " + command.getPostCode());
        }

        // 创建岗位聚合根
        IamPost post = IamPost.create(
                command.getPostCode(),
                command.getPostName(),
                command.getTenantId()
        );

        // 设置ID
        post.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        post.setPostLevel(command.getPostLevel());
        post.setPostCategory(command.getPostCategory());
        post.setSortOrder(command.getSortOrder());
        post.setRemark(command.getRemark());

        // 设置所属部门
        if (command.getDeptId() != null) {
            post.setDeptId(command.getDeptId().toString());
        }

        String id = postRepository.save(post);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreatePosts(List<IamPostCreateCommand> commands) {
        return commands.stream()
                .map(this::createPost)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePost(IamPostUpdateCommand command) {
        IamPost post = postRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + command.getId()));

        // 更新属性
        if (command.getPostName() != null) {
            post.setPostName(command.getPostName());
        }
        post.setPostLevel(command.getPostLevel());
        post.setPostCategory(command.getPostCategory());
        post.setSortOrder(command.getSortOrder());
        post.setRemark(command.getRemark());

        if (command.getDeptId() != null) {
            post.setDeptId(command.getDeptId().toString());
        }

        post.setUpdateTime(LocalDateTime.now());
        postRepository.update(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePost(Long id, String tenantId) {
        postRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamPostDTO getPostById(Long id, String tenantId) {
        IamPost post = postRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        return postConverter.toDTO(post);
    }

    @Override
    public List<IamPostDTO> listPosts(String tenantId) {
        List<IamPost> posts = postRepository.findAll(tenantId);
        return posts.stream()
                .map(postConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IamPostDTO> listPostsByDeptId(Long deptId, String tenantId) {
        List<IamPost> posts = postRepository.findByDeptId(deptId.toString(), tenantId);
        return posts.stream()
                .map(postConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enablePost(Long id, String tenantId) {
        IamPost post = postRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        post.enable();
        postRepository.update(post);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disablePost(Long id, String tenantId) {
        IamPost post = postRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        post.disable();
        postRepository.update(post);
    }
}
