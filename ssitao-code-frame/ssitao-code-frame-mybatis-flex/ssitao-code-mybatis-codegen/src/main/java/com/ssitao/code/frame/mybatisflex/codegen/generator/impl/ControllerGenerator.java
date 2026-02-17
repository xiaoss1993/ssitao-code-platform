
package com.ssitao.code.frame.mybatisflex.codegen.generator.impl;

import com.ssitao.code.frame.mybatisflex.codegen.config.ControllerConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.config.PackageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.constant.TemplateConst;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;
import com.ssitao.code.frame.mybatisflex.codegen.generator.IGenerator;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller 生成器。
 *
 * @author 王帅
 * @since 2023-05-14
 */
public class ControllerGenerator implements IGenerator {

    private String templatePath;

    public ControllerGenerator() {
        this(TemplateConst.CONTROLLER);
    }

    public ControllerGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isControllerGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        ControllerConfig controllerConfig = globalConfig.getControllerConfig();

        String sourceDir = StringUtil.hasText(controllerConfig.getSourceDir()) ? controllerConfig.getSourceDir() : packageConfig.getSourceDir();

        String controllerPackagePath = packageConfig.getControllerPackage().replace(".", "/");
        File controllerJavaFile = new File(sourceDir, controllerPackagePath + "/" +
            table.buildControllerClassName() + globalConfig.getFileType());


        if (controllerJavaFile.exists() && !controllerConfig.isOverwriteEnable()) {
            return;
        }


        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("packageConfig", packageConfig);
        params.put("controllerConfig", controllerConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());
        params.put("withSwagger", globalConfig.isEntityWithSwagger());
        params.put("swaggerVersion", globalConfig.getSwaggerVersion());
        params.putAll(globalConfig.getCustomConfig());
        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, controllerJavaFile);

        System.out.println("Controller ---> " + controllerJavaFile);
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
