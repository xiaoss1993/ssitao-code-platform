# 测试数据生成器实现计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 通过 REST API 接口为 IAM 和 Meta 模块生成大量测试数据（每个实体 1000-5000 条）

**Architecture:** 集中式生成器架构，在 modular-iam 模块中创建统一的 TestDataGeneratorService，通过调用现有业务 Service API 生成数据，保证业务逻辑一致性。

**Tech Stack:** Spring Boot, Mybatis-Flex, Hutool (RandomUtil, IdUtil, DateUtil)

---

## 阶段一：准备阶段

### Task 1: 创建 IAM 模块数据类型枚举

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/enums/IamDataTypeEnum.java`

**Step 1: 创建枚举类**

```java
package com.ssitao.code.modular.iam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IAM 模块数据类型枚举
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Getter
@AllArgsConstructor
public enum IamDataTypeEnum {

    COMPANY("company", "公司"),
    DEPARTMENT("department", "部门"),
    POST("post", "岗位"),
    ROLE("role", "角色"),
    PERMISSION("permission", "权限"),
    MENU("menu", "菜单"),
    USER("user", "用户"),
    ACCOUNT("account", "账号"),
    ALL("all", "全部");

    private final String code;
    private final String desc;

    public static IamDataTypeEnum getByCode(String code) {
        for (IamDataTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/enums/IamDataTypeEnum.java
git commit -m "feat(test-data): add IamDataTypeEnum for test data generator"
```

---

### Task 2: 查看现有的 IAM Service 接口

**Files:**
- Research: 查看现有的用户、角色、部门等 Service 接口
- 查看路径:
  - `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/*/service/*.java`
  - `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/*/application/service/*.java`

**Step 1: 查找 Service 文件**

```bash
# 使用 Grep 查找所有 Service 类
glob: **/*Service.java
```

**Step 2: 读取关键 Service 接口**
- 读取 IamUserService, IamDepartmentService, IamRoleService 等接口
- 了解其 create 方法签名

**Step 3: Commit**

```bash
git commit -m "docs: document existing IAM service interfaces"
```

---

## 阶段二：实现核心生成器

### Task 3: 创建测试数据生成服务接口

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/TestDataGeneratorService.java`

**Step 1: 创建接口**

```java
package com.ssitao.code.modular.iam.testdata;

import com.ssitao.code.modular.iam.enums.IamDataTypeEnum;

/**
 * 测试数据生成服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface TestDataGeneratorService {

    /**
     * 生成 IAM 模块测试数据
     *
     * @param type 数据类型
     * @param count 数量
     * @return 生成数量
     */
    int generateIamData(IamDataTypeEnum type, int count);

    /**
     * 生成全部 IAM 模块测试数据
     *
     * @param count 每个实体生成的数量
     * @return 生成的总数量
     */
    int generateAllIamData(int count);

    /**
     * 清空测试数据
     *
     * @param type 数据类型
     * @return 清空数量
     */
    int clearTestData(IamDataTypeEnum type);
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/TestDataGeneratorService.java
git commit -m "feat(test-data): add TestDataGeneratorService interface"
```

---

### Task 4: 创建用户数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamUserDataGenerator.java`

**Step 1: 创建用户数据生成器**

```java
package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamUserDataGenerator {

    private static final List<String> SEX_LIST = Arrays.asList("MALE", "FEMALE", "UNKNOWN");
    private static final List<String> STATUS_LIST = Arrays.asList("ON_JOB", "RESIGN", "probation");
    private static final List<String> EMPLOYMENT_TYPE_LIST = Arrays.asList("FULL_TIME", "PART_TIME", "INTERNSHIP", "OUTSOURCING");
    private static final List<String> EDUCATION_LIST = Arrays.asList("博士", "硕士", "本科", "大专", "高中", "初中");

    private static final String[] SURNAMES = {"王", "李", "张", "刘", "陈", "杨", "黄", "赵", "周", "吴", "徐", "孙", "马", "朱", "胡", "郭", "何", "高", "林", "罗"};
    private static final String[] NAMES = {"伟", "芳", "娜", "秀英", "敏", "静", "丽", "强", "磊", "军", "洋", "勇", "艳", "杰", "涛", "明", "超", "秀兰", "霞", "平"};

    /**
     * 生成随机用户数据
     *
     * @param deptIds    部门ID列表（用于关联）
     * @param postIds    岗位ID列表（用于关联）
     * @param tenantId   租户ID
     * @return 用户数据对象
     */
    public IamUserDO generate(List<String> deptIds, List<String> postIds, String tenantId) {
        IamUserDO user = IamUserDO.builder()
                .userCode("U" + RandomUtil.randomNumbers(8))
                .userName(generateRandomName())
                .userSex(RandomUtil.randomEle(SEX_LIST))
                .userBirthday(generateRandomBirthday())
                .userPhone(generateRandomPhone())
                .userMail(RandomUtil.randomString(6).toLowerCase() + "@test.com")
                .userNation("汉族")
                .userMaritalStatus(RandomUtil.randomBoolean() ? "MARRIED" : "SINGLE")
                .userPoliticalStatus(RandomUtil.randomEle(Arrays.asList("群众", "党员", "团员")))
                .userWorkNumber("W" + RandomUtil.randomNumbers(6))
                .userEntryDate(generateRandomEntryDate())
                .userEmploymentType(RandomUtil.randomEle(EMPLOYMENT_TYPE_LIST))
                .userEducation(RandomUtil.randomEle(EDUCATION_LIST))
                .userStatus(RandomUtil.randomEle(STATUS_LIST))
                .tenantId(tenantId)
                .createTime(LocalDateTime.now())
                .isDeleted(0)
                .version(0)
                .build();

        // 关联部门
        if (deptIds != null && !deptIds.isEmpty()) {
            user.setCreateOrgId(RandomUtil.randomEle(deptIds));
        }

        return user;
    }

    private String generateRandomName() {
        return RandomUtil.randomEle(SURNAMES) + RandomUtil.randomEle(NAMES);
    }

    private LocalDate generateRandomBirthday() {
        int age = RandomUtil.randomInt(18, 60);
        return LocalDate.now().minusYears(age).minusMonths(RandomUtil.randomInt(0, 12)).minusDays(RandomUtil.randomInt(0, 28));
    }

    private String generateRandomPhone() {
        String[] prefixes = {"138", "139", "150", "151", "152", "188", "189"};
        return RandomUtil.randomEle(prefixes) + RandomUtil.randomNumbers(8);
    }

    private LocalDate generateRandomEntryDate() {
        return LocalDate.now().minusMonths(RandomUtil.randomInt(0, 36));
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamUserDataGenerator.java
git commit -m "feat(test-data): add IamUserDataGenerator"
```

---

### Task 5: 创建部门数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamDepartmentDataGenerator.java`

**Step 1: 创建部门数据生成器**

```java
package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDepartmentDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamDepartmentDataGenerator {

    private static final String[] DEPT_NAMES = {"研发部", "产品部", "设计部", "市场部", "销售部", "财务部", "人力资源部", "行政部", "技术部", "运营部"};

    /**
     * 生成部门树形结构数据
     *
     * @param companyIds 公司ID列表
     * @param tenantId   租户ID
     * @param count      生成数量
     * @return 部门列表
     */
    public List<IamDepartmentDO> generateTree(List<String> companyIds, String tenantId, int count) {
        List<IamDepartmentDO> departments = new ArrayList<>();
        int treeLevel = 3; // 3层树形结构

        // 生成顶级部门
        for (int i = 0; i < count / 3; i++) {
            String deptId = generateId();
            IamDepartmentDO dept = IamDepartmentDO.builder()
                    .deptId(deptId)
                    .deptCode("DEPT" + RandomUtil.randomNumbers(6))
                    .deptName(RandomUtil.randomEle(DEPT_NAMES) + (i + 1))
                    .deptType("DEPARTMENT")
                    .deptParentId("0")
                    .deptCompanyId(RandomUtil.randomEle(companyIds))
                    .deptPhone("400-" + RandomUtil.randomNumbers(7))
                    .deptStatus(1)
                    .deptSort(i * 10)
                    .deptTreePath("0," + deptId)
                    .deptTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            departments.add(dept);
        }

        // 生成二级部门
        for (IamDepartmentDO parent : departments) {
            int childCount = RandomUtil.randomInt(2, 5);
            for (int i = 0; i < childCount; i++) {
                String deptId = generateId();
                IamDepartmentDO dept = IamDepartmentDO.builder()
                        .deptId(deptId)
                        .deptCode("DEPT" + RandomUtil.randomNumbers(6))
                        .deptName(RandomUtil.randomEle(DEPT_NAMES) + (i + 1))
                        .deptType("DEPARTMENT")
                        .deptParentId(parent.getDeptId())
                        .deptCompanyId(parent.getDeptCompanyId())
                        .deptPhone("400-" + RandomUtil.randomNumbers(7))
                        .deptStatus(1)
                        .deptSort(i * 10)
                        .deptTreePath(parent.getDeptTreePath() + "," + deptId)
                        .deptTreeLevel(2)
                        .tenantId(tenantId)
                        .createTime(LocalDateTime.now())
                        .isDeleted(0)
                        .version(0)
                        .build();
                departments.add(dept);
            }
        }

        return departments;
    }

    private String generateId() {
        return "DEPT" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamDepartmentDataGenerator.java
git commit -m "feat(test-data): add IamDepartmentDataGenerator"
```

---

### Task 6: 创建角色数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamRoleDataGenerator.java`

**Step 1: 创建角色数据生成器**

```java
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
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamRoleDataGenerator.java
git commit -m "feat(test-data): add IamRoleDataGenerator"
```

---

### Task 7: 创建权限数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamPermissionDataGenerator.java`

**Step 1: 创建权限数据生成器**

```java
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
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamPermissionDataGenerator.java
git commit -m "feat(test-data): add IamPermissionDataGenerator"
```

---

### Task 8: 创建菜单数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamMenuDataGenerator.java`

**Step 1: 创建菜单数据生成器**

```java
package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.menu.dal.dataobject.IamMenuDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 菜单测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamMenuDataGenerator {

    private static final String[] MENU_NAMES = {"用户管理", "角色管理", "部门管理", "菜单管理", "权限管理", "公司管理", "岗位管理", "字典管理", "系统配置", "日志管理"};
    private static final String[] COMPONENTS = {"system/user/index", "system/role/index", "system/dept/index", "system/menu/index", "system/permission/index"};
    private static final List<String> TYPE_LIST = Arrays.asList("DIRECTORY", "MENU", "BUTTON");

    /**
     * 生成菜单树形结构数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 菜单列表
     */
    public List<IamMenuDO> generateTree(String tenantId, int count) {
        List<IamMenuDO> menus = new ArrayList<>();

        // 生成顶级菜单（目录）
        int dirCount = Math.min(count / 3, 10);
        for (int i = 0; i < dirCount; i++) {
            String menuId = generateId();
            IamMenuDO menu = IamMenuDO.builder()
                    .menuId(menuId)
                    .menuCode("MENU" + RandomUtil.randomNumbers(6))
                    .menuName(RandomUtil.randomEle(MENU_NAMES))
                    .menuType("DIRECTORY")
                    .menuParentId("0")
                    .menuPath("/" + RandomUtil.randomString(6).toLowerCase())
                    .menuIcon(RandomUtil.randomEle(Arrays.asList("user", "role", "dept", "menu", "setting")))
                    .menuSort(i * 100)
                    .menuIsVisible(1)
                    .menuIsCached(0)
                    .menuIsAffix(0)
                    .menuStatus(1)
                    .menuIsBuiltin(0)
                    .menuTreePath("0," + menuId)
                    .menuTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            menus.add(menu);

            // 为每个顶级菜单生成子菜单
            int childCount = RandomUtil.randomInt(2, 5);
            for (int j = 0; j < childCount; j++) {
                String childId = generateId();
                IamMenuDO child = IamMenuDO.builder()
                        .menuId(childId)
                        .menuCode("MENU" + RandomUtil.randomNumbers(6))
                        .menuName(RandomUtil.randomEle(MENU_NAMES))
                        .menuType("MENU")
                        .menuParentId(menuId)
                        .menuPath("/" + RandomUtil.randomString(6).toLowerCase())
                        .menuComponent(RandomUtil.randomEle(COMPONENTS))
                        .menuIcon(RandomUtil.randomEle(Arrays.asList("user", "role", "dept", "menu", "setting")))
                        .menuSort(j * 10)
                        .menuIsVisible(1)
                        .menuIsCached(0)
                        .menuIsAffix(0)
                        .menuStatus(1)
                        .menuIsBuiltin(0)
                        .menuTreePath("0," + menuId + "," + childId)
                        .menuTreeLevel(2)
                        .tenantId(tenantId)
                        .createTime(LocalDateTime.now())
                        .isDeleted(0)
                        .version(0)
                        .build();
                menus.add(child);

                // 生成按钮
                if (RandomUtil.randomBoolean()) {
                    String btnId = generateId();
                    IamMenuDO btn = IamMenuDO.builder()
                            .menuId(btnId)
                            .menuCode("BTN" + RandomUtil.randomNumbers(6))
                            .menuName(RandomUtil.randomEle(Arrays.asList("新增", "编辑", "删除", "导出", "导入")))
                            .menuType("BUTTON")
                            .menuParentId(childId)
                            .menuPermission(RandomUtil.randomString(6).toLowerCase() + ":" + RandomUtil.randomEle(Arrays.asList("add", "edit", "delete", "export", "import")))
                            .menuSort(0)
                            .menuStatus(1)
                            .menuIsBuiltin(0)
                            .menuTreePath("0," + menuId + "," + childId + "," + btnId)
                            .menuTreeLevel(3)
                            .tenantId(tenantId)
                            .createTime(LocalDateTime.now())
                            .isDeleted(0)
                            .version(0)
                            .build();
                    menus.add(btn);
                }
            }
        }

        return menus;
    }

    private String generateId() {
        return "MENU" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamMenuDataGenerator.java
git commit -m "feat(test-data): add IamMenuDataGenerator"
```

---

### Task 9: 创建公司数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamCompanyDataGenerator.java`

**Step 1: 创建公司数据生成器**

```java
package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamCompanyDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamCompanyDataGenerator {

    private static final String[] COMPANY_NAMES = {"阿里巴巴", "腾讯科技", "百度在线", "京东集团", "美团点评", "字节跳动", "网易公司", "小米科技", "华为技术", "滴滴出行"};

    /**
     * 生成公司数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 公司列表
     */
    public List<IamCompanyDO> generate(String tenantId, int count) {
        List<IamCompanyDO> companies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamCompanyDO company = IamCompanyDO.builder()
                    .companyId(generateId())
                    .companyCode("CO" + RandomUtil.randomNumbers(6))
                    .companyName(RandomUtil.randomEle(COMPANY_NAMES) + (i + 1) + "分公司")
                    .companyType("COMPANY")
                    .companyParentId("0")
                    .companyPhone("400-" + RandomUtil.randomNumbers(7))
                    .companyAddress("测试地址" + (i + 1))
                    .companyStatus(1)
                    .companySort(i * 10)
                    .companyTreePath("0,")
                    .companyTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            companies.add(company);
        }
        return companies;
    }

    private String generateId() {
        return "CO" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamCompanyDataGenerator.java
git commit -m "feat(test-data): add IamCompanyDataGenerator"
```

---

### Task 10: 创建岗位数据生成器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamPostDataGenerator.java`

**Step 1: 创建岗位数据生成器**

```java
package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamPostDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamPostDataGenerator {

    private static final String[] POST_NAMES = {"Java开发工程师", "Python开发工程师", "前端开发工程师", "产品经理", "UI设计师", "测试工程师", "运维工程师", "数据分析师", "算法工程师", "项目经理"};

    /**
     * 生成岗位数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 岗位列表
     */
    public List<IamPostDO> generate(String tenantId, int count) {
        List<IamPostDO> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamPostDO post = IamPostDO.builder()
                    .postId(generateId())
                    .postCode("POST" + RandomUtil.randomNumbers(5))
                    .postName(RandomUtil.randomEle(POST_NAMES) + (i + 1))
                    .postType("TECHNICAL")
                    .postStatus(1)
                    .postSort(i * 10)
                    .postDesc("测试岗位描述" + (i + 1))
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            posts.add(post);
        }
        return posts;
    }

    private String generateId() {
        return "POST" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/generator/IamPostDataGenerator.java
git commit -m "feat(test-data): add IamPostDataGenerator"
```

---

## 阶段三：实现服务实现类

### Task 11: 实现 TestDataGeneratorServiceImpl

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/impl/TestDataGeneratorServiceImpl.java`

**Step 1: 查看 Mapper 文件**

先查看 IamUserMapper, IamDepartmentMapper 等 Mapper 接口

**Step 2: 实现服务类**

```java
package com.ssitao.code.modular.iam.testdata.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
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

        return switch (type) {
            case COMPANY -> generateCompanies(count);
            case DEPARTMENT -> generateDepartments(count);
            case POST -> generatePosts(count);
            case ROLE -> generateRoles(count);
            case PERMISSION -> generatePermissions(count);
            case MENU -> generateMenus(count);
            case USER -> generateUsers(count);
            default -> 0;
        };
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int generateAllIamData(int count) {
        int total = 0;

        // 1. 生成公司
        total += generateCompanies(count / 10);

        // 2. 获取公司ID列表
        List<String> companyIds = iamCompanyMapper.selectList(null).stream()
                .map(IamCompanyDO::getCompanyId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(companyIds)) {
            companyIds.add(DEFAULT_TENANT_ID);
        }

        // 3. 生成部门（树形结构）
        total += generateDepartments(count / 8);

        // 4. 获取部门ID列表
        List<String> deptIds = iamDepartmentMapper.selectList(null).stream()
                .map(IamDepartmentDO::getDeptId)
                .collect(Collectors.toList());

        // 5. 生成岗位
        total += generatePosts(count / 10);

        // 6. 获取岗位ID列表
        List<String> postIds = iamPostMapper.selectList(null).stream()
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
        List<String> companyIds = iamCompanyMapper.selectList(null).stream()
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
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/impl/TestDataGeneratorServiceImpl.java
git commit -m "feat(test-data): add TestDataGeneratorServiceImpl"
```

---

### Task 12: 创建测试数据 Controller

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/TestDataController.java`

**Step 1: 创建 Controller**

```java
package com.ssitao.code.modular.iam.testdata;

import com.ssitao.code.modular.iam.enums.IamDataTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 测试数据生成控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/test-data")
@RequiredArgsConstructor
public class TestDataController {

    private final TestDataGeneratorService testDataGeneratorService;

    /**
     * 生成 IAM 模块测试数据
     *
     * @param type  数据类型
     * @param count 数量
     * @return 生成数量
     */
    @PostMapping("/iam/{type}")
    public int generateIamData(@PathVariable String type, @RequestParam(defaultValue = "1000") int count) {
        log.info("开始生成 IAM 测试数据, type: {}, count: {}", type, count);
        IamDataTypeEnum dataType = IamDataTypeEnum.getByCode(type);
        int result = testDataGeneratorService.generateIamData(dataType, count);
        log.info("IAM 测试数据生成完成, 数量: {}", result);
        return result;
    }

    /**
     * 生成全部测试数据
     *
     * @param count 数量
     * @return 生成数量
     */
    @PostMapping("/all")
    public int generateAllData(@RequestParam(defaultValue = "1000") int count) {
        log.info("开始生成全部测试数据, count: {}", count);
        int result = testDataGeneratorService.generateAllIamData(count);
        log.info("全部测试数据生成完成, 数量: {}", result);
        return result;
    }

    /**
     * 清空测试数据
     *
     * @param type 数据类型
     * @return 清空数量
     */
    @DeleteMapping("/clear")
    public int clearTestData(@RequestParam(required = false) String type) {
        log.info("开始清空测试数据, type: {}", type);
        IamDataTypeEnum dataType = IamDataTypeEnum.getByCode(type);
        int result = testDataGeneratorService.clearTestData(dataType);
        log.info("测试数据清空完成, 数量: {}", result);
        return result;
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/testdata/TestDataController.java
git commit -m "feat(test-data): add TestDataController"
```

---

## 阶段四：测试验证

### Task 13: 测试 API 接口

**Step 1: 启动应用**

```bash
# 启动 Spring Boot 应用
cd ssitao-code-boot
mvn spring-boot:run
```

**Step 2: 调用 API 生成数据**

```bash
# 生成全部测试数据 (1000条/实体)
curl -X POST "http://localhost:8080/api/test-data/all?count=1000"

# 生成用户数据 (1000条)
curl -X POST "http://localhost:8080/api/test-data/iam/user?count=1000"

# 清空测试数据
curl -X DELETE "http://localhost:8080/api/test-data/clear?type=all"
```

**Step 3: 验证数据**

- 登录数据库验证数据条数
- 验证数据关联关系（部门、角色等）
- 验证 API 响应时间

**Step 4: Commit**

```bash
git commit -m "test: verify test data generation API"
```

---

## 总结

本计划包含以下任务：

1. **Task 1**: 创建 IAM 数据类型枚举
2. **Task 2**: 查看现有 Service 接口
3. **Task 3**: 创建测试数据生成服务接口
4. **Task 4**: 创建用户数据生成器
5. **Task 5**: 创建部门数据生成器
6. **Task 6**: 创建角色数据生成器
7. **Task 7**: 创建权限数据生成器
8. **Task 8**: 创建菜单数据生成器
9. **Task 9**: 创建公司数据生成器
10. **Task 10**: 创建岗位数据生成器
11. **Task 11**: 实现 TestDataGeneratorServiceImpl
12. **Task 12**: 创建测试数据 Controller
13. **Task 13**: 测试验证
