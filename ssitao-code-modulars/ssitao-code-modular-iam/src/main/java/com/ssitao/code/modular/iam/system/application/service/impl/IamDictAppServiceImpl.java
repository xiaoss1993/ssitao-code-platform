package com.ssitao.code.modular.iam.system.application.service.impl;

import com.ssitao.code.modular.iam.system.application.command.IamDictDataCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictTypeCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictTypeUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamDictAppService;
import com.ssitao.code.modular.iam.system.api.dto.IamDictDataDTO;
import com.ssitao.code.modular.iam.system.api.dto.IamDictTypeDTO;
import com.ssitao.code.modular.iam.system.domain.model.IamDictData;
import com.ssitao.code.modular.iam.system.domain.model.IamDictType;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictDataRepository;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictTypeRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictDataConverter;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictTypeConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM字典应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamDictAppServiceImpl implements IamDictAppService {

    @Resource
    private IamDictTypeRepository dictTypeRepository;

    @Resource
    private IamDictDataRepository dictDataRepository;

    @Resource
    private IamDictTypeConverter dictTypeConverter;

    @Resource
    private IamDictDataConverter dictDataConverter;

    // ==================== 字典类型管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictType(IamDictTypeCreateCommand command) {
        // 检查字典类型编码是否已存在
        if (dictTypeRepository.existsByDictTypeCode(command.getDictTypeCode(), command.getTenantId(), null)) {
            throw new IllegalArgumentException("字典类型编码已存在: " + command.getDictTypeCode());
        }

        // 创建字典类型聚合根
        IamDictType dictType = IamDictType.create(
                command.getDictTypeCode(),
                command.getDictTypeName(),
                command.getTenantId()
        );

        // 设置ID
        dictType.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        dictType.setDescription(command.getDescription());
        dictType.setSortOrder(command.getSortOrder());
        dictType.setRemark(command.getRemark());

        String id = dictTypeRepository.save(dictType);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(IamDictTypeUpdateCommand command) {
        IamDictType dictType = dictTypeRepository.findById(command.getId().toString(), command.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("字典类型不存在: " + command.getId()));

        // 更新属性
        if (command.getDictTypeName() != null) {
            dictType.setDictTypeName(command.getDictTypeName());
        }
        dictType.setDescription(command.getDescription());
        dictType.setSortOrder(command.getSortOrder());
        dictType.setRemark(command.getRemark());
        dictType.setUpdateTime(LocalDateTime.now());

        dictTypeRepository.update(dictType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictType(Long id, String tenantId) {
        // 先删除关联的字典数据
        dictDataRepository.deleteByDictTypeId(id.toString(), tenantId);
        // 再删除字典类型
        dictTypeRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public IamDictTypeDTO getDictTypeById(Long id, String tenantId) {
        IamDictType dictType = dictTypeRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("字典类型不存在: " + id));
        return convertToDictTypeDTO(dictType);
    }

    @Override
    public List<IamDictTypeDTO> listDictTypes(String tenantId) {
        List<IamDictType> dictTypes = dictTypeRepository.findAll(tenantId);
        return dictTypes.stream()
                .map(this::convertToDictTypeDTO)
                .collect(Collectors.toList());
    }

    // ==================== 字典数据管理 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictData(IamDictDataCreateCommand command) {
        // 创建字典数据聚合根
        IamDictData dictData = IamDictData.create(
                command.getDictTypeId().toString(),
                command.getDictTypeCode(),
                command.getDictDataCode(),
                command.getDictDataLabel(),
                command.getDictDataValue(),
                command.getTenantId()
        );

        // 设置ID
        dictData.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        dictData.setCssClass(command.getCssClass());
        dictData.setListClass(command.getListClass());
        if (Boolean.TRUE.equals(command.getIsDefault())) {
            dictData.setAsDefault();
        }
        dictData.setSortOrder(command.getSortOrder());
        dictData.setRemark(command.getRemark());

        String id = dictDataRepository.save(dictData);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> batchCreateDictData(List<IamDictDataCreateCommand> commands) {
        return commands.stream()
                .map(this::createDictData)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictData(IamDictDataDTO dictData, String tenantId) {
        IamDictData data = dictDataRepository.findById(dictData.getId().toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("字典数据不存在: " + dictData.getId()));

        // 更新属性
        data.setDictDataCode(dictData.getDictDataCode());
        data.setDictDataValue(dictData.getDictDataValue());
        data.setDictDataLabel(dictData.getDictDataLabel());
        data.setCssClass(dictData.getCssClass());
        data.setListClass(dictData.getListClass());
        if (Boolean.TRUE.equals(dictData.getIsDefault())) {
            data.setAsDefault();
        } else {
            data.cancelDefault();
        }
        data.setSortOrder(dictData.getSortOrder());
        data.setRemark(dictData.getRemark());
        data.setUpdateTime(LocalDateTime.now());

        dictDataRepository.update(data);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictData(Long id, String tenantId) {
        dictDataRepository.deleteById(id.toString(), tenantId);
    }

    @Override
    public List<IamDictDataDTO> listDictDataByTypeCode(String dictTypeCode, String tenantId) {
        List<IamDictData> dictDataList = dictDataRepository.findByDictTypeCode(dictTypeCode, tenantId);
        return dictDataList.stream()
                .map(this::convertToDictDataDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IamDictDataDTO> listDictDataByTypeId(Long dictTypeId, String tenantId) {
        List<IamDictData> dictDataList = dictDataRepository.findByDictTypeId(dictTypeId.toString(), tenantId);
        return dictDataList.stream()
                .map(this::convertToDictDataDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<IamDictDataDTO>> getAllDictData(String tenantId) {
        List<IamDictType> dictTypes = dictTypeRepository.findAll(tenantId);
        Map<String, List<IamDictDataDTO>> result = new HashMap<>();

        for (IamDictType dictType : dictTypes) {
            List<IamDictData> dictDataList = dictDataRepository.findByDictTypeId(dictType.getId(), tenantId);
            List<IamDictDataDTO> dtoList = dictDataList.stream()
                    .map(this::convertToDictDataDTO)
                    .collect(Collectors.toList());
            result.put(dictType.getDictTypeCode(), dtoList);
        }

        return result;
    }

    /**
     * 转换字典类型为DTO
     */
    private IamDictTypeDTO convertToDictTypeDTO(IamDictType dictType) {
        IamDictTypeDTO dto = new IamDictTypeDTO();
        dto.setId(dictType.getId());
        dto.setDictTypeCode(dictType.getDictTypeCode());
        dto.setDictTypeName(dictType.getDictTypeName());
        dto.setDescription(dictType.getDescription());
        dto.setStatus(dictType.getStatus());
        dto.setTenantId(dictType.getTenantId());
        dto.setSortOrder(dictType.getSortOrder());
        dto.setRemark(dictType.getRemark());
        dto.setCreateTime(dictType.getCreateTime());
        dto.setUpdateTime(dictType.getUpdateTime());
        dto.setCreator(dictType.getCreator());
        dto.setUpdater(dictType.getUpdater());
        return dto;
    }

    /**
     * 转换字典数据为DTO
     */
    private IamDictDataDTO convertToDictDataDTO(IamDictData dictData) {
        IamDictDataDTO dto = new IamDictDataDTO();
        dto.setId(dictData.getId());
        dto.setDictTypeId(dictData.getDictTypeId());
        dto.setDictTypeCode(dictData.getDictTypeCode());
        dto.setDictDataCode(dictData.getDictDataCode());
        dto.setDictDataValue(dictData.getDictDataValue());
        dto.setDictDataLabel(dictData.getDictDataLabel());
        dto.setCssClass(dictData.getCssClass());
        dto.setListClass(dictData.getListClass());
        dto.setIsDefault(dictData.getIsDefault());
        dto.setSortOrder(dictData.getSortOrder());
        dto.setStatus(dictData.getStatus());
        dto.setTenantId(dictData.getTenantId());
        dto.setRemark(dictData.getRemark());
        dto.setCreateTime(dictData.getCreateTime());
        dto.setUpdateTime(dictData.getUpdateTime());
        dto.setCreator(dictData.getCreator());
        dto.setUpdater(dictData.getUpdater());
        return dto;
    }
}
