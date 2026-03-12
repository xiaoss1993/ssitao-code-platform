
package com.ssitao.code.frame.mybatisflex.core.datasource;

import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.exception.locale.LocalizedFormats;
import com.ssitao.code.frame.mybatisflex.core.util.ConvertUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceBuilder {

    private static final Map<String, String> dataSourceAlias = new HashMap<>();

    static {
        dataSourceAlias.put("druid", "com.alibaba.druid.pool.DruidDataSource");
        dataSourceAlias.put("hikari", "com.zaxxer.hikari.HikariDataSource");
        dataSourceAlias.put("hikaricp", "com.zaxxer.hikari.HikariDataSource");
        dataSourceAlias.put("bee", "cn.beecp.BeeDataSource");
        dataSourceAlias.put("beecp", "cn.beecp.BeeDataSource");
        dataSourceAlias.put("dbcp", "org.apache.commons.dbcp2.BasicDataSource");
        dataSourceAlias.put("dbcp2", "org.apache.commons.dbcp2.BasicDataSource");
    }

    private final Map<String, String> dataSourceProperties;

    public DataSourceBuilder(Map<String, String> dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    public DataSource build() {
        String dataSourceClassName = null;
        String type = dataSourceProperties.get("type");
        if (StringUtil.hasText(type)) {
            dataSourceClassName = dataSourceAlias.getOrDefault(type, type);
        } else {
            dataSourceClassName = detectDataSourceClass();
        }


        if (StringUtil.noText(dataSourceClassName)) {
            if (StringUtil.noText(type)) {
                throw FlexExceptions.wrap(LocalizedFormats.DATASOURCE_TYPE_BLANK);
            } else {
                throw FlexExceptions.wrap(LocalizedFormats.DATASOURCE_TYPE_NOT_FIND, type);
            }
        }

        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Class<?> dataSourceClass = Class.forName(dataSourceClassName, false, contextClassLoader);
            Object dataSourceObject = dataSourceClass.newInstance();
            setDataSourceProperties(dataSourceObject);
            return (DataSource) dataSourceObject;
        } catch (Exception e) {
            throw FlexExceptions.wrap(e, LocalizedFormats.DATASOURCE_CAN_NOT_INSTANCE, dataSourceClassName);
        }
    }

    private void setDataSourceProperties(Object dataSourceObject) throws Exception {
        Reflector reflector = new Reflector(dataSourceObject.getClass());
        for (String attr : dataSourceProperties.keySet()) {
            String value = dataSourceProperties.get(attr);
            String camelAttr = attrToCamel(attr);
            if ("url".equals(camelAttr) || "jdbcUrl".equals(camelAttr)) {
                if (reflector.hasSetter("url")) {
                    reflector.getSetInvoker("url").invoke(dataSourceObject, new Object[]{value});
                } else if (reflector.hasSetter("jdbcUrl")) {
                    reflector.getSetInvoker("jdbcUrl").invoke(dataSourceObject, new Object[]{value});
                }
            } else {
                if (reflector.hasSetter(camelAttr)) {
                    Invoker setInvoker = reflector.getSetInvoker(camelAttr);
                    setInvoker.invoke(dataSourceObject, new Object[]{ConvertUtil.convert(value, setInvoker.getType())});
                }
            }
        }
    }


    public static String attrToCamel(String string) {
        int strLen = string.length();
        StringBuilder sb = new StringBuilder(strLen);
        for (int i = 0; i < strLen; i++) {
            char c = string.charAt(i);
            if (c == '-') {
                if (++i < strLen) {
                    sb.append(Character.toUpperCase(string.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    private String detectDataSourceClass() {
        String[] detectClassNames = new String[]{
            "com.alibaba.druid.pool.DruidDataSource",
            "com.zaxxer.hikari.HikariDataSource",
            "cn.beecp.BeeDataSource",
            "org.apache.commons.dbcp2.BasicDataSource",
        };

        for (String detectClassName : detectClassNames) {
            String result = doDetectDataSourceClass(detectClassName);
            if (result != null) {
                return result;
            }
        }

        return null;
    }


    private String doDetectDataSourceClass(String className) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Class.forName(className, false, contextClassLoader);
            return className;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}
