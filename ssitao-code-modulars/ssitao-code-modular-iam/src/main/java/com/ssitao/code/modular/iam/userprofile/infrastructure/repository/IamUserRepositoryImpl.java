package com.ssitao.code.modular.iam.userprofile.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.dal.mapper.IamUserMapper;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUser;
import com.ssitao.code.modular.iam.userprofile.domain.repository.IamUserRepository;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IAM用户仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamUserRepositoryImpl implements IamUserRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamUserMapper userMapper;

    @Resource
    private IamUserConverter userConverter;

    @Override
    public String save(IamUser user) {
        IamUserDO userDO = userConverter.toDO(user);
        userDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        userDO.setSyStatus("1");
        userMapper.insert(userDO);
        return userDO.getTbIamUserId();
    }

    @Override
    public List<String> saveBatch(List<IamUser> users) {
        List<String> ids = new ArrayList<>();
        if (users == null || users.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamUser user : users) {
            IamUserDO userDO = userConverter.toDO(user);
            userDO.setSyCreatetime(now);
            userDO.setSyStatus("1");
            userMapper.insert(userDO);
            ids.add(userDO.getTbIamUserId());
        }
        return ids;
    }

    @Override
    public void update(IamUser user) {
        IamUserDO userDO = userConverter.toDO(user);
        userDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        userMapper.update(userDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_user_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        userMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamUser> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_user_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUser> findByUsername(String username, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_code", username);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUser> findByEmail(String email, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_mail", email);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUser> findByPhone(String phone, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_phone", phone);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userConverter.toDomain(userDO));
    }

    @Override
    public List<IamUser> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userConverter.toDomainList(list);
    }

    @Override
    public List<IamUser> findByDeptId(String deptId, String tenantId) {
        // 可能需要关联部门用户关联表查询
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userConverter.toDomainList(list);
    }

    @Override
    public List<IamUser> findByPostId(String postId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_post_code", postId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userConverter.toDomainList(list);
    }

    @Override
    public boolean existsByUsername(String username, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_code", username);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_user_id", excludeId);
        }
        return userMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByEmail(String email, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_mail", email);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_user_id", excludeId);
        }
        return userMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByPhone(String phone, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_phone", phone);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_user_id", excludeId);
        }
        return userMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamUser> findPage(String tenantId, String keyword, String deptId, int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        // 始终使用 where 作为第一个条件
        query.eq("sy_status", "1");
        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("sy_tenant_id = ?", tenantId);
        }
        if (keyword != null && !keyword.isEmpty()) {
            query.like("user_name", keyword);
        }
        query.orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userConverter.toDomainList(list);
    }

    @Override
    public long count(String tenantId, String keyword, String deptId) {
        QueryWrapper query = QueryWrapper.create();
        // 始终使用 where 作为第一个条件
        query.eq("sy_status", "1");
        if (tenantId != null && !tenantId.isEmpty()) {
            query.and("sy_tenant_id = ?", tenantId);
        }
        if (keyword != null && !keyword.isEmpty()) {
            query.like("user_name", keyword);
        }
        return userMapper.selectCountByQuery(query);
    }
}
