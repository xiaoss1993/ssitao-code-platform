
package com.ssitao.code.frame.mybatisflex.spring.boot;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDependsOnDatabaseInitializationDetector;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitializationDetector;

import java.util.Collections;
import java.util.Set;

/**
 * MyBatis-Flex 数据库初始化依赖检测器
 * <p>
 * 用于检测哪些 Bean 需要在数据库初始化完成后才能创建。
 * 确保 SqlSessionTemplate 在数据库初始化完成前不会被创建。
 *
 * @author ssitao
 * @since 1.0.0
 */
class MybatisFlexDependsOnDatabaseInitializationDetector
    extends AbstractBeansOfTypeDependsOnDatabaseInitializationDetector {

    /**
     * 获取需要依赖数据库初始化的 Bean 类型
     *
     * @return Bean 类型集合
     */
    @Override
    protected Set<Class<?>> getDependsOnDatabaseInitializationBeanTypes() {
        return Collections.singleton(SqlSessionTemplate.class);
    }

}
