package com.ssitao.code.modular.devtools.writer;

import java.util.List;

/**
 *
 * @since 3.0
 */
public interface CodeWriter {
    String write(List<GeneratedCode> codes);
}
