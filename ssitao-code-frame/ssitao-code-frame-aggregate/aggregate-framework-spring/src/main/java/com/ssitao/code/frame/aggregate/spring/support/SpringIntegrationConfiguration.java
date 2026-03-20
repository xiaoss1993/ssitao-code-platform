package com.ssitao.code.frame.aggregate.spring.support;

import com.xfvape.uid.UidGenerator;
import com.xfvape.uid.impl.CachedUidGenerator;
import com.ssitao.code.frame.aggregate.AggClient;
import com.ssitao.code.frame.aggregate.ClientConfig;
import com.ssitao.code.frame.aggregate.spring.datasource.TransactionManagerAutoProxyCreator;
import com.ssitao.code.frame.aggregate.spring.datasource.TransactionManagerInterceptor;
import com.ssitao.code.frame.aggregate.spring.eventhandling.AnnotationEventListenerBeanPostProcessor;
import com.ssitao.code.frame.aggregate.spring.factory.SpringBeanFactory;
import com.ssitao.code.frame.aggregate.spring.xid.DefaultUUIDGenerator;
import com.ssitao.code.frame.aggregate.spring.xid.SimpleWorkerIdAssigner;
import com.ssitao.code.frame.aggregate.xid.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class SpringIntegrationConfiguration {

    @Autowired(required = false)
    private ClientConfig clientConfig;

    private final static String TRANSACTION_MANAGER_INTERCEPTOR_BEAN_NAME = "transactionManagerInterceptor";

    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }

    @Bean
    public AnnotationEventListenerBeanPostProcessor getAnnotationEventListenerBeanPostProcessor() {
        return new AnnotationEventListenerBeanPostProcessor();
    }

    @Bean(TRANSACTION_MANAGER_INTERCEPTOR_BEAN_NAME)
    public TransactionManagerInterceptor getTransactionManagerInterceptor() {
        return new TransactionManagerInterceptor();
    }

    @Bean
    public TransactionManagerAutoProxyCreator getTransactionManagerAutoProxyCreator() {
        TransactionManagerAutoProxyCreator transactionManagerAutoProxyCreator = new TransactionManagerAutoProxyCreator();
        transactionManagerAutoProxyCreator.setInterceptorNames(TRANSACTION_MANAGER_INTERCEPTOR_BEAN_NAME);
        return transactionManagerAutoProxyCreator;
    }

    @Bean
    @DependsOn({"springBeanFactory"})
    public AggClient getAggClient() {
        return new AggClient(clientConfig);
    }

    @Bean
    public UidGenerator uidGenerator() {
        int timeBits = 28;
        int workBits = 22;
        int seqBits = 13;
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setEpochStr("2022-01-01");
        cachedUidGenerator.setWorkerBits(workBits);
        cachedUidGenerator.setTimeBits(timeBits);
        cachedUidGenerator.setSeqBits(seqBits);
        cachedUidGenerator.setWorkerIdAssigner(new SimpleWorkerIdAssigner(workBits));
        return cachedUidGenerator;
    }

    @Bean
    public UUIDGenerator uuidGenerator() {
        return new DefaultUUIDGenerator(uidGenerator());
    }

}
