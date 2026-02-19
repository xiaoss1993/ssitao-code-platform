package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionListReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PermissionDO;
import com.ssitao.code.modular.iam.dal.mapper.PermissionMapper;
import com.ssitao.code.modular.iam.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPermission(PermissionCreateReqVO createReqVO) {
        // 检查权限编码是否已存在
        PermissionDO existPermission = permissionMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("code", createReqVO.getCode())
                        .eq("deleted", 0)
        );
        if (existPermission != null) {
            throw new BusinessException("权限编码已存在: " + createReqVO.getCode());
        }

        // 创建权限
        PermissionDO permission = new PermissionDO();
        permission.setCode(createReqVO.getCode());
        permission.setName(createReqVO.getName());
        permission.setType(createReqVO.getType());
        permission.setParentId(createReqVO.getParentId() != null ? createReqVO.getParentId() : 0L);
        permission.setPath(createReqVO.getPath());
        permission.setComponent(createReqVO.getComponent());
        permission.setIcon(createReqVO.getIcon());
        permission.setSort(createReqVO.getSort());
        permission.setVisible(createReqVO.getVisible() != null ? createReqVO.getVisible() : 1);
        permission.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : 1);
        permission.setRemark(createReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            permission.setTenantId(loginUser.getTenantId());
            permission.setCreator(loginUser.getUsername());
        }
        permission.setCreateTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        permission.setDeleted(0);

        permissionMapper.insert(permission);
        log.info("创建权限成功: code={}, name={}, type={}", permission.getCode(), permission.getName(), permission.getType());
        return permission.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePermission(PermissionUpdateReqVO updateReqVO) {
        PermissionDO permission = permissionMapper.selectOneById(updateReqVO.getId());
        if (permission == null || permission.getDeleted() == 1) {
            throw new NotFoundException("权限不存在");
        }

        // 检查权限编码是否已被其他权限使用
        if (!permission.getCode().equals(updateReqVO.getCode())) {
            PermissionDO existPermission = permissionMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq("code", updateReqVO.getCode())
                            .ne("id", updateReqVO.getId())
                            .eq("deleted", 0)
            );
            if (existPermission != null) {
                throw new BusinessException("权限编码已存在: " + updateReqVO.getCode());
            }
        }

        // 更新权限
        permission.setCode(updateReqVO.getCode());
        permission.setName(updateReqVO.getName());
        permission.setType(updateReqVO.getType());
        if (updateReqVO.getParentId() != null) {
            permission.setParentId(updateReqVO.getParentId());
        }
        permission.setPath(updateReqVO.getPath());
        permission.setComponent(updateReqVO.getComponent());
        permission.setIcon(updateReqVO.getIcon());
        if (updateReqVO.getSort() != null) {
            permission.setSort(updateReqVO.getSort());
        }
        if (updateReqVO.getVisible() != null) {
            permission.setVisible(updateReqVO.getVisible());
        }
        if (updateReqVO.getStatus() != null) {
            permission.setStatus(updateReqVO.getStatus());
        }
        permission.setRemark(updateReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            permission.setUpdater(loginUser.getUsername());
        }
        permission.setUpdateTime(LocalDateTime.now());

        permissionMapper.update(permission);
        log.info("更新权限成功: id={}, code={}, name={}", permission.getId(), permission.getCode(), permission.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePermission(Long id) {
        PermissionDO permission = permissionMapper.selectOneById(id);
        if (permission == null || permission.getDeleted() == 1) {
            throw new NotFoundException("权限不存在");
        }

        // 检查是否有子权限
        PermissionDO childPermission = permissionMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("parent_id", id)
                        .eq("deleted", 0)
        );
        if (childPermission != null) {
            throw new BusinessException("存在子权限，无法删除");
        }

        // 逻辑删除
        permission.setDeleted(1);
        permission.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            permission.setUpdater(loginUser.getUsername());
        }

        permissionMapper.update(permission);
        log.info("删除权限成功: id={}, code={}, name={}", permission.getId(), permission.getCode(), permission.getName());
    }

    @Override
    public PermissionDO getPermission(Long id) {
        PermissionDO permission = permissionMapper.selectOneById(id);
        if (permission == null || permission.getDeleted() == 1) {
            throw new NotFoundException("权限不存在");
        }
        return permission;
    }

    @Override
    public List<PermissionDO> getPermissionList(PermissionListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (reqVO.getCode() != null && !reqVO.getCode().isEmpty()) {
            queryWrapper.like("code", reqVO.getCode());
        }
        if (reqVO.getName() != null && !reqVO.getName().isEmpty()) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (reqVO.getType() != null && !reqVO.getType().isEmpty()) {
            queryWrapper.eq("type", reqVO.getType());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }
        if (reqVO.getVisible() != null) {
            queryWrapper.eq("visible", reqVO.getVisible());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("sort", true).orderBy("id", true);
        return permissionMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public List<PermissionDO> getPermissionTree(PermissionListReqVO reqVO) {
        // 获取所有权限
        List<PermissionDO> allPermissions = getPermissionList(reqVO);

        // 构建权限树
        return buildPermissionTree(allPermissions, 0L);
    }

    /**
     * 构建权限树
     *
     * @param permissions 所有权限列表
     * @param parentId    父权限ID
     * @return 权限树
     */
    private List<PermissionDO> buildPermissionTree(List<PermissionDO> permissions, Long parentId) {
        return permissions.stream()
                .filter(permission -> parentId.equals(permission.getParentId()))
                .peek(permission -> {
                    // 递归设置子权限
                    List<PermissionDO> children = buildPermissionTree(permissions, permission.getId());
                    // 这里可以设置子权限到 permission 对象中，如果需要的话
                })
                .collect(Collectors.toList());
    }

}
