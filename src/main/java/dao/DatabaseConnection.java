package dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:4308/flower_shop", "MySQL84", "Augu82");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
