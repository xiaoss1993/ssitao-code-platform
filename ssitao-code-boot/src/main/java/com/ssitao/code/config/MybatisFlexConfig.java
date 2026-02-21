package com.ssitao.code.config;

import com.ssitao.code.frame.mybatisflex.core.audit.AuditManager;
import com.ssitao.code.frame.mybatisflex.core.audit.ConsoleMessageCollector;
import com.ssitao.code.frame.mybatisflex.core.audit.MessageCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Flex 配置类
 * 用于配置 SQL 审计和打印功能
 *
 * @author ssitao-code
 */
@Configuration
public class MybatisFlexConfig implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(MybatisFlexConfig.class);

    @Override
    public void afterPropertiesSet() {
        // 启用 SQL 审计功能
        AuditManager.setAuditEnable(true);

        // 设置控制台 SQL 打印器
        MessageCollector consoleCollector = new ConsoleMessageCollector((sql, tookTimeMillis) -> {
            if (tookTimeMillis != null) {
                log.info("MyBatis-Flex SQL [{} ms] >>> {}", tookTimeMillis, sql);
            } else {
                log.info("MyBatis-Flex SQL >>> {}", sql);
            }
        });
        AuditManager.setMessageCollector(consoleCollector);

        log.info("MyBatis-Flex SQL 审计已启用，SQL 将打印到控制台");
    }
}
