package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.config.ConfigCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigListReqVO;
import com.ssitao.code.modular.iam.controller.vo.config.ConfigUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.ConfigDO;

import java.util.List;

/**
 * 系统参数服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface ConfigService {

    /**
     * 创建系统参数
     *
     * @param createReqVO 创建请求
     * @return 参数ID
     */
    Long createConfig(ConfigCreateReqVO createReqVO);

    /**
     * 更新系统参数
     *
     * @param updateReqVO 更新请求
     */
    void updateConfig(ConfigUpdateReqVO updateReqVO);

    /**
     * 删除系统参数
     *
     * @param id 参数ID
     */
    void deleteConfig(Long id);

    /**
     * 获取系统参数详情
     *
     * @param id 参数ID
     * @return 系统参数
     */
    ConfigDO getConfig(Long id);

    /**
     * 获取系统参数列表
     *
     * @param reqVO 查询请求
     * @return 系统参数列表
     */
    List<ConfigDO> getConfigList(ConfigListReqVO reqVO);

    /**
     * 根据键名获取参数值
     *
     * @param key 参数键名
     * @return 参数值
     */
    String getConfigValue(String key);

    /**
     * 根据键名获取参数
     *
     * @param key 参数键名
     * @return 系统参数
     */
    ConfigDO getConfigByKey(String key);

}
