package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigListReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.ConfigDO;
import com.ssitao.code.modular.iam.dal.mapper.ConfigMapper;
import com.ssitao.code.modular.iam.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统参数服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigMapper configMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createConfig(ConfigCreateReqVO createReqVO) {
        // 检查参数键名是否已存在
        ConfigDO existConfig = configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("key", createReqVO.getKey())
                        .eq("deleted", 0)
        );
        if (existConfig != null) {
            throw new BusinessException("参数键名已存在: " + createReqVO.getKey());
        }

        ConfigDO config = new ConfigDO();
        BeanUtils.copyProperties(createReqVO, config);

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            config.setTenantId(loginUser.getTenantId());
            config.setCreator(loginUser.getUsername());
        }

        config.setCreateTime(LocalDateTime.now());
        config.setUpdateTime(LocalDateTime.now());
        config.setDeleted(0);

        configMapper.insert(config);
        return config.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(ConfigUpdateReqVO updateReqVO) {
        ConfigDO existConfig = configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (existConfig == null) {
            throw new NotFoundException("系统参数不存在: " + updateReqVO.getId());
        }

        // 系统内置参数不允许修改键名
        if (existConfig.getIsSystem() == 1 && !existConfig.getKey().equals(updateReqVO.getKey())) {
            throw new BusinessException("系统内置参数不允许修改键名");
        }

        // 检查参数键名是否被其他记录使用
        ConfigDO keyConflict = configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("key", updateReqVO.getKey())
                        .ne("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (keyConflict != null) {
            throw new BusinessException("参数键名已存在: " + updateReqVO.getKey());
        }

        BeanUtils.copyProperties(updateReqVO, existConfig, "id", "createTime", "creator", "isSystem");

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            existConfig.setUpdater(loginUser.getUsername());
        }
        existConfig.setUpdateTime(LocalDateTime.now());

        configMapper.update(existConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteConfig(Long id) {
        ConfigDO config = configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (config == null) {
            throw new NotFoundException("系统参数不存在: " + id);
        }

        // 系统内置参数不允许删除
        if (config.getIsSystem() == 1) {
            throw new BusinessException("系统内置参数不允许删除");
        }

        // 逻辑删除
        config.setDeleted(1);
        config.setUpdateTime(LocalDateTime.now());
        configMapper.update(config);
    }

    @Override
    public ConfigDO getConfig(Long id) {
        ConfigDO config = configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (config == null) {
            throw new NotFoundException("系统参数不存在: " + id);
        }
        return config;
    }

    @Override
    public List<ConfigDO> getConfigList(ConfigListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (StringUtils.hasText(reqVO.getName())) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (StringUtils.hasText(reqVO.getKey())) {
            queryWrapper.like("key", reqVO.getKey());
        }
        if (reqVO.getIsSystem() != null) {
            queryWrapper.eq("is_system", reqVO.getIsSystem());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("id", true);
        return configMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public String getConfigValue(String key) {
        ConfigDO config = getConfigByKey(key);
        return config != null ? config.getValue() : null;
    }

    @Override
    public ConfigDO getConfigByKey(String key) {
        return configMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("key", key)
                        .eq("deleted", 0)
        );
    }

}
