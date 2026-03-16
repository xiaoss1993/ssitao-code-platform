package com.ssitao.code.modular.datasource.service.simple;

import com.ssitao.code.modular.datasource.service.DataSourceConfigService;
import com.tweb.commons.utils.bean.FastBeanCopier;
import com.tweb.frame.datasource.config.DynamicDataSourceConfigRepository;
import com.ssitao.code.modular.datasource.entity.DataSourceConfigEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @since 1.0
 */
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class InDBDataSourceRepository implements DynamicDataSourceConfigRepository<InDBDynamicDataSourceConfig> {
    private DataSourceConfigService dataSourceConfigService;

    public InDBDataSourceRepository(DataSourceConfigService dataSourceConfigService) {
        this.dataSourceConfigService = dataSourceConfigService;
    }

    public InDBDataSourceRepository() {
    }

    public void setDataSourceConfigService(DataSourceConfigService dataSourceConfigService) {
        this.dataSourceConfigService = dataSourceConfigService;
    }

    protected InDBDynamicDataSourceConfig convert(DataSourceConfigEntity entity) {
        if (null == entity) {
            return null;
        }
        Map<String, Object> config = entity.getProperties();
        if (config == null) {
            return null;
        }
        InDBDynamicDataSourceConfig target = FastBeanCopier.copy(config, InDBDynamicDataSourceConfig::new);
        target.setId(entity.getId());
        target.setName(entity.getName());
        target.setDescribe(entity.getDescribe());
        target.setProperties(config);
        return target;
    }

    @Override
    public List<InDBDynamicDataSourceConfig> findAll() {
        return dataSourceConfigService.select().stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    @Override
    public InDBDynamicDataSourceConfig findById(String dataSourceId) {
        return convert(dataSourceConfigService.selectByPk(dataSourceId));
    }

    @Override
    public InDBDynamicDataSourceConfig add(InDBDynamicDataSourceConfig config) {
        throw new UnsupportedOperationException("add AtomikosDataSourceConfig not support");
    }

    @Override
    public InDBDynamicDataSourceConfig remove(String dataSourceId) {
        throw new UnsupportedOperationException("remove datasource not support");
    }
}
