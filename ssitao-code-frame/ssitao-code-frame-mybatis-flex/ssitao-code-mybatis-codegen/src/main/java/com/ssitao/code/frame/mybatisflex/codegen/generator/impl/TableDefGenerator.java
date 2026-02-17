
package com.ssitao.code.frame.mybatisflex.codegen.generator.impl;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.PackageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.TableDefConfig;
import com.ssitao.code.frame.mybatisflex.codegen.constant.TemplateConst;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * TableDef 生成器。
 *
 * @author Michael Yang
 * @author 王帅
 */
public class TableDefGenerator implements IGenerator {

    private String templatePath;

    public TableDefGenerator() {
        this(TemplateConst.TABLE_DEF);
    }

    public TableDefGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isTableDefGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        TableDefConfig tableDefConfig = globalConfig.getTableDefConfig();

        String sourceDir = StringUtil.hasText(tableDefConfig.getSourceDir()) ? tableDefConfig.getSourceDir() : packageConfig.getSourceDir();

        String tableDefPackagePath = packageConfig.getTableDefPackage().replace(".", "/");
        File tableDefJavaFile = new File(sourceDir, tableDefPackagePath + "/" +
            table.buildTableDefClassName() + globalConfig.getFileType());


        if (tableDefJavaFile.exists() && !tableDefConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("tableDefConfig", tableDefConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("entityConfig", globalConfig.getEntityConfig());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, tableDefJavaFile);

        System.out.println("TableDef ---> " + tableDefJavaFile);
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
