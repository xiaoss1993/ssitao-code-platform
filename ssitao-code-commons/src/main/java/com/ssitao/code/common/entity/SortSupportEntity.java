package com.ssitao.code.common.entity;

public interface SortSupportEntity extends Comparable<SortSupportEntity>, Entity {

    String sortIndex = "sortIndex";

    Long getSortIndex();

    void setSortIndex(Long sortIndex);

    @Override
    default int compareTo(SortSupportEntity support) {
        if (support == null) {
            return -1;
        }

        return Long.compare(getSortIndex() == null ? 0 : getSortIndex(), support.getSortIndex() == null ? 0 : support.getSortIndex());
    }
}
