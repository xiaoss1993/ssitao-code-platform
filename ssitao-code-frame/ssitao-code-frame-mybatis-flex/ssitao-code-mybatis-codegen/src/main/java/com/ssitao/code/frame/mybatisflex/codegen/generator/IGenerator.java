
package com.ssitao.code.frame.mybatisflex.codegen.generator;

import com.ssitao.code.frame.mybatisflex.codegen.config.GlobalConfig;
import com.ssitao.code.frame.mybatisflex.codegen.entity.Table;

/**
 * 文件生成器接口。
 *
 * @author ssitao
 */
public interface IGenerator {

    /**
     * 获取模板文件位置。
     *
     * @return 路径
     */
    String getTemplatePath();

    /**
     * 设置模板文件位置。
     *
     * @param templatePath
     */
    void setTemplatePath(String templatePath);

    /**
     * 根据模板生成文件。
     *
     * @param table        表内容
     * @param globalConfig 全局配置
     */
    void generate(Table table, GlobalConfig globalConfig);

}
