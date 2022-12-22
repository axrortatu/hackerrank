package dao;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDatabaseConnection {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "8463";

    private static final String DATABASE_NAME = "hackerrank";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/" + DATABASE_NAME;

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void closeConnection(Connection connection, Statement statement) {
        try {
            if (connection != null && statement != null) {
                connection.close();
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
