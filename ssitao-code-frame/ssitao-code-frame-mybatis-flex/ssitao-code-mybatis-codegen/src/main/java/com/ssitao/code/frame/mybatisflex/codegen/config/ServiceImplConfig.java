
package com.ssitao.code.frame.mybatisflex.codegen.config;

import java.io.Serializable;

/**
 * 生成 ServiceImpl 的配置。
 *
 * @author ssitao 
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ServiceImplConfig implements Serializable {

    private static final long serialVersionUID = 17115432462168151L;

    /**
     * 代码生成目录，当未配置时，使用 PackageConfig 的配置
     */
    private String sourceDir;

    /**
     * ServiceImpl 类的前缀。
     */
    private String classPrefix = "";

    /**
     * ServiceImpl 类的后缀。
     */
    private String classSuffix = "ServiceImpl";

    /**
     * 自定义 ServiceImpl 的父类。
     */
    private Class<?> superClass;

    /**
     * 是否覆盖之前生成的文件。
     */
    private boolean overwriteEnable;

    /**
     * 是否生成缓存样例代码。
     */
    private boolean cacheExample;

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    public String buildSuperClassImport() {
        if (superClass == null) {
            return "com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl";
        }
        return superClass.getName();
    }

    public String buildSuperClassName() {
        if (superClass == null) {
            return "ServiceImpl";
        }
        return superClass.getSimpleName();
    }

    /**
     * 获取类前缀。
     */
    public String getClassPrefix() {
        return classPrefix;
    }

    /**
     * 设置类前缀。
     */
    public ServiceImplConfig setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
        return this;
    }

    /**
     * 获取类后缀。
     */
    public String getClassSuffix() {
        return classSuffix;
    }

    /**
     * 设置类后缀。
     */
    public ServiceImplConfig setClassSuffix(String classSuffix) {
        this.classSuffix = classSuffix;
        return this;
    }

    /**
     * 获取父类。
     */
    public Class<?> getSuperClass() {
        return superClass;
    }

    /**
     * 设置父类。
     */
    public ServiceImplConfig setSuperClass(Class<?> superClass) {
        this.superClass = superClass;
        return this;
    }

    /**
     * 是否覆盖原有文件。
     */
    public boolean isOverwriteEnable() {
        return overwriteEnable;
    }

    /**
     * 设置是否覆盖原有文件。
     */
    public ServiceImplConfig setOverwriteEnable(boolean overwriteEnable) {
        this.overwriteEnable = overwriteEnable;
        return this;
    }

    /**
     * 是否生成缓存例子。
     */
    public boolean isCacheExample() {
        return cacheExample;
    }

    /**
     * 设置生成缓存例子。
     */
    public ServiceImplConfig setCacheExample(boolean cacheExample) {
        this.cacheExample = cacheExample;
        return this;
    }

}
