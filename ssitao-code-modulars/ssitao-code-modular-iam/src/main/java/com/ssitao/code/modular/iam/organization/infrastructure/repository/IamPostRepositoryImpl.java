package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamPostDO;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamPostMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamPost;
import com.ssitao.code.modular.iam.organization.domain.repository.IamPostRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamPostConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamPostMapper postMapper;

    @Resource
    private IamPostConverter postConverter;

    @Override
    public String save(IamPost post) {
        IamPostDO postDO = postConverter.toDO(post);
        postDO.setCreateTime(LocalDateTime.now());
        postDO.setPostStatus(STATUS_ACTIVE);
        postDO.setIsDeleted(NOT_DELETED);
        postMapper.insert(postDO);
        return postDO.getPostId();
    }

    @Override
    public List<String> saveBatch(List<IamPost> posts) {
        List<String> ids = new ArrayList<>();
        if (posts == null || posts.isEmpty()) {
            return ids;
        }
        LocalDateTime now = LocalDateTime.now();
        for (IamPost post : posts) {
            IamPostDO postDO = postConverter.toDO(post);
            postDO.setCreateTime(now);
            postDO.setPostStatus(STATUS_ACTIVE);
            postDO.setIsDeleted(NOT_DELETED);
            postMapper.insert(postDO);
            ids.add(postDO.getPostId());
        }
        return ids;
    }

    @Override
    public void update(IamPost post) {
        IamPostDO postDO = postConverter.toDO(post);
        postDO.setModifyTime(LocalDateTime.now());
        postMapper.update(postDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("post_id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        postMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamPost> findById(String id, String tenantId) {
        IamPostDO postDO = postMapper.selectOneById(id);
        if (postDO != null && (tenantId == null || tenantId.isEmpty() || tenantId.equals(postDO.getTenantId()))) {
            return Optional.ofNullable(postConverter.toDomain(postDO));
        }
        return Optional.empty();
    }

    @Override
    public Optional<IamPost> findByPostCode(String postCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("post_code", postCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        IamPostDO postDO = postMapper.selectOneByQuery(query);
        return Optional.ofNullable(postConverter.toDomain(postDO));
    }

    @Override
    public List<IamPost> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("post_sort", true);
        List<IamPostDO> list = postMapper.selectListByQuery(query);
        return postConverter.toDomainList(list);
    }

    @Override
    public List<IamPost> findByDeptId(String deptId, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("post_sort", true);
        List<IamPostDO> list = postMapper.selectListByQuery(query);
        return postConverter.toDomainList(list);
    }

    @Override
    public boolean existsByPostCode(String postCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("post_code", postCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("post_id", excludeId);
        }
        return postMapper.selectCountByQuery(query) > 0;
    }
}
