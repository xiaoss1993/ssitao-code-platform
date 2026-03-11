package com.ssitao.code.modular.hr.organization.application.service.impl;

import com.ssitao.code.modular.hr.organization.api.dto.HrPositionDTO;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionCreateCommand;
import com.ssitao.code.modular.hr.organization.application.command.HrPositionUpdateCommand;
import com.ssitao.code.modular.hr.organization.application.service.HrPositionAppService;
import com.ssitao.code.modular.hr.organization.domain.model.HrPosition;
import com.ssitao.code.modular.hr.organization.domain.repository.HrPositionRepository;
import com.ssitao.code.modular.hr.organization.infrastructure.converter.HrPositionConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HrPositionAppServiceImpl implements HrPositionAppService {

    @Resource
    private HrPositionRepository positionRepository;

    @Resource
    private HrPositionConverter positionConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createPosition(HrPositionCreateCommand command) {
        if (positionRepository.existsByPositionCode(command.getPositionCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("岗位编码已存在: " + command.getPositionCode());
        }
        HrPosition position = positionConverter.toDomain(command);
        return positionRepository.save(position);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePosition(HrPositionUpdateCommand command) {
        HrPosition position = positionRepository.findById(command.getId(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + command.getId()));

        if (command.getPositionCode() != null && !command.getPositionCode().equals(position.getPositionCode())) {
            if (positionRepository.existsByPositionCode(command.getPositionCode(), command.getTenantId(), command.getId())) {
                throw new IllegalArgumentException("岗位编码已存在: " + command.getPositionCode());
            }
            position.setPositionCode(command.getPositionCode());
        }

        positionConverter.toDomain(command, position);
        position.setUpdateTime(LocalDateTime.now());
        positionRepository.update(position);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deletePosition(String id, String tenantId) {
        positionRepository.deleteById(id, tenantId);
    }

    @Override
    public HrPositionDTO getPositionById(String id, String tenantId) {
        HrPosition position = positionRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        return positionConverter.toDTO(position);
    }

    @Override
    public List<HrPositionDTO> listPositions(String tenantId) {
        List<HrPosition> positions = positionRepository.findAll(tenantId);
        return positionConverter.toDTOList(positions);
    }

    @Override
    public List<HrPositionDTO> listPositionsByDept(String deptId, String tenantId) {
        List<HrPosition> positions = positionRepository.findByDeptId(deptId, tenantId);
        return positionConverter.toDTOList(positions);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enablePosition(String id, String tenantId) {
        HrPosition position = positionRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        position.enable();
        positionRepository.update(position);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disablePosition(String id, String tenantId) {
        HrPosition position = positionRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("岗位不存在: " + id));
        position.disable();
        positionRepository.update(position);
    }
}
