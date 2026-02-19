package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.oauth2.OAuth2ClientCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.oauth2.OAuth2ClientUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.OAuth2ClientDO;
import com.ssitao.code.modular.iam.dal.mapper.OAuth2ClientMapper;
import com.ssitao.code.modular.iam.service.OAuth2ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OAuth2客户端服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ClientServiceImpl implements OAuth2ClientService {

    private final OAuth2ClientMapper oauth2ClientMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createClient(OAuth2ClientCreateReqVO createReqVO) {
        // 检查客户端标识是否已存在
        OAuth2ClientDO existClient = oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("client_id", createReqVO.getClientId())
                        .eq("deleted", 0)
        );
        if (existClient != null) {
            throw new BusinessException("客户端标识已存在: " + createReqVO.getClientId());
        }

        OAuth2ClientDO client = new OAuth2ClientDO();
        BeanUtils.copyProperties(createReqVO, client);

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            client.setTenantId(loginUser.getTenantId());
            client.setCreator(loginUser.getUsername());
        }

        client.setCreateTime(LocalDateTime.now());
        client.setUpdateTime(LocalDateTime.now());
        client.setDeleted(0);

        oauth2ClientMapper.insert(client);
        return client.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateClient(OAuth2ClientUpdateReqVO updateReqVO) {
        OAuth2ClientDO existClient = oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (existClient == null) {
            throw new NotFoundException("OAuth2客户端不存在: " + updateReqVO.getId());
        }

        // 检查客户端标识是否被其他记录使用
        OAuth2ClientDO clientIdConflict = oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("client_id", updateReqVO.getClientId())
                        .ne("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (clientIdConflict != null) {
            throw new BusinessException("客户端标识已存在: " + updateReqVO.getClientId());
        }

        BeanUtils.copyProperties(updateReqVO, existClient, "id", "createTime", "creator");

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            existClient.setUpdater(loginUser.getUsername());
        }
        existClient.setUpdateTime(LocalDateTime.now());

        oauth2ClientMapper.update(existClient);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteClient(Long id) {
        OAuth2ClientDO client = oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (client == null) {
            throw new NotFoundException("OAuth2客户端不存在: " + id);
        }

        // 逻辑删除
        client.setDeleted(1);
        client.setUpdateTime(LocalDateTime.now());
        oauth2ClientMapper.update(client);
    }

    @Override
    public OAuth2ClientDO getClient(Long id) {
        OAuth2ClientDO client = oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (client == null) {
            throw new NotFoundException("OAuth2客户端不存在: " + id);
        }
        return client;
    }

    @Override
    public OAuth2ClientDO getClientByClientId(String clientId) {
        return oauth2ClientMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("client_id", clientId)
                        .eq("deleted", 0)
        );
    }

    @Override
    public List<OAuth2ClientDO> getClientList() {
        return oauth2ClientMapper.selectListByQuery(
                QueryWrapper.create()
                        .eq("deleted", 0)
                        .orderBy("id", true)
        );
    }

}
