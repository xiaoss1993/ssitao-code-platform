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
import com.ssitao.code.modular.iam.testdata.dto.TestDataGenerateResultDTO;
import com.ssitao.code.modular.iam.testdata.generator.*;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.userprofile.dal.mapper.IamUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public TestDataGenerateResultDTO generateIamData(IamDataTypeEnum type, int count) {
        return generateIamData(type, count, DEFAULT_TENANT_ID);
    }

    @Override
    public TestDataGenerateResultDTO generateIamData(IamDataTypeEnum type, int count, String tenantId) {
        String actualTenantId = getActualTenantId(tenantId);
        long startTime = System.currentTimeMillis();

        if (type == null || type == IamDataTypeEnum.ALL) {
            return generateAllIamData(count, actualTenantId);
        }

        Map<String, Integer> typeCountMap = new HashMap<>();
        int totalCount = 0;

        try {
            if (type == IamDataTypeEnum.COMPANY) {
                int countGenerated = generateCompanies(actualTenantId, count);
                typeCountMap.put("company", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.DEPARTMENT) {
                int countGenerated = generateDepartments(actualTenantId, count);
                typeCountMap.put("department", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.POST) {
                int countGenerated = generatePosts(actualTenantId, count);
                typeCountMap.put("post", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.ROLE) {
                int countGenerated = generateRoles(actualTenantId, count);
                typeCountMap.put("role", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.PERMISSION) {
                int countGenerated = generatePermissions(actualTenantId, count);
                typeCountMap.put("permission", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.MENU) {
                int countGenerated = generateMenus(actualTenantId, count);
                typeCountMap.put("menu", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.USER) {
                int countGenerated = generateUsers(actualTenantId, count);
                typeCountMap.put("user", countGenerated);
                totalCount += countGenerated;
            } else if (type == IamDataTypeEnum.ACCOUNT) {
                int countGenerated = generateAccounts(actualTenantId, count);
                typeCountMap.put("account", countGenerated);
                totalCount += countGenerated;
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            return TestDataGenerateResultDTO.builder()
                    .totalCount(totalCount)
                    .typeCountMap(typeCountMap)
                    .tenantId(actualTenantId)
                    .elapsedTime(elapsedTime)
                    .success(true)
                    .build();
        } catch (Exception e) {
            log.error("生成测试数据失败", e);
            long elapsedTime = System.currentTimeMillis() - startTime;
            return TestDataGenerateResultDTO.builder()
                    .totalCount(0)
                    .typeCountMap(typeCountMap)
                    .tenantId(actualTenantId)
                    .elapsedTime(elapsedTime)
                    .success(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public TestDataGenerateResultDTO generateAllIamData(int count) {
        return generateAllIamData(count, DEFAULT_TENANT_ID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TestDataGenerateResultDTO generateAllIamData(int count, String tenantId) {
        String actualTenantId = getActualTenantId(tenantId);
        long startTime = System.currentTimeMillis();
        Map<String, Integer> typeCountMap = new HashMap<>();
        int totalCount = 0;

        try {
            // 1. 生成账号
            int accountCount = generateAccounts(actualTenantId, 10);
            typeCountMap.put("account", accountCount);
            totalCount += accountCount;

            // 2. 获取账号ID列表
            List<String> accountIds = iamAccountMapper.selectAll().stream()
                    .map(IamAccountDO::getAccountId)
                    .collect(Collectors.toList());

            // 3. 生成公司
            int companyCount = generateCompanies(actualTenantId, count / 10);
            typeCountMap.put("company", companyCount);
            totalCount += companyCount;

            // 4. 获取公司ID列表
            List<String> companyIds = iamCompanyMapper.selectAll().stream()
                    .map(IamCompanyDO::getCompanyId)
                    .collect(Collectors.toList());
            if (CollUtil.isEmpty(companyIds)) {
                companyIds.add(actualTenantId);
            }

            // 5. 生成部门（树形结构）
            int deptCount = generateDepartments(actualTenantId, count / 8);
            typeCountMap.put("department", deptCount);
            totalCount += deptCount;

            // 6. 获取部门ID列表
            List<String> deptIds = iamDepartmentMapper.selectAll().stream()
                    .map(IamDepartmentDO::getDeptId)
                    .collect(Collectors.toList());

            // 7. 生成岗位
            int postCount = generatePosts(actualTenantId, count / 10);
            typeCountMap.put("post", postCount);
            totalCount += postCount;

            // 8. 获取岗位ID列表
            List<String> postIds = iamPostMapper.selectAll().stream()
                    .map(IamPostDO::getPostId)
                    .collect(Collectors.toList());

            // 9. 生成角色
            int roleCount = generateRoles(actualTenantId, count / 10);
            typeCountMap.put("role", roleCount);
            totalCount += roleCount;

            // 10. 获取角色ID列表
            List<String> roleIds = iamRoleMapper.selectAll().stream()
                    .map(IamRoleDO::getRoleId)
                    .collect(Collectors.toList());

            // 11. 生成权限
            int permCount = generatePermissions(actualTenantId, count / 8);
            typeCountMap.put("permission", permCount);
            totalCount += permCount;

            // 12. 生成菜单
            int menuCount = generateMenus(actualTenantId, count / 10);
            typeCountMap.put("menu", menuCount);
            totalCount += menuCount;

            // 13. 生成用户（关联部门）
            int userCount = generateUsersWithRelations(actualTenantId, count, deptIds, postIds);
            typeCountMap.put("user", userCount);
            totalCount += userCount;

            // 14. 关联账号和角色
            if (CollUtil.isNotEmpty(accountIds) && CollUtil.isNotEmpty(roleIds)) {
                // 为第一个账号关联所有角色（测试用）
                // 实际业务中需要创建 iam_account_role 关联表
            }

            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("测试数据生成完成，总计: {} 条，耗时: {}ms", totalCount, elapsedTime);

            return TestDataGenerateResultDTO.builder()
                    .totalCount(totalCount)
                    .typeCountMap(typeCountMap)
                    .tenantId(actualTenantId)
                    .elapsedTime(elapsedTime)
                    .success(true)
                    .build();
        } catch (Exception e) {
            log.error("生成测试数据失败", e);
            long elapsedTime = System.currentTimeMillis() - startTime;
            return TestDataGenerateResultDTO.builder()
                    .totalCount(totalCount)
                    .typeCountMap(typeCountMap)
                    .tenantId(actualTenantId)
                    .elapsedTime(elapsedTime)
                    .success(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public int clearTestData(IamDataTypeEnum type) {
        return clearTestData(type, DEFAULT_TENANT_ID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int clearTestData(IamDataTypeEnum type, String tenantId) {
        String actualTenantId = getActualTenantId(tenantId);
        int totalCleared = 0;

        try {
            // 清空用户
            if (type == null || type == IamDataTypeEnum.USER || type == IamDataTypeEnum.ALL) {
                List<IamUserDO> users = iamUserMapper.selectAll();
                for (IamUserDO user : users) {
                    iamUserMapper.deleteById(user.getUserId());
                }
                totalCleared += users.size();
                log.info("清空用户数据: {} 条", users.size());
            }

            // 清空菜单
            if (type == null || type == IamDataTypeEnum.MENU || type == IamDataTypeEnum.ALL) {
                List<IamMenuDO> menus = iamMenuMapper.selectAll();
                for (IamMenuDO menu : menus) {
                    iamMenuMapper.deleteById(menu.getMenuId());
                }
                totalCleared += menus.size();
                log.info("清空菜单数据: {} 条", menus.size());
            }

            // 清空权限
            if (type == null || type == IamDataTypeEnum.PERMISSION || type == IamDataTypeEnum.ALL) {
                List<IamPermissionDO> permissions = iamPermissionMapper.selectAll();
                for (IamPermissionDO permission : permissions) {
                    iamPermissionMapper.deleteById(permission.getPermissionId());
                }
                totalCleared += permissions.size();
                log.info("清空权限数据: {} 条", permissions.size());
            }

            // 清空角色
            if (type == null || type == IamDataTypeEnum.ROLE || type == IamDataTypeEnum.ALL) {
                List<IamRoleDO> roles = iamRoleMapper.selectAll();
                for (IamRoleDO role : roles) {
                    iamRoleMapper.deleteById(role.getRoleId());
                }
                totalCleared += roles.size();
                log.info("清空角色数据: {} 条", roles.size());
            }

            // 清空岗位
            if (type == null || type == IamDataTypeEnum.POST || type == IamDataTypeEnum.ALL) {
                List<IamPostDO> posts = iamPostMapper.selectAll();
                for (IamPostDO post : posts) {
                    iamPostMapper.deleteById(post.getPostId());
                }
                totalCleared += posts.size();
                log.info("清空岗位数据: {} 条", posts.size());
            }

            // 清空部门
            if (type == null || type == IamDataTypeEnum.DEPARTMENT || type == IamDataTypeEnum.ALL) {
                List<IamDepartmentDO> departments = iamDepartmentMapper.selectAll();
                for (IamDepartmentDO dept : departments) {
                    iamDepartmentMapper.deleteById(dept.getDeptId());
                }
                totalCleared += departments.size();
                log.info("清空部门数据: {} 条", departments.size());
            }

            // 清空公司
            if (type == null || type == IamDataTypeEnum.COMPANY || type == IamDataTypeEnum.ALL) {
                List<IamCompanyDO> companies = iamCompanyMapper.selectAll();
                for (IamCompanyDO company : companies) {
                    iamCompanyMapper.deleteById(company.getCompanyId());
                }
                totalCleared += companies.size();
                log.info("清空公司数据: {} 条", companies.size());
            }

            // 清空账号
            if (type == null || type == IamDataTypeEnum.ACCOUNT || type == IamDataTypeEnum.ALL) {
                List<IamAccountDO> accounts = iamAccountMapper.selectAll();
                for (IamAccountDO account : accounts) {
                    iamAccountMapper.deleteById(account.getAccountId());
                }
                totalCleared += accounts.size();
                log.info("清空账号数据: {} 条", accounts.size());
            }

            log.info("清空测试数据完成，总计: {} 条", totalCleared);
        } catch (Exception e) {
            log.error("清空测试数据失败", e);
            throw e;
        }

        return totalCleared;
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
        List<String> deptIds = iamDepartmentMapper.selectAll().stream()
                .map(IamDepartmentDO::getDeptId)
                .collect(Collectors.toList());
        List<String> postIds = iamPostMapper.selectAll().stream()
                .map(IamPostDO::getPostId)
                .collect(Collectors.toList());
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
