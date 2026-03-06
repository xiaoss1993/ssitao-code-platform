package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.api.dto.IamGroupDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupUpdateCommand;

import java.util.List;

/**
 * IAM集团应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamGroupAppService {

    /**
     * 创建集团
     *
     * @param command 创建命令
     * @return 集团ID
     */
    String createGroup(IamGroupCreateCommand command);

    /**
     * 更新集团
     *
     * @param command 更新命令
     */
    void updateGroup(IamGroupUpdateCommand command);

    /**
     * 删除集团
     *
     * @param id 集团ID
     */
    void deleteGroup(String id);

    /**
     * 获取集团详情
     *
     * @param id 集团ID
     * @return 集团DTO
     */
    IamGroupDTO getGroup(String id);

    /**
     * 获取集团列表
     *
     * @param tenantId 租户ID
     * @return 集团列表
     */
    List<IamGroupDTO> listGroups(String tenantId);
}
