package com.ssitao.code.modular.iam.system.application.service.impl;

import cn.hutool.core.util.StrUtil;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * IAM字典应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@Service
public class IamDictAppServiceImpl implements IamDictAppService {

    /**
     * 字典缓存Key前缀
     */
    private static final String DICT_TYPE_LIST_KEY = "dict:type:list:";
    private static final String DICT_DATA_BY_CODE_KEY = "dict:data:code:";
    private static final String DICT_DATA_ALL_KEY = "dict:data:all:";

    /**
     * 缓存过期时间（秒）：24小时
     */
    private static final long CACHE_EXPIRE_SECONDS = 86400;

    @Resource
    private IamDictTypeRepository dictTypeRepository;

    @Resource
    private IamDictDataRepository dictDataRepository;

    @Resource
    private IamDictTypeConverter dictTypeConverter;

    @Resource
    private IamDictDataConverter dictDataConverter;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

        // 清除字典类型列表缓存
        clearDictTypeCache(command.getTenantId());
        // 清除所有字典数据缓存
        clearAllDictDataCache(command.getTenantId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictType(Long id, String tenantId) {
        // 先删除关联的字典数据
        dictDataRepository.deleteByDictTypeId(id.toString(), tenantId);
        // 再删除字典类型
        dictTypeRepository.deleteById(id.toString(), tenantId);

        // 清除字典类型列表缓存
        clearDictTypeCache(tenantId);
        // 清除所有字典数据缓存
        clearAllDictDataCache(tenantId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableDictType(String ids, String tenantId) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            if (!id.trim().isEmpty()) {
                dictTypeRepository.updateStatus(id.trim(), "1", tenantId);
            }
        }
        clearDictTypeCache(tenantId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableDictType(String ids, String tenantId) {
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            if (!id.trim().isEmpty()) {
                dictTypeRepository.updateStatus(id.trim(), "0", tenantId);
            }
        }
        clearDictTypeCache(tenantId);
    }

    @Override
    public IamDictTypeDTO getDictTypeById(Long id, String tenantId) {
        IamDictType dictType = dictTypeRepository.findById(id.toString(), tenantId)
                .orElseThrow(() -> new IllegalArgumentException("字典类型不存在: " + id));
        return convertToDictTypeDTO(dictType);
    }

    @Override
    public List<IamDictTypeDTO> listDictTypes(String tenantId) {
        // 尝试从缓存获取
        String cacheKey = DICT_TYPE_LIST_KEY + tenantId;
        try {
            @SuppressWarnings("unchecked")
            List<IamDictTypeDTO> cachedList = (List<IamDictTypeDTO>) redisTemplate.opsForValue().get(cacheKey);
            if (cachedList != null) {
                log.debug("从缓存获取字典类型列表，tenantId={}", tenantId);
                return cachedList;
            }
        } catch (Exception e) {
            log.warn("获取字典类型缓存失败: {}", e.getMessage());
        }

        // 从数据库查询
        List<IamDictType> dictTypes = dictTypeRepository.findAll(tenantId);
        List<IamDictTypeDTO> result;
        if (dictTypes == null || dictTypes.isEmpty()) {
            // 返回模拟数据
            result = getMockDictTypes();
        } else {
            result = dictTypes.stream()
                    .map(this::convertToDictTypeDTO)
                    .collect(Collectors.toList());
        }

        // 放入缓存
        try {
            redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_SECONDS, TimeUnit.SECONDS);
            log.debug("字典类型列表已缓存，tenantId={}", tenantId);
        } catch (Exception e) {
            log.warn("缓存字典类型列表失败: {}", e.getMessage());
        }

        return result;
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

        // 清除字典数据缓存
        clearDictDataCache(command.getDictTypeCode(), command.getTenantId());
        clearAllDictDataCache(command.getTenantId());

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

        // 清除字典数据缓存
        clearDictDataCache(data.getDictTypeCode(), tenantId);
        clearAllDictDataCache(tenantId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictData(Long id, String tenantId) {
        // 获取字典数据信息用于清除缓存
        String dictTypeCode = null;
        try {
            java.util.Optional<IamDictData> dictData = dictDataRepository.findById(id.toString(), tenantId);
            if (dictData.isPresent()) {
                dictTypeCode = dictData.get().getDictTypeCode();
            }
        } catch (Exception e) {
            log.warn("获取字典数据类型编码失败: {}", e.getMessage());
        }

        dictDataRepository.deleteById(id.toString(), tenantId);

        // 清除字典数据缓存
        if (dictTypeCode != null) {
            clearDictDataCache(dictTypeCode, tenantId);
        }
        clearAllDictDataCache(tenantId);
    }

    @Override
    public List<IamDictDataDTO> listDictDataByTypeCode(String dictTypeCode, String tenantId) {
        // 尝试从缓存获取
        String cacheKey = DICT_DATA_BY_CODE_KEY + tenantId + ":" + dictTypeCode;
        try {
            @SuppressWarnings("unchecked")
            List<IamDictDataDTO> cachedList = (List<IamDictDataDTO>) redisTemplate.opsForValue().get(cacheKey);
            if (cachedList != null) {
                log.debug("从缓存获取字典数据，tenantId={}, dictTypeCode={}", tenantId, dictTypeCode);
                return cachedList;
            }
        } catch (Exception e) {
            log.warn("获取字典数据缓存失败: {}", e.getMessage());
        }

        // 从数据库查询
        List<IamDictData> dictDataList = dictDataRepository.findByDictTypeCode(dictTypeCode, tenantId);
        List<IamDictDataDTO> result;
        if (dictDataList == null || dictDataList.isEmpty()) {
            // 返回模拟数据
            result = getMockDictDataList(dictTypeCode);
        } else {
            result = dictDataList.stream()
                    .map(this::convertToDictDataDTO)
                    .collect(Collectors.toList());
        }

        // 放入缓存
        try {
            redisTemplate.opsForValue().set(cacheKey, result, CACHE_EXPIRE_SECONDS, TimeUnit.SECONDS);
            log.debug("字典数据已缓存，tenantId={}, dictTypeCode={}", tenantId, dictTypeCode);
        } catch (Exception e) {
            log.warn("缓存字典数据失败: {}", e.getMessage());
        }

        return result;
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
        // 设置前端兼容字段
        dto.setCode(dictType.getDictTypeCode());
        dto.setName(dictType.getDictTypeName());
        dto.setLabel(dictType.getDictTypeName());
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
        // 设置前端兼容字段
        dto.setCode(dictData.getDictDataCode());
        dto.setName(dictData.getDictDataLabel());
        dto.setKey(dictData.getDictDataCode());
        dto.setValue(dictData.getDictDataValue());
        dto.setLabel(dictData.getDictDataLabel());
        dto.setYx(dictData.getStatus() != null && dictData.getStatus() ? "1" : "0");
        dto.setDic(dictData.getDictTypeCode());
        // 设置树形结构字段
        dto.setParentId(dictData.getParentId());
        dto.setLayer(dictData.getLayer());
        return dto;
    }

    /**
     * 获取模拟字典类型数据
     */
    private List<IamDictTypeDTO> getMockDictTypes() {
        List<IamDictTypeDTO> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        IamDictTypeDTO status = new IamDictTypeDTO();
        status.setId("1");
        status.setDictTypeCode("sys_status");
        status.setDictTypeName("系统状态");
        status.setCode("sys_status");
        status.setName("系统状态");
        status.setDescription("系统状态字典");
        status.setStatus(true);
        status.setSortOrder(1);
        status.setCreateTime(now);
        list.add(status);

        IamDictTypeDTO yesNo = new IamDictTypeDTO();
        yesNo.setId("2");
        yesNo.setDictTypeCode("sys_yes_no");
        yesNo.setDictTypeName("是否");
        yesNo.setCode("sys_yes_no");
        yesNo.setName("是否");
        yesNo.setDescription("是否字典");
        yesNo.setStatus(true);
        yesNo.setSortOrder(2);
        yesNo.setCreateTime(now);
        list.add(yesNo);

        IamDictTypeDTO gender = new IamDictTypeDTO();
        gender.setId("3");
        gender.setDictTypeCode("sys_gender");
        gender.setDictTypeName("性别");
        gender.setCode("sys_gender");
        gender.setName("性别");
        gender.setDescription("性别字典");
        gender.setStatus(true);
        gender.setSortOrder(3);
        gender.setCreateTime(now);
        list.add(gender);

        IamDictTypeDTO userType = new IamDictTypeDTO();
        userType.setId("4");
        userType.setDictTypeCode("sys_user_type");
        userType.setDictTypeName("用户类型");
        userType.setCode("sys_user_type");
        userType.setName("用户类型");
        userType.setDescription("用户类型字典");
        userType.setStatus(true);
        userType.setSortOrder(4);
        userType.setCreateTime(now);
        list.add(userType);

        IamDictTypeDTO menuType = new IamDictTypeDTO();
        menuType.setId("5");
        menuType.setDictTypeCode("sys_menu_type");
        menuType.setDictTypeName("菜单类型");
        menuType.setCode("sys_menu_type");
        menuType.setName("菜单类型");
        menuType.setDescription("菜单类型字典");
        menuType.setStatus(true);
        menuType.setSortOrder(5);
        menuType.setCreateTime(now);
        list.add(menuType);

        return list;
    }

    /**
     * 获取模拟字典数据
     */
    private List<IamDictDataDTO> getMockDictDataList(String dictTypeCode) {
        List<IamDictDataDTO> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        if ("sys_status".equals(dictTypeCode)) {
            IamDictDataDTO normal = new IamDictDataDTO();
            normal.setId("101");
            normal.setDictTypeId("1");
            normal.setDictTypeCode("sys_status");
            normal.setDictDataCode("0");
            normal.setDictDataValue("0");
            normal.setDictDataLabel("正常");
            normal.setName("正常");
            normal.setKey("0");
            normal.setYx("1");
            normal.setSortOrder(1);
            normal.setStatus(true);
            normal.setCreateTime(now);
            list.add(normal);

            IamDictDataDTO disabled = new IamDictDataDTO();
            disabled.setId("102");
            disabled.setDictTypeId("1");
            disabled.setDictTypeCode("sys_status");
            disabled.setDictDataCode("1");
            disabled.setDictDataValue("1");
            disabled.setDictDataLabel("停用");
            disabled.setName("停用");
            disabled.setKey("1");
            disabled.setYx("1");
            disabled.setSortOrder(2);
            disabled.setStatus(true);
            disabled.setCreateTime(now);
            list.add(disabled);
        } else if ("sys_yes_no".equals(dictTypeCode)) {
            IamDictDataDTO yes = new IamDictDataDTO();
            yes.setId("201");
            yes.setDictTypeId("2");
            yes.setDictTypeCode("sys_yes_no");
            yes.setDictDataCode("Y");
            yes.setDictDataValue("Y");
            yes.setDictDataLabel("是");
            yes.setName("是");
            yes.setKey("Y");
            yes.setYx("1");
            yes.setSortOrder(1);
            yes.setStatus(true);
            yes.setCreateTime(now);
            list.add(yes);

            IamDictDataDTO no = new IamDictDataDTO();
            no.setId("202");
            no.setDictTypeId("2");
            no.setDictTypeCode("sys_yes_no");
            no.setDictDataCode("N");
            no.setDictDataValue("N");
            no.setDictDataLabel("否");
            no.setName("否");
            no.setKey("N");
            no.setYx("1");
            no.setSortOrder(2);
            no.setStatus(true);
            no.setCreateTime(now);
            list.add(no);
        } else if ("sys_gender".equals(dictTypeCode)) {
            String[] genders = {"未知", "男", "女"};
            for (int i = 0; i < genders.length; i++) {
                IamDictDataDTO dto = new IamDictDataDTO();
                dto.setId(String.valueOf(301 + i));
                dto.setDictTypeId("3");
                dto.setDictTypeCode("sys_gender");
                dto.setDictDataCode(String.valueOf(i));
                dto.setDictDataValue(String.valueOf(i));
                dto.setDictDataLabel(genders[i]);
                dto.setName(genders[i]);
                dto.setKey(String.valueOf(i));
                dto.setYx("1");
                dto.setSortOrder(i + 1);
                dto.setStatus(true);
                dto.setCreateTime(now);
                list.add(dto);
            }
        } else if ("sys_user_type".equals(dictTypeCode)) {
            String[] types = {"系统用户", "普通用户", "访客"};
            for (int i = 0; i < types.length; i++) {
                IamDictDataDTO dto = new IamDictDataDTO();
                dto.setId(String.valueOf(401 + i));
                dto.setDictTypeId("4");
                dto.setDictTypeCode("sys_user_type");
                dto.setDictDataCode(String.valueOf(i));
                dto.setDictDataValue(String.valueOf(i));
                dto.setDictDataLabel(types[i]);
                dto.setName(types[i]);
                dto.setKey(String.valueOf(i));
                dto.setYx("1");
                dto.setSortOrder(i + 1);
                dto.setStatus(true);
                dto.setCreateTime(now);
                list.add(dto);
            }
        } else if ("sys_menu_type".equals(dictTypeCode)) {
            String[] types = {"目录", "菜单", "按钮"};
            for (int i = 0; i < types.length; i++) {
                IamDictDataDTO dto = new IamDictDataDTO();
                dto.setId(String.valueOf(501 + i));
                dto.setDictTypeId("5");
                dto.setDictTypeCode("sys_menu_type");
                dto.setDictDataCode(String.valueOf(i));
                dto.setDictDataValue(String.valueOf(i));
                dto.setDictDataLabel(types[i]);
                dto.setName(types[i]);
                dto.setKey(String.valueOf(i));
                dto.setYx("1");
                dto.setSortOrder(i + 1);
                dto.setStatus(true);
                dto.setCreateTime(now);
                list.add(dto);
            }
        }

        return list;
    }

    // ==================== 缓存管理方法 ====================

    /**
     * 清除字典类型列表缓存
     */
    private void clearDictTypeCache(String tenantId) {
        try {
            String cacheKey = DICT_TYPE_LIST_KEY + tenantId;
            redisTemplate.delete(cacheKey);
            log.debug("清除字典类型列表缓存，tenantId={}", tenantId);
        } catch (Exception e) {
            log.warn("清除字典类型列表缓存失败: {}", e.getMessage());
        }
    }

    /**
     * 清除指定字典类型的数据缓存
     */
    private void clearDictDataCache(String dictTypeCode, String tenantId) {
        if (StrUtil.isBlank(dictTypeCode)) {
            return;
        }
        try {
            String cacheKey = DICT_DATA_BY_CODE_KEY + tenantId + ":" + dictTypeCode;
            redisTemplate.delete(cacheKey);
            log.debug("清除字典数据缓存，tenantId={}, dictTypeCode={}", tenantId, dictTypeCode);
        } catch (Exception e) {
            log.warn("清除字典数据缓存失败: {}", e.getMessage());
        }
    }

    /**
     * 清除所有字典数据缓存
     */
    private void clearAllDictDataCache(String tenantId) {
        try {
            String cacheKey = DICT_DATA_ALL_KEY + tenantId;
            redisTemplate.delete(cacheKey);
            log.debug("清除所有字典数据缓存，tenantId={}", tenantId);
        } catch (Exception e) {
            log.warn("清除所有字典数据缓存失败: {}", e.getMessage());
        }
    }
}
