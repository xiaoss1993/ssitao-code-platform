package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dept.DeptUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DeptDO;
import com.ssitao.code.modular.iam.dal.mapper.DeptMapper;
import com.ssitao.code.modular.iam.dal.mapper.UserMapper;
import com.ssitao.code.modular.iam.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDept(DeptCreateReqVO createReqVO) {
        // 检查部门编码是否已存在
        DeptDO existDept = deptMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("code", createReqVO.getCode())
                        .eq("deleted", 0)
        );
        if (existDept != null) {
            throw new BusinessException("部门编码已存在: " + createReqVO.getCode());
        }

        // 检查父部门是否存在
        if (createReqVO.getParentId() != null && createReqVO.getParentId() > 0) {
            DeptDO parentDept = deptMapper.selectOneById(createReqVO.getParentId());
            if (parentDept == null || parentDept.getDeleted() == 1) {
                throw new NotFoundException("父部门不存在");
            }
        }

        // 创建部门
        DeptDO dept = new DeptDO();
        dept.setParentId(createReqVO.getParentId() != null ? createReqVO.getParentId() : 0L);
        dept.setName(createReqVO.getName());
        dept.setCode(createReqVO.getCode());
        dept.setSort(createReqVO.getSort());
        dept.setLeaderUserId(createReqVO.getLeaderUserId());
        dept.setPhone(createReqVO.getPhone());
        dept.setEmail(createReqVO.getEmail());
        dept.setStatus(createReqVO.getStatus() != null ? createReqVO.getStatus() : 1);
        dept.setRemark(createReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            dept.setTenantId(loginUser.getTenantId());
            dept.setCreator(loginUser.getUsername());
        }
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        dept.setDeleted(0);

        deptMapper.insert(dept);
        log.info("创建部门成功: code={}, name={}", dept.getCode(), dept.getName());
        return dept.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(DeptUpdateReqVO updateReqVO) {
        DeptDO dept = deptMapper.selectOneById(updateReqVO.getId());
        if (dept == null || dept.getDeleted() == 1) {
            throw new NotFoundException("部门不存在");
        }

        // 检查部门编码是否被其他部门使用
        if (!dept.getCode().equals(updateReqVO.getCode())) {
            DeptDO existDept = deptMapper.selectOneByQuery(
                    QueryWrapper.create()
                            .eq("code", updateReqVO.getCode())
                            .ne("id", updateReqVO.getId())
                            .eq("deleted", 0)
            );
            if (existDept != null) {
                throw new BusinessException("部门编码已存在: " + updateReqVO.getCode());
            }
        }

        // 检查父部门是否存在且不能设置为自己或自己的子部门
        if (updateReqVO.getParentId() != null) {
            if (updateReqVO.getParentId().equals(updateReqVO.getId())) {
                throw new BusinessException("父部门不能是自己");
            }
            // 这里简化处理，实际应该检查是否为自己的子部门
            if (updateReqVO.getParentId() > 0) {
                DeptDO parentDept = deptMapper.selectOneById(updateReqVO.getParentId());
                if (parentDept == null || parentDept.getDeleted() == 1) {
                    throw new NotFoundException("父部门不存在");
                }
            }
        }

        // 更新部门
        dept.setParentId(updateReqVO.getParentId() != null ? updateReqVO.getParentId() : 0L);
        dept.setName(updateReqVO.getName());
        dept.setCode(updateReqVO.getCode());
        dept.setSort(updateReqVO.getSort());
        dept.setLeaderUserId(updateReqVO.getLeaderUserId());
        dept.setPhone(updateReqVO.getPhone());
        dept.setEmail(updateReqVO.getEmail());
        if (updateReqVO.getStatus() != null) {
            dept.setStatus(updateReqVO.getStatus());
        }
        dept.setRemark(updateReqVO.getRemark());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            dept.setUpdater(loginUser.getUsername());
        }
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
        log.info("更新部门成功: id={}, code={}, name={}", dept.getId(), dept.getCode(), dept.getName());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDept(Long id) {
        DeptDO dept = deptMapper.selectOneById(id);
        if (dept == null || dept.getDeleted() == 1) {
            throw new NotFoundException("部门不存在");
        }

        // 检查是否有子部门
        List<DeptDO> children = deptMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("parent_id", id)
                        .eq("deleted", 0)
        );
        if (!children.isEmpty()) {
            throw new BusinessException("该部门下有子部门，不能删除");
        }

        // 检查是否有用户
        long userCount = userMapper.selectCountByQuery(
                QueryWrapper.create()
                        .eq("dept_id", id)
                        .eq("deleted", 0)
        );
        if (userCount > 0) {
            throw new BusinessException("该部门下有用户，不能删除");
        }

        // 逻辑删除
        dept.setDeleted(1);
        dept.setUpdateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            dept.setUpdater(loginUser.getUsername());
        }

        deptMapper.update(dept);
        log.info("删除部门成功: id={}, code={}, name={}", dept.getId(), dept.getCode(), dept.getName());
    }

    @Override
    public DeptDO getDept(Long id) {
        DeptDO dept = deptMapper.selectOneById(id);
        if (dept == null || dept.getDeleted() == 1) {
            throw new NotFoundException("部门不存在");
        }
        return dept;
    }

    @Override
    public List<DeptDO> getDeptList(DeptListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (reqVO.getName() != null && !reqVO.getName().isEmpty()) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (reqVO.getCode() != null && !reqVO.getCode().isEmpty()) {
            queryWrapper.like("code", reqVO.getCode());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }
        if (reqVO.getParentId() != null) {
            queryWrapper.eq("parent_id", reqVO.getParentId());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("sort", true).orderBy("id", true);
        return deptMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public List<DeptDO> getDeptTree() {
        List<DeptDO> allDepts = deptMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("deleted", 0)
                        .eq("status", 1)
                        .orderBy("sort", true).orderBy("id", true)
        );

        // 构建部门树
        return buildDeptTree(allDepts, 0L);
    }

    /**
     * 构建部门树
     */
    private List<DeptDO> buildDeptTree(List<DeptDO> allDepts, Long parentId) {
        List<DeptDO> tree = new ArrayList<>();
        for (DeptDO dept : allDepts) {
            if (dept.getParentId().equals(parentId)) {
                tree.add(dept);
                // 递归查找子部门
                dept.setChildren(buildDeptTree(allDepts, dept.getId()));
            }
        }
        return tree;
    }

}
