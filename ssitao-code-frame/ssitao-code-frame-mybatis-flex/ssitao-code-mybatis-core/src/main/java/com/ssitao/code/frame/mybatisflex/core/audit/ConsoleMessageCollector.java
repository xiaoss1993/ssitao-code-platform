
package com.ssitao.code.frame.mybatisflex.core.audit;


public class ConsoleMessageCollector implements MessageCollector {

    private SqlDebugPrinter printer = (sql, tookTimeMillis) -> {
        if (tookTimeMillis != null) {
            System.out.println("Flex exec sql took " + tookTimeMillis + " ms >>>  " + sql);
        } else {
            System.out.println("Flex exec sql >>>  " + sql);
        }
    };

    public ConsoleMessageCollector() {
    }

    public ConsoleMessageCollector(SqlDebugPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void collect(AuditMessage message) {
        printer.print(message.getFullSql(), message.getElapsedTime());
    }

    public interface SqlDebugPrinter {

        void print(String sql, Long tookTimeMillis);

    }

}
