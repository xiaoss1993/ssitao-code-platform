
package com.ssitao.code.frame.mybatisflex.codegen.generator.impl;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.PackageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.ServiceConfig;
import com.ssitao.code.frame.mybatisflex.codegen.constant.TemplateConst;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Service 生成器。
 *
 * @author 王帅
 * @since 2023-05-14
 */
public class ServiceGenerator implements IGenerator {

    private String templatePath;

    public ServiceGenerator() {
        this(TemplateConst.SERVICE);
    }

    public ServiceGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isServiceGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        ServiceConfig serviceConfig = globalConfig.getServiceConfig();

        String sourceDir = StringUtil.hasText(serviceConfig.getSourceDir()) ? serviceConfig.getSourceDir() : packageConfig.getSourceDir();

        String servicePackagePath = packageConfig.getServicePackage().replace(".", "/");
        File serviceJavaFile = new File(sourceDir, servicePackagePath + "/" +
            table.buildServiceClassName() + globalConfig.getFileType());


        if (serviceJavaFile.exists() && !serviceConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("serviceConfig", serviceConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, serviceJavaFile);

        System.out.println("Service ---> " + serviceJavaFile);
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
