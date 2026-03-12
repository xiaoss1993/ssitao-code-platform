# SSITAO Code Platform 详细实施计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**Goal:** 完善权限管理体系和低代码管理功能，实现多租户、组织机构(五级)、功能权限、数据权限(行级)及低代码基础框架

**Architecture:** 基于现有DDD分层架构(Controller→ApplicationService→Repository→DomainModel)，使用MyBatis-Flex ORM，Sa-Token认证，多租户通过ThreadLocal+拦截器实现

**Tech Stack:** Spring Boot 2.7, Spring Cloud, MyBatis-Flex 1.9.5, Sa-Token 1.37.0, Vue 3, Element Plus

---

## 阶段一：多租户基础架构 (Tasks 1-10)

### Task 1: 创建租户DTO和Command类

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/api/dto/IamTenantDTO.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/command/IamTenantCreateCommand.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/command/IamTenantUpdateCommand.java`

**Step 1: 创建IamTenantDTO**

```java
package com.ssitao.code.modular.iam.system.api.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class IamTenantDTO {
    private String id;
    private String tenantCode;
    private String tenantName;
    private String tenantType;
    private String contact;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private String tenantStatus;
    private LocalDateTime expireTime;
    private Integer userLimit;
    private Integer currentUserCount;
    private Long storageLimit;
    private Long usedStorage;
    private String logoUrl;
    private String domain;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
```

**Step 2: 创建IamTenantCreateCommand**

```java
package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

@Data
public class IamTenantCreateCommand {
    private String tenantCode;
    private String tenantName;
    private String tenantType;
    private String contact;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private LocalDateTime expireTime;
    private Integer userLimit;
    private Long storageLimit;
    private String logoUrl;
    private String domain;
    private String remark;
}
```

**Step 3: 创建IamTenantUpdateCommand**

```java
package com.ssitao.code.modular.iam.system.application.command;

import lombok.Data;

@Data
public class IamTenantUpdateCommand {
    private String id;
    private String tenantName;
    private String contact;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private String tenantStatus;
    private LocalDateTime expireTime;
    private Integer userLimit;
    private Long storageLimit;
    private String logoUrl;
    private String domain;
    private String remark;
}
```

**Step 4: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/api/dto/IamTenantDTO.java ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/command/IamTenantCreateCommand.java ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/command/IamTenantUpdateCommand.java
git commit -m "feat(iam): add tenant DTO and command classes"
```

---

### Task 2: 创建租户Application Service

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/service/IamTenantAppService.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/service/impl/IamTenantAppServiceImpl.java`

**Step 1: 创建服务接口**

```java
package com.ssitao.code.modular.iam.system.application.service;

import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import java.util.List;

public interface IamTenantAppService {
    String create(IamTenantCreateCommand command);
    void update(IamTenantUpdateCommand command);
    void delete(String id);
    IamTenantDTO getById(String id);
    List<IamTenantDTO> listAll();
    IamTenantDTO getByTenantCode(String tenantCode);
    IamTenantDTO getByDomain(String domain);
    void enable(String id);
    void disable(String id);
}
```

**Step 2: 创建服务实现**

```java
package com.ssitao.code.modular.iam.system.application.service.impl;

import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IamTenantAppServiceImpl implements IamTenantAppService {

    private final IamTenantRepository tenantRepository;

    public IamTenantAppServiceImpl(IamTenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public String create(IamTenantCreateCommand command) {
        IamTenant tenant = IamTenant.create(command.getTenantCode(),
            command.getTenantName(), command.getTenantType());
        tenant.setContact(command.getContact());
        tenant.setContactPhone(command.getContactPhone());
        tenant.setContactEmail(command.getContactEmail());
        tenant.setAddress(command.getAddress());
        tenant.setExpireTime(command.getExpireTime());
        tenant.setUserLimit(command.getUserLimit());
        tenant.setStorageLimit(command.getStorageLimit());
        tenant.setLogoUrl(command.getLogoUrl());
        tenant.setDomain(command.getDomain());
        tenant.setRemark(command.getRemark());
        return tenantRepository.save(tenant);
    }

    @Override
    public void update(IamTenantUpdateCommand command) {
        IamTenant tenant = tenantRepository.findById(command.getId())
            .orElseThrow(() -> new RuntimeException("租户不存在"));
        tenant.setTenantName(command.getTenantName());
        tenant.setContact(command.getContact());
        tenant.setContactPhone(command.getContactPhone());
        tenant.setContactEmail(command.getContactEmail());
        tenant.setAddress(command.getAddress());
        tenant.setTenantStatus(command.getTenantStatus());
        tenant.setExpireTime(command.getExpireTime());
        tenant.setUserLimit(command.getUserLimit());
        tenant.setStorageLimit(command.getStorageLimit());
        tenant.setLogoUrl(command.getLogoUrl());
        tenant.setDomain(command.getDomain());
        tenant.setRemark(command.getRemark());
        tenantRepository.update(tenant);
    }

    @Override
    public void delete(String id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public IamTenantDTO getById(String id) {
        return tenantRepository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<IamTenantDTO> listAll() {
        return tenantRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public IamTenantDTO getByTenantCode(String tenantCode) {
        return tenantRepository.findByTenantCode(tenantCode).map(this::toDTO).orElse(null);
    }

    @Override
    public IamTenantDTO getByDomain(String domain) {
        return tenantRepository.findByDomain(domain).map(this::toDTO).orElse(null);
    }

    @Override
    public void enable(String id) {
        IamTenant tenant = tenantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("租户不存在"));
        tenant.enable();
        tenantRepository.update(tenant);
    }

    @Override
    public void disable(String id) {
        IamTenant tenant = tenantRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("租户不存在"));
        tenant.disable();
        tenantRepository.update(tenant);
    }

    private IamTenantDTO toDTO(IamTenant tenant) {
        IamTenantDTO dto = new IamTenantDTO();
        dto.setId(tenant.getId());
        dto.setTenantCode(tenant.getTenantCode());
        dto.setTenantName(tenant.getTenantName());
        dto.setTenantType(tenant.getTenantType());
        dto.setContact(tenant.getContact());
        dto.setContactPhone(tenant.getContactPhone());
        dto.setContactEmail(tenant.getContactEmail());
        dto.setAddress(tenant.getAddress());
        dto.setTenantStatus(tenant.getTenantStatus());
        dto.setExpireTime(tenant.getExpireTime());
        dto.setUserLimit(tenant.getUserLimit());
        dto.setCurrentUserCount(tenant.getCurrentUserCount());
        dto.setStorageLimit(tenant.getStorageLimit());
        dto.setUsedStorage(tenant.getUsedStorage());
        dto.setLogoUrl(tenant.getLogoUrl());
        dto.setDomain(tenant.getDomain());
        dto.setRemark(tenant.getRemark());
        dto.setCreateTime(tenant.getCreateTime());
        dto.setUpdateTime(tenant.getUpdateTime());
        return dto;
    }
}
```

**Step 3: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/service/IamTenantAppService.java ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/application/service/impl/IamTenantAppServiceImpl.java
git commit -m "feat(iam): add tenant application service"
```

---

### Task 3: 创建租户管理Controller

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/controller/IamTenantController.java`

**Step 1: 创建Controller**

```java
package com.ssitao.code.modular.iam.system.controller;

import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "租户管理", description = "租户CRUD操作")
@RestController
@RequestMapping("/iam/tenant")
public class IamTenantController {

    private final IamTenantAppService tenantAppService;

    public IamTenantController(IamTenantAppService tenantAppService) {
        this.tenantAppService = tenantAppService;
    }

    @PostMapping
    @Operation(summary = "创建租户")
    public ResponseEntity<String> create(@RequestBody IamTenantCreateCommand command) {
        return ResponseEntity.ok(tenantAppService.create(command));
    }

    @PutMapping
    @Operation(summary = "更新租户")
    public ResponseEntity<Void> update(@RequestBody IamTenantUpdateCommand command) {
        tenantAppService.update(command);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除租户")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tenantAppService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取租户详情")
    public ResponseEntity<IamTenantDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(tenantAppService.getById(id));
    }

    @GetMapping("/list")
    @Operation(summary = "获取租户列表")
    public ResponseEntity<List<IamTenantDTO>> listAll() {
        return ResponseEntity.ok(tenantAppService.listAll());
    }

    @GetMapping("/code/{tenantCode}")
    @Operation(summary = "根据编码获取租户")
    public ResponseEntity<IamTenantDTO> getByTenantCode(@PathVariable String tenantCode) {
        return ResponseEntity.ok(tenantAppService.getByTenantCode(tenantCode));
    }

    @GetMapping("/domain/{domain}")
    @Operation(summary = "根据域名获取租户")
    public ResponseEntity<IamTenantDTO> getByDomain(@PathVariable String domain) {
        return ResponseEntity.ok(tenantAppService.getByDomain(domain));
    }

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用租户")
    public ResponseEntity<Void> enable(@PathVariable String id) {
        tenantAppService.enable(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用租户")
    public ResponseEntity<Void> disable(@PathVariable String id) {
        tenantAppService.disable(id);
        return ResponseEntity.ok().build();
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/controller/IamTenantController.java
git commit -m "feat(iam): add tenant controller"
```

---

### Task 4: 创建租户上下文Holder

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/domain/context/TenantContext.java`

**Step 1: 创建TenantContext**

```java
package com.ssitao.code.modular.iam.system.domain.context;

/**
 * 租户上下文
 * 使用ThreadLocal存储当前线程的租户信息
 */
public class TenantContext {

    private static final ThreadLocal<String> TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<String> TENANT_CODE = new ThreadLocal<>();

    /**
     * 设置当前租户ID
     */
    public static void setTenantId(String tenantId) {
        TENANT_ID.set(tenantId);
    }

    /**
     * 获取当前租户ID
     */
    public static String getTenantId() {
        return TENANT_ID.get();
    }

    /**
     * 设置当前租户编码
     */
    public static void setTenantCode(String tenantCode) {
        TENANT_CODE.set(tenantCode);
    }

    /**
     * 获取当前租户编码
     */
    public static String getTenantCode() {
        return TENANT_CODE.get();
    }

    /**
     * 判断是否有租户上下文
     */
    public static boolean hasTenant() {
        return TENANT_ID.get() != null;
    }

    /**
     * 清除租户上下文
     */
    public static void clear() {
        TENANT_ID.remove();
        TENANT_CODE.remove();
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/domain/context/TenantContext.java
git commit -m "feat(iam): add tenant context holder"
```

---

### Task 5: 创建多租户拦截器

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/infrastructure/tenant/TenantInterceptor.java`
- Modify: `ssitao-code-boot/src/main/java/com/ssitao/code/config/WebConfig.java` (添加拦截器注册)

**Step 1: 创建TenantInterceptor**

```java
package com.ssitao.code.modular.iam.system.infrastructure.tenant;

import com.ssitao.code.modular.iam.system.domain.context.TenantContext;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import cn.dev33.satoken.stp.StpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {

    private final IamTenantRepository tenantRepository;

    public TenantInterceptor(IamTenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从请求头获取租户ID
        String tenantId = request.getHeader("X-Tenant-Id");

        // 2. 如果没有，从域名获取
        if (tenantId == null || tenantId.isEmpty()) {
            String host = request.getHeader("Host");
            if (host != null && host.contains(".")) {
                String domain = host.split(":")[0];
                String[] parts = domain.split("\\.");
                if (parts.length >= 2) {
                    String subDomain = parts[0];
                    tenantId = tenantRepository.findByDomain(subDomain)
                        .map(IamTenant::getId).orElse(null);
                }
            }
        }

        // 3. 如果是登录用户，从Token获取租户
        if ((tenantId == null || tenantId.isEmpty()) && StpUtil.isLogin()) {
            String loginId = StpUtil.getLoginIdAsString();
            // TODO: 从用户信息获取租户ID
        }

        // 4. 设置租户上下文
        if (tenantId != null && !tenantId.isEmpty()) {
            TenantContext.setTenantId(tenantId);
            tenantRepository.findById(tenantId).ifPresent(tenant ->
                TenantContext.setTenantCode(tenant.getTenantCode()));
            log.debug("设置租户上下文: {}", tenantId);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear();
    }
}
```

**Step 2: 注册拦截器到WebConfig**

在WebConfig.java的addInterceptors方法中添加:

```java
@Autowired
private TenantInterceptor tenantInterceptor;

@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(tenantInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/login", "/logout", "/doc.html", "/swagger-ui/**", "/webjars/**");
}
```

**Step 3: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/system/infrastructure/tenant/TenantInterceptor.java
git add ssitao-code-boot/src/main/java/com/ssitao/code/config/WebConfig.java
git commit -m "feat(iam): add tenant interceptor"
```

---

### Task 6: 创建数据权限上下文Holder

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/authorization/domain/context/DataPermissionContext.java`

**Step 1: 创建DataPermissionContext**

```java
package com.ssitao.code.modular.iam.authorization.domain.context;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限上下文
 * 存储当前用户的数据权限范围和部门信息
 */
public class DataPermissionContext {

    /**
     * 数据权限范围: ALL/DEPT/DEPT_AND_CHILD/SELF/CUSTOM
     */
    private static final ThreadLocal<String> DATA_SCOPE = new ThreadLocal<>();

    /**
     * 用户所属部门ID
     */
    private static final ThreadLocal<String> DEPT_ID = new ThreadLocal<>();

    /**
     * 用户所属公司ID
     */
    private static final ThreadLocal<String> COMPANY_ID = new ThreadLocal<>();

    /**
     * 自定义数据权限-允许访问的部门ID列表
     */
    private static final ThreadLocal<List<String>> ALLOWED_DEPT_IDS = new ThreadLocal<>();

    public static void setDataScope(String dataScope) {
        DATA_SCOPE.set(dataScope);
    }

    public static String getDataScope() {
        return DATA_SCOPE.get();
    }

    public static void setDeptId(String deptId) {
        DEPT_ID.set(deptId);
    }

    public static String getDeptId() {
        return DEPT_ID.get();
    }

    public static void setCompanyId(String companyId) {
        COMPANY_ID.set(companyId);
    }

    public static String getCompanyId() {
        return COMPANY_ID.get();
    }

    public static void setAllowedDeptIds(List<String> deptIds) {
        ALLOWED_DEPT_IDS.set(deptIds);
    }

    public static List<String> getAllowedDeptIds() {
        return ALLOWED_DEPT_IDS.get();
    }

    public static void clear() {
        DATA_SCOPE.remove();
        DEPT_ID.remove();
        COMPANY_ID.remove();
        ALLOWED_DEPT_IDS.remove();
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/authorization/domain/context/DataPermissionContext.java
git commit -m "feat(iam): add data permission context"
```

---

### Task 7: 创建集团(Group)实体

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/model/IamGroup.java`

**Step 1: 创建IamGroup实体**

```java
package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * IAM集团聚合根
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamGroup {

    private String id;
    private String groupCode;      // 集团编码
    private String groupName;      // 集团名称
    private String leader;         // 负责人
    private String phone;          // 电话
    private String email;          // 邮箱
    private Integer sortOrder;     // 排序
    private Boolean status;         // 状态
    private String tenantId;       // 租户ID
    private String remark;          // 备注
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String creator;
    private String updater;
    private Boolean deleted;

    public static IamGroup create(String groupCode, String groupName, String tenantId) {
        IamGroup group = new IamGroup();
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);
        group.setTenantId(tenantId);
        group.setStatus(true);
        group.setDeleted(false);
        group.setCreateTime(LocalDateTime.now());
        return group;
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/model/IamGroup.java
git commit -m "feat(iam): add group entity"
```

---

### Task 8: 创建用户组织关联实体

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/model/IamUserOrg.java`

**Step 1: 创建IamUserOrg实体**

```java
package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * IAM用户组织关联
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
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/model/IamUserOrg.java
git commit -m "feat(iam): add user-org relation entity"
```

---

### Task 9: 创建组织机构Repository和Service

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/repository/IamGroupRepository.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/repository/IamUserOrgRepository.java`
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/IamOrganizationAppService.java`

**Step 1: 创建Repository接口**

```java
// IamGroupRepository.java
package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamGroup;
import java.util.List;
import java.util.Optional;

public interface IamGroupRepository {
    String save(IamGroup group);
    void update(IamGroup group);
    void deleteById(String id);
    Optional<IamGroup> findById(String id);
    List<IamGroup> findByTenantId(String tenantId);
    List<IamGroup> findAll();
    boolean existsByGroupCode(String groupCode, String tenantId);
}

// IamUserOrgRepository.java
package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamUserOrg;
import java.util.List;
import java.util.Optional;

public interface IamUserOrgRepository {
    String save(IamUserOrg userOrg);
    void deleteByUserId(String userId);
    List<IamUserOrg> findByUserId(String userId);
    Optional<IamUserOrg> findMainByUserId(String userId);
}
```

**Step 2: 创建Service接口**

```java
package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.domain.model.*;
import java.util.List;

public interface IamOrganizationAppService {
    // Group
    String createGroup(IamGroup group);
    void updateGroup(IamGroup group);
    void deleteGroup(String id);
    IamGroup getGroupById(String id);
    List<IamGroup> listGroups(String tenantId);

    // Company
    String createCompany(IamCompany company);
    void updateCompany(IamCompany company);
    void deleteCompany(String id);
    IamCompany getCompanyById(String id);
    List<IamCompany> listCompanies(String tenantId);
    List<IamCompany> listCompaniesByGroup(String groupId);

    // Department
    String createDepartment(IamDepartment department);
    void updateDepartment(IamDepartment department);
    void deleteDepartment(String id);
    IamDepartment getDepartmentById(String id);
    List<IamDepartment> listDepartments(String tenantId);
    List<IamDepartment> listDepartmentsByCompany(String companyId);

    // User Org
    void assignUserOrg(String userId, List<IamUserOrg> userOrgs);
    List<IamUserOrg> getUserOrgs(String userId);
    IamUserOrg getMainUserOrg(String userId);
}
```

**Step 3: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/repository/IamGroupRepository.java
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/domain/repository/IamUserOrgRepository.java
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/application/service/IamOrganizationAppService.java
git commit -m "feat(iam): add organization repository and service interfaces"
```

---

### Task 10: 创建组织机构Controller

**Files:**
- Create: `ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/controller/IamOrganizationController.java`

**Step 1: 创建Controller**

```java
package com.ssitao.code.modular.iam.organization.controller;

import com.ssitao.code.modular.iam.organization.domain.model.*;
import com.ssitao.code.modular.iam.organization.application.service.IamOrganizationAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "组织机构管理", description = "集团/公司/部门/岗位管理")
@RestController
@RequestMapping("/iam/org")
public class IamOrganizationController {

    private final IamOrganizationAppService orgService;

    public IamOrganizationController(IamOrganizationAppService orgService) {
        this.orgService = orgService;
    }

    // ====== 集团管理 ======
    @PostMapping("/group")
    @Operation(summary = "创建集团")
    public ResponseEntity<String> createGroup(@RequestBody IamGroup group) {
        return ResponseEntity.ok(orgService.createGroup(group));
    }

    @PutMapping("/group")
    @Operation(summary = "更新集团")
    public ResponseEntity<Void> updateGroup(@RequestBody IamGroup group) {
        orgService.updateGroup(group);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/group/{id}")
    @Operation(summary = "删除集团")
    public ResponseEntity<Void> deleteGroup(@PathVariable String id) {
        orgService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group/{id}")
    @Operation(summary = "获取集团详情")
    public ResponseEntity<IamGroup> getGroup(@PathVariable String id) {
        return ResponseEntity.ok(orgService.getGroupById(id));
    }

    @GetMapping("/group/list")
    @Operation(summary = "获取集团列表")
    public ResponseEntity<List<IamGroup>> listGroups(@RequestParam String tenantId) {
        return ResponseEntity.ok(orgService.listGroups(tenantId));
    }

    // ====== 公司管理 ======
    @PostMapping("/company")
    @Operation(summary = "创建公司")
    public ResponseEntity<String> createCompany(@RequestBody IamCompany company) {
        return ResponseEntity.ok(orgService.createCompany(company));
    }

    @GetMapping("/company/list")
    @Operation(summary = "获取公司列表")
    public ResponseEntity<List<IamCompany>> listCompanies(@RequestParam String tenantId) {
        return ResponseEntity.ok(orgService.listCompanies(tenantId));
    }

    @GetMapping("/company/by-group/{groupId}")
    @Operation(summary = "获取集团下的公司")
    public ResponseEntity<List<IamCompany>> listCompaniesByGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(orgService.listCompaniesByGroup(groupId));
    }

    // ====== 部门管理 ======
    @PostMapping("/department")
    @Operation(summary = "创建部门")
    public ResponseEntity<String> createDepartment(@RequestBody IamDepartment department) {
        return ResponseEntity.ok(orgService.createDepartment(department));
    }

    @GetMapping("/department/list")
    @Operation(summary = "获取部门列表")
    public ResponseEntity<List<IamDepartment>> listDepartments(@RequestParam String tenantId) {
        return ResponseEntity.ok(orgService.listDepartments(tenantId));
    }

    @GetMapping("/department/by-company/{companyId}")
    @Operation(summary = "获取公司下的部门")
    public ResponseEntity<List<IamDepartment>> listDepartmentsByCompany(@PathVariable String companyId) {
        return ResponseEntity.ok(orgService.listDepartmentsByCompany(companyId));
    }

    // ====== 用户组织关联 ======
    @PostMapping("/user-org")
    @Operation(summary = "分配用户组织")
    public ResponseEntity<Void> assignUserOrg(@RequestParam String userId, @RequestBody List<IamUserOrg> userOrgs) {
        orgService.assignUserOrg(userId, userOrgs);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user-org/{userId}")
    @Operation(summary = "获取用户组织")
    public ResponseEntity<List<IamUserOrg>> getUserOrgs(@PathVariable String userId) {
        return ResponseEntity.ok(orgService.getUserOrgs(userId));
    }
}
```

**Step 2: Commit**

```bash
git add ssitao-code-modulars/ssitao-code-modular-iam/src/main/java/com/ssitao/code/modular/iam/organization/controller/IamOrganizationController.java
git commit -m "feat(iam): add organization controller"
```

---

## 阶段二：权限体系 (Tasks 11-20)

### Task 11: 完善权限实体和数据权限规则

### Task 12: 创建数据权限拦截器

### Task 13: 创建权限验证Service

### Task 14: 完善角色管理

### Task 15: 创建用户角色关联

### Task 16: 创建权限菜单Controller

### Task 17: 配置Sa-Token权限验证

### Task 18: 创建数据权限规则实体

### Task 19: 创建角色数据规则关联

### Task 20: 实现行级数据权限过滤

---

## 阶段三：低代码基础框架 (Tasks 21-30)

### Task 21: 创建Meta模块Maven配置

### Task 22: 创建实体定义Entity

### Task 23: 创建字段定义Field

### Task 24: 实现动态Entity生成

### Task 25: 实现动态Mapper

### Task 26: 实现动态CRUD API

### Task 27: 创建表单配置

### Task 28: 创建列表配置

### Task 29: 实现动态表单渲染

### Task 30: 实现动态列表渲染

---

## 阶段四：工作流引擎 (Tasks 31-35)

### Task 31: 创建工作流实体

### Task 32: 创建流程实例

### Task 33: 实现流程引擎服务

### Task 34: 创建待办任务API

### Task 35: 实现流程审批

---

## 实施说明

1. **每个Task为一个独立的功能点**
2. **按照顺序执行，每个Task约30-60分钟**
3. **每个Task完成后需要Commit**
4. **如遇问题，使用 @systematic-debugging skill**

**Plan complete and saved to `docs/plans/2026-03-04-detailed-implementation-plan.md`.**

**Two execution options:**

**1. Subagent-Driven (this session)** - I dispatch fresh subagent per task, review between tasks, fast iteration

**2. Parallel Session (separate)** - Open new session with executing-plans, batch execution with checkpoints

Which approach?
