
package com.ssitao.code.frame.mybatisflex.core.datasource;

import com.ssitao.code.frame.mybatisflex.core.datasource.processor.DataSourceProcessor;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.ClassUtil;
import org.apache.ibatis.logging.LogFactory;

import javax.sql.DataSource;
import java.lang.reflect.Method;

/**
 * @author michael
 */
public class DataSourceManager {

    private static DataSourceDecipher decipher;

    public static DataSourceDecipher getDecipher() {
        return decipher;
    }

    public static void setDecipher(DataSourceDecipher decipher) {
        DataSourceManager.decipher = decipher;
    }

    /**
     * 动态数据源key取值处理
     */
    private static DataSourceProcessor dataSourceProcessor;

    public static DataSourceProcessor getDataSourceProcessor() {
        return dataSourceProcessor;
    }

    public static void setDataSourceProcessor(DataSourceProcessor dataSourceProcessor) {
        DataSourceManager.dataSourceProcessor = dataSourceProcessor;
    }

    private static DataSourceShardingStrategy dataSourceShardingStrategy;

    public static DataSourceShardingStrategy getDataSourceShardingStrategy() {
        return dataSourceShardingStrategy;
    }

    public static void setDataSourceShardingStrategy(DataSourceShardingStrategy dataSourceShardingStrategy) {
        DataSourceManager.dataSourceShardingStrategy = dataSourceShardingStrategy;
    }

    public static void decryptDataSource(DataSource dataSource) {
        if (decipher == null) {
            return;
        }

        try {
            restartDataSource(dataSource);
        } catch (Exception ignored) {
            // do nothing here.
        }

        for (DataSourceProperty property : DataSourceProperty.values()) {
            Method getterMethod = ClassUtil.getAnyMethod(dataSource.getClass(), property.getGetterMethods());
            if (getterMethod != null) {
                String value = invokeMethod(getterMethod, dataSource);
                if (value != null) {
                    value = decipher.decrypt(property, value);
                    Method setter = ClassUtil.getAnyMethod(dataSource.getClass(), property.getSetterMethods());
                    if (setter != null && value != null) {
                        invokeMethod(setter, dataSource, value);
                    }
                }
            }
        }
    }

    static void restartDataSource(DataSource dataSource) {
        Method restartMethod = ClassUtil.getFirstMethod(ClassUtil.getUsefulClass(dataSource.getClass())
            , method -> "restart".equals(method.getName()) && method.getParameterCount() == 0);
        if (restartMethod != null) {
            try {
                restartMethod.invoke(dataSource);
            } catch (Exception e) {
                throw FlexExceptions.wrap(e);
            }
        }
    }


    static String invokeMethod(Method method, Object object, Object... params) {
        try {
            return (String) method.invoke(object, params);
        } catch (Exception e) {
            LogFactory.getLog(DataSourceManager.class).error("Can not invoke method: " + method.getName(), e);
        }
        return null;
    }

    static String processDataSourceKey(String dataSourceKey, Object targetOrProxy, Method method, Object[] arguments) {
        // 如果没有配置 DataSourceProcessor 实例,则不做处理,返回原始值
        return dataSourceProcessor == null ? dataSourceKey : dataSourceProcessor.process(dataSourceKey, targetOrProxy, method, arguments);
    }


    static String getShardingDsKey(String dataSource, Object mapper, Method method, Object[] args) {
        return dataSourceShardingStrategy != null ? dataSourceShardingStrategy.doSharding(dataSource, mapper, method, args) : null;
    }
}
