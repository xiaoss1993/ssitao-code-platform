
package com.ssitao.code.frame.mybatisflex.codegen.generator.impl;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.MapperXmlConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.PackageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.constant.TemplateConst;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * MapperXml 生成器。
 *
 * @author ssitao 
 * @since 1.0.0
 */
public class MapperXmlGenerator implements IGenerator {

    private String templatePath;

    public MapperXmlGenerator() {
        this(TemplateConst.MAPPER_XML);
    }

    public MapperXmlGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isMapperXmlGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        MapperXmlConfig mapperXmlConfig = globalConfig.getMapperXmlConfig();

        File mapperXmlFile = new File(packageConfig.getMapperXmlPath() + "/" +
            table.buildMapperXmlFileName() + ".xml");


        if (mapperXmlFile.exists() && !mapperXmlConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(2);
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, mapperXmlFile);

        System.out.println("MapperXML ---> " + mapperXmlFile);
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

}
