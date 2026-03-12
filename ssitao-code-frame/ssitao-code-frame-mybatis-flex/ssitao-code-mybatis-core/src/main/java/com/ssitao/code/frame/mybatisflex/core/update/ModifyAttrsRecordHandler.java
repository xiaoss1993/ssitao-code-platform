
package com.ssitao.code.frame.mybatisflex.core.update;

import com.ssitao.code.frame.mybatisflex.core.util.FieldWrapper;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.apache.ibatis.javassist.util.proxy.MethodHandler;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

class ModifyAttrsRecordHandler implements MethodHandler {

    /**
     * 更新的字段和内容
     */
    private final Map<String, Object> updates = new LinkedHashMap<>();

    public Map<String, Object> getUpdates() {
        return updates;
    }

    @Override
    public Object invoke(Object self, Method originalMethod, Method proxyMethod, Object[] args) throws Throwable {

        String methodName = originalMethod.getName();
        if (methodName.startsWith("set")
            && methodName.length() > 3
            && Character.isUpperCase(methodName.charAt(3))
            && originalMethod.getParameterCount() == 1) {

            String property = StringUtil.firstCharToLowerCase(originalMethod.getName().substring(3));

            //标识 @Column(ignore=true) 的字段，不去更新
            FieldWrapper fw = FieldWrapper.of(originalMethod.getDeclaringClass(), property);
            if (fw != null && fw.isIgnore()) {
                return proxyMethod.invoke(self, args);
            }

            //用 fw.getField().getName() 的原因是 property 可能获取的内容不正确，比如 ID 会得到的内容为 iD
            updates.put(fw == null ? property : fw.getField().getName(), args[0]);
        }

        return proxyMethod.invoke(self, args);
    }


}
