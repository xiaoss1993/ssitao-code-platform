package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.oauth2.OAuth2ClientCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.oauth2.OAuth2ClientUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.OAuth2ClientDO;

import java.util.List;

/**
 * OAuth2客户端服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface OAuth2ClientService {

    /**
     * 创建OAuth2客户端
     *
     * @param createReqVO 创建请求
     * @return 客户端ID
     */
    Long createClient(OAuth2ClientCreateReqVO createReqVO);

    /**
     * 更新OAuth2客户端
     *
     * @param updateReqVO 更新请求
     */
    void updateClient(OAuth2ClientUpdateReqVO updateReqVO);

    /**
     * 删除OAuth2客户端
     *
     * @param id 客户端ID
     */
    void deleteClient(Long id);

    /**
     * 获取OAuth2客户端详情
     *
     * @param id 客户端ID
     * @return OAuth2客户端
     */
    OAuth2ClientDO getClient(Long id);

    /**
     * 根据客户端标识获取客户端
     *
     * @param clientId 客户端标识
     * @return OAuth2客户端
     */
    OAuth2ClientDO getClientByClientId(String clientId);

    /**
     * 获取OAuth2客户端列表
     *
     * @return OAuth2客户端列表
     */
    List<OAuth2ClientDO> getClientList();

}
