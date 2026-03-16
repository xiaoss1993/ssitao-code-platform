package com.ssitao.code.modular.dynamic.form.entity;

import lombok.Getter;
import lombok.Setter;
import com.ssitao.code.frame.ezorm.rdb.meta.RDBColumnMetaData;
import com.ssitao.code.commons.bean.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @since 3.0
 */
@Setter
@Getter
public class DictConfig implements Bean {

    private static final long serialVersionUID = 2115608884837210121L;

    private String type;

    private String toField;

    private Map<String, Object> config=new HashMap<>();

    private RDBColumnMetaData column;

}
