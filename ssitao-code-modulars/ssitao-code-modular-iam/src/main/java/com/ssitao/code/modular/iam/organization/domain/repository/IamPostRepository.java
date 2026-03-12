package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamPost;

import java.util.List;
import java.util.Optional;

/**
 * IAM岗位仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamPostRepository {

    /**
     * 保存岗位
     *
     * @param post 岗位聚合根
     * @return 岗位ID
     */
    String save(IamPost post);

    /**
     * 批量保存岗位
     *
     * @param posts 岗位列表
     * @return 岗位ID列表
     */
    List<String> saveBatch(List<IamPost> posts);

    /**
     * 更新岗位
     *
     * @param post 岗位聚合根
     */
    void update(IamPost post);

    /**
     * 根据ID删除岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     */
    void deleteById(String id, String tenantId);

    /**
     * 根据ID查找岗位
     *
     * @param id       岗位ID
     * @param tenantId 租户ID
     * @return 岗位聚合根
     */
    Optional<IamPost> findById(String id, String tenantId);

    /**
     * 根据岗位编码查找岗位
     *
     * @param postCode 岗位编码
     * @param tenantId 租户ID
     * @return 岗位聚合根
     */
    Optional<IamPost> findByPostCode(String postCode, String tenantId);

    /**
     * 查找所有岗位
     *
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<IamPost> findAll(String tenantId);

    /**
     * 根据部门ID查找岗位
     *
     * @param deptId   部门ID
     * @param tenantId 租户ID
     * @return 岗位列表
     */
    List<IamPost> findByDeptId(String deptId, String tenantId);

    /**
     * 检查岗位编码是否存在
     *
     * @param postCode  岗位编码
     * @param tenantId  租户ID
     * @param excludeId 排除的ID
     * @return true-存在，false-不存在
     */
    boolean existsByPostCode(String postCode, String tenantId, String excludeId);

}
