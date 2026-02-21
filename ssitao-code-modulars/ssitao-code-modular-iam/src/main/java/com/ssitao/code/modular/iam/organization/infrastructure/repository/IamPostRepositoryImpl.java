package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamPostDO;
import com.ssitao.code.modular.iam.dal.mapper.IamPostMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import com.ssitao.code.modular.iam.organization.domain.repository.IamPostRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamPostConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IAM岗位仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamPostRepositoryImpl implements IamPostRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamPostMapper postMapper;

    @Resource
    private IamPostConverter postConverter;

    @Override
    public String save(IamPost post) {
        IamPostDO postDO = postConverter.toDO(post);
        postDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        postMapper.insert(postDO);
        return postDO.getTbIamPostId();
    }

    @Override
    public List<String> saveBatch(List<IamPost> posts) {
        List<String> ids = new ArrayList<>();
        if (posts == null || posts.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamPost post : posts) {
            IamPostDO postDO = postConverter.toDO(post);
            postDO.setSyCreatetime(now);
            postMapper.insert(postDO);
            ids.add(postDO.getTbIamPostId());
        }
        return ids;
    }

    @Override
    public void update(IamPost post) {
        IamPostDO postDO = postConverter.toDO(post);
        postDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        postMapper.update(postDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_post_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        postMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPost> findById(String id, String tenantId) {
        IamPostDO postDO = postMapper.selectOneById(id);
        if (postDO != null && (tenantId == null || tenantId.isEmpty() || tenantId.equals(postDO.getSyTenantId()))) {
            return Optional.ofNullable(postConverter.toDomain(postDO));
        }
        return Optional.empty();
    }

    @Override
    public Optional<IamPost> findByPostCode(String postCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("post_code", postCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamPostDO postDO = postMapper.selectOneByQuery(query);
        return Optional.ofNullable(postConverter.toDomain(postDO));
    }

    @Override
    public List<IamPost> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamPostDO> list = postMapper.selectListByQuery(query);
        return postConverter.toDomainList(list);
    }

    @Override
    public List<IamPost> findByDeptId(String deptId, String tenantId) {
        // 岗位表中可能需要关联部门表查询
        // 这里假设通过某种关联方式查询，实际实现可能需要调整
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamPostDO> list = postMapper.selectListByQuery(query);
        return postConverter.toDomainList(list);
    }

    @Override
    public boolean existsByPostCode(String postCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("post_code", postCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        if (excludeId != null) {
            query.ne("tb_iam_post_id", excludeId);
        }
        return postMapper.selectCountByQuery(query) > 0;
    }
}
