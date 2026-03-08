package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaEntityDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaEntityMapper;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.repository.MetaEntityRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaEntityConverter;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 元数据实体仓储实现
 *
 * @author ssitao-code
 */
@Repository
@RequiredArgsConstructor
public class MetaEntityRepositoryImpl implements MetaEntityRepository {

    private final MetaEntityMapper metaEntityMapper;

    @Override
    @Transactional
    public String save(MetaEntity entity) {
        MetaEntityDO metaEntityDO = MetaEntityConverter.INSTANCE.toDO(entity);
        metaEntityMapper.insert(metaEntityDO);
        return metaEntityDO.getId();
    }

    @Override
    @Transactional
    public void update(MetaEntity entity) {
        MetaEntityDO metaEntityDO = MetaEntityConverter.INSTANCE.toDO(entity);
        metaEntityMapper.update(metaEntityDO);
    }

    @Override
    @Transactional
    public void delete(String id, String tenantId) {
        MetaEntityDO entity = new MetaEntityDO();
        entity.setId(id);
        entity.setIsDeleted(1);
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getId, id)
                .eq(MetaEntityDO::getTenantId, tenantId);
        metaEntityMapper.updateByQuery(entity, wrapper);
    }

    @Override
    public Optional<MetaEntity> findById(String id, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getId, id)
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0);
        MetaEntityDO metaEntityDO = metaEntityMapper.selectOneByQuery(wrapper);
        if (metaEntityDO == null) {
            return Optional.empty();
        }
        return Optional.of(MetaEntityConverter.INSTANCE.toDomain(metaEntityDO));
    }

    @Override
    public List<MetaEntity> findAll(String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0)
                .orderBy(MetaEntityDO::getCreateTime, true);
        List<MetaEntityDO> entities = metaEntityMapper.selectListByQuery(wrapper);
        return entities.stream()
                .map(MetaEntityConverter.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaEntity> page(String keyword, int page, int limit, String sort, String order, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0);

        // 处理排序
        if (sort != null && !sort.isEmpty()) {
            boolean isDesc = "desc".equalsIgnoreCase(order);
            switch (sort) {
                case "create_time":
                    wrapper.orderBy(MetaEntityDO::getCreateTime, isDesc);
                    break;
                case "entity_code":
                    wrapper.orderBy(MetaEntityDO::getEntityCode, isDesc);
                    break;
                case "entity_name":
                    wrapper.orderBy(MetaEntityDO::getEntityName, isDesc);
                    break;
                default:
                    wrapper.orderBy(MetaEntityDO::getCreateTime, true);
                    break;
            }
        } else {
            wrapper.orderBy(MetaEntityDO::getCreateTime, true);
        }

        int offset = (page - 1) * limit;
        wrapper.limit(offset, limit);

        List<MetaEntityDO> entities = metaEntityMapper.selectListByQuery(wrapper);
        return entities.stream()
                .map(MetaEntityConverter.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCode(String entityCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getEntityCode, entityCode)
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaEntityDO::getId, excludeId);
        }
        return metaEntityMapper.selectCountByQuery(wrapper) > 0;
    }
}
