package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.api.dto.IamUserOrgDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamUserOrgCreateCommand;

import java.util.List;

/**
 * IAM用户组织应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamUserOrgAppService {

    /**
     * 分配用户组织
     *
     * @param command 用户组织创建命令
     * @return 用户组织ID
     */
    String assignUserOrg(IamUserOrgCreateCommand command);

    /**
     * 获取用户组织列表
     *
     * @param userId 用户ID
     * @return 用户组织列表
     */
    List<IamUserOrgDTO> getUserOrgs(String userId);
}
