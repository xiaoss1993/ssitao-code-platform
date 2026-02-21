package com.ssitao.code.modular.iam.userprofile.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamUserDO;
import com.ssitao.code.modular.iam.dal.mapper.IamUserMapper;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUserProfile;
import com.ssitao.code.modular.iam.userprofile.domain.repository.IamUserProfileRepository;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserProfileConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IAM用户档案仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamUserProfileRepositoryImpl implements IamUserProfileRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamUserMapper userMapper;

    @Resource
    private IamUserProfileConverter userProfileConverter;

    @Override
    public String save(IamUserProfile userProfile) {
        IamUserDO userDO = userProfileConverter.toDO(userProfile);
        userDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        userMapper.insert(userDO);
        return userDO.getTbIamUserId();
    }

    @Override
    public List<String> saveBatch(List<IamUserProfile> userProfiles) {
        List<String> ids = new ArrayList<>();
        if (userProfiles == null || userProfiles.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamUserProfile userProfile : userProfiles) {
            IamUserDO userDO = userProfileConverter.toDO(userProfile);
            userDO.setSyCreatetime(now);
            userMapper.insert(userDO);
            ids.add(userDO.getTbIamUserId());
        }
        return ids;
    }

    @Override
    public void update(IamUserProfile userProfile) {
        IamUserDO userDO = userProfileConverter.toDO(userProfile);
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
    public Optional<IamUserProfile> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_user_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userProfileConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUserProfile> findByCode(String userCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_code", userCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userProfileConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUserProfile> findByPhone(String phone, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_phone", phone);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userProfileConverter.toDomain(userDO));
    }

    @Override
    public Optional<IamUserProfile> findByEmail(String email, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_mail", email);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamUserDO userDO = userMapper.selectOneByQuery(query);
        return Optional.ofNullable(userProfileConverter.toDomain(userDO));
    }

    @Override
    public List<IamUserProfile> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userProfileConverter.toDomainList(list);
    }

    @Override
    public List<IamUserProfile> findByDepartmentId(String deptId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_monitordept_id", deptId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userProfileConverter.toDomainList(list);
    }

    @Override
    public List<IamUserProfile> findByPostCode(String postCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_post_code", postCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userProfileConverter.toDomainList(list);
    }

    @Override
    public List<IamUserProfile> findByRoleId(String roleId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_role_id", roleId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userProfileConverter.toDomainList(list);
    }

    @Override
    public List<IamUserProfile> findPage(String tenantId, String keyword, String deptId, int page, int size) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (keyword != null && !keyword.isEmpty()) {
            query.and(wrapper -> {
                wrapper.like("user_name", "%" + keyword + "%")
                        .or("user_phone", "%" + keyword + "%")
                        .or("user_mail", "%" + keyword + "%");
            });
        }
        if (deptId != null && !deptId.isEmpty()) {
            query.eq("user_monitordept_id", deptId);
        }
        query.orderBy("sy_createtime", false)
                .offset((page - 1) * size)
                .limit(size);
        List<IamUserDO> list = userMapper.selectListByQuery(query);
        return userProfileConverter.toDomainList(list);
    }

    @Override
    public long count(String tenantId, String keyword, String deptId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (keyword != null && !keyword.isEmpty()) {
            query.and(wrapper -> {
                wrapper.like("user_name", "%" + keyword + "%")
                        .or("user_phone", "%" + keyword + "%")
                        .or("user_mail", "%" + keyword + "%");
            });
        }
        if (deptId != null && !deptId.isEmpty()) {
            query.eq("user_monitordept_id", deptId);
        }
        return userMapper.selectCountByQuery(query);
    }

    @Override
    public boolean existsByCode(String userCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("user_code", userCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
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
        if (excludeId != null) {
            query.ne("tb_iam_user_id", excludeId);
        }
        return userMapper.selectCountByQuery(query) > 0;
    }

}
