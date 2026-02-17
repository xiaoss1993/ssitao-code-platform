
package com.ssitao.code.frame.mybatisflex.codegen.generator.impl;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.PackageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.ServiceImplConfig;
import com.ssitao.code.frame.mybatisflex.codegen.constant.TemplateConst;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceImpl 生成器。
 *
 * @author 王帅
 * @since 2023-05-14
 */
public class ServiceImplGenerator implements IGenerator {

    private String templatePath;

    public ServiceImplGenerator() {
        this(TemplateConst.SERVICE_IMPL);
    }

    public ServiceImplGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isServiceImplGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        ServiceImplConfig serviceImplConfig = globalConfig.getServiceImplConfig();

        String sourceDir = StringUtil.hasText(serviceImplConfig.getSourceDir()) ? serviceImplConfig.getSourceDir() : packageConfig.getSourceDir();

        String serviceImplPackagePath = packageConfig.getServiceImplPackage().replace(".", "/");
        File serviceImplJavaFile = new File(sourceDir, serviceImplPackagePath + "/" +
            table.buildServiceImplClassName() + globalConfig.getFileType());


        if (serviceImplJavaFile.exists() && !serviceImplConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("serviceImplConfig", serviceImplConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, serviceImplJavaFile);

        System.out.println("ServiceImpl ---> " + serviceImplJavaFile);
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
