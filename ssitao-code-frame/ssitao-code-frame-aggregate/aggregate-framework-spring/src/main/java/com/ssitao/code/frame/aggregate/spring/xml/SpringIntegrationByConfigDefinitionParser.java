package com.ssitao.code.frame.aggregate.spring.xml;

import com.ssitao.code.frame.aggregate.spring.support.SpringIntegrationConfiguration;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class SpringIntegrationByConfigDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class<?> getBeanClass(Element element) {
        return SpringIntegrationConfiguration.class;
    }

    protected boolean shouldGenerateIdAsFallback() {
        return true;
    }

    protected void doParse(Element element, BeanDefinitionBuilder builder) {
    }
}
