
package com.ssitao.code.frame.mybatisflex.core.datasource;

import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

public enum DataSourceProperty {

    URL("url", new String[]{"url", "jdbcUrl"}),
    USERNAME("username", new String[]{"username"}),
    PASSWORD("password", new String[]{"password"}),
    ;

    String property;
    String[] methodFlags;

    DataSourceProperty(String property, String[] methodFlags) {
        this.property = property;
        this.methodFlags = methodFlags;
    }

    String[] getGetterMethods() {
        String[] getterMethods = new String[methodFlags.length];
        for (int i = 0; i < methodFlags.length; i++) {
            getterMethods[i] = "get" + StringUtil.firstCharToUpperCase(methodFlags[i]);
        }
        return getterMethods;
    }

    String[] getSetterMethods() {
        String[] getterMethods = new String[methodFlags.length];
        for (int i = 0; i < methodFlags.length; i++) {
            getterMethods[i] = "set" + StringUtil.firstCharToUpperCase(methodFlags[i]);
        }
        return getterMethods;
    }
}
