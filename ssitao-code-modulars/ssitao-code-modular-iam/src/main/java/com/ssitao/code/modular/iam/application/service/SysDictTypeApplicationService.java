package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysDictType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysDictTypeDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictTypeCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDictTypeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictTypeCommand;
import com.ssitao.code.modular.iam.domain.model.SysDictTypeAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDictTypeRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysDictTypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典类型应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictTypeApplicationService {

    private final SysDictTypeRepository dictTypeRepository;
    private final SysDictTypeConverter dictTypeConverter;
    private final ISysDictTypeService dictTypeService;

    public List<SysDictTypeDTO> listDictTypes(SysDictTypeDTO query) {
        SysDictType dictType = new SysDictType();
        if (query != null && query.getDictName() != null) {
            dictType.setDictName(query.getDictName());
        }
        if (query != null && query.getDictType() != null) {
            dictType.setDictType(query.getDictType());
        }
        if (query != null && query.getStatus() != null) {
            dictType.setStatus(query.getStatus());
        }
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return dictTypeConverter.toDTOListFromDictType(list);
    }

    public SysDictTypeDTO getDictTypeById(Long dictId) {
        SysDictTypeAggregate aggregate = dictTypeRepository.findOne(dictId);
        if (aggregate == null) {
            throw new ServiceException("字典类型不存在");
        }
        return dictTypeConverter.toDTO(aggregate);
    }

    public SysDictTypeDTO getDictTypeByType(String dictType) {
        SysDictTypeAggregate aggregate = dictTypeRepository.findByDictType(dictType);
        if (aggregate == null) {
            throw new ServiceException("字典类型不存在");
        }
        return dictTypeConverter.toDTO(aggregate);
    }

    public List<SysDictTypeDTO> listAllNormalDictTypes() {
        List<SysDictType> list = dictTypeService.selectDictTypeAll();
        return dictTypeConverter.toDTOListFromDictType(list);
    }

    @Transactional
    public Long createDictType(CreateDictTypeCommand command) {
        if (dictTypeRepository.existsByDictType(command.getDictType())) {
            throw new ServiceException("字典类型已存在");
        }
        SysDictTypeAggregate aggregate = new SysDictTypeAggregate();
        aggregate.createDictType(
                command.getDictName(),
                command.getDictType(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );
        dictTypeRepository.save(aggregate);
        log.info("创建字典类型: {}", aggregate.getDictId());
        return aggregate.getDictId();
    }

    @Transactional
    public void updateDictType(UpdateDictTypeCommand command) {
        SysDictTypeAggregate aggregate = dictTypeRepository.findOne(command.getDictId());
        if (aggregate == null) {
            throw new ServiceException("字典类型不存在");
        }
        SysDictTypeAggregate existing = dictTypeRepository.findByDictType(command.getDictType());
        if (existing != null && !existing.getDictId().equals(command.getDictId())) {
            throw new ServiceException("字典类型已存在");
        }
        aggregate.updateDictType(
                command.getDictName(),
                command.getDictType(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );
        dictTypeRepository.save(aggregate);
        log.info("更新字典类型: {}", command.getDictId());
    }

    @Transactional
    public void deleteDictTypes(DeleteDictTypeCommand command) {
        for (Long dictId : command.getDictIds()) {
            SysDictTypeAggregate aggregate = dictTypeRepository.findOne(dictId);
            if (aggregate != null) {
                dictTypeRepository.delete(aggregate);
                log.info("删除字典类型: {}", dictId);
            }
        }
    }

    @Transactional
    public void refreshCache() {
        dictTypeService.resetDictCache();
        log.info("刷新字典缓存");
    }
}
