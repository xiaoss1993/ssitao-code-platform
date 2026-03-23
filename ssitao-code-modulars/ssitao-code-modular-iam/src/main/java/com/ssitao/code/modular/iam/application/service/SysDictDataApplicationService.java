package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.core.domain.entity.SysDictData;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysDictDataDTO;
import com.ssitao.code.modular.iam.application.command.CreateDictDataCommand;
import com.ssitao.code.modular.iam.application.command.DeleteDictDataCommand;
import com.ssitao.code.modular.iam.application.command.UpdateDictDataCommand;
import com.ssitao.code.modular.iam.domain.model.SysDictDataAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysDictDataRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysDictDataConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典数据应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDictDataApplicationService {

    private final SysDictDataRepository dictDataRepository;
    private final SysDictDataConverter dictDataConverter;
    private final ISysDictDataService dictDataService;

    public List<SysDictDataDTO> listDictDatas(SysDictDataDTO query) {
        SysDictData dictData = new SysDictData();
        if (query != null && query.getDictLabel() != null) {
            dictData.setDictLabel(query.getDictLabel());
        }
        if (query != null && query.getDictType() != null) {
            dictData.setDictType(query.getDictType());
        }
        if (query != null && query.getStatus() != null) {
            dictData.setStatus(query.getStatus());
        }
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return dictDataConverter.toDTOListFromDictData(list);
    }

    public SysDictDataDTO getDictDataById(Long dictCode) {
        SysDictDataAggregate aggregate = dictDataRepository.findOne(dictCode);
        if (aggregate == null) {
            throw new ServiceException("字典数据不存在");
        }
        return dictDataConverter.toDTO(aggregate);
    }

    public List<SysDictDataDTO> listDictDatasByType(String dictType) {
        List<SysDictDataAggregate> aggregates = dictDataRepository.findByDictType(dictType);
        return dictDataConverter.toDTOList(aggregates);
    }

    @Transactional
    public Long createDictData(CreateDictDataCommand command) {
        SysDictDataAggregate aggregate = new SysDictDataAggregate();
        aggregate.createDictData(
                command.getDictSort(),
                command.getDictLabel(),
                command.getDictValue(),
                command.getDictType(),
                command.getCssClass(),
                command.getListClass(),
                command.getIsDefault(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );
        dictDataRepository.save(aggregate);
        log.info("创建字典数据: {}", aggregate.getDictCode());
        return aggregate.getDictCode();
    }

    @Transactional
    public void updateDictData(UpdateDictDataCommand command) {
        SysDictDataAggregate aggregate = dictDataRepository.findOne(command.getDictCode());
        if (aggregate == null) {
            throw new ServiceException("字典数据不存在");
        }
        aggregate.updateDictData(
                command.getDictSort(),
                command.getDictLabel(),
                command.getDictValue(),
                command.getDictType(),
                command.getCssClass(),
                command.getListClass(),
                command.getIsDefault(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );
        dictDataRepository.save(aggregate);
        log.info("更新字典数据: {}", command.getDictCode());
    }

    @Transactional
    public void deleteDictDatas(DeleteDictDataCommand command) {
        for (Long dictCode : command.getDictCodes()) {
            SysDictDataAggregate aggregate = dictDataRepository.findOne(dictCode);
            if (aggregate != null) {
                dictDataRepository.delete(aggregate);
                log.info("删除字典数据: {}", dictCode);
            }
        }
    }

    @Transactional
    public void changeStatus(Long dictCode, String status) {
        SysDictDataAggregate aggregate = dictDataRepository.findOne(dictCode);
        if (aggregate == null) {
            throw new ServiceException("字典数据不存在");
        }
        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        dictDataRepository.save(aggregate);
        log.info("修改字典数据状态: {} -> {}", dictCode, status);
    }
}
