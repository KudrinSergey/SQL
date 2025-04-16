package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(System.getProperty("db.url"),  "app",  "pass");
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (var conn = getConnect()) {
            return runner.query(conn, codeSQL, new ScalarHandler<>());
        }
    }


    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }

    @SneakyThrows
    public static void cleanAuthCodes() {
        try (var connection = getConnect()) {
            runner.execute(connection, "DELETE FROM auth_codes");
        }
    }
}
