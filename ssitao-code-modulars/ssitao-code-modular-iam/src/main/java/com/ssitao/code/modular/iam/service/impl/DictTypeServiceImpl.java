package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictTypeDO;
import com.ssitao.code.modular.iam.dal.mapper.DictTypeMapper;
import com.ssitao.code.modular.iam.service.DictTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 字典类型服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictType(DictTypeCreateReqVO createReqVO) {
        // 检查字典类型是否已存在
        DictTypeDO existDictType = dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("type", createReqVO.getType())
                        .eq("deleted", 0)
        );
        if (existDictType != null) {
            throw new BusinessException("字典类型已存在: " + createReqVO.getType());
        }

        DictTypeDO dictType = new DictTypeDO();
        BeanUtils.copyProperties(createReqVO, dictType);

        // 设置租户ID
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            dictType.setTenantId(loginUser.getTenantId());
            dictType.setCreator(loginUser.getUsername());
        }

        dictType.setCreateTime(LocalDateTime.now());
        dictType.setUpdateTime(LocalDateTime.now());
        dictType.setDeleted(0);

        dictTypeMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictType(DictTypeUpdateReqVO updateReqVO) {
        DictTypeDO existDictType = dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (existDictType == null) {
            throw new NotFoundException("字典类型不存在: " + updateReqVO.getId());
        }

        // 检查字典类型是否被其他记录使用
        DictTypeDO typeConflict = dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("type", updateReqVO.getType())
                        .ne("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (typeConflict != null) {
            throw new BusinessException("字典类型已存在: " + updateReqVO.getType());
        }

        BeanUtils.copyProperties(updateReqVO, existDictType, "id", "createTime", "creator");

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            existDictType.setUpdater(loginUser.getUsername());
        }
        existDictType.setUpdateTime(LocalDateTime.now());

        dictTypeMapper.update(existDictType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictType(Long id) {
        DictTypeDO dictType = dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (dictType == null) {
            throw new NotFoundException("字典类型不存在: " + id);
        }

        // 逻辑删除
        dictType.setDeleted(1);
        dictType.setUpdateTime(LocalDateTime.now());
        dictTypeMapper.update(dictType);
    }

    @Override
    public DictTypeDO getDictType(Long id) {
        DictTypeDO dictType = dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (dictType == null) {
            throw new NotFoundException("字典类型不存在: " + id);
        }
        return dictType;
    }

    @Override
    public List<DictTypeDO> getDictTypeList(DictTypeListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (StringUtils.hasText(reqVO.getName())) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (StringUtils.hasText(reqVO.getType())) {
            queryWrapper.like("type", reqVO.getType());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("id", false);
        return dictTypeMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public DictTypeDO getDictTypeByType(String type) {
        return dictTypeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("type", type)
                        .eq("deleted", 0)
        );
    }

    @Override
    public boolean isDictTypeExists(String type) {
        Long count = dictTypeMapper.selectCountByQuery(
                QueryWrapper.create()
                        .eq("type", type)
                        .eq("deleted", 0)
        );
        return count != null && count > 0;
    }

}
