package com.ssitao.code.modular.database.service.meta.table;

import lombok.*;
import com.ssitao.code.modular.database.service.meta.ObjectMetadata;

import java.util.List;

/**
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableMetadata extends ObjectMetadata {
    private static final long serialVersionUID = 1762059989615865556L;

    private String comment;

    private List<Constraint> constraints;

    private List<ColumnMetadata> columns;
}
