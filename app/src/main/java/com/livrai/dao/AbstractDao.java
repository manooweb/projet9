package com.livrai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDao {

    private static final String DEFAULT_DATABASE_HOST = "localhost";
    private static final String DEFAULT_DATABASE_PORT = "3306";
    private static final String DEFAULT_DATABASE_NAME = "livrai";
    private static final String DEFAULT_DATABASE_USER = "root";
    private static final String DEFAULT_DATABASE_PASSWORD = "password";

    private Connection connection;

    private Connection getConnection() {
        if (connection == null) {
            loadDatabase();
        }
        return connection;
    }

    protected <T> T execute(SqlQuery<T> query) {
        try {
            return query.execute(getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void loadDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        }

        try {
            String host = getEnvironmentVariable("LIVRAI_DB_HOST", DEFAULT_DATABASE_HOST);
            String port = getEnvironmentVariable("LIVRAI_DB_PORT", DEFAULT_DATABASE_PORT);
            String database = getEnvironmentVariable("LIVRAI_DB_NAME", DEFAULT_DATABASE_NAME);
            String user = getEnvironmentVariable("LIVRAI_DB_USER", DEFAULT_DATABASE_USER);
            String password = getEnvironmentVariable("LIVRAI_DB_PASSWORD", DEFAULT_DATABASE_PASSWORD);

            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user,
                    password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getEnvironmentVariable(String name, String defaultValue) {
        String value = System.getenv(name);
        return value == null || value.isEmpty() ? defaultValue : value;
    }

    protected interface SqlQuery<T> {
        T execute(Connection connection) throws SQLException;
    }

}
