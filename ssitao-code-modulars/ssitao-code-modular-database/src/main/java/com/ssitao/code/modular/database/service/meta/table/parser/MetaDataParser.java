package com.ssitao.code.modular.database.service.meta.table.parser;

import com.ssitao.code.modular.database.service.meta.ObjectMetadata;

import java.sql.SQLException;
import java.util.List;

public interface MetaDataParser<M extends ObjectMetadata> {

    List<M> parseAll() throws SQLException;

    M parse(String objectName) throws SQLException;
}
