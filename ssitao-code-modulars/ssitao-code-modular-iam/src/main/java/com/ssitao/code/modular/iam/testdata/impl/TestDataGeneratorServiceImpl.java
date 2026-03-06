package com.ssitao.code.modular.iam.testdata.impl;

import cn.hutool.core.collection.CollUtil;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamPermissionDO;
import com.ssitao.code.modular.iam.authorization.dal.dataobject.IamRoleDO;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamPermissionMapper;
import com.ssitao.code.modular.iam.authorization.dal.mapper.IamRoleMapper;
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

    private final IamUserDataGenerator iamUserDataGenerator;
    private final IamDepartmentDataGenerator iamDepartmentDataGenerator;
    private final IamRoleDataGenerator iamRoleDataGenerator;
    private final IamPermissionDataGenerator iamPermissionDataGenerator;
    private final IamMenuDataGenerator iamMenuDataGenerator;
    private final IamCompanyDataGenerator iamCompanyDataGenerator;
    private final IamPostDataGenerator iamPostDataGenerator;

    private static final String DEFAULT_TENANT_ID = "TEST_TENANT";

    @Override
    public int generateIamData(IamDataTypeEnum type, int count) {
        if (type == null || type == IamDataTypeEnum.ALL) {
            return generateAllIamData(count);
        }

        if (type == IamDataTypeEnum.COMPANY) {
            return generateCompanies(count);
        } else if (type == IamDataTypeEnum.DEPARTMENT) {
            return generateDepartments(count);
        } else if (type == IamDataTypeEnum.POST) {
            return generatePosts(count);
        } else if (type == IamDataTypeEnum.ROLE) {
            return generateRoles(count);
        } else if (type == IamDataTypeEnum.PERMISSION) {
            return generatePermissions(count);
        } else if (type == IamDataTypeEnum.MENU) {
            return generateMenus(count);
        } else if (type == IamDataTypeEnum.USER) {
            return generateUsers(count);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int generateAllIamData(int count) {
        int total = 0;

        // 1. 生成公司
        total += generateCompanies(count / 10);

        // 2. 获取公司ID列表
        List<String> companyIds = iamCompanyMapper.selectAll().stream()
                .map(IamCompanyDO::getCompanyId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(companyIds)) {
            companyIds.add(DEFAULT_TENANT_ID);
        }

        // 3. 生成部门（树形结构）
        total += generateDepartments(count / 8);

        // 4. 获取部门ID列表
        List<String> deptIds = iamDepartmentMapper.selectAll().stream()
                .map(IamDepartmentDO::getDeptId)
                .collect(Collectors.toList());

        // 5. 生成岗位
        total += generatePosts(count / 10);

        // 6. 获取岗位ID列表
        List<String> postIds = iamPostMapper.selectAll().stream()
                .map(IamPostDO::getPostId)
                .collect(Collectors.toList());

        // 7. 生成角色
        total += generateRoles(count / 10);

        // 8. 生成权限
        total += generatePermissions(count / 8);

        // 9. 生成菜单
        total += generateMenus(count / 10);

        // 10. 生成用户（关联部门）
        total += generateUsersWithRelations(count, deptIds, postIds);

        log.info("测试数据生成完成，总计: {} 条", total);
        return total;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int clearTestData(IamDataTypeEnum type) {
        // 清空测试数据逻辑
        return 0;
    }

    private int generateCompanies(int count) {
        List<IamCompanyDO> companies = iamCompanyDataGenerator.generate(DEFAULT_TENANT_ID, count);
        for (IamCompanyDO company : companies) {
            iamCompanyMapper.insert(company);
        }
        log.info("生成公司数据: {} 条", count);
        return count;
    }

    private int generateDepartments(int count) {
        List<String> companyIds = iamCompanyMapper.selectAll().stream()
                .map(IamCompanyDO::getCompanyId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(companyIds)) {
            companyIds.add(DEFAULT_TENANT_ID);
        }

        List<IamDepartmentDO> departments = iamDepartmentDataGenerator.generateTree(companyIds, DEFAULT_TENANT_ID, count);
        for (IamDepartmentDO dept : departments) {
            iamDepartmentMapper.insert(dept);
        }
        log.info("生成部门数据: {} 条", departments.size());
        return departments.size();
    }

    private int generatePosts(int count) {
        List<IamPostDO> posts = iamPostDataGenerator.generate(DEFAULT_TENANT_ID, count);
        for (IamPostDO post : posts) {
            iamPostMapper.insert(post);
        }
        log.info("生成岗位数据: {} 条", count);
        return count;
    }

    private int generateRoles(int count) {
        List<IamRoleDO> roles = iamRoleDataGenerator.generate(DEFAULT_TENANT_ID, count);
        for (IamRoleDO role : roles) {
            iamRoleMapper.insert(role);
        }
        log.info("生成角色数据: {} 条", count);
        return count;
    }

    private int generatePermissions(int count) {
        List<IamPermissionDO> permissions = iamPermissionDataGenerator.generate(DEFAULT_TENANT_ID, count);
        for (IamPermissionDO permission : permissions) {
            iamPermissionMapper.insert(permission);
        }
        log.info("生成权限数据: {} 条", count);
        return count;
    }

    private int generateMenus(int count) {
        List<IamMenuDO> menus = iamMenuDataGenerator.generateTree(DEFAULT_TENANT_ID, count);
        for (IamMenuDO menu : menus) {
            iamMenuMapper.insert(menu);
        }
        log.info("生成菜单数据: {} 条", menus.size());
        return menus.size();
    }

    private int generateUsers(int count) {
        List<String> deptIds = new ArrayList<>();
        List<String> postIds = new ArrayList<>();
        return generateUsersWithRelations(count, deptIds, postIds);
    }

    private int generateUsersWithRelations(int count, List<String> deptIds, List<String> postIds) {
        List<IamUserDO> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamUserDO user = iamUserDataGenerator.generate(deptIds, postIds, DEFAULT_TENANT_ID);
            users.add(user);
        }

        for (IamUserDO user : users) {
            iamUserMapper.insert(user);
        }
        log.info("生成用户数据: {} 条", count);
        return count;
    }
}
