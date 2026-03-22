package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysDept;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.common.utils.StringUtils;
import com.ssitao.code.common.utils.uuid.IdUtils;
import com.ssitao.code.modular.iam.api.dto.SysDeptDTO;
import com.ssitao.code.modular.iam.application.command.CreateDeptCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDeptCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDeptCommand;
import com.ssitao.code.modular.iam.domain.model.SysDeptAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDeptRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysDeptConverter;
import com.ssitao.code.modular.iam.service.ISysDeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 部门应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDeptApplicationService {

    private final SysDeptRepository deptRepository;
    private final SysDeptConverter deptConverter;
    private final ISysDeptService deptService;

    /**
     * 查询部门列表
     */
    public List<SysDeptDTO> listDepts(SysDeptDTO query) {
        SysDept dept = new SysDept();
        if (query != null && StringUtils.isNotEmpty(query.getDeptName())) {
            dept.setDeptName(query.getDeptName());
        }
        if (query != null && StringUtils.isNotEmpty(query.getStatus())) {
            dept.setStatus(query.getStatus());
        }
        List<SysDept> list = deptService.selectDeptList(dept);
        return deptConverter.toDTOListFromDept(list);
    }

    /**
     * 根据ID查询部门
     */
    public SysDeptDTO getDeptById(Long deptId) {
        SysDeptAggregate aggregate = deptRepository.findOne(deptId);
        if (aggregate == null) {
            throw new ServiceException("部门不存在");
        }
        return deptConverter.toDTO(aggregate);
    }

    /**
     * 查询所有正常状态的部门
     */
    public List<SysDeptDTO> listAllNormalDepts() {
        List<SysDeptAggregate> aggregates = deptRepository.findAllNormal();
        return deptConverter.toDTOList(aggregates);
    }

    /**
     * 根据父部门ID查询子部门列表
     */
    public List<SysDeptDTO> listDeptsByParentId(Long parentId) {
        List<SysDeptAggregate> aggregates = deptRepository.findByParentId(parentId);
        return deptConverter.toDTOList(aggregates);
    }

    /**
     * 创建部门
     */
    @Transactional
    public Long createDept(CreateDeptCommand command) {
        // 检查部门名称是否已存在
        if (deptRepository.existsByDeptNameAndParentId(command.getDeptName(), command.getParentId())) {
            throw new ServiceException("部门名称已存在");
        }

        // 生成部门ID
        Long deptId = System.nanoTime();

        // 创建部门聚合根
        SysDeptAggregate aggregate = new SysDeptAggregate();
        aggregate.createDept(
                deptId,
                command.getDeptName(),
                command.getParentId(),
                command.getOrderNum(),
                command.getLeader(),
                command.getPhone(),
                command.getEmail(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        deptRepository.save(aggregate);

        log.info("创建部门: {}", deptId);
        return deptId;
    }

    /**
     * 更新部门
     */
    @Transactional
    public void updateDept(UpdateDeptCommand command) {
        SysDeptAggregate aggregate = deptRepository.findOne(command.getDeptId());
        if (aggregate == null) {
            throw new ServiceException("部门不存在");
        }

        // 检查部门名称是否已存在（排除自己）
        if (deptRepository.existsByDeptNameAndParentId(command.getDeptName(), command.getParentId())) {
            throw new ServiceException("部门名称已存在");
        }

        // 更新部门信息
        aggregate.updateDept(
                command.getDeptName(),
                command.getOrderNum(),
                command.getLeader(),
                command.getPhone(),
                command.getEmail(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        deptRepository.save(aggregate);

        log.info("更新部门: {}", command.getDeptId());
    }

    /**
     * 移动部门
     */
    @Transactional
    public void moveDept(Long deptId, Long newParentId, String ancestors) {
        SysDeptAggregate aggregate = deptRepository.findOne(deptId);
        if (aggregate == null) {
            throw new ServiceException("部门不存在");
        }

        aggregate.moveDept(newParentId, ancestors, ShiroUtils.getLoginName());
        deptRepository.save(aggregate);

        log.info("移动部门: {} -> parent: {}", deptId, newParentId);
    }

    /**
     * 删除部门
     */
    @Transactional
    public void deleteDepts(DeleteDeptCommand command) {
        for (Long deptId : command.getDeptIds()) {
            SysDeptAggregate aggregate = deptRepository.findOne(deptId);
            if (aggregate != null) {
                // 检查是否有子部门
                List<SysDeptAggregate> children = deptRepository.findByParentId(deptId);
                if (children != null && !children.isEmpty()) {
                    throw new ServiceException("存在下级部门，不允许删除");
                }
                aggregate.markDeleted(ShiroUtils.getLoginName());
                deptRepository.save(aggregate);
                log.info("删除部门: {}", deptId);
            }
        }
    }

    /**
     * 修改部门状态
     */
    @Transactional
    public void changeStatus(Long deptId, String status) {
        SysDeptAggregate aggregate = deptRepository.findOne(deptId);
        if (aggregate == null) {
            throw new ServiceException("部门不存在");
        }

        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        deptRepository.save(aggregate);

        log.info("修改部门状态: {} -> {}", deptId, status);
    }
}
