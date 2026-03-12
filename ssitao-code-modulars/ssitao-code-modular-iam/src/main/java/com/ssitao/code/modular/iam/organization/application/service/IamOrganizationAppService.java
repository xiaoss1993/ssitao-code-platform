package com.ssitao.code.modular.iam.organization.application.service;

import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import com.ssitao.code.modular.iam.organization.domain.model.IamDepartment;
import com.ssitao.code.modular.iam.organization.domain.model.IamGroup;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import com.ssitao.code.modular.iam.organization.domain.model.IamUserOrg;

import java.util.List;

/**
 * IAM组织架构应用服务接口
 * 统一管理集团、公司、部门、岗位、用户组织
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamOrganizationAppService {

    // ==================== 集团管理 ====================

    /**
     * 创建集团
     *
     * @param group 集团信息
     * @return 集团ID
     */
    String createGroup(IamGroup group);

    /**
     * 更新集团
     *
     * @param group 集团信息
     */
    void updateGroup(IamGroup group);

    /**
     * 删除集团
     *
     * @param id 集团ID
     */
    void deleteGroup(String id);

    /**
     * 根据ID获取集团
     *
     * @param id 集团ID
     * @return 集团信息
     */
    IamGroup getGroupById(String id);

    /**
     * 获取集团列表
     *
     * @param tenantId 租户ID
     * @return 集团列表
     */
    List<IamGroup> listGroups(String tenantId);

    // ==================== 公司管理 ====================

    /**
     * 创建公司
     *
     * @param company 公司信息
     * @return 公司ID
     */
    String createCompany(IamCompany company);

    /**
     * 更新公司
     *
     * @param company 公司信息
     */
    void updateCompany(IamCompany company);

    /**
     * 删除公司
     *
     * @param id 公司ID
     */
    void deleteCompany(String id);

    /**
     * 根据ID获取公司
     *
     * @param id 公司ID
     * @return 公司信息
     */
    IamCompany getCompanyById(String id);

    /**
     * 获取公司列表
     *
     * @param tenantId 租户ID
     * @return 公司列表
     */
    List<IamCompany> listCompanies(String tenantId);

    /**
     * 根据集团获取公司列表
     *
     * @param groupId 集团ID
     * @return 公司列表
     */
    List<IamCompany> listCompaniesByGroup(String groupId);

    // ==================== 部门管理 ====================

    /**
     * 创建部门
     *
     * @param department 部门信息
     * @return 部门ID
     */
    String createDepartment(IamDepartment department);

    /**
     * 更新部门
     *
     * @param department 部门信息
     */
    void updateDepartment(IamDepartment department);

    /**
     * 删除部门
     *
     * @param id 部门ID
     */
    void deleteDepartment(String id);

    /**
     * 根据ID获取部门
     *
     * @param id 部门ID
     * @return 部门信息
     */
    IamDepartment getDepartmentById(String id);

    /**
     * 获取部门列表
     *
     * @param tenantId 租户ID
     * @return 部门列表
     */
    List<IamDepartment> listDepartments(String tenantId);

    /**
     * 根据公司获取部门列表
     *
     * @param companyId 公司ID
     * @return 部门列表
     */
    List<IamDepartment> listDepartmentsByCompany(String companyId);

    // ==================== 岗位管理 ====================

    /**
     * 创建岗位
     *
     * @param post 岗位信息
     * @return 岗位ID
     */
    String createPost(IamPost post);

    /**
     * 更新岗位
     *
     * @param post 岗位信息
     */
    void updatePost(IamPost post);

    /**
     * 删除岗位
     *
     * @param id 岗位ID
     */
    void deletePost(String id);

    /**
     * 根据ID获取岗位
     *
     * @param id 岗位ID
     * @return 岗位信息
     */
    IamPost getPostById(String id);

    /**
     * 获取岗位列表
     *
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<IamPost> listPosts(String tenantId);

    /**
     * 根据部门获取岗位列表
     *
     * @param deptId 部门ID
     * @return 岗位列表
     */
    List<IamPost> listPostsByDepartment(String deptId);

    // ==================== 用户组织管理 ====================

    /**
     * 分配用户组织
     *
     * @param userOrg 用户组织信息
     * @return 用户组织ID
     */
    String assignUserOrg(IamUserOrg userOrg);

    /**
     * 获取用户的所有组织
     *
     * @param userId 用户ID
     * @return 用户组织列表
     */
    List<IamUserOrg> getUserOrgs(String userId);

    /**
     * 获取用户主组织
     *
     * @param userId 用户ID
     * @return 用户主组织
     */
    IamUserOrg getMainUserOrg(String userId);
}
