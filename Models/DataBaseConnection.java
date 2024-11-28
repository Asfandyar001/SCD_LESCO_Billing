
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String JDBC_URL = "jdbc:sqlserver://103.31.104.114:1433;databaseName=SCD_Assignment;user=SCD_User;password=12345678;encrypt=true;trustServerCertificate=true;";

    public DataBaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(JDBC_URL);
        return con;
    }
}
