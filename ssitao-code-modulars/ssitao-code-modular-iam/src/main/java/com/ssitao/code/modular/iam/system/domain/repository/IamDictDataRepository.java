package com.ssitao.code.modular.iam.system.domain.repository;

import com.ssitao.code.modular.iam.system.domain.model.IamDictData;

import java.util.List;
import java.util.Optional;

/**
 * IAM字典数据仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDictDataRepository {

    /**
     * 保存字典数据
     *
     * @param dictData 字典数据聚合根
     * @return 字典数据ID
     */
    String save(IamDictData dictData);

    /**
     * 批量保存字典数据
     *
     * @param dictDataList 字典数据列表
     * @return 字典数据ID列表
     */
    List<String> saveBatch(List<IamDictData> dictDataList);

    /**
     * 更新字典数据
     *
     * @param dictData 字典数据聚合根
     */
    void update(IamDictData dictData);

    /**
     * 根据ID删除字典数据
     *
     * @param id       字典数据ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据字典类型ID删除所有数据
     *
     * @param dictTypeId 字典类型ID
     * @param tenantId   租户ID
     */
    void deleteByDictTypeId(String dictTypeId, String tenantId);

    /**
     * 根据ID查找字典数据
     *
     * @param id       字典数据ID
     * @param tenantId 租户ID
     * @return 字典数据聚合根
     */
    Optional<IamDictData> findById(String id, String tenantId);

    /**
     * 根据字典类型ID查找数据
     *
     * @param dictTypeId 字典类型ID
     * @param tenantId   租户ID
     * @return 字典数据列表
     */
    List<IamDictData> findByDictTypeId(String dictTypeId, String tenantId);

    /**
     * 根据字典类型编码查找数据
     *
     * @param dictTypeCode 字典类型编码
     * @param tenantId    租户ID
     * @return 字典数据列表
     */
    List<IamDictData> findByDictTypeCode(String dictTypeCode, String tenantId);

    /**
     * 根据字典类型编码查找默认数据
     *
     * @param dictTypeCode 字典类型编码
     * @param tenantId    租户ID
     * @return 字典数据聚合根
     */
    Optional<IamDictData> findDefaultByDictTypeCode(String dictTypeCode, String tenantId);

    /**
     * 查找所有字典数据
     *
     * @param tenantId 租户ID
     * @return 字典数据列表
     */
    List<IamDictData> findAll(String tenantId);

}
