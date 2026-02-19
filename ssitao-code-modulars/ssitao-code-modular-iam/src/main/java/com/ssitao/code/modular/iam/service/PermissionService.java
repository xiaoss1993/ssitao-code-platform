package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.permission.PermissionCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionListReqVO;
import com.ssitao.code.modular.iam.controller.vo.permission.PermissionUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.PermissionDO;

import java.util.List;

/**
 * 权限服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface PermissionService {

    /**
     * 创建权限
     *
     * @param createReqVO 创建请求
     * @return 权限ID
     */
    Long createPermission(PermissionCreateReqVO createReqVO);

    /**
     * 更新权限
     *
     * @param updateReqVO 更新请求
     */
    void updatePermission(PermissionUpdateReqVO updateReqVO);

    /**
     * 删除权限
     *
     * @param id 权限ID
     */
    void deletePermission(Long id);

    /**
     * 获取权限详情
     *
     * @param id 权限ID
     * @return 权限信息
     */
    PermissionDO getPermission(Long id);

    /**
     * 获取权限列表
     *
     * @param reqVO 查询请求
     * @return 权限列表
     */
    List<PermissionDO> getPermissionList(PermissionListReqVO reqVO);

    /**
     * 获取权限树
     *
     * @param reqVO 查询请求
     * @return 权限树
     */
    List<PermissionDO> getPermissionTree(PermissionListReqVO reqVO);

}
