package com.sunrise.dental.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton database connection provider.
 *
 * The MySQL JDBC driver is loaded explicitly so the application also works
 * reliably when deployed from Eclipse to Tomcat, where JDBC auto-discovery
 * can fail if the connector has not been initialized by the webapp classloader.
 */
public final class DBConnection {
    private static volatile DBConnection instance;

    private static final String DEFAULT_URL =
            "jdbc:mysql://127.0.0.1:3306/sunrise_dental_clinic?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "";

    private final String url;
    private final String user;
    private final String password;

    private DBConnection() {
        this.url = readSetting("SUNRISE_DB_URL", "sunrise.db.url", DEFAULT_URL);
        this.user = readSetting("SUNRISE_DB_USER", "sunrise.db.user", DEFAULT_USER);
        this.password = readSetting("SUNRISE_DB_PASSWORD", "sunrise.db.password", DEFAULT_PASSWORD);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(
                    "MySQL JDBC driver is missing. In Eclipse run Maven > Update Project, "
                    + "then clean/redeploy Tomcat. The dependency must be available in WEB-INF/lib.", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static String readSetting(String envName, String propertyName, String defaultValue) {
        String propertyValue = System.getProperty(propertyName);
        if (propertyValue != null && !propertyValue.isBlank()) {
            return propertyValue;
        }
        String envValue = System.getenv(envName);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        return defaultValue;
    }
}
