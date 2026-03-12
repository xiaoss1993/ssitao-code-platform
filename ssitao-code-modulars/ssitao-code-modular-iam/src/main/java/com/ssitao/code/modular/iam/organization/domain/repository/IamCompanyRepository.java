package com.ssitao.code.modular.iam.organization.domain.repository;

import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;

import java.util.List;
import java.util.Optional;

/**
 * IAM公司仓储接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface IamCompanyRepository {

    /**
     * 保存公司
     *
     * @param company 公司聚合根
     * @return 保存后的公司ID
     */
    String save(IamCompany company);

    /**
     * 更新公司
     *
     * @param company 公司聚合根
     */
    void update(IamCompany company);

    /**
     * 根据ID获取公司
     *
     * @param id 公司ID
     * @return 公司聚合根
     */
    Optional<IamCompany> findById(String id);

    /**
     * 根据公司编码获取公司
     *
     * @param companyCode 公司编码
     * @param tenantId    租户ID
     * @return 公司聚合根
     */
    Optional<IamCompany> findByCompanyCode(String companyCode, String tenantId);

    /**
     * 根据社会信用代码获取公司
     *
     * @param creditCode 统一社会信用代码
     * @param tenantId   租户ID
     * @return 公司聚合根
     */
    Optional<IamCompany> findByCreditCode(String creditCode, String tenantId);

    /**
     * 检查公司编码是否存在
     *
     * @param companyCode 公司编码
     * @param tenantId    租户ID
     * @param excludeId   排除的公司ID
     * @return true-存在，false-不存在
     */
    boolean existsByCompanyCode(String companyCode, String tenantId, String excludeId);

    /**
     * 获取所有公司
     *
     * @param tenantId 租户ID
     * @return 公司列表
     */
    List<IamCompany> findAll(String tenantId);

    /**
     * 根据条件查询公司列表
     *
     * @param companyName 公司名称(模糊查询)
     * @param companyType 公司类型
     * @param tenantId    租户ID
     * @return 公司列表
     */
    List<IamCompany> findByConditions(String companyName, String companyType, String tenantId);

    /**
     * 删除公司
     *
     * @param id 公司ID
     */
    void delete(String id);
}
