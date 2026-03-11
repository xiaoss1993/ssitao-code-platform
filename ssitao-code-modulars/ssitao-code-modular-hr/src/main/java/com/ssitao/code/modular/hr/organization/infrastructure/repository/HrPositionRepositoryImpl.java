package com.ssitao.code.modular.hr.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.hr.organization.dal.dataobject.HrPositionDO;
import com.ssitao.code.modular.hr.organization.dal.mapper.HrPositionMapper;
import com.ssitao.code.modular.hr.organization.domain.model.HrPosition;
import com.ssitao.code.modular.hr.organization.domain.repository.HrPositionRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HrPositionRepositoryImpl implements HrPositionRepository {

    @Resource
    private HrPositionMapper positionMapper;

    @Override
    public Optional<HrPosition> findById(String id, String tenantId) {
        HrPositionDO hrPositionDO = positionMapper.selectOneById(id);
        return Optional.ofNullable(toDomain(hrPositionDO));
    }

    @Override
    public List<HrPosition> findAll(String tenantId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("tenant_id", tenantId).eq("deleted", 0);
        wrapper.orderBy("position_sort", true);
        List<HrPositionDO> dos = positionMapper.selectListByQuery(wrapper);
        return dos.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HrPosition> findByDeptId(String deptId, String tenantId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("dept_id", deptId).eq("tenant_id", tenantId).eq("deleted", 0);
        wrapper.orderBy("position_sort", true);
        List<HrPositionDO> dos = positionMapper.selectListByQuery(wrapper);
        return dos.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public boolean existsByPositionCode(String positionCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("position_code", positionCode).eq("tenant_id", tenantId).eq("deleted", 0);
        if (excludeId != null) {
            wrapper.ne("id", excludeId);
        }
        return positionMapper.selectCountByQuery(wrapper) > 0;
    }

    @Override
    public String save(HrPosition position) {
        HrPositionDO hrPositionDO = toDO(position);
        positionMapper.insert(hrPositionDO);
        return hrPositionDO.getId();
    }

    @Override
    public void update(HrPosition position) {
        HrPositionDO hrPositionDO = toDO(position);
        positionMapper.update(hrPositionDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        HrPositionDO hrPositionDO = new HrPositionDO();
        hrPositionDO.setId(id);
        hrPositionDO.setDeleted(1);
        positionMapper.update(hrPositionDO);
    }

    private HrPosition toDomain(HrPositionDO hrPositionDO) {
        if (hrPositionDO == null) return null;
        HrPosition position = new HrPosition();
        position.setId(hrPositionDO.getId());
        position.setPositionCode(hrPositionDO.getPositionCode());
        position.setPositionName(hrPositionDO.getPositionName());
        position.setDeptId(hrPositionDO.getDeptId());
        position.setPositionLevel(hrPositionDO.getPositionLevel());
        position.setPositionType(hrPositionDO.getPositionType());
        position.setPositionSummary(hrPositionDO.getPositionSummary());
        position.setPositionRequirement(hrPositionDO.getPositionRequirement());
        position.setPositionSort(hrPositionDO.getPositionSort());
        position.setStatus(hrPositionDO.getStatus());
        position.setTenantId(hrPositionDO.getTenantId());
        position.setCreator(hrPositionDO.getCreator());
        position.setCreateTime(hrPositionDO.getCreateTime());
        position.setUpdater(hrPositionDO.getUpdater());
        position.setUpdateTime(hrPositionDO.getUpdateTime());
        position.setDeleted(hrPositionDO.getDeleted());
        return position;
    }

    private HrPositionDO toDO(HrPosition position) {
        HrPositionDO hrPositionDO = new HrPositionDO();
        hrPositionDO.setId(position.getId());
        hrPositionDO.setPositionCode(position.getPositionCode());
        hrPositionDO.setPositionName(position.getPositionName());
        hrPositionDO.setDeptId(position.getDeptId());
        hrPositionDO.setPositionLevel(position.getPositionLevel());
        hrPositionDO.setPositionType(position.getPositionType());
        hrPositionDO.setPositionSummary(position.getPositionSummary());
        hrPositionDO.setPositionRequirement(position.getPositionRequirement());
        hrPositionDO.setPositionSort(position.getPositionSort());
        hrPositionDO.setStatus(position.getStatus());
        hrPositionDO.setTenantId(position.getTenantId());
        hrPositionDO.setCreator(position.getCreator());
        hrPositionDO.setCreateTime(position.getCreateTime());
        hrPositionDO.setUpdater(position.getUpdater());
        hrPositionDO.setUpdateTime(position.getUpdateTime());
        hrPositionDO.setDeleted(position.getDeleted());
        return hrPositionDO;
    }
}
