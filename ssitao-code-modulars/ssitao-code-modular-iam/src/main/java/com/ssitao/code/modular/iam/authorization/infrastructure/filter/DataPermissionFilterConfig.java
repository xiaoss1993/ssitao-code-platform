package com.ssitao.code.modular.iam.authorization.infrastructure.filter;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 数据权限过滤器配置类
 * <p>
 * 将 DataPermissionFilter 注册到 MyBatis 拦截器链中
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Configuration
public class DataPermissionFilterConfig {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactories;

    /**
     * 注册数据权限过滤器
     *
     * @return 数据权限过滤器
     */
    @Bean
    public DataPermissionFilter dataPermissionFilter() {
        return new DataPermissionFilter();
    }

    /**
     * 初始化时将数据权限过滤器添加到 SqlSessionFactory
     */
    @javax.annotation.PostConstruct
    public void addDataPermissionInterceptor() {
        if (sqlSessionFactories == null || sqlSessionFactories.isEmpty()) {
            log.warn("未找到 SqlSessionFactory，跳过数据权限过滤器注册");
            return;
        }

        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactories) {
            // 获取已有的拦截器
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();

            // 检查是否已经添加过该拦截器
            boolean alreadyAdded = false;
            for (org.apache.ibatis.plugin.Interceptor interceptor : configuration.getInterceptors()) {
                if (interceptor instanceof DataPermissionFilter) {
                    alreadyAdded = true;
                    break;
                }
            }

            // 如果没有添加，则添加
            if (!alreadyAdded) {
                configuration.addInterceptor(dataPermissionFilter());
                log.info("数据权限过滤器已添加到 SqlSessionFactory");
            }
        }
    }

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DataPermissionFilterConfig.class);
}
