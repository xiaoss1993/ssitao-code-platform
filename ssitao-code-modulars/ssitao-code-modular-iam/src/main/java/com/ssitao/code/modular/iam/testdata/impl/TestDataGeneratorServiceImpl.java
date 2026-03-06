package com.ssitao.code.modular.iam.testdata.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermissionMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRoleMapper;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import com.ssitao.code.modular.iam.identity.dal.mapper.IamAccountMapper;
import com.ssitao.code.modular.iam.menu.dal.dataobject.IamMenuDO;
import com.ssitao.code.modular.iam.menu.dal.mapper.IamMenuMapper;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamCompanyDO;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDepartmentDO;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamPostDO;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamCompanyMapper;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamDepartmentMapper;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamPostMapper;
import com.ssitao.code.modular.iam.enums.IamDataTypeEnum;
import com.ssitao.code.modular.iam.testdata.TestDataGeneratorService;
import com.ssitao.code.modular.iam.testdata.generator.*;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.dal.mapper.IamUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试数据生成服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TestDataGeneratorServiceImpl implements TestDataGeneratorService {

    private final IamUserMapper iamUserMapper;
    private final IamDepartmentMapper iamDepartmentMapper;
    private final IamRoleMapper iamRoleMapper;
    private final IamPermissionMapper iamPermissionMapper;
    private final IamMenuMapper iamMenuMapper;
    private final IamCompanyMapper iamCompanyMapper;
    private final IamPostMapper iamPostMapper;
    private final IamAccountMapper iamAccountMapper;

    private final IamUserDataGenerator iamUserDataGenerator;
    private final IamDepartmentDataGenerator iamDepartmentDataGenerator;
    private final IamRoleDataGenerator iamRoleDataGenerator;
    private final IamPermissionDataGenerator iamPermissionDataGenerator;
    private final IamMenuDataGenerator iamMenuDataGenerator;
    private final IamCompanyDataGenerator iamCompanyDataGenerator;
    private final IamPostDataGenerator iamPostDataGenerator;
    private final IamAccountDataGenerator iamAccountDataGenerator;

    private static final String DEFAULT_TENANT_ID = "TEST_TENANT";

    @Override
    public int generateIamData(IamDataTypeEnum type, int count) {
        return generateIamData(type, count, DEFAULT_TENANT_ID);
    }

    @Override
    public int generateIamData(IamDataTypeEnum type, int count, String tenantId) {
        String actualTenantId = getActualTenantId(tenantId);

        if (type == null || type == IamDataTypeEnum.ALL) {
            return generateAllIamData(count, actualTenantId);
        }

        if (type == IamDataTypeEnum.COMPANY) {
            return generateCompanies(actualTenantId, count);
        } else if (type == IamDataTypeEnum.DEPARTMENT) {
            return generateDepartments(actualTenantId, count);
        } else if (type == IamDataTypeEnum.POST) {
            return generatePosts(actualTenantId, count);
        } else if (type == IamDataTypeEnum.ROLE) {
            return generateRoles(actualTenantId, count);
        } else if (type == IamDataTypeEnum.PERMISSION) {
            return generatePermissions(actualTenantId, count);
        } else if (type == IamDataTypeEnum.MENU) {
            return generateMenus(actualTenantId, count);
        } else if (type == IamDataTypeEnum.USER) {
            return generateUsers(actualTenantId, count);
        } else if (type == IamDataTypeEnum.ACCOUNT) {
            return generateAccounts(actualTenantId, count);
        }
        return 0;
    }

    @Override
    public int generateAllIamData(int count) {
        return generateAllIamData(count, DEFAULT_TENANT_ID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int generateAllIamData(int count, String tenantId) {
        String actualTenantId = getActualTenantId(tenantId);
        int total = 0;

        // 1. 生成账号
        total += generateAccounts(actualTenantId, 10);

        // 2. 获取账号ID列表
        List<String> accountIds = iamAccountMapper.selectAll().stream()
                .map(IamAccountDO::getAccountId)
                .collect(Collectors.toList());

        // 3. 生成公司
        total += generateCompanies(actualTenantId, count / 10);

        // 4. 获取公司ID列表
        List<String> companyIds = iamCompanyMapper.selectAll().stream()
                .map(IamCompanyDO::getCompanyId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(companyIds)) {
            companyIds.add(actualTenantId);
        }

        // 5. 生成部门（树形结构）
        total += generateDepartments(actualTenantId, count / 8);

        // 6. 获取部门ID列表
        List<String> deptIds = iamDepartmentMapper.selectAll().stream()
                .map(IamDepartmentDO::getDeptId)
                .collect(Collectors.toList());

        // 7. 生成岗位
        total += generatePosts(actualTenantId, count / 10);

        // 8. 获取岗位ID列表
        List<String> postIds = iamPostMapper.selectAll().stream()
                .map(IamPostDO::getPostId)
                .collect(Collectors.toList());

        // 9. 生成角色
        total += generateRoles(actualTenantId, count / 10);

        // 10. 获取角色ID列表
        List<String> roleIds = iamRoleMapper.selectAll().stream()
                .map(IamRoleDO::getRoleId)
                .collect(Collectors.toList());

        // 11. 生成权限
        total += generatePermissions(actualTenantId, count / 8);

        // 12. 生成菜单
        total += generateMenus(actualTenantId, count / 10);

        // 13. 生成用户（关联部门）
        total += generateUsersWithRelations(actualTenantId, count, deptIds, postIds);

        // 14. 关联账号和角色
        if (CollUtil.isNotEmpty(accountIds) && CollUtil.isNotEmpty(roleIds)) {
            // 为第一个账号关联所有角色（测试用）
            // 实际业务中需要创建 iam_account_role 关联表
        }

        log.info("测试数据生成完成，总计: {} 条", total);
        return total;
    }

    @Override
    public int clearTestData(IamDataTypeEnum type) {
        return clearTestData(type, DEFAULT_TENANT_ID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int clearTestData(IamDataTypeEnum type, String tenantId) {
        // 清空测试数据逻辑
        return 0;
    }

    private String getActualTenantId(String tenantId) {
        return StrUtil.isBlank(tenantId) ? DEFAULT_TENANT_ID : tenantId;
    }

    private int generateAccounts(String tenantId, int count) {
        List<IamAccountDO> accounts = iamAccountDataGenerator.generate(tenantId, count);
        for (IamAccountDO account : accounts) {
            iamAccountMapper.insert(account);
        }
        log.info("生成账号数据: {} 条", count);
        return count;
    }

    private int generateCompanies(String tenantId, int count) {
        List<IamCompanyDO> companies = iamCompanyDataGenerator.generate(tenantId, count);
        for (IamCompanyDO company : companies) {
            iamCompanyMapper.insert(company);
        }
        log.info("生成公司数据: {} 条", count);
        return count;
    }

    private int generateDepartments(String tenantId, int count) {
        List<String> companyIds = iamCompanyMapper.selectAll().stream()
                .map(IamCompanyDO::getCompanyId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(companyIds)) {
            companyIds.add(tenantId);
        }

        List<IamDepartmentDO> departments = iamDepartmentDataGenerator.generateTree(companyIds, tenantId, count);
        for (IamDepartmentDO dept : departments) {
            iamDepartmentMapper.insert(dept);
        }
        log.info("生成部门数据: {} 条", departments.size());
        return departments.size();
    }

    private int generatePosts(String tenantId, int count) {
        List<IamPostDO> posts = iamPostDataGenerator.generate(tenantId, count);
        for (IamPostDO post : posts) {
            iamPostMapper.insert(post);
        }
        log.info("生成岗位数据: {} 条", count);
        return count;
    }

    private int generateRoles(String tenantId, int count) {
        List<IamRoleDO> roles = iamRoleDataGenerator.generate(tenantId, count);
        for (IamRoleDO role : roles) {
            iamRoleMapper.insert(role);
        }
        log.info("生成角色数据: {} 条", count);
        return count;
    }

    private int generatePermissions(String tenantId, int count) {
        List<IamPermissionDO> permissions = iamPermissionDataGenerator.generate(tenantId, count);
        for (IamPermissionDO permission : permissions) {
            iamPermissionMapper.insert(permission);
        }
        log.info("生成权限数据: {} 条", count);
        return count;
    }

    private int generateMenus(String tenantId, int count) {
        List<IamMenuDO> menus = iamMenuDataGenerator.generateTree(tenantId, count);
        for (IamMenuDO menu : menus) {
            iamMenuMapper.insert(menu);
        }
        log.info("生成菜单数据: {} 条", menus.size());
        return menus.size();
    }

    private int generateUsers(String tenantId, int count) {
        List<String> deptIds = new ArrayList<>();
        List<String> postIds = new ArrayList<>();
        return generateUsersWithRelations(tenantId, count, deptIds, postIds);
    }

    private int generateUsersWithRelations(String tenantId, int count, List<String> deptIds, List<String> postIds) {
        List<IamUserDO> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamUserDO user = iamUserDataGenerator.generate(deptIds, postIds, tenantId);
            users.add(user);
        }

        for (IamUserDO user : users) {
            iamUserMapper.insert(user);
        }
        log.info("生成用户数据: {} 条", count);
        return count;
    }
}
