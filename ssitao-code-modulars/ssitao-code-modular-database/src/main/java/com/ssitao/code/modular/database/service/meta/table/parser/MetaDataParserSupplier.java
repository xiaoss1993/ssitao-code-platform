package com.ssitao.code.modular.database.service.meta.table.parser;

import com.ssitao.code.modular.database.service.meta.ObjectMetadata;
import com.tweb.frame.datasource.DatabaseType;

public interface MetaDataParserSupplier<M extends ObjectMetadata>  {
    boolean isSupport(DatabaseType type);

    MetaDataParser<M> get();
}
