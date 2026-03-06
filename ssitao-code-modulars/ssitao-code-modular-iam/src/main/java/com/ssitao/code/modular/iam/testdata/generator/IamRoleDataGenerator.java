package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamRoleDataGenerator {

    private static final String[] ROLE_NAMES = {"管理员", "普通用户", "超级管理员", "部门经理", "项目经理", "财务人员", "hr", "运维人员", "开发人员", "测试人员"};

    /**
     * 生成角色数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 角色列表
     */
    public List<IamRoleDO> generate(String tenantId, int count) {
        List<IamRoleDO> roles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamRoleDO role = IamRoleDO.builder()
                    .roleId(generateId())
                    .roleCode("ROLE" + RandomUtil.randomNumbers(6))
                    .roleName(RandomUtil.randomEle(ROLE_NAMES) + (i + 1))
                    .roleType(RandomUtil.randomBoolean() ? "SYSTEM" : "BUSINESS")
                    .roleLevel(RandomUtil.randomInt(1, 10))
                    .roleDesc("测试角色描述" + (i + 1))
                    .roleStatus(RandomUtil.randomInt(0, 2))
                    .roleIsBuiltin(0)
                    .roleSort(i * 10)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            roles.add(role);
        }
        return roles;
    }

    private String generateId() {
        return "ROLE" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
