package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.role.RoleCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleListReqVO;
import com.ssitao.code.modular.iam.controller.vo.role.RoleUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.RoleDO;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface RoleService {

    /**
     * 创建角色
     *
     * @param createReqVO 创建请求
     * @return 角色ID
     */
    Long createRole(RoleCreateReqVO createReqVO);

    /**
     * 更新角色
     *
     * @param updateReqVO 更新请求
     */
    void updateRole(RoleUpdateReqVO updateReqVO);

    /**
     * 删除角色
     *
     * @param id 角色ID
     */
    void deleteRole(Long id);

    /**
     * 获取角色详情
     *
     * @param id 角色ID
     * @return 角色信息
     */
    RoleDO getRole(Long id);

    /**
     * 获取角色列表
     *
     * @param reqVO 查询请求
     * @return 角色列表
     */
    List<RoleDO> getRoleList(RoleListReqVO reqVO);

    /**
     * 分配权限给角色
     *
     * @param roleId        角色ID
     * @param permissionIds 权限ID列表
     */
    void assignPermissions(Long roleId, List<Long> permissionIds);

    /**
     * 获取角色的权限列表
     *
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<Long> getRolePermissions(Long roleId);

}
