package com.ssitao.code.modular.iam.organization.application.service.impl;

import com.ssitao.code.modular.iam.organization.api.dto.IamGroupDTO;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupCreateCommand;
import com.ssitao.code.modular.iam.organization.application.command.IamGroupUpdateCommand;
import com.ssitao.code.modular.iam.organization.application.service.IamGroupAppService;
import com.ssitao.code.modular.iam.organization.domain.repository.IamGroupRepository;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * IAM集团应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IamGroupAppServiceImpl implements IamGroupAppService {

    @Resource
    private IamGroupRepository groupRepository;

    /**
     * 模拟数据库存储
     */
    private final Map<String, IamGroupDTO> groupStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public IamGroupAppServiceImpl() {
        // 初始化一些模拟数据
        initMockData();
    }

    private void initMockData() {
        IamGroupDTO group1 = IamGroupDTO.builder()
                .id("group-1")
                .groupCode("GROUP001")
                .groupName("集团A")
                .leader("张三")
                .phone("13800138000")
                .email("zhangsan@example.com")
                .sortOrder(1)
                .status(true)
                .tenantId("default")
                .remark("这是集团A")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .creator("system")
                .updater("system")
                .build();

        IamGroupDTO group2 = IamGroupDTO.builder()
                .id("group-2")
                .groupCode("GROUP002")
                .groupName("集团B")
                .leader("李四")
                .phone("13800138001")
                .email("lisi@example.com")
                .sortOrder(2)
                .status(true)
                .tenantId("default")
                .remark("这是集团B")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .creator("system")
                .updater("system")
                .build();

        groupStore.put(group1.getId(), group1);
        groupStore.put(group2.getId(), group2);
    }

    @Override
    public String createGroup(IamGroupCreateCommand command) {
        String id = "group-" + idGenerator.getAndIncrement();
        IamGroupDTO group = IamGroupDTO.builder()
                .id(id)
                .groupCode(command.getGroupCode())
                .groupName(command.getGroupName())
                .leader(command.getLeader())
                .phone(command.getPhone())
                .email(command.getEmail())
                .sortOrder(command.getSortOrder())
                .status(true)
                .tenantId(command.getTenantId())
                .remark(command.getRemark())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .creator(command.getCreator())
                .updater(command.getCreator())
                .build();
        groupStore.put(id, group);
        return id;
    }

    @Override
    public void updateGroup(IamGroupUpdateCommand command) {
        IamGroupDTO existing = groupStore.get(command.getId());
        if (existing == null) {
            throw new IllegalArgumentException("集团不存在: " + command.getId());
        }

        existing.setGroupCode(command.getGroupCode());
        existing.setGroupName(command.getGroupName());
        existing.setLeader(command.getLeader());
        existing.setPhone(command.getPhone());
        existing.setEmail(command.getEmail());
        existing.setSortOrder(command.getSortOrder());
        existing.setStatus(command.getStatus());
        existing.setRemark(command.getRemark());
        existing.setUpdater(command.getUpdater());
        existing.setUpdateTime(LocalDateTime.now());

        groupStore.put(command.getId(), existing);
    }

    @Override
    public void deleteGroup(String id) {
        groupStore.remove(id);
    }

    @Override
    public IamGroupDTO getGroup(String id) {
        return groupStore.get(id);
    }

    @Override
    public List<IamGroupDTO> listGroups(String tenantId) {
        List<IamGroupDTO> result = new ArrayList<>(groupStore.values());

        // Filter by tenantId if provided and not "default"
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            result = result.stream()
                    .filter(g -> tenantId.equals(g.getTenantId()))
                    .collect(Collectors.toList());
        }

        return result;
    }
}
