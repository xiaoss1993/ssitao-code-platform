package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictDataDO;
import com.ssitao.code.modular.iam.dal.mapper.DictDataMapper;
import com.ssitao.code.modular.iam.service.DictDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 字典数据服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DictDataServiceImpl implements DictDataService {

    private final DictDataMapper dictDataMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createDictData(DictDataCreateReqVO createReqVO) {
        DictDataDO dictData = new DictDataDO();
        BeanUtils.copyProperties(createReqVO, dictData);

        // 设置租户ID
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            dictData.setTenantId(loginUser.getTenantId());
            dictData.setCreator(loginUser.getUsername());
        }

        dictData.setCreateTime(LocalDateTime.now());
        dictData.setUpdateTime(LocalDateTime.now());
        dictData.setDeleted(0);

        dictDataMapper.insert(dictData);
        return dictData.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDictData(DictDataUpdateReqVO updateReqVO) {
        DictDataDO existDictData = dictDataMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (existDictData == null) {
            throw new NotFoundException("字典数据不存在: " + updateReqVO.getId());
        }

        BeanUtils.copyProperties(updateReqVO, existDictData, "id", "createTime", "creator");

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            existDictData.setUpdater(loginUser.getUsername());
        }
        existDictData.setUpdateTime(LocalDateTime.now());

        dictDataMapper.update(existDictData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictData(Long id) {
        DictDataDO dictData = dictDataMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (dictData == null) {
            throw new NotFoundException("字典数据不存在: " + id);
        }

        // 逻辑删除
        dictData.setDeleted(1);
        dictData.setUpdateTime(LocalDateTime.now());
        dictDataMapper.update(dictData);
    }

    @Override
    public DictDataDO getDictData(Long id) {
        DictDataDO dictData = dictDataMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (dictData == null) {
            throw new NotFoundException("字典数据不存在: " + id);
        }
        return dictData;
    }

    @Override
    public List<DictDataDO> getDictDataList(DictDataListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (StringUtils.hasText(reqVO.getDictType())) {
            queryWrapper.eq("dict_type", reqVO.getDictType());
        }
        if (StringUtils.hasText(reqVO.getLabel())) {
            queryWrapper.like("label", reqVO.getLabel());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("sort", true).orderBy("id", true);
        return dictDataMapper.selectListByQuery(queryWrapper);
    }

    @Override
    public List<DictDataDO> getDictDataByType(String dictType) {
        return dictDataMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("dict_type", dictType)
                        .eq("status", 1)
                        .eq("deleted", 0)
                        .orderBy("sort", true)
                        .orderBy("id", true)
        );
    }

    @Override
    public DictDataDO getDictData(String dictType, String value) {
        return dictDataMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("dict_type", dictType)
                        .eq("value", value)
                        .eq("deleted", 0)
        );
    }

}
