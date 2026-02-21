package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.application.command.IamPostCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamPostUpdateCommand;
import com.ssitao.code.modular.iam.organization.api.dto.IamPostDTO;

import java.util.List;

/**
 * IAM岗位应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPostAppService {

    /**
     * 创建岗位
     *
     * @param command 创建命令
     * @return 岗位ID
     */
    Long createPost(IamPostCreateCommand command);

    /**
     * 批量创建岗位
     *
     * @param commands 创建命令列表
     * @return 岗位ID列表
     */
    List<Long> batchCreatePosts(List<IamPostCreateCommand> commands);

    /**
     * 更新岗位
     *
     * @param command 更新命令
     */
    void updatePost(IamPostUpdateCommand command);

    /**
     * 删除岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     */
    void deletePost(Long id, String tenantId);

    /**
     * 根据ID获取岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     * @return 岗位DTO
     */
    IamPostDTO getPostById(Long id, String tenantId);

    /**
     * 获取岗位列表
     *
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<IamPostDTO> listPosts(String tenantId);

    /**
     * 根据部门ID获取岗位
     *
     * @param deptId   部门ID
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<IamPostDTO> listPostsByDeptId(Long deptId, String tenantId);

    /**
     * 启用岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     */
    void enablePost(Long id, String tenantId);

    /**
     * 禁用岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     */
    void disablePost(Long id, String tenantId);

}
