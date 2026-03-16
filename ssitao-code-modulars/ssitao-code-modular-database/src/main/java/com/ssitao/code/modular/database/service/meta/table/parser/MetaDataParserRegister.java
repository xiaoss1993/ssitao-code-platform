package com.ssitao.code.modular.database.service.meta.table.parser;

import com.ssitao.code.modular.database.service.meta.ObjectMetadata;
import com.tweb.frame.datasource.DatabaseType;

public interface MetaDataParserRegister {
    <M extends ObjectMetadata> void registerMetaDataParser(DatabaseType databaseType, ObjectMetadata.ObjectType objectType, MetaDataParser<M> parser);
}
