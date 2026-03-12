
package com.ssitao.code.frame.mybatisflex.core.row;

public interface BatchArgsSetter {

    int getBatchSize();

    Object[] getSqlArgs(int index);

}
