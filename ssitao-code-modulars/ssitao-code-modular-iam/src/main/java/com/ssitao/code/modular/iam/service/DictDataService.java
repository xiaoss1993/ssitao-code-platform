package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.dict.DictDataCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictDataUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictDataDO;

import java.util.List;

/**
 * 字典数据服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface DictDataService {

    /**
     * 创建字典数据
     *
     * @param createReqVO 创建请求
     * @return 字典数据ID
     */
    Long createDictData(DictDataCreateReqVO createReqVO);

    /**
     * 更新字典数据
     *
     * @param updateReqVO 更新请求
     */
    void updateDictData(DictDataUpdateReqVO updateReqVO);

    /**
     * 删除字典数据
     *
     * @param id 字典数据ID
     */
    void deleteDictData(Long id);

    /**
     * 获取字典数据详情
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    DictDataDO getDictData(Long id);

    /**
     * 获取字典数据列表
     *
     * @param reqVO 查询请求
     * @return 字典数据列表
     */
    List<DictDataDO> getDictDataList(DictDataListReqVO reqVO);

    /**
     * 根据字典类型获取字典数据列表
     *
     * @param dictType 字典类型
     * @return 字典数据列表
     */
    List<DictDataDO> getDictDataByType(String dictType);

    /**
     * 根据字典类型和键值获取字典数据
     *
     * @param dictType 字典类型
     * @param value    键值
     * @return 字典数据
     */
    DictDataDO getDictData(String dictType, String value);

}
