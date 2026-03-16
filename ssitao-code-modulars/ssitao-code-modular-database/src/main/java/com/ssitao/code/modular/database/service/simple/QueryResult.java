package com.ssitao.code.modular.database.service.simple;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class QueryResult {
    private List<String> columns;

    private List<List<Object>> data=new ArrayList<>();

}
