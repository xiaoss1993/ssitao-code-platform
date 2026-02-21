package com.ssitao.code.modular.iam.system.application.service;

import com.ssitao.code.modular.iam.system.application.command.IamDictTypeCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictTypeUpdateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamDictDataCreateCommand;
import com.ssitao.code.modular.iam.system.api.dto.IamDictTypeDTO;
import com.ssitao.code.modular.iam.system.api.dto.IamDictDataDTO;

import java.util.List;
import java.util.Map;

/**
 * IAM字典应用服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamDictAppService {

    // ==================== 字典类型管理 ====================

    /**
     * 创建字典类型
     *
     * @param command 创建命令
     * @return 字典类型ID
     */
    Long createDictType(IamDictTypeCreateCommand command);

    /**
     * 更新字典类型
     *
     * @param command 更新命令
     */
    void updateDictType(IamDictTypeUpdateCommand command);

    /**
     * 删除字典类型
     *
     * @param id       字典类型ID
     * @param tenantId 租户ID
     */
    void deleteDictType(Long id, String tenantId);

    /**
     * 根据ID获取字典类型
     *
     * @param id       字典类型ID
     * @param tenantId 租户ID
     * @return 字典类型DTO
     */
    IamDictTypeDTO getDictTypeById(Long id, String tenantId);

    /**
     * 获取所有字典类型
     *
     * @param tenantId 租户ID
     * @return 字典类型列表
     */
    List<IamDictTypeDTO> listDictTypes(String tenantId);

    // ==================== 字典数据管理 ====================

    /**
     * 创建字典数据
     *
     * @param command 创建命令
     * @return 字典数据ID
     */
    Long createDictData(IamDictDataCreateCommand command);

    /**
     * 批量创建字典数据
     *
     * @param commands 创建命令列表
     * @return 字典数据ID列表
     */
    List<Long> batchCreateDictData(List<IamDictDataCreateCommand> commands);

    /**
     * 更新字典数据
     *
     * @param dictData 字典数据DTO
     * @param tenantId 租户ID
     */
    void updateDictData(IamDictDataDTO dictData, String tenantId);

    /**
     * 删除字典数据
     *
     * @param id       字典数据ID
     * @param tenantId 租户ID
     */
    void deleteDictData(Long id, String tenantId);

    /**
     * 根据字典类型编码获取字典数据
     *
     * @param dictTypeCode 字典类型编码
     * @param tenantId    租户ID
     * @return 字典数据列表
     */
    List<IamDictDataDTO> listDictDataByTypeCode(String dictTypeCode, String tenantId);

    /**
     * 根据字典类型ID获取字典数据
     *
     * @param dictTypeId 字典类型ID
     * @param tenantId   租户ID
     * @return 字典数据列表
     */
    List<IamDictDataDTO> listDictDataByTypeId(Long dictTypeId, String tenantId);

    /**
     * 获取所有字典数据
     *
     * @param tenantId 租户ID
     * @return 字典类型与数据映射
     */
    Map<String, List<IamDictDataDTO>> getAllDictData(String tenantId);

}
