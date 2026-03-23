package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysConfigDTO;
import com.ssitao.code.modular.iam.application.command.CreateConfigCommand;
import com.ssitao.code.modular.iam.application.command.DeleteConfigCommand;
import com.ssitao.code.modular.iam.application.command.UpdateConfigCommand;
import com.ssitao.code.modular.iam.domain.SysConfig;
import com.ssitao.code.modular.iam.domain.model.SysConfigAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysConfigRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysConfigConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 参数配置应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysConfigApplicationService {

    private final SysConfigRepository configRepository;
    private final SysConfigConverter configConverter;
    private final ISysConfigService configService;

    /**
     * 查询参数配置列表
     */
    public List<SysConfigDTO> listConfigs(SysConfigDTO query) {
        SysConfig config = new SysConfig();
        if (query != null && query.getConfigName() != null) {
            config.setConfigName(query.getConfigName());
        }
        if (query != null && query.getConfigKey() != null) {
            config.setConfigKey(query.getConfigKey());
        }
        if (query != null && query.getConfigType() != null) {
            config.setConfigType(query.getConfigType());
        }
        List<SysConfig> list = configService.selectConfigList(config);
        return configConverter.toDTOListFromConfig(list);
    }

    /**
     * 根据ID查询参数配置
     */
    public SysConfigDTO getConfigById(Long configId) {
        SysConfigAggregate aggregate = configRepository.findOne(configId);
        if (aggregate == null) {
            throw new ServiceException("参数配置不存在");
        }
        return configConverter.toDTO(aggregate);
    }

    /**
     * 根据参数键名查询参数配置
     */
    public SysConfigDTO getConfigByKey(String configKey) {
        SysConfigAggregate aggregate = configRepository.findByConfigKey(configKey);
        if (aggregate == null) {
            throw new ServiceException("参数配置不存在");
        }
        return configConverter.toDTO(aggregate);
    }

    /**
     * 创建参数配置
     */
    @Transactional
    public Long createConfig(CreateConfigCommand command) {
        // 检查参数键名是否已存在
        if (configRepository.existsByConfigKey(command.getConfigKey())) {
            throw new ServiceException("参数键名已存在");
        }

        // 创建参数配置聚合根
        SysConfigAggregate aggregate = new SysConfigAggregate();
        aggregate.createConfig(
                command.getConfigName(),
                command.getConfigKey(),
                command.getConfigValue(),
                command.getConfigType(),
                ShiroUtils.getLoginName()
        );

        // 保存
        configRepository.save(aggregate);

        log.info("创建参数配置: {}", aggregate.getConfigId());
        return aggregate.getConfigId();
    }

    /**
     * 更新参数配置
     */
    @Transactional
    public void updateConfig(UpdateConfigCommand command) {
        SysConfigAggregate aggregate = configRepository.findOne(command.getConfigId());
        if (aggregate == null) {
            throw new ServiceException("参数配置不存在");
        }

        // 如果是系统内置参数，不允许修改键名
        if (aggregate.isSystem()) {
            throw new ServiceException("系统内置参数，不允许修改");
        }

        // 检查参数键名是否已存在（排除自己）
        SysConfigAggregate existingByKey = configRepository.findByConfigKey(command.getConfigKey());
        if (existingByKey != null && !existingByKey.getConfigId().equals(command.getConfigId())) {
            throw new ServiceException("参数键名已存在");
        }

        // 更新参数配置信息
        aggregate.updateConfig(
                command.getConfigName(),
                command.getConfigKey(),
                command.getConfigValue(),
                command.getConfigType(),
                ShiroUtils.getLoginName()
        );

        // 保存
        configRepository.save(aggregate);

        log.info("更新参数配置: {}", command.getConfigId());
    }

    /**
     * 删除参数配置
     */
    @Transactional
    public void deleteConfigs(DeleteConfigCommand command) {
        for (Long configId : command.getConfigIds()) {
            SysConfigAggregate aggregate = configRepository.findOne(configId);
            if (aggregate != null) {
                // 如果是系统内置参数，不允许删除
                if (aggregate.isSystem()) {
                    throw new ServiceException("系统内置参数，不允许删除");
                }
                configRepository.delete(aggregate);
                log.info("删除参数配置: {}", configId);
            }
        }
    }

    /**
     * 刷新参数缓存
     */
    @Transactional
    public void refreshCache() {
        configService.resetConfigCache();
        log.info("刷新参数缓存");
    }
}
