package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysPostAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysPostRepository;
import com.ssitao.code.modular.iam.domain.SysPost;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 岗位仓储实现
 */
@Repository
public class SysPostRepositoryImpl extends AbstractAggregateRepository<SysPostAggregate, Long> implements SysPostRepository {

    @Autowired
    private SysPostMapper postMapper;

    public SysPostRepositoryImpl() {
        super(SysPostAggregate.class);
    }

    @Override
    protected Collection<SysPostAggregate> doSave(Collection<SysPostAggregate> aggregates) {
        List<SysPostAggregate> saved = new ArrayList<>();
        for (SysPostAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                postMapper.insertPost(toEntity(aggregate));
            } else {
                postMapper.updatePost(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysPostAggregate> aggregates) {
        for (SysPostAggregate aggregate : aggregates) {
            postMapper.deletePostByIds(new Long[]{aggregate.getPostId()});
        }
    }

    @Override
    protected SysPostAggregate doFindOne(Long id) {
        SysPost post = postMapper.selectPostById(id);
        if (post == null) {
            return null;
        }
        return toAggregate(post);
    }

    @Override
    protected boolean doExists(Long id) {
        return postMapper.selectPostById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysPost> posts = postMapper.selectPostList(new SysPost());
        List<Long> ids = new ArrayList<>();
        if (posts != null) {
            for (SysPost post : posts) {
                ids.add(post.getPostId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysPostAggregate> doFindAll(Collection<Long> ids) {
        List<SysPostAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysPostAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return postMapper.selectPostList(new SysPost()).size();
    }

    @Override
    public SysPostAggregate findByPostCode(String postCode) {
        SysPost post = postMapper.checkPostCodeUnique(postCode);
        if (post == null) {
            return null;
        }
        return toAggregate(post);
    }

    @Override
    public SysPostAggregate findByPostName(String postName) {
        SysPost post = postMapper.checkPostNameUnique(postName);
        if (post == null) {
            return null;
        }
        return toAggregate(post);
    }

    @Override
    public boolean existsByPostCode(String postCode) {
        return postMapper.checkPostCodeUnique(postCode) != null;
    }

    @Override
    public boolean existsByPostName(String postName) {
        return postMapper.checkPostNameUnique(postName) != null;
    }

    /**
     * 将聚合根转换为实体
     */
    private SysPost toEntity(SysPostAggregate aggregate) {
        SysPost post = new SysPost();
        post.setPostId(aggregate.getPostId());
        post.setPostCode(aggregate.getPostCode());
        post.setPostName(aggregate.getPostName());
        post.setPostSort(aggregate.getPostSort());
        post.setStatus(aggregate.getStatus());
        post.setCreateBy(aggregate.getCreateBy());
        post.setCreateTime(aggregate.getCreateTime());
        post.setUpdateBy(aggregate.getUpdateBy());
        post.setUpdateTime(aggregate.getUpdateTime());
        post.setRemark(aggregate.getRemark());
        return post;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysPostAggregate toAggregate(SysPost post) {
        SysPostAggregate aggregate = new SysPostAggregate();
        aggregate.setPostId(post.getPostId());
        aggregate.setPostCode(post.getPostCode());
        aggregate.setPostName(post.getPostName());
        aggregate.setPostSort(post.getPostSort());
        aggregate.setStatus(post.getStatus());
        aggregate.setCreateBy(post.getCreateBy());
        aggregate.setCreateTime(post.getCreateTime());
        aggregate.setUpdateBy(post.getUpdateBy());
        aggregate.setUpdateTime(post.getUpdateTime());
        aggregate.setRemark(post.getRemark());
        return aggregate;
    }
}
