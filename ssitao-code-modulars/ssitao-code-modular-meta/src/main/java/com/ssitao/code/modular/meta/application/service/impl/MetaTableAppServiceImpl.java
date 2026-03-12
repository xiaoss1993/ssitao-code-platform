package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.frame.mybatisflex.codegen.Generator;
import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.dialect.impl.MySqlJdbcDialect;
import com.ssitao.code.modular.meta.api.dto.MetaTableDTO;
import com.ssitao.code.modular.meta.application.command.MetaTableCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaTableUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaTableAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTableDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaTableMapper;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaTableConverter;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * 元数据表配置应用服务实现
 *
 * @author ssitao-code
 */
@Slf4j
@Service
public class MetaTableAppServiceImpl implements MetaTableAppService {

    @Resource
    private MetaTableMapper metaTableMapper;

    @Resource
    private DataSource dataSource;

    @Override
    public String create(MetaTableCreateCommand command, String tenantId) {
        if (checkExists(command.getTableName(), tenantId, null)) {
            throw new BusinessException("表名称已存在");
        }

        MetaTableDO metaTableDO = MetaTableConverter.INSTANCE.toDO(command);
        metaTableDO.setId(IdUtil.fastSimpleUUID());
        metaTableDO.setTenantId(tenantId);
        metaTableDO.setEnabled(1);
        metaTableDO.setGenerated(0);
        metaTableDO.setCreateTime(LocalDateTime.now());
        metaTableDO.setIsDeleted(0);
        metaTableDO.setVersion(0);

        metaTableMapper.insert(metaTableDO);
        return metaTableDO.getId();
    }

    @Override
    public void update(MetaTableUpdateCommand command, String tenantId) {
        MetaTableDO existing = findById(command.getId(), tenantId);
        if (existing == null) {
            throw new BusinessException("表不存在");
        }

        MetaTableDO updateTable = new MetaTableDO();
        updateTable.setId(command.getId());
        updateTable.setTableName(command.getTableName());
        updateTable.setTableDesc(command.getTableDesc());
        updateTable.setTableType(command.getTableType());
        updateTable.setPackageName(command.getPackageName());
        updateTable.setModuleName(command.getModuleName());
        updateTable.setClassName(command.getClassName());
        updateTable.setClassDesc(command.getClassDesc());
        updateTable.setEntityName(command.getEntityName());
        updateTable.setAuthor(command.getAuthor());
        updateTable.setEnabled(command.getEnabled());
        updateTable.setGenPath(command.getGenPath());
        updateTable.setRemark(command.getRemark());
        updateTable.setModifyTime(LocalDateTime.now());

        metaTableMapper.update(updateTable);
    }

    @Override
    public void delete(String id, String tenantId) {
        MetaTableDO table = new MetaTableDO();
        table.setId(id);
        table.setIsDeleted(1);
        table.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getId, id)
                .eq(MetaTableDO::getTenantId, tenantId);
        metaTableMapper.updateByQuery(table, wrapper);
    }

    @Override
    public MetaTableDTO getById(String id, String tenantId) {
        MetaTableDO metaTableDO = findById(id, tenantId);
        if (metaTableDO == null) {
            return null;
        }
        return MetaTableConverter.INSTANCE.toDTO(metaTableDO);
    }

    @Override
    public List<MetaTableDTO> list(String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0)
                .orderBy("create_time", true);
        List<MetaTableDO> tables = metaTableMapper.selectListByQuery(wrapper);
        return MetaTableConverter.INSTANCE.toDTOList(tables);
    }

    @Override
    public List<MetaTableDTO> page(String keyword, int page, int limit, String sort, String order, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("is_deleted", 0);

        // 处理排序
        String sortColumn = sort != null && !sort.isEmpty() ? sort : "create_time";
        boolean ascending = order == null || !"desc".equalsIgnoreCase(order);
        wrapper.orderBy(sortColumn, ascending);

        // 处理分页
        wrapper.offset((page - 1) * limit).limit(limit);

        List<MetaTableDO> tables = metaTableMapper.selectListByQuery(wrapper);
        return MetaTableConverter.INSTANCE.toDTOList(tables);
    }

    @Override
    public boolean checkExists(String tableName, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getTableName, tableName)
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaTableDO::getId, excludeId);
        }
        return metaTableMapper.selectCountByQuery(wrapper) > 0;
    }

    private MetaTableDO findById(String id, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getId, id)
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0);
        return metaTableMapper.selectOneByQuery(wrapper);
    }

    @Override
    public void generate(String id, String tenantId) {
        MetaTableDO metaTable = findById(id, tenantId);
        if (metaTable == null) {
            throw new BusinessException("表配置不存在");
        }

        log.info("开始生成代码 - tableName: {}, tableDesc: {}, genPath: {}",
                metaTable.getTableName(), metaTable.getTableDesc(), metaTable.getGenPath());

        // 构建代码生成配置
        GlobalConfig globalConfig = buildGlobalConfig(metaTable);

        // 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig, new MySqlJdbcDialect());

        // 执行代码生成
        generator.generate();

        // 更新生成状态
        MetaTableDO updateTable = new MetaTableDO();
        updateTable.setId(id);
        updateTable.setGenerated(1);
        updateTable.setModifyTime(LocalDateTime.now());
        QueryWrapper updateWrapper = QueryWrapper.create().eq(MetaTableDO::getId, id);
        metaTableMapper.updateByQuery(updateTable, updateWrapper);

        log.info("代码生成完成 - tableName: {}", metaTable.getTableName());
    }

    private GlobalConfig buildGlobalConfig(MetaTableDO metaTable) {
        GlobalConfig globalConfig = new GlobalConfig();

        // 设置基础包名
        String basePackage = metaTable.getPackageName();
        globalConfig.setBasePackage(basePackage);

        // 设置作者
        globalConfig.setAuthor(metaTable.getAuthor());

        // 设置生成路径
        String genPath = metaTable.getGenPath();
        if (genPath != null && !genPath.isEmpty()) {
            globalConfig.setSourceDir(genPath);
        }

        // 设置包路径
        globalConfig.setEntityPackage(basePackage + ".dal.dataobject");
        globalConfig.setMapperPackage(basePackage + ".dal.mapper");
        globalConfig.setServicePackage(basePackage + ".application.service");
        globalConfig.setServiceImplPackage(basePackage + ".application.service.impl");
        globalConfig.setControllerPackage(basePackage + ".controller");

        // 启用需要生成的代码
        globalConfig.enableEntity();
        globalConfig.enableMapper();
        globalConfig.enableService();
        globalConfig.enableServiceImpl();
        globalConfig.enableController();

        // 设置覆盖允许
        globalConfig.setEntityOverwriteEnable(true);
        globalConfig.setMapperOverwriteEnable(true);
        globalConfig.setServiceOverwriteEnable(true);
        globalConfig.setServiceImplOverwriteEnable(true);
        globalConfig.setControllerOverwriteEnable(true);

        // 设置实体配置
        globalConfig.getEntityConfig().setWithLombok(true);
        globalConfig.getEntityConfig().setWithSwagger(true);

        // 设置表名
        globalConfig.setGenerateTable(metaTable.getTableName());

        // 设置表前缀（如果有的话，可以根据entityName自动生成类名）
        if (metaTable.getEntityName() != null && !metaTable.getEntityName().isEmpty()) {
            globalConfig.getEntityConfig().setClassPrefix(metaTable.getEntityName());
        }

        return globalConfig;
    }
}
