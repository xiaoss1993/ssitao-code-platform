package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * IAM用户组织关联
 * 支持五级组织架构：集团 -> 公司 -> 部门 -> 岗位 -> 用户
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamUserOrg {

    private String id;
    private String userId;          // 用户ID
    private String groupId;         // 集团ID
    private String companyId;       // 公司ID
    private String deptId;          // 部门ID
    private String postId;          // 岗位ID
    private Boolean isMain;         // 是否主组织(1是0否)
    private String tenantId;        // 租户ID
    private LocalDateTime createTime;
    private String creator;
    private Boolean deleted;

    public static IamUserOrg create(String userId, String companyId, String deptId, String postId, Boolean isMain, String tenantId) {
        IamUserOrg userOrg = new IamUserOrg();
        userOrg.setUserId(userId);
        userOrg.setCompanyId(companyId);
        userOrg.setDeptId(deptId);
        userOrg.setPostId(postId);
        userOrg.setIsMain(isMain);
        userOrg.setTenantId(tenantId);
        userOrg.setDeleted(false);
        userOrg.setCreateTime(LocalDateTime.now());
        return userOrg;
    }
}
