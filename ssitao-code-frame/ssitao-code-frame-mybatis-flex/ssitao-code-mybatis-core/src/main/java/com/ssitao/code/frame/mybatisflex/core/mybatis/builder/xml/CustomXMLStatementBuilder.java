package com.ssitao.code.frame.mybatisflex.core.mybatis.builder.xml;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.builder.xml.XMLStatementBuilder;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;


/**
 * 重写 XMLStatementBuilder
 * <p>
 * 1.增加自定义主键策略
 *
 */
public class CustomXMLStatementBuilder extends XMLStatementBuilder {
    private final MapperBuilderAssistant builderAssistant;
    private final XNode context;
    private final String requiredDatabaseId;

    public CustomXMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, XNode context) {
        this(configuration, builderAssistant, context, null);
    }

    public CustomXMLStatementBuilder(Configuration configuration, MapperBuilderAssistant builderAssistant, XNode context, String databaseId) {
        super(configuration, builderAssistant, context, databaseId);
        this.builderAssistant = builderAssistant;
        this.context = context;
        this.requiredDatabaseId = databaseId;
    }
    @Override
    public void parseStatementNode() {

    }

}
