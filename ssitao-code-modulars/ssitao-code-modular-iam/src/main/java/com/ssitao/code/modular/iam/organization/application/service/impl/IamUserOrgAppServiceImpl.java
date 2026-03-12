package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.modular.iam.organization.api.dto.IamUserOrgDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamUserOrgCreateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamUserOrgAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * IAM用户组织应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IamUserOrgAppServiceImpl implements IamUserOrgAppService {

    /**
     * 模拟数据库存储 - key: userId, value: list of user orgs
     */
    private final Map<String, List<IamUserOrgDTO>> userOrgStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public IamUserOrgAppServiceImpl() {
        // 初始化一些模拟数据
        initMockData();
    }

    private void initMockData() {
        // 模拟用户1的组织
        List<IamUserOrgDTO> user1Orgs = new ArrayList<>();
        user1Orgs.add(IamUserOrgDTO.builder()
                .id("user-org-1")
                .userId("user-1")
                .groupId("group-1")
                .groupName("集团A")
                .companyId("company-1")
                .companyName("公司A")
                .deptId("dept-1")
                .deptName("技术部")
                .postId("post-1")
                .postName("开发工程师")
                .isMain(true)
                .tenantId("default")
                .createTime(LocalDateTime.now())
                .creator("system")
                .build());
        user1Orgs.add(IamUserOrgDTO.builder()
                .id("user-org-2")
                .userId("user-1")
                .groupId("group-1")
                .groupName("集团A")
                .companyId("company-1")
                .companyName("公司A")
                .deptId("dept-2")
                .deptName("测试部")
                .postId("post-2")
                .postName("测试工程师")
                .isMain(false)
                .tenantId("default")
                .createTime(LocalDateTime.now())
                .creator("system")
                .build());
        userOrgStore.put("user-1", user1Orgs);

        // 模拟用户2的组织
        List<IamUserOrgDTO> user2Orgs = new ArrayList<>();
        user2Orgs.add(IamUserOrgDTO.builder()
                .id("user-org-3")
                .userId("user-2")
                .groupId("group-2")
                .groupName("集团B")
                .companyId("company-2")
                .companyName("公司B")
                .deptId("dept-3")
                .deptName("销售部")
                .postId("post-3")
                .postName("销售经理")
                .isMain(true)
                .tenantId("default")
                .createTime(LocalDateTime.now())
                .creator("system")
                .build());
        userOrgStore.put("user-2", user2Orgs);
    }

    @Override
    public String assignUserOrg(IamUserOrgCreateCommand command) {
        String id = "user-org-" + idGenerator.getAndIncrement();

        IamUserOrgDTO userOrg = IamUserOrgDTO.builder()
                .id(id)
                .userId(command.getUserId())
                .groupId(command.getGroupId())
                .companyId(command.getCompanyId())
                .deptId(command.getDeptId())
                .postId(command.getPostId())
                .isMain(command.getIsMain())
                .tenantId(command.getTenantId())
                .createTime(LocalDateTime.now())
                .creator(command.getCreator())
                .build();

        // 获取用户的现有组织列表
        List<IamUserOrgDTO> userOrgs = userOrgStore.computeIfAbsent(command.getUserId(), k -> new ArrayList<>());

        // 如果是主组织，取消其他主组织
        if (Boolean.TRUE.equals(command.getIsMain())) {
            for (IamUserOrgDTO org : userOrgs) {
                org.setIsMain(false);
            }
        }

        userOrgs.add(userOrg);

        return id;
    }

    @Override
    public List<IamUserOrgDTO> getUserOrgs(String userId) {
        return userOrgStore.getOrDefault(userId, new ArrayList<>());
    }
}
