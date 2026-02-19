package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.dict.DictTypeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.DictTypeDO;

import java.util.List;

/**
 * 字典类型服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface DictTypeService {

    /**
     * 创建字典类型
     *
     * @param createReqVO 创建请求
     * @return 字典类型ID
     */
    Long createDictType(DictTypeCreateReqVO createReqVO);

    /**
     * 更新字典类型
     *
     * @param updateReqVO 更新请求
     */
    void updateDictType(DictTypeUpdateReqVO updateReqVO);

    /**
     * 删除字典类型
     *
     * @param id 字典类型ID
     */
    void deleteDictType(Long id);

    /**
     * 获取字典类型详情
     *
     * @param id 字典类型ID
     * @return 字典类型
     */
    DictTypeDO getDictType(Long id);

    /**
     * 获取字典类型列表
     *
     * @param reqVO 查询请求
     * @return 字典类型列表
     */
    List<DictTypeDO> getDictTypeList(DictTypeListReqVO reqVO);

    /**
     * 根据类型获取字典类型
     *
     * @param type 字典类型
     * @return 字典类型
     */
    DictTypeDO getDictTypeByType(String type);

    /**
     * 检查字典类型是否存在
     *
     * @param type 字典类型
     * @return 是否存在
     */
    boolean isDictTypeExists(String type);

}
