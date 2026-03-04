package com.ssitao.code;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库元数据测试类 - 用于获取表结构
 */
public class DatabaseMetaTest {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ssitao?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "123456";

        try {
            // 注册驱动
            DriverManager.registerDriver(new Driver());

            // 获取连接
            Connection connection = DriverManager.getConnection(url, username, password);
            DatabaseMetaData metaData = connection.getMetaData();

            // 获取所有表
            ResultSet tables = metaData.getTables("ssitao", null, "%", new String[]{"TABLE"});

            List<String> tableNames = new ArrayList<>();
            System.out.println("=== 数据库表列表 ===");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                tableNames.add(tableName);
                System.out.println("- " + tableName);
            }
            tables.close();

            // 获取每个表的列信息
            System.out.println("\n=== 表结构详细信息 ===");
            for (String tableName : tableNames) {
                System.out.println("\n表名: " + tableName);
                System.out.println("----------------------------------------");

                ResultSet columns = metaData.getColumns("ssitao", null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    int columnSize = columns.getInt("COLUMN_SIZE");
                    int nullable = columns.getInt("NULLABLE");
                    String isNullable = nullable == DatabaseMetaData.columnNullable ? "YES" : "NO";
                    String remarks = columns.getString("REMARKS");

                    System.out.println(String.format("  %-20s %-15s %-5s %s",
                        columnName,
                        columnType + "(" + columnSize + ")",
                        isNullable,
                        remarks != null ? remarks : ""));
                }
                columns.close();

                // 获取主键信息
                ResultSet primaryKeys = metaData.getPrimaryKeys("ssitao", null, tableName);
                StringBuilder pk = new StringBuilder("主键: ");
                while (primaryKeys.next()) {
                    pk.append(primaryKeys.getString("COLUMN_NAME")).append(", ");
                }
                if (pk.length() > 3) {
                    System.out.println("  " + pk.substring(0, pk.length() - 2));
                }
                primaryKeys.close();
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
