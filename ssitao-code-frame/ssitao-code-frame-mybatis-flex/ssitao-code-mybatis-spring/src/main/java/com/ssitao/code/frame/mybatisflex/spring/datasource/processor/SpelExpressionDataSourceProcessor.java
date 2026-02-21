package com.ssitao.code.frame.mybatisflex.spring.datasource.processor;

import com.ssitao.code.frame.mybatisflex.core.datasource.processor.DataSourceProcessor;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.BeanResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * SpEL表达式支持处理器
 * <p>
 * 支持使用 Spring Expression Language (SpEL) 表达式动态计算数据源名称。
 * 可以使用 #paramName 或 #{#paramName} 的格式引用方法参数。
 * <p>
 * 示例：
 * <pre>
 * // 使用方法参数作为数据源名称
 * &#64;UseDataSource("#dsKey")
 * void processData(String dsKey);
 *
 * // 使用对象属性
 * &#64;UseDataSource("#user.dataSource")
 * void processUser(User user);
 * </pre>
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SpelExpressionDataSourceProcessor implements DataSourceProcessor {
    /**
     * 动态表达式前缀
     */
    private static final String DYNAMIC_PREFIX = "#";

    /**
     * 参数发现器
     */
    private static final ParameterNameDiscoverer NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    /**
     * SpEL语法解析器
     */
    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * Bean解析器，用于在SpEL表达式中引用Spring Bean
     */
    private BeanResolver beanResolver;

    /**
     * 解析上下文的模板
     * <p>
     * 对于默认不设置的情况下，从参数中取值的方式为 #param1
     * 设置指定模板 ParserContext.TEMPLATE_EXPRESSION 后的取值方式为 #{#param1}
     */
    private ParserContext parserContext = new ParserContext() {

        @Override
        public boolean isTemplate() {
            return false;
        }

        @Override
        public String getExpressionPrefix() {
            return null;
        }

        @Override
        public String getExpressionSuffix() {
            return null;
        }
    };


    /**
     * 处理数据源key
     * <p>
     * 如果数据源key以#开头，则使用SpEL表达式进行解析
     *
     * @param dataSourceKey 原始数据源key
     * @param mapper Mapper对象
     * @param method 执行的方法
     * @param arguments 方法参数
     * @return 解析后的数据源key，如果不需要解析则返回null
     */
    @Override
    public String process(String dataSourceKey, Object mapper, Method method, Object[] arguments) {
        if (StringUtil.noText(dataSourceKey)) return null;
        if (!dataSourceKey.startsWith(DYNAMIC_PREFIX)) return null;
        if (arguments.length == 0) return null;

        RootObject rootObject = new RootObject(method, arguments, mapper);
        StandardEvaluationContext context = new MethodBasedEvaluationContext(rootObject, method, arguments, NAME_DISCOVERER);
        context.setBeanResolver(beanResolver);
        final Object value = PARSER.parseExpression(dataSourceKey, parserContext).getValue(context);
        return value == null ? null : value.toString();
    }
    /**
     * 设置Bean解析器
     *
     * @param beanResolver Bean解析器
     */
    public void setBeanResolver(BeanResolver beanResolver) {
        this.beanResolver = beanResolver;
    }

    /**
     * 设置解析上下文模板
     *
     * @param parserContext 解析上下文
     */
    public void setParserContext(ParserContext parserContext) {
        this.parserContext = parserContext;
    }

    /**
     * SpEL表达式根对象
     * <p>
     * 用于在SpEL表达式中访问方法、参数和目标对象
     */
    public static class RootObject {
        /**
         * 被调用的方法
         */
        private final Method method;

        /**
         * 方法参数数组
         */
        private final Object[] args;

        /**
         * 目标对象
         */
        private final Object target;

        /**
         * 构造函数
         *
         * @param method 被调用的方法
         * @param args 方法参数
         * @param target 目标对象
         */
        public RootObject(Method method, Object[] args, Object target) {
            this.method = method;
            this.args = args;
            this.target = target;
        }

        /**
         * 获取方法
         *
         * @return 方法对象
         */
        public Method getMethod() {
            return method;
        }

        /**
         * 获取方法参数
         *
         * @return 参数数组
         */
        public Object[] getArgs() {
            return args;
        }

        /**
         * 获取目标对象
         *
         * @return 目标对象
         */
        public Object getTarget() {
            return target;
        }
    }

}
