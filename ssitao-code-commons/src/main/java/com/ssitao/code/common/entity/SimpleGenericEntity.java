package com.ssitao.code.common.entity;

import lombok.SneakyThrows;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @since 3.0
 */
@ToString
public abstract class SimpleGenericEntity<PK> implements GenericEntity<PK> {

    private static final long serialVersionUID = 4546315942526096290L;

    private PK id;
    private Map<String, Object> properties;

    @Override
    public PK getId() {
        return this.id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String propertyName, T defaultValue) {
        if (null == properties) {
            return defaultValue;
        }
        return (T) properties.getOrDefault(propertyName, defaultValue);
    }

    @Override
    public <T> T getProperty(String propertyName) {
        return getProperty(propertyName, null);
    }

    @Override
    public void setProperty(String propertyName, Object value) {
        if (null == properties) {
            properties = new LinkedHashMap<>();
        }
        properties.put(propertyName, value);
    }

    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public SimpleGenericEntity<PK> clone() {
        return (SimpleGenericEntity) super.clone();
    }
}
