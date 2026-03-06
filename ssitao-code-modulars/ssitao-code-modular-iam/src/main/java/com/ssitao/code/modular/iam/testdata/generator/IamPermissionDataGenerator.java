package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 权限测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamPermissionDataGenerator {

    private static final String[] PERMISSION_NAMES = {"查看", "新增", "编辑", "删除", "导出", "导入", "审批", "分配", "审核", "发布"};
    private static final String[] RESOURCE_NAMES = {"user", "role", "department", "menu", "permission", "company", "post", "dict", "config", "log"};
    private static final List<String> TYPE_LIST = Arrays.asList("MENU", "BUTTON", "API", "DATA");
    private static final List<String> ACTION_LIST = Arrays.asList("query", "create", "update", "delete", "export", "import", "approve", "assign", "audit", "publish");

    /**
     * 生成权限数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 权限列表
     */
    public List<IamPermissionDO> generate(String tenantId, int count) {
        List<IamPermissionDO> permissions = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String resource = RandomUtil.randomEle(RESOURCE_NAMES);
            String action = RandomUtil.randomEle(ACTION_LIST);

            IamPermissionDO permission = IamPermissionDO.builder()
                    .permissionId(generateId())
                    .permissionCode(resource + ":" + action)
                    .permissionName(RandomUtil.randomEle(PERMISSION_NAMES))
                    .permissionType(RandomUtil.randomEle(TYPE_LIST))
                    .permissionResource(resource)
                    .permissionAction(action)
                    .permissionDesc("测试权限描述" + (i + 1))
                    .permissionStatus(RandomUtil.randomInt(0, 2))
                    .permissionIsBuiltin(0)
                    .permissionSort(i * 10)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .build();
            permissions.add(permission);
        }
        return permissions;
    }

    private String generateId() {
        return "PERM" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
