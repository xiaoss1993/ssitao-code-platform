package com.ssitao.code.modular.database.service.sql;

import com.ssitao.code.modular.database.service.SqlInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TransactionInfo implements Serializable {
    private static final long serialVersionUID = -4174268983558799472L;
    private String id;

    private List<SqlInfo> sqlHistory=new ArrayList<>();

    private Date createTime;

    private Date lastExecuteTime;

}
