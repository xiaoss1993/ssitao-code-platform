package com.ssitao.code.modular.iam.system.domain.repository;

import com.ssitao.code.modular.iam.system.domain.model.IamDictType;

import java.util.List;
import java.util.Optional;

/**
 * IAM字典类型仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDictTypeRepository {

    /**
     * 保存字典类型
     *
     * @param dictType 字典类型聚合根
     * @return 字典类型ID
     */
    String save(IamDictType dictType);

    /**
     * 更新字典类型
     *
     * @param dictType 字典类型聚合根
     */
    void update(IamDictType dictType);

    /**
     * 根据ID删除字典类型
     *
     * @param id       字典类型ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找字典类型
     *
     * @param id       字典类型ID
     * @param tenantId 租户ID
     * @return 字典类型聚合根
     */
    Optional<IamDictType> findById(String id, String tenantId);

    /**
     * 根据字典类型编码查找
     *
     * @param dictTypeCode 字典类型编码
     * @param tenantId    租户ID
     * @return 字典类型聚合根
     */
    Optional<IamDictType> findByDictTypeCode(String dictTypeCode, String tenantId);

    /**
     * 查找所有字典类型
     *
     * @param tenantId 租户ID
     * @return 字典类型列表
     */
    List<IamDictType> findAll(String tenantId);

    /**
     * 检查字典类型编码是否存在
     *
     * @param dictTypeCode 字典类型编码
     * @param tenantId    租户ID
     * @param excludeId   排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByDictTypeCode(String dictTypeCode, String tenantId, String excludeId);

}
