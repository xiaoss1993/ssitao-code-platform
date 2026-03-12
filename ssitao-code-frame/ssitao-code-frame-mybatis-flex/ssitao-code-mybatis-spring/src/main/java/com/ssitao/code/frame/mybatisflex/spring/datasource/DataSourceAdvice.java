

package com.ssitao.code.frame.mybatisflex.spring.datasource;

import com.ssitao.code.frame.mybatisflex.annotation.UseDataSource;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;/**
 * 多数据源切面
 * <p>
 * 用于在方法执行时拦截 @UseDataSource 注解，实现动态数据源切换。
 * 该切面会拦截类或方法上标注了 @UseDataSource 注解的方法调用，
 * 在方法执行前切换到指定的数据源，方法执行后恢复默认数据源。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class DataSourceAdvice extends AbstractPointcutAdvisor {

    /**
     * 切面通知
     */
    private final Advice advice;

    /**
     * 切入点
     */
    private final Pointcut pointcut;

    /**
     * 构造函数
     * <p>
     * 初始化数据源切面，配置切点和通知
     */
    public DataSourceAdvice() {
        this.advice = new DataSourceInterceptor();

        AnnotationMatchingPointcut cpc = new AnnotationMatchingPointcut(UseDataSource.class, true);
        AnnotationMethodMatcher mpc = new AnnotationMethodMatcher(UseDataSource.class);
        this.pointcut =  new ComposablePointcut(mpc).union(cpc);
    }

    /**
     * 获取切入点
     *
     * @return 切入点对象
     */
    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    /**
     * 获取通知
     *
     * @return 通知对象
     */
    @Override
    public Advice getAdvice() {
        return this.advice;
    }

}
