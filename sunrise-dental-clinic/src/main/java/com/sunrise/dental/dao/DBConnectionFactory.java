package com.sunrise.dental.dao;
import java.sql.*;
public final class DBConnectionFactory {
    private DBConnectionFactory() {
    }
    public static Connection getConnection() throws SQLException {
        return DBConnection.getInstance().getConnection();
    }
}
