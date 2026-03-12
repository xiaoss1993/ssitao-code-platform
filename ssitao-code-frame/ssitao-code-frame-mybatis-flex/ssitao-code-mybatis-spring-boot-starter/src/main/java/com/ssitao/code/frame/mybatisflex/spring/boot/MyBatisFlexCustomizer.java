
package com.ssitao.code.frame.mybatisflex.spring.boot;

import com.ssitao.code.frame.mybatisflex.core.FlexGlobalConfig;

/**
 * MyBatis-Flex 全局配置自定义器接口
 * <p>
 * 用于在 Spring Boot 启动时对 MyBatis-Flex 进行自定义配置。
 * 可以用于初始化：
 * <ul>
 *      <li>FlexGlobalConfig 的全局配置</li>
 *      <li>自定义主键生成器</li>
 *      <li>多租户配置</li>
 *      <li>动态表名配置</li>
 *      <li>逻辑删除处理器配置</li>
 *      <li>自定义脱敏规则</li>
 *      <li>SQL 审计配置</li>
 *      <li>SQL 打印配置</li>
 *      <li>数据源解密器配置</li>
 *      <li>自定义数据方言配置</li>
 * </ul>
 *
 * @author ssitao
 * @since 1.0.0
 */
public interface MyBatisFlexCustomizer {

    /**
     * 自定义 MyBatis-Flex 全局配置
     *
     * @param globalConfig MyBatis-Flex 全局配置对象
     */
    void customize(FlexGlobalConfig globalConfig);

}
